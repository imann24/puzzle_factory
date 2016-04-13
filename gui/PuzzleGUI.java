package imann.puzzlefactory.gui;
import imann.puzzlefactory.controllers.RestartListener;
import imann.puzzlefactory.controllers.SolveListener;
import imann.puzzlefactory.controllers.SwitchGameListener;
import imann.puzzlefactory.puzzles.components.Puzzle;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PuzzleGUI extends JFrame{
	private Puzzle p;
	private Container pane;
	private Container game;
	private Container buttons;
	private SpotGUI [][] spots;
	public PuzzleGUI(Puzzle p) {
		this.p = p;
		spots = new SpotGUI[p.getColumns()][p.getRows()];
	}
	
	public void init () {
		pane = this.getContentPane();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(900, 850));
		createBottomButtons();
		createPuzzle();
		
	}
	
	public void printPuzzle () {
		System.out.println(p);
	}
	
	public void updateSpots () {
		for (int x = 0; x < p.getColumns();x++) {
			for (int y = 0; y < p.getRows(); y++) {
				spots[x][y].setValue();
			}
		}
		repaint();
	}
	
	public Dimension buttonSize () {
		return new Dimension (800/p.getColumns(), 600/p.getRows());
	}
	
	private void createBottomButtons () {
		JButton restart;
		JPanel puzzles;
		JComboBox selection;
		JButton solve;
		Dimension buttonSize = new Dimension(100, 100);
		pane.add((buttons = new JPanel()), BorderLayout.PAGE_END);
		buttons.setLayout(new GridLayout(1, 3));;
		buttons.add((restart = new JButton("Restart")));
		restart.setPreferredSize(buttonSize);
		restart.addActionListener(new RestartListener(p, this));
		buttons.add((solve = new JButton("Solve Puzzle")));
		solve.addActionListener(new SolveListener(p, this));
		buttons.add((puzzles = new JPanel()));
		puzzles.add(new JLabel ("Puzzles:"));
		puzzles.add((selection = new JComboBox()));
		for (int i = 0; i < Puzzle.Puzzles.length; i++) {
			selection.addItem(Puzzle.Puzzles[i]);
		}
		puzzles.setPreferredSize(buttonSize);
		selection.addActionListener(new SwitchGameListener(p));
		solve.setPreferredSize(buttonSize);
	}
	
	private void createPuzzle () {
		game = new JPanel();
		game.setLayout(new BorderLayout());
		Container gameSpots = new JPanel();
		if (p.hasBorder())
			createBorder();
		gameSpots.setLayout(new GridLayout(p.getRows()+1, p.getColumns()+1));
		gameSpots.setPreferredSize(new Dimension(300, 75 * p.getRows()));
		int n = 0;
		for (int i = 0; i < p.getRows(); i++) {
			for (int j = 0; j < p.getColumns(); j++) {
				SpotGUI g;
				if (p.isToggleable()) {
					gameSpots.add(g = new SpotToggleGUI(p.getSpots()[n++], p, this));
				} else if (p.hasStaticValues() && p.isStaticValue(p.getSpots()[n].getValue())) {
					gameSpots.add(g = new Label(p.getSpots()[n++], this));
				} else {
					gameSpots.add((g = new SpotGUI(p.getSpots()[n++], p.getVals(), this)));
				}
				spots[j][i] = g;
			}
		}
		if (p.hasColors()) {
			for (int x = 0; x < p.getColumns(); x++) {
				for (int y = 0; y < p.getRows(); y++) {
					spots[x][y].setOpaque(true);
					spots[x][y].setBackground(p.getColors()[x][y]);
				}
			}
		}
		game.add(gameSpots, BorderLayout.CENTER);
		pane.add(game, BorderLayout.PAGE_START);
	}
	
	private void createBorder () {
		Container top = new JPanel();
		Container bottom = new JPanel();
		Container left = new JPanel();
		Container right = new JPanel();
		top.setLayout(new GridLayout(1, p.getColumns()));
		bottom.setLayout(new GridLayout(1, p.getColumns()));
		left.setLayout(new GridLayout(p.getRows(),1));
		right.setLayout(new GridLayout(p.getRows(),1));
		for (int x = 0; x < p.getColumns(); x++) {
			if (p.getBorders()[0].length != 0)
				top.add((new SimpleLabel(p.getBorders()[0][x])));
			if (p.getBorders()[1].length != 0)
				bottom.add((new SimpleLabel(p.getBorders()[1][x])));
		}
		for (int y = 0; y < p.getRows(); y++) {
			if (p.getBorders()[2].length != 0) 
				left.add((new SimpleLabel(p.getBorders()[2][y])));
			if (p.getBorders()[3].length != 0)
			right.add((new SimpleLabel(p.getBorders()[3][y])));
		}
		game.add(left, BorderLayout.LINE_START);
		game.add(right, BorderLayout.LINE_END);
		game.add(top, BorderLayout.PAGE_START);
		game.add(bottom, BorderLayout.PAGE_END);
	}
}
