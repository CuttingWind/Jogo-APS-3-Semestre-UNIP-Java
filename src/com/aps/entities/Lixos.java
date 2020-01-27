package com.aps.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.aps.main.Game;

public class Lixos extends Entity {

	public static int lixos = 0;
	public static int tipoLixo = 0;

	public static BufferedImage[] lixosSprites;

	public static BufferedImage PAPEL_SPRITE = Game.spritesheet.getSprite(0, 56, 56, 56);
	public static BufferedImage PLASTICO_SPRITE = Game.spritesheet.getSprite(56, 56, 56, 56);
	public static BufferedImage METAL_SPRITE = Game.spritesheet.getSprite(56 * 2, 56, 56, 56);
	public static BufferedImage VIDRO_SPRITE = Game.spritesheet.getSprite(56 * 3, 56, 56, 56);

	public static int randSprite;

	public Lixos(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		lixos = Entity.rand.nextInt(4);

		lixosSprites = new BufferedImage[4];

		lixosSprites[0] = PAPEL_SPRITE;
		lixosSprites[1] = PLASTICO_SPRITE;
		lixosSprites[2] = METAL_SPRITE;
		lixosSprites[3] = VIDRO_SPRITE;

		randSprite = Entity.rand.nextInt(4);
	}

	@Override
	public void update() {
		y += speed;

		if (y >= Game.HEIGHT) {
			Player.life--;
			Game.entities.remove(this);
		}
	}

	@Override
	public void render(Graphics g) {
		// g.drawImage(lixosSprites[randSprite], getX(), getY(), 56, 56, null);
		super.render(g);
	}
}
