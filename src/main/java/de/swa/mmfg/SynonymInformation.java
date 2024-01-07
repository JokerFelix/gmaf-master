package de.swa.mmfg;

/** data type to represent synonym information **/
public class SynonymInformation {
	private static final int TYPE_AGGREGATION = 50;
	private static final int TYPE_GENERALISATION = 51;
	private static final int TYPE_SYNONYM = 52;
	
	private int type;
	private Node relatedObject;
	
	public SynonymInformation() {}
	public SynonymInformation(int type, Node n) {
		this.type = type;
		relatedObject = n;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Node getRelatedObject() {
		return relatedObject;
	}
	public void setRelatedObject(Node relatedObject) {
		this.relatedObject = relatedObject;
	}
}
