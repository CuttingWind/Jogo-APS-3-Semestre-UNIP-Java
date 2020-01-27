package com.aps.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.aps.main.Game;
import com.aps.world.Camera;
import com.aps.world.World;

public class Player extends Entity {

	public boolean right, left;
	private double speed = 2.5;

	public static int life = 3;

	private BufferedImage[] moveSprite;
	private boolean moved = false;
	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 3;

	public Player(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

		moveSprite = new BufferedImage[4];

		for (int i = 0; i < 4; i++) {
			moveSprite[i] = Game.spritesheet.getSprite(0 + (i * 56), 0, 56, 56);
		}
	}

	@Override
	public void update() {
		moved = false;
		if (right) {
			moved = true;
			x += speed;
		} else if (left) {
			moved = true;
			x -= speed;
		}

		if (x + width > Game.WIDTH) {
			x = Game.WIDTH - width;
		} else if (x < 0) {
			x = 0;
		}

		if (moved) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxIndex) {
					index = 0;
				}
			}
		}
		updateCamera();
		checkCollisionLixos();
	}

	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH / 2), 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT / 2), 0, World.HEIGHT * 16 - Game.HEIGHT);
	}

	public void checkCollisionLixos() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity atual = Game.entities.get(i);
			if (atual instanceof Lixos) {
				if (Entity.isColidding(this, atual)) {
					Game.score++;
					Game.entities.remove(atual);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		if (moved) {
			g.drawImage(moveSprite[index], this.getX(), this.getY(), null);
		} else {
			super.render(g);
		}
	}
}
