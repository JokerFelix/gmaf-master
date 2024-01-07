package de.swa.gmaf.plugin.fusion;

import de.swa.gmaf.plugin.FeatureFusionStrategy;
import de.swa.gmaf.plugin.GMAF_Plugin;
import de.swa.mmfg.MMFG;

public interface FeatureFusionPlugin extends GMAF_Plugin {
	public MMFG process(GMAF_Plugin a, GMAF_Plugin b, FeatureFusionStrategy fs);
}
