package net.matrixstudios.snake.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import net.matrixstudios.snake.entity.Entity;
import net.matrixstudios.snake.entity.Snake;
import net.matrixstudios.snake.entity.SnakeUpgrade;

public class GameCanvas extends JPanel implements Runnable {
	
	private static final int SNAKE_SPEED = 2;
	
	private BufferedImage backbuffer;
	private Graphics2D g2d;
	private Snake snake;
	private ArrayList<Entity> entities;
	
	private boolean upgradeOnField;
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	public GameCanvas() {
		super();
		entities = new ArrayList<Entity>(1);
		backbuffer = new BufferedImage(Window.WIDTH, Window.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		g2d = backbuffer.createGraphics();
		snake = new Snake(Window.WIDTH/2, Window.HEIGHT/2);
		entities.add(snake);
		up = false;
		down = false;
		right = false;
		left = false;
		upgradeOnField = false;
		this.setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backbuffer, 0, 0, this); //draw the buffer to the screen
		g2d.setColor(Color.white); //clear the buffer, but leaving the screen painted so there is no flickering
		g2d.fillRect(0, 0, Window.WIDTH, Window.HEIGHT);
	}

	public void paintEntities() {
		for(Entity entity : entities) {
			entity.paint(g2d);
		}
	}
	
	public void updateEntities() {
		for(Entity entity : entities) {
			entity.update();
		}
		//check if snake is within borders
		if(snake.getX() < 0 || snake.getX() > Window.WIDTH-snake.getSize() || snake.getY() < 0 || snake.getY() > Window.HEIGHT-snake.getSize()) {
			snake.lose();
		}
	}
	
	public void checkCollisions() {
		for(Entity entity : entities) { //for each entity in the arraylist entities, loop
			if(entity != snake) {
				if(entity.getBounds().intersects(snake.getBounds())) {
					snake.add();
					entity.destroy();
				}
			}
		}
	}
	
	public void cleanupEntities() {
		
		for(int i = 0;i<entities.size();i++) {
			if(entities.get(i).getDestroy()) {
				entities.remove(i);
				upgradeOnField = false;
			}
		}
	}
	
	public void createUpgrade() {
		if(!upgradeOnField) {
			Random r = new Random();
			int x = r.nextInt(Window.WIDTH - 20) + 10; //generate a random upgrade x coord from 10 to WIDTH-10 
			int y = r.nextInt(Window.HEIGHT - 20) + 10; //generate a random upgrade y coord from 10 to HEIGHT-10
			entities.add(new SnakeUpgrade(x, y));
			upgradeOnField = true;
		}
	}
	
	public void run() {
		while((2 + 2) == 4) {
			try {
				Thread.sleep(20);
				paintEntities();
				updateEntities();
				checkCollisions();
				cleanupEntities();
				createUpgrade();
				repaint();
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && !down) {
			snake.setVelX(0);
			snake.setVelY(-SNAKE_SPEED);
			up = true;
			down = false;
			left = false;
			right = false;
		} else if((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && !right) {
			snake.setVelX(-SNAKE_SPEED);
			snake.setVelY(0);
			left = true;
			down = false;
			up = false;
			right = false;
		} else if((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && !up) {
			snake.setVelX(0);
			snake.setVelY(SNAKE_SPEED);
			down = true;
			up = false;
			right = false;
			left = false;
		} else if((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && !left) {
			snake.setVelX(SNAKE_SPEED);
			snake.setVelY(0);
			right = true;
			left = false;
			down = false;
			up = false;
		} else if(key == KeyEvent.VK_SPACE) {
			snake.setVelX(0);
			snake.setVelY(0);
		}
	}
	
	public int getWidth() {
		return Window.WIDTH;
	}
	
	public int getHeight() {
		return Window.HEIGHT;
	}
}
