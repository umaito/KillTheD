package killthed;

public class Boss2 extends Enemy {
	public int health = 10;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private int speedX = 0;
	final int MOVESPEED = 1;

	public Boss2(int centerX, int centerY) {

		setCenterX(centerX);
		setCenterY(centerY);
	}

	/*
	 * public void update(){ setCenterX(getCenterX()+ speedX);
	 * 
	 * }
	 */
	public void moveRight() {

		speedX = MOVESPEED;
		setCenterX(getCenterX() + speedX);
	}

	public void moveLeft() {

		speedX = -MOVESPEED;
		setCenterX(getCenterX() + speedX);

	}

	public void stopRight() {
		setMovingRight(false);

	}

	public void stopLeft() {
		setMovingLeft(false);

	}

	public void shoot() {

		BadProjectile p = new BadProjectile(getCenterX() - 10,
				getCenterY() - 25);
		badprojectiles.add(p);
		BadProjectile b = new BadProjectile(getCenterX() + 10,
				getCenterY() - 25);
		badprojectiles.add(b);

	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

}
