package de.swa.mmfg.extension;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Vector;

import de.swa.gmaf.extensions.Explainable;
import de.swa.gmaf.extensions.defaults.Word;
import de.swa.mmfg.extension.esmmfg.Phrase;

/**
 * language generation based on Linguatools
 * https://rapidapi.com/petapro/api/linguatools-sentence-generating/
 */
public class ComplexLanguageModel extends LanguageModel {
	public String produceText(Explainable exp, int levelOfDetail) {
		Phrase psTree = exp.getPSTree();
		StringBuffer sb = new StringBuffer();
		produceText(psTree, sb);
		return sb.toString();
	}

	boolean hasNoun = false;
	boolean hasVerb = false;
	boolean hasObject = false;

	private void produceText(Phrase p, StringBuffer text) {
		for (Phrase pi : p.getSubPhrases()) {
			produceText(pi, text);
		}
		try {
			String query = "";
			Vector<Word> words = p.getWords();
			if (p.getType() == Phrase.V) {
				if (p.getSubPhrases().size() >= 2) {
					query += "subject=" + trim(p.getSubPhrases().get(0).getPhraseText()) + "&";
					query += "object=" + trim(p.getSubPhrases().get(1).getPhraseText()) + "&";
					query += "verb=" + trim(words.get(0).getWord()) + "&";

					hasNoun = true;
					hasVerb = true;
					hasObject = true;
				}
			} else if (p.getType() == Phrase.SENTENCE) {
				for (Phrase subPhrase : p.getSubPhrases()) {
					Word w = subPhrase.getWords().get(0);
					if (subPhrase.getType() == Phrase.N && !hasNoun) {
						query += "subject=" + w.getWordStem() + "&";
						hasNoun = true;
					} else if (subPhrase.getType() == Phrase.N && hasNoun) {
						query += "verb=" + w.getWordStem() + "&";
						hasObject = true;
					} else if (subPhrase.getType() == Phrase.V) {
						query += "object=" + w.getWordStem() + "&";
						hasVerb = true;
					}
				}
			}

			if (hasNoun && hasVerb && hasObject) {
				try {
					if (query.indexOf("root-image") <= 0 || true) {
						HttpRequest request = HttpRequest.newBuilder()
								.uri(URI.create(
										"https://linguatools-sentence-generating.p.rapidapi.com/realise?" + query))
								.header("x-rapidapi-host", "linguatools-sentence-generating.p.rapidapi.com")
								.header("x-rapidapi-key", "282bc55d1fmshe44adc5330a9cb0p1f2d91jsn482bdecd391a")
								.method("GET", HttpRequest.BodyPublishers.noBody()).build();
						HttpResponse<String> response = HttpClient.newHttpClient().send(request,
								HttpResponse.BodyHandlers.ofString());
						String resp = response.body();
						String sentence = resp.substring(resp.indexOf("sentence") + 11, resp.lastIndexOf("}") - 2) + ". ";
						if (sentence.indexOf("error") <= 0)
							text.append(sentence);
					}

					hasNoun = false;
					hasVerb = false;
					hasObject = false;
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		} catch (Exception x) {
			x.printStackTrace();
		}
		return;
	}

	private String trim(String text) {
		if (text.startsWith("is related"))
			return "has";
		text = text.replace("the", " ");
		text = text.replace(" ", "");
		text = text.replace("\n", "");
		return text;
	}
}
