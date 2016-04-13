package imann.puzzlefactory.controllers;
import imann.puzzlefactory.gui.PuzzleGUI;
import imann.puzzlefactory.puzzles.Akari;
import imann.puzzlefactory.puzzles.Hitori;
import imann.puzzlefactory.puzzles.Kakurasu;
import imann.puzzlefactory.puzzles.Nonogram;
import imann.puzzlefactory.puzzles.Skyscraper;
import imann.puzzlefactory.puzzles.Strimko;
import imann.puzzlefactory.puzzles.SuDoKu;
import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;

import java.awt.*;
import java.awt.event.WindowEvent;

import javax.swing.*;

public class RunPuzzle {
	private static Puzzle g;
	private static PuzzleGUI gui;
	private static Spot[] theSpots;
	
	public RunPuzzle () {

	}

	public static void main (String[] args) {
		g = new Skyscraper();
		gui = new PuzzleGUI(g);
		theSpots = g.getSpots();
		CreateGUI();
		
	}

	public boolean label (int i, Spot[] theSpots, Puzzle g, String[] possibleValues) {
		return true;
	}
	
	public static void switchGame (String gameName) {
		if (gameName.equals("Strimko")) 
			g = new Strimko();
		else if (gameName.equals("Sky Scrapers")) 
			g = new Skyscraper();
		else if (gameName.equals("Su Do Ku")) 
			g = new SuDoKu();
		else if (gameName.equals("Hitori"))
			g = new Hitori();
		else if (gameName.equals("Akari"))
			g = new Akari();
		else if (gameName.equals("Paint by Numbers"))
			g = new Nonogram();
		else if (gameName.equals("Kakurasu"))
			g = new Kakurasu();
		theSpots = g.getSpots();
		JFrame oldGUI = gui;
		oldGUI.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		gui = new PuzzleGUI(g);
		CreateGUI();
		oldGUI.dispatchEvent(new WindowEvent(gui, WindowEvent.WINDOW_CLOSING));
	}
	
	private static void CreateGUI() {
		gui.init();
		gui.pack();
		gui.setVisible(true);
		gui.setSize(new Dimension(500, 500));
	}
}