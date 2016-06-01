package by.ansgar.rpg.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player implements GameObject {

	// Fields
	// Coordination
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int width;
	private int height;
	private int currentFrame;
	private long startTime;
	// Player sprites
	private BufferedImage image;
	private BufferedImage[] playerSprite = new BufferedImage[4];

	// Game characteristics
	private int health;
	private double currentHealth;
	private double regenSpeed;
	private double regenIntSpeed;
	private double currentIntelligence;
	private int strength;
	private int intelligence;
	private int agility;

	// Player moving
	private boolean top;
	private boolean down;
	private boolean right;
	private boolean left;

	private boolean swimming;

	// action
	private boolean hit;
	private boolean healing;

	public Player() {

	}

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		width = 30;
		height = 30;
		health = 100;
		strength = 25;
		intelligence = 95;
		agility = 19;
		currentHealth = health;
		currentIntelligence = intelligence;
		regenIntSpeed = 0.7;
		regenSpeed = 0.5;
		startTime = System.nanoTime();

	}

	public void update() {
		try {

			if (swimming) {
				image = ImageIO.read(getClass().getResourceAsStream(
						"/image/sprites/player/player_swimming.png"));
				dx = 1;
				dy = 1;
			}else {
				image = ImageIO.read(getClass().getResourceAsStream(
						"/image/sprites/player/player1.png"));
				dx = 2;
				dy = 2;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		if (top) {
			y -= dy;
			for (int i = 0; i < playerSprite.length; i++) {
				playerSprite[i] = image.getSubimage(i * width, height * 4,
						width, height);
			}

		} else if (down) {
			y += dy;
			for (int i = 0; i < playerSprite.length; i++) {
				playerSprite[i] = image.getSubimage(i * width, height * 3,
						width, height);
			}
		} else if (right) {
			x += dx;
			for (int i = 0; i < playerSprite.length; i++) {
				playerSprite[i] = image.getSubimage(i * width, height * 1,
						width, height);
			}
		} else if (left) {
			x -= dx;
			for (int i = 0; i < playerSprite.length; i++) {
				playerSprite[i] = image.getSubimage(i * width, height * 2,
						width, height);
			}
		} else {
			staying();
		}
		currentFrameRender();
		regeneration();
	}

	public void currentFrameRender() {
		long elapsed = (System.nanoTime() - startTime) / 10000;
		if (elapsed > 25000) {
			currentFrame++;
			startTime = System.nanoTime();

		}
		if (currentFrame == playerSprite.length) {
			currentFrame = 0;
		}
	}

	public void damaged(int damage) {
		currentHealth = currentHealth - damage;
	}

	public void healing(int heal) {
		if (currentHealth < health) {
			currentHealth = currentHealth + heal;
			if (currentHealth > health) {
				currentHealth = health;
			}
		}
	}

	public void regeneration() {
		if (currentHealth < health) {
			currentHealth = currentHealth + 0.1 * regenSpeed;
		}
		if (currentIntelligence < intelligence) {
			currentIntelligence = currentIntelligence + 0.1 * regenIntSpeed;
		}
	}

	public void staying() {
		for (int i = 0; i < playerSprite.length; i++) {
			playerSprite[i] = image.getSubimage(i * width, height * 0, width,
					height);
		}
	}

	public void draw(Graphics g) {
		g.drawImage(playerSprite[currentFrame], x, y, 30, 30, null);

		g.setColor(Color.white);
		g.drawString("" + (int) currentHealth, 10, 15);
		g.drawString("" + (int) currentIntelligence, 10, 23);

		g.setColor(Color.red);
		g.fillRect(30, 10, (int) currentHealth, 5);
		g.setColor(Color.blue);
		g.fillRect(30, 18, (int) currentIntelligence, 5);
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

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public boolean isTop() {
		return top;
	}

	public void setTop(boolean top) {
		this.top = top;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public BufferedImage[] getPlayerSprite() {
		return playerSprite;
	}

	public void setPlayerSprite(BufferedImage[] playerSprite) {
		this.playerSprite = playerSprite;
	}

	public double getCurrentHealth() {
		return currentHealth;
	}

	public void setCurrentHealth(double currentHealth) {
		this.currentHealth = currentHealth;
	}

	public double getRegenSpeed() {
		return regenSpeed;
	}

	public void setRegenSpeed(double regenSpeed) {
		this.regenSpeed = regenSpeed;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public boolean isHealing() {
		return healing;
	}

	public void setHealing(boolean healing) {
		this.healing = healing;
	}

	public double getRegenIntSpeed() {
		return regenIntSpeed;
	}

	public void setRegenIntSpeed(double regenIntSpeed) {
		this.regenIntSpeed = regenIntSpeed;
	}

	public double getCurrentIntelligence() {
		return currentIntelligence;
	}

	public void setCurrentIntelligence(double currentIntelligence) {
		this.currentIntelligence = currentIntelligence;
	}

	public boolean isSwimming() {
		return swimming;
	}

	public void setSwimming(boolean swimming) {
		this.swimming = swimming;
	}

}
