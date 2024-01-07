package de.swa.mmfg.extension.esmmfg;

import java.util.Vector;

import de.swa.gmaf.extensions.defaults.GeneralDictionary;
import de.swa.gmaf.extensions.defaults.Word;
import de.swa.mmfg.CompositionRelationship;

public class Phrase {
	public static final int ROOT = 1;
	public static final int SENTENCE = 2;
	public static final int NP = 3;
	public static final int VP = 4;
	public static final int N = 5;
	public static final int V = 6;
	public static final int DET = 7;
	public static final int PP = 8;
	public static final int PR = 9;
	private int type = 0;

	public enum type {
		ROOT, SENTENCE, NP, VP, N, V, DET, PP, PR
	};

	private Word word;
	private type myType;
	private Vector<Phrase> subPhrases = new Vector<Phrase>();
	private Vector<Word> allWords = new Vector<Word>();

	public Phrase(Word w, int type) {
		this.word = w;
		this.type = type;
		allWords.add(w);
	}

	public Phrase(Word w, int tp, Word... words) {
		this.word = w;
		this.type = tp;
		allWords.add(w);
		for (Word w2 : words)
			allWords.add(w2);
	}

	public Phrase(Phrase... subphrase) {
		for (Phrase p : subphrase) {
			subPhrases.add(p);
		}
	}
	
	public Vector<Word> getWords() {
		return allWords;
	}

	public String getPhraseText() {
		StringBuffer phrases = new StringBuffer();
		append(phrases);
		return phrases.toString();
	}

	private void append(StringBuffer sb) {
		if (type == N)
			sb.append(word.getWord());
		else if (type == V) {
			if (subPhrases.size() == 2) {
				sb.append(subPhrases.get(0).getPhraseText());
				sb.append(word.getWord() + " ");
				sb.append(subPhrases.get(1).getPhraseText());
				sb.append(". ");
			}
			else sb.append(word.getWord() + " ");
		}
		else if (type == PP) {
			sb.append(word.getWord() + " ");
		}
		else if (type == DET)
			sb.append("the");
		else if (type == SENTENCE) {
			String sentence = "";
			for (Phrase p : subPhrases) {
				sentence += p.getPhraseText() + " ";
			}
			sentence += " ";
			sb.append(sentence);
		}
		else if (subPhrases.size() > 0) {
			String sentence = "";
			for (Phrase p : subPhrases) {
				sentence += p.getPhraseText() + " ";
			}
			sentence += " ";
			sb.append(sentence);
		}
	}
	
	public int getType() {
		return type;
	}
	
	public Vector<Phrase> getSubPhrases() {
		return subPhrases;
	}

	public void addPhrase(Phrase p) {
		subPhrases.add(p);
	}

	public static Phrase getPhraseForRelationship(int val) {
		GeneralDictionary dict = GeneralDictionary.getInstance();
		Phrase relationship = null;
		if (val == CompositionRelationship.RELATION_ABOVE)
			relationship = new Phrase(new Phrase(dict.getFirstWord("be"), Phrase.V),
					new Phrase(dict.getFirstWord("above"), Phrase.PP));
		else if (val == CompositionRelationship.RELATION_ATTACHED_TO)
			relationship = new Phrase(new Phrase(dict.getFirstWord("be"), Phrase.V),
					new Phrase(dict.getFirstWord("attached"), Phrase.PP),
					new Phrase(dict.getFirstWord("to"), Phrase.PP));
		else if (val == CompositionRelationship.RELATION_BEFORE)
			relationship = new Phrase(new Phrase(dict.getFirstWord("be"), Phrase.V),
					new Phrase(dict.getFirstWord("before"), Phrase.PP));
		else if (val == CompositionRelationship.RELATION_BEHIND)
			relationship = new Phrase(new Phrase(dict.getFirstWord("be"), Phrase.V),
					new Phrase(dict.getFirstWord("behind"), Phrase.PP));
		else if (val == CompositionRelationship.RELATION_DESCRIPTION)
			relationship = new Phrase(new Phrase(dict.getFirstWord("describe"), Phrase.V));
		else if (val == CompositionRelationship.RELATION_DOING)
			relationship = new Phrase(new Phrase(dict.getFirstWord("do"), Phrase.V));
		else if (val == CompositionRelationship.RELATION_NEXT_TO)
			relationship = new Phrase(new Phrase(dict.getFirstWord("be"), Phrase.V),
					new Phrase(dict.getFirstWord("next"), Phrase.PP), new Phrase(dict.getFirstWord("to"), Phrase.PP));
		else if (val == CompositionRelationship.RELATION_PART_OF)
			relationship = new Phrase(new Phrase(dict.getFirstWord("be"), Phrase.V),
					new Phrase(dict.getFirstWord("part"), Phrase.N), new Phrase(dict.getFirstWord("of"), Phrase.PP));
		else if (val == CompositionRelationship.RELATION_PROPERTY)
			relationship = new Phrase(new Phrase(dict.getFirstWord("be"), Phrase.V));
		else if (val == CompositionRelationship.RELATION_RELATED_TO)
			relationship = new Phrase(new Phrase(dict.getFirstWord("be"), Phrase.V),
					new Phrase(dict.getFirstWord("related"), Phrase.PP));
		else if (val == CompositionRelationship.RELATION_UNDER)
			relationship = new Phrase(new Phrase(dict.getFirstWord("be"), Phrase.V),
					new Phrase(dict.getFirstWord("below"), Phrase.PP));

		else relationship = new Phrase(dict.getFirstWord("is related to"), Phrase.V);
		return relationship;
	}
}
