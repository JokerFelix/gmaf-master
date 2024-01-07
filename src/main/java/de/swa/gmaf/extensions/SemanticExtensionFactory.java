package de.swa.gmaf.extensions;

import de.swa.gmaf.extensions.defaults.DefaultExtension;
import de.swa.ui.Configuration;

/** Factory to create the semantic extension.
 * 
 * @author stefan_wagenpfeil
 */


public class SemanticExtensionFactory {
	private SemanticExtensionFactory() {}
	private static SemanticExtension instance = null;
	/** returns the instance of the Semantic Extension, which is configured in the GMAF config file **/
	public static synchronized SemanticExtension getInstance() {
		if (instance == null) {
			String cls = Configuration.getInstance().getSemanticExtension();
			try {
				Class c = Class.forName(cls);
				SemanticExtension se = (SemanticExtension)c.newInstance();
				instance = se;
			}
			catch (Exception x) {
				x.printStackTrace();
				instance = new DefaultExtension();
			}
		}
		return instance;
	}
	

}
