package imann.puzzlefactory.puzzles;
import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;
import imann.puzzlefactory.puzzles.components.constraints.AllValues;
import imann.puzzlefactory.puzzles.components.constraints.Constraint;
import imann.puzzlefactory.puzzles.components.constraints.SuDoKuUnique;
import imann.puzzlefactory.puzzles.components.constraints.Unique;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class SuDoKu extends Puzzle {
	private Color [] c1 = {Color.BLUE, Color.RED, Color.GREEN};
	private Color [] c2 = {Color.CYAN, Color.YELLOW, Color.MAGENTA};
	private Color [] c3 = {Color.ORANGE, Color.PINK, Color.RED};
	private Color [][] colors = {c1, c2, c3}; 
	private static String[] PosVals = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 "};
	private static String[] StaticVals = {"1 ", "2 ", "3 ", "4 ", "5 ", "6 ", "7 ", "8 ", "9 "};
	private String [] r1 = {"5 ", "3 ", " ", "6 ", "7 ", "8 ", "9 ", "1 ", "2 "};
	private String [] r2 = {"6 ", "7 ", " ", "1 ", "9 ", "5 ", " ", "4 ", " "};
	private String [] r3 = {" ", "9 ", "8 ", "3 ", "4 ", " ", " ", "6 ", " "};
	private String [] r4 = {"8 ", " ", "9 ", " ", "6 ", " ", " ", " ", "3 "};
	private String [] r5 = {"4 ", "2 ", " ", "8 ", " ", "3 ", "7 ", " ", "1 "};
	private String [] r6 = {"7 ", " ", " ", " ", "2 ", " ", " ", " ", "6 "};
	private String [] r7 = {" ", "6 ", "1 ", "5 ", " ", "7 ", "2 ", "8 ", " "};
	private String [] r8 = {" ", "8 ", " ", "4 ", "1 ", "9 ", " ", " ", "5 "};
	private String [] r9 = {"3 ", " ", "5 ", " " , "8 ", " ", "1 ", "7 ", "9 "};
	private String [][] spots = {r1, r2, r3, r4, r5, r6, r7, r8, r9};
	private String [] s1 = {"5 ", "3 ", "4", "6", "7 ", "8", "9", "1", "2"};
	private String [] s2 = {"6 ", "7", "2", "1 ", "9 ", "5 ", "3", "4", "8"};
	private String [] s3 = {"1", "9 ", "8 ", "3", "4", "2", "5", "6 ", "7"};
	private String [] s4 = {"8 ", "5", "9", "7", "6", "1", "4", "2", " 3"};
	private String [] s5 = {"4 ", "2", "6", "8 ", "5", "3 ", "7", "9", "1 "};
	private String [] s6 = {"7 ", "1", "3", "9", "2 ", "4", "8", "5", "6 "};
	private String [] s7 = {"9", "6", "1", "5", "3", "7", "2", "8", "4"};
	private String [] s8 = {"2", "8", "7", "4 ", "1 ", "9 ", "6", "3", "5 "};
	private String [] s9 = {"3", "4", "5", "2" , "8 ", "6", "1", "7 ", "9 "};
	private String [][] solution = {s1, s2, s3, s4, s5, s6, s7, s8, s9};
	private boolean debugging = false;
 	private Constraint [] constraints = new Constraint [27];
	public SuDoKu() {
		super(9, 9, PosVals);
		puzzleName = "Su Do Ku";
		reorderPuzzleList(puzzleName);
		hasColors(true);
		setColors();
		setSpots();
		initConstraints();
	}

	@Override
	public boolean constraintsSatisfied() {
		boolean empty = true;
		outerloop:
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				if (!allSpots()[x][y].equals(" ") && !isStaticValue(allSpots()[x][y].getValue())) {
					empty = false;
					break outerloop;
				}
			}
		}
		if (empty)  {
			return false;
		}
		for (Constraint c: constraints) {
			if (!c.satisfied()) {
				return false;
			}
		}
		return true;
	}
	
	public void solve () {
		if (debugging) {
			for (int x = 0; x < getColumns(); x++) {
				for (int y = 0; y < getRows(); y++) {
					allSpots()[x][y].setValue(solution[x][y]);
				}
			}
		} else super.solve();
		
	}
	
	@Override 
	public String defaultValue (int x, int y) {
		return spots[y][x];
	}

	private void setColors () {
		Color[][] all = new Color[getColumns()][getRows()];
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				all[x][y] = colors[x/3][y/3];
			}
		}
		super.setColors(all);
		
	}
	
	private void initConstraints () {
		int index = 0;
		for (int x = 0; x < getColumns(); x++) {
			Spot [] temp = new Spot[getRows()];
			for (int y = 0; y < getRows(); y++) {
				temp[y] = allSpots()[x][y];
			}
			constraints[index] = new SuDoKuUnique(this);
			constraints[index++].setSpots(temp);
			
		}
		for (int y = 0; y < getRows(); y++) {
			Spot [] temp = new Spot[getColumns()];
			for (int x = 0; x < getColumns(); x++) {
				temp[x] = allSpots()[x][y];
			}
			constraints[index] = new SuDoKuUnique(this);
			constraints[index++].setSpots(temp);
		}
		Entry[][]boxes = new Entry[3][3];
		for (int x = 0; x < getColumns()/3; x++) {
			for (int y = 0; y < getRows()/3; y++) {
				boxes[x][y] = new Entry();
			}
		}
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				boxes[x/3][y/3].entries.add(allSpots()[x][y]);
			}
		}
		for (int x = 0; x < getColumns()/3; x++) {
			for (int y = 0; y < getRows()/3; y++) {
				constraints[index] = new SuDoKuUnique(this);
				constraints[index++].setSpots(boxes[x][y].entries.toArray(new Spot[0]));
			}
		}
	}
	
	//code adapted from: http://stackoverflow.com/questions/16992561/java-2d-array-of-arraylists
	class Entry {
	    final List<Spot> entries = new ArrayList<Spot>();
	}
	
	private void setSpots () {
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				allSpots()[x][y].setValue(spots[x][y]);
			}
		}
	}
}
