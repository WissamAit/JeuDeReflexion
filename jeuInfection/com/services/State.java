package services;
import java.util.ArrayList;


import entity.Move;
import entity.Point;
public class State {
	public int [][] board;
	public int current_player;
	public int m,n;
	int nbCoutA;
	
	
	
	

	
	public State(int[][] board,int n, int m,int current_player,int nbCoutA) {
		this.board = board;
		this.n=n;
		this.m=m;
		this.current_player=current_player;
		this.nbCoutA=nbCoutA;
	}

	
	
	
	



	public int[][] getBoard() {
		return board;
	}




	public void setBoard(int[][] board) {
		this.board = board;
	}




	public int getCurrent_player() {
		return current_player;
	}




	public void setCurrent_player(int current_player) {
		this.current_player = current_player;
	}


	//Creation of the initial board where we put the first player (1-->white) on the lower right and the second player (2-->black)on the upper right
	public int [][] init_board(){
		int [][] board = new int[n][m];
		for(int i=0;i<n;i++) {
			for (int j=0;j<m;j++) {
				board[i][j]=0;
				}
			}
		board[0][0]=2;
		board[n-1][m-1]=1;
		this.board=board;
		return board;
		}
	
	// we print the board with players and all empty cases
	public void print_board(){
		for(int i=0;i<n;i++) {
			for (int j=0; j<m;j++) {
				System.out.print(this.board[i][j]+" ");
				}
				System.out.println(" ");
			}
	}
	
	
//here we test if the round is over or not 
//the round is over when the two players have no possible mouvement
// or we have just player one and empty cases
// or juste player two and empty cases 
public boolean isFinished() {
		boolean isFinished=false;
			if((this.getMoves(1).size()==0)||(this.getMoves(2).size()==0))
			{
			isFinished =true;
			return isFinished;
			}
			else {
				int i=0;
				int j=0;
				while((i<this.n)&&(isFinished=false)){
					while(j<this.m){
						if(((this.board[i][j]==1)||(this.board[i][j]==0))||((this.board[i][j]==2)||(this.board[i][j]==0))) { 
					isFinished=true;
						}j++;
						}
						i++;} 
				}
					return isFinished;
			}
				
	
	
				

	
	public State play (Move move) {
		
		int [][] board2=new int[n][m];
		
		for(int i=0;i<this.n;i++) {
			for(int j=0;j<this.m;j++) {
				board2[i][j]=this.board[i][j];
			}
		}
		
		
		
		int endX=move.getEnd().getX();
		int startX=move.getStart().getX();
		int endY=move.getEnd().getY();
		int startY=move.getStart().getY();
		
		
		if (move.type==true) {
			board2[move.end.getX()][move.end.getY()]=board2[move.start.getX()][move.start.getY()];
			
			  if((endX+ 1 < n) && (board2[endX+1][endY]  !=0) && (board2[endX+1][endY] != board2[startX][startY])) {
                  board2[endX+1][endY] = board2[endX][endY] ;
              }
              if((endX - 1 >= 0) && (board2[endX-1][endY] != 0 ) && (board2[endX-1][endY] != board2[startX][startY] )) {
                  board2[endX-1][endY] = board2[endX][endY] ;
              }
              if((endY + 1 < m) && (board2[endX][endY + 1] != 0) && (board2[endX][endY + 1] != board2[startX][startY])) {
                  board2[endX][endY + 1] = board2[endX][endY] ;
              }
              if((endY -1 >= 0) && (board2[endX][endY-1] != 0) && (board2[endX][endY-1] != board2[startX][startY])) {
                  board2[endX][endY-1] = board2[endX][endY] ;
              }
              
		 
			
		}
		else
		{	
			board2[endX][endY]=board[startX][startY];
			board2[startX][startY]=0;
			
			
		}
		
		State s=new State(board2,n,m,current_player,nbCoutA);
		
		
		
		return s;
		
		
	}
	
	
	

	//it returns the position of a player in the grid 
	public ArrayList<Point>getPosition(int player){
		ArrayList<Point> position=new ArrayList<Point>();
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if (this.board[i][j]==player){
					Point point=new Point(i,j);
					position.add(point);
					}
				}
			}
		return position;
		}
	
	//it returns an ArrayList of the movements that a player can make 
	public ArrayList< Move > getMoves(int player) {
		ArrayList<Point> pos=this.getPosition(player);
		ArrayList<Move> movePossible=new ArrayList<>();
		
		//if the case which is adjacent to the current position of player's pawn is empty we can duplicate 
		//we test the three directions (left,right,up,down)
		for(Point point :pos) {
				
			
			if((point.getX()+1 < this.n && point.getX()+1 >= 0 )&&( this.board[point.getX()+1][point.getY()] == 0)){
               movePossible.add(new Move(new Point(point.getX(),point.getY()),new Point(point.getX()+1,point.getY()),true));
            }
            if((point.getX()-1 < this.n && point.getX()-1 >=0) && (this.board[point.getX()-1][point.getY()] == 0)) {
                movePossible.add(new Move(new Point(point.getX(),point.getY()),new Point(point.getX()-1,point.getY()),true));
            }
            if((point.getY()+1 < this.m && point.getY()+1 >=0 )&&( this.board[point.getX()][point.getY()+1] ==0)) {
                movePossible.add(new Move(new Point(point.getX(),point.getY()),new Point(point.getX(),point.getY()+1),true));   
            }
            if((point.getY()-1 < this.m && point.getY()-1 >=0) && (this.board[point.getX()][point.getY()-1] == 0)) {
                movePossible.add(new Move(new Point(point.getX(),point.getY()),new Point(point.getX(),point.getY()-1),true));
            }
           
           
            //if the case which has the postion = position of pawn plus two is empty we can move the pawn to this case and we jump over the pawn adjacent whoever was his player
            if(point.getX()+2 < this.n && point.getX()+2 >=0 && (this.board[point.getX()+2][point.getY()] ==0 )) {
                movePossible.add(new Move(new Point(point.getX(),point.getY()),new Point(point.getX()+2,point.getY()),false));
            }
            if(point.getX()-2 < this.n && point.getX()-2 >=0 && (this.board[point.getX()-2][point.getY()] == 0)) {
                movePossible.add(new Move(new Point(point.getX(),point.getY()),new Point(point.getX()-2,point.getY()),false));
            }
            if(point.getY()+2 < this.m && point.getY()+2 >=0 &&(this.board[point.getX()][point.getY()+2] == 0 )) {
                movePossible.add(new Move(new Point(point.getX(),point.getY()),new Point(point.getX(),point.getY()+2),false));
            }
            if(point.getY()-2 < this.m && point.getY()-2 >=0 &&(this.board[point.getX()][point.getY()-2] == 0)) {
                movePossible.add(new Move(new Point(point.getX(),point.getY()),new Point(point.getX(),point.getY()-2),false));
            }
					
					}
		
		
		
		return movePossible;
		
	}
	
	// we count score of player every time he makes a movement 
	// the score must be between 0 and 1
	public float getScore(int player) {
		int nb1=0;
		int nb2=0;
		float score;
		
		for(int i=0;i<this.n;i++){
			for(int j=0;j<this.m;j++) {
				if(board[i][j]==1) {
					nb1++;
				}
				if(board[i][j]==2) {
					nb2++;
				}
				}
			
			}
		score=(float) nb1/(nb1+nb2);
		if(player==1) {
			return score;
		}
		if(player==2)
		{
			score=1-score;
		}
		return score;
		
		}
	
	
}
