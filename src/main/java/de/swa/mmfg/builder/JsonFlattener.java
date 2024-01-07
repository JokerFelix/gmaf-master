package de.swa.mmfg.builder;

import com.google.gson.Gson;

import de.swa.mmfg.MMFG;

/** exports a MMFG as JSon **/

public class JsonFlattener implements Flattener {
	public String flatten(MMFG fv) {
		Gson gson = new Gson();
		String json = gson.toJson(fv);
		return json;
	}
	
	public String getFileExtension() {
		return "json";
	}
	public String endFile() { return ""; }
	public String startFile() { return ""; }

}
