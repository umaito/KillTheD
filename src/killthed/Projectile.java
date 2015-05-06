package killthed;

import java.awt.Rectangle;

public class Projectile {
	private int x, y, speedY;
	private boolean visible;
	private Rectangle r;

	public Projectile(int startX, int startY) {
		x = startX;
		y = startY;
		speedY = 7;
		visible = true;
		
		r = new Rectangle(0, 0, 0, 0);
	}

	public void update() {
		y += -speedY;
		r.setBounds(x, y, 5, 5);
		if (y < 0 ) {
			visible = false;
			r = null;
		}
		if (y > 1){
			checkCollision();
		}
	}
	
	private void checkCollision() {
		if(r.intersects(StartingClass.hb.r)){
			visible = false;
			if (StartingClass.hb.health > 0) {
				StartingClass.hb.health -= 1;
			}
			if (StartingClass.hb.health == 0) {
				StartingClass.hb.setCenterX(-100);
				StartingClass.score += 5;
				
			}
		}
		
		if (r.intersects(StartingClass.hb2.r)){
			visible = false;
			if (StartingClass.hb2.health > 0) {
				StartingClass.hb2.health -= 1;
			}
			if (StartingClass.hb2.health == 0) {
				StartingClass.hb2.setCenterX(-100);
				StartingClass.score += 5;
			}

		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedY() {
		return speedY;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
