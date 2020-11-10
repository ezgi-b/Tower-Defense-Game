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
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
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
		}else if(currentState == INSTRUCTIONSONE){
		    drawInstructionsStateOne(g);
		}else if(currentState == INSTRUCTIONSTWO){
		    drawInstructionsStateTwo(g);
		}else if(currentState == INSTRUCTIONSTHREE){
		    drawInstructionsStateThree(g);
		}else if(currentState == INSTRUCTIONSFOUR){
		    drawInstructionsStateFour(g);
		}
	}
	
	public static Image image;
	Timer frameDraw;
	//Timer alienSpawn;
	GamePanel(){
		frameDraw = new Timer(1000/70,this);
		frameDraw.start();
	}
	
	int score = 0;
	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    final int INSTRUCTIONS = 13;
    final int INSTRUCTIONSONE = 20;
    final int INSTRUCTIONSTWO = 21;
    final int INSTRUCTIONSTHREE = 22;
    final int INSTRUCTIONSFOUR = 23;
    public int currentState = MENU;
    public static final Color NIGHT_SKY = new Color(30,10,90);
    public static final Color STAR = new Color(210,250,230);
    public static final Color GAMEBACKGROUND = new Color(100,100,30);
    public static final Color PATH = new Color(50,50,10);
    ArrayList<Integer> scores = new ArrayList<Integer>();
    
    Font titleFont = new Font("Arial", Font.PLAIN,48);
    Font enter_spaceFont = new Font("Arial", Font.PLAIN,24);
    ArrayList<Tower> tower = new ArrayList<Tower>();
    ObjectManager oj = new ObjectManager();
    
    
    void changeState(int s) {
    	currentState = s;
    }
    
    void updateMenuState() {
   
	}
	
	void updateGameState() {
		oj.update();
		if(oj.tc.hp<=0) {
			 changeState(END);
			 System.out.println("END");
		}
	}
	
	
	
	void updateEndState() {
		
	}
	
	void addScoreToList(int x) {
		scores.add(x);
		System.out.println(scores);
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
		g.drawString("Your score is " + oj.getGameScore(), 145, 400);
		score = oj.getGameScore();
		g.drawString("Enemies Killed: " + oj.aliensKilled, 145, 420);
		g.drawString("Towers: " + oj.towers.size(), 145, 440);
		g.setFont(enter_spaceFont);
		g.setColor(STAR);
		g.drawString("Press ENTER to restart", 120, 550);
		
		g.drawString("Scores", 210, 180);
		
		Collections.sort(scores, Collections.reverseOrder());
		for(int e = 0; e < scores.size(); e++) {
			g.drawString("" + scores.get(e), 240, 200+(20*e));
		}
	}
	
	void drawInstructionsState(Graphics g) {
		g.setColor(GAMEBACKGROUND);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
		g.setColor(STAR);
		g.setFont(titleFont);
		g.drawString("Instructions", 115, 80);
		g.setFont(enter_spaceFont);
		g.drawString("Your job is to defend the treasure chest from", 10, 140);
		g.drawString("the enemies that follow a specific path. Click ", 10, 165);
		g.drawString("a spot on the screen to spawn a tower. There", 10, 190);
		g.drawString("are two types of towers: “shooty” and “firey”", 10, 215);
		g.drawString("ones. A popup will appear. Type in the name", 10, 240);
		g.drawString("of the tower (either “shooty or “firey”) you wish", 10, 265);
		g.drawString("to buy to buy it. Shooty towers shoot", 10, 290);
		g.drawString("projectiles at the enemies that take away 2 ", 10, 315);
		g.drawString("health from them. Firey towers set the", 10, 340);
		g.drawString("enemies on fire, and any projectile from a", 10, 365);
		g.drawString("shooty tower will do double the damage. You", 10, 390);
		g.drawString("can upgrade shooty towers by pressing the 'u'", 10, 415);
		g.drawString("key then specifying which number tower you", 10, 440);
		g.drawString("wish to upgrade. Shooty towers cost $20, then", 10, 465);
		g.drawString("each upgrade is $20 more, and you can only", 10, 490);
		g.drawString("upgrade twice. Firey towers cost $40, and you", 10, 515);
		g.drawString("can’t upgrade them. The more towers you,", 10, 540);
		g.drawString("have the quicker the enemies speed up. To", 10, 565);
		g.drawString("slow them down, press the “s” key and it", 10, 590);
		g.drawString("takes away $15 and slows the spawn rate of", 10, 615);
		g.drawString("the enemies. The more enemies you kill,", 10, 640);
		g.drawString("the higher your final score will be.", 10, 665);
		
		g.drawString("Press the 'r' key to return to the menu.", 10, 750);
	}
	
	void drawInstructionsStateOne(Graphics g) {
		g.setColor(GAMEBACKGROUND);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
		g.setFont(titleFont);
		g.setColor(STAR);
		g.drawString("Game Objective", 90, 80);
		g.setFont(enter_spaceFont);
		g.drawString("Your job is to defend the treasure chest from", 10, 140);
		g.drawString("the enemies that follow a specific path.", 10, 165);
		
		g.drawString("Press the 'n' key to see more instructions.", 10, 750);
	}
	void drawInstructionsStateTwo(Graphics g) {
		g.setColor(GAMEBACKGROUND);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
		g.setFont(titleFont);
		g.setColor(STAR);
		g.drawString("Towers", 150, 80);
		g.setFont(enter_spaceFont);
		g.drawString("Shooty (Cost $20, 2 Upgrades: $40, $80)", 10, 140);
		g.drawString("Damage to enemies: 2, 4, 8", 10, 165);
		g.drawString("Shooty (Cost $60, No Upgrades)", 10, 190);
		g.drawString("Sets enemies on fire. 2x damge by shooty", 10, 215);
		g.drawString("tower", 10, 240);
		
		g.drawString("Press the 'n' key to see more instructions.", 10, 750);
	}
	void drawInstructionsStateThree(Graphics g) {
		g.setColor(GAMEBACKGROUND);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
		g.setFont(titleFont);
		g.setColor(STAR);
		g.drawString("Buying and Upgrading", 10, 80);
		g.setFont(enter_spaceFont);
		g.drawString("Buy towers by:", 10, 140);
		g.drawString("Clicking on screen where you want tower", 10, 165);
		g.drawString("Answering the prompt with 'firey' or 'shooty'", 10, 190);
		g.drawString("Can't spawn tower on road or chest, or near $", 10, 215);
		g.drawString("Maximun of 10 towers", 10, 240);
		g.drawString("Upgrade towers by:", 10, 265);
		g.drawString("Pressing the 'u' key", 10, 290);
		g.drawString("Answering the prompt with tower number", 10, 315);
		
		g.drawString("Press the 'n' key to see more instructions.", 10, 750);
	}
	void drawInstructionsStateFour(Graphics g) {
		g.setColor(GAMEBACKGROUND);
		g.fillRect(0, 0, TowerDefense.WIDTH, TowerDefense.HEIGHT);
		g.setFont(titleFont);
		g.setColor(STAR);
		g.drawString("Key Info", 150, 80);
		g.setFont(enter_spaceFont);
		g.drawString("Enemies increase in frequency", 10, 140);
		g.drawString("More towers = faster increase", 10, 165);
		g.drawString("Final Score = (# of enemies killed * 10) - ", 10, 190);
		g.drawString("(30 * # of towers)", 10, 215);
		g.drawString("So, less towers = higher score", 10, 240);
		g.drawString("The treasure chest has 20 health", 10, 265);
		g.drawString("The game is over when it gets to zero", 10, 290);
		
		g.drawString("Press the 'r' key to return to the menu.", 10, 750);
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
		    	addScoreToList(score);
		        currentState = END;
		        stopGame();
		    }
		}
		if(currentState == MENU) {
			if (e.getKeyCode()==KeyEvent.VK_SPACE) {
				currentState = INSTRUCTIONSONE;
			}
		}
		
		if(currentState == INSTRUCTIONSONE) {
			if (e.getKeyCode()==KeyEvent.VK_N) {
				currentState = INSTRUCTIONSTWO;
			}
		}else if(currentState == INSTRUCTIONSTWO) {
			if (e.getKeyCode()==KeyEvent.VK_N) {
				currentState = INSTRUCTIONSTHREE;
			}
		}else if(currentState == INSTRUCTIONSTHREE) {
			if (e.getKeyCode()==KeyEvent.VK_N) {
				currentState = INSTRUCTIONSFOUR;
			}
		}
		
		if(currentState == INSTRUCTIONSFOUR) {
			if (e.getKeyCode()==KeyEvent.VK_R) {
				currentState = MENU;
			}
		}
		
		if (e.getKeyCode()==KeyEvent.VK_U) {
			System.out.println("ooo");
			int upgradeNum = Integer.parseInt(JOptionPane.showInputDialog("Which number Tower do you want to updgrade"));
			oj.upgradeTower(upgradeNum);
		}
		if (e.getKeyCode()==KeyEvent.VK_S) {
			System.out.println("slow down");
			oj.slowAliens();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(currentState == GAME) {
			String input = JOptionPane.showInputDialog("What type of tower do you want to buy? (firey or shooty)");
			if(input.equals("firey")||input.equals("shooty")){
				oj.addTower(e.getX()-25, e.getY()-50, input, (input.equals("shooty"))? 20: 60);
			}else {
				JOptionPane.showMessageDialog(null, "That is not a valid tower name!!! Valid names are 'firey' or 'shooty'.");
			}
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
