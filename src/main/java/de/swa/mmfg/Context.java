package de.swa.mmfg;

import java.util.UUID;
import java.util.Vector;

/** data type to represent the Context **/
public class Context {
	public static Vector<Context> allContexts = new Vector<Context>();
	
	private String name;
	private UUID id;
	
	public Context() {}
	public Context(UUID id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	public static Context getDefaultContext() {
		if (allContexts.size() > 0) {
			for (Context c : allContexts) {
				if (c.getName().equals("Default")) return c;
			}
		}
		Context c = new Context(UUID.randomUUID(), "Default");
		allContexts.add(c);
		return c;
	}	
	
}
