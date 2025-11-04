//Παπαποστόλου Βασιλική
//	ΑΕΜ:9935
//	Ηλεκτρονική διεύθυνση: vkpapapo@ece.auth.gr
//	Τηλέφωνο:6941435566
//Γκούμα Βασιλική  
//	ΑΕΜ : 9755  
//	Ηλεκτρονική διεύθυνση: vasilikiig@ece.auth.gr
//	Τηλέφωνο:6985822621






package HungerGames;




public class Game {

	int round;
	
	Game(){
		
	}
	
	public int getRound() {
		return round;
	}
	
	public void setRound(int round) {
		this.round=round;
	}
	
	
	public static void main(String[] args) {
		System.out.println("\n\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~THE GAME BEGINS~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n\n");
		Player player1=new Player();
		Player player2=new Player();
		Game game=new Game();
		Board board12=new Board(20,20,6,10,8);
		
		int[] next_move=new int[5];
		int resize;
		board12.setWeaponAreaLimits(-2, -2, 2, -2, -2, 2, 2, 2);
		board12.setFoodAreaLimits(-3, -3, 3, -3, -3, 3, 3, 3);
		board12.setTrapAreaLimits(-4, -4, 4, -4, -4, 4, 4, 4);
		
		player1.setId(1);
		player1.setName("player1");
		player1.setScore(0);
		//Positioning player1 to the top left corner
		player1.setX((-board12.getM_dimension())/2);
		player1.setY((-board12.getN_dimension())/2);
		
		player2.setId(2);
		player2.setName("player2");
		player2.setScore(0);
		//Positioning player2 to the bottom right corner
		player2.setX(board12.getM_dimension()/2);
		player2.setY(board12.getN_dimension()/2);
		
		board12.createBoard();
		
		game.setRound(1);
		for(;;) {
			
			
			System.out.println("\n--------------------Round : "+game.getRound()+"--------------------\n");
			
			//Printing the board
			for(int i=0;i<board12.getN_dimension();i++) {
				for(int j=0;j<board12.getM_dimension();j++) {
					System.out.print(board12.getStringRepresentation()[i][j]+"\t");
				}
				System.out.println("\n");
			}
			System.out.println();
			
			//Player1 moves
			
			next_move=player1.move(board12);
			System.out.println("Player1 : \nCoordinate x : "+next_move[0]+"   Coordinate y : "+next_move[1]);
			
			//Player2 moves
			for(int i=0;i<5;i++) {
				next_move[i]=player2.move(board12)[i];
			}
			System.out.println("Player1 : \nCoordinate x : "+next_move[0]+"   Coordinate y : "+next_move[1]);
			
			resize=game.getRound();
			System.out.println(resize/3);
			
			board12.resizeBoard(player1, player2);
				
			if(board12.getM_dimension()==4 && board12.getN_dimension()==4) {
				break;
			}
			
			game.setRound(game.getRound()+1);
			
		}
		System.out.println("\n\n\n***********************************************************************************");
		System.out.println("Player1 score : "+player1.getScore()+"\tPlayer2 score : "+player2.getScore());
		if(player1.getScore()>player2.getScore()) {
			System.out.println("Player1 is the winner of this game!!!!!");
		}
		else if(player1.getScore()<player2.getScore()) {
			System.out.println("Player2 is the winner of this game!!!!!");
		}
		else if(player1.getScore()==player2.getScore()) {
			System.out.println("It's a tie!!");
		}
	}
}
