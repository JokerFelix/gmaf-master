package de.swa.gc;

import java.util.Vector;

/** Implementation of the Graph Code Metric
 * 
 * @author stefan_wagenpfeil
 */

public class GraphCodeMetric {
	/** calculates the metric triple for Graph Codes based on a given query **/
	public static float[] calculateSimilarity(GraphCode gcQuery, GraphCode gc) {
		float node_metric = 0f;
		float edge_metric = 0f;
		float edge_type_metric = 0f;
		
		// node metric checks matching vocabulary terms
		Vector<String> voc = gcQuery.getDictionary();
		int sim = 0;
		for (String s : voc) {
			if (s.trim().equals("")) continue;
			Vector<String> otherDict = gc.getDictionary();
			for (String t : otherDict) {
				if (s.equals(t)) sim ++;
//				if (s.indexOf(t) >= 0) { 
//					sim ++;
//				}
//				else if (t.indexOf(s) >= 0) {
//					sim ++; 
//				}
			}
		}
		if (sim > voc.size()) sim = voc.size();
		node_metric = (float)sim / (float)voc.size();
		
		// edge metric checks matching edges of the non diagonal fields
		// edge type metric checks for corresponding type values
		int num_of_non_zero_edges = 0;
		int edge_metric_count = 0;
		int edge_type = 0;
		for (int i = 0; i < voc.size(); i++) {
			for (int j = 0; j < voc.size(); j++) {
				if (i != j) {
					if (gcQuery.getValue(i, j) != 0) {
						num_of_non_zero_edges ++;
						try {
							int gc_edge = gc.getEdgeValueForTerms(voc.get(i), voc.get(j));
							if (gc_edge != 0) edge_metric_count ++;
							if (gc_edge == gcQuery.getValue(i, j)) edge_type ++;
						}
						catch (Exception x) {
							x.printStackTrace();
						}
					}
				}
			}
		}
		if (num_of_non_zero_edges > 0) edge_metric = (float)edge_metric_count / (float)num_of_non_zero_edges;
		if (edge_metric_count > 0) edge_type_metric = (float)edge_type / (float)edge_metric_count;
		float[] result = new float[] {node_metric, edge_metric, edge_type_metric};
		
		return result;
	}
}
