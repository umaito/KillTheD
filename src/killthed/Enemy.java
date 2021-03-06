package killthed;

import java.awt.Rectangle;
import java.util.ArrayList;



public class Enemy {

	private int  power, speedY, centerX, centerY;
	private Background bg = StartingClass.getBg1();
	private TheD thed = StartingClass.getTheD();
	public Rectangle r = new Rectangle(0, 0, 0, 0);
	

	public ArrayList<BadProjectile> badprojectiles = new ArrayList<BadProjectile>();


	public void update() {
		
		centerY += speedY;
		speedY = bg.getSpeedY();
		r.setBounds(centerX - 25, centerY - 25, 60, 60);
		if (r.intersects(TheD.yellowRed)) {
			checkCollision();
		}
		
	}

	private void checkCollision() {
		if (r.intersects(TheD.rect) || r.intersects(TheD.rect2)) {
			System.out.println("collision");
			thed.setCenterX(centerX+800);
		}
	}
	
public void follow() {
		
	}

	public void die() {
	}

	public void attack() {

	}


	public int getPower() {
		return power;
	}

	public int getSpeedY() {
		return speedY;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}


	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
	public ArrayList getBadProjectiles() {
		return badprojectiles;
	}
}