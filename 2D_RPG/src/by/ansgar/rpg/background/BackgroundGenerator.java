package by.ansgar.rpg.background;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import by.ansgar.rpg.main.GamePanel;

public class BackgroundGenerator {

	private BufferedImage image;
	private int[][] map;
	private int[] subInt = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 };
	private int numCols;
	private int numRows;
	private int width = 30;
	private int height = 30;

	public void readFile(String path) {

		try {
			image = ImageIO.read(getClass()
					.getResourceAsStream("/background_level/map.png"));

			InputStream in = getClass().getResourceAsStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			numCols = 28;
			numRows = 20;

			map = new int[numRows][numCols];

			String delims = " ";
			for (int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for (int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {
		fixBoundsToPlayer();
		fixBoundsToEnemy();
	}

	private void fixBoundsToPlayer() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {

				if (map[i][j] == 4
						&& GamePanel.player.getX() < (width * j + 30)) {
					GamePanel.player.setX(width * j + 30);
				}
				if (map[i][j] == 5
						&& GamePanel.player.getX() + 30 > (width * j)) {
					GamePanel.player.setX(width * j - 30);
				}
				if (map[i][j] == 6
						&& GamePanel.player.getY() < (height * i + 30)) {
					GamePanel.player.setY(height * i + 30);
				}
				if (map[i][j] == 7
						&& GamePanel.player.getY() + 30 > (height * i)) {
					GamePanel.player.setY(height * i - 30);
				}
				if (map[i][j] == 10
						&& GamePanel.player.getX() + 20 > (width * j)
						&& GamePanel.player.getX() < (width * j + 20)
						&& GamePanel.player.getY() + 30 > (height * i)
						&& GamePanel.player.getY() < (height * i + 30)) {
					GamePanel.player.setSwimming(true);
				}
				if (map[i][j] != 10
						&& GamePanel.player.getX() + 30 > (width * j)
						&& GamePanel.player.getX() < (width * j + 30)
						&& GamePanel.player.getY() + 30 > (height * i)
						&& GamePanel.player.getY() < (height * i + 30)) {
					GamePanel.player.setSwimming(false);
				}

				if (map[i][j] == 11
						&& GamePanel.player.getX() < (width * j + 30)
						&& GamePanel.player.getX() + 30 > (width * j)
						&& GamePanel.player.getY() + 30 > (height * i)
						&& GamePanel.player.getY() < (height * i + 30)) {

					if (GamePanel.player.isLeft()) {
						GamePanel.player.setX(width * j + 30);
					} else if (GamePanel.player.isRight()) {
						GamePanel.player.setX(width * j - 30);
					} else if (GamePanel.player.isDown()) {
						GamePanel.player.setY(height * i - 30);
					} else if (GamePanel.player.isTop()) {
						GamePanel.player.setY(height * i + 30);
					}

				}

			}
		}
	}

	private void fixBoundsToEnemy() {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				for (int k = 0; k < GamePanel.enemy.size(); k++) {
					if (map[i][j] == 4 && GamePanel.enemy.get(k)
							.getX() < (width * j + 30)) {
						GamePanel.enemy.get(k).setBoundLeft(true);
						GamePanel.enemy.get(k).setBoundRight(false);
						GamePanel.enemy.get(k).setBoundTop(false);
						GamePanel.enemy.get(k).setBoundDown(false);
					}
					if (map[i][j] == 5 && GamePanel.enemy.get(k).getX()
							+ 30 > (width * j)) {
						GamePanel.enemy.get(k).setBoundRight(true);
						GamePanel.enemy.get(k).setBoundLeft(false);
						GamePanel.enemy.get(k).setBoundTop(false);
						GamePanel.enemy.get(k).setBoundDown(false);
					}
					if (map[i][j] == 6 && GamePanel.enemy.get(k)
							.getY() < (height * i + 30)) {
						GamePanel.enemy.get(k).setBoundTop(true);
						GamePanel.enemy.get(k).setBoundLeft(false);
						GamePanel.enemy.get(k).setBoundRight(false);
						GamePanel.enemy.get(k).setBoundDown(false);
					}
					if (map[i][j] == 7 && GamePanel.enemy.get(k).getY()
							+ 30 > (height * i)) {
						GamePanel.enemy.get(k).setBoundDown(true);
						GamePanel.enemy.get(k).setBoundLeft(false);
						GamePanel.enemy.get(k).setBoundRight(false);
						GamePanel.enemy.get(k).setBoundTop(false);
					}

					if (map[i][j] == 10
							&& GamePanel.enemy.get(k).getX() < (width * j + 40)
							&& GamePanel.enemy.get(k).getX() + 40 > (width * j)
							&& GamePanel.enemy.get(k).getY() + 56 > (height * i)
							&& GamePanel.enemy.get(k)
									.getY() < (height * i + 56)) {

						if (GamePanel.enemy.get(k).isLeft()) {
							GamePanel.enemy.get(k).setBoundLeft(true);
							GamePanel.enemy.get(k).setBoundRight(false);
							GamePanel.enemy.get(k).setBoundTop(false);
							GamePanel.enemy.get(k).setBoundDown(false);
						} else if (GamePanel.enemy.get(k).isRight()) {
							GamePanel.enemy.get(k).setBoundLeft(false);
							GamePanel.enemy.get(k).setBoundRight(true);
							GamePanel.enemy.get(k).setBoundTop(false);
							GamePanel.enemy.get(k).setBoundDown(false);
						} else if (GamePanel.enemy.get(k).isDown()) {
							GamePanel.enemy.get(k).setBoundLeft(false);
							GamePanel.enemy.get(k).setBoundRight(false);
							GamePanel.enemy.get(k).setBoundTop(false);
							GamePanel.enemy.get(k).setBoundDown(true);
						} else if (GamePanel.player.isTop()) {
							GamePanel.enemy.get(k).setBoundLeft(false);
							GamePanel.enemy.get(k).setBoundRight(false);
							GamePanel.enemy.get(k).setBoundTop(true);
							GamePanel.enemy.get(k).setBoundDown(false);
						}

					}

					if (map[i][j] == 11
							&& GamePanel.enemy.get(k).getX() < (width * j + 40)
							&& GamePanel.enemy.get(k).getX() + 40 > (width * j)
							&& GamePanel.enemy.get(k).getY() + 56 > (height * i)
							&& GamePanel.enemy.get(k)
									.getY() < (height * i + 56)) {

						if (GamePanel.enemy.get(k).isLeft()) {
							GamePanel.enemy.get(k).setBoundLeft(true);
							GamePanel.enemy.get(k).setBoundRight(false);
							GamePanel.enemy.get(k).setBoundTop(false);
							GamePanel.enemy.get(k).setBoundDown(false);
						} else if (GamePanel.enemy.get(k).isRight()) {
							GamePanel.enemy.get(k).setBoundLeft(false);
							GamePanel.enemy.get(k).setBoundRight(true);
							GamePanel.enemy.get(k).setBoundTop(false);
							GamePanel.enemy.get(k).setBoundDown(false);
						} else if (GamePanel.enemy.get(k).isDown()) {
							GamePanel.enemy.get(k).setBoundLeft(false);
							GamePanel.enemy.get(k).setBoundRight(false);
							GamePanel.enemy.get(k).setBoundTop(false);
							GamePanel.enemy.get(k).setBoundDown(true);
						} else if (GamePanel.player.isTop()) {
							GamePanel.enemy.get(k).setBoundLeft(false);
							GamePanel.enemy.get(k).setBoundRight(false);
							GamePanel.enemy.get(k).setBoundTop(true);
							GamePanel.enemy.get(k).setBoundDown(false);
						}

					}

				}
			}
		}
	}

	public void draw(Graphics g) {
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				for (int k = 0; k < subInt.length; k++) {
					if (map[i][j] == subInt[k]) {
						g.drawImage(
								image.getSubimage(width * k, 0, width, height),
								width * j, height * i, null);
					}
				}
			}
		}

	}

}
