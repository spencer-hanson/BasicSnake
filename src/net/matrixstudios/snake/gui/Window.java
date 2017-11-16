package net.matrixstudios.snake.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Window extends JFrame implements KeyListener {

	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	
	public GameCanvas canvas;
	
	public void initComponents() {
		canvas = new GameCanvas();
		add(canvas);
	}
	
	public void init() {
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when you hit the 'x'
		setLocationRelativeTo(null);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS)); //component management object, organizes components along the X axis, left to right as they are added
		addKeyListener(this);
		initComponents();
		setVisible(true);
		
		new Thread(canvas).start();
	}
	
	
	public Window() {
		super("Snakey"); //name of window
		init();
	}
	
	public static void main(String[] args) {
		new Window();
	}

	public void keyPressed(KeyEvent e) {
		canvas.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) { }

	public void keyTyped(KeyEvent e) { }
}
