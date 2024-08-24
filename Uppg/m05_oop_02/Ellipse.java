package m05_oop_02;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ellipse extends Shape {
	private int width;
	private int height;

	public Ellipse(int width, int height, int xPos, int yPos, Color color) {
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
		int area = (int) (Math.PI * width * height);
		return area;
	}

	public int getPerimeter(){
		int perimeter = (int) (Math.PI * Math.sqrt(2 * (Math.pow(width, 2) + Math.pow(height, 2))));
		return perimeter;
	}
  	
	public void draw(Graphics2D g) {
		g.setColor(super.getColor());
		g.fillOval(super.getxPos(), super.getyPos(), width, height);
	}
}
