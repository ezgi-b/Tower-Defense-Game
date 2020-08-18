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
		}else if(currentState == INSTRUCTIONS){
		    drawInstructionsState(g);
		}
	}
	
	public static Image image;
	Timer frameDraw;
	//Timer alienSpawn;
	GamePanel(){
		frameDraw = new Timer(1000/70,this);
		frameDraw.start();
	}
	
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    final int INSTRUCTIONS = 13;
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
		g.drawString("TOWER DEFENSE", 33, 100);
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
	
	void drawInstructionsState(Graphics g) {
		g.setColor(GAMEBACKGROUND);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
		g.setColor(STAR);
		g.setFont(titleFont);
		g.drawString("Instructions", 115, 80);
		g.setFont(enter_spaceFont);
		g.drawString("Your job is to defend the treasure chest from", 10, 140);
		g.drawString("the enemies. Click a spot on the screen to", 10, 165);
		g.drawString("spawn a tower. There are two types of towers:", 10, 190);
		g.drawString("freezy and shooty ones. Type in the type of", 10, 215);
		g.drawString("tower you wish to buy to buy it. If you want to", 10, 240);
		g.drawString("upgrade a tower, press the 'u' button then", 10, 265);
		g.drawString("specify which number tower you wish to", 10, 290);
		g.drawString("upgrade.", 10, 315);
		
		g.drawString("Press the 'r' key to return to the menu.", 10, 380);
	}
	
	void startGame() {
		oj.startAliens();
	}
	void stopGame() {
	    oj.stopAliens();
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
		if(currentState == MENU) {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				currentState = INSTRUCTIONS;
			}
		}
		
		if(currentState == INSTRUCTIONS) {
			if (e.getKeyCode()==KeyEvent.VK_R) {
				currentState = MENU;
			}
		}
		
		if (e.getKeyCode()==KeyEvent.VK_U) {
			System.out.println("ooo");
			int upgradeNum = Integer.parseInt(JOptionPane.showInputDialog("Which number Tower do you want to updgrade"));
			oj.upgradeTower(upgradeNum);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String input = JOptionPane.showInputDialog("What type of tower do you want to buy? (freezy or shooty)");
		if(input.equals("freezy")||input.equals("shooty")){
			oj.addTower(e.getX()-25, e.getY()-50, input);
		}else {
			JOptionPane.showMessageDialog(null, "That is not a valid tower name!!! Valid names are 'freezy' or 'shooty'.");
		}
		
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
