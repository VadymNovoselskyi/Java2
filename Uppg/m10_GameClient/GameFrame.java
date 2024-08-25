package m10_GameClient;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

import javax.swing.JFrame;

public class GameFrame extends JFrame implements KeyListener{

	public HashMap<String, Boolean> keyDown = new HashMap<>();

	private Canvas gameCanvas;
	private BufferStrategy backBuffer;

	private Dimension canvasDimension= new Dimension(800, 600);

	public GameFrame(){   
		addKeyListener(this);

		createWindow();      

		keyDown.put("left", false);
		keyDown.put("right", false);
		keyDown.put("up", false);
		keyDown.put("down", false);
	}

	public void createWindow(){
		gameCanvas = new Canvas();
		gameCanvas.setSize(canvasDimension);
		gameCanvas.setFocusable(false);

		this.add(gameCanvas);
		this.pack();
		this.setMinimumSize(canvasDimension);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		gameCanvas.createBufferStrategy(2);
		backBuffer = gameCanvas.getBufferStrategy();
	}

	public synchronized void render(HashMap<Integer, Player> playerMap){
		Graphics2D g = (Graphics2D)backBuffer.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

		for (Player player : playerMap.values()) {
			player.draw(g);
		}

		g.dispose();
		backBuffer.show();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_LEFT)
			keyDown.put("left", true);
		else if(key == KeyEvent.VK_RIGHT)
			keyDown.put("right", true);
		else if(key == KeyEvent.VK_UP)
			keyDown.put("up", true);
		else if(key == KeyEvent.VK_DOWN)
			keyDown.put("down", true);;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if(key == KeyEvent.VK_LEFT)
			keyDown.put("left", false);
		else if(key == KeyEvent.VK_RIGHT)
			keyDown.put("right", false);
		else if(key == KeyEvent.VK_UP)
			keyDown.put("up", false);
		else if(key == KeyEvent.VK_DOWN)
			keyDown.put("down", false);;
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	public Dimension getCanvasDimension() {
		return canvasDimension;
	}
}
