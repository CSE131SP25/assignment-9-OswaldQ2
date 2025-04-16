package assignment9;

import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	
	private Snake snake;
	private Food food;
	private int score;
	
	public Game() {
		StdDraw.enableDoubleBuffering();
		snake = new Snake();
		food = new Food();
		score = 0;
	}
	
	public void play() {
		while (snake.isInbounds() && snake.touchingSelf() == false) { //TODO: Update this condition to check if snake is in bounds
			int dir = getKeypress();
			//Testing only: you will eventually need to do more work here
			System.out.println("Keypress: " + dir);
			snake.changeDirection(getKeypress());
			snake.move();
			if (snake.eatFood(food) == true) {
				food = new Food();
				score++;
			}
			updateDrawing();
			/*
			 * 1. Pass direction to your snake
			 * 2. Tell the snake to move
			 * 3. If the food has been eaten, make a new one
			 * 4. Update the drawing
			 */
		}
		this.GameOver();
	}
	
	private void GameOver() {
		StdDraw.setPenColor(Color.RED);
		StdDraw.text(0.5, 0.5, "Game Over!");
		StdDraw.text(0.5, 0.45, "Your score was: " + score);
		StdDraw.show();
	}
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	private void Score() {
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.text(0.1, 0.9, "Score: " + score);
	}
	
	private void Eyes() {
		StdDraw.setPenColor(Color.BLACK);
		
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		StdDraw.clear();
		snake.draw();
		food.draw();
		this.Score();
		StdDraw.pause(50);
		StdDraw.show();
		/*
		 * 1. Clear screen
		 * 2. Draw snake and food
		 * 3. Pause (50 ms is good)
		 * 4. Show
		 */
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
