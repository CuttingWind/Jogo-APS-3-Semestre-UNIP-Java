package com.aps.grafics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.aps.entities.Player;
import com.aps.main.Game;

public class UI {

	public void render(Graphics g) {
		g.fillRect(5, 3, 126, 29);
		g.setColor(Color.green);
		g.fillRect(7, 5, 122, 25);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.setColor(Color.black);
		g.drawString("SCORE: " + Game.score, 10, 25);

		g.fillRect(228, 3, 126, 29);
		g.setColor(Color.orange);
		g.fillRect(230, 5, 122, 25);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.setColor(Color.black);
		g.drawString("VIDAS: " + Player.life, 240, 25);

		if (Player.life <= 0) {
			Game.entities.clear();
			Game.gameState = "GAME OVER";
			g.setFont(new Font("arial", Font.BOLD, 50));
			g.drawString("GAME OVER!!", Game.WIDTH / 2 - 170, Game.HEIGHT / 2 - 150);
			g.setFont(new Font("arial", Font.BOLD, 50));
			g.drawString("SCORE: " + Game.score, Game.WIDTH / 2 - 125, Game.HEIGHT / 2 - 40);
			g.setFont(new Font("arial", Font.BOLD, 20));
			g.drawString("Pressione ESC para SAIR!", Game.WIDTH / 2 - 120, Game.HEIGHT / 2 + 50);
		}
	}
}
