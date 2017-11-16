package net.matrixstudios.snake.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Snake extends Entity {
	private int length;
	private ArrayList<SnakeBox> snake;
	public Snake(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		length = 0;
		snake = new ArrayList<SnakeBox>(1);
		snake.add(new SnakeBox(this.x, this.y));
	} 

	@Override
	public void paint(Graphics2D g2d) {
		for(SnakeBox box : snake) {
			box.paint(g2d);
		}
	}

	public void add() {
		length++;
	}
	
	public void lose() {
		System.out.println("Lose!");
		destroy();
	}
	
	@Override
	public void updateMovement() {
		snake.add(new SnakeBox(getX(), getY()));
		
		if(snake.size() > 10 * (length+1)) {
			snake.remove(0);
		}
		
		x += velX;
		y += velY;
		
		for(int i = 0;i<snake.size()-10;i++) { //if the snake runs into itself
			if(snake.get(i).getBounds().intersects(getBounds())) {
				lose();
			}
		}
	}
}
