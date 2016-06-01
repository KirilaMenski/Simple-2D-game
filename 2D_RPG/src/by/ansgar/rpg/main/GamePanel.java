package by.ansgar.rpg.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import by.ansgar.rpg.background.Background;
import by.ansgar.rpg.background.BackgroundGenerator;
import by.ansgar.rpg.state_manager.StateManager;
import by.ansgar.rpg.states.Menu;
import by.ansgar.rpg.times_of_day.TimesOfDay;
import by.ansgar.rpg.weather.Weather;
import by.ansgar.rpg.weather.WeatherGeneration;
import by.ansgar.rpg.constants.GameState;
import by.ansgar.rpg.constants.R;
import by.ansgar.rpg.listener.MouseInput;
import by.ansgar.rpg.music_manager.AudioThread;
import by.ansgar.rpg.object.Enemy;
import by.ansgar.rpg.object.GameObject;
import by.ansgar.rpg.object.Player;
import by.ansgar.rpg.object.player_spells.PlayerSpells;
import by.ansgar.rpg.object.player_spells.SpellsBar;

public class GamePanel extends JPanel implements Runnable {

	/* Fields */
	private BufferedImage bufferedImage;
	private Graphics2D g;
	private Thread thread;
	private Thread audio = new Thread(new AudioThread());

	public static GameState state = GameState.MENU;
	public static Background background;
	public static GameObject player;

	private BackgroundGenerator lvlBack = new BackgroundGenerator();
	private Menu menu = new Menu();
	private SpellsBar spellBar = new SpellsBar();
	private TimesOfDay time = new TimesOfDay();

	public static List<PlayerSpells> playerSpells = new ArrayList<PlayerSpells>();
	public static List<Weather> weather = new ArrayList<Weather>();
	public static List<Enemy> enemy = new ArrayList<Enemy>();

	/* GamePanel Constructor */
	public GamePanel() {
		player = new Player(65, 55);
		enemy.add(new Enemy(200, 100));
		enemy.add(new Enemy(200, 200));
		enemy.add(new Enemy(200, 250));
		lvlBack.readFile("/background_level/lvl1/lvl1.txt");
		addMouseListener(new MouseInput());
		addMouseMotionListener(new MouseInput());
	}

	/* In this method will start thread */
	public void start() {
		thread = new Thread(this);
		thread.start();
		audio.start();
	}

	/* Method for updating GamePanel */
	public void update() {
		// Updated game state
		StateManager.stateRendering(state);
		// Update Menu
		menu.update();
		// update player
		player.update();

		// update player spells
		for (int i = 0; i < playerSpells.size(); i++) {
			playerSpells.get(i).update();
		}
		lvlBack.update();

		// weather update
		for (int i = 0; i < weather.size(); i++) {
			weather.get(i).update();
			if (weather.get(i).getX() < 0 || weather.get(i).getY() > R.HEIGTH) {
				weather.remove(i);
			}
		}

		// update enemy
		for (int i = 0; i < enemy.size(); i++) {
			enemy.get(i).update();
		}

		// time update
		time.update();

	}

	// Method for draw our component
	public void render() {
		// Draw background
		background.draw(g);
		// Draw GameState
		switch (state) {
		case MENU:
			menu.draw(g);
			break;

		case GAME:
			lvlBack.draw(g);
			player.draw(g);

			// draw player spells
			for (int i = 0; i < playerSpells.size(); i++) {
				playerSpells.get(i).draw(g);
			}

			// draw enemy
			for (int i = 0; i < enemy.size(); i++) {
				enemy.get(i).draw(g);
			}

			// draw weather
			for (int i = 0; i < weather.size(); i++) {
				weather.get(i).draw(g);
			}
			// draw spell bar
			spellBar.draw(g);

			// draw time
			time.draw(g);

			// Generation wether
			WeatherGeneration.wetherGeneration();
			break;

		default:
			break;
		}

	}

	// Method for initializing Graphics
	public void draw() {
		Graphics g2 = this.getGraphics();
		g2.drawImage(bufferedImage, 0, 0, null);
		g2.dispose();
	}

	@SuppressWarnings("static-access")
	public void run() {

		bufferedImage = new BufferedImage(800, 640, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) bufferedImage.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// Game performance
		while (true) {

			update();
			render();
			draw();

			try {
				thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
