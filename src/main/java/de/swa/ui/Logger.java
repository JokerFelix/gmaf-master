package de.swa.ui;

import java.util.Vector;

public class Logger {
	private static Logger instance = null;
	public static Logger getInstance() {
		if (instance == null) instance = new Logger();
		return instance;
	}

	private Vector<LogListener> listeners = new Vector<LogListener>();
	public void addLogger(LogListener ll) {
		listeners.add(ll);
	}
	
	public void log(String msg) {
		System.out.println("Log: " + msg);
		for (LogListener ll : listeners) ll.log(msg);
	}
}
