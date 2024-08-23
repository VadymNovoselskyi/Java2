package m6_spel;

import java.awt.Image;
import java.util.HashMap;
import java.awt.event.*;
import javax.swing.ImageIcon;

import se.egy.graphics.*;

public class Game implements KeyListener{
    private HashMap<String, Boolean> keyDown = new HashMap<>();
	private boolean gameRunning = true;
	
	private long lastUpdateTime;
	
    private Entity[] spriteList;
    
    private int speed = 250;
    private int width = 1000;
    private int height = 600;
	private GameScreen gameScreen = new GameScreen("Game", width, height, false); // false vid testkörning

	public Game() {
		gameScreen.setKeyListener(this);
		
	    keyDown.put("left", false); 
	    keyDown.put("right", false);
	    keyDown.put("esc", false);
	   
	    spriteList = new Entity[6];
		loadImages();
		gameLoop();
	}

	public void loadImages() {
		gameScreen.setBackground("background.jpg");
		
	    Image shipImg = new ImageIcon(getClass().getResource("/ship.png")).getImage();
		spriteList[0] = new ShipEntity(shipImg, width / 2, height - 200, speed);
	    spriteList[0].setY(height - spriteList[0].getHeight() - 14);

	    Image alienImg = new ImageIcon(getClass().getResource("/alien.png")).getImage();
	    for(int i = 1; i < 6; i++) {	    	
	    	spriteList[i] = new AlienEntity(alienImg, i * 175, 20, 20);
	    }
	}

	public void update(long deltaTime) {
		if(keyDown.get("esc"))
	        System.exit(0);
		
		else if(keyDown.get("right") && !(spriteList[0].getX() >= width - spriteList[0].getWidth()))
	        spriteList[0].setDirectionX(1);
		else if(keyDown.get("left") && !(spriteList[0].getX() <= 0))
	    	spriteList[0].setDirectionX(-1);
		
		for(int i = 0; i < 6; i++) {
			if(spriteList[i].getY() < height - spriteList[i].getHeight()) {
				spriteList[i].move(deltaTime);
			}
		}
		spriteList[0].setDirectionX(0);
	}

	public void render() {
		gameScreen.render(spriteList);
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
	}
	    
	public void keyReleased(KeyEvent e) {
	     int key = e.getKeyCode();

	     if(key == KeyEvent.VK_LEFT)
	        keyDown.put("left", false);
	     else if(key == KeyEvent.VK_RIGHT)
	        keyDown.put("right", false);
	}


	public static void main(String[] args) {
		new Game();
	}
	
}
