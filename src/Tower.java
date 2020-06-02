import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Tower extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;
	Tower(int x, int y, int width, int height, int hp){
		super(x,y,width,height, hp);
		speed = 15;
		if (needImage) {
			loadImage("rocket.png");
		}
	}
	void draw(Graphics g) {
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.CYAN);
			g.fillRect(x, y, width, height);
		}
	}	
	public void right() {
		x+=speed;
		super.update();
	}
	public void left() {
		x-=speed;
		super.update();
	}
	public void down() {
		y+=speed;
		super.update();
	}
	public void up() {
		y-=speed;
		super.update();
	}
	
	void loadImage(String imageFile) {
	    if (needImage) {
	        try {
	            image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		    gotImage = true;
	        } catch (Exception e) {
	            
	        }
	        needImage = false;
	    }
	}
	
	public Projectile getProjectile() {
        return new Projectile(x+width/2, y, 10, 10, 0);
	} 
}
