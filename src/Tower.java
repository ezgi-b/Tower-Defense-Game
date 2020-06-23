import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Tower extends GameObject{
	Tower(int x, int y, int width, int height, int hp){
		super(x,y,width,height, hp);
		speed = 15;
	}
	boolean shootAnim;
	int upgradeNumber=0;
	int direction = 1;
	void draw(Graphics g) {
			Color z = new Color(140, 140,255);
			Color r = new Color(170,10,190);
			Color p = new Color(100,30,150);
				g.setColor(z);
				g.fillRect(x, y, width, height);
				g.setColor(r);
				for(int i = 0; i<upgradeNumber; i++) {
					if(direction==1) {
						g.fillRect(x, y+((i+1)*15), 50, 10);
					}else if(direction==2) {
						g.fillRect(x+40-((i+1)*15), y, 10, 50);
					}else if(direction==3) {
						g.fillRect(x, y+40-((i+1)*15), 50, 10);
					}else if(direction==4) {
						g.fillRect(x+((i+1)*15), y, 10, 50);
					}
				}
				g.setColor(p);
				if(direction==1) {
					if(shootAnim==true) {
						g.fillRect(x+12, y+25, 26, 25);
					}else {
						g.fillRect(x+10, y+25, 30, 25);
					}
				}else if(direction==2) {
					if(shootAnim==true) {
						g.fillRect(x, y+12, 25, 26);
					}else {
						g.fillRect(x, y+10, 25, 30);
					}
				}else if(direction==3) {
					if(shootAnim==true) {
						g.fillRect(x+12, y, 26, 25);
					}else {
						g.fillRect(x+10, y, 30, 25);
					}
				}else if(direction==4) {
					if(shootAnim==true) {
						g.fillRect(x+25, y+12, 25, 26);
					}else {
						g.fillRect(x+25, y+10, 25, 30);
					}
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
        return new Projectile(x+width/2, y, 10, 10, 0);
	} 
}
