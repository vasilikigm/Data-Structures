package HungerGames;

public class Trap {
	int id;			//Trap's id
	int x;			//Coordinate x of the board's tile (where the trap is located)
	int y;			//Coordinate y of the board's tile (where the trap is located)
	String type;	//Trap's type (ropes or animal)
	int points;		//Points the player loses when he falls into the trap without the proper weapon to deal with it

	
	//Constructor No.1
	//Empty constructor
	Trap(){
		
	}
	
	//Constructor No.2
	//Initializes the variables of the object that is created, with the variables of another object of this class
	Trap(Trap trap){
		id=trap.id;
		x=trap.x;
		y=trap.y;
		type=trap.type;
		points=trap.points;
	}
	
	//Function that returns the trap's id
	public int getId() {
		return id;
	}
	
	//Function that sets the trap's id
	public void setId(int in_id) {
		id=in_id;
	}
	
	//Function that returns the coordinate x of the board's tile where the trap is located
	public int getX() {
		return x;
	}
	
	//Function that sets the coordinate x of the board's tile where the trap is located
	public void setX(int in_x) {
		x=in_x;
	}
	
	//Function that returns the coordinate y of the board's tile where the trap is located
	public int getY() {
		return y;
	}
	
	//Function that sets the coordinate y of the board's tile where the trap is located
	public void setY(int in_y) {
		y=in_y;
	}
	
	//Function that returns the trap's type
	public String getType() {
		return type;
	}
	
	//Function that sets the trap's type
	public void setType(String in_type) {
		type=in_type;
	}
	
	/* Function that returns the points the player loses when he falls into
	 a the trap without the proper weapon to deal with it*/
	public int getPoints() {
		return points;
	}
	
	/* Function that sets the points the player loses when he falls into
	 a the trap without the proper weapon to deal with it*/
	public void setPoints(int in_points) {
		points=in_points;
	}
	
}
