package com.aps.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.aps.entities.Entity;
import com.aps.entities.Player;
import com.aps.grafics.Spritesheet;
import com.aps.grafics.UI;
import com.aps.world.LixoGenerator;
import com.aps.world.World;

public class Game extends Canvas implements KeyListener, Runnable {

	private static final long serialVersionUID = -8090487609518634933L;

	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 360;
	public static final int HEIGHT = 640;
	public static final int SCALE = 1;
	public static int score = 0;

	public static String gameState = "MENU";
	public static Menu menu;
	public static List<Entity> entities;
	private BufferedImage image;
	public static UI ui;
	public static LixoGenerator lixos;
	public static Spritesheet spritesheet;
	public static World world;
	public static Player player;

	//Construtor
	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

		initFrames();

		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(0, 0, 56, 56, 1, spritesheet.getSprite(0, 0, 56, 56));
		lixos = new LixoGenerator();
		menu = new Menu();
		ui = new UI();
		world = new World("/map.png");
		entities = new ArrayList<Entity>();
		entities.add(player);
	}

	// Aqui é criado o JFrame.
	public void initFrames() {
		frame = new JFrame("APS: JOGO");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// Aqui e aonde colocamos o jogo pra rodar.
	public void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	// Aqui e aonde paramos o jogo.
	public void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Metodo principal
	public static void main(String args[]) {
		Game game = new Game();
		game.start();
	}

	// Aqui e aonde fica toda a logica do jogo.
	public void update() {
		if (gameState == "NORMAL") {
			lixos.update();
			for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.update();
			}
		} else if (gameState == "MENU") {
			menu.update();
		}
	}

	// Aqui e aonde e renderizado o jogo.
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		world.render(g);
		ui.render(g);
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}

		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		if (gameState == "MENU") {
			menu.render(g);
		}

		bs.show();
	}

	// Aqui e aonde deixamos todo o codigo rodando
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double ticks = 60.0;
		double ns = 1000000000 / ticks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		this.requestFocus();

		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				update();
				render();
				frames++;
				delta--;
			}
			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
	}

	// Aqui pegamos eventos de teclado
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = true;
		}

		if (gameState == "MENU") {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				menu.up = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				menu.down = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				menu.enter = true;
			}
		}
		if (Player.life <= 0) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				System.exit(1);
		}
	}

	// Aqui pegamos eventos de teclado
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
