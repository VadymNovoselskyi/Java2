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
	   return g;
   }
   
   public void render(Drawable drawObj) {
	   Graphics2D g = emptyScreen();
	   drawObj.draw(g);
	   g.dispose();
	   backBuffer.show();
	}

   
   public void render(Drawable[] drawArray) {
	   Graphics2D g = emptyScreen();
	   for(Drawable drawoObj : drawArray) {
	       drawoObj.draw(g);
	   }
	   g.dispose();
	   backBuffer.show();
	}

	public void render(Collection<? extends Drawable> drawList) {
		Graphics2D g = emptyScreen();
	    for(Drawable drawoObj : drawList) {
	       drawoObj.draw(g);
	    }
	    g.dispose();
	    backBuffer.show();
	}


   public void setKeyListener(KeyListener keyListener) {
	   canvas.addKeyListener(keyListener);
	}
   
}
