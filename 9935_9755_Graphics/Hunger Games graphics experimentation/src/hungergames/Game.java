package hungergames;
import java.util.ArrayList;


public class Game {
	private static int round;

	public Game(int round) {
		this.round = round;
	}

	public Game() {
		round = 0;
	}

	public static int getRound() {
		return round;
	}

	public void setRound(int round) {
		Game.round = round;
	}

	public static void main(String[] args) {
		
		Game game = new Game();
		// Initialize board
		int N = 10;
		Board board = new Board(N, N, 6, 10, 8);
		// Initialize board limits
		int[][] wal = {
				{-2,-2},
				{2,-2},
				{2,2},
				{-2,2},
			};
		int[][] fal = {
				{-3,-3},
				{3,-3},
				{3,3},
				{-3,3},
		};
		int[][] tal = {
				{-4,-4},
				{4,-4},
				{4,4},
				{-4,4},
			};
		
		board.setWeaponAreaLimits(wal);
		board.setFoodAreaLimits(fal);
		board.setTrapAreaLimits(tal);
		
		// Generate random board
		board.createBoard();
		HeuristicPlayer.r=3;
		
		
		ArrayList<Integer[]>path=new ArrayList<Integer[]>();
		
		
		// Initialize players
		
		//=====================================Graphics======================================
		
		new Display(board,"Hunger Games Graphics !!",680,680);
		
		//=====NOT NEEDED CODE!!======
		//PARTS  OF THIS CODE HAVE BEEN TRANSFERED TO OUR GameState CLASS
		
		// Game loop
		
		/*int maxRounds = 10000; // Just for safety
		while(maxRounds-- >= 0) {
			System.out.println("======= Round " + game.round + "===========");
			heuristicPlayer.setRound(game.round);
			// Make each player move
			heuristicPlayer.move(player);
			heuristicPlayer.statistics();
			if(HeuristicPlayer.kill(heuristicPlayer, player,2)==true){
				//end game
				//winner heuristicPlayer
				System.out.println("Game finished : total rounds "+game.round+"  heuristicPlayer score "+heuristicPlayer.getScore()+"  player score "+player.getScore()  +" --- heuristicPlayer Killed player");
				System.out.println("HeuristicPlayer is the winner!!!!");
				break;
			}
			else if(HeuristicPlayer.kill(player, heuristicPlayer,2)==true) {
				//end game
				//winner player
				System.out.println("Game finished : total rounds "+game.round+"  heuristicPlayer score "+heuristicPlayer.getScore()+"  player score "+player.getScore()  +" --- player Killed heuristicPlayer");
				System.out.println("Player is the winner!!!!");
				break;
			}
			player.move();
			if(HeuristicPlayer.kill(player, heuristicPlayer,2)==true) {
				//end game
				//winner player
				System.out.println("Game finished : total rounds "+game.round+"  heuristicPlayer score "+heuristicPlayer.getScore()+"  player score "+player.getScore()  +" --- player Killed heuristicPlayer");
				System.out.println("Player is the winner!!!!");
				break;
			}
			else if(HeuristicPlayer.kill(heuristicPlayer, player,2)==true){
				//end game
				//winner heuristicPlayer
				System.out.println("Game finished : total rounds "+game.round+"  heuristicPlayer score "+heuristicPlayer.getScore()+"  player score "+player.getScore()  +" --- heuristicPlayer Killed player");
				System.out.println("HeuristicPlayer is the winner!!!!");
				break;
			}

			// Resize board
			if(game.round != 0 && game.round%3 == 0) {
				board.resizeBoard(heuristicPlayer, player);
			}
			
			// Increment round counter
			game.round += 1;
			
			// Get board as string array to print
			String ss[][] = board.getStringRepresentation();
			
			// Add player positions on the board
			ss[board.x2i(heuristicPlayer.getX())][board.y2j(heuristicPlayer.getY())] = "  P0";
			ss[board.x2i(player.getX())][board.y2j(player.getY())] = "  P1";			
			
			// Print board
			for(int i=0; i<ss.length; i++) {
				for(int j=0; j<ss[0].length; j++) {
					System.out.print(ss[i][j]);
					System.out.print("|");
				}
				System.out.print("\n");
			}
			
			// Check end game conditions
			if(board.getM() == 4 && board.getN() == 4) {
				// End game
				
				// Find winner
				Player winner = null;
				if(player.getScore() > heuristicPlayer.getScore()) {
					winner = player;
				} else if(player.getScore() < heuristicPlayer.getScore()) {
					winner = heuristicPlayer;
				}
			
				
				
				// Print game status
				System.out.printf("Game finished: total rounds %d, scores %d %d, winner is player %s", (game.round-1), heuristicPlayer.getScore(), player.getScore(), (winner!=null ? winner.getName() : "...actually, it was a tie"));
				break;
			}
		}*/
	}
	

}



