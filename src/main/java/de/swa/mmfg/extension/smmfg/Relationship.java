package de.swa.mmfg.extension.smmfg;

import de.swa.mmfg.Node;

public class Relationship {
	private Node n1, n2;
	private int type;

	
	public static final int TYPE_CHILD = 1;
	public static final int TYPE_SPACIAL = 2;
	public static final int TYPE_SEMANTIC = 3;
	public static final int TYPE_TECHNICAL = 4;
	
	
	public Relationship(Node n1, Node n2, int type) {
		this.n1 = n1;
		this.n2 = n2;
		this.type = type;
	}
	
	public int getType() {
		return type;
	}
	
	public Node getSource() {
		return n1;
	}
	
	public Node getTarget() {
		return n2;
	}
}
