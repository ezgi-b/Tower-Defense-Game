import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Tower extends GameObject{
	Tower(int x, int y, int width, int height, int hp){
		super(x,y,width,height, hp);
		speed = 15;
	}
	
	
	void draw(Graphics g) {
		if(isActive==false) {
			g.setColor(Color.CYAN);
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
