package com.aps.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.aps.main.Game;

public class Tile {

	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(280, 0, 56, 56);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(224, 0, 56, 56);
	public static BufferedImage TILE_SKY = Game.spritesheet.getSprite(336, 0, 56, 56);

	private BufferedImage sprite;
	private int x, y;

	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}

	public void render(Graphics g) {
		g.drawImage(sprite, x, y, null);
	}
}
