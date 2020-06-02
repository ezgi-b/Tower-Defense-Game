import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Enemy extends GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	
	Enemy(int x, int y, int width, int height, int hp) {
		super(x, y, width, height, hp);
		speed = 1;
		if (needImage) {
		    loadImage ("alien.png");
		}
		// TODO Auto-generated constructor stub
	}
	void draw(Graphics g){
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			g.drawString("" + hp, x, y);
			g.setColor(Color.MAGENTA);
		    g.fillRect(x, y, width, height);
		}
	}
	
	void update(){
		y+=speed;
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
