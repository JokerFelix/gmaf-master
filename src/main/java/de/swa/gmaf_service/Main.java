package de.swa.gmaf_service;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.Vector;
import java.util.Scanner;

import de.swa.gmaf.*;
import de.swa.mmfg.Location;
import de.swa.mmfg.MMFG;
import de.swa.mmfg.Node;
import de.swa.mmfg.Security;
import de.swa.mmfg.builder.FeatureVectorBuilder;
import de.swa.mmfg.builder.XMLEncodeDecode;
import de.swa.ui.MMFGCollection;
import de.swa.ui.Configuration;
import de.swa.gc.GraphCode;
import de.swa.gc.GraphCode;
import de.swa.gc.GraphCodeIO;
import de.swa.gc.GraphCodeGenerator;


public class Main {
	
	public static void main(String[] args) throws InterruptedException, java.io.IOException {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("To cancel, keep empty or type 'exit', hit return.");
			System.out.print("Enter name of file, absolute or relative path: ");
			String filepath = sc.nextLine();
			if (filepath.isEmpty() || filepath.toLowerCase() == "exit") break;

			GMAF gmaf = new GMAF();
			try {
				File f = new File(filepath);
				FileInputStream fs = new FileInputStream(f);
				byte[] bytes = fs.readAllBytes();
				System.out.println("\nFile to process: " + f.getAbsolutePath());
				MMFG fv = gmaf.processAsset(bytes, f.getName(), 
							"system", Configuration.getInstance().getMaxRecursions(),
						Configuration.getInstance().getMaxNodes(), f.getName(), f);
				System.out.println("Detection and MMFG construction done. Saving..");

				String xml = FeatureVectorBuilder.flatten(fv, new XMLEncodeDecode());
				RandomAccessFile rf = new RandomAccessFile(
						Configuration.getInstance().getMMFGRepo() + 
						File.separatorChar + f.getName() + ".mmfg", "rw");
				rf.setLength(0);
				rf.writeBytes(xml);
				rf.close();

				GraphCode gc = GraphCodeGenerator.generate(fv);
				GraphCodeIO.write(gc, new File(
						Configuration.getInstance().getGraphCodeRepository() + File.separatorChar + f.getName() + ".gc"));
				System.out.println("\nEntire file processing done.\n");


			}
			catch (Exception x) {
				x.printStackTrace();
			}
		}
	}
}
