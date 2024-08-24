package m9_spel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import java.awt.event.*;
import java.net.URLDecoder;
import java.io.File;
import javax.swing.ImageIcon;

public class GameController implements KeyListener {
	private Font gameNameFont, gameOverFont;
	private String gameStatus = "";

	private ArrayList<Entity> spriteList = new ArrayList<>();
	private ShipEntity ship;
	private int shipSpeed, missileSpeed, alienSpeed, alienCount;

	private int fps;
	private GameView gameView;
	private int gvWidth, gvHeight;
	private boolean gameRunning = true;

	public GameController(GameView gameView) {
		this.gameView = gameView;
		this.gvWidth = gameView.getWidth();
		this.gvHeight = gameView.getHeight();
		shipSpeed = 200;
		missileSpeed = 340;
		alienSpeed = 40;
		alienCount = 7;
		fps = 60;
		gameView.setKeyListener(this);
		loadFonts();
		loadScene();
	}

	public GameController(GameView gameView, int shipSpeed, int missileSpeed, int alienSpeed, int alienCount, int fps) {
		this.gameView = gameView;
		this.gvWidth = gameView.getWidth();
		this.gvHeight = gameView.getHeight();
		this.shipSpeed = shipSpeed;
		this.missileSpeed = missileSpeed;
		this.alienSpeed = alienSpeed;
		this.alienCount = alienCount;
		this.fps = fps;

		gameView.setKeyListener(this);
		loadFonts();
		loadScene();
	}

	public void loadScene() {
		//bakgrunden
		Image background = new ImageIcon(getClass().getResource("/background3.jpg")).getImage();
		gameView.setBackground(background);

		//spelare
		Image shipImg = new ImageIcon(getClass().getResource("/ship.png")).getImage();
		ship = new ShipEntity(shipImg, gvWidth / 2, gvHeight - shipImg.getHeight(null) - 50, shipSpeed, missileSpeed);
		spriteList.add(ship);

		//fiender
		Image alienImg = new ImageIcon(getClass().getResource("/alien.png")).getImage();
		for(int i = 1; i <= alienCount; i++) {	    	
			spriteList.add(new AlienEntity(alienImg, (gvWidth / (alienCount + 1)) * i, 20, alienSpeed));
		}
	}

	public void update(long deltaTime) {
		if(ship.missile != null && ship.missile.isActive()){
			if(ship.missile.getY() < 0 || ship.missile.getY() > gvWidth - ship.missile.getWidth()) {
				ship.missile.setActive(false);
			}
			else checkCollisionAndRemove();
		}

		for(int i = 1; i < spriteList.size(); i++) {
			if(spriteList.get(i).getY() < gvHeight - spriteList.get(i).getHeight()) {
				spriteList.get(i).move(deltaTime);
			}
			else {
				gameOver(false);
				return;
			}
		}
		ship.move(deltaTime);
	}

	public void render() {
		gameView.beginRender();
		gameView.openRender(spriteList);
		gameView.write("Space Invaders", 25, 45, Color.GREEN, gameNameFont);
		gameView.show();
	}

	public void run() {
		long lastUpdateTime = System.nanoTime(); 
		double delay = 1e9 / fps;

		while(gameRunning) {
			long deltaTime = System.nanoTime() - lastUpdateTime;

			if(deltaTime > delay) {            	
				update(deltaTime);
				render();
				lastUpdateTime = System.nanoTime();
			}
			else
				try {
					Thread.sleep((long) ((delay - deltaTime) / 1e6));
				} catch (InterruptedException e) {}
		}
		gameView.write(gameStatus, Color.RED, gameOverFont);
		gameView.show();
	}

	public void loadFonts() {
		try {
			String path = getClass().getResource("/droidlover.ttf").getFile();
			path = URLDecoder.decode(path, "utf-8");
			Font baseFont = Font.createFont(Font.TRUETYPE_FONT, new File(path));

			gameNameFont = baseFont.deriveFont(32f);
			gameOverFont = baseFont.deriveFont(150f);

		} catch (Exception e) {
			gameNameFont = new Font("Serif", Font.PLAIN, 32);
			gameOverFont = new Font("Serif", Font.PLAIN, 150);
			e.printStackTrace();
		}
	}

	public void gameOver(boolean win) {
		if(win) gameStatus = "You Won";
		else gameStatus = "Game Over";
		gameRunning = false;
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

	/** Spelets tangentbordslyssnare */
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_ESCAPE)
			System.exit(0);
		else if(key == KeyEvent.VK_LEFT && !(ship.getX() >= gvWidth - ship.getWidth()))
			ship.setDirectionX(-1);
		else if(key == KeyEvent.VK_RIGHT && !(ship.getX() <= 0))
			ship.setDirectionX(1);
		else if(key == KeyEvent.VK_SPACE) {
			ship.tryToFire();
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT)
			ship.setDirectionX(0);

	}
}



