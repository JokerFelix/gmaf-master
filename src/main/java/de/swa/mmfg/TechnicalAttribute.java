package de.swa.mmfg;

import java.awt.Color;
import java.util.Vector;

/** data type to represent technical attributes **/
public class TechnicalAttribute {
	private int relative_x;
	private int relative_y;
	private int width;
	private int height;
	private float sharpness;
	private float blurryness;
	
	private Vector<Color> dominantColors = new Vector<Color>();
	
	public TechnicalAttribute() {}
	public TechnicalAttribute(int x, int y, int w, int h, float sharp, float blurr) {
		relative_x = x;
		relative_y = y;
		width = w;
		height = h;
		sharpness = sharp;
		blurryness = blurr;
	}
	
	public void addDominantColor(Color c) {
		dominantColors.add(c);
	}
	
	public Vector<Color> getDominantColors() {
		return dominantColors;
	}

	public int getRelative_x() {
		return relative_x;
	}

	public void setRelative_x(int relative_x) {
		this.relative_x = relative_x;
	}

	public int getRelative_y() {
		return relative_y;
	}

	public void setRelative_y(int relative_y) {
		this.relative_y = relative_y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public float getSharpness() {
		return sharpness;
	}

	public void setSharpness(float sharpness) {
		this.sharpness = sharpness;
	}

	public float getBlurryness() {
		return blurryness;
	}

	public void setBlurryness(float blurryness) {
		this.blurryness = blurryness;
	}
	
	
}
