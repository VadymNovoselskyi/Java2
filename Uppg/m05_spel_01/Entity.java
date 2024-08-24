package m05_spel_01;

import java.awt.Image;
import se.egy.graphics.*;

public abstract class Entity implements Drawable {
	private Image img;
	private int x, y, speed, dx, dy;
	private boolean active;
	
	public Entity(Image img, int x, int y, int speed) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
	}
	
	public void draw(java.awt.Graphics2D g) {
		g.drawImage(img, x, y, null);
	}
	
	public abstract void move();

	public boolean isActive() {
		return active;
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
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDirectionX() {
		return dx;
	}

	public int getDirectoinY() {
		return dy;
	}
	
	

	public void setDirectionX(int dx) {
		this.dx = dx;
	}

	public void setDirectionY(int dy) {
		this.dy = dy;
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