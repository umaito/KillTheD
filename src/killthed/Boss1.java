package killthed;


public class Boss1 extends Enemy {
	public int health = 10;


	public Boss1(int centerX, int centerY) {
		setCenterX(centerX);
		setCenterY(centerY);

	}

	public void shoot() {

		BadProjectile p = new BadProjectile(getCenterX(), getCenterY() - 25);
		badprojectiles.add(p);

	}

}
