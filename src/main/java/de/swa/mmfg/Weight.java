package de.swa.mmfg;

/** data type to represent weights **/
public class Weight {
	private Context context;
	private float weight;
	
	public Weight() {}
	public Weight(Context c, float w) {
		context = c;
		weight = w;
	}
	
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	
}
