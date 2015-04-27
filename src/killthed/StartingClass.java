package killthed;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener{ 
	
	private TheD thed;
	private Image image, character, background;
	private Graphics second;
	private URL base;
	private static Background bg1, bg2;

	@Override
	public void init() {
		
		setSize(800, 480);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Kill the D Alpha");
        try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		// Image Setups
        background = getImage(base, "data/background.png");
		character = getImage(base, "data/character.png");
		
        
	}
	@Override
	public void start() {
	    bg1 = new Background(0, 0);
	    bg2 = new Background(2160, 0);
		thed = new TheD();
		
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
		while (true) {
			thed.update();
			bg1.update();
			bg2.update();
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
			
	}
	
	@Override
	public void update(Graphics g){
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
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		g.drawImage(character, thed.getCenterX() - 61, thed.getCenterY() - 63, this);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			   System.out.println("Stop Move up");
		      break;

		   case KeyEvent.VK_DOWN:
			   System.out.println("Stop Move Down");
		      break;

		   case KeyEvent.VK_LEFT:
			   thed.moveLeft();
	           thed.setMovingLeft(true);;
		      break;

		   case KeyEvent.VK_RIGHT:
			   thed.moveRight();
	           thed.setMovingRight(true);
		      break;
		      
		   case KeyEvent.VK_SPACE:
			   System.out.println(" Stop Jump or I don't know what it will do");
		      break;
		   }
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		   switch (e.getKeyCode()) {
		   case KeyEvent.VK_UP:
	            System.out.println("Stop moving up");
	            break;

	        case KeyEvent.VK_DOWN:
	            //currentSprite = character;
	            thed.setDucked(false);
	            break;

	        case KeyEvent.VK_LEFT:
	            thed.stopLeft();
	            break;

	        case KeyEvent.VK_RIGHT:
	            thed.stopRight();
	            break;

	        case KeyEvent.VK_SPACE:
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
}
