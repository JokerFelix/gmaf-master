package de.swa.gmaf.plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.URL;
import java.net.URLConnection;
import java.util.Vector;

import de.swa.gmaf.GMAF;
import de.swa.mmfg.MMFG;
import de.swa.mmfg.Node;

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
			String command3 = "python detect.py --weights best.pt --conf 0.48 --img-size 640 --source " + f.getAbsolutePath() +" --save-txt";
			String command = String.join(" && ",command0, command1, command2, command3);
			String[] cmd = {"cmd.exe", "/C", command};
			process = new ProcessBuilder(cmd).start();
		} else if (osName.contains("mac") || osName.contains("linux")) {
		// Mac (or Unix/Linux) commands
			String command0 = "cd yolov7/";
			String command1 = "rm -rf runs/detect";
			String command2 = "source yolodep/bin/activate";
			String command3 = "python detect.py --weights best.pt --conf 0.48 --img-size 640 --source " + f.getAbsolutePath() +" --save-txt";
			String command = String.join(" && ", command0, command1, command2, command3);
			String[] cmd = {"/bin/bash", "-c", command};
			process = new ProcessBuilder(cmd).start();
		} else {
			throw new UnsupportedOperationException("Unsupported operating system: " + osName);
		}

		int exitCode = process.waitFor();
		if (exitCode == 0) {
			System.out.println("Commands executed successfully");
			// Make sure detect.py is adapted to --save-txt in matching format
			// Read from the file of the last run in yolov7/runs/detect/exp/labels/filename.txt
		} else {
			System.out.println("Error executing commands");
		}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		/*
		try {
			System.out.println("AvatarDetector process...");
			String[] cmd = {"python3", "detect.py"};
			Process process = Runtime.getRuntime().exec(cmd);
			InputStream inputStream = process.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder output = new StringBuilder();
			String line;

			// Read from the input stream and append to the StringBuilder
			while ((line = reader.readLine()) != null) {
				output.append(line).append(System.lineSeparator());
			}

			// Convert the StringBuilder to a String
			String result = output.toString();

			// Close the resources
			reader.close();
			inputStream.close();

			// Output stored in result variable
			System.out.println("Command Output: \n" + result);
		} 
		catch (Exception x) {
			x.printStackTrace();
		}

		if (!f.exists()) {
			// create the video out of the bytes
			try {
				f = new File("temp/" + System.currentTimeMillis() + ".mp4");
				FileOutputStream fout = new FileOutputStream(f);
				fout.write(bytes);
				fout.close();
			}
			catch (Exception x) {
				x.printStackTrace();
			}
		}
		if (f.exists()) {
			// create folder for the scene screenshots
			File scene_screenshots = new File("temp/" + f.getName() + "_Scenes");
			scene_screenshots.mkdirs();
			String[] cmd = {"ffmpeg", "-i", 
					f.getAbsolutePath(), 
					"-vf", "select='gt(scene\\,0,4)'", 
					"-vsync", "vfr", 
					"temp/" + f.getName() + "_Scenes/frame_%d.png"};
			try {
				Runtime.getRuntime().exec(cmd);
				
				File[] fs = new File("temp/" + f.getName() + "_Scenes").listFiles();
				GMAF gmaf = new GMAF();
				for (File fi : fs) {
					MMFG sceneMMFG = gmaf.processAsset(fi);
					String name = fi.getName();
					String timecode = name.substring(name.indexOf("_"), name.indexOf("."));
					Node tcNode = new Node(timecode, fv);
					for (Node n : sceneMMFG.getNodes()) {
						tcNode.addChildNode(n);
						nodes.add(n);
					}
					fv.addNode(tcNode);
					nodes.add(tcNode);
				}
			}
			catch (Exception x) {
				x.printStackTrace();
			}
		}
	*/
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
}
