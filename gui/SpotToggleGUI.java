package imann.puzzlefactory.gui;

import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class SpotToggleGUI extends SpotGUI {
	private Spot s;
	private PuzzleGUI gui;
	private Puzzle p;
	private boolean shaded = false;
	private JLabel g;
	public SpotToggleGUI(Spot s, Puzzle p, PuzzleGUI gui) {
		super(s, gui);
		this.s = s;
		this.p = p;
		this.gui = gui;
		this.addActionListener(new HitoriListener());
		this.add(g = new JLabel(s.getValue()));
		Font font = new Font("Arial", Font.PLAIN, 20);
		g.setMinimumSize(new Dimension(200, 100));
		g.setFont(font);
		setBackground(Color.BLACK);
		g.setBackground(Color.BLACK);
		g.setForeground(Color.BLACK);
	}
	
	public void recolorSpot () {
		if (shaded) {
			setOpaque(true);
			g.setOpaque(true);
		} else {
			setOpaque(false);
			g.setOpaque(false);
		}
	}
	
	public void setValue () {
		shaded = p.isShaded(s);
		recolorSpot();
	}
	
	private void toggleSpot () {
		shaded = p.toggleSpot(s);
		recolorSpot();
	}
	
	class HitoriListener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			toggleSpot();
		}
	}
}
