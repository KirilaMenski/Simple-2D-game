package by.ansgar.rpg.manager;

import java.awt.Color;

import by.ansgar.rpg.background.Background;
import by.ansgar.rpg.constants.GameState;
import by.ansgar.rpg.main.GamePanel;

public class GameStateManager {
	
	public static boolean menuChose = false;
	public static boolean gameChose = false;
	public static boolean optionChose = false;
	
	public static void stateRendering(GameState state) {

		switch (state) {
		case MENU:
			GamePanel.background = new Background("/image/menu/menu_background.jpg");
			menuChose = true;
			gameChose = false;
			optionChose = false;
			break;
		case GAME:
			GamePanel.background = new Background("/image/game/background/game_background.jpg");
			break;
		case OPTION:
			GamePanel.background = new Background("/image/menu/option_background.jpg");
			menuChose = false;
			gameChose = false;
			optionChose = true;
			break;
		case EXIT:
			System.exit(0);
			break;

		default:
			break;
		}

	}

}
