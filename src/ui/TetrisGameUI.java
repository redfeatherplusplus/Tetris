// Author: Daren Cheng
// Class: CS 6366
// Date: 9/18/16
//
// Desc: Renders a Tetris game with minimal interaction.

package ui;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import api.Conversions;
import entities.RenderingDemo;
import entities.TetrisGame;
import ui.components.ActiveTetromino;
import ui.components.GameBorder;
import ui.components.NextTetromino;
import ui.components.PauseIndicator;
import ui.components.PlayArea;
import ui.components.QuitButton;
import ui.components.ScoreIndicator;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

public class TetrisGameUI extends JFrame {

	private static final long serialVersionUID = -4294448890074857524L;
	
	public static final int GAME_WIDTH = 416;
	public static final int GAME_HEIGHT = 512;
	public static final int FRAME_WIDTH = 10;
	public static final int FRAME_HEIGHT = 32;
	
	private JPanel contentPane;
	private GameBorder gameBorder;
	private PlayArea playArea;
	private ActiveTetromino activeTetromino;
	private NextTetromino nextTetromino;
	private ScoreIndicator scoreIndicator;
	private PauseIndicator pauseIndicator;
	private QuitButton quitButton;
	
	private TetrisGame game;
	private Timer gameUpdateTimer;
	
	//launch application
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TetrisGameUI ui = new TetrisGameUI();
					ui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//default constructor
	public TetrisGameUI() {
		//setup frame and content pane
		setTitle("Tetris!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, GAME_WIDTH + FRAME_WIDTH, GAME_HEIGHT + FRAME_HEIGHT);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//set the logical and device drawing dimensions
		Conversions.setLogicalDimensions(GAME_WIDTH, GAME_HEIGHT);
		Conversions.setDeviceDimensions(contentPane.getSize());
		
		//start the game
		game = new TetrisGame();

		//create game UI components
		gameBorder = new GameBorder(GAME_WIDTH, GAME_HEIGHT);
		playArea = new PlayArea(game);
		activeTetromino = new ActiveTetromino(game);
		nextTetromino = new NextTetromino(game);
		scoreIndicator = new ScoreIndicator(game);
		pauseIndicator = new PauseIndicator(game);
		quitButton = new QuitButton();
		
		//add each component to the UI
		//these are rendered in LIFO order
		contentPane.add(quitButton);
		contentPane.add(pauseIndicator);
		contentPane.add(scoreIndicator);
		contentPane.add(nextTetromino);
		contentPane.add(activeTetromino);
		contentPane.add(playArea);
		contentPane.add(gameBorder);
		
		//add UI mouse events to the content pane
		addMouseEvents();
		
		//create and start a timer to periodically update the game
		gameUpdateTimer = new Timer();
		gameUpdateTimer.schedule(new TimerTask() {
	        public void run() {
	            game.update();
	            repaint();
	        }
	    }, 0, game.getUpdateInterval());
	}

	//adds mouse listeners and mouse pressed events to the content pane
	public void addMouseEvents() {
		
		//this method should be removed in favor of each component
		//handling its own mouse events. At the moment this cannot 
		//done as component bound computation is not implemented
		
		contentPane.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				float x = Conversions.toLogicalX(event.getX());
				float y = Conversions.toLogicalY(event.getY());

				//close application when mouse pressed in quit button
				if (QuitButton.FRAME.contains(x, y)) {
					System.exit(0);
				}
			}
		});
		
		contentPane.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent event) {
				float x = Conversions.toLogicalX(event.getX());
				float y = Conversions.toLogicalY(event.getY());
				
				//must check both if the frame contains the mouse and if
				//it does not contain the mouse, cannot use if/else since 
				//an interrupt may/will occur between the if and else
				
				//pause game when play area is hovered
				if (PlayArea.FRAME.contains(x, y) && !pauseIndicator.isHovered()) {
					pauseIndicator.setHovered(true);
					repaint();
				}
				if (!PlayArea.FRAME.contains(x, y) && pauseIndicator.isHovered()) {
					pauseIndicator.setHovered(false);
					repaint();
				}
				
				//animate quit button on hover
				if (QuitButton.FRAME.contains(x, y) && !quitButton.isHovered()) {
					quitButton.setHovered(true);
					repaint();
				}
				if (!QuitButton.FRAME.contains(x, y) && quitButton.isHovered()) {
					quitButton.setHovered(false);
					repaint();
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	//called on each resize
	@Override
 	public void validate() {
		super.validate();

		//update the device drawing dimensions for the game
		Conversions.setDeviceDimensions(contentPane.getSize());
		
		//update bounds of child components
		for(Component component : contentPane.getComponents())
			component.setBounds(0, 0, 
					contentPane.getSize().width, 
					contentPane.getSize().height);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
	}
}
