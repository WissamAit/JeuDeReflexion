package services;

import entity.Move;


public class IA {
	
	
	private int deepness;
	private int current_player;
	private boolean cut;
	public int nbNodes;
	
	
	public IA(int deepness, int current_player, boolean cut) {
		this.deepness = deepness;
		this.current_player = current_player;
		this.cut = cut;
		
	}
	
	
	public int getDeepness() {
		return deepness;
	}
	
	
	public void setDeepness(int deepness) {
		this.deepness = deepness;
	}
	
	
	public int getCurrent_player() {
		return current_player;
	}
	
	
	public void setCurrent_player(int current_player) {
		this.current_player = current_player;
	}
	
	
	public boolean isCut() {
		return cut;
	}
	
	
	public void setCut(boolean cut) {
		this.cut = cut;
	}
	
	// it decides which algorithm we will use 
	//if cut is true then we call minMax Algorithm else we call the alphaBeta Algorithm
	// after that we return the best move that the current player can make 
	public Move decide(State s,int d) {
		Move bestMove=new Move();
		float v=Float.NEGATIVE_INFINITY;
		float v1;
		for (Move move:s.getMoves(current_player)) {
			State s1=s.play(move);
			if(cut==true) 
			{
			v1=minMax(s1,d-1);}
			else 
			{
			 v1=alphaBeta(s1,1,0,d-1);	
			}
			if (v1>v) {
				v=v1;
				bestMove=move;
			}
		}
		return bestMove;
		
	}
	
	//IA's Algorithms : MinMax and AlphaBeta cut 
	
	//the player search for the best cost to play 
	public float minMax(State state,int d) {
		nbNodes++;
		float b;
		float m;

		if((d==0)||(state.isFinished())) {
			return state.getScore(current_player);
		}
		else {
		if(state.current_player==current_player) {
			b=Float.NEGATIVE_INFINITY;
			for (Move move:state.getMoves(state.current_player)) {
				State s1=state.play(move);
				 m=minMax(s1,d-1);
				if (b<m) {
					b=m;
				}
				
			}
		}
		else {
			
				 b=Float.POSITIVE_INFINITY;
				for (Move move:state.getMoves(current_player)) {
					State s1=state.play(move);
					m=minMax(s1,d-1);
					if (b>m) {
						b=m;
					}
					}
				}
		
			
		return b;
		}
	}
	
	public float alphaBeta(State state,float alpha,float beta,int d) throws NullPointerException{
		nbNodes++;
		

		if((d==0)||(state.isFinished())) {
			return state.getScore(current_player);
		}
		else {
		if(state.current_player==current_player) {
			for ( Move move:state.getMoves(state.current_player)) {
				alpha=Float.max(alpha,alphaBeta(state,alpha,beta,d-1));
				if (alpha>=beta) {
					return alpha;
				}
				}
			return alpha;
		}
		else {
			for (Move move:state.getMoves(current_player)) {
				beta=Float.min(beta,alphaBeta(state,alpha,beta,d-1));
					if (alpha>=beta) {
						return beta;
					}}
				}
		return beta;
		
	}
	}
	
	
	
}
