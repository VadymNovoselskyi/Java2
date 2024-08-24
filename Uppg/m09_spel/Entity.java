package m09_spel;

import java.awt.Image;
import java.awt.Rectangle;

public abstract class Entity implements Drawable {
	private Image img;
	private double x, y;
	private int speed, dx = 0, dy = 0;
	private boolean active = true;
	private Rectangle rectangle = null;

	public Entity(Image img, double x, double y, int speed) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		rectangle = new Rectangle((int)x, (int)y, img.getWidth(null), img.getHeight(null));
	}

	public void draw(java.awt.Graphics2D g) {
		g.drawImage(img, (int)x, (int)y, null);
	}

	public abstract void move(long deltaTime);

	public boolean collision(Entity entity) {
		getRectangle();
		return rectangle.intersects(entity.getRectangle());
	}

	public Rectangle getRectangle() { 
		rectangle.setLocation((int)x, (int)y);
		return rectangle;
	}

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

	public double getX() {
		return x;
	}

	public double getY() {
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

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

}
