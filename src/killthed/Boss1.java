package killthed;

import java.util.ArrayList;

public class Boss1 extends Enemy {
	public int health = 10;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private int speedX = 0;
	final int MOVESPEED = 5;

	

	public Boss1(int centerX, int centerY) {
		setCenterX(centerX);
		setCenterY(centerY);

	}
	
	public void moveRight() {
		
			speedX = MOVESPEED;
		
	}

	public void moveLeft() {
		
			speedX = -MOVESPEED;
		
	}

	public void shoot() {

		BadProjectile p = new BadProjectile(getCenterX(), getCenterY() - 25);
		badprojectiles.add(p);

	}


	

}
