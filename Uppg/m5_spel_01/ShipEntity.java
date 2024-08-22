package m5_spel_01;

import java.awt.Image;

public class ShipEntity extends Entity{
	private int speed;
	
	public ShipEntity(Image image, int xPos, int yPos, int speed) {
		super(image, xPos, yPos, speed);
		this.speed = speed;
	}
	
	public void move() {
		int dx = super.getDirectionX();
		int x = super.getX();
		int dy = super.getDirectoinY();
		int y = super.getY();
		
		super.setX(x + dx * speed);
		super.setY(y + dy * speed);
	}
}
