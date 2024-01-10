package de.swa.gmaf.plugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;
import java.util.Arrays;
import java.util.ArrayList;

import de.swa.gmaf.GMAF;
import de.swa.mmfg.MMFG;
import de.swa.mmfg.Node;
import de.swa.mmfg.Context;
import de.swa.mmfg.TechnicalAttribute;
import de.swa.mmfg.Weight;

/** automated avatar detection **/
public class AvatarDetector implements GMAF_Plugin {
	public boolean canProcess(String extension) {
		String[] extensions = {".png", ".jpeg", ".jpg"};
		for (String ext : extensions) {
			if (ext.equalsIgnoreCase(extension)) return true;
		}
		return false;
	}

	public void process(URL url, File f, byte[] bytes, MMFG fv) {
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			Process process;

		if (osName.contains("win")) {
		// Windows commands
			String command0 = "cd yolov7\\";
			String command1 = "rmdir /s /q runs\\detect";
			String command2 = "yolodep\\bin\\activate";
			String command3 = "python detect.py --weights best.pt --conf 0.245 --img-size 640 --source " + f.getAbsolutePath() +" --save-txt --agnostic-nms --save-conf";
			String command = String.join(" && ",command0, command1, command2, command3);
			String[] cmd = {"cmd.exe", "/C", command};
			process = new ProcessBuilder(cmd).start();
		} else if (osName.contains("mac") || osName.contains("linux")) {
		// Mac (or Unix/Linux) commands
			String command0 = "cd yolov7/";
			String command1 = "rm -rf runs/detect";
			String command2 = "source yolodep/bin/activate";
			String command3 = "python detect.py --weights best.pt --conf 0.245 --img-size 640 --source " + f.getAbsolutePath() +" --save-txt --agnostic-nms --save-conf";
			String command = String.join(" && ", command0, command1, command2, command3);
			String[] cmd = {"/bin/bash", "-c", command};
			process = new ProcessBuilder(cmd).start();
		} else {
			throw new UnsupportedOperationException("Unsupported operating system: " + osName);
		}

		int exitCode = process.waitFor();
		// Make sure detect.py worked then import, convert and add to mmfg
		if (exitCode == 0) {
			// Load image to read dimensions.
			BufferedImage image = ImageIO.read(f);
			int width = image.getWidth();
			int height = image.getHeight();
			System.out.println("Input Image Dimensions: ");
			System.out.println("Width: " + width + "pixels");
			System.out.println("Height: " + height + "pixels");

			// Avatars Node
			Node avatars = new Node("Avatars", fv);

			// Read from last run in yolov7/runs/detect/exp/labels/filename.txt
			String fileDetections = "yolov7/runs/detect/exp/labels/" 
				+ stripExtension(f.getName())
				+ ".txt";
			BufferedReader reader = new BufferedReader(new FileReader(fileDetections));
			String line;
			while ((line = reader.readLine()) != null) {
				// Split by space seperator.
				String[] words = line.split(" ");
				Node avatar; 
				if (words[0].equals("0")) {
					avatar = new Node("HumanAvatar", fv);
				} else {
					avatar = new Node("NonHumanAvatar", fv);
				}

				TechnicalAttribute tecAt = new TechnicalAttribute(); 
				tecAt.setRelative_x(convertRelative(words[1], width));
				tecAt.setRelative_y(convertRelative(words[2], width));
				tecAt.setHeight(convertRelative(words[4], width));
				tecAt.setWidth(convertRelative(words[3], width));
				tecAt.setSharpness(1.0f);
				tecAt.setBlurryness(0.0f);
				avatar.addTechnicalAttribute(tecAt);
				
				Context gmafContext = new Context();
				gmafContext.setName("Confidence");
				Weight weight = new Weight(gmafContext, Float.parseFloat(words[5]));
				avatar.addWeight(weight);

				avatars.addChildNode(avatar);
			}
	
			System.out.println("Detection and MMFG construction done.\n");
			
		} else {
			System.out.println("Error executing commands\n");
		}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	Vector<Node> nodes = new Vector<Node>();
	public Vector<Node> getDetectedNodes() {
		return nodes;
	}

	public boolean isGeneralPlugin() {
		return false;
	}


	public boolean providesRecoursiveData() {
		return false;
	}
	
	private static String stripExtension(String filename) {
		int dotIndex = filename.lastIndexOf(".");
		// Checks if dot is not the first or last char, then takes last dot.
		if (dotIndex > 0 && dotIndex < filename.length() - 1) {
			return filename.substring(0, dotIndex);
		}
		// Returns original filename if there is no extension.
		return filename;
	}

	private static int convertRelative(String a, int b) {
		int result;
		float fa = Float.parseFloat(a);
		return result = (int) (fa * b);
	}
}
