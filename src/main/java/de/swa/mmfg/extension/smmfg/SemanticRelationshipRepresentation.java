package de.swa.mmfg.extension.smmfg;

public class SemanticRelationshipRepresentation {
	private String name;
	private AnnotationAnchor anchor;
	private SemanticNodeRepresentation rangeNode, domainNode;
	
	public SemanticRelationshipRepresentation(String name, AnnotationAnchor anchor, SemanticNodeRepresentation domainNode, SemanticNodeRepresentation rangeNode) {
		this.name = name;
		this.anchor = anchor;
		this.domainNode = domainNode;
		this.rangeNode = rangeNode;
	}
	
	public String getName() {
		return name;
	}
	
	public AnnotationAnchor getAnchor() {
		return anchor;
	}
	
	public SemanticNodeRepresentation getRangeNode() {
		return rangeNode;
	}
	
	public SemanticNodeRepresentation getDomainNode() {
		return domainNode;
	}
}
