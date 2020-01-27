package com.aps.world;

import com.aps.entities.Entity;
import com.aps.entities.Lixos;
import com.aps.main.Game;

public class LixoGenerator {

	public int time = 0;
	public int selector;
	public double randSpeed;

	public Lixos lixos;

	public int randPos;

	public void update() {
		time++;
		if (time == 70) {
			randSpeed = Entity.rand.nextDouble() + 4;
			selector = Entity.rand.nextInt(4);
			randPos = Entity.rand.nextInt(Game.WIDTH - 56);
			if (selector == 0) {
				lixos = new Lixos(randPos, 0, 56, 56, randSpeed, Lixos.PAPEL_SPRITE);
				Game.entities.add(lixos);
			} else if (selector == 2) {
				lixos = new Lixos(randPos, 0, 56, 56, randSpeed, Lixos.PLASTICO_SPRITE);
				Game.entities.add(lixos);
			} else if (selector == 2) {
				lixos = new Lixos(randPos, 0, 56, 56, randSpeed, Lixos.VIDRO_SPRITE);
				Game.entities.add(lixos);
			} else if (selector == 3) {
				lixos = new Lixos(randPos, 0, 56, 56, randSpeed, Lixos.METAL_SPRITE);
				Game.entities.add(lixos);
			}
			time = 0;
		}
		if (Game.gameState == "GAME OVER") {
			time = 100;
		}
	}
}
