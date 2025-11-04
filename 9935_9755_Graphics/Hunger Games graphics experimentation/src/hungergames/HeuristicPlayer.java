package hungergames;
//Παπαποστόλου Βασιλική
//	ΑΕΜ:9935
//	Ηλεκτρονική διεύθυνση: vkpapapo@ece.auth.gr
//	Τηλέφωνο:6941435566
//Γκούμα Βασιλική  
//	ΑΕΜ : 9755  
//	Ηλεκτρονική διεύθυνση: vasilikiig@ece.auth.gr
//	Τηλέφωνο:6985822621



import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HeuristicPlayer extends Player{
	
	private ArrayList <Integer[]> path = new ArrayList <Integer[]>();
	
	static int r; //Variable that determines the heuristic player's field of view.
	private int d;	//If the distance between the two players is less than d ,a player owns a pistol and it is his/hers turn he/she can kill the opponent.
	private int round;	//Variable that gets the value of the game's round.
	 
	//Constructor No.1
	HeuristicPlayer(){
		super();
		//--------------==============================---------------------------==========================-------------------------================
	}
	
	//Constructor No.2
	HeuristicPlayer(int id, String name, Board board, int score, int x, int y, Weapon bow, Weapon pistol, Weapon sword,ArrayList <Integer[]> path,int d,int round){
		super(id,name,board,score,x,y,bow,pistol,sword);
		this.path=path;
	}
	
	//Function that returns the game's round.
	public int getRound() {
		return round;
	}
	
	//Function that sets the game's round.
	public void setRound(int round) {
		this.round=round;
	}
	
	//Function that returns the variable d.
	//If the distance between the two players is less than d ,a player owns a pistol and it is his/hers turn he/she can kill the opponent.
	public int getD() {
		return d;
	}
	
	//Function that sets the variable d.
	//If the distance between the two players is less than d ,then if a player owns a pistol and it is his/hers turn he/she can kill the opponent.
	public void setD(int d) {
		this.d=d;
	}
	
	//Function that returns the distance between the two players
	public float playersDistance(Player p) {
		double d;
		if(Math.abs(p.getX()-getX())<=r && Math.abs(p.getY()-getY())<=r ) {
			d=(p.getX()-getX())*(p.getX()-getX()) + (p.getY()-getY())*(p.getY()-getY());
			return (float)Math.sqrt(d);
		}
		else {
			return -1;
		}
			
	}
	
	
	//Function that evaluates each possible move the heuristic player can make.
	public double evaluate(int dice,Player player) {
		double evaluation=0;
		int x=0;
		int y=0;
		
		
		double kill=0;		//Variable that gets a value other than 0 if by moving to this tile the heuristic player reduces the distance between him/her and the opponent so that he/she is closer in killing the other player.
		double protection=0;	//Variable that gets a value other than 0 if by moving to this tile the heuristic player protects himself/herself from being killed by the opponent.
		double getThePistol=0;	//Variable that gets a value other than 0 if by moving to this tile the distance between the heuristic player and the pistol assigned to him/her is reduced.
		double getTheBow=0;		//Variable that gets a value other than 0 if by moving to this tile the distance between the heuristic player and the bow assigned to him/her is reduced.
		double getTheSword=0;		//Variable that gets a value other than 0 if by moving to this tile the distance between the heuristic player and the sword assigned to him/her is reduced.
		double getNowPistol=0;	//Variable that gets a value other than 0 if by moving to this tile the heuristic player is located at the same tile as the pistol assigned to him/her(he/she can acquire it in case it isn't in his/her possession).	
		double getNowBow=0;	//Variable that gets a value other than 0 if by moving to this tile the heuristic player is located at the same tile as the bow assigned to him/her(he/she can acquire it in case it isn't in his/her possession).
		double getNowSword=0;	//Variable that gets a value other than 0 if by moving to this tile the heuristic player is located at the same tile as the sword assigned to him/her(he/she can acquire it in case it isn't in his/her possession).	
		double fallIntoTraps=0;	//Variable that gets a value other than 0 if by moving to this tile, the heuristic player is located at the same tile as a trap.
		double getPoints=0;	//Variable that gets a value other than 0 if by moving to this tile the heuristic player is located at the same tile as a supply.
		double findPoints=0;	//Variable that gets a value other than 0 if by moving to this tile the heuristic player is located closer to a supply.
		double moveToCenter=0;		//Variable that gets a value other than 0 if by moving to this tile the heuristic player gets closer to the center of the board.
		double moveToPlayerFirstPosition=0;	//Variable that gets a value other than 0 if by moving to this tile the heuristic player gets closer to the opponent's first position.
		
		// Find next tile
		int moves[][] = {
				{0,-1},
				{1,-1},
				{1,0},
				{1,1},
				{0,1},
				{-1,1},
				{-1,0},
				{-1,-1},
		};
		x = getX() + moves[dice-1][0];
		y = getY() + moves[dice-1][1];
		
		// Skip row 0 and column 0
		if(x == 0) {
			x += moves[dice-1][0];
		}
		if(y == 0) {
			y += moves[dice-1][1];
		}
		if(!getBoard().isPositionValid(x, y)) {
			return -1000.0;
		}
			
		
		
		
		
		if(getBow()==null || getSword()==null) {
			if(getBow()==null && getSword()==null) {
				for(int i=0;i<getBoard().getT();i++) {
					if(getBoard().getTraps()[i].getX()==x && getBoard().getTraps()[i].getY()==y) {
						fallIntoTraps=getBoard().getTraps()[i].getPoints();
					}
				}
			}
			else if(getBow()==null) {
				for(int i=0;i<getBoard().getT();i++) {
					if(getBoard().getTraps()[i].getX()==x && getBoard().getTraps()[i].getY()==y && getBoard().getTraps()[i].getType()=="animals") {
						fallIntoTraps=getBoard().getTraps()[i].getPoints();
					}
				}
			}
			else if(getSword()==null) {
				for(int i=0;i<getBoard().getT();i++) {
					if(getBoard().getTraps()[i].getX()==x && getBoard().getTraps()[i].getY()==y && getBoard().getTraps()[i].getType()=="ropes") {
						fallIntoTraps=getBoard().getTraps()[i].getPoints();
					}
				}
			}
		}
		else if(getBow()!=null && getSword()!=null) {
			fallIntoTraps=0;
		}
		
		
		if(getBow()==null) {
			for(int i=0;i<getBoard().getW();i++) {
				if(getBoard().getWeapons()[i].getX()==x && getBoard().getWeapons()[i].getY()==y && getId()==getBoard().getWeapons()[i].getPlayerId()) {
					if(getBoard().getWeapons()[i].getType()=="bow") {
						getNowBow=1;
					}
				}
				else if(getId()==getBoard().getWeapons()[i].getPlayerId() && getBoard().getWeapons()[i].getType()=="bow") {
					if(Math.abs(getBoard().getWeapons()[i].getX()-getX())<=r && Math.abs(getBoard().getWeapons()[i].getY()-getY())<=r) {
						getTheBow = Math.sqrt( (getBoard().getWeapons()[i].getX()-getX())*(getBoard().getWeapons()[i].getX()-getX()) + (getBoard().getWeapons()[i].getY()-getY())*(getBoard().getWeapons()[i].getY()-getY()))   -   (Math.sqrt( (getBoard().getWeapons()[i].getX()-x)*(getBoard().getWeapons()[i].getX()-x) + (getBoard().getWeapons()[i].getY()-y)*(getBoard().getWeapons()[i].getY()-y) ));
						
					}
				}
			}
		}
		if(getSword()==null) {
			for(int i=0;i<getBoard().getW();i++) {
				if(getBoard().getWeapons()[i].getX()==x && getBoard().getWeapons()[i].getY()==y && getId()==getBoard().getWeapons()[i].getPlayerId()) {
					if(getBoard().getWeapons()[i].getType()=="sword") {
						getNowSword=1;
					}
				}
				else if(getId()==getBoard().getWeapons()[i].getPlayerId() && getBoard().getWeapons()[i].getType()=="sword") {
					if(Math.abs(getBoard().getWeapons()[i].getX()-getX())<=r && Math.abs(getBoard().getWeapons()[i].getY()-getY())<=r) {
						getTheSword=Math.sqrt( (getBoard().getWeapons()[i].getX()-getX())*(getBoard().getWeapons()[i].getX()-getX()) + (getBoard().getWeapons()[i].getY()-getY())*(getBoard().getWeapons()[i].getY()-getY()))   -   (Math.sqrt( (getBoard().getWeapons()[i].getX()-x)*(getBoard().getWeapons()[i].getX()-x) + (getBoard().getWeapons()[i].getY()-y)*(getBoard().getWeapons()[i].getY()-y) ));
						
					}
				}
			}
		}
		
		
		for(int i=0;i<getBoard().getF();i++) {
			if(getBoard().getFood()[i].getX()==x && getBoard().getFood()[i].getY()==y) {
				getPoints=getBoard().getFood()[i].getPoints();
			}
		}
		
		for(int i=0;i<getBoard().getF();i++) {
			if(Math.abs(getBoard().getFood()[i].getX()-getX())<=r && Math.abs(getBoard().getFood()[i].getY()-getY())<=r && getPoints==0) {
				if(Math.abs(getBoard().getFood()[i].getX()-getX())<=2 && Math.abs(getBoard().getFood()[i].getY()-getY())<=2 ) {
					findPoints=2*getBoard().getFood()[i].getPoints();
				}
				else if(Math.abs(getBoard().getFood()[i].getX()-getX())<=3 && Math.abs(getBoard().getFood()[i].getY()-getY())<=3) {
					findPoints=1*getBoard().getFood()[i].getPoints();

				}
				
				
			}
			
		}
		
		
		
		
		//-----------------------------------------------------------------------------------------------------------
		//if the player has a pistol
		if(getPistol()!=null) {
			//if the opponent also has a pistol
			if(player.getPistol()!=null) {
				if(playersDistance(player)!=-1) {
					if(Math.sqrt( (player.getX()-x)*(player.getX()-x) + (player.getY()-y)*(player.getY()-y))<d) {
						protection=7-Math.sqrt( (player.getX()-x)*(player.getX()-x) + (player.getY()-y)*(player.getY()-y));
					}
					else if((Math.abs(player.getX()-x)==3 && Math.abs(player.getY()-y)<=3)  ||  (Math.abs(player.getX()-x)<=3 && Math.abs(player.getY()-y)==3)) {
						protection=5-Math.sqrt( (player.getX()-x)*(player.getX()-x) + (player.getY()-y)*(player.getY()-y));
					}
					kill=playersDistance(player) - (Math.sqrt(( (x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()) )));
					
				}
				else if(playersDistance(player)==-1 && round>=1) {
					if((Math.abs(path.get(round-1)[8]-getX())==3 && Math.abs(path.get(round-1)[9]-getY())<=3)  ||  (Math.abs(path.get(round-1)[8]-getX())<=3 && Math.abs(path.get(round-1)[9]-getY())==3)) {
						if(Math.sqrt( (path.get(round-1)[8]-getX())*(path.get(round-1)[8]-getX())  +  (path.get(round-1)[9]-getY())*(path.get(round-1)[9]-getY()) )==Math.sqrt(18)) {
							if(Math.sqrt( (path.get(round-1)[8]-x)*(path.get(round-1)[8]-x)  +  (path.get(round-1)[9]-y)*(path.get(round-1)[9]-y) )==Math.sqrt(8)) {
								protection=1.0;
							}
						}
						else if(Math.sqrt( (path.get(round-1)[8]-getX())*(path.get(round-1)[8]-getX())  +  (path.get(round-1)[9]-getY())*(path.get(round-1)[9]-getY()) )==3) {
							if(Math.sqrt( (path.get(round-1)[8]-x)*(path.get(round-1)[8]-x)  +  (path.get(round-1)[9]-y)*(path.get(round-1)[9]-y) )==2) {
								protection=1.0;
							}
						}
						else if(Math.sqrt( (path.get(round-1)[8]-getX())*(path.get(round-1)[8]-getX())  +  (path.get(round-1)[9]-getY())*(path.get(round-1)[9]-getY()) )==Math.sqrt(13)) {
							if(Math.sqrt( (path.get(round-1)[8]-x)*(path.get(round-1)[8]-x)  +  (path.get(round-1)[9]-y)*(path.get(round-1)[9]-y) )==Math.sqrt(5)) {
								protection=1.0;
							}
						}
						else if(Math.sqrt( (path.get(round-1)[8]-getX())*(path.get(round-1)[8]-getX())  +  (path.get(round-1)[9]-getY())*(path.get(round-1)[9]-getY()) )==Math.sqrt(10)) {
							if(Math.sqrt( (path.get(round-1)[8]-x)*(path.get(round-1)[8]-x)  +  (path.get(round-1)[9]-y)*(path.get(round-1)[9]-y) )==2) {
								protection=1.0;
							}
						}
						
						
					}
				}
				moveToCenter=( Math.sqrt(getX()*getX()+ getY()*getY()) - Math.sqrt(x*x+y*y) );

				
				
				
				evaluation = 100*protection +  40*kill + 2*getPoints + fallIntoTraps + 15*getNowSword + 15*getNowBow + 0.9*getTheSword + 0.9*getTheBow+findPoints+moveToCenter;
				
			
			}
			
			//---------------------------------------------------------------------------------------------------------------------
			//if the opponent doesn't have a pistol
			else if(player.getPistol()==null) {
				if(playersDistance(player)!=-1) {
					kill=playersDistance(player) - (Math.sqrt(( (x-player.getX())*(x-player.getX()) + (y-player.getY())*(y-player.getY()) )));
				}
				
				
				if(playersDistance(player)!=-1) {
					if(Math.sqrt( (player.getX()-x)*(player.getX()-x) + (player.getY()-y)*(player.getY()-y))<d) {
						protection=10-Math.sqrt( (player.getX()-x)*(player.getX()-x) + (player.getY()-y)*(player.getY()-y));
					}
				}
				
				//Checking if the heuristic player prevents the other player from getting his pistol.
				for(int i=0;i<getBoard().getW();i++) {
					if( Math.abs(getBoard().getWeapons()[i].getX()-getX()) <=r && Math.abs(getBoard().getWeapons()[i].getY()-getY()) <=r && getBoard().getWeapons()[i].getPlayerId()==player.getId() && getBoard().getWeapons()[i].getType()=="pistol") {
						if(playersDistance(player)==-1) {
							moveToPlayerFirstPosition = Math.sqrt((getX()-getBoard().getM()/2)*(getX()-getBoard().getM()/2)+ (getY()-getBoard().getN()/2)*(getY()-getBoard().getN()/2)) - Math.sqrt((x-getBoard().getM()/2)*(x-getBoard().getM()/2)+(y-getBoard().getN()/2)*(y-getBoard().getN()/2) );

						}						
					}
				}
				
				moveToCenter=( Math.sqrt(getX()*getX()+ getY()*getY()) - Math.sqrt(x*x+y*y) );

				
				
				
				evaluation = 100*kill +40*moveToPlayerFirstPosition+ 2*getPoints + fallIntoTraps + 15*getNowSword + 15*getNowBow + 0.9*getTheSword + 0.9*getTheBow+findPoints+0.5*moveToCenter;

			}
		}
		
		
		
		
		
		//--------------------------------------------------------------------------------------------------------------------------------
		//if the player doesn't have a pistol
		else if(getPistol()==null) {
			//if the opponent has a pistol
			if(player.getPistol()!=null) {
				for(int i=0;i<getBoard().getW();i++) {
					if(getBoard().getWeapons()[i].getX()==x && getBoard().getWeapons()[i].getY()==y && getId()==getBoard().getWeapons()[i].getPlayerId()) {
						if(getBoard().getWeapons()[i].getType()=="pistol") {
							getNowPistol=1.0;
						}
					}
					if(getId()==getBoard().getWeapons()[i].getPlayerId() && getBoard().getWeapons()[i].getType()=="pistol") {
						if(Math.abs(getBoard().getWeapons()[i].getX()-getX())<=r && Math.abs(getBoard().getWeapons()[i].getY()-getY())<=r) {
							getThePistol=(Math.sqrt( (getBoard().getWeapons()[i].getX()-getX())*(getBoard().getWeapons()[i].getX()-getX()) + (getBoard().getWeapons()[i].getY()-getY())*(getBoard().getWeapons()[i].getY()-getY())))   -   (Math.sqrt( (getBoard().getWeapons()[i].getX()-x)*(getBoard().getWeapons()[i].getX()-x) + (getBoard().getWeapons()[i].getY()-y)*(getBoard().getWeapons()[i].getY()-y) ));
							
						}
					}
				}
				
				moveToCenter=( Math.sqrt(getX()*getX()+ getY()*getY()) - Math.sqrt(x*x+y*y) );
				
				
				
				
				if(playersDistance(player)!=-1) {
					if(Math.sqrt((player.getX()-x)*(player.getX()-x) + (player.getY()-y)*(player.getY()-y)) > Math.sqrt(8) ) {
						protection=1.0;
					}
				}
				else if(playersDistance(player)==-1) {
					protection=1.0;
				}
				
				
				
				
				evaluation = 25*moveToCenter+ 350*protection + getPoints + fallIntoTraps + 9*getNowSword + 9*getNowBow + 0.9*getTheSword + 0.9*getTheBow + 150*getNowPistol + 51*getThePistol+findPoints;
			}
			
			//-------------------------------------------------------------------------------------------------------------
			//if the opponent doesn't have a pistol
			else if(player.getPistol()==null) {
				
				for(int i=0;i<getBoard().getW();i++) {
					if(getBoard().getWeapons()[i].getX()==x && getBoard().getWeapons()[i].getY()==y && getId()==getBoard().getWeapons()[i].getPlayerId()) {
						if(getBoard().getWeapons()[i].getType()=="pistol") {
							getNowPistol=1.0;
						}
					}
					else if(getId()==getBoard().getWeapons()[i].getPlayerId() && getBoard().getWeapons()[i].getType()=="pistol") {
						if(Math.abs(getBoard().getWeapons()[i].getX()-getX())<=r && Math.abs(getBoard().getWeapons()[i].getY()-getY())<=r) {
							getThePistol=(Math.sqrt( (getBoard().getWeapons()[i].getX()-getX())*(getBoard().getWeapons()[i].getX()-getX()) + (getBoard().getWeapons()[i].getY()-getY())*(getBoard().getWeapons()[i].getY()-getY())))   -   (Math.sqrt( (getBoard().getWeapons()[i].getX()-x)*(getBoard().getWeapons()[i].getX()-x) + (getBoard().getWeapons()[i].getY()-y)*(getBoard().getWeapons()[i].getY()-y) ));
						}
					}
				}
			
				
				moveToCenter=( Math.sqrt(getX()*getX()+ getY()*getY()) - Math.sqrt(x*x+y*y) );
				
				for(int i=0;i<getBoard().getW();i++) {
					if(getBoard().getWeapons()[i].getPlayerId()==player.getId() && Math.abs(getBoard().getWeapons()[i].getX()-x) <=r && Math.abs(getBoard().getWeapons()[i].getY()-y) <=r) {
						if(playersDistance(player)!=-1) {
							if((Math.abs(player.getX()-getBoard().getWeapons()[i].getX())==2 && Math.abs(player.getY()-getBoard().getWeapons()[i].getY())<=2)  ||  (Math.abs(player.getX()-getBoard().getWeapons()[i].getX())<=2 && Math.abs(player.getY()-getBoard().getWeapons()[i].getY())==2)) {
								if(Math.abs(getBoard().getWeapons()[i].getX()-x)>=1 || Math.abs(getBoard().getWeapons()[i].getY()-y)>=1 ) {
									protection=1.0;
								}
							}
							else if((Math.abs(player.getX()-getBoard().getWeapons()[i].getX())==1 && Math.abs(player.getY()-getBoard().getWeapons()[i].getY())<=1)  ||  (Math.abs(player.getX()-getBoard().getWeapons()[i].getX())<=1 && Math.abs(player.getY()-getBoard().getWeapons()[i].getY())==1)) {
								if(Math.abs(getBoard().getWeapons()[i].getX()-x)>=2 || Math.abs(getBoard().getWeapons()[i].getY()-y)>=2 ) {
									protection=1.0;
								}
							}
							
						}
					}
				}
				
				
				
			
				evaluation= 25*moveToCenter+ 350*protection + getPoints + fallIntoTraps + 9*getNowSword + 9*getNowBow + 0.9*getTheSword + 0.9*getTheBow + 150*getNowPistol + 51*getThePistol+findPoints;
			
			}
			
		}
		return evaluation;
	}
	
	
	
	
	//Function that checks if the distance between the two players is less than d.In that case it is the player's turn and the player has a pistol it returns true.
	public static boolean kill(Player player1,Player player2,int d) {
		boolean result=false;
		if(Math.sqrt( (player1.getX()-player2.getX())*(player1.getX()-player2.getX()) + (player1.getY()-player2.getY())*(player1.getY()-player2.getY()))<d){
			if(player1.getPistol()!=null) {
				result=true;
			}
		}
		return result;
	}
	
	
	
	//Function that chooses and returns the heuristic player's move based on return result of the function :public double evaluate(int dice,Player player)
	//This function also renews the the variable path.
	
	int[] move(Player p) {
		Map<Integer,Double> rating= new HashMap <Integer,Double>();
		int dice=0;
		int newPoints=0;
		int eatFood=0;
		int fallTrap=0;
		int takeWeapon=0;
		int typePistol=0;
		int typeBow=0;
		int typeSword=0;
		double bestMove;
		int[] nextPosition=new int[2];
		//nextPosition[0]	--Coordinate x of the player's new position--
		//nextPosition[1]	--Coordinate y of the player's new position--
		Weapon w = null;
		Food f = null;
		Trap t = null;
		
		//Evaluating each possible move and storing the results.
		for(int i=1;i<=8;i++) {
			rating.put(i,evaluate(i,p));
		}
		bestMove=rating.get(1);
		dice=1;
		
		//selecting the player's best move
		for(int i=2;i<=8;i++) {
			if(rating.get(i)>bestMove) {
				bestMove=rating.get(i);	
				dice=i;
			}
		}
		
	
		// Find next tile
				int moves[][] = {
						{0,-1},
						{1,-1},
						{1,0},
						{1,1},
						{0,1},
						{-1,1},
						{-1,0},
						{-1,-1},
				};
				nextPosition[0] = getX() + moves[dice-1][0];
				nextPosition[1] = getY() + moves[dice-1][1];
				
				// Skip row 0 and column 0
				if(nextPosition[0] == 0) {
					nextPosition[0] += moves[dice-1][0];
				}
				if(nextPosition[1] == 0) {
					nextPosition[1] += moves[dice-1][1];
				}
		
		
		// Check if there is a weapon on the new tile
		
		for(int i=0; i<getBoard().getWeapons().length; i++) {
			if(getBoard().getWeapons()[i].getX() == nextPosition[0] && 
				getBoard().getWeapons()[i].getY() == nextPosition[1] &&
				getBoard().getWeapons()[i].getPlayerId() == getId() ) {
				w = getBoard().getWeapons()[i];
				System.out.println("Found a weapon");
				
				takeWeapon=1;
				if(w.getType() == "sword") {
					setSword(w);
					typeSword=1;
				} else if(w.getType() == "bow") {
					setBow(w);
					typeBow=1;
				} else if(w.getType() == "pistol") {
					setPistol(w);
					typePistol=1;
				}
				w.setX(0);
				w.setY(0);
			}
		}
		
		// Check if there is food on the new tile
		for(int i=0; i<getBoard().getFood().length; i++) {
			if(getBoard().getFood()[i].getX() == nextPosition[0] && getBoard().getFood()[i].getY() == nextPosition[1]) {
				f = getBoard().getFood()[i];
				System.out.println("Found food");
				eatFood=1;
				setScore(getScore()+f.getPoints());
				newPoints=f.getPoints();
				f.setX(0);
				f.setY(0);
			}
		}
		
		// Check if there is a trap on the new tile
		for(int i=0; i<getBoard().getTraps().length; i++) {
			if(getBoard().getTraps()[i].getX() == nextPosition[0] && getBoard().getTraps()[i].getY() == nextPosition[1]) {
				t = getBoard().getTraps()[i];
				fallTrap=1;
				boolean avoided = false;
				if(t.getType() == "ropes") {
					if(getSword() != null) {
						avoided = true;
					}
				} else if(t.getType() == "animals") {
					if(getBow() != null) {
						avoided = true;
					}
				}
				if(!avoided) {
					System.out.printf("Player %d (Trap) | prevscore %d, newscore %d\n", getId(), getScore(), getScore()+t.getPoints());
					setScore(getScore()+t.getPoints());
					newPoints=t.getPoints();
				} else {
					System.out.printf("Player %d (Trap) | successfully avoided", getId());
				}
			}
		}
				
		setX(nextPosition[0]);
		setY(nextPosition[1]);
		
		
		path.add(new Integer[] {dice,newPoints,eatFood,fallTrap,takeWeapon,typePistol,typeBow,typeSword,p.getX(),p.getY()});
		
		return nextPosition;
	}
	
	
	
	//Function that prints the information of the heuristic player's move.
	public void statistics(){
		
		System.out.println("Round : "+round+"\tPlayer(heuristic player) : "+getName());
		System.out.println("The dice is : "+path.get(round)[0]);
		
		System.out.print("The player got"+path.get(round)[1]+" points");
		if(path.get(round)[2]==1) {
			System.out.print(" ,ate 1 supply");
		}
		if(path.get(round)[3]==1) {
			System.out.print(" ,fell into 1 trap");
		}
		else if(path.get(round)[3]==0){
			System.out.print(" ,fell into 0 traps");
		}
		if(path.get(round)[4]==1) {
			System.out.print(" ,got a weapon ");
			if(path.get(round)[5]==1) {
				System.out.println("type pistol!");
			}
			else if(path.get(round)[6]==1) {
				System.out.println("type bow!");
			}
			else if(path.get(round)[7]==1) {
				System.out.println("type sword!");
			}
		}
		else {
			System.out.println(".");
		}
		
	}
	
	
	
	//=========FUNCTIONS TICK & RENDER============
	
	
		public void tick() {
			
		}
		
		public void render(Graphics g) {
			
		}
}


	
	
	

