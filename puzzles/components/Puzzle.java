package imann.puzzlefactory.puzzles.components;

import imann.puzzlefactory.puzzles.components.constraints.Constraint;

import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Puzzle {
	public static String [] Puzzles = {"Sky Scrapers", "Strimko", "Akari", "Hitori", "Su Do Ku", "Paint by Numbers", "Kakurasu"};
	public String puzzleName;
	private Spot [][] spots;
	private int rows;
	private int columns;
	private boolean hasBorder;
	private boolean hasColors;
	private boolean hasStaticValues = false;
	private boolean isHitori = false;
	private boolean checkStaticValuesNormally = false;
	private String[] staticValues;
	private String[] values;
	private String[] top;
	private String[] bottom;
	private String[] left;
	private String[] right;
	private String[][] borders = new String[4][];
	private Color[][] buttonColors;
	private Constraint[][] constraints;
	private Solver s;
	private boolean debugging = true;
	public Puzzle (int numCol, int numRow, String[] posVals) {
		spots = new Spot[numRow][numCol];
		rows = numRow;
		columns = numCol;
		values = posVals;
		populateSpots();
		s = new Solver(this);
	}
	
	public Puzzle (String fileName) {
		ReadFile r = null;
		try {
			 r = new ReadFile(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("File not found. Program terminated");
			System.exit(0);
		}
		spots = new Spot[r.getColumns()][r.getRows()];
		rows = r.getRows();
		columns = r.getColumns();
		values = r.getPosValues();
		spots = new Spot[columns][rows];
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				if (r.getValues()[x][y].equals("B"))
					spots[x][y] = new Spot(" ");
				else spots[x][y] = new Spot(r.getValues()[x][y]);
			}
		}
		s = new Solver(this);
	}

	public Puzzle () {
		this(3, 3, new String[]{"1", "2", "3"});
	}
	
	private void populateSpots () {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++)
				spots[x][y] = new Spot (" ");
		}
	}

	public boolean checkConstraints () {
		return true;
	}

	public Spot[] getSpots () {
		Spot [] s = new Spot [rows * columns];
		int n = 0;
		//nested for loop that converts from 2d to 1d array
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				s[n++] = spots[i][j];
			}
		}
		return s;
	}
	
	public int getRows () {
		return rows;
	}
	
	public int getColumns () {
		return columns;
	}
	
	public boolean hasBorder () {
		return hasBorder;
	}
	
	public void toggleBorder (boolean hasBorder) {
		this.hasBorder = hasBorder;
	}
	
	public void setBorders (String[] top, String[] bottom, String[] left, String[] right) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		borders[0] = top;
		borders[1] = bottom;
		borders[2] = left;
		borders[3] = right;
	}
	
	public String[] getVals () {
		return values;
	}
	
	public void restart () {
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				spots[x][y].setValue(" ");
			}
		}
	}
	
	public void solve () {
		s.label(spots, 0, 0);
	}
	
	public void reorderPuzzleList(String puzzle) {
		int index = -1;
		for (int i = 0; i < Puzzles.length; i++) {
			if (puzzle.equals(Puzzles[i]))
				index = i;
		}
		if (index != -1) {
			String temp = Puzzles[0];
			Puzzles[0] = puzzle;
			Puzzles[index] = temp;
		}
			
	}
	
	public String toString () {
		String s = "";
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				if (!spots[x][y].getValue().equals(" "))
						s += (spots[x][y].getValue() + " ");
				else s+= "B ";
			}
			s += "\n";
		}
		//s += "Constraints Satisfied: " + constraintsSatisfied();
		return s;
	}
	
	public String[][] getBorders () {
		return borders;
	}
	
	public Constraint[][] getConstraints () {
		return constraints;
	}
	
	public void setConstraints (Constraint [][] c) {
		constraints = c;
	}
	
	public Spot[][] allSpots () {
		return spots;
	}
	
	public boolean hasColors () {
		return hasColors;
	}
	
	public void hasColors (boolean b) {
		hasColors = b;
	}
	
	public void setColors (Color [][] c) {
		buttonColors = c;
	}
	
	public Color[][] getColors () {
		return buttonColors;
	}
	
	public boolean hasStaticValues () {
		return hasStaticValues;
	}
	
	public void hasStaticValues (boolean b) {
		hasStaticValues = b;
	}
	
	public void setStaticValues (String[]s) {
		staticValues = s;
	}
	
	public String[] getStaticValues () {
		return staticValues;
	}
	
	public boolean isStaticValue (String s) {
		if (!hasStaticValues)
			return false;
		for (int i = 0; i < staticValues.length; i++) {
			if (s.equals(staticValues[i]))
				return true;
		}
		return false;
	}
	
	public ArrayList<Spot> addCross (Spot s, int x, int y) {
		ArrayList<Spot> spots = new ArrayList<Spot>();
		spots.add(s);
		if (rangeCheck(x-1, y)) {
			spots.add(allSpots()[x-1][y]);
		}
		if (rangeCheck(x+1, y)) {
			spots.add(allSpots()[x+1][y]);
		}
		if (rangeCheck(x, y-1)) {
			spots.add(allSpots()[x][y-1]);
		}
		if (rangeCheck(x, y+1)) {
			spots.add(allSpots()[x][y+1]);
		}
		return spots;
	}
	
	public boolean rangeCheck (int x, int y) {
		if (x < getColumns() && x >= 0 && y < getRows() && y >= 0)
			return true;
		else return false;
	}
	
	public void isToggleable(boolean b) {
		isHitori = b;
	}
	
	public boolean isToggleable () {
		return isHitori;
	}
	
	public String defaultValue (int x, int y) {
		return " ";
	}
	
	public boolean isDefaultValue (String s) {
		if (s.equals(" ")) {
			return true;
		} else return false;
	}
	//placeholder method for Hitori and Nonogram
	public boolean isShaded (Spot s) {
		return false;
	}
	
	//placeholder method for Hitori and Nonogram
	public boolean toggleSpot (Spot s) {
		return false;
	}
	
	public String[] getPossibleValues () {
		String[] s = new String[values.length-1];
		System.arraycopy(values, 1, s, 0, values.length-1);
		return s;
	}
	
	public boolean checkStaticValuesNormally () {
		return checkStaticValuesNormally;
	}
	
	public void checkStaticValuesNormally (boolean b) {
		checkStaticValuesNormally = b;
	}

	public abstract boolean constraintsSatisfied ();
}