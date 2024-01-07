package de.swa.gmaf.extensions.defaults;

import java.util.Vector;

import org.tartarus.snowball.ext.EnglishStemmer;

/** Word Stemmer to reduce words into their word-stems 
 * 
 * @author stefan_wagenpfeil
 */
public class Stemmer {
	/** returns the word stem for a given term **/
	public static String stemTerm (String term) {
		EnglishStemmer stemmer = new EnglishStemmer();
//	    PorterStemmer stemmer = new PorterStemmer();
	    stemmer.setCurrent(term);
	    stemmer.stem();
	    String stem = stemmer.getCurrent();
	    return stem;
	}
}
