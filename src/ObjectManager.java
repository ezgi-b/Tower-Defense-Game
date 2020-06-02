import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener{
	Tower rocket;
	int score;
	public static final Color STAR = new Color(210,250,230);
	Font font = new Font("Arial", Font.PLAIN,48);
	ObjectManager(Tower s){
		rocket = s;
		score = 0;
	}
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
ArrayList<Enemy> aliens = new ArrayList<Enemy>();
	
	Random r = new Random();

	void addAlien() {
		aliens.add(new Enemy(r.nextInt(TowerDefense.WIDTH),0,50,50,10));
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
			if(p.y>TowerDefense.HEIGHT) {
				p.isActive=false;
			}
		}
		checkCollision();
		purgeObjects();
	}
	
	void draw(Graphics g) {
		g.setColor(STAR);
		g.setFont(font);
		g.drawString("Score: " + getScore(), 27, 100);
		rocket.draw(g);
		for(Enemy a : aliens) {
			a.draw(g);
		}
		for(Projectile p: projectiles) {
			p.draw(g);
		}
	}
	
	void purgeObjects(){
		for(int i = 0; i<aliens.size();i++) {
			if(aliens.get(i).isActive==false) {
				aliens.remove(i);
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
					a.isActive = false;
					p.isActive = false;
					score++;
				}
			}
		}
		for(Enemy a: aliens) {
				if(a.collisionBox.intersects(rocket.collisionBox)) {
					a.isActive = false;
					rocket.isActive = false;
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
	}
}
