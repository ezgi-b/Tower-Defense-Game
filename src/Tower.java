import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Tower extends GameObject{
	int cost = 0;
	Tower(int x, int y, int width, int height, int hp, String type, int cost){
		super(x,y,width,height, hp);
		speed = 15;
		this.type = type;
		this.cost = cost;
	}
	
	String type;
	boolean shootAnim;
	int upgradeNumber=0;
	void draw(Graphics g) {
		Color z = new Color(140, 140, 255);
		Color r = new Color(170, 10, 190);
		Color p = new Color(100, 30, 150);
		
		Color e = new Color(255, 105, 38);
		Color a = new Color(170, 80, 190);
		Color w = new Color(240, 25, 83);
			if(type.equals("shooty")) {
				g.setColor(z);
			}else {
				g.setColor(e);
			}
			g.fillRect(x, y, width, height);
			
			g.setColor(r);
			
			for(int i = 0; i<upgradeNumber; i++) {
				g.fillRect(x, y+((i+1)*15), 50, 10);
			}
			if(type.equals("shooty")) {
				g.setColor(p);
			}else {
				g.setColor(w);
			}
				
			if(shootAnim==true) {
				g.fillRect(x+15, y+15, 20, 20);
			}else {
				g.fillRect(x+10, y+10, 30, 30);
			}
	}	
	public void right() {
		
	}
	public void left() {
		
	}
	public void down() {
		
	}
	public void up() {
		
	}
	
	
	public Projectile getProjectile() {
		if(type=="shooty") {
        return new Projectile(x+width/2, y, 10, 10, 0, type);
		}else {
			return new Projectile(x+width/2, y, 10, 10, 0, type);
		}
	} 
}
