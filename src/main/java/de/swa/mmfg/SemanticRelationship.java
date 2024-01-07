package de.swa.mmfg;

import java.net.URL;

/** data type to represent semantic relationships **/
public class SemanticRelationship {
	private URL relatedNode;
	private String description;
	
	public SemanticRelationship() {}
	public SemanticRelationship(URL relatedNode, String description) {
		this.relatedNode = relatedNode;
		this.description = description;
	}

	public URL getRelatedNode() {
		return relatedNode;
	}

	public void setRelatedNode(URL relatedNode) {
		this.relatedNode = relatedNode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
