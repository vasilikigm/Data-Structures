package HungerGames;


public class Food {
	int id;		//Supply's id
	int x;		//Coordinate x of the board's tile (where the supply is located)
	int y;		//Coordinate y of the board's tile (where the supply is located)
	int points;	//Points the player gains from eating the supply
	//constructor No.1
	//Empty constructor
	Food(){
		
	}

	//constructor No.2
	//Initializes the variables of the object that is created, with the variables of another object of this class
	Food(Food food){
		id=food.id;
		x=food.x;
		y=food.y;
		points=food.points;
	}
	//Function that returns the supply's id
	public int getId() {
		return id;
	}
	
	//Function that sets the supply's id
	public void setId(int in_id) {
		id=in_id;
	}
	
	//Function that returns the coordinate x of the board's tile where the supply is located
	public int getX() {
		return x;
	}
	
	//Function that sets the coordinate x of the board's tile where the supply is located
	public void setX(int in_x) {
		x=in_x;
	}
	
	//Function that returns the coordinate y of the board's tile where the supply is located
	public int getY() {
		return y;
	}
	
	//Function that sets the coordinate y of the board's tile where the supply is located
	public void setY(int in_y) {
		y=in_y;
	}
	
	//Function that returns the points the player gains from eating the supply
	public int getPoints() {
		return points;
	}
	
	//Function that sets the points the player gains from eating the supply
	public void setPoints(int in_points) {
		points=in_points;
	}
	
	
	
	
	
	
}
