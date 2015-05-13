package killthed;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import framework.RandomNumberGenerator;

public class StartingClass extends Applet implements Runnable, KeyListener {

	enum GameState {
		Running, Dead
	}

	GameState state = GameState.Running;

	public static TheD thed;

	public static Heliboy hb, hb2, hb3, hb4;
	public static Boss1 boss1;
	private Image image, character, background, heliboy, imboss1;
	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	public static int score = 0;
	private Font font = new Font(null, Font.BOLD, 30);

	@Override
	public void init() {

		setSize(480, 800);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Kill the D");

		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// Image Setups
		background = getImage(base, "data/background.png");
		character = getImage(base, "data/character.png");
		heliboy = getImage(base, "data/heliboy.png");
		imboss1 = getImage(base, "data/imboss.png");
	}

	@Override
	public void start() {
		float randomY1 = (float) Math.random() * 700;
		float randomX1 = 50 + (float) Math.random() * 350;
		float randomY2 = (float) Math.random() * 700;
		float randomX2 = 50 + (float) Math.random() * 350;
		float randomY3 = (float) Math.random() * 700;
		float randomX3 = 50 + (float) Math.random() * 350;
		float randomY4 = (float) Math.random() * 700;
		float randomX4 = 50 + (float) Math.random() * 350;

		bg1 = new Background(0, -1080);
		bg2 = new Background(0, -3100);
		boss1 = new Boss1(225, -8000);
		thed = new TheD();
		hb = new Heliboy((int) randomX1, -(int) randomY1);
		hb2 = new Heliboy((int) randomX2, -(int) randomY2);
		hb3 = new Heliboy((int) randomX3, -(int) randomY3);
		hb4 = new Heliboy((int) randomX4, -(int) randomY4);

		/*
		 * for (int i = 0; i < 10; i++){ Heliboy h = new
		 * Heliboy(RandomNumberGenerator.getRandIntBetween(0, 400),
		 * RandomNumberGenerator.getRandInt(50), 100, 150); heliboyList.add(h);
		 * h.update(); }
		 */
		Thread thread = new Thread(this);
		thread.start();

	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void run() {
		int t = 0;
		int b = 0;

		if (state == GameState.Running) {

			while (true) {
				float randomX1 = 50 + (float) Math.random() * 350;

				float randomX2 = 50 + (float) Math.random() * 350;

				float randomX3 = 50 + (float) Math.random() * 350;

				float randomX4 = 50 + (float) Math.random() * 350;
				thed.update();
				hb.update();
				hb2.update();
				hb3.update();
				hb4.update();
				boss1.update();

				if (thed.getCenterY() - boss1.getCenterY() > 1000
						|| boss1.getCenterX() == -100) {
					if (hb.getCenterX() == -100 || hb.getCenterY() > 800) {
						hb.setCenterX((int) randomX1);
						hb.setCenterY(thed.getCenterY() - 800);
						hb.health = 4;

					}
					if (hb2.getCenterX() == -100 || hb2.getCenterY() > 800) {
						hb2.setCenterX((int) randomX2);
						hb2.setCenterY(thed.getCenterY() - 800);
						hb2.health = 4;
					}
					if (hb3.getCenterX() == -100 || hb3.getCenterY() > 800) {
						hb3.setCenterX((int) randomX3);
						hb3.setCenterY(thed.getCenterY() - 800);
						hb3.health = 3;
					}
					if (hb4.getCenterX() == -100 || hb4.getCenterY() > 800) {
						hb4.setCenterX((int) randomX4);
						hb4.setCenterY(thed.getCenterY() - 800);
						hb4.health = 4;
					}
				}
				bg1.update();
				bg2.update();

				t = t + 1;
				
				System.out.println(b);
				if (t == 10) {
					thed.shoot();
					t = 0;
				}
				
				if (boss1.getCenterY() > 0) {
					b = b + 1;
					if (b == 50) {
						boss1.shoot();
						b = 0;
					}
				}

				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (thed.getCenterX() > 500) {
					state = GameState.Dead;
				}

				ArrayList projectiles = thed.getProjectiles();
				for (int i = 0; i < projectiles.size(); i++) {
					Projectile p = (Projectile) projectiles.get(i);
					if (p.isVisible() == true) {
						p.update();
					} else {
						projectiles.remove(i);
					}
				}
				ArrayList badprojectiles = boss1.getBadProjectiles();
				for (int i = 0; i < badprojectiles.size(); i++) {
					BadProjectile p = (BadProjectile) badprojectiles.get(i);
					if (p.isVisible() == true) {
						p.update();
					} else {
						badprojectiles.remove(i);
					}
				}

			}

		}
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {
		if (state == GameState.Running) {
			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);

			ArrayList projectiles = thed.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				g.setColor(Color.YELLOW);
				g.fillRect(p.getX(), p.getY(), 5, 5);
			}
			ArrayList badprojectiles = boss1.getBadProjectiles();
			for (int i = 0; i < badprojectiles.size(); i++) {
				BadProjectile p = (BadProjectile) badprojectiles.get(i);
				g.setColor(Color.red);
				g.fillRect(p.getX(), p.getY(), 5, 5);
			}

			g.drawImage(heliboy, hb.getCenterX() - 48, hb.getCenterY() - 48,
					this);
			g.drawImage(heliboy, hb2.getCenterX() - 48, hb2.getCenterY() - 48,
					this);
			g.drawImage(heliboy, hb3.getCenterX() - 48, hb3.getCenterY() - 48,
					this);
			g.drawImage(heliboy, hb4.getCenterX() - 48, hb4.getCenterY() - 48,
					this);
			g.drawImage(imboss1, boss1.getCenterX() - 48,
					boss1.getCenterY() - 48, this);

			// g.drawRect((int)thed.rect.getX(), (int)thed.rect.getY(),
			// (int)thed.rect.getWidth(), (int)thed.rect.getHeight());
			// g.drawRect((int)thed.rect2.getX(), (int)thed.rect2.getY(),
			// (int)thed.rect2.getWidth(), (int)thed.rect2.getHeight());
			g.drawImage(character, thed.getCenterX() - 50,
					thed.getCenterY() - 75, this);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 400, 50);
		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 480, 800);
			g.setColor(Color.WHITE);
			g.drawString("They killed the D", 120, 360);
			g.drawString("You are a loser!", 120, 480);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			thed.moveUp();
			thed.setMovingUp(true);
			break;

		case KeyEvent.VK_DOWN:
			thed.moveDown();
			thed.setMovingDown(true);
			break;

		case KeyEvent.VK_LEFT:
			thed.moveLeft();
			thed.setMovingLeft(true);
			break;

		case KeyEvent.VK_RIGHT:
			thed.moveRight();
			thed.setMovingRight(true);
			break;

		case KeyEvent.VK_SPACE:
			System.out.println(" Stop Jump or I don't know what it will do");
			break;

		case KeyEvent.VK_CONTROL:

			thed.shoot();
			thed.setReadyToFire(false);

			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			thed.stopUp();
			break;

		case KeyEvent.VK_DOWN:
			thed.stopDown();
			break;

		case KeyEvent.VK_LEFT:
			thed.stopLeft();
			break;

		case KeyEvent.VK_RIGHT:
			thed.stopRight();
			break;

		case KeyEvent.VK_SPACE:
			break;

		case KeyEvent.VK_CONTROL:
			thed.setReadyToFire(true);
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}

	public static TheD getTheD() {
		return thed;
	}

	public static Boss1 getBoss1() {
		return boss1;
	}

	public static void setBoss1(Boss1 boss1) {
		StartingClass.boss1 = boss1;
	}
}
