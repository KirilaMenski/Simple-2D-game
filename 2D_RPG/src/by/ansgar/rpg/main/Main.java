package by.ansgar.rpg.main;

import javax.swing.JFrame;

import by.ansgar.rpg.constants.R;
import by.ansgar.rpg.listener.KeyBoardListener;

public class Main {

	public static void main(String str[]) {

		JFrame frame = new JFrame();
		GamePanel game = new GamePanel();
		frame.setTitle(R.GAME_TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setVisible(true);
		frame.setSize(R.WIDTH, R.HEIGTH);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.add(game);
		frame.addKeyListener(new KeyBoardListener());
		game.start();

	}

}
