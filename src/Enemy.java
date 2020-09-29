import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Enemy extends GameObject{
	
	Enemy(int x, int y, int width, int height, int hp) {
		super(x, y, width, height, hp);
		speed = 1;
		// TODO Auto-generated constructor stub
	}
	boolean chased = false;
	int type = 0;
	int slowDown = 0;
	int slowDownNum = 30;
	boolean burnt = false;
	Font font = new Font("Arial", Font.BOLD,28);
	void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("" + hp, x+10, y-20);
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.fillOval(x+width/4, y+width/4, width/2, height/2);
		g.setColor(Color.black);
		g.fillOval(x+2*width/5, y+2*width/5, width/5, height/5);
		
		g.setColor(Color.GREEN);
		g.fillRect(x, y-15, hp*5, 10);
		g.setColor(Color.RED);
		g.fillRect(x+(hp*5), y-15, 50-(hp*5), 10);
		if(burnt==true) {
			
			if((x+y)%17<8) {
				g.setColor(Color.orange);
				g.fillRect(x+24, y-5, 10, 10);
			}else {
				g.setColor(Color.orange);
				g.fillRect(x+16, y-5, 10, 10);
			}
			if((x+y)%27<14) {
				g.setColor(Color.orange);
				g.fillRect(x+4, y+20, 40, 20);
			}else {
				g.setColor(Color.orange);
				g.fillRect(x+6, y+20, 40, 20);
			}
			if((x+y)%20<10) {
				g.setColor(Color.orange);
				g.fillRect(x+13, y+5, 30, 15);
				g.setColor(Color.red);
				g.fillRect(x+25, y+10, 10, 15);
			}else {
				g.setColor(Color.orange);
				g.fillRect(x+7, y+5, 30, 15);
				g.setColor(Color.red);
				g.fillRect(x+15, y+10, 10, 15);
			}
		}

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

}
