package de.swa.gmaf.extensions;

import de.swa.gc.GraphCode;

/** Interface to attach semantic extensions to a MMFG collection
 * 
 * @author stefan_wagenpfeil
 */

public interface SemanticExtension {
	/** returns the unique semantic ID for a vocabulary term (i.e. concept) **/
	public String getCollectionIdForConcept(String concept);
	
	/** returns a Graph Code for a given SparQL query, which represents the
	 *  external inferences of an already existing calculated base Graph Code */
	public GraphCode getQueryGraphCode(String sparql, GraphCode base);
	
	/** returns all externalized relationship types for two vocabulary terms **/
	public int[] getRelationShipTypes(String concept1, String concept2);
}
