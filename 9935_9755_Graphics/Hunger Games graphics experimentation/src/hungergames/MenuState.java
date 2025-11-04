package hungergames;

import java.awt.Graphics;

public class MenuState extends States{
	private Player player1,player2;
	private Board board;
	
	MenuState(Board board,Player player1,Player player2){
		this.board=board;
		this.player1=player1;
		this.player2=player2;
	}

	@Override
	public void tick() {
		player1.getClass();
	}

	@Override
	public void render(Graphics g) {
		g.fillRect(0, 0, 50, 50);
		
	}

	
}
