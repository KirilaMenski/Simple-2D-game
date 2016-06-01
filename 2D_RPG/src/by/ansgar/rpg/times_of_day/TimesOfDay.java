package by.ansgar.rpg.times_of_day;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import by.ansgar.rpg.constants.R;

public class TimesOfDay implements Runnable {

	private int hours = 0;
	private int minutes = 0;
	private int seconds = 0;
	private Color color;

	public void update() {
		seconds++;
		if (seconds == 60) {
			seconds = 0;
			minutes++;
		}
		if (minutes == 60) {
			minutes = 0;
			hours++;
		}
		if (hours == 24) {
			hours = 0;
		}

		if (hours >= 18 ||  hours < 6) {
			color = new Color(0f, 0f, 0f, 0.5f);
		} else{
			color = new Color(0f, 0f, 0f, 0f);
		}

	}

	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.drawString(hours + ":" + minutes + ":" + seconds, R.WIDTH / 2 - 10,
				50);
		g.setColor(color);
		g.fillRect(0, 0, R.WIDTH, R.HEIGTH);
	}

	@Override
	public void run() {

	}

}
