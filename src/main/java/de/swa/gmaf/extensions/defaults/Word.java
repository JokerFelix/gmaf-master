package de.swa.gmaf.extensions.defaults;

import java.io.Serializable;
import java.util.Vector;

/** Dictionary-Word implementation to represent words, word-stems and synonym links 
 * 
 * @author stefan_wagenpfeil
 */

public class Word implements Serializable {
	public static final int TYPE_NOUN = 1;
	public static final int TYPE_VERB = 2;
	public static final int TYPE_ADVERB = 3;
	public static final int TYPE_ADJECTIVE = 4;
	public static final int TYPE_UNKNOWN = 5;
	
	private String id, word, stem;
	private int type;
	private Vector<String> synonymIDs = new Vector<String>();
	
	public Word(String id, String word, int type, Vector<String> synonyms) {
		this.id = id;
		this.word = word;
		this.type = type;
		this.synonymIDs = synonyms;
	}
	
	public int getType() {
		return type;
	}
	
	public Vector<String> getSynonymIDs() {
		return synonymIDs;
	}
	
	public String getWord() {
		return word;
	}
	
	public void setWordStem(String stem) {
		this.stem = stem;
	}
	
	public String getWordStem() {
		return stem;
	}
	
	public String getId() {
		return id;
	}
}
