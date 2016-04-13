package imann.puzzlefactory.controllers;
import imann.puzzlefactory.gui.PuzzleGUI;
import imann.puzzlefactory.gui.SpotGUI;
import imann.puzzlefactory.puzzles.components.Spot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;


public class SpotListener implements ActionListener{
	Spot s;
	SpotGUI g;
	PuzzleGUI gui;
	public SpotListener(Spot s, SpotGUI g, PuzzleGUI gui) {
		this.s = s;
		this.g = g;
		this.gui = gui;
	}
	//code from Oracle Documentation
	public void actionPerformed (ActionEvent e) {
		JComboBox cb = (JComboBox) e.getSource();
		String string = (String) cb.getSelectedItem();
		s.setValue(string);
	}

}
