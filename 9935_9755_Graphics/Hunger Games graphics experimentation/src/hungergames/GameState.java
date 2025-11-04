package hungergames;

import java.awt.Graphics;


public class GameState extends States {
	
	private Player player1,player2;
	private Board board;
	//Player that plays first
	private Player firstPlayer;


	//Player that plays second
	private Player secondPlayer;
	
	private Game game;
	private static boolean stopThread=false;
	//Object that will eventually get the value of whoever player wins
	private Player winner=null;
	private Player turn;
	
	
	public GameState(Board board,Player player1,Player player2) {
		this.board=board;
		this.player1=player1;
		this.player2=player2;
		game=new Game();
		
		turn=setTurns();
		//Based on what the function setTurns returns we initialize the objects firstPlayer,secondPlayer.
		if(turn==player1) {
			firstPlayer=player1;
			secondPlayer=player2;
		}
		else {
			firstPlayer=player2;
			secondPlayer=player1;
		}
			
		

	}


	@Override
	public void tick() {
		if(turn==player1) {
			firstPlayer=player1;
			secondPlayer=player2;
		}
		else {
			firstPlayer=player2;
			secondPlayer=player1;
		}
		
		// Game loop
		System.out.println("======= Round " + Game.getRound() + "===========");
		// Make each player move
		
		if(firstPlayer instanceof HeuristicPlayer)
			((HeuristicPlayer) firstPlayer).move(secondPlayer);
		else
			firstPlayer.move();
		
		if(HeuristicPlayer.kill(firstPlayer, secondPlayer,2)==true){
			//end game
			//winner firstPlayer
			winner=firstPlayer;
			if(stopThread=false)
				stopThread=true;
		}
		else if(HeuristicPlayer.kill(secondPlayer, firstPlayer,2)==true) {
			//end game
			//winner secondPlayer
			winner=secondPlayer;
			if(stopThread=false)
				stopThread=true;

		}
		if(secondPlayer instanceof HeuristicPlayer) 
			((HeuristicPlayer) secondPlayer).move(firstPlayer);
		
		else
			secondPlayer.move();
		
		if(HeuristicPlayer.kill(secondPlayer, firstPlayer,2)==true) {
			//end game
			//winner secondPlayer
			winner=secondPlayer;
			if(stopThread=false)
				stopThread=true;

		}
		else if(HeuristicPlayer.kill(firstPlayer, firstPlayer,2)==true){
			//end game
			//winner firstPlayer
			winner=firstPlayer;
			if(stopThread=false)
				stopThread=true;

		}
		
		
		
			
		

		

		// Resize board
		if(Game.getRound() != 0 && Game.getRound()%3 == 0) {
			board.resizeBoard(firstPlayer, secondPlayer);
		}
		
		// Increment round counter
		game.setRound(Game.getRound()+1);
		
		//If the board is 4x4 the game is over
		//Winner is the player with the biggest score
		if(board.getM() == 4 && board.getN() == 4) {
			// End game
			
			// Find winner
			Player winner = null;
			if(firstPlayer.getScore() > secondPlayer.getScore()) {
				winner = firstPlayer;
			} else if(firstPlayer.getScore() < secondPlayer.getScore()) {
				winner = secondPlayer;
			}
		
			
			
			// Print game status
			//System.out.printf("Game finished: total rounds %d, scores %d %d, winner is player %s", (game.getRound()-1), firstPlayer.getScore(), secondPlayer.getScore(), (winner!=null ? winner.getName() : "...actually, it was a tie"));
			stopThread=true;

		}
	
		

	}

	@Override
	public void render(Graphics g) {
		g.clearRect(0, 0, Display.width, Display.height);
		board.renderBoard(g);
		board.renderFood(g);
		board.renderTrap(g);
		board.renderWeapon(g);
		

		
		if(firstPlayer==player1) {
			board.renderPlayer(g, firstPlayer, Assets.player1);
			
			board.renderPlayer(g, secondPlayer, Assets.player2);
		}
		
		if(firstPlayer==player2) {
			board.renderPlayer(g, firstPlayer, Assets.player2);
			
			board.renderPlayer(g, secondPlayer, Assets.player1);
			
		}
		
		
	}
	
	//Function that sets which player plays first (it returns the player that plays first)
	public Player setTurns() {
		int turn=(int)(Math.random()*2);
		if(turn==0)
			return player1;
		else
			return player2;
	}


	//Function that returns a boolean variable and based on the returned value we decide whether to stop or not the thread
	public static boolean getStopThread() {
		return stopThread;
	}






	

	
}
