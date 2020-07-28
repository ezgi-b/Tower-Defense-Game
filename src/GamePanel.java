import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener, MouseListener{
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
		frameDraw = new Timer(1000/70,this);
		frameDraw.start();
	}
	
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    public int currentState = MENU;
    public static final Color NIGHT_SKY = new Color(30,10,90);
    public static final Color STAR = new Color(210,250,230);
    public static final Color GAMEBACKGROUND = new Color(100,100,30);
    public static final Color PATH = new Color(50,50,10);
    Font titleFont = new Font("Arial", Font.PLAIN,48);
    Font enter_spaceFont = new Font("Arial", Font.PLAIN,24);
    ArrayList<Tower> tower = new ArrayList<Tower>();
    ObjectManager oj = new ObjectManager();
    
    
	
    void updateMenuState() {
   
	}
	
	void updateGameState() {
		oj.update();
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
		g.setColor(GAMEBACKGROUND);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
		g.setColor(PATH);
		g.fillRect(100, 0, 50, 350);
		g.fillRect(100, 300, 250, 50);
		g.fillRect(300, 300, 50, 500);
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
		alienSpawn = new Timer(4000, oj);
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
			    oj = new ObjectManager();
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
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				
			}
		}
		
		if (e.getKeyCode()==KeyEvent.VK_U) {
			System.out.println("ooo");
			oj.upgradeTower(Integer.parseInt(JOptionPane.showInputDialog("Which number Tower do you want to updgrade")));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	
		oj.addTower(e.getX()-25, e.getY()-50/*, Integer.parseInt(JOptionPane.showInputDialog("What direction do you want the shooter to shoot?"))*/);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
