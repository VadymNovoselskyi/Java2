package m02_spel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import se.egy.graphics.*;

public class Ship implements KeyListener{
	
    private HashMap<String, Boolean> keyDown = new HashMap<>();
    
	private boolean gameRunning = true;
    private ImgContainer player;
    private int step = 2;
    
    private int width = 1000;
    private int height = 600;
	private GameScreen gameScreen = new GameScreen("Game", width, height, false); // false vid testkörning

	public Ship() {
		gameScreen.setKeyListener(this);
		
		keyDown.put("esc", false); 
	    keyDown.put("left", false); 
	    keyDown.put("right", false);
	   
		loadImages();
		gameLoop();
	}

	public void loadImages() {
		gameScreen.setBackground("background.jpg");
		player = new ImgContainer(384, 284, "ship.png");
	}

	public void update() {
		if(keyDown.get("esc"))
	        System.exit(0);
		
		else if(keyDown.get("right") && !(player.getX() + step > width - player.getWidth()))
	        player.setX(player.getX() + step);
		else if(keyDown.get("left") && !(player.getX() - step < player.getWidth()))
	    	player.setX(player.getX() - step);
	}

	public void render() {
		gameScreen.render(player);
	}

	public void gameLoop() {
		while(gameRunning) {
			update();
			render();
			
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
		new Ship();
	}
	
}

