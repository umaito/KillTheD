package killthed;

import java.awt.Rectangle;



public class Enemy {

	private int  power, speedY, centerX, centerY;
	private Background bg = StartingClass.getBg1();
	private TheD thed = StartingClass.getTheD();
	public Rectangle r = new Rectangle(0, 0, 0, 0);
	public int health = 5;
	private int movementSpeed;

	
	public void update() {
		follow();
		centerY += speedY;
		speedY = bg.getSpeedY();
		r.setBounds(centerX - 25, centerY - 25, 50, 60);

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
		
		if (centerY < -95 || centerY > 810){
			movementSpeed = 0;
		}

		else if (Math.abs(thed.getCenterY() - centerY) < 5) {
			movementSpeed = 0;
		}

		else {

			if (thed.getCenterY() >= centerY) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}

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
}