package de.swa.gc;

import java.io.File;
import java.io.RandomAccessFile;

import com.google.gson.Gson;

import de.swa.mmfg.MMFG;
import de.swa.mmfg.builder.Flattener;

/** Utility Class for Graph Code Import and Export, implements the MMFG-Flattener-Interface
 * 
 * @author stefan_wagenpfeil
 */

public class GraphCodeIO implements Flattener {
	/** exports a Graph Code based on a MMFG to Json **/
	public String flatten(MMFG fv) {
		GraphCode gc = GraphCodeGenerator.generate(fv);
		Gson g = new Gson();
		String s = g.toJson(gc);
		return s;
	}

	/** returns "json" **/
	public String getFileExtension() {
		return "json";
	}

	/** reads a Graph Code from a Json-File **/
	public static GraphCode read(File f) {
		Gson gson = new Gson();
		String s = "";
		try {
			RandomAccessFile rf = new RandomAccessFile(f, "r");
			String line = "";
			while ((line = rf.readLine()) != null) {
				s += line + "\n";
			}
			rf.close();
		} catch (Exception x) {
			x.printStackTrace();
		}
		GraphCode gc = gson.fromJson(s, GraphCode.class);
		return gc;
	}
	
	/** writes a Graph Code as Json into File f **/
	public static void write(GraphCode gc, File f) {
		Gson gson = new Gson();
		String s = gson.toJson(gc);
		try {
			RandomAccessFile rf = new RandomAccessFile(f, "rw");
			rf.setLength(0);
			rf.writeBytes(s);
			rf.close();
		} catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	/** returns a Graph Code as JSon **/
	public static String asJson(GraphCode gc) {
		Gson gson = new Gson();
		String s = gson.toJson(gc);
		return s;
	}

	/** for Graph Codes, no header is required **/
	public String endFile() { return ""; }
	
	/** for Graph Codes, no footer is required **/
	public String startFile() { return ""; }

}
