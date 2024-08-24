package m05_oop_02;

import java.awt.Color;
import java.awt.Graphics2D;


public class Rectangle extends Shape {
	private int width;
	private int height;

	
	public Rectangle(int width, int height, int xPos, int yPos, Color color){
		super(xPos, yPos, color);
		this.width = width;
		this.height = height;

	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getArea(){
		int area = width * height;
		return area;
	}

	public int getPerimeter(){
		int perimeter = 2 * (width + height);
		return perimeter;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(super.getColor());
		g.fillRect(super.getxPos(), super.getyPos(), width, height);
	}
}

