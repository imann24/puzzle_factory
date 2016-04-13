package imann.puzzlefactory.controllers;
import imann.puzzlefactory.puzzles.components.Puzzle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;


public class SwitchGameListener implements ActionListener {
	private Puzzle p;
	
	public SwitchGameListener(Puzzle p) {
		this.p = p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		String game = (String) cb.getSelectedItem();
		RunPuzzle.switchGame(game);
	}

}
