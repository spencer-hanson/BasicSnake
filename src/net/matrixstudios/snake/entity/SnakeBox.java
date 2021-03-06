package net.matrixstudios.snake.entity;

import java.awt.Color;
import java.awt.Graphics2D;

public class SnakeBox extends Entity {
	
	public SnakeBox(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		updateBounds();
	}
	
	@Override
	public void paint(Graphics2D g2d) {
		g2d.setColor(Color.black);
		g2d.fillRect(getX(), getY(), size, size);
	}

	@Override
	public void updateMovement() { }

}
