package ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import api.Conversions;
import entities.RenderingDemo;
import entities.TetrisGame;
import ui.components.ActiveTetromino;
import ui.components.GameBorder;
import ui.components.NextTetromino;
import ui.components.PlayArea;
import ui.components.QuitButton;
import ui.components.ScoreIndicator;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TetrisGameUI extends JFrame {

	private static final long serialVersionUID = -4294448890074857524L;
	
	public static final int GAME_WIDTH = 416;
	public static final int GAME_HEIGHT = 512;
	public static final int FRAME_WIDTH = 10;
	public static final int FRAME_HEIGHT = 32;
	
	private JPanel contentPane;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, GAME_WIDTH + FRAME_WIDTH, GAME_HEIGHT + FRAME_HEIGHT);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//set the logical and device drawing dimensions
		Conversions.setLogicalDimensions(GAME_WIDTH, GAME_HEIGHT);
		Conversions.setDeviceDimensions(contentPane.getSize());
		
		//start the game
		TetrisGame game = new RenderingDemo();

		//create game UI components
		PlayArea playArea = new PlayArea(game);
		ScoreIndicator scoreIndicator = new ScoreIndicator(game);
		ActiveTetromino activeTetromino = new ActiveTetromino(game);
		NextTetromino nextTetromino = new NextTetromino(game);
		GameBorder border = new GameBorder(GAME_WIDTH, GAME_HEIGHT);
		QuitButton quitButton = new QuitButton();
		
		//add each component to the UI
		contentPane.add(playArea);
		contentPane.add(scoreIndicator);
		contentPane.add(activeTetromino);
		contentPane.add(nextTetromino);
		contentPane.add(border);
		contentPane.add(quitButton);
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
}
