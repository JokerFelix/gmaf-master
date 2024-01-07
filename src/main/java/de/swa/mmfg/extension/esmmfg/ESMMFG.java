package de.swa.mmfg.extension.esmmfg;

import java.util.Vector;

import de.swa.gmaf.extensions.Explainable;
import de.swa.gmaf.extensions.defaults.GeneralDictionary;
import de.swa.gmaf.extensions.defaults.Word;
import de.swa.mmfg.extension.smmfg.SMMFG;
import de.swa.mmfg.extension.smmfg.SemanticRelationshipRepresentation;

public class ESMMFG implements Explainable {
	public Phrase psTree = new Phrase();
	
	public ESMMFG(SMMFG smmfg) {
		Vector<SemanticRelationshipRepresentation> srrs = smmfg.getSemanticRelationships();
		for (SemanticRelationshipRepresentation srr : srrs) {
			Phrase p = Phrase.getPhraseForRelationship(Phrase.V);
			p.addPhrase(new Phrase(GeneralDictionary.getInstance().getFirstWord(srr.getDomainNode().getName()), Phrase.N));
			p.addPhrase(new Phrase(GeneralDictionary.getInstance().getFirstWord(srr.getRangeNode().getName()), Phrase.N));
			psTree.addPhrase(p);
		}
	}
	
	public Phrase getPSTree() {
		return psTree;
	}
}
