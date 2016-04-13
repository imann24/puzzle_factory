package imann.puzzlefactory.gui;
import imann.puzzlefactory.puzzles.components.Spot;

import javax.swing.JLabel;


public class Label extends SpotGUI {
	public Label (Spot s, PuzzleGUI gui) {
		super(s, gui);
		this.setEnabled(false);
		this.add(new JLabel(s.getValue()));
		isStatic(true);
	}
	
	public Label (String s) {
		this.add(new JLabel(s));
	}
}
