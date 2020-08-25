import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class ObjectManager extends JFrame implements ActionListener{
	ArrayList<Tower> towers = new ArrayList<Tower>();
	ArrayList<Timer> shootTimers = new ArrayList<Timer>();
	ArrayList<Timer> animTimers = new ArrayList<Timer>();
	int score;
	int hpOfEnemies = 2;
	public static final Color STAR = new Color(210,250,230);
	Font font = new Font("Arial", Font.PLAIN,48);
	Timer enemyHP = new Timer(10000, this);
	Timer faster = new Timer(80300, this);
	Timer alienSpawn;
	Rectangle collisionBox0 = new Rectangle(100, 0, 50, 350);
	Rectangle collisionBox1 = new Rectangle(100, 300, 250, 50);
	Rectangle collisionBox2 = new Rectangle(300, 300, 50, 500);
	
	
	
	Treasure_Chest tc = new Treasure_Chest(275, 700, 100, 100, 20);
	ObjectManager(){
		score = 50;
		faster.start();
	}
	void addTower(int x, int y, String s) {
		if(score>=20) {
			boolean draw = true;
			Tower t = new Tower(x, y, 50, 50, 0, s);
			Timer shoot = new Timer(4053, this);
			shoot.start();
			Timer anim = new Timer(250, this);
			t.isActive=false;
			for(Tower w: towers) {
				if(t.collisionBox.intersects(w.collisionBox)) {
					draw = false;
				}
			}
			if(t.collisionBox.intersects(collisionBox0)||t.collisionBox.intersects(collisionBox1)||t.collisionBox.intersects(collisionBox2)) {
				draw = false;
			}
			if(draw==true) {
				towers.add(t);
				shootTimers.add(shoot);
				animTimers.add(anim);
				score-=20;
			}else {
				JOptionPane.showMessageDialog(null, "You cannot place a tower there!");
			}
		}
	}
	
	void startAliens() {
		alienSpawn = new Timer(4000, this);
	    alienSpawn.start();
	    enemyHP.start();
	}
	void stopAliens() {
	    alienSpawn.stop();
	}
	
	void upgradeTower(int i) {
		int ii = i-1;
		if(score>=(40*(towers.get(ii).upgradeNumber+1))) {
			System.out.println(towers.get(ii).upgradeNumber);
			if(towers.get(ii).upgradeNumber<2) {
				score-=(40*(towers.get(ii).upgradeNumber+1));
				towers.get(ii).upgradeNumber++;
				shootTimers.get(ii).stop();
				shootTimers.get(ii).setDelay((int) (4053/(towers.get(ii).upgradeNumber + 0.3)));
				shootTimers.get(ii).start();
			}
		}
	}
	
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	
	void addProjectile(Projectile p) {
		projectiles.add(p);
	}
	
ArrayList<Enemy> aliens = new ArrayList<Enemy>();
	
	Random r = new Random();

	void addAlien() {
		
		Enemy e = new Enemy(100,-50,50,50,hpOfEnemies);
		aliens.add(e);
	}
	
	void update() {
		for(Enemy a : aliens) {
			a.update();
			if(a.y>TowerDefense.HEIGHT) {
				a.isActive=false;
			}
		}
		for(Projectile p: projectiles) {
			p.update(aliens);
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
		tc.draw(g);
	}
	
	void purgeObjects(){
		for(int i = 0; i<aliens.size();i++) {
			if(aliens.get(i).hp<=0) {
				aliens.remove(i);
				score+=4;
				//playSound("DeathSound.wav");
			}
		}
		for(int i = 0; i<projectiles.size();i++) {
			if(projectiles.get(i).isActive==false) {
				projectiles.remove(i);
			}
		}
		for(int i = 0; i<aliens.size();i++) {
			if(aliens.get(i).isActive==false) {
				aliens.remove(i);
			}
		}
	}
	
	private void playSound(String fileName) {
		AudioClip sound = JApplet.newAudioClip(getClass().getResource(fileName));
		sound.play();
		System.out.println("ll");
	}
	
	
	
	
	void checkCollision() {
		for(Enemy a: aliens) {
			for(Projectile p: projectiles) {
				if(a.collisionBox.intersects(p.collisionBox)) {
					if(p.type.equals("shooty")) {
						a.hp-=(2*(p.upgrade+1));
						p.isActive = false;
						a.speed = 1;
					}else if(p.type.equals("freezy")) {
						a.slowDown = 1;
						p.isActive = false;
					}
				}
			}
		}
		for(Enemy a: aliens) {
			if(a.collisionBox.intersects(tc.collisionBox)) {
				tc.hp--;
				a.isActive=false;
			}
		}
	}
	
	int getScore() {
		return score;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i<towers.size(); i++) {
			if(e.getSource().equals(shootTimers.get(i))) {
		
				Projectile p = new Projectile(towers.get(i).x+15, towers.get(i).y+15, 20, 20, 0, towers.get(i).type);
				p.upgrade=towers.get(i).upgradeNumber;
				addProjectile(p);
				towers.get(i).shootAnim=true;
				animTimers.get(i).start();
				break;
			}
		}
		for(int i = 0; i<towers.size(); i++) {
			if(e.getSource().equals(animTimers.get(i))){
				towers.get(i).shootAnim=false;
				animTimers.get(i).stop();
				break;
			}
		}
		if(e.getSource().equals(enemyHP)) {
			if(hpOfEnemies==2) {
				hpOfEnemies=4;
				enemyHP = new Timer(20000, this);
				enemyHP.start();
			}else if(hpOfEnemies==4) {
				hpOfEnemies=6;
				enemyHP = new Timer(30000, this);
				enemyHP.start();
			}else if(hpOfEnemies==6) {
				hpOfEnemies=8;
				enemyHP = new Timer(40000, this);
				enemyHP.start();
			}else if(hpOfEnemies==8) {
				hpOfEnemies=10;
			}
		}
		if(e.getSource().equals(faster)){
			faster.stop();
			alienSpawn = new Timer(2027, this);
			alienSpawn.start();
			System.out.println("faster");
		}else if(e.getSource().equals(alienSpawn)){
			
			addAlien();
		}
	}
}
