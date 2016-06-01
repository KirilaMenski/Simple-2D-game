package by.ansgar.rpg.object.player_spells;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import by.ansgar.rpg.constants.R;

public class SpellsBar {

	private int width = 30;
	private int height = 30;

	private BufferedImage water, earth, fire, air, healing;

	private String number[] = { "1", "2", "3", "4", "5" };

	public SpellsBar() {
		try {
			water = ImageIO.read(getClass().getResourceAsStream(
					"/image/sprites/player_spells/water.png"));
			earth = ImageIO.read(getClass().getResourceAsStream(
					"/image/sprites/player_spells/earth.png"));
			fire = ImageIO.read(getClass().getResourceAsStream(
					"/image/sprites/player_spells/fire.png"));
			air = ImageIO.read(getClass().getResourceAsStream(
					"/image/sprites/player_spells/air.png"));
			healing = ImageIO.read(getClass().getResourceAsStream(
					"/image/sprites/player_spells/healing.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		for(int i = 0; i < number.length; i++){
			g.drawRoundRect(50 + (width*i + 5), R.HEIGTH - 40, width, height, 1, 1);
		}
		g.drawImage(water.getSubimage(3 * width, 0, width, height), 50,
				R.HEIGTH - 40, null);
		g.drawImage(earth.getSubimage(3 * width, 0, width, height), 85,
				R.HEIGTH - 40, null);
		g.drawImage(fire.getSubimage(3 * width, 0, width, height), 115,
				R.HEIGTH - 40, null);
		g.drawImage(air.getSubimage(3 * width, 0, width, height), 145,
				R.HEIGTH - 40, null);
		g.drawImage(healing.getSubimage(3 * width, 0, width, height), 175,
				R.HEIGTH - 40, null);
		for (int i = 0; i < number.length; i++) {
			g.drawString(number[i], 55 + width*i, R.HEIGTH - 30);
		}
	}

}
