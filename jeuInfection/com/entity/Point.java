package entity;

public class Point {
	public int x;
	public int y;
	
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}


	public int getX() throws NullPointerException{
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() throws NullPointerException{
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	@Override
	public String toString() {
		return "("+ x + "," + y + ")";
	}
	
	
	
	
	

}
