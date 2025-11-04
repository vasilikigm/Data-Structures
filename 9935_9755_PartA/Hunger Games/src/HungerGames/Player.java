package HungerGames;

public class Player {
	int id; 		//Player's id
	String name;	//Player's name
	Board board;	//The game's board
	int score;		/*Player's score
					  It is defined by the points the player gains (from eating supplies) or loses (from falling into traps without the proper weapon to deal with them)*/
	int x;			//Coordinate x of the board's tile where the player is located
	int y;			//Coordinate y of the board's tile where the player is located
	Weapon bow=new Weapon();		/*The player's weapon type bow
					  In case the player doesn't possess this specific weapon,it's value is null*/
	Weapon pistol=new Weapon();	/*The player's weapon type pistol
	  				  In case the player doesn't possess this specific weapon,it's value is null*/
	Weapon sword=new Weapon();	/*The player's weapon type sword
	 				  In case the player doesn't possess this specific weapon,it's value is null*/
					
	//Constructor No.1
	//Empty constructor
	Player(){
		
	}
	
	
	//Function that gets the player's id
	public int getId() {
		return id;
	}
	
	//Function that sets the player's id
	public void setId(int id) {
		this.id=id;
	}
	
	//Function that gets the player's name
	public String getName() {
		return name;
	}
	
	//Function that returns the player's name
	public void setName(String name) {
		this.name=name;
	}
	
	//Function that gets the player's score
	public int getScore() {
		return score;
	}
	
	//Function that returns the player's score
	public void setScore(int score) {
		this.score=score;
	}
	
	//Function that gets the coordinate x of the board's tile where the player is located
	public int getX() {
		return x;
	}
	
	//Function that sets the coordinate x of the board's tile where the player is located
	public void setX(int x) {
		this.x=x;
	}
	
	//Function that gets the coordinate y of the board's tile where the player is located
	public int getY() {
		return y;
	}
	
	//Function that sets the coordinate y of the board's tile where the player is located
	public void setY(int y) {
		this.y=y;
	}
	
	
	//--------------------------------------------------------------------------------------------
	
	
	//Function that  chooses randomly one on the player's available moves , and returns his new position in coordinates (x,y)
	public int[] getRandomMove(Board board){
		
		
		
		
		 int[] newPosition=new int[2];
		 // newPosition[0] stands for the coordinate x of his next position
		 // newPosition[1] stands for the coordinate y of his next position
		
		int dice;	//Variable which takes random values ranging from 1 to 8 and determines the direction of the player's new position
		
		//Setting the directions the player is allowed to move in order to remain inside the board

		if(getX()==(-board.getM_dimension()/2)){
			if(getY()==(-board.getN_dimension()/2)) {
				//if player's coordinates are(-M/2,-N/2)
				dice=(int)(Math.random()*3)+3;
			}
			else if(getY()==(board.getN_dimension()/2)) {
				//if player's coordinates are (-M/2,N/2)
				dice=(int)(Math.random()*3)+1;
			}
			else {
				//if player's coordinates are (-M/2,y) where y!=-N/2 and y!=N/2
				dice=(int)(Math.random()*5)+1;
			}
		}
		
		else if(getX()==(board.getM_dimension()/2)) {
			if(getY()==(-board.getN_dimension()/2)) {
				//if player's coordinates are (M/2,-N/2)
				dice=(int)(Math.random()*3)+5;
			}
			else if(getY()==(board.getN_dimension()/2)) {
				//if player's coordinates are (M/2,N/2)
				do {
					dice=(int)(Math.random()*8)+1;
				}while(dice!=1 && dice!=7 && dice!=8);
			}
			else {
				//if player's coordinates are (M/2,y) where y!=-N/2 and y!=N/2
				do {
					dice=(int)(Math.random()*8)+1;
				}while(dice==2 || dice ==3 || dice ==4);
			}
		}
		
		else if(getY()==(-board.getN_dimension()/2) && getX()!=(-board.getM_dimension()/2) && getX()!=(board.getM_dimension()/2)) {
			//if the player's coordinates are (x,-N/2) where x!=-M/2 and x!=M/2
			dice=(int)(Math.random()*5)+3;
		}
		
		else if(getY()==(board.getN_dimension()/2) && getX()!=(-board.getM_dimension()/2) && getX()!=(board.getM_dimension()/2)) {
			//if the player's coordinates are (x,N/2) where x!=-M/2 and x!=M/2
			do {
				dice=(int)(Math.random()*8)+1;
			}while(dice == 6 || dice == 5|| dice == 4 );
		}
		else {
			//if the player isn't located at the outer tiles(can move to any direction)
			dice=(int)(Math.random()*8)+1;
		}
		
		//Setting the position the player will take (when he makes his next move) based on the direction he is moving(which is determined by the value of the variable dice )  
		
		//If player's direction  is : up   --- (dice==1)
		if(dice==1) {
			
			if(getY()==1) {
				newPosition[0]=getX();
				newPosition[1]=getY()-2;	
				
			}
			else {
				newPosition[0]=getX();
				newPosition[1]=getY()-1;
			}
		}
		
		//If player's direction is : up and to the right  ---  (dice==2)
		else if(dice==2) {
			if(getX()==-1 && getY()==1) {
				
				newPosition[0]=getX()+2;
				newPosition[1]=getY()-2;
			}
			else if(getX()==-1) {
				
				newPosition[0]=getX()+2;
				newPosition[1]=getY()-1;
				
			}
			else if(getY()==1) {
				
				newPosition[0]=getX()+1;
				newPosition[1]=getY()-2;
			}
			else {
				
				newPosition[0]=getX()+1;
				newPosition[1]=getY()-1;
			}
		}
		
		//If player's direction is : right  ---  (dice==3)
		else if(dice==3) {
			if(getX()==-1) {
				newPosition[0]=getX()+2;
				newPosition[1]=getY();
			}
			else {
				newPosition[0]=getX()+1;
				newPosition[1]=getY();
			}
		}
		
		//If player's direction is : down and to the right  ---  (dice==4)
		else if(dice==4) {
			if(getX()==-1 && getY()==-1) {
				newPosition[0]=getX()+2;
				newPosition[1]=getY()+2;
			}
			else if(getX()==-1) {
				newPosition[0]=getX()+2;
				newPosition[1]=getY()+1;
			}
			else if(getY()==-1) {
				newPosition[0]=getX()+1;
				newPosition[1]=getY()+2;
			}
			else {
				newPosition[0]=getX()+1;
				newPosition[1]=getY()+1;
			}
		}
		
		//If player's direction is : down  ---  (dice==5)
		else if(dice==5) {
			if(getY()==-1) {
				newPosition[0]=getX();
				newPosition[1]=getY()+2;
			}
			else {
				newPosition[0]=getX();
				newPosition[1]=getY()+1;
			}
		}
		
		//If player's direction is : down and to the left  ---  (dice==6)
		else if(dice==6) {
			if(getX()==1 && getY()==-1) {
				newPosition[0]=getX()-2;
				newPosition[1]=getY()+2;
			}
			else if(getX()==1) {
				newPosition[0]=getX()-2;
				newPosition[1]=getY()+1;
			}
			else if(getY()==-1) {
				newPosition[0]=getX()-1;
				newPosition[1]=getY()+2;
			}
			else {
				newPosition[0]=getX()-1;
				newPosition[1]=getY()+1;
			}
		}
		
		//If player's direction is : left  ---  (dice==7)
		else if(dice==7) {
			if(getX()==1) {
				newPosition[0]=getX()-2;
				newPosition[1]=getY();
			}
			else {
				newPosition[0]=getX()-1;
				newPosition[1]=getY();
			}
		}
		
		//If player's direction is :up and to the left  ---  (dice==8)
		else if(dice==8) {
			if(getX()==1 && getY()==1) {
				newPosition[0]=getX()-2;
				newPosition[1]=getY()-2;
			}
			else if(getX()==1) {
				newPosition[0]=getX()-2;
				newPosition[1]=getY()-1;
			}
			else if(getY()==1) {
				newPosition[0]=getX()-1;
				newPosition[1]=getY()-2;
			}
			else {
				newPosition[0]=getX()-1;
				newPosition[1]=getY()-1;
				
			}
		}
		return newPosition;
	}
	
	
	/*Function that moves the player to his next location,makes some changes to the proper variables and returns an array which includes : the coordinates x,y of his new location, 
	  the number of weapons and supplies he got in his possession as well as the number of traps he fell into, by moving to this new location*/
	public int[] move(Board board) {
		
		int pin[]= new int [5];
		int[] r=new int [2];
		int w=0;		//Variable which stands for the number of weapons the player collected by moving to this position
		int t=0;		//Variable which stands for the number of traps the player collected by moving to this position
		int f=0;		//Variable which stands for the number of food the player collected by moving to this position
		r=getRandomMove(board);
		setX(r[0]);
		setY(r[1]);
		for(int i=0;i<board.getW();i++) {
			if(board.weapons[i].getX()==getX() && board.weapons[i].getY()==getY() && board.weapons[i].getPlayerId()==getId()) {
				w=1;
				System.out.println("Player"+id+" collected a weapon type : "+ board.weapons[i].getType());
				if(board.weapons[i].getType()=="pistol") {
					
					//Making the object pistol point into the same direction as the object weapons[i] ,since we have confirmed they represent the same object just by different names
					//This way the object pistol is initialized and the condition pistol==null is no longer valid (meaning the player now has this weapon in his possession)
					pistol=board.weapons[i];
				}
				else if(board.weapons[i].getType()=="sword") {
					
					//Making the object sword point into the same direction as the object weapons[i] ,since we have confirmed they represent the same object just by different names
					//This way the object sword is initialized and the condition sword==null is no longer valid (meaning the player now has this weapon in his possession)
					sword=board.weapons[i];
				}
				else if(board.weapons[i].getType()=="bow") {
					//Making the object bow point into the same direction as the object weapons[i] ,since we have confirmed they represent the same object just by different names
					//This way the object bow is initialized and the condition bow==null is no longer valid (meaning the player now has this weapon in his possession)
					pistol=board.weapons[i];
				}
				//Setting both coordinates of this object to 0 since it is no longer on the board(because the player has it now)
				board.weapons[i].setX(0);
				board.weapons[i].setY(0);
			}
		}
		for(int i=0;i<board.getF();i++) {
			if(board.food[i].getX()==getX() && board.food[i].getY()==getY()) {
				f=1;
				System.out.println("Player"+id+" collected a supply");
				setScore(getScore()+board.food[i].getPoints());
				//Setting both coordinates of this object to 0 since it is no longer on the board(because the player has it now)
				board.food[i].setX(0);
				board.food[i].setY(0);
			}
		}
		for(int i=0;i<board.getT();i++) {
			if(board.traps[i].getX()==getX() && board.traps[i].getY()==getY()) {
				t=1;
				System.out.println("Player"+id+" fell into a trap type :" +board.traps[i].getType());
				if(board.traps[i].getType()=="animal") {
					if(bow!=null) {
						//The player has the proper weapon to deal with the animal, therefore he doesn't lose points
					}
					else if(bow==null) {
						//The player doesn't a bow (which is the proper weapon to deal with an animal) , therefore he loses the proper amount of points
						setScore(getScore()+board.traps[i].getPoints());
					}
				}
				
				if(board.traps[i].getType()=="ropes") {
					if(sword!=null) {
						//The player has the proper weapon to deal with the rope, therefore he doesn't lose points
					}
					else if(bow==null) {
						//The player doesn't a sword (which is the proper weapon to deal with a rope) , therefore he loses the proper amount of points
						setScore(getScore()+board.traps[i].getPoints());
					}
				}
			}
		}
		pin[0]=getX();
		pin[1]=getY();
		pin[2]=w;
		pin[3]=f;
		pin[4]=t;
		
		return pin;
	}
}
