package de.swa.mmfg.extension.smmfg;

import java.util.Vector;

import de.swa.gmaf.extensions.defaults.GeneralDictionary;
import de.swa.gmaf.extensions.defaults.Word;
import de.swa.mmfg.Node;

public class SemanticVocabulary {
	public static Word getSemanticRelationshipVocabularyTerm(SemanticRelationshipRepresentation srr) {
		int type = srr.getAnchor().getAnnotationRelationshipForRelationship().getType();
		if (type == Relationship.TYPE_CHILD) return GeneralDictionary.getInstance().getWord("belong").get(0);
		if (type == Relationship.TYPE_SEMANTIC) return GeneralDictionary.getInstance().getWord("describe").get(0);
		if (type == Relationship.TYPE_SPACIAL) return GeneralDictionary.getInstance().getWord("locates").get(0);
		if (type == Relationship.TYPE_TECHNICAL) return GeneralDictionary.getInstance().getWord("detail").get(0);
		return GeneralDictionary.getInstance().getWord("link").get(0);
	}
	
	public static Word getSemanticFeatureVocabularyTerm(SemanticNodeRepresentation snr) {
		Node n = snr.getAnchor().getAnnotationRelationshipForNode();
		Vector<Word> w = GeneralDictionary.getInstance().getWord(n.getName());
		return w.get(0);
	}
}
