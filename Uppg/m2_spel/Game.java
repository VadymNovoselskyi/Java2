package m2_spel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import se.egy.graphics.*;

public class Game implements KeyListener{
	
    private HashMap<String, Boolean> keyDown = new HashMap<>();
    
	private boolean gameRunning = true;
    private ImgContainer player;
    private int step = 2;
    
    private int width = 1000;
    private int height = 600;
	private GameScreen gameScreen = new GameScreen("Game", width, height, false); // false vid testkörning

	public Game() {
		gameScreen.setKeyListener(this);
		
	    keyDown.put("left", false); 
	    keyDown.put("right", false);
	    keyDown.put("up", false); 
	    keyDown.put("down", false);
	    keyDown.put("esc", false);
	   
		loadImages();
		gameLoop();
	}

	public void loadImages() {
		gameScreen.setBackground("background.jpg");
		player = new ImgContainer(384, 284, "playerImg.png");
	}

	public void update() {
		if(keyDown.get("esc"))
	        System.exit(0);
		
		else if(keyDown.get("right") && !(player.getX() + step > width - player.getWidth()))
	        player.setX(player.getX() + step);
		else if(keyDown.get("left") && !(player.getX() - step < 0))
	    	player.setX(player.getX() - step);
		else if(keyDown.get("up") && !(player.getY() - step < 0))
	        player.setY(player.getY() - step);
		else if(keyDown.get("down") && !(player.getY() + step > height - player.getHeight()))
	    	player.setY(player.getY() + step);
	}

	public void render() {
		gameScreen.render(player);
	}

	public void gameLoop() {
		while(gameRunning) {
			update();
			render();
			
			try {
				Thread.sleep(3);
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
	     else if(key == KeyEvent.VK_UP)
	        keyDown.put("up", true);
	     else if(key == KeyEvent.VK_DOWN)
	        keyDown.put("down", true);
	}
	    
	public void keyReleased(KeyEvent e) {
	     int key = e.getKeyCode();

	     if(key == KeyEvent.VK_LEFT)
	        keyDown.put("left", false);
	     else if(key == KeyEvent.VK_RIGHT)
	        keyDown.put("right", false);
	     else if(key == KeyEvent.VK_UP)
	    	keyDown.put("up", false);
	     else if(key == KeyEvent.VK_DOWN)
        	keyDown.put("down", false);
	}


	public static void main(String[] args) {
		new Game();
	}
	
}

