package by.ansgar.rpg.weather;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import by.ansgar.rpg.constants.R;

public class Weather {

	private int x;
	private int y;
	private int dx;
	private int dy;
	private BufferedImage image;
	private BufferedImage wetherImage;
	private Color c = new Color(0f, 0f, 0f, 0.7f);
	private Random rnd = new Random();

	public Weather(int x, int y) {
		this.x = x;
		this.y = y;
		dx = -1;
		dy = 1;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(
					"/image/wether/wether.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		x += dx;
		y += dy;
		switch (WeatherGeneration.countCurrentSeason()) {
		case 0:
			break;
		case 1:
			wetherImage = image.getSubimage(R.w * 1, 0, R.w, R.h);
			break;
		case 2:
			wetherImage = image.getSubimage(R.w * 2, 0, R.w, R.h);
			break;
		case 3:
			wetherImage = image.getSubimage(R.w * 3, 0, R.w, R.h);
			break;
		default:
			break;
		}
	}

	public void draw(Graphics g) {
		g.drawImage(wetherImage, x, y, R.w / 2, R.h / 2, null);

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

}
