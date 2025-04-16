package assignment9;

import java.awt.Color;

import edu.princeton.cs.introcs.StdDraw;

public class BodySegment {

	private double x, y, size;
	private Color color;
	
	public BodySegment(double initX, double initY, double initSize) {
		x = initX;
		y = initY;
		size = initSize;
		color = Color.GREEN;
		//See ColorUtils for some color options (or choose your own)
	}
	
	public double getX() {
    	return this.x;
	}
	
	public double getY() {
    	return this.y;
	}
	
	public void setX(double inputtedX) {
    	this.x = inputtedX;
	}
	
	public void setY(double inputtedY) {
    	this.y = inputtedY;
	}
	
	/**
	 * Draws the segment
	 */
	public void draw() {
		StdDraw.setPenColor(color);
		StdDraw.filledCircle(x, y, size);
	}
	
}
