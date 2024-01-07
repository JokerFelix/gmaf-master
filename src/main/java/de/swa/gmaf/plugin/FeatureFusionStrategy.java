package de.swa.gmaf.plugin;

import java.util.Vector;

import de.swa.mmfg.MMFG;
import de.swa.mmfg.Node;

public interface FeatureFusionStrategy {
	public void optimize(MMFG mmfg, Vector<MMFG> collection);
}

 