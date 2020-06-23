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
		// TODO Auto-generated constructor stub
	}
	void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.drawString("" + hp, x, y-20);
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
		g.setColor(Color.GREEN);
		g.fillRect(x, y-15, hp*5, 10);
		g.setColor(Color.RED);
		g.fillRect(x+(hp*5), y-15, 50-(hp*5), 10);

	}
	
	void update(){
		
		if(y<300) {
			y+=speed;
		}else if(y>300 && x<300) {
			x+=speed;
		}else{
			y+=speed;
		}
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
