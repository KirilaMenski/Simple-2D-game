package by.ansgar.rpg.object.player_spells;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import by.ansgar.rpg.main.GamePanel;

public class PlayerSpells {

	private int width;
	private int height;

	private int w;
	private int h;

	private int x;
	private int y;
	private int dx;
	private int dy;
	private int manaCoast;
	private int damage;
	private String spellName;

	private int currentFrame;
	private long startTime;

	private BufferedImage image;
	private BufferedImage[] spellSprite = new BufferedImage[4];

	private boolean top;
	private boolean down;
	private boolean right;
	private boolean left;

	public PlayerSpells() {

	}

	public PlayerSpells(String spellName, boolean top, boolean down,
			boolean right, boolean left) {
		this.top = top;
		this.down = down;
		this.right = right;
		this.left = left;
		x = GamePanel.player.getX();
		y = GamePanel.player.getY();
		width = 30;
		height = 30;
		w = 30;
		h = 30;
		startTime = System.nanoTime();
		this.spellName = spellName;
		checkSpell(spellName);

	}

	private void checkSpell(String spellName) {

		try {
			switch (spellName) {
			case "water":
				image = ImageIO.read(getClass().getResourceAsStream(
						"/image/sprites/player_spells/water.png"));
				manaCoast = 15;
				damage = 25;
				w = 15;
				h = 15;
				dx = 10;
				dy = 10;
				GamePanel.player.setCurrentIntelligence(
						GamePanel.player.getCurrentIntelligence() - manaCoast);
				break;

			case "earth":
				image = ImageIO.read(getClass().getResourceAsStream(
						"/image/sprites/player_spells/earth.png"));
				manaCoast = 30;
				damage = 5;
				w = 30;
				h = 30;
				dx = 0;
				dy = 0;
				GamePanel.player.setCurrentIntelligence(
						GamePanel.player.getCurrentIntelligence() - manaCoast);
				break;
			case "fire":
				image = ImageIO.read(getClass().getResourceAsStream(
						"/image/sprites/player_spells/fire.png"));
				manaCoast = 25;
				damage = 35;
				w = 15;
				h = 15;
				dx = 10;
				dy = 10;
				GamePanel.player.setCurrentIntelligence(
						GamePanel.player.getCurrentIntelligence() - manaCoast);
				break;
			case "air":
				image = ImageIO.read(getClass().getResourceAsStream(
						"/image/sprites/player_spells/air.png"));
				manaCoast = 40;
				damage = 15;
				w = 30;
				h = 30;
				dx = 5;
				dy = 5;
				GamePanel.player.setCurrentIntelligence(
						GamePanel.player.getCurrentIntelligence() - manaCoast);
				break;
			case "healing":
				image = ImageIO.read(getClass().getResourceAsStream(
						"/image/sprites/player_spells/healing.png"));
				manaCoast = 50;
				damage = 50;
				w = 30;
				h = 30;
				dx = 0;
				dy = 0;
				GamePanel.player.setCurrentIntelligence(
						GamePanel.player.getCurrentIntelligence() - manaCoast);
				GamePanel.player.healing(damage);
				break;

			default:
				image = null;
				break;
			}
		} catch (Exception e) {

		}
	}

	public void update() {
		if (top) {
			for (int i = 0; i < spellSprite.length; i++) {
				spellSprite[i] = image.getSubimage(i * width, height * 2, width,
						height);
			}
			y -= dy;
		}
		if (down) {
			for (int i = 0; i < spellSprite.length; i++) {
				spellSprite[i] = image.getSubimage(i * width, height * 3, width,
						height);
			}
			y += dy;
		}
		if (right) {
			for (int i = 0; i < spellSprite.length; i++) {
				spellSprite[i] = image.getSubimage(i * width, height * 0, width,
						height);
			}
			x += dx;
		}

		if (left) {
			for (int i = 0; i < spellSprite.length; i++) {
				spellSprite[i] = image.getSubimage(i * width, height * 1, width,
						height);
			}
			x -= dx;
		}
		// else{
		// spellSprite = null;
		// }

		currentFrameRender();
	}

	public void currentFrameRender() {
		long elapsed = (System.nanoTime() - startTime) / 10000;
		if (elapsed > 25000) {
			currentFrame++;
			startTime = System.nanoTime();

		}
		if (currentFrame == spellSprite.length) {
			if (spellName != "healing") {
				currentFrame = spellSprite.length - 1;
			} else {
				GamePanel.playerSpells
						.remove(GamePanel.playerSpells.size() - 1);
			}
		}
	}

	public void draw(Graphics g) {
		g.drawImage(spellSprite[currentFrame], x, y, h, w, null);
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

	public int getManaCoast() {
		return manaCoast;
	}

	public void setManaCoast(int manaCoast) {
		this.manaCoast = manaCoast;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getSpellName() {
		return spellName;
	}

	public void setSpellName(String spellName) {
		this.spellName = spellName;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public BufferedImage[] getSpellSprite() {
		return spellSprite;
	}

	public void setSpellSprite(BufferedImage[] spellSprite) {
		this.spellSprite = spellSprite;
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

}
