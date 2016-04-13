package imann.puzzlefactory.gui;
import imann.puzzlefactory.controllers.SpotListener;
import imann.puzzlefactory.puzzles.components.Spot;

import java.awt.*;

import javax.swing.*;

public class SpotGUI extends JButton {
	private Spot s;
	private String[] o;
	private JComboBox select;
	private JLabel d;
	private PuzzleGUI gui;
	private boolean isStatic = false;
	public SpotGUI(Spot s, String[] o, PuzzleGUI gui) {
		this.s = s;
		this.o = o;
		this.gui = gui;
		createSelector();
		this.setMinimumSize(gui.buttonSize());
	}
	
	public SpotGUI(Spot s, PuzzleGUI gui) {
		this.s = s;
		this.gui = gui;
	}
	
	public SpotGUI() {
		//empty constructor for border label
	}
	
	public void setValue () {
		if (!isStatic) {
			select.setSelectedItem(s.getValue());
		}
	}
	
	public void isStatic (boolean b) {
		isStatic = b;
	}
	
	private void createSelector() {
		this.add((select = new JComboBox()));
		select.addActionListener(new SpotListener(s, this, gui));
		for (int i = 0; i < o.length; i++) {
			select.addItem(o[i]);
		}
		setValue();
	}
}
