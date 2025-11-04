package hungergames;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameManager extends JPanel implements Runnable{

	private Thread thread;
	private boolean running=false;
	private States gameState;
	private States menuState;
	private boolean startRunning=false;
	private Player player1;
	private Player player2;
	
	
	
	//BUTTON STUFF
	private JComboBox cb1;
	private JComboBox cb2;
	private JButton b;
	private JButton s;
	private JButton q;
	private JLabel l1,l2,l3;
	
	//Array that contains all player types
	//It will be used to create the JCombobox

	private Board board;
	//Variable with value false if the button "Generate Board" hasn't been clicked ,and true if it has.
	private boolean isButtonGenerateBoardClicked=false;
	//Variable with value false if the button "Start" hasn't been clicked ,and true if it has.
	private boolean isButtonStartClicked=false;
	//Variable with value true if it is the first time the PaintComponent method is called ,and false if it isn't
	private boolean isFirtsTimeCalled=true;
	
	
	//!!!!CONSTRUCTOR!!!!!
	
	public GameManager(Board board) {
		
		//------BUTTON STUFF!!-----
		
		setLayout(null);

		this.board=board;
		String[] playerType= {"Select player","player","heuristicPlayer"};
		b=new JButton("Generate Board");
		s=new JButton("Start");
		q=new JButton("Quit");
		cb1=new JComboBox(playerType);
		cb2=new JComboBox(playerType);
		this.board.setTILEWIDTH((Display.width-50*2)/board.getN());
		this.board.setTILEHEIGHT((Display.height-50*2)/board.getM());
		this.board.setRandomFoodType();
		

		l3=new JLabel("Round : "+Game.getRound());
		l3.setBounds(200, 600, 100, 50);
		l1=new JLabel("Player A");
		l1.setBounds(10,600,100,50);
		l2=new JLabel("Player B");
		l2.setBounds(500, 600, 100, 50);
		
		

		ArrayList<Integer[]>path=new ArrayList<Integer[]>();
		
		b.setBounds(190, 618, 130, 30);
		cb1.setBounds(40, 618, 130, 30);
		cb2.setBounds(520, 618, 130, 30);

		//b.setBounds(270, 600, 130, 30);
		s.setBounds(340, 618, 70, 30);
		q.setBounds(430, 618, 70, 30);
		
		//We want to be able to start the game only when the board has been generated (the button "Generate Board" has been clicked and disabled(as we set it to do when being clicked))
		b.setEnabled(false);
		s.setEnabled(false);
		
		//button "Generate Board" ActionListener (what to do if the button is clicked)
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				//If the button is clicked : 
				
				isButtonGenerateBoardClicked=true;
				
				cb1.setEnabled(false);
				cb2.setEnabled(false);
				b.setEnabled(false);
				s.setEnabled(true);
				repaint();
				
			}
		});	
		
		//button "Start" ActionListener (what to do if the button is clicked)
		s.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//If the button is clicked :
				
				isButtonStartClicked=true;

				repaint();
				s.setEnabled(false);
			
				
			}
		});
		
		//button "Quit" ActionListener (what to do if the button is clicked)
		q.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			
				
			}
		});
		
		//Left ComboBox ActionListeber (what to do if it is clicked)
		cb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cb1.getItemAt(cb1.getSelectedIndex())=="player") {
					player1=new Player(1, "Player1", board, 0, -board.getN()/2, -board.getM()/2, null, null, null);
				}
				else if(cb1.getItemAt(cb1.getSelectedIndex())=="heuristicPlayer") {
					player1=new HeuristicPlayer(1, "player1", board, 0, -board.getN()/2, -board.getM()/2, null, null, null,path,2,0);
				}
				else if(cb1.getItemAt(cb1.getSelectedIndex())=="Select player") {
					player1=null;
				}
				if(player1!=null && player2!=null) {
					if(!b.isEnabled())
						b.setEnabled(true);
				}
				if(b.isEnabled()) {
					if(player1==null)
						b.setEnabled(false);
				}
			}
		});
		
		
		//Left ComboBox ActionListeber (what to do if it is clicked)
		cb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cb2.getItemAt(cb2.getSelectedIndex())=="player") {
					player2=new Player(2, "Player2", board, 0, board.getN()/2, board.getM()/2, null, null, null);

					

				}
				else if(cb2.getItemAt(cb2.getSelectedIndex())=="heuristicPlayer") {
					player2=new HeuristicPlayer(2, "player1", board, 0, board.getN()/2, board.getM()/2, null, null, null,path,2,0);
					

				}
				else if(cb2.getItemAt(cb2.getSelectedIndex())=="Select player") {
					player2=null;

				}
				if(player1!=null && player2!=null) {
					if(!b.isEnabled())
						b.setEnabled(true);
				}
				if(b.isEnabled()) {
					if(player2==null)
						b.setEnabled(false);
				}
			}
		});
		
		
		
		
		
		add(b);
		add(q);
		add(s);
		add(cb1);
		add(cb2);
		repaint();
	}
	
	
	
	
	
	
	
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	
	
	
	
	//Function that initializes the game's graphics
	
	private void init() {
		gameState=new GameState(board,player1,player2);
		menuState=new MenuState(board,player1,player2);
		States.setState(gameState);

	}

	
	//Function that calls functions tick and render ,so that the games graphics can be displayed in the screen
	//In this case it can only move the player but in case we want o advance our game, we can program it to move other entities too (for example traps or supplies)
	@Override
	public void run() {

		init();
		startRunning =true;
		repaint();
		
		
	}
	
	//Function that updates all variables,positions of objects etc.
	private void tick() {
		if(States.getState()!=null)
			States.getState().tick();
	}
	
	

	//Function that draws everything to the screen
	private void render(Graphics g) {
		
		
		//Clear screen
		
		//Draw Here!! 
		if(States.getState()!=null)
			States.getState().render(g);
		
		//End drawing
		
		
		
	}
	//Function that draws stuff to the screen
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(isFirtsTimeCalled==true) {

			g.drawString("LET THE HUNGER GAMES BEGINN !!!", 200, 300);
			g.drawString("MAY THE ODDS BE EVER IN YOUR FAVOR", 230, 330);
			
			Assets.init();
			g.drawImage(Assets.player1,0,0,200,200,null);
			g.drawImage(Assets.player2,400,400,200,200,null);
			
			isFirtsTimeCalled=false;
			
		}
		
		
		
		if(isButtonGenerateBoardClicked) {
			g.clearRect(0, 0, Display.width, Display.height);
			board.renderBoard(g);
			board.renderFood(g);
			board.renderTrap(g);
			board.renderWeapon(g);
			isButtonGenerateBoardClicked=false;

			board.renderPlayer(g, player1, Assets.player1);
			board.renderPlayer(g, player2, Assets.player2);
			
		}
		
		if(isButtonStartClicked) {
			//We dont wan't to able to change our player's types after the game has started.
			cb1.setEnabled(false);
			cb2.setEnabled(false);
			//startRunning=true;
			isButtonStartClicked=false;
			start();
			
				
			}

		
		if(startRunning) {

			//frames/tick per second
			//Meaning we want to call functions tick and render fps=60 times per second
			int fps=60;
			//Maximum time our functions (tick and render) have to run ,in order to being called fps times per second
			double timePerTick=1000000000/fps;
			
			double delta=0;
			long now;
			//Current computer time in nanoseconds
			long lastTime=System.nanoTime();
			
			long timer=0;
			long ticks=0;
		

			while(running) {		
							
				now=System.nanoTime();
				delta+=(now-lastTime)/timePerTick;
				timer+=now-lastTime;
				lastTime=now;
				
				if(delta>=1) {
					tick();
					render(g);
					ticks++;
					delta--;
				}
				if(timer>=1000000000) {
					System.out.println("Ticks and frames : "+ticks);
					ticks=0;
					timer=0;
				}
				
				
				if(GameState.getStopThread()==true) {
					break;
				}
				
			}
			stop();

		}
	}
	
	
	
	
	//Function that starts the thread and also calls the function run()
	public synchronized void start() {
		if(running) {
			return;
		}

		
		running=true;
		thread=new Thread(this);

		thread.start();
	}
	
	//Function that stops the thread
	public synchronized void stop() {
		if(!running)
			return;
		running=false;
		System.out.println("stopedd");

		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	

}
