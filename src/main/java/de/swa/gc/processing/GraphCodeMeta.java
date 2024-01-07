package de.swa.gc.processing;

import de.swa.gc.GraphCode;

public class GraphCodeMeta {
	private String fileName;
	private float[] metric = new float[] {0f,0f,0f};
	private GraphCode graphcode;
	
	public GraphCodeMeta(String file, GraphCode gc) {
		fileName = file;
		graphcode = gc;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public float[] getMetric() {
		return metric;
	}
	public void setMetric(float[] metric) {
		this.metric = metric;
	}
	public GraphCode getGraphcode() {
		return graphcode;
	}
	public void setGraphcode(GraphCode graphcode) {
		this.graphcode = graphcode;
	}
}
