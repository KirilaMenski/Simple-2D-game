package by.ansgar.rpg.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import by.ansgar.rpg.constants.GameState;
import by.ansgar.rpg.main.GamePanel;
import by.ansgar.rpg.object.player_spells.PlayerSpells;

public class KeyBoardListener implements KeyListener {

	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			GamePanel.state = GameState.MENU;
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			GamePanel.player.setTop(true);
			GamePanel.player.setDown(false);
			GamePanel.player.setRight(false);
			GamePanel.player.setLeft(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			GamePanel.player.setDown(true);
			GamePanel.player.setTop(false);
			GamePanel.player.setRight(false);
			GamePanel.player.setLeft(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			GamePanel.player.setRight(true);
			GamePanel.player.setDown(false);
			GamePanel.player.setTop(false);
			GamePanel.player.setLeft(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			GamePanel.player.setLeft(true);
			GamePanel.player.setDown(false);
			GamePanel.player.setRight(false);
			GamePanel.player.setTop(false);
		}

		if (e.getKeyCode() == KeyEvent.VK_R) {
			GamePanel.player.damaged(5);
		}

		if (e.getKeyCode() == KeyEvent.VK_1) {
			GamePanel.playerSpells.add(new PlayerSpells("water",
					GamePanel.player.isTop(), GamePanel.player.isDown(),
					GamePanel.player.isRight(), GamePanel.player.isLeft()));
		}

		if (e.getKeyCode() == KeyEvent.VK_2) {
			GamePanel.playerSpells.add(new PlayerSpells("earth",
					GamePanel.player.isTop(), GamePanel.player.isDown(),
					GamePanel.player.isRight(), GamePanel.player.isLeft()));
		}

		if (e.getKeyCode() == KeyEvent.VK_3) {
			GamePanel.playerSpells.add(new PlayerSpells("fire",
					GamePanel.player.isTop(), GamePanel.player.isDown(),
					GamePanel.player.isRight(), GamePanel.player.isLeft()));
		}

		if (e.getKeyCode() == KeyEvent.VK_4) {
			GamePanel.playerSpells.add(new PlayerSpells("air",
					GamePanel.player.isTop(), GamePanel.player.isDown(),
					GamePanel.player.isRight(), GamePanel.player.isLeft()));
		}

		if (e.getKeyCode() == KeyEvent.VK_5) {
			GamePanel.playerSpells.add(new PlayerSpells("healing",
					GamePanel.player.isTop(), GamePanel.player.isDown(),
					GamePanel.player.isRight(), GamePanel.player.isLeft()));
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			GamePanel.player.setTop(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			GamePanel.player.setDown(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			GamePanel.player.setRight(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			GamePanel.player.setLeft(false);
		}
	}

	public void keyTyped(KeyEvent e) {

	}

}
