package com.rs.snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.naming.InitialContext;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener,KeyListener{
	private static final long serialVersionUID = 1L;

	private int width=400,height=400;
	private int size=20;
	private int dots=400;
	private int tails;
	
	private int apple_x,apple_y;
	
	private int x[]=new int[dots];
	private int y[]=new int[dots];
	
	private boolean down=false,right=true,up=false,left=false;
	private boolean inGame;
	
	private Timer timer;
	private int delay=150;
	
	public Board() {
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width, height));
		init();
		
	}
	
	private void init() {
		addKeyListener(this);
		setFocusable(true);
		inGame=true;
		locateApple();
		tails=3;
		for (int i = 0; i < tails; i++) {
			if (i==0) {
				x[0]=100;
				y[0]=40;
			}else {
				x[i]=x[0]- i*size;
				y[i]=40;
				System.out.println(x[i]);
			}
		}
		delay=150;
		timer=new Timer(delay, this);
		timer.start();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//locateApple(g);
		doDrawing(g);
	}
	private void doDrawing(Graphics g) {
		if (inGame) {
		g.setColor(Color.green);
		g.fillRect(apple_x, apple_y, size, size);
		for (int i = 0; i < tails; i++) {
			if (i==0) {
				g.setColor(Color.black);
				g.fillOval(x[0], y[0],size, size);
			} else {
				g.setColor(Color.red);
				g.fillOval(x[i], y[i],size, size);
			}
		}
		}else {
			gameOver(g);
			//timer.stop();
			
		}
	}
	private void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Arial", Font.BOLD, 50));
		g.drawString("GameOver", 100,200);
	}
	private void locateApple(){
		int p=(int) (Math.random()*19);
		int q=(int) (Math.random()*19);
	
		apple_x=p*size;
		apple_y=q*size;
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		move();
		checkApple();
		checkCollision();
		repaint();
	}
	private void move() {
		for (int i = tails; i>=1; i--) {
			x[i]=x[i-1];
			y[i]=y[i-1];
		}
		if (right) {
			x[0]+=size;
		}
		if (down) {
			y[0]+=size;
		}
		if (up) {
			y[0]-=size;
		}
		if (left) {
			x[0]-=size;
		}
	}
	private void checkApple() {
		if (x[0]==apple_x&&y[0]==apple_y) {
			locateApple();
			tails++;
		}
	}
	private void checkCollision() {
		if (x[0]<0 || y[0]<0) {
			inGame=false;
		}
		if (x[0]>=400||y[0]>=400) {
			inGame=false;
		}
		for (int i = 0; i < tails; i++) {
			if (x[0]==x[i+2] && y[0]==y[i+2] ) {
				inGame=false;
			}
		}
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		if ((e.getKeyCode()==KeyEvent.VK_RIGHT)&&(!left)) {
			right=true;
			up=false;
			down=false;
		}
		if ((e.getKeyCode()==KeyEvent.VK_DOWN)&&(!up)) {
			down=true;
			left=false;
			right=false;
		}
		if ((e.getKeyCode()==KeyEvent.VK_LEFT)&&(!right)) {
			down=false;
			left=true;
			up=false;
		}
		if ((e.getKeyCode()==KeyEvent.VK_UP)&&(!down)) {
			up=true;
			left=false;
			right=false;
		}
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			//inGame=true;
			//new Board();
			timer.start();
			init();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
