import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Projectile extends  GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	Projectile(int x, int y, int width, int height, int hp) {
		super(x, y, width, height, hp);
		// TODO Auto-generated constructor stub
		speed=10;
		if (needImage) {
		    loadImage ("bullet.png");
		}
	}
	void draw(Graphics g){
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.setColor(Color.PINK);
			g.fillRect(x, y, width, height);
		}
	}
	
	void update(){
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
}
