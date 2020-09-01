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
	boolean frozen = false;
	Font font = new Font("Arial", Font.BOLD,28);
	void draw(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("" + hp, x+10, y-20);
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
		g.setColor(Color.GREEN);
		g.fillRect(x, y-15, hp*5, 10);
		g.setColor(Color.RED);
		g.fillRect(x+(hp*5), y-15, 50-(hp*5), 10);
		if(frozen==true) {
			g.setColor(Color.blue);
			g.fillRect(x, y, width, height);
			g.setColor(Color.cyan);
			g.drawLine(x, y, x+20, y+30);
			g.drawLine(x+10, y+15, x, y+30);
			g.drawLine(x+20, y+30, x+50, y+50);
			g.drawLine(x+29, y+36, x+35, y+50);
		}

	}
	
	void update(){
		
		if(y<300) {
			if(slowDown%slowDownNum==0) {
				y+=speed;
				slowDown=0;
				frozen = false;
			}else {
				slowDown++;
				frozen = true;
			}
		}else if(y>300 && x<300) {
			if(slowDown%slowDownNum==0) {
				x+=speed;
				slowDown=0;
				frozen = false;
			}else {
				slowDown++;
				frozen = true;
			}
		}else{
			if(slowDown%slowDownNum==0) {
				y+=speed;
				slowDown=0;
				frozen = false;
			}else {
				slowDown++;
				frozen = true;
			}
		}
		super.update();
	}

}
