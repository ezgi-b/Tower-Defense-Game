import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Enemy extends GameObject{
	
	Enemy(int x, int y, int width, int height, int hp) {
		super(x, y, width, height, hp);
		speed = 1;
		// TODO Auto-generated constructor stub
	}
	int type = 0;
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
