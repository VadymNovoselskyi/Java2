package m05_oop_02;

import java.awt.Color;
import java.awt.Graphics2D;

public class Square extends Rectangle {
	private int side;
  	
  	public Square(int side, int x, int y, Color c){
       	super(side,side,x,y,c); 
       	this.side = side;
  	}
  	
	public int getArea(){
		int area = side * side;
		return area;
	}

	public int getPerimeter(){
		int perimeter = 4 * side;
		return perimeter;
	}
  	
	public void draw(Graphics2D g) {
		g.setColor(super.getColor());
		g.fillRect(super.getxPos(), super.getyPos(), side, side);
	}
}

