package hungergames;

import java.awt.Graphics;

public abstract class States {

private static States currentState=null;
	
	public static void setState(States states) {
		currentState=states;
	}
	
	public static States getState() {
		return currentState;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
}
