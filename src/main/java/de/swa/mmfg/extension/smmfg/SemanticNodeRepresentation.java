package de.swa.mmfg.extension.smmfg;

public class SemanticNodeRepresentation {
	private String name;
	private AnnotationAnchor anchor;

	public SemanticNodeRepresentation(String name, AnnotationAnchor anchor) {
		this.name = name;
		this.anchor = anchor;
	}
	
	public String getName() {
		return name;
	}
	
	public AnnotationAnchor getAnchor() {
		return anchor;
	}
}
