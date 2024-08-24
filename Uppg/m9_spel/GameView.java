package m9_spel;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Collection;

import javax.swing.JFrame;

public class GameView {
	private int height, width;
	private String title;

	private JFrame jf; // Fönstret
	private Canvas canvas; // ritytan där renderingen sker
	private BufferStrategy backBuffer;
	private Image background = null;
	private Graphics2D g;

	public GameView(int width, int height, String title) {
		this.height = height;
		this.width = width;
		this.title = title;
		createWindow();

	}

	private void createWindow() {
		// Skapar vår rityta canvas med rätt bredd och höjd
		canvas = new Canvas();
		canvas.setSize(new Dimension(width, height));

		// Skapar fönstret.
		jf = new JFrame(title);
		// Lägger in ritytan i fönstret.
		jf.add(canvas);

		// Lite inställningar
		jf.setResizable(false); // Går ej att ändra storlek på fönster
		jf.pack(); // Packar så att inget tomrum visas
		jf.setLocationRelativeTo(null); // Placeras i mitten på skärmen

		// Går att stänga av med x-rutan
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		jf.setIgnoreRepaint(true); // Ritas inte om av JVM.       
		jf.setVisible(true); // Gör allt synligt!
		canvas.requestFocus(); // Ger vår canvas fokus

		canvas.createBufferStrategy(2); 
		backBuffer = canvas.getBufferStrategy();
	}


	private Graphics2D emptyScreen() {
		Graphics2D g = (Graphics2D)backBuffer.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
		g.drawImage(background, 0, 0, width, height, canvas);
		return g;
	}


	public void write(String text, int x, int y, Color color, Font font) {
		g.setFont(font);
		g.setColor(color);
		g.drawString(text, x, y);
	}

	public void write(String text, Color color, Font font) {
		g.setFont(font);
		g.setColor(color);
		FontMetrics fontMetrics = g.getFontMetrics(font);
		int x = (width - fontMetrics.stringWidth(text)) / 2;
		int y = ((height - fontMetrics.getHeight()) / 2);
		g.drawString(text, x, y);
	}


	public void beginRender() {
		this.g = emptyScreen();
	}

	public void openRender(Drawable drawObj) {
		drawObj.draw(g);
	}
	public void openRender(Drawable[] drawArray) {
		for(Drawable drawoObj : drawArray) {
			drawoObj.draw(g);
		}
	}
	public void openRender(Collection<? extends Drawable> drawList) {
		for(Drawable drawoObj : drawList) {
			drawoObj.draw(g);
		}
	}

	public void show() {
		backBuffer.show();
	}


	public void render(Drawable drawObj) {
		Graphics2D g = emptyScreen();
		drawObj.draw(g);
		backBuffer.show();
	}
	public void render(Drawable[] drawArray) {
		Graphics2D g = emptyScreen();
		for(Drawable drawoObj : drawArray) {
			drawoObj.draw(g);
		}
		backBuffer.show();
	}
	public void render(Collection<? extends Drawable> drawList) {
		Graphics2D g = emptyScreen();
		for(Drawable drawoObj : drawList) {
			drawoObj.draw(g);
		}
		backBuffer.show();
	}



	public void setKeyListener(KeyListener keyListener) {
		canvas.addKeyListener(keyListener);
	}


	public void setBackground(Image background) {
		this.background = background;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
