package by.ansgar.rpg.object;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Enemy implements GameObject {

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
	private Random rnd = new Random();
	private int direction;
	// Enemy sprites
	private BufferedImage image;
	private BufferedImage[] enemySprite = new BufferedImage[4];
	// Enemy characteristics
	private int health;
	private double currentHealth;
	private double regenSpeed;
	private double regenIntSpeed;
	private double currentIntelligence;
	private int strength;
	private int intelligence;
	private int agility;

	// Enemy moving
	private boolean top;
	private boolean down;
	private boolean right;
	private boolean left;
	// fix bounds
	private boolean boundLeft;
	private boolean boundRight;
	private boolean boundTop;
	private boolean boundDown;

	private boolean swimming;

	// action
	private boolean hit;
	private boolean healing;

	public Enemy() {

	}

	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		width = 40;
		height = 56;
		health = 500;
		dx = 2;
		dy = 2;
		strength = 25;
		intelligence = 95;
		agility = 19;
		currentHealth = health;
		currentIntelligence = intelligence;
		regenIntSpeed = 0.7;
		regenSpeed = 0.5;
		startTime = System.nanoTime();
		try {

			image = ImageIO.read(getClass()
					.getResourceAsStream("/image/sprites/enemy/orc/orc.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update() {

		if (top) {
			y -= dy;
			for (int i = 0; i < enemySprite.length; i++) {
				enemySprite[i] = image.getSubimage(i * width, height * 3, width,
						height);
			}

		} else if (down) {
			y += dy;
			for (int i = 0; i < enemySprite.length; i++) {
				enemySprite[i] = image.getSubimage(i * width, height * 0, width,
						height);
			}
		} else if (right) {
			x += dx;
			for (int i = 0; i < enemySprite.length; i++) {
				enemySprite[i] = image.getSubimage(i * width, height * 2, width,
						height);
			}
		} else if (left) {
			x -= dx;
			for (int i = 0; i < enemySprite.length; i++) {
				enemySprite[i] = image.getSubimage(i * width, height * 1, width,
						height);
			}
		}
		System.out.println(
				down + "/" + top
						+ "/" + left + "/"
						+ right + "/");
		currentFrameRender();

	}

	public void currentFrameRender() {
		long elapsed = (System.nanoTime() - startTime) / 10000;
		if (elapsed > 25000) {
			currentFrame++;
			startTime = System.nanoTime();

		}
		if (currentFrame == enemySprite.length) {
			currentFrame = 0;
			if (boundTop) {
				currentDirection(1);
			} else if (boundDown) {
				currentDirection(0);
			} else if (boundRight) {
				currentDirection(3);
			} else if (boundLeft) {
				currentDirection(2);
			} else {
				direction = rnd.nextInt(4);
				currentDirection(direction);
			}
			setBoundDown(false);
			setBoundLeft(false);
			setBoundRight(false);
			setBoundTop(false);

		}
	}

	private void currentDirection(int dir) {
		direction = dir;

		if (direction == 0) {
			top = true;
			down = false;
			right = false;
			left = false;
		}
		if (direction == 1) {
			down = true;
			top = false;
			right = false;
			left = false;
		}
		if (direction == 2) {
			right = true;
			top = false;
			down = false;
			left = false;
		}
		if (direction == 3) {
			left = true;
			top = false;
			down = false;
			right = false;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(enemySprite[currentFrame], x, y, null);
		
		g.setColor(Color.red);
		g.fillRect(x, y - 5, (int) currentHealth/10, 5);

	}

	@Override
	public void damaged(int damage) {

	}

	@Override
	public void healing(int heal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void regeneration() {
		// TODO Auto-generated method stub

	}

	@Override
	public double getRegenSpeed() {
		// TODO Auto-generated method stub
		return regenIntSpeed;
	}

	@Override
	public void setRegenSpeed(double regenSpeed) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isHit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHit(boolean hit) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isHealing() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHealing(boolean healing) {
		// TODO Auto-generated method stub

	}

	@Override
	public double getCurrentIntelligence() {
		// TODO Auto-generated method stub
		return currentIntelligence;
	}

	@Override
	public void setCurrentIntelligence(double currentIntelligence) {
		this.currentIntelligence = currentIntelligence;

	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return health;
	}

	@Override
	public void setHealth(int health) {
		this.health = health;

	}

	@Override
	public double getCurrentHealth() {
		return currentHealth;
	}

	@Override
	public void setCurrentHealth(double currentHealth) {
		this.currentHealth = currentHealth;

	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return strength;
	}

	@Override
	public void setStrength(int strength) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getIntelligence() {
		// TODO Auto-generated method stub
		return intelligence;
	}

	@Override
	public void setIntelligence(int intelligence) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getAgility() {
		// TODO Auto-generated method stub
		return agility;
	}

	@Override
	public void setAgility(int agility) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isTop() {
		// TODO Auto-generated method stub
		return top;
	}

	@Override
	public void setTop(boolean top) {
		this.top = top;

	}

	@Override
	public boolean isDown() {
		// TODO Auto-generated method stub
		return down;
	}

	@Override
	public void setDown(boolean down) {
		this.down = down;

	}

	@Override
	public boolean isLeft() {
		return left;
	}

	@Override
	public void setLeft(boolean left) {
		this.left = left;

	}

	@Override
	public boolean isRight() {
		// TODO Auto-generated method stub
		return right;
	}

	@Override
	public void setRight(boolean right) {
		this.right = right;

	}

	@Override
	public boolean isSwimming() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSwimming(boolean swimming) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setX(int x) {
		this.x = x;

	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setY(int y) {
		this.y = y;

	}

	@Override
	public int getY() {
		return y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isBoundLeft() {
		return boundLeft;
	}

	public void setBoundLeft(boolean boundLeft) {
		this.boundLeft = boundLeft;
	}

	public boolean isBoundRight() {
		return boundRight;
	}

	public void setBoundRight(boolean boundRight) {
		this.boundRight = boundRight;
	}

	public boolean isBoundTop() {
		return boundTop;
	}

	public void setBoundTop(boolean boundTop) {
		this.boundTop = boundTop;
	}

	public boolean isBoundDown() {
		return boundDown;
	}

	public void setBoundDown(boolean boundDown) {
		this.boundDown = boundDown;
	}

}
