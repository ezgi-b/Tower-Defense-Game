import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Treasure_Chest extends GameObject{

	Treasure_Chest(int x, int y, int width, int height, int hp) {
		super(x, y, width, height, hp);
		// TODO Auto-generated constructor stub
	}
	Color gold = new Color(230, 200, 100);
	Color metal = new Color(150, 170, 160);
	Color chest = new Color(60, 20, 25);
	Font aytchpee = new Font("Arial", Font.PLAIN,50);
	void draw(Graphics g){
		
		//jewels
		g.setColor(Color.RED);
		g.fillRect(x+30, y+19, 10, 15);
		g.fillRect(x+51, y+18, 11, 15);
		g.fillRect(x+79, y+20, 9, 15);
		
		
		//gold coins
		g.setColor(gold);
		g.fillOval(x+1, y+22, 15, 15);
		g.fillOval(x+16, y+19, 15, 15);
		g.fillOval(x+34, y+20, 15, 15);
		g.fillOval(x+50, y+22, 15, 15);
		g.fillOval(x+67, y+20, 15, 15);
		g.fillOval(x+84, y+23, 15, 15);
		
		//jewels
		g.setColor(Color.CYAN);
		g.fillRect(x+10, y+22, 12, 15);
		g.fillRect(x+40, y+25, 13, 15);
		g.fillRect(x+70, y+23, 11, 15);
		
		//necklace
		g.setColor(Color.GREEN);
		g.fillOval(x+41, y+25, 9, 9);
		g.fillOval(x+45, y+20, 9, 9);
		g.fillOval(x+53, y+18, 9, 9);
		g.fillOval(x+59, y+22, 9, 9);
		
		//chest wood
		g.setColor(chest);
		g.fillRoundRect(x, y-(40-(hp*2)), 100, 30, 20, 20);
		g.fillRect(x, y-(40-(hp*2))+10, 100, 20);
		g.fillRect(x, y+30, 100, 70);
		
		//chest metal
		g.setColor(metal);
		g.fillRect(x+30, y+15-(40-(hp*2)), 40, 15);
		g.fillRect(x+30, y+30, 40, 15);
		g.fillRect(x, y+25-(40-(hp*2)), 100, 5);
		g.fillRect(x, y+30, 100, 5);
		
		Color hpee = new Color(230, 200-(10*(20-hp)), 100-(5*(20-hp)));
		g.setColor(hpee);
		g.setFont(aytchpee);
		g.drawString("" + hp, x+120, y+50);
		
	}
}
