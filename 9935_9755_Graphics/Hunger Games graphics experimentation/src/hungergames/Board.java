package hungergames;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Board {
	private int N, M;
	private int W, F, T;
	private int weaponAreaLimits[][];
	private int foodAreaLimits[][];
	private int trapAreaLimits[][];
	
	private Weapon[] weapons;
	private Food[] food;
	private Trap[] traps;
	
	//FOR THE GRAPHICS
	private int spaceX=50;
	private int spaceY=5;
	
	//Width and height of the game's tiles
	private int TILEWIDTH,TILEHEIGHT;
	
	private int[] foodType;
	
	//original boar dimensions
	private int fN,fM;
	
	
	public Board() {
		// Sizes
		N = 0;
		M = 0;
		W = 0;
		F = 0;
		T = 0;
		// Arrays
		weaponAreaLimits = new int[4][2];
		foodAreaLimits = new int[4][2];
		trapAreaLimits = new int[4][2];
		weapons = new Weapon[W];
		food = new Food[F];
		traps = new Trap[T];
	}
	
	public Board(int n, int m, int w, int f, int t) {
		// Sizes
		N = n;
		M = m;
		W = w;
		F = f;
		T = t;
		// Arrays
		weaponAreaLimits = new int[4][2];
		foodAreaLimits = new int[4][2];
		trapAreaLimits = new int[4][2];
		weapons = new Weapon[W];
		food = new Food[F];
		traps = new Trap[T];
		
	}
	

	
	public Board(Board b) {
		// Sizes
		N = b.N;
		M = b.M;
		W = b.W;
		F = b.F;
		T = b.T;
		// Arrays
		weaponAreaLimits = new int[4][2];
		foodAreaLimits = new int[4][2];
		trapAreaLimits = new int[4][2];
		weapons = new Weapon[W];
		food = new Food[F];
		traps = new Trap[T];
		// Deep copy
		for(int i=0; i<4; i++) {
			for(int j=0; j<2; j++) {
				weaponAreaLimits[i][j] = b.weaponAreaLimits[i][j];
				foodAreaLimits[i][j] = b.foodAreaLimits[i][j];
				trapAreaLimits[i][j] = b.trapAreaLimits[i][j];
			}
		}
		for(int i=0; i<W; i++) {
			weapons[i] = new Weapon(b.weapons[i]);
		}
		for(int i=0; i<F; i++) {
			food[i] = new Food(b.food[i]);
		}
		for(int i=0; i<T; i++) {
			traps[i] = new Trap(b.traps[i]);
		}
	}
	
	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}

	public int getM() {
		return M;
	}

	public void setM(int m) {
		M = m;
	}

	public int getW() {
		return W;
	}

	public void setW(int w) {
		W = w;
	}

	public int getF() {
		return F;
	}

	public void setF(int f) {
		F = f;
	}

	public int getT() {
		return T;
	}

	public void setT(int t) {
		T = t;
	}

	public int[][] getWeaponAreaLimits() {
		return weaponAreaLimits;
	}

	public void setWeaponAreaLimits(int[][] weaponAreaLimits) {
		this.weaponAreaLimits = weaponAreaLimits;
	}

	public int[][] getFoodAreaLimits() {
		return foodAreaLimits;
	}

	public void setFoodAreaLimits(int[][] foodAreaLimits) {
		this.foodAreaLimits = foodAreaLimits;
	}

	public int[][] getTrapAreaLimits() {
		return trapAreaLimits;
	}

	public void setTrapAreaLimits(int[][] trapAreaLimits) {
		this.trapAreaLimits = trapAreaLimits;
	}

	public Weapon[] getWeapons() {
		return weapons;
	}

	public void setWeapons(Weapon[] weapons) {
		this.weapons = weapons;
	}

	public Food[] getFood() {
		return food;
	}

	public void setFood(Food[] food) {
		this.food = food;
	}

	public Trap[] getTraps() {
		return traps;
	}

	public void setTraps(Trap[] traps) {
		this.traps = traps;
	}
	
	// Check if the tile at coordinates (x, y) contains an item
	boolean containsItem(int x, int y) {
		for(int i=0; i<weapons.length; i++) {
			Weapon w = weapons[i];
			if(w!= null && w.getId() != -1) {
				if(w.getX() == x && w.getY() == y) {
					return true;
				}
			}
		}
		for(int i=0; i<traps.length; i++) {
			Trap w = traps[i];
			if(w != null &&w.getId() != -1) {
				if(w.getX() == x && w.getY() == y) {
					return true;
				}
			}
		}
		for(int i=0; i<food.length; i++) {
			Food w = food[i];
			if(w !=null &&w.getId() != -1) {
				if(w.getX() == x && w.getY() == y) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	void createRandomWeapon() {
		String types[] = {
				"pistol",
				"bow",
				"sword"
		};
		Random r = new Random();
		
		for(int wCounter=0; wCounter<W; wCounter++) {
			int areaLimits = (weaponAreaLimits[1][0] - weaponAreaLimits[0][0])*(weaponAreaLimits[3][1] - weaponAreaLimits[0][1]);
			// Number of total available tiles to place the new weapon. We will pick one of these at random.
			int totalTiles = areaLimits - wCounter;
			// Index of chosen tile to place item
			int die = r.nextInt(totalTiles);
			// Array with available tiles
			int tiles[][] = new int[totalTiles][2];
			int counter = 0;
			for(int x=weaponAreaLimits[0][0]; x<=weaponAreaLimits[1][0];x++) {
				for(int y=weaponAreaLimits[0][1]; y<=weaponAreaLimits[3][1];y++) {
					if(x == 0 || y == 0) {
						continue;
					}
					if(!containsItem(x, y)) {
						tiles[counter][0] = x;
						tiles[counter][1] = y;
						counter++;
					}
				}	
			}
			// New item
			Weapon w = new Weapon();
			w.setId(wCounter);
			w.setType(types[wCounter%3]);
			w.setX( tiles[die][0] );
			w.setY( tiles[die][1] );
			w.setPlayerId(wCounter%2);
			this.weapons[wCounter] = w;
		}
	}
	
	// Helper function. Checks if the given (x,y) coordinates lie inside the limits indicated by the array "limits".
	boolean isInside(int x, int y, int[][] limits) {
		int minx = limits[0][0];
		int maxx = limits[1][0];
		int miny = limits[0][1];
		int maxy = limits[3][1];
		return (x >= minx && x <= maxx && y >= miny && y <= maxy);
	}
	
	void createRandomFood() {
		Random r = new Random();
		for(int fCounter=0; fCounter<F; fCounter++) {
			int areaLimits = (foodAreaLimits[1][0] - foodAreaLimits[0][0])*(foodAreaLimits[3][1] - foodAreaLimits[0][1]);
			int areaOffLimits = (weaponAreaLimits[1][0] - weaponAreaLimits[0][0])*(weaponAreaLimits[3][1] - weaponAreaLimits[0][1]);
			// Number of total available tiles to place the new food. We will pick one of these at random.
			int totalTiles = areaLimits - areaOffLimits - fCounter;
			// Index of chosen tile to place item
			int die = r.nextInt(totalTiles);
			// Array with available tiles
			int tiles[][] = new int[totalTiles][2];
			int counter = 0;
			for(int x=foodAreaLimits[0][0]; x<=foodAreaLimits[1][0];x++) {
				for(int y=foodAreaLimits[0][1]; y<=foodAreaLimits[3][1];y++) {
					if(x == 0 || y == 0) {
						continue;
					}
					if(!isInside(x, y, weaponAreaLimits) && !containsItem(x, y)) {
						tiles[counter][0] = x;
						tiles[counter][1] = y;
						counter++;
					}
				}	
			}
			
			Food f = new Food();
			f.setId(fCounter);
			f.setX( tiles[die][0] );
			f.setY( tiles[die][1] );
			f.setPoints(r.nextInt(10) + 1);
			this.food[fCounter] = f;
		}
	}
	
	void createRandomTrap() {
		String types[] = {
				"ropes",
				"animals"
		};
		Random r = new Random();
		for(int tCounter=0; tCounter<T; tCounter++) {
			int areaLimits = (trapAreaLimits[1][0] - trapAreaLimits[0][0])*(trapAreaLimits[3][1] - trapAreaLimits[0][1]);
			int areaOffLimits = (foodAreaLimits[1][0] - foodAreaLimits[0][0])*(foodAreaLimits[3][1] - foodAreaLimits[0][1]);
			// Number of total available tiles to place the new trap. We will pick one of these at random.
			int totalTiles = areaLimits - areaOffLimits - tCounter;
			// Index of chosen tile to place item
			int die = r.nextInt(totalTiles);
			// Array with available tiles
			int tiles[][] = new int[totalTiles][2];
			int counter = 0;
			// Loop goes through all possible positions where a trap can be placed until the counter reaches
			// the value tile. It creates a new trap at that tile.
			for(int x=trapAreaLimits[0][0]; x<=trapAreaLimits[1][0];x++) {
				for(int y=trapAreaLimits[0][1]; y<=trapAreaLimits[3][1];y++) {
					if(x == 0 || y == 0) {
						continue;
					}
					if(!isInside(x, y, foodAreaLimits) && !containsItem(x, y)) {
						tiles[counter][0] = x;
						tiles[counter][1] = y;
						counter++;
					}
				}	
			}
			Trap t = new Trap();
			t.setId(tCounter);
			t.setX( tiles[die][0] );
			t.setY( tiles[die][1] );
			t.setType(types[r.nextInt(types.length)]);
			t.setPoints(r.nextInt(10) + 1);
			this.traps[tCounter] = t;
		}
	}
	
	void createBoard() {
		createRandomWeapon();
		createRandomFood();
		createRandomTrap();
		fN=N;
		fM=M;
	}
	
	// Helper function. Checks if the given player's position is on the edge of the board.
	boolean  isOnMargin(Player p) {
		return p.getX() == -N/2 || p.getX() == N/2 || p.getY() == -M/2 || p.getY() == M/2;
	}
	
	// Helper function. Checks if the given position (x, y) lies inside the board area.
	boolean isPositionValid(int x, int y) {
		return x >= -N/2 && x <= N/2 && y >= -M/2 && y <= M/2;
	}
	
	void resizeBoard(Player p1, Player p2) {
		if(!isOnMargin(p1) && !isOnMargin(p2)) {
			N -= 2;
			M -= 2;
			System.out.printf("Board (Resize), newMN %d %d\n", M, N);
		}
	}
	
	// Helper function. Returns the string representation of the tile at the given position (x, y).
	String tileAsString(int x, int y) {
		// Look for weapon in the tile
		for(int i=0; i<W; i++) {
			if(weapons[i].getX() == x && weapons[i].getY() == y) {
				return " W" + weapons[i].getPlayerId() + weapons[i].getId();  // Tile with weapon
			}
		}
		// Look for food in the tile
		for(int i=0; i<F; i++) {
			if(food[i].getX() == x && food[i].getY() == y) {
				return "  F" + food[i].getId();  // Tile with food
			}
		}
		// Look for trap in the tile
		for(int i=0; i<T; i++) {
			if(traps[i].getX() == x && traps[i].getY() == y) {
				return "  T" + traps[i].getId();  // Tile with trap
			}
		}
		return "____"; // Empty tile
	}
	
	// Helper function. Converts from x coordinate to array index i.
	int x2i(int x) {
		if(x > 0) {
			return x + N/2 - 1;
		} else {
			return x + N/2;
		}
	}
	
	// Helper function. Converts from y coordinate to array index j.
	int y2j(int y) {
		if(y > 0) {
			return y + M/2 - 1;
		} else {
			return y + M/2;
		}
	}
	
	String[][] getStringRepresentation() {
		String s[][] = new String[N][M];
		for(int x=-N/2; x<=N/2; x++) {
			for(int y=-N/2; y<=N/2; y++) {
				s[x2i(x)][y2j(y)] = tileAsString(x, y);
			}
		}
		return s;
	}
	
	
	//Function  that renders(draws) the game's board
	public void renderBoard(Graphics g) {
		
		//g.drawImage(Assets.sea,0,0,GameManager.width,GameManager.height,null);
		int Fx,Fy,Tx,Ty,Rx,Ry;
		int counter;
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				g.drawImage(Assets.grass,(N/2-2+i)*TILEWIDTH+spaceX,(M/2-2+j)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			}
		}
		if(N/2>=Math.abs(foodAreaLimits[0][0]) && M/2>=Math.abs(foodAreaLimits[0][1])) {
			
			Fx=Math.abs(foodAreaLimits[0][0]);
			
			Fy=Math.abs(foodAreaLimits[0][1]);
			
			for(int i=0;i<Fx*2;i++) {

				g.drawImage(Assets.stone,(N/2-Fx+i)*TILEWIDTH+spaceX,(M/2-Fy)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
				g.drawImage(Assets.stone,(N/2-Fx+i)*TILEWIDTH+spaceX,(M/2-Fy+2*Fy-1)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			}
			for(int j=0;j<Fy*2;j++) {

				g.drawImage(Assets.stone,(N/2-Fx)*TILEWIDTH+spaceX,(M/2-Fy+j)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
				g.drawImage(Assets.stone,(N/2-Fx+2*Fx-1)*TILEWIDTH+spaceX,(M/2-Fy+j)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			}
		}

			
		if(N/2>=Math.abs(trapAreaLimits[0][0]) && M/2>=Math.abs(trapAreaLimits[0][1])) {
				
			Tx=Math.abs(trapAreaLimits[0][0]);
				
			Ty=Math.abs(trapAreaLimits[0][1]);
				
			for(int i=0;i<Tx*2;i++) {

				g.drawImage(Assets.sand,(N/2-Tx+i)*TILEWIDTH+spaceX,(M/2-Ty)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
				g.drawImage(Assets.sand,(N/2-Tx+i)*TILEWIDTH+spaceX,(M/2-Ty+2*Ty-1)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			}
			for(int j=0;j<Ty*2;j++) {

				g.drawImage(Assets.sand,(N/2-Tx)*TILEWIDTH+spaceX,(M/2-Ty+j)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
				g.drawImage(Assets.sand,(N/2-Tx+2*Tx-1)*TILEWIDTH+spaceX,(M/2-Ty+j)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			}
		}
		
		counter=1;
		while((N/2>=Math.abs(trapAreaLimits[0][0])+counter) && (M/2>=Math.abs(trapAreaLimits[0][1])+counter)) {
			Rx=Math.abs(trapAreaLimits[0][0])+counter;
			Ry=Math.abs(trapAreaLimits[0][1])+counter;

				
			for(int i=0;i<Rx*2;i++) {

				g.drawImage(Assets.sea,(N/2-Rx+i)*TILEWIDTH+spaceX,(M/2-Ry)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
				g.drawImage(Assets.sea,(N/2-Rx+i)*TILEWIDTH+spaceX,(M/2-Ry+2*Ry-1)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			}
			for(int j=0;j<Ry*2;j++) {

				g.drawImage(Assets.sea,(N/2-Rx)*TILEWIDTH+spaceX,(M/2-Ry+j)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
				g.drawImage(Assets.sea,(N/2-Rx+2*Rx-1)*TILEWIDTH+spaceX,(M/2-Ry+j)*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			}
			counter++;
		}
	}

	
	//Function that renders(draws) the board's supplies
	public void renderFood(Graphics g) {

		
		for(int i=0;i<F;i++) {
			if(foodType[i]==0) 
				g.drawImage(Assets.apple,(x2i(food[i].getX()))*TILEWIDTH+spaceX,(y2j(food[i].getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			else if(foodType[i]==1) 
				g.drawImage(Assets.grape,(x2i(food[i].getX()))*TILEWIDTH+spaceX,(y2j(food[i].getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			
			else if(foodType[i]==2)
				g.drawImage(Assets.pineapple,(x2i(food[i].getX()))*TILEWIDTH+spaceX,(y2j(food[i].getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);

			if(food[i].getX()==0 && food[i].getY()==0) {
				g.clearRect((x2i(food[i].getX()))*TILEWIDTH+spaceX,(y2j(food[i].getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT);
				g.drawImage(Assets.stone,(x2i(food[i].getX()))*TILEWIDTH+spaceX,(y2j(food[i].getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);

			}
			else if((Math.abs(food[i].getX())>getN()/2) || Math.abs(food[i].getY())>getM()/2){
				g.clearRect(  (x2i(food[i].getX()))*TILEWIDTH+spaceX,(y2j(food[i].getY()))*TILEHEIGHT+spaceY, TILEWIDTH, TILEHEIGHT  );
			}
			
		}
		

	}
	
	//Function that renders(draws) the board's traps
	public void renderTrap(Graphics g) {
		for(int i=0;i<T;i++) {
			if(traps[i].getType()=="ropes")
				g.drawImage(Assets.rope,(x2i(traps[i].getX()))*TILEWIDTH+spaceX,(y2j(traps[i].getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			else if(traps[i].getType()=="animals")
				g.drawImage(Assets.animal,(x2i(traps[i].getX()))*TILEWIDTH+spaceX,(y2j(traps[i].getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			

			if((Math.abs(traps[i].getX())>getN()/2) || Math.abs(traps[i].getY())>getM()/2){
				g.clearRect(  (x2i(traps[i].getX()))*TILEWIDTH+spaceX,(y2j(traps[i].getY()))*TILEHEIGHT+spaceY, TILEWIDTH, TILEHEIGHT  );
			}
		}

	}
	
	//Function that draws the weapons to the screen
	public void renderWeapon(Graphics g) {
		for(int i=0;i<W;i++) {
			if(weapons[i].getType()=="pistol") {
				g.drawImage(Assets.pistol,(x2i(weapons[i].getX()))*TILEWIDTH+spaceX,(y2j(weapons[i].getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			}
			else if(weapons[i].getType()=="bow") {
				g.drawImage(Assets.bow,(x2i(weapons[i].getX()))*TILEWIDTH+spaceX,(y2j(weapons[i].getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			}
			else if(weapons[i].getType()=="sword") {
				g.drawImage(Assets.sword,(x2i(weapons[i].getX()))*TILEWIDTH+spaceX,(y2j(weapons[i].getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
			}
			if(weapons[i].getX()==0 && weapons[i].getY()==0) {
				g.clearRect((x2i(weapons[i].getX()))*TILEWIDTH+spaceX,(y2j(weapons[i].getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT);
				g.drawImage(Assets.grass,(x2i(weapons[i].getX()))*TILEWIDTH+spaceX,(y2j(weapons[i].getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);

			}

			else if((Math.abs(weapons[i].getX())>getN()/2) || Math.abs(weapons[i].getY())>getM()/2){
				g.clearRect(  (x2i(weapons[i].getX()))*TILEWIDTH+spaceX,(y2j(weapons[i].getY()))*TILEHEIGHT+spaceY, TILEWIDTH, TILEHEIGHT  );
			}
		}
		
	}
	
	
	//Function that draws the player to the screen
	public void renderPlayer(Graphics g,Player player,BufferedImage image) {
		g.drawImage(image,(x2i(player.getX()))*TILEWIDTH+spaceX,(y2j(player.getY()))*TILEHEIGHT+spaceY,TILEWIDTH,TILEHEIGHT,null);
	}
	
	
	//Function that sets the height of the board's tiles
	public void setTILEWIDTH(int TILEWIDTH) {
		this.TILEWIDTH=TILEWIDTH;
	}
	
	//Function that sets the width of the board's tiles
	public void setTILEHEIGHT(int TILEHEIGHT) {
		this.TILEHEIGHT=TILEHEIGHT;
	}
	
	
	//Function that gives random values to the array foodType ranging from 0 to 2.
	//These values will eventually determine which food image will be drawn for each board's supply.
	public void setRandomFoodType() {
		int r;
		foodType=new int[F];

		for(int i=0;i<F;i++) {		
			r=(int)(Math.random()*3);
			if(r==0)
				foodType[i]=0;
			else if(r==1)
				foodType[i]=1;
			else if(r==2)
				foodType[i]=2;
			
		}
	
	}

	public int getSpaceX() {
		return spaceX;
	}


	public int getSpaceY() {
		return spaceY;
	}

	public int getfN() {
		return fN;
	}

	public void setfN(int fN) {
		this.fN = fN;
	}

	public int getfM() {
		return fM;
	}
	
	

	
	
	
	
	
	
}


