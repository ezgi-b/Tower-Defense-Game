import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Tower extends GameObject{
	Tower(int x, int y, int width, int height, int hp){
		super(x,y,width,height, hp);
		speed = 15;
	}
	int upgradeNumber=0;
	int direction = 1;
	void draw(Graphics g) {
		if(isActive==false) {
			Color z = new Color(180-(upgradeNumber*40), 180-(upgradeNumber*40), 255);
				g.setColor(z);
				g.fillRect(x, y, width, height);
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
