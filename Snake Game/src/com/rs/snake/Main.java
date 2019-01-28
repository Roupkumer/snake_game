package com.rs.snake;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main extends JFrame{
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Main() {
		add(new Board());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Snake Game");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
	}
}
