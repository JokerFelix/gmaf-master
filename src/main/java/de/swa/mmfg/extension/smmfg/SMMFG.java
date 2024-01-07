package de.swa.mmfg.extension.smmfg;

import java.util.Vector;

import de.swa.mmfg.MMFG;
import de.swa.mmfg.Node;

public class SMMFG {
	private Vector<SemanticRelationshipRepresentation> srrs = new Vector<SemanticRelationshipRepresentation>();
	
	public SMMFG(MMFG mmfg) {
		try {
			Vector<Node> nodes = mmfg.getAllNodes();
			for (Node n : nodes) {
				SemanticNodeRepresentation domainNode = new SemanticNodeRepresentation(n.getName(), new AnnotationAnchor(n));
				for (Node child : n.getChildNodes()) {
					SemanticNodeRepresentation rangeNode = new SemanticNodeRepresentation(child.getName(), new AnnotationAnchor(child));
					SemanticRelationshipRepresentation srr = new SemanticRelationshipRepresentation("child", new AnnotationAnchor(new Relationship(n, child, Relationship.TYPE_CHILD)), domainNode, rangeNode);
					srrs.add(srr);
				}
			}
		}
		catch (Exception x) {
			x.printStackTrace();
		}
	}
	
	public Vector<SemanticRelationshipRepresentation> getSemanticRelationships() {
		return srrs;
	}
}
