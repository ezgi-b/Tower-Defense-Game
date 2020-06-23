import java.awt.Color;
import java.awt.Graphics;

public class Treasure_Chest extends GameObject{

	Treasure_Chest(int x, int y, int width, int height, int hp) {
		super(x, y, width, height, hp);
		// TODO Auto-generated constructor stub
	}
	Color gold = new Color(230, 200, 100);
	Color chest = new Color(60, 20, 25);
	void draw(Graphics g){
		g.setColor(chest);
		g.fillRect(x, y, width, height);
		
		g.setColor(gold);
		g.fillRect(x, y+25, 30, 10);
		g.fillRect(x+70, y+25, 30, 10);
		g.fillRect(x+30, y+15, 40, 30);
		
		g.setColor(gold);
		g.fillRect(x, y-15, hp*5, 10);
		g.setColor(Color.RED);
		g.fillRect(x+(hp*5), y-15, 50-(hp*5), 10);
	}
}
