package test;

import java.util.Scanner;

import entity.Move;
import services.State;
import services.IA;

public class Main {
	public static void main(String[] args){
		@SuppressWarnings("resource")
		//here we ask the user to choose the size of the board, the player who is going to play first, which algorithm do we call and also the deepness
		Scanner sc=new Scanner(System.in);
		int [][] board = null;
		
		System.out.println("Please choose the size of the board : \n number of rows ");
		int lig=sc.nextInt();
		System.out.println("number  of columns : ");
		int col=sc.nextInt();
		
		
		
		
		
		int player;
		int profondeur;
		@SuppressWarnings("resource")
		// We choose the algorithm to excute the program either minMax or alphaBeta
		int ChoiceOfAlgo;
		System.out.println("Make your choise : \n -1 AlphaBeta \n -2 MinMax");
		do {
			ChoiceOfAlgo=sc.nextInt();
			if((ChoiceOfAlgo!=1)&&(ChoiceOfAlgo!=2)) {
				System.out.println("Warning! you have entered a wrong number ------> Make your choice : \n -1 AlphaBeta \n -2 MinMax" );
			}
			
		}
		while((ChoiceOfAlgo!=1) && (ChoiceOfAlgo!=2));
		
		boolean debug;
		
		if(ChoiceOfAlgo==1) {
			debug=false;	
		}
		else {
			debug=true;
		}
		// we choose the deepness
		System.out.println("Choose a deepness for player 1!");
		int prof=sc.nextInt();
		System.out.println("Choose a deepness for player 2!");
		int prof2=sc.nextInt();
		// we choose the number of advance costs of player one
		System.out.println("Choose the player one advance costs!");
		int nbCoutA=sc.nextInt();
		
		//first, we have to print the initial board without any movement done
		State state=new State(board,lig,col,1,nbCoutA);
		state.init_board();
		state.print_board();
		System.out.println("________________");
		
		// 
		IA ia=new IA(prof,1,debug);
		IA iaa=new IA(prof2,2,debug);
		
		for(int i=0;i<nbCoutA;i++) {
			Move move=ia.decide(state, ia.getDeepness());
			state=state.play(move);
			state.print_board();
			System.out.println("____________________");
			}
		

		// while the round is not finished, we choose the best movement of the current player then we make the movement, count its score and finally we change the player
		while(!state.isFinished()) {
			
			Move move=ia.decide(state, ia.getDeepness());
			state=state.play(move);
			
			state.print_board();
			System.out.println("SCORE ====> player ONE :  "+state.getScore(1)+"  -  Player TWO :  "+state.getScore(2));
			System.out.println("____________________");
			if(!state.isFinished()) {
			Move movee=iaa.decide(state, iaa.getDeepness());
			state=state.play(movee);
			
			state.print_board();
			System.out.println("SCORE ====> player ONE :  "+state.getScore(1)+"  -  Player TWO :  "+state.getScore(2));
			System.out.println("____________________");
			}
			
		}
		
		System.out.println("THE ROUND IS OVER :'(");
		if (state.getScore(1)<state.getScore(2)) {
			System.out.println("THE PLAYER TWO HAS WON THE ROUND ******CONGRATULATION*****");
		}
		else {
			System.out.println("THE PLAYER ONE HAS WON THE ROUND ******CONGRATULATION*****");
		}
		System.out.println("____________________");
		System.out.println("le nombre de noeuds visités par joueur blanc est : "+ia.nbNodes);
		System.out.println("____________________");
		System.out.println("le nombre de noeuds visités par le joueur noir est : "+iaa.nbNodes);
		int somme;
		somme=ia.nbNodes+iaa.nbNodes;
		System.out.println("____________________");
		System.out.println("total des noeuds visités est : "+somme);
		System.out.println("____________________GoodBye_________________");
		
		}
	
	}
