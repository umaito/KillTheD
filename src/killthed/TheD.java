package killthed;

import java.awt.Rectangle;
import java.util.ArrayList;

public class TheD {
	// In Java, Class Variables should be private so that only its methods can
	// change them.

	// Constants are Here
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	final int GROUND = 640;

	private int centerX = 240;
	private int centerY = GROUND;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean movingDown = false;
	private boolean movingUp = false;
	private boolean ducked = false;

	private boolean readyToFire = true;

	private static Background bg1 = StartingClass.getBg1();
	private static Background bg2 = StartingClass.getBg2();

	private int speedX = 0;
	private int speedY = 0;
	public static Rectangle rect = new Rectangle(0, 0, 0, 0);
	public static Rectangle rect2 = new Rectangle(0, 0, 0, 0);

	public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);

	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public void update() {

		// Moves Character or Scrolls Background accordingly.

		if (speedY == 0 || speedY < 0) {
			bg1.setSpeedY(MOVESPEED);
			bg2.setSpeedY(MOVESPEED);

		}

		if (speedX < 0) {
			centerX += speedX;
		}
		if (centerX <= 415 && speedX > 0) {
			centerX += speedX;
		}
		if (speedY == 10) {
			centerY += speedY;
			bg1.setSpeedY(MOVESPEED);
			bg2.setSpeedY(MOVESPEED);
		}
		if (centerY <= 810 && speedY > 0) {
			centerY += speedY;
			bg1.setSpeedY(MOVESPEED);
			bg2.setSpeedY(MOVESPEED);
		}

		if (speedY < 0 && centerY < 200) {
			bg1.setSpeedY(MOVESPEED);
			bg2.setSpeedY(MOVESPEED);
		}

		// Updates Y Position
		centerY += speedY;
		if (centerY + speedY >= GROUND) {
			centerY = GROUND;
			bg1.setSpeedY(MOVESPEED);
			bg2.setSpeedY(MOVESPEED);
		}

		// Handles Jumping
		if (jumped == true) {
			speedY += 1;

			if (centerY + speedY >= GROUND) {
				centerY = GROUND;
				speedY = 0;
				jumped = false;
			}

		}

		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}
		if (centerY + speedY <= 150) {
			centerY = 151;
		}

		rect.setRect(centerX - 25, centerY - 63, 50, 55);
		rect2.setRect(centerX - 35, rect.getY() + 40, 68, 90);
		
		yellowRed.setRect(centerX - 110, centerY - 110, 180, 180);

	}

	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void moveUp() {
		
			speedY = -MOVESPEED;
		
	}

	public void moveDown() {
		
			speedY = MOVESPEED;
		
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	public void stopUp() {
		setMovingUp(false);
		stop();
	}

	public void stopDown() {
		setMovingDown(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}
		if (isMovingUp() == false && isMovingDown() == false) {
			speedY = 0;
		}

		if (isMovingUp() == false && isMovingDown() == true) {
			moveDown();
		}

		if (isMovingUp() == true && isMovingDown() == false) {
			moveUp();
		}

	}

	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			jumped = true;
		}

	}

	public void shoot() {
		if (readyToFire) {
			Projectile p = new Projectile(centerX , centerY - 25);
			projectiles.add(p);
		}
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public boolean isMovingDown() {
		return movingDown;
	}

	public boolean isMovingUp() {
		return movingUp;
	}

	public void setMovingDown(boolean movingDown) {
		this.movingDown = movingDown;
	}

	public void setMovingUp(boolean movingUp) {
		this.movingUp = movingUp;
	}

	public ArrayList getProjectiles() {
		return projectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}
}
