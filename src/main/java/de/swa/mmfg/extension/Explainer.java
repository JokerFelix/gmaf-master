package de.swa.mmfg.extension;

import de.swa.gmaf.extensions.Explainable;

public class Explainer {
	public static String explain(Explainable exp, int levelOfDetail, int languageLevel) {
		LanguageModel model = LanguageModel.getInstance(languageLevel);
		String text = model.produceText(exp, levelOfDetail);
		return text;
	}
}
