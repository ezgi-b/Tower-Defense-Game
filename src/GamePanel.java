import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener{
	@Override
	
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}else if(currentState == GAME){
		    drawGameState(g);
		}else if(currentState == END){
		    drawEndState(g);
		}
	}
	
	public static Image image;
	
	Timer frameDraw;
	Timer alienSpawn;
	GamePanel(){
		frameDraw = new Timer(1000/60,this);
		frameDraw.start();
	}
	
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    public int currentState = MENU;
    public static final Color NIGHT_SKY = new Color(30,10,90);
    public static final Color STAR = new Color(210,250,230);
    Font titleFont = new Font("Arial", Font.PLAIN,48);
    Font enter_spaceFont = new Font("Arial", Font.PLAIN,24);
    Tower r = new Tower(225,700,50,50, 0);
    ObjectManager oj = new ObjectManager(r);
    
    
	
    void updateMenuState() {
   
	}
	
	void updateGameState() {
		oj.update();
		if(r.isActive==false) {
			currentState = END;
		}
	}
	
	void updateEndState() {
		
	}
	
	void drawMenuState(Graphics g) {  
		g.setColor(NIGHT_SKY);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
		g.setFont(titleFont);
		g.setColor(STAR);
		g.drawString("TOWER DEFENSE", 27, 100);
		g.setFont(enter_spaceFont);
		g.setColor(STAR);
		g.drawString("Press ENTER to start", 125, 350);
		g.setFont(enter_spaceFont);
		g.setColor(STAR);
		g.drawString("Press SPACE for instructions", 90, 550);
	}
	
	void drawGameState(Graphics g) {  
		loadImage("space.png");
		g.drawImage(image, 0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT, null);
		oj.draw(g);
	}
	
	void drawEndState(Graphics g)  {  
		g.setColor(Color.RED);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
		g.setFont(titleFont);
		g.setColor(STAR);
		g.drawString("GAME OVER", 100, 100);
		g.setFont(enter_spaceFont);
		g.setColor(STAR);
		g.drawString("You killed " + oj.getScore() + " enemies", 145, 350);
		g.setFont(enter_spaceFont);
		g.setColor(STAR);
		g.drawString("Press ENTER to restart", 120, 550);
	}
	
	void startGame() {
		alienSpawn = new Timer(1000, oj);
	    alienSpawn.start();
	}
	void stopGame() {
	    alienSpawn.stop();
	}
	
	void loadImage(String imageFile) {
		try {
			image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
		} catch (Exception e) {
	            
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(currentState == MENU){
		    updateMenuState();
		}else if(currentState == GAME){
		    updateGameState();
		}else if(currentState == END){
		    updateEndState();
		}
		
		
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode()==KeyEvent.VK_ENTER) {
		    if (currentState == END) {
		    	r = new Tower(225,700,50,50, 0);
		    	r.isActive=true;
			    oj = new ObjectManager(r);
		        currentState = MENU;
		    } else if (currentState == MENU) {
		        currentState = GAME;
		        startGame();
		    } else if (currentState == GAME) {
		        currentState = END;
		        stopGame();
		    }
		}
		if(currentState == GAME) {
			if (e.getKeyCode()==KeyEvent.VK_UP) {
				if(r.y>0) {
					r.up();
				}
			}
		
			if (e.getKeyCode()==KeyEvent.VK_DOWN) {
				if(r.y<725) {
					r.down();
				}
			}	
		
			if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
				if(r.x<450) {
					r.right();
				}
			}
		
			if (e.getKeyCode()==KeyEvent.VK_LEFT) {
				if(r.x>0) {
					r.left();
				}
			}
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				oj.addProjectile(r.getProjectile());
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
