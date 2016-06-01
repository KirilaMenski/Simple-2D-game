package by.ansgar.rpg.weather;

import java.util.Random;

import by.ansgar.rpg.constants.R;
import by.ansgar.rpg.main.GamePanel;

public class WetherGeneration {
	static Random rnd = new Random();

	public static int countCurrentSeason() {
		int currentweather = 2;
//		 try {
//		 Thread.sleep(3000);
//		 Random rnd = new Random();
//		 currentweather = rnd.nextInt(4);
//		 } catch (InterruptedException e) {
//		 e.printStackTrace();
//		 }

		return currentweather;
	}

	public static void wetherGeneration() {
		GamePanel.weather.add(new Weather(rnd.nextInt(R.WIDTH), rnd.nextInt(R.HEIGTH)));

	}

}
