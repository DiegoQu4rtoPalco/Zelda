package com.santana.zelda;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.santana.entities.Entities;
import com.santana.entities.Player;
import com.santana.graficos.SpriteSheet;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int WHIDTH = 720;
	private static final int HEIGHT = 480;

	private Thread thread;
	private boolean isRunning;
	
	public List <Entities> entities;
	public SpriteSheet spriteSheet;

	public static void main(String[] args) {
		Main game = new Main();
		game.start();
	}

	public Main() {
		this.setPreferredSize(new Dimension(WHIDTH, HEIGHT));
		initFrame();
		entities = new ArrayList<Entities>();
		spriteSheet = new SpriteSheet("/personagem.png");
		Player player = new Player(0, 0, 60, 60, spriteSheet.getSprite(120, 0, 60, 60));
		entities.add(player);
		
	}

	public synchronized void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initFrame() {
		JFrame frame = new JFrame();

		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
			
		}
		stop();
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
		}
		
		Graphics g = this.getGraphics();
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}

	}

	private void tick() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).ticks();
		}

	}

}
