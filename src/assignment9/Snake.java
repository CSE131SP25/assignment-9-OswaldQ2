package assignment9;

import java.awt.Color;
import java.util.LinkedList;

import edu.princeton.cs.introcs.StdDraw;

public class Snake {

	private static final double SEGMENT_SIZE = 0.02;
	private static final double MOVEMENT_SIZE = SEGMENT_SIZE * 1.5;
	private LinkedList<BodySegment> segments;
	private double deltaX;
	private double deltaY;
	
	public Snake() {
		segments = new LinkedList<BodySegment>();
		BodySegment bodySegment1 = new BodySegment(0.5, 0.5, SEGMENT_SIZE); //Starting snake
		segments.add(bodySegment1);
		deltaX = 0;
		deltaY = 0;
	}
	
	public void changeDirection(int direction) {
		if(direction == 1) { //up
			deltaY = MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 2) { //down
			deltaY = -MOVEMENT_SIZE;
			deltaX = 0;
		} else if (direction == 3) { //left
			deltaY = 0;
			deltaX = -MOVEMENT_SIZE;
		} else if (direction == 4) { //right
			deltaY = 0;
			deltaX = MOVEMENT_SIZE;
		}
	}
	
	/**
	 * Moves the snake by updating the position of each of the segments
	 * based on the current direction of travel
	 */
	public void move() {
		//Body
		for (int i = segments.size() - 1; i >= 1; i--) { //Loop starting from back
			segments.get(i).setX(segments.get(i - 1).getX());
			segments.get(i).setY(segments.get(i - 1).getY());
		}
		
		//Head
		segments.getFirst().setX(segments.getFirst().getX() + deltaX);
		segments.getFirst().setY(segments.getFirst().getY() + deltaY);
	}
	
	/**
	 * Draws the snake by drawing each segment
	 */
	public void draw() {
		for (int i = 0; i < segments.size(); i++) {
			segments.get(i).draw();
		}
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledCircle(segments.getFirst().getX(), segments.getFirst().getY(), 0.005);
	}
	
	/**
	 * The snake attempts to eat the given food, growing if it does so successfully
	 * @param f the food to be eaten
	 * @return true if the snake successfully ate the food
	 */
	public boolean eatFood(Food f) {
		BodySegment head = segments.getFirst();
		double distanceCenters = Math.sqrt(Math.pow(head.getX() - f.getX(), 2) + Math.pow(head.getY() - f.getY(), 2));
		if (distanceCenters <= 2*SEGMENT_SIZE) {
			//X position of new segment
			double xNew = segments.getLast().getX() + 2*SEGMENT_SIZE; //So the snake doesn't hit itself immediately
			
			//Y position of new segment
			double yNew = segments.getLast().getY() + 2*SEGMENT_SIZE; //So the snake doesn't hit itself immediately
			
			BodySegment newBodySegment = new BodySegment(xNew, yNew, SEGMENT_SIZE); //New body segment
			segments.add(newBodySegment);
			return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the head of the snake is in bounds
	 * @return whether or not the head is in the bounds of the window
	 */
	public boolean isInbounds() {
		BodySegment head = segments.getFirst();
		if (head.getX() + SEGMENT_SIZE >= 1 || head.getX() - SEGMENT_SIZE <= 0 || head.getY() + SEGMENT_SIZE >= 1 || head.getY() - SEGMENT_SIZE <= 0) {
			return false;
		}
		return true;
	}
	
	public boolean touchingSelf() {
		BodySegment head = segments.getFirst();
		for (int i = 1; i < segments.size(); i++) {
			double distanceCenters = Math.sqrt(Math.pow(head.getX() - segments.get(i).getX(), 2) + Math.pow(head.getY() - segments.get(i).getY(), 2));
			if (distanceCenters < SEGMENT_SIZE) { //Fudge factor 
				return true;
			}
		}
		return false;
	}
}
