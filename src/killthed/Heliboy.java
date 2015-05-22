package killthed;

public class Heliboy extends Enemy {
	public int health = 2;

	public Heliboy( int centerX, int centerY ) {
		setCenterX(centerX);
		setCenterY(centerY);
	}
	public void shoot() {

		BadProjectile p = new BadProjectile(getCenterX(), getCenterY() - 25);
		badprojectiles.add(p);

	}

}
