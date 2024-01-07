package de.swa.mmfg.extension;

import de.swa.gmaf.extensions.Explainable;
import de.swa.mmfg.extension.esmmfg.Phrase;

public abstract class LanguageModel {
	public static final int SIMPLE = 0;
	public static final int NORMAL = 1;
	public static final int COMPLEX = 2;
	
	public static LanguageModel getInstance(int languageModel) {
		if (languageModel == SIMPLE) return new SimpleLanguageModel();
		else if (languageModel == COMPLEX) return new ComplexLanguageModel();
		else return new DefaultLanguageModel();
	}
	
	public abstract String produceText(Explainable exp, int levelOfDetail);
}
