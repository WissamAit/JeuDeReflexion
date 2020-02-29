package entity;


public class Move {
	
	public Point start;
	public Point end;
	public boolean type;// true means we duplicate and false mouvement with removing the pawn in the first position 
	
	public Move() {
		
	}
	
	public Move(Point start, Point end, boolean type) {
		this.start = start;
		this.end = end;
		this.type = type;
	}


	public Point getStart()throws NullPointerException {
		return start;
	}


	public void setStart(Point start) {
		this.start = start;
	}


	public Point getEnd() throws NullPointerException{
		return end;
	}


	public void setEnd(Point end) {
		this.end = end;
	}


	public boolean isType() {
		return type;
	}


	public void setType(boolean type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "(start=" + start + ", end=" + end + "," + type+")";
	}
	
	
	

}
