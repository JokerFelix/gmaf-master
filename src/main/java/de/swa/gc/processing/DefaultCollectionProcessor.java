package de.swa.gc.processing;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import de.swa.gc.GraphCodeMetric;


public class DefaultCollectionProcessor extends CollectionProcessor {
	private Vector<GraphCodeMeta> collection;
	
	public void preloadIndex(Vector<GraphCodeMeta> collection) {
		this.collection = collection;
	}
	
	public void execute() {
		for (GraphCodeMeta meta : collection) {
			float[] sim = GraphCodeMetric.calculateSimilarity(gcQuery, meta.getGraphcode());
			meta.setMetric(sim);
		}
	}

	public Vector<GraphCodeMeta> getResultList() {
		Collections.sort(collection, new Comparator<GraphCodeMeta>() {
			public int compare(GraphCodeMeta m1, GraphCodeMeta m2) {
				float[] metric_a = m1.getMetric();
				float[] metric_b = m2.getMetric();
//				System.out.println("A: " + metric_a[0] + " " + metric_a[1] + " " + metric_a[2]);
//				System.out.println("B: " + metric_b[0] + " " + metric_b[1] + " " + metric_b[2]);
				
				if (operation == SIMILARITY) {
					// calculate numeric values to support java-compatible comparison
					float a = metric_a[0] * 100000 + metric_a[1] * 100 + metric_a[2];
					float b = metric_b[0] * 100000 + metric_b[1] * 100 + metric_b[2];
//					System.out.println("-> S " + (b-a));
					return (int)(b - a);
				}
				else if (operation == RECOMMENDATION) {
					// calculate numeric values to support java-compatible comparison
					float a = metric_a[1] * 100000 + metric_a[0] * 100 + metric_a[2];
					float b = metric_b[1] * 100000 + metric_b[0] * 100 + metric_b[2];
//					System.out.println("-> R " + (b-a));
					
					return (int)(b - a);

				}
				return 0;
			};
		});	
		return collection;
	}
}
