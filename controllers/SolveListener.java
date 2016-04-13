package imann.puzzlefactory.controllers;
import imann.puzzlefactory.gui.PuzzleGUI;
import imann.puzzlefactory.puzzles.components.Puzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SolveListener implements ActionListener {
	private Puzzle p;
	private PuzzleGUI g;
	public SolveListener(Puzzle p, PuzzleGUI g) {
		this.p = p;
		this.g = g;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		p.solve();
		g.updateSpots();
	}

}
