package HungerGames;

public class Board {
	int N,M;	//Dimensions of the board NxM (N for number of rows and M for number of columns)
	int W;		//Number of weapons at the board
	int F;		//Number of supplies at the board
	int T;		//Number of traps at the board
	
	int[][] weaponAreaLimits=new int [4][2];	/* A two dimensional array 4x2 which includes the coordinates (x,y) which mark
	 											   the limits of the board's area where weapons are located */
	int[][] foodAreaLimits=new int [4][2];		/* A two dimensional array 4x2 which includes the coordinates (x,y) of the corner
	 											   tiles of the board's area where supplies are located*/
	int[][] trapAreaLimits=new int [4][2];		/* A two dimensional array 4x2 which includes the coordinates (x,y) of the corner
	   											   tiles of the board's area where traps are located*/
	Weapon[] weapons;
	
	Trap[] traps;
	Food[] food;	
	
	
	
	
	//Constructor No.1
	//Empty constructor
	Board() {
		
	}
	
	//Constructor No.2
	Board(int N, int M, int W, int F,int T){
		this.N=N;
		this.M=M;
		this.W=W;
		this.F=F;
		this.T=T;
	}
	
	//Constructor No.3
	//Initializes the variables of the object that is created, with the variables of another object of this class
	Board(Board board){
		N=board.N;
		M=board.M;
		W=board.W;
		F=board.F;
		T=board.T;
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<2;j++) {
				weaponAreaLimits[i][j]=board.weaponAreaLimits[i][j];
			}
		}
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<2;j++) {
				foodAreaLimits[i][j]=board.foodAreaLimits[i][j];
			}
		}
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<2;j++) {
				trapAreaLimits[i][j]=board.trapAreaLimits[i][j];
			}
		}
		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++===============================================================
		
		for(int i=0;i<W;i++) {
			weapons[i]=board.weapons[i];
			/*weapons[i].setId(board.weapons[i].getId());
			weapons[i].setX(board.weapons[i].getX());
			weapons[i].setY(board.weapons[i].getY());
			weapons[i].setPlayerId(board.weapons[i].getPlayerId());
			weapons[i].setType(board.weapons[i].getType());*/
			
		}
		
		for(int i=0;i<F;i++) {
			food[i]=board.food[i];
			/*food[i].setId(board.food[i].getId());
			food[i].setX(board.food[i].getX());
			food[i].setY(board.food[i].getY());
			food[i].setPoints(board.food[i].getPoints());*/
			
		}
		
		for(int i=0;i<F;i++) {
			traps[i]=board.traps[i];
			/*traps[i].setId(board.traps[i].getId());
			traps[i].setX(board.traps[i].getX());
			traps[i].setY(board.traps[i].getY());
			traps[i].setType(board.traps[i].getType());
			traps[i].setPoints(board.traps[i].getPoints());*/
		}
	}
	
	
	//Function that returns the N dimension of the board (N stands for the number of board's rows)
	public int getN_dimension() {
		return N;
	}
	
	//Function that sets the N dimension of the board (N stands for the number of board's rows)
	public void setN_dimension(int N) {
		this.N=N;
	}
	
	//The below two functions may seem unnecessary because the instructions of the exercise state that N==M
	//Taking the above into consideration we can set and return both of the board's dimensions through the above two functions
	//However, I am mentioning them in case that we may wish for an extended version (where the board's dimensions aren't equal  (N != M)) in the future 
	
	//Function that returns the M dimension of the board (M stands for the number of board's rows)
	public int getM_dimension() {
		return M;
	}
	
	//Function that sets the M dimension of the board (M stands for the number of board's rows)
	public void setM_dimension(int M) {
		this.M=M;
	}
	
	//Function that returns the number of weapons existing on the board
	public int getW() {
		return W;
	}
	
	//Function that sets the number of weapons existing on the board
	public void setW(int W) {
		this.W=W;
	}
	
	//Function that returns the number of supplies existing on the board
	public int getF() {
		return F;
	}
	
	//Function that sets the number of supplies existing on the board
	public void setF(int F) {
		this.F=F;
	}
	
	//Function that returns the number of traps existing on the board
	public int getT() {
		return T;
	}
	
	//Function that sets the number of traps existing on the board
	public void setT(int T) {
		this.T=T;
	}
	
	
	/*Function that returns a two dimensional array 4x2 with the coordinates (x,y)
	  which mark the limits of the board's area where weapons are located */
	public int[][] getWeaponAreaLimits() {
		return weaponAreaLimits;
	}
	
	/*Function that sets a two dimensional array 4x2 the coordinates (x,y)
	  which mark the limits of the board's area where weapons are located */
	//The coordinate (x,y) are matching the corner tiles which mark the limits of the area where weapons are located
	public void setWeaponAreaLimits(int x_top_left,int y_top_left,int x_top_right,int y_top_right,int x_bottom_left,int y_bottom_left,int x_bottom_right,int y_bottom_right) {
		weaponAreaLimits[0][0]=	x_top_left;		//coordinate x of the top left corner
		weaponAreaLimits[0][1]=	y_top_left;		//coordinate y of the top left corner
		
		weaponAreaLimits[1][0]= x_top_right;	//coordinate x of the top right corner
		weaponAreaLimits[1][1]= y_top_right;	//coordinate y of the top right corner
		
		weaponAreaLimits[2][0]= x_bottom_left;	//coordinate x of the bottom left corner
		weaponAreaLimits[2][1]= y_bottom_left;	//coordinate y of the bottom left corner
				
		weaponAreaLimits[3][0]= x_bottom_right;	//coordinate x of the bottom right corner
		weaponAreaLimits[3][1]= y_bottom_right; //coordinate y of the bottom right corner
		
	}
	
	/*Function that returns a two dimensional array 4x2 which includes the coordinates (x,y)
	of the corner tiles of the board's area where supplies are located*/
	public int[][] getFoodAreaLimits() {
		return foodAreaLimits;
	}
	
	/*Function that sets a two dimensional array 4x2 the coordinates (x,y) of the corner
	   tiles of the board's area where supplies are located */
	public void setFoodAreaLimits(int x_top_left,int y_top_left,int x_top_right,int y_top_right,int x_bottom_left,int y_bottom_left,int x_bottom_right,int y_bottom_right) {
		foodAreaLimits[0][0]=	x_top_left;		//coordinate x of the top left corner
		foodAreaLimits[0][1]=	y_top_left;		//coordinate y of the top left corner
		
		foodAreaLimits[1][0]= x_top_right;	//coordinate x of the top right corner
		foodAreaLimits[1][1]= y_top_right;	//coordinate y of the top right corner
		
		foodAreaLimits[2][0]= x_bottom_left;	//coordinate x of the bottom left corner
		foodAreaLimits[2][1]= y_bottom_left;	//coordinate y of the bottom left corner
				
		foodAreaLimits[3][0]= x_bottom_right;	//coordinate x of the bottom right corner
		foodAreaLimits[3][1]= y_bottom_right; //coordinate y of the bottom right corner
	}
	
	/*Function that returns a two dimensional array 4x2 which includes the coordinates (x,y)
	of the corner tiles of the board's area where traps are located*/
	public int[][] getTrapAreaLimits() {
		return trapAreaLimits;
	}
	
	/*Function that sets a two dimensional array 4x2 the coordinates (x,y) of the corner
	   tiles of the board's area where traps are located */
	public void setTrapAreaLimits(int x_top_left,int y_top_left,int x_top_right,int y_top_right,int x_bottom_left,int y_bottom_left,int x_bottom_right,int y_bottom_right) {
		trapAreaLimits[0][0]=	x_top_left;		//coordinate x of the top left corner
		trapAreaLimits[0][1]=	y_top_left;		//coordinate y of the top left corner
		
		trapAreaLimits[1][0]= x_top_right;	//coordinate x of the top right corner
		trapAreaLimits[1][1]= y_top_right;	//coordinate y of the top right corner
		
		trapAreaLimits[2][0]= x_bottom_left;	//coordinate x of the bottom left corner
		trapAreaLimits[2][1]= y_bottom_left;	//coordinate y of the bottom left corner
				
		trapAreaLimits[3][0]= x_bottom_right;	//coordinate x of the bottom right corner
		trapAreaLimits[3][1]= y_bottom_right; //coordinate y of the bottom right corner
	}
	
	
	
	
	//--------------------------------------------------------------------------------------------------------------------------
	
	
	
	//Functions that initializes the objects of the array weapons in a random way
	public void createRandomWeapon() {
		weapons=new Weapon[getW()];
		for(int i=0;i<getW();i++) {
			weapons[i]=new Weapon();
		}
		
		
		//auxiliary variables
		int weapon_type;
		int j;
		int cp=0;
		int cs=0;
		int cb=0;
		int count_pl1=0;
		int count_pl2=0;
		
		
		weapon_type=(int)(Math.random()*3);
		//Setting id,x,y,playerId,type for the first weapon that is created(weapons[0])
		
		weapons[0].setId((int)(Math.random()*getW())+1);
		weapons[0].setX((int)(Math.random()*(getWeaponAreaLimits()[1][0]-getWeaponAreaLimits()[0][0]+1)) + getWeaponAreaLimits()[0][0]);
		weapons[0].setY((int)(Math.random()*(getWeaponAreaLimits()[3][1]-getWeaponAreaLimits()[1][1]+1)) + getWeaponAreaLimits()[1][1]);
		weapons[0].setPlayerId((int)(Math.random()*2)+1);
		if(weapon_type==0) {
			weapons[0].setType("pistol");
		}
		else if(weapon_type==1) {
			weapons[0].setType("bow");
		}
		else if(weapon_type==2) {
			weapons[0].setType("sword"); 
		}
		
		//Setting id,x,y,playerId,type for the rest of the weapons(weapons[1]...weapons[W-1])
		for(int i=1;i<getW();i++) {
		
	

			weapons[i].setId((int)(Math.random()*getW())+1);
			//Making sure that every weapon we are creating, has its own unique id ranging from 1 to W
			j=-1;
			for(;;) {
				j++;
				if(weapons[i].getId()==weapons[j].getId()) {
					weapons[i].setId((int)(Math.random()*getW())+1);
					j=-1;
				}
				if(j==i-1) {
					break;
				}
			}
			
			weapons[i].setX((int)(Math.random()*(getWeaponAreaLimits()[1][0]-getWeaponAreaLimits()[0][0]+1)) + getWeaponAreaLimits()[0][0]);
			weapons[i].setY((int)(Math.random()*(getWeaponAreaLimits()[3][1]-getWeaponAreaLimits()[1][1]+1)) + getWeaponAreaLimits()[1][1]);
			
			//Making sure there aren't more than one weapons on the same block's tile
			
			j=-1;
			for(;;) {
				j++;
				if(weapons[i].getX()==weapons[j].getX() && weapons[i].getY()==weapons[j].getY()) {
					
					weapons[i].setX((int)(Math.random()*(getWeaponAreaLimits()[1][0]-getWeaponAreaLimits()[0][0]+1)) + getWeaponAreaLimits()[0][0]);
					weapons[i].setY((int)(Math.random()*(getWeaponAreaLimits()[3][1]-getWeaponAreaLimits()[1][1]+1)) + getWeaponAreaLimits()[1][1]);
					j=-1;
					
				}
				if(j==i-1) {
					break;
				}
			}
			
			weapons[i].setPlayerId((int)(Math.random()*2)+1);
			
			//Counting the weapons each player owns
			for(int a=0;a<=i;a++) {
				if(weapons[a].getPlayerId()==1)
					count_pl1++;
				else if(weapons[a].getPlayerId()==2) {
					count_pl2++;
				}
			}
			//Making sure the weapons are equally distributed to the two players
			if(count_pl1>((getW())/2)) {
				weapons[i].setPlayerId(2);
			}
			if(count_pl2<(getW()/2)) {
				weapons[i].setPlayerId(1);
			}
			
			//Checking if the player (to whom the current weapon (weapon[i]) is intended) has a weapon type pistol also intended to him
			//cp==1 if he has and cp==0 if he hasn't
			for(int p=0;p<i;p++) {
				cp=0;
				if(weapons[p].getType()=="pistol") {
					if(weapons[i].getPlayerId()==weapons[p].getPlayerId()) {
						cp=1;
						break;
					}
				}
			}
			
			//Checking if the player to whom the current weapon (weapon[i]) is intended, has a weapon type sword also intended to him
			//cs==1 if he has and cs==0 if he hasn't
			for(int s=0;s<i;s++) {
				cs=0;
				if(weapons[s].getType()=="sword") {
					if(weapons[i].getPlayerId()==weapons[s].getPlayerId()) {
						cs=1;
						break;
					}
				}
			}
			
			//Checking if the player to whom the current weapon (weapon[i]) is intended, has a weapon type bow also intended to him
			//cb==1 if he has and cb==0 if he hasn't
			for(int b=0;b<i;b++) {
				cb=0;
				if(weapons[b].getType()=="bow") {
					if(weapons[i].getPlayerId()==weapons[b].getPlayerId()) {
						cb=1;
						break;
					}
				}
			}
			//Making sure a player can't be intended to have two weapons of the same type without already being intended to have all weapon types(pistol,bow,sword)
			//This way if the number of weapon on the board are 6,each player will have intended to him one weapon of each type
			if((cp==0 || cs==0 || cb==0) && getW()>=6) {
				if(cp==1) {
					if(cs==1) {
						weapons[i].setType("bow");;
					}
					else if(cb==1) {
						weapons[i].setType("sword");
					}
					else {
						weapon_type=(int)(Math.random()*2);
						if(weapon_type==0) {
							weapons[i].setType("bow");
						}
						else if(weapon_type==1) {
							weapons[i].setType("sword");
						}
					}
				}
				if(cs==1) {
					if(cb==1) {
						weapons[i].setType("pistol");
					}
					if(cb==0 && cp==0) {
						weapon_type=(int)(Math.random()*2);
						if(weapon_type==0) {
							weapons[i].setType("bow");
						}
						else if(weapon_type==1) {
							weapons[i].setType("pistol");
						}
					}
				}
				if(cb==1 && cp==0 && cs==0) {
					do {
						weapon_type=(int)(Math.random()*2);
						if(weapon_type==0) {
							weapons[i].setType("pistol");
						}
						else if(weapon_type==1) {
							weapons[i].setType("sword");
						}
					}while(weapon_type==2);
				}
			}
			
			else {
				weapon_type=(int)(Math.random()*3);
				if(weapon_type==0) {
					weapons[i].setType("pistol");
				}
				else if(weapon_type==1) {
					weapons[i].setType("bow");
				}
				else if(weapon_type==2) {
					weapons[i].setType("sword"); 
				}
			}
			System.out.println("  WEAPONS    :"+weapons[i].getType()+weapons[i].getId()+weapons[i].getPlayerId()+"  x,y : "+weapons[i].getX()+weapons[i].getY());
		}
	}
	
	
	
	//---------------------------------------------------------------------------------------------------
	
	
	
	//Functions that initializes the objects of the array traps in a random way
	public void createRandomTrap() {
		traps =new Trap[getT()];
		for(int i=0;i<getT();i++) {
			traps[i]=new Trap();
		}
		
		//auxiliary variables
		int trap_type; 
		int j;
		int ca=0;
		int cr=0;
		
		trap_type=(int)(Math.random()*2);
		//Setting id,x,y,type,points for the first trap that is created(traps[0])
		
		traps[0].setId((int)(Math.random()*getT())+1);
		do {
			traps[0].setX((int)(Math.random()*(getTrapAreaLimits()[1][0]-getTrapAreaLimits()[0][0]+1))+getTrapAreaLimits()[0][0]);
			traps[0].setY((int)(Math.random()*(getTrapAreaLimits()[3][1]-getTrapAreaLimits()[1][1]+1))+getTrapAreaLimits()[1][1]);
		}while(!(((traps[0].getY()==getTrapAreaLimits()[3][1] || traps[0].getY()==getTrapAreaLimits()[1][1]) && traps[0].getX()<=getTrapAreaLimits()[1][0] && traps[0].getX()>=getTrapAreaLimits()[0][0]) || ((traps[0].getX()==getTrapAreaLimits()[1][0] || traps[0].getX()==getTrapAreaLimits()[0][0]) && traps[0].getY()<=getTrapAreaLimits()[3][1] && traps[0].getY()>=getTrapAreaLimits()[1][1])));
		if(trap_type==0) {
			traps[0].setType("animals");
		}
		else if(trap_type==1) {
			traps[0].setType("ropes");
		}
		traps[0].setPoints((int)(Math.random()*10)-10);
		
		
		//Setting id,x,y,type,points for the rest of the traps(traps[1]...traps[T-1])
		
		for(int i=1;i<getT();i++) {
			
			traps[i].setId((int)(Math.random()*getT())+1);
			//Making sure that every trap we are creating, has its own unique id ranging from 1 to T
			j=-1;
			for(;;) {
				j++;
				if(traps[i].getId()==traps[j].getId()) {
					traps[i].setId((int)(Math.random()*getT())+1);
					j=-1;
				}
				if(j==i-1) {
					break;
				}
			}
			
			do {
				traps[i].setX((int)(Math.random()*(getTrapAreaLimits()[1][0]-getTrapAreaLimits()[0][0]+1))+getTrapAreaLimits()[0][0]);
				traps[i].setY((int)(Math.random()*(getTrapAreaLimits()[3][1]-getTrapAreaLimits()[1][1]+1))+getTrapAreaLimits()[1][1]);
			}while(!(((traps[i].getY()==getTrapAreaLimits()[3][1] || traps[i].getY()==getTrapAreaLimits()[1][1]) && traps[i].getX()<=getTrapAreaLimits()[1][0] && traps[i].getX()>=getTrapAreaLimits()[0][0]) || ((traps[i].getX()==getTrapAreaLimits()[1][0] || traps[i].getX()==getTrapAreaLimits()[0][0]) && traps[i].getY()<=getTrapAreaLimits()[3][1] && traps[i].getY()>=getTrapAreaLimits()[1][1])));

			
			
			//Making sure there aren't more than one traps on the same block's tile
			j=-1;
			for(;;) {
				j++;
				if(traps[i].getX()==traps[j].getX() && traps[i].getY()==traps[j].getY()) {
					do {
						traps[i].setX((int)(Math.random()*(getTrapAreaLimits()[1][0]-getTrapAreaLimits()[0][0]+1))+getTrapAreaLimits()[0][0]);
						traps[i].setY((int)(Math.random()*(getTrapAreaLimits()[3][1]-getTrapAreaLimits()[1][1]+1))+getTrapAreaLimits()[1][1]);
					}while(!(((traps[i].getY()==getTrapAreaLimits()[3][1] || traps[i].getY()==getTrapAreaLimits()[1][1]) && traps[i].getX()<=getTrapAreaLimits()[1][0] && traps[i].getX()>=getTrapAreaLimits()[0][0]) || ((traps[i].getX()==getTrapAreaLimits()[1][0] || traps[i].getX()==getTrapAreaLimits()[0][0]) && traps[i].getY()<=getTrapAreaLimits()[3][1] && traps[i].getY()>=getTrapAreaLimits()[1][1])));
					
					j=-1;
				}
				if(j==i-1) {
					break;
				}
			}
			
			
			//Checking if there has been created a trap type "animals"
			for(int a=0;a<i;a++) {
				ca=0;
				if(traps[a].getType()=="animals)") {
					ca=1;
					break;
				}
			}
			
			//Checking if there has been created a trap type "ropes"
			for(int r=0;r<i;r++) {
				cr=0;
				if(traps[r].getType()=="ropes") {
					cr=1;
				}
			}
			
			//Making sure that if the number of weapons on the board is greater or equal to 2,there will be both types of traps located on the board
			if(i==getT()-1 && getT()>=2) {
				if(ca==0) {
					traps[i].setType("animals");
				}
				else if(cr==0) {
					traps[i].setType("ropes");
				}
				else {
					trap_type=(int)(Math.random()*2);
					if(trap_type==0) {
						traps[i].setType("animals");
					}
					else if(trap_type==1) {
						traps[i].setType("ropes");
					}
				}
			}	
				
			traps[i].setPoints((int)(Math.random()*10)-10);
			System.out.println("  TRAPS    :"+traps[i].getType()+traps[i].getId()+"  x,y : "+traps[i].getX()+traps[i].getY());
		}
	}
	
	
	//-------------------------------------------------------------------------------------------------------------
	
	
	//Functions that initializes the objects of the array food in a random way
	public void createRandomFood() {
		food = new Food[getF()];
		for(int i=0;i<getF();i++) {
			food[i]=new Food();
		}
		//auxiliary variables
		int j;
		//Setting id,x,y,points for the first supply that is created(food[0])
		
		food[0].setId((int)(Math.random()*getF())+1);
		
		do {
			food[0].setX((int)(Math.random()*(getFoodAreaLimits()[1][0]-getFoodAreaLimits()[0][0]+1))+getFoodAreaLimits()[0][0]);
			food[0].setY((int)(Math.random()*(getFoodAreaLimits()[3][1]-getFoodAreaLimits()[1][1]+1))+getFoodAreaLimits()[1][1]);
		}while(!(((food[0].getY()==getFoodAreaLimits()[3][1] || food[0].getY()==getFoodAreaLimits()[1][1]) && food[0].getX()<=getFoodAreaLimits()[1][0] && food[0].getX()>=getFoodAreaLimits()[0][0]) || ((food[0].getX()==getFoodAreaLimits()[1][0] || food[0].getX()==getFoodAreaLimits()[0][0]) && food[0].getY()<=getFoodAreaLimits()[3][1] && food[0].getY()>=getFoodAreaLimits()[1][1])));
		
		food[0].setPoints((int)(Math.random()*10)+1);
		
		//Setting id,x,y,points for the rest of the supplies(food[1]...food[F-1])
		for(int i=1;i<getF();i++) {
			food[i].setId((int)(Math.random()*getF())+1);
			
			//Making sure that every supply we are creating, has its own unique id ranging from 1 to F
			j=-1;
			for(;;) {
				j++;
				if(food[i].getId()==food[j].getId()) {
					food[i].setId((int)(Math.random()*getF())+1);
					j=-1;
				}
				if(j==i-1) {
					break;
				}
			}
			
			do {
				food[i].setX((int)(Math.random()*(getFoodAreaLimits()[1][0]-getFoodAreaLimits()[0][0]+1))+getFoodAreaLimits()[0][0]);
				food[i].setY((int)(Math.random()*(getFoodAreaLimits()[3][1]-getFoodAreaLimits()[1][1]+1))+getFoodAreaLimits()[1][1]);
			}while(!(((food[i].getY()==getFoodAreaLimits()[3][1] || food[i].getY()==getFoodAreaLimits()[1][1]) && food[i].getX()<=getFoodAreaLimits()[1][0] && food[i].getX()>=getFoodAreaLimits()[0][0]) || ((food[i].getX()==getFoodAreaLimits()[1][0] || food[i].getX()==getFoodAreaLimits()[0][0]) && food[i].getY()<=getFoodAreaLimits()[3][1] && food[i].getY()>=getFoodAreaLimits()[1][1])));
			
			
			
			//Making sure there aren't more than one supplies on the same block's tile
			j=-1;
			for(;;) {
				j++;
				if(food[i].getX()==food[j].getX() && food[i].getY()==food[j].getY()) {
					do {
						food[i].setX((int)(Math.random()*(getFoodAreaLimits()[1][0]-getFoodAreaLimits()[0][0]+1))+getFoodAreaLimits()[0][0]);
						food[i].setY((int)(Math.random()*(getFoodAreaLimits()[3][1]-getFoodAreaLimits()[1][1]+1))+getFoodAreaLimits()[1][1]);
					}while(!(((food[i].getY()==getFoodAreaLimits()[3][1] || food[i].getY()==getFoodAreaLimits()[1][1]) && food[i].getX()<=getFoodAreaLimits()[1][0] && food[i].getX()>=getFoodAreaLimits()[0][0]) || ((food[i].getX()==getFoodAreaLimits()[1][0] || food[i].getX()==getFoodAreaLimits()[0][0]) && food[i].getY()<=getFoodAreaLimits()[3][1] && food[i].getY()>=getFoodAreaLimits()[1][1])));
					j=-1;
					
				}
				if(j==i-1) {
					break;
				}
			}
			food[i].setPoints((int)(Math.random()*10)+1);
			System.out.println("  FOOD    :"+food[i].getId()+"  x,y : "+food[i].getX()+food[i].getY());
		}
	}
	
	
	//-----------------------------------------------------------------------------------------------------------
	
	//Function that creates the game's board in a pseudo-random way using the above three functions(createRandomWeapon() , createRandomTrap(), createRandomFood()
			public void createBoard() {
				
				createRandomWeapon();
				createRandomTrap();
				createRandomFood();
				
				//The rest of the board is created based on the board's size
				//if M the number of columns and N the number of rows , Xmax=M/2 , Xmin=-M/2, Ymax=N/2, Ymin=-N/2
			}
	
	
	
	//Function that removes the outside tile perimeter of the board ,and so the total the board's size is diminished by 2 points
	public void resizeBoard(Player p1,Player p2) {
		//Checking if any player is located at the outer tile perimeter
		if( p1.getX()!=((-getM_dimension())/2 ) && p1.getX()!= (getM_dimension()/2) && p1.getY()!=((-getN_dimension())/2 ) && p1.getY()!= (getN_dimension()/2)   &&   p2.getX()!=((-getM_dimension())/2 ) && p2.getX()!= (getM_dimension()/2) && p2.getY()!=((-getN_dimension())/2 ) && p2.getY()!= (getN_dimension()/2)) {
			setM_dimension(getM_dimension()-2);
			setN_dimension(getN_dimension()-2);
		}
	}
	
	//Function that creates and returns an array NxM.Every array's element contains a String which represents the object located on the corresponding board's tile accompanied by it's id
	public String[][] getStringRepresentation(){

		String[][] plakidio=new String[getN_dimension()][getM_dimension()];
		
		
		Weapon[] weapons=new Weapon[getW()];
		for(int i=0;i<getW();i++) {
			//Making weapons[i](object created in this function)to point at the same direction as weapon[i](object created in class Weapon) , so that they'll practically  behave as the same object
			weapons[i]=new Weapon(this.weapons[i]);
		}
		
		Food[] food=new Food[getF()];
		for(int i=0;i<getF();i++) {
			//Making food[i](object created in this function) to point at the same direction as food[i](object created in class Food) , so that they'll practically behave as the same weapon
			food[i]=new Food(this.food[i]);
		}
		
		Trap[] traps=new Trap[getT()];
		for(int i=0;i<getT();i++) {
			//Making trap[i](object created in this function) to point at the same direction as trap[i](object created in class Trap) , so that they'll practically behave as the same weapon
			traps[i]=new Trap(this.traps[i]);
		}
		
		
		
		for(int i=0;i<getN_dimension();i++) {
			for(int j=0;j<getM_dimension();j++) {
				
				//Searching for traps,supplies and weapons within the area limits of the traps(which also includes the area limits of the supplies and weapons)
				
				
				if(j>=(getM_dimension()/2-getTrapAreaLimits()[1][0]) && j<(getM_dimension()/2+getTrapAreaLimits()[1][0]) && i>=(getN_dimension()/2-getTrapAreaLimits()[3][1]) && i<(getN_dimension()/2+getTrapAreaLimits()[3][1])) {
					

					//Checking on the board's top left quarter
					
					if(i<=(getN_dimension()/2-1) && j<=(getM_dimension()/2-1)){
						
						//Assigning specific value type String to the board's tiles which contain a weapon
						for(int pw=0;pw<getW();pw++) {
							if(weapons[pw].getX()==(-getM_dimension()/2+j) && weapons[pw].getY()==(-getN_dimension()/2+i)) {
								plakidio[i][j]="W"+weapons[pw].getPlayerId()+weapons[pw].getId();
							}							
						}
						//Assigning specific value type String to the board's tiles which contain a supply
						for(int pf=0;pf<getF();pf++) {
							if(food[pf].getX()==(-getM_dimension()/2+j) && food[pf].getY()==(-getN_dimension()/2+i)) {
								plakidio[i][j]="F"+food[pf].getId();
							}
						}
						//Assigning specific value type String to the board's tiles which contain a trap
						for(int pt=0;pt<getT();pt++) {
							if(traps[pt].getX()==(-getM_dimension()/2+j) && traps[pt].getY()==(-getN_dimension()/2+i)) {
								plakidio[i][j]="T"+traps[pt].getId();
							}
						}
					}
					
					
					//Checking on the board's bottom left quarter
					
					if(i>=(getN_dimension()/2) && j<=(getM_dimension()/2-1)) {
						
						//Assigning specific value type String to the board's tiles which contain a weapon
						for(int pw=0;pw<getW();pw++) {
							if(weapons[pw].getX()==(-getM_dimension()/2+j) && weapons[pw].getY()==(-getN_dimension()/2+i+1)) {
								plakidio[i][j]="W"+weapons[pw].getPlayerId()+weapons[pw].getId();
							}
						}
						//Assigning specific value type String to the board's tiles which contain a supply
						for(int pf=0;pf<getF();pf++) {
							if(food[pf].getX()==(-getM_dimension()/2+j) && food[pf].getY()==(-getN_dimension()/2+i+1)) {
								plakidio[i][j]="F"+food[pf].getId();
							}
						}
						//Assigning specific value type String to the board's tiles which contain a trap
						for(int pt=0;pt<getT();pt++) {
							if(traps[pt].getX()==(-getM_dimension()/2+j) && traps[pt].getY()==(-getN_dimension()/2+i+1)) {
								plakidio[i][j]="T"+traps[pt].getId();
							}
						}
						
					}
					
				
					//Checking on the board's top right quarter
					
					if(i<=(getN_dimension()/2-1) && j>=(getM_dimension()/2)) {
						
						//Assigning specific value type String to the board's tiles which contain a weapon
						for(int pw=0;pw<getW();pw++) {
							if(weapons[pw].getX()==(-getM_dimension()/2+j+1) && weapons[pw].getY()==(-getN_dimension()/2+i)) {
								plakidio[i][j]="W"+weapons[pw].getPlayerId()+weapons[pw].getId();
							}
						}
						//Assigning specific value type String to the board's tiles which contain a supply
						for(int pf=0;pf<getF();pf++) {
							if(food[pf].getX()==(-getM_dimension()/2+j+1) && food[pf].getY()==(-getN_dimension()/2+i)) {
								plakidio[i][j]="F"+food[pf].getId();
							}
						}
						//Assigning specific value type String to the board's tiles which contain a trap
						for(int pt=0;pt<getT();pt++) {
							if(traps[pt].getX()==(-getM_dimension()/2+j+1) && traps[pt].getY()==(-getN_dimension()/2+i)) {
								plakidio[i][j]="T"+traps[pt].getId();
							}
						}
					}
					
					
					//Checking on the board's bottom right quarter
					
					if(i>=(getN_dimension()/2) && j>=(getM_dimension()/2)) {
						
						//Assigning specific value type String to the board's tiles which contain a weapon
						for(int pw=0;pw<getW();pw++) {
							if(weapons[pw].getX()==(-getM_dimension()/2+j+1) && weapons[pw].getY()==(-getN_dimension()/2+i+1)) {
								plakidio[i][j]="W"+weapons[pw].getPlayerId()+weapons[pw].getId();
							}
						}
						//Assigning specific value type String to the board's tiles which contain a supply
						for(int pf=0;pf<getF();pf++) {
							if(food[pf].getX()==(-getM_dimension()/2+j+1) && food[pf].getY()==(-getN_dimension()/2+i+1)) {
								plakidio[i][j]="F"+food[pf].getId();
							}
						}
						//Assigning specific value type String to the board's tiles which contain a trap
						for(int pt=0;pt<getT();pt++) {
							if(traps[pt].getX()==(-getM_dimension()/2+j+1) && traps[pt].getY()==(-getN_dimension()/2+i+1)) {
								plakidio[i][j]="T"+traps[pt].getId();
							}
						}
					}
				}
				
				//Assigning value type String to the board's tiles which contain no weapon,supply or trap
				else {
					plakidio[i][j]="____";
				}
				
				if(plakidio[i][j]==null) {
					plakidio[i][j]="____";
				}
			}
		}
		return plakidio;
	}
	
}
