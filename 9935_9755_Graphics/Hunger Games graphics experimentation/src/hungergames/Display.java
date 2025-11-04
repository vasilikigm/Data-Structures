package hungergames;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Display{
	//Creating a window for graphics
	
	private JFrame frame;
	private String title;
	public static int width,height;
	private Board board;

	
	public Display(Board board,String title,int width,int height) {
		this.title=title;
		Display.width=width;
		Display.height=height;
		this.board=board;
		
		createDisplay();
	}
	
	private void createDisplay(){
		
		frame=new JFrame(title);
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));

		//Making sure that the window closes properly
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(new GameManager(board));
		frame.setVisible(true);
	
		
	}
}
