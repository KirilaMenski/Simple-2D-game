package by.ansgar.rpg.background;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import by.ansgar.rpg.constants.GameState;
import by.ansgar.rpg.constants.R;
import by.ansgar.rpg.listener.MouseInput;
import by.ansgar.rpg.main.GamePanel;
import by.ansgar.rpg.manager.GameStateManager;

public class Menu {

	private int width = 200;
	private int height = 50;
	private static int i = 0;
	private static int j = 0;
	private static int k = 0;

	private BufferedImage newGame, option, exit;
	private List<BufferedImage> newGameFocused = new ArrayList<BufferedImage>();
	private List<BufferedImage> optionFocused = new ArrayList<BufferedImage>();
	private List<BufferedImage> exitFocused = new ArrayList<BufferedImage>();

	public Menu() {
		try {
			newGame = ImageIO.read(getClass().getResourceAsStream(
					"/image/menu/newgame.png"));
			option = ImageIO.read(getClass().getResourceAsStream(
					"/image/menu/option.png"));
			exit = ImageIO.read(getClass().getResourceAsStream(
					"/image/menu/exit.png"));

			newGameFocused.add(newGame.getSubimage(0, 0, width, height));
			newGameFocused.add(newGame.getSubimage(width, 0, width, height));
			optionFocused.add(option.getSubimage(0, 0, width, height));
			optionFocused.add(option.getSubimage(width, 0, width, height));
			exitFocused.add(exit.getSubimage(0, 0, width, height));
			exitFocused.add(exit.getSubimage(width, 0, width, height));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {
		if (GameStateManager.menuChose) {
			if (MouseInput.mouseX >= R.WIDTH / 2
					&& MouseInput.mouseX <= R.WIDTH / 2 + width
					&& MouseInput.mouseY >= R.HEIGTH / 2
					&& MouseInput.mouseY < R.HEIGTH / 2 + height) {
				i = 1;
			} else {
				i = 0;
			}
			if (MouseInput.mouseX >= R.WIDTH / 2
					&& MouseInput.mouseX <= R.WIDTH / 2 + width
					&& MouseInput.mouseY >= R.HEIGTH / 2 + 50
					&& MouseInput.mouseY < R.HEIGTH / 2 + 50 + height) {
				j = 1;
			} else {
				j = 0;
			}
			if (MouseInput.mouseX >= R.WIDTH / 2
					&& MouseInput.mouseX <= R.WIDTH / 2 + width
					&& MouseInput.mouseY >= R.HEIGTH / 2 + 100
					&& MouseInput.mouseY < R.HEIGTH / 2 + 100 + height) {
				k = 1;
			} else {
				k = 0;
			}

			if (MouseInput.mousePX >= R.WIDTH / 2
					&& MouseInput.mousePX <= R.WIDTH / 2 + width
					&& MouseInput.mousePY >= R.HEIGTH / 2
					&& MouseInput.mousePY < R.HEIGTH / 2 + height) {
				GamePanel.state = GameState.GAME;
			}
			if (MouseInput.mousePX >= R.WIDTH / 2
					&& MouseInput.mousePX <= R.WIDTH / 2 + width
					&& MouseInput.mousePY >= R.HEIGTH / 2 + 50
					&& MouseInput.mousePY < R.HEIGTH / 2 + 50 + height) {
				GamePanel.state = GameState.OPTION;
			}
			if (MouseInput.mousePX >= R.WIDTH / 2
					&& MouseInput.mousePX <= R.WIDTH / 2 + width
					&& MouseInput.mousePY >= R.HEIGTH / 2 + 100
					&& MouseInput.mousePY < R.HEIGTH / 2 + 100 + height) {
				GamePanel.state = GameState.EXIT;
			}
		}
	}

	public void draw(Graphics2D g) {
		g.drawImage(newGameFocused.get(i), R.WIDTH / 2, R.HEIGTH / 2, null);
		g.drawImage(optionFocused.get(j), R.WIDTH / 2, R.HEIGTH / 2 + 50, null);
		g.drawImage(exitFocused.get(k), R.WIDTH / 2, R.HEIGTH / 2 + 100, null);
	}

}
