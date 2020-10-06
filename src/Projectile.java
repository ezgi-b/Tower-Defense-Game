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
	int circle = 0;
	//int chaseNumber = 0;
	int cost;
	String type;
	Projectile(int x, int y, int width, int height, int hp, String s) {
		super(x, y, width, height, hp);
		// TODO Auto-generated constructor stub
		speed=10;
		if (needImage) {
		    loadImage ("bullet.png");
		}
		type = s;
	}
	void draw(Graphics g){
		if (gotImage) {
			g.drawImage(image, x, y, width, height, null);
		} else {
			if(type.equals("firey")) {
				g.setColor(Color.orange);
			}else {
				if(upgrade==0) {
					g.setColor(Color.PINK);
				}else if(upgrade==1) {
					g.setColor(Color.red);
				}else if(upgrade==2) {
					g.setColor(Color.BLACK);
				}
			}
			g.fillOval(x, y, width, height);
		}
	}
	
	void update(ArrayList<Enemy> enemies){
		
		if(enemies.isEmpty()) {
			if(circle%8==0) {
				x+=4;
			}else if(circle%8==1) {
				x+=3;
				y+=3;
			}else if(circle%8==2) {
				y+=4;
			}else if(circle%8==3) {
				y+=3;
				x-=3;
			}else if(circle%8==4) {
				x-=4;
			}else if(circle%8==5) {
				y-=3;
				x-=3;
			}else if(circle%8==6) {
				y-=4;
			}else if(circle%8==7) {
				y-=3;
				x+=3;
			}
			circle++;
			
			
			
		}else{
			float xgo;
			float ygo;
			float xplus = 0;
			float yplus = 0;
			xgo = enemies.get((type.equals("shooty"))? 0: enemies.size()-1).x-x;
			ygo = enemies.get((type.equals("shooty"))? 0: enemies.size()-1).y-y;
			
			float slope = Math.abs(ygo/xgo);
			if(slope>1000) {
				yplus = (float) 2.44948974278;
				System.out.println("ddd");
			}else {
				while(6>=((xplus*xplus)+(yplus)*(yplus))) {
					xplus+=0.005;
					yplus = xplus*slope;
				}
			}
			
			if(xgo<0) {
				x-=xplus;
			}
			if(xgo>0) {
				x+=xplus;
			}
			if(ygo<0) {
				y-=yplus;
			}
			if(ygo>0) {
				y+=yplus;
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
