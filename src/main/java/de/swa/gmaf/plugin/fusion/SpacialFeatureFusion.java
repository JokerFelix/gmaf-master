package de.swa.gmaf.plugin.fusion;

import java.util.Vector;

import de.swa.gmaf.plugin.FeatureFusionStrategy;
import de.swa.mmfg.CompositionRelationship;
import de.swa.mmfg.MMFG;
import de.swa.mmfg.Node;
import de.swa.mmfg.TechnicalAttribute;

public class SpacialFeatureFusion implements FeatureFusionStrategy {
	public void optimize(MMFG mmfg, Vector<MMFG> collection) {
		for (Node n : mmfg.getAllNodes()) {
			if (n.getTechnicalAttributes() != null && n.getTechnicalAttributes().size() > 0) {
				TechnicalAttribute ta = n.getTechnicalAttributes().get(0);

				for (Node n2 : mmfg.getAllNodes()) {
					if (n2.getTechnicalAttributes() != null && n2.getTechnicalAttributes().size() > 0 && n != n2) {
						TechnicalAttribute ta2 = n2.getTechnicalAttributes().get(0);

						if (ta.getRelative_x() + ta.getWidth() < ta2.getRelative_x()) {
							n.addCompositionRelationship(
									new CompositionRelationship(CompositionRelationship.RELATION_NEXT_TO, n2));
							n2.addCompositionRelationship(
									new CompositionRelationship(CompositionRelationship.RELATION_NEXT_TO, n));
							
						}
						if (ta.getRelative_x() > ta2.getRelative_x() + ta2.getWidth()) {
							n.addCompositionRelationship(
									new CompositionRelationship(CompositionRelationship.RELATION_NEXT_TO, n2));
							n2.addCompositionRelationship(
									new CompositionRelationship(CompositionRelationship.RELATION_NEXT_TO, n));
							
						}
						if (ta.getRelative_y() + ta.getHeight() < ta2.getRelative_y()) {
							n.addCompositionRelationship(
									new CompositionRelationship(CompositionRelationship.RELATION_UNDER, n2));
							n2.addCompositionRelationship(
									new CompositionRelationship(CompositionRelationship.RELATION_ABOVE, n));
							
						}
						if (ta.getRelative_y() > ta2.getRelative_y() + ta2.getHeight()) {
							n.addCompositionRelationship(
									new CompositionRelationship(CompositionRelationship.RELATION_ABOVE, n2));
							n2.addCompositionRelationship(
									new CompositionRelationship(CompositionRelationship.RELATION_UNDER, n));
							
						}
					}
				}
			}
		}
	}
}
