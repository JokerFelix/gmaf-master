package de.swa.gmaf.extensions.defaults;

import java.util.Hashtable;
import java.util.Vector;

import de.swa.gc.GraphCode;
import de.swa.gmaf.extensions.SemanticExtension;

/** Extension to external semantic models - WORK IN PROGRESS
 * 
 * @author stefan_wagenpfeil
 */
public class DefaultExtension implements SemanticExtension {
	private Hashtable<String, String> termIDs = new Hashtable<String, String>();

	public String getCollectionIdForConcept(String s) {
		// the term is in the collection's dictionary
		if (termIDs.containsKey(s)) return termIDs.get(s);
		else {
			// check, if synonyms are already in the collection's dictionary
			Vector<Word> words = GeneralDictionary.getInstance().getWord(s);
			for (Word w : words) {
				try {
					if (termIDs.containsKey(w.getId())) return w.getId();
					for (String syn : w.getSynonymIDs()) {
						if (termIDs.containsKey(syn)) return syn;
					}
				}
				catch (Exception ex) {
					
				}
			}
			// still no occurence found -> add word as a new collection vocabulary term
			if (words.size() != 0) {
				termIDs.put(s, words.get(0).getId());
				return words.get(0).getId();
			}
			// word not found in dictionary -> add new entry
			termIDs.put(s, s);
			return s;
		}
	}

	public GraphCode getQueryGraphCode(String sparql, GraphCode base) {
		return null;
	}
	
	public int[] getRelationShipTypes(String concept1, String concept2) {
		return null;
	}
}
