package com.aps.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Menu {
	public String[] options = { "novo jogo", "sair" };

	public int currentOption = 0;
	public int maxOption = options.length - 1;

	public boolean up, down, enter;

	public boolean pause = false;

	public void update() {
		if (up) {
			up = false;
			currentOption--;
			if (currentOption < 0)
				currentOption = maxOption;
		}
		if (down) {
			down = false;
			currentOption++;
			if (currentOption > maxOption)
				currentOption = 0;
		}
		if (enter) {
			if (options[currentOption] == "novo jogo" || options[currentOption] == "continuar") {
				Game.gameState = "NORMAL";
				pause = false;
			} else if (options[currentOption] == "sair") {
				System.exit(1);
			}
			enter = false;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 110));

		g.drawString(">APS<", (Game.WIDTH * Game.SCALE) / 2 - 178, (Game.HEIGHT * Game.SCALE) / 2 - 160);
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 24));
		g.drawString("Novo jogo", (Game.WIDTH * Game.SCALE) / 2 - 50, 240);
		g.drawString("Sair", (Game.WIDTH * Game.SCALE) / 2 - 17, 290);
		if (options[currentOption] == "novo jogo") {
			g.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 80, 240);
		} else if (options[currentOption] == "sair") {
			g.drawString(">", (Game.WIDTH * Game.SCALE) / 2 - 40, 290);
		}
	}
}
