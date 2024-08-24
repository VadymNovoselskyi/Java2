package m08_spel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.*;
import java.net.URLDecoder;
import java.io.File;
import javax.swing.ImageIcon;

import se.egy.graphics.*;

public class Game implements KeyListener{
    private HashMap<String, Boolean> keyDown = new HashMap<>();
	private boolean gameRunning = true;
	public Font fontName = null;
	public Font fontOver = null;
	private TxtContainer gameName;
	private TxtContainer gameOver;
	
	private long lastUpdateTime;
	
    private ArrayList<Entity> spriteList = new ArrayList<>();
    private ShipEntity ship;
    private int alienCount = 6;
    
    private int speed = 250;
    private int width = 1000;
    private int height = 600;
	private GameScreen gameScreen = new GameScreen("Game", width, height, false); // false vid testkörning

	public Game() {
		try {
		   String path = getClass().getResource("/droidlover.ttf").getFile();
		   path =  URLDecoder.decode(path,"utf-8");
		   
		   fontName = Font.createFont(Font.TRUETYPE_FONT, new File(path));
		   fontName = fontName.deriveFont(32f); // Typsnittsstorlek
		   fontOver = Font.createFont(Font.TRUETYPE_FONT, new File(path));
		   fontOver = fontOver.deriveFont(150f); // Typsnittsstorlek
		} catch (Exception e) {
		   e.printStackTrace();
		} 
		gameName = new TxtContainer("Space Invader", 10, 32, fontName, Color.BLUE);
		gameOver = new TxtContainer("", (int) width / 2 - 372, (int) height / 2 + 10, fontOver, Color.RED);
		
		gameScreen.setKeyListener(this);
		
	    keyDown.put("left", false); 
	    keyDown.put("right", false);
	    keyDown.put("space", false);
	    keyDown.put("esc", false);
	   
		loadImages();
		gameLoop();
	}

	public void loadImages() {
		gameScreen.setBackground("background.jpg");
		
	    Image shipImg = new ImageIcon(getClass().getResource("/ship.png")).getImage();
		spriteList.add(new ShipEntity(shipImg, width / 2, height - 100, speed));
		ship = (ShipEntity) spriteList.get(0);
		ship.setY(height - ship.getHeight() - 14);

	    Image alienImg = new ImageIcon(getClass().getResource("/alien.png")).getImage();
	    for(int i = 1; i < alienCount + 1; i++) {	    	
	    	spriteList.add(new AlienEntity(alienImg, (width / (alienCount + 1)) * i, 20, 30));
	    }
	}

	public void update(long deltaTime) {
		if(keyDown.get("esc"))
	        System.exit(0);
		
		
		else if(keyDown.get("right") && !(ship.getX() >= width - ship.getWidth()))
			ship.setDirectionX(1);
		else if(keyDown.get("left") && !(ship.getX() <= 0))
			ship.setDirectionX(-1);
		else if(keyDown.get("space")) {
			ship.tryToFire();
		}
		
		if(ship.missile != null && ship.missile.isActive()){
			if(ship.missile.getY() < 0 || ship.missile.getY() > width - ship.missile.getWidth()) {
				ship.missile.setActive(false);
			}
			else checkCollisionAndRemove();
		}
		
		for(int i = 1; i < spriteList.size(); i++) {
			if(spriteList.get(i).getY() < height - spriteList.get(i).getHeight()) {
				spriteList.get(i).move(deltaTime);
			}
			else {
				gameOver(false);
				return;
			}
		}
		ship.move(deltaTime);
		ship.setDirectionX(0);
	}

	public void render() {
		gameScreen.beginRender();
     	gameScreen.openRender(gameName);
     	gameScreen.openRender(gameOver);
     	gameScreen.openRender(spriteList);
     	gameScreen.show();
	}

	public void gameLoop() {
		lastUpdateTime = System.nanoTime(); 
		while(gameRunning) {
			long deltaTime = System.nanoTime() - lastUpdateTime;

            if(deltaTime > 16666666) {            	
            	lastUpdateTime = System.nanoTime();
            	update(deltaTime);
            	render();
            }
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {}
		}
	}

	/** Spelets tangentbordslyssnare */
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
	     int key = e.getKeyCode();
	     
	     if(key == KeyEvent.VK_ESCAPE)
	    	 keyDown.put("esc", true);
	     
	     else if(key == KeyEvent.VK_LEFT)
	        keyDown.put("left", true);
	     else if(key == KeyEvent.VK_RIGHT)
	        keyDown.put("right", true);
	     else if(key == KeyEvent.VK_SPACE) {
	    	 keyDown.put("space", true);
	     }
	}
	    
	public void keyReleased(KeyEvent e) {
	     int key = e.getKeyCode();

	     if(key == KeyEvent.VK_LEFT)
	        keyDown.put("left", false);
	     else if(key == KeyEvent.VK_RIGHT)
	        keyDown.put("right", false);
	     else if(key == KeyEvent.VK_SPACE) {
	    	 keyDown.put("space", false);
	     }
	}
	
	public void gameOver(boolean win) {
		gameRunning = false;
		if(!win) gameOver.setTxt("Game Over");
		else {
			gameOver.setX(gameOver.getX() + 75);
			gameOver.setTxt("You Won");
		}

	}
	
	public void checkCollisionAndRemove(){
	    ArrayList<Entity> removeList = new ArrayList<>();
	    
	    if(ship.missile != null && ship.missile.isActive()) {
	    	for(int i = 1; i < spriteList.size(); i++) {	    		
	    		if(ship.missile.collision(spriteList.get(i))) {
	    			removeList.add(spriteList.get(i));
	    			ship.missile.setActive(false);
	    		}
	    	}
	    }

	    spriteList.removeAll(removeList);
	    if(spriteList.size() == 1) gameOver(true);
	}


	public static void main(String[] args) {
		new Game();
	}
	
}

