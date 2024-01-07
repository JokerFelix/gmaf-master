package de.swa.gc.processing;

import java.util.Vector;

import de.swa.gc.GraphCode;

public abstract class CollectionProcessor {
	public static final int SIMILARITY = 1;
	public static final int RECOMMENDATION = 2;
	
	protected int operation;
	protected GraphCode gcQuery;
	protected String gcName;
	
	public void preloadIndex(Vector<GraphCodeMeta> collection) { }
	public void setQueryObject(GraphCode gc) {
		gcQuery = gc;
	}
		
	public final void setOperation(int i) {
		operation = i;
	}
	
	public abstract void execute();
	
	public abstract Vector<GraphCodeMeta> getResultList();
}
