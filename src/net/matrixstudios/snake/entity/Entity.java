package net.matrixstudios.snake.entity;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Entity {
	protected int x;
	protected int y;
	protected int velX;
	protected int velY;
	protected int size;
	
	protected boolean destroy;
	protected Rectangle bounds;
	
	public Entity() {
		this.x = 0;
		this.y = 0;
		this.velX = 0;
		this.velY = 0;
		this.size = 10;
		this.destroy = false;
		updateBounds();
	}
	
	public void update() {
		updateBounds();
		updateMovement();
	}
	
	public void updateBounds() {
		bounds = new Rectangle(x, y, size, size);
	}
	
	public abstract void paint(Graphics2D g2d);
	public abstract void updateMovement();

	public void destroy() {
		destroy = true;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setVelX(int velX) {
		this.velX = velX;
	}
	
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getVelX() {
		return velX;
	}
	
	public int getVelY() {
		return velY;
	}
	
	public int getSize() {
		return size;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public boolean getDestroy() {
		return destroy;
	}
}
