package de.swa.mmfg.extension;

import de.swa.gmaf.extensions.Explainable;
import de.swa.mmfg.extension.esmmfg.Phrase;

public class SimpleLanguageModel extends LanguageModel {
	public String produceText(Explainable exp, int levelOfDetail) {
		Phrase rootPhrase = exp.getPSTree();
		String text = rootPhrase.getPhraseText();
		text = text.replace(".", ".\n");
		return text;
	}
}
