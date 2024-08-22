package miniP1;

import java.awt.Image;
import se.egy.graphics.*;

public class Entity implements Drawable {
	private Image img;
	private int x, y;
	
	public Entity(Image img, int x, int y) {
		this.img = img;
		this.x = x;
		this.y = y;
	}
	
	public void draw(java.awt.Graphics2D g) {
		g.drawImage(img, x, y, null);
	}

	public int getWidth() {
		return img.getWidth(null);
	}

	public int getHeight() {
		return img.getHeight(null);
	}

	public Image getImg() {
		return img;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
