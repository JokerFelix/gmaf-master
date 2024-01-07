package de.swa.gmaf.api;

import java.util.Hashtable;
import java.util.UUID;

import de.swa.gmaf.GMAF;

public class GMAF_SessionFactory {
	public static String API_KEY;
	
	private GMAF_SessionFactory() {}
	private static GMAF_SessionFactory instance = null;
	public static GMAF_SessionFactory getInstance() {
		if (instance == null) instance = new GMAF_SessionFactory();
		return instance;
	}
	
	private Hashtable<String, GMAF> sessions = new Hashtable<String, GMAF>();

	public String getAuthToken(String api_key) {
		if (api_key.equals(API_KEY)) {
			UUID session = UUID.randomUUID();
			sessions.put(session.toString(), new GMAF());
			return session.toString();
		}
		return null;
	}

	public GMAF getGmaf(String session_id) {
		return sessions.get(session_id);
	}
}
