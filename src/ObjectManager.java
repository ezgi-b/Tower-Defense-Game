import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;

public class ObjectManager extends JFrame implements ActionListener{
	ArrayList<Tower> towers = new ArrayList<Tower>();
	int score;
	public static final Color STAR = new Color(210,250,230);
	Font font = new Font("Arial", Font.PLAIN,48);
	ObjectManager(){
		score = 100;
	}
	void addTower(int x, int y, Projectile p) {
		if(score>=20) {
			boolean draw = true;
			Tower t = new Tower(x, y, 50, 50, 0);
			t.isActive=false;
			for(Tower w: towers) {
				if(t.collisionBox.intersects(w.collisionBox)) {
					draw = false;
				}
			}
			if(draw==true) {
				towers.add(t);
				projectiles.add(p);
				score-=20;
			}
		}
	}
	
	void upgradeTower(int i) {
		if(score>=40) {
			towers.get(i-1).upgradeNumber++;
			score-=40;
		}
	}
	
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
ArrayList<Enemy> aliens = new ArrayList<Enemy>();
	
	Random r = new Random();

	void addAlien() {
		aliens.add(new Enemy(100,30,50,50,10));
		
	}
	
	void update() {
		for(Enemy a : aliens) {
			a.update();
			if(a.y>TowerDefense.HEIGHT) {
				a.isActive=false;
			}
		}
		for(Projectile p: projectiles) {
			p.update();
			if(p.y>TowerDefense.HEIGHT || p.x>TowerDefense.WIDTH) {
				p.isActive=false;
			}
		}
		checkCollision();
		purgeObjects();
	}
	
	void draw(Graphics g) {
		for(int i=0; i<towers.size(); i++) {
			g.setColor(STAR);
			g.setFont(font);
			int num = i+1;
			g.drawString(""+num, towers.get(i).x, towers.get(i).y-10);
		}
		g.setColor(STAR);
		g.setFont(font);
		g.drawString("$" + getScore(), 200, 100);
		for(Tower t: towers) {
				t.draw(g);
				
		}
		for(Enemy a : aliens) {
			a.draw(g);
		}
		for(Projectile p: projectiles) {
			p.draw(g);
		}
	}
	
	void purgeObjects(){
		for(int i = 0; i<aliens.size();i++) {
			if(aliens.get(i).hp==0) {
				aliens.remove(i);
				score+=8;
			}
		}
		for(int i = 0; i<projectiles.size();i++) {
			if(projectiles.get(i).isActive==false) {
				projectiles.remove(i);
			}
		}
	}
	void checkCollision() {
		for(Enemy a: aliens) {
			for(Projectile p: projectiles) {
				if(a.collisionBox.intersects(p.collisionBox)) {
					a.hp-=2;
					p.isActive = false;
				}
			}
		}
	}
	
	int getScore() {
		return score;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addAlien();
		for(Tower t: towers) {
			Projectile p = new Projectile(t.x, t.y, 30, 30, 0);
			addProjectile(p);
			if(t.upgradeNumber==1) {
				Projectile z = new Projectile(t.x-10, t.y, 30, 30, 0);
				addProjectile(z);
			}
		}
	}
}
