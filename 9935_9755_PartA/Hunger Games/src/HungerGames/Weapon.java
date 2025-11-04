package HungerGames;

public class Weapon {
	int id ;		//weapon's id
	int x;			//Coordinate x of the board's tile (where the weapon is located)
	int y;			//Coordinate y of the board's tile (where the weapon is located)
	int PlayerId;	//The id of the player who owns the weapon 
	String type;	//Weapon's type (pistol,bow,sword)
	
	// Constructor No.1
	//Empty constructor
	Weapon(){
		
	}
	
	//Constructor No.2
	//Initializes the variables of the object that is created, with the variables of another object of this class
	Weapon(Weapon weapon){
		
		id=weapon.id;
		x=weapon.x;
		y=weapon.y;
		PlayerId=weapon.PlayerId;
		type=weapon.type;
	}
	
	//Function that returns the weapon's id
	public int getId() {
		return id;
	}
	
	//Function that sets the weapon's id
	public void setId(int in_id) {
		id=in_id;
	}
	
	//Function that returns the coordinate x of the board's tile where the weapon is located
	public int getX() {
		return x;
	}
	
	//Function that sets the coordinate x of the board's tile where the weapon is located
	public void setX(int in_x) {
		x=in_x;
	}
	
	//Function that returns the coordinate y of the board's tile where the weapon is located
	public int getY() {
		return y;
	}
	
	//Function that sets the coordinate y of the board's tile where the weapon is located	
	public void setY(int in_y) {
		y=in_y;
	}
	
	//Function that returns the id of the player who owns the weapon
	public int getPlayerId() {
		return PlayerId;
	}
	
	//Function that sets the id of the player who owns the weapon
	public void setPlayerId(int in_PlayerId) {
		PlayerId=in_PlayerId;
	}
	
	//Function that returns the type of the weapon (pistol,bow,sword)
	public String getType() {
		return type;
	}
	
	//Function that sets the type of the weapon (pistol,bow,sword)
	public void setType(String in_type) {
		type = in_type;
	}
		
	
	
	
	

}
