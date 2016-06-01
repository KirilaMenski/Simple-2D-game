package by.ansgar.rpg.object;

import java.awt.Graphics;

public interface GameObject {

	public void update();

	public void draw(Graphics g);

	// action
	public void damaged(int damage);

	public void healing(int heal);

	public void regeneration();
	
	public double getRegenSpeed();
	
	public void setRegenSpeed(double regenSpeed);
	
	public boolean isHit();
	
	public void setHit(boolean hit);
	
	public boolean isHealing();
	
	public void setHealing(boolean healing);
	
	public double getCurrentIntelligence();
	
	public void setCurrentIntelligence(double currentIntelligence);

	// Characteristics
	public int getHealth();

	public void setHealth(int health);

	public double getCurrentHealth();

	public void setCurrentHealth(double currentHealth);

	public int getStrength();

	public void setStrength(int strength);

	public int getIntelligence();

	public void setIntelligence(int intelligence);

	public int getAgility();

	public void setAgility(int agility);

	// Moving
	public boolean isTop();

	public void setTop(boolean top);

	public boolean isDown();

	public void setDown(boolean down);

	public boolean isLeft();

	public void setLeft(boolean left);

	public boolean isRight();

	public void setRight(boolean right);
	
	public boolean isSwimming();
	
	public void setSwimming(boolean swimming);

	// Coordination
	public void setX(int x);

	public int getX();

	public void setY(int y);

	public int getY();
}
