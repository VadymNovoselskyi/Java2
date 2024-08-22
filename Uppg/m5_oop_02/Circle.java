package m5_oop_02;

import java.awt.Color;
import java.awt.Graphics2D;


public class Circle extends Ellipse {
	private int radius;

	public Circle(int radius, int xPos, int yPos, Color color) { 
		super(radius, radius, xPos, yPos, color);
		this.radius = radius;
	}

	public int getArea(){
		int area = (int) (Math.PI * Math.pow(radius, 2));
		return area;
	}

	public int getPerimeter(){
		int perimeter = (int) (2 * Math.PI * radius);
		return perimeter;
	}

	public void draw(Graphics2D g) {
		g.setColor(super.getColor());
		g.fillOval(super.getxPos(), super.getyPos(), radius * 2, radius * 2);
	}
}

