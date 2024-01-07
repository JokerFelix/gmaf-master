package de.swa.mmfg.extension.smmfg;

import de.swa.mmfg.Node;

public class AnnotationAnchor {
	private Node nodeAnchor;
	private Relationship relationshipAnchor;
	
	public AnnotationAnchor(Node n) {
		setAnnotationRelationship(n);
	}
	
	public AnnotationAnchor(Relationship r) {
		relationshipAnchor = r;
	}
	
	public void setAnnotationRelationship(Node n) {
		nodeAnchor = n;
	}
	
	public void setAnnotationRelationship(Relationship r) {
		relationshipAnchor = r;
	}
	
	public Node getAnnotationRelationshipForNode() {
		return nodeAnchor;
	}
	
	public Relationship getAnnotationRelationshipForRelationship() {
		return relationshipAnchor;
	}

}
