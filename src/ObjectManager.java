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
	int aliensKilled = 0;
	int gameScore = 0;
	int hpOfEnemies = 2;
	int spawnTime = 4000;
	boolean fastest = false;
	public static final Color STAR = new Color(210,250,230);
	Font font = new Font("Arial", Font.PLAIN,48);
	Font better = new Font("Arial", Font.PLAIN,20);
	Timer enemyHP = new Timer(15000, this);
	Timer faster = new Timer(15000, this);
	Timer alienSpawn;
	Rectangle collisionBox0 = new Rectangle(100, 0, 50, 350);
	Rectangle collisionBox1 = new Rectangle(100, 300, 250, 50);
	Rectangle collisionBox2 = new Rectangle(300, 300, 50, 500);
	Rectangle collisionBoxTreasureChest = new Rectangle(275, 700, 100, 100);
	Rectangle collisionBoxMoney = new Rectangle(300, 70, 200, 40);
	
	
	Treasure_Chest tc = new Treasure_Chest(275, 700, 200, 100, 20);
	ObjectManager(){
		score = 20;
		faster.start();
	}
	void addTower(int x, int y, String s, int cost) {
		if(towers.size()>=10) {
			JOptionPane.showMessageDialog(null, "You cannot have more than 10 towers!");
		}else {
			Tower t = new Tower(x, y, 50, 50, 0, s, cost);
			if(score>=t.cost) {
				boolean draw = true;
				Timer shoot = new Timer(4000, this);
				shoot.start();
				Timer anim = new Timer(250, this);
				for(Tower w: towers) {
					if(t.collisionBox.intersects(w.collisionBox)) {
						draw = false;
					}
				}
				if(t.collisionBox.intersects(collisionBox0)||t.collisionBox.intersects(collisionBox1)||t.collisionBox.intersects(collisionBox2)||t.collisionBox.intersects(collisionBoxTreasureChest)||t.collisionBox.intersects(collisionBoxMoney)) {
					draw = false;
				}
				if(draw==true) {
					changeGameScore(-30);
					towers.add(t);
					shootTimers.add(shoot);
					animTimers.add(anim);
					score-=t.cost;
					System.out.println(t.cost);
				}else {
					JOptionPane.showMessageDialog(null, "You cannot place a tower there!");
				}
			}else {
				JOptionPane.showMessageDialog(null, "You do not have enough money to buy this tower! You need $" + t.cost + " to buy it.");
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
	
	
	void slowAliens() {
		//if(spawnTime<=1000) {
			//if(score>=15) {
				//spawnTime+=800;
				//alienSpawn = new Timer(spawnTime, this);
				//alienSpawn.restart();
				//System.out.println(spawnTime);
				//score-=15;
				//JOptionPane.showMessageDialog(null, "Enemies have been slowed down!");
			//}else {
				//JOptionPane.showMessageDialog(null, "You do not have enough money to slow down the enemies! You need $15 to do this.");
			//}
		//}else {
			//JOptionPane.showMessageDialog(null, "You cannot slow the enemies more than this!");
		//}
	}
	
	void upgradeTower(int i) {
		int ii = i-1;
		if(i<=towers.size()&&i>0) {
			if(towers.get(ii).type=="firey") {
				JOptionPane.showMessageDialog(null, "You cannot upgrade the firey tower at all.");
			}else {
				if(score>=(towers.get(ii).cost+20)) {
					System.out.println(towers.get(ii).upgradeNumber);
					if(towers.get(ii).upgradeNumber<2) {
						score-=(towers.get(ii).cost+20);
						towers.get(ii).upgradeNumber++;
						towers.get(ii).cost+=20;
					}else {
						JOptionPane.showMessageDialog(null, "You are at maximum upgrade limit.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "You do not have enough money to upgrade this tower! You must have at least $" + (towers.get(ii).cost+20) + ".");
				}
			}
		}else {
			JOptionPane.showMessageDialog(null, "None of your towers have that number!!");
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
		for(Tower t: towers) {
			t.draw(g);
			
		}
		for(int i=0; i<towers.size(); i++) {
			g.setColor(STAR);
			g.setFont(font);
			int num = i+1;
			g.setFont(better);
			if(num<10) {
				g.drawString(""+num, towers.get(i).x+20, towers.get(i).y+33);
			}else {
				g.drawString(""+num, towers.get(i).x+14, towers.get(i).y+33);
			}
		}
		g.setColor(STAR);
		g.setFont(font);
		g.drawString("$" + getScore(), 300, 100);
		
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
				if(fastest==true) {
					score+=1;
				}else {
					score+=5;
				}
				playSound("EnemyDeath.wav");
				changeGameScore(10);
				aliensKilled++;
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
						if(a.burnt==true) {
							a.hp-=(4*(p.upgrade+1));	
						}else {
							a.hp-=(2*(p.upgrade+1));
						}
						p.isActive = false;
						a.speed = 1;
					}else if(p.type.equals("firey")) {
						p.isActive = false;
						a.burnt = true;
					}
				}
			}
		}
		for(Enemy a: aliens) {
			if(a.collisionBox.intersects(tc.collisionBox)) {
				tc.hp--;
				a.isActive=false;
				playSound("AttackedChest.wav");
			}
		}
	}
	
	int getScore() {
		return score;
	}
	
	int getGameScore() {
		return gameScore;
	}
	
	void changeGameScore(int i) {
		gameScore+=i;
		System.out.println(gameScore);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i<towers.size(); i++) {
			if(e.getSource().equals(shootTimers.get(i))&&aliens.size()>0) {
				spawnTime-=22;
				if(aliens.size()>0) {
					Projectile p = new Projectile(towers.get(i).x+15, towers.get(i).y+15, 20, 20, 0, towers.get(i).type);
					p.upgrade=towers.get(i).upgradeNumber;
					addProjectile(p);
					towers.get(i).shootAnim=true;
					animTimers.get(i).start();
					break;
				}
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
				enemyHP = new Timer(30000, this);
				enemyHP.start();
			}else if(hpOfEnemies==4) {
				hpOfEnemies=6;
				enemyHP = new Timer(40000, this);
				enemyHP.start();
			}else if(hpOfEnemies==6) {
				hpOfEnemies=8;
				enemyHP = new Timer(50000, this);
				enemyHP.start();
			}else if(hpOfEnemies==8) {
				hpOfEnemies=10;
			}
		}
		if(e.getSource().equals(faster)){
			if(spawnTime>1100) {
				alienSpawn = new Timer(spawnTime, this);
			}else if(spawnTime==1100){
				fastest = true;
			}else {
				spawnTime = 1100;
				alienSpawn = new Timer(1100, this);
				fastest = true;
			}
			alienSpawn.start();
			System.out.println("faster");
			System.out.println(spawnTime);
		}else if(e.getSource().equals(alienSpawn)){
			
			addAlien();
		}
	}
}
