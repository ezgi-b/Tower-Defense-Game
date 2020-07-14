import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Projectile extends  GameObject{
	public static BufferedImage image;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	int upgrade;
	int direction = 2;
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
	
	void update(ArrayList<Enemy> enemies){
		//if(direction==1) {
			//y-=speed;
			//super.update();
		//}else if(direction==2) {
			//x+=speed;
			//super.update();
		//}else if(direction==3) {
			//y+=speed;
			//super.update();
		//}else if(direction==4) {
			//x-=speed;
			//super.update();
		//}
		if(enemies.isEmpty()) {
			
		}else{
			int xgo;
			int ygo;
			xgo = enemies.get(0).x-x;
			ygo = enemies.get(0).y-y;
			if(xgo<0) {
				x-=2;
			}
			if(xgo>0) {
				x+=2;
			}
			if(ygo<0) {
				y-=2;
			}
			if(ygo>0) {
				y+=2;
			}
			super.update();
		}
		
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
