package imann.puzzlefactory.puzzles;
import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;
import imann.puzzlefactory.puzzles.components.constraints.Constraint;
import imann.puzzlefactory.puzzles.components.constraints.Shaded;


public class Nonogram extends Puzzle {
	private static String [] PosValues = {" ", "+", "X"};
	private static String [] left = {"2 1", "1 3", "1 2", "3", "4", "1"};
	private static String [] top = {"1", "5", "2", "5", "2 1", "2"};
	private Constraint [] constraints = new Constraint[getRows() + getColumns()];
	private String [] s1 = {"X", "X", "+", "+", "+", "X"};
	private String [] s2 = {"+", "X", "+", "X", "X", "X"};
	private String [] s3 = {"+", "X", "+", "X", "X", "+"};
	private String [] s4 = {"+", "X", "X", "X", "+", "+"};
	private String [] s5 = {"+", "X", "X", "X", "X", "+"};
	private String [] s6 = {"+", "+", "+", "X", "+", "+"};
	private String [][] solution = {s1, s2, s3, s4, s5, s6}; 
	private boolean debugging = false;
	
	public Nonogram() {
		super(6, 6, PosValues);
		puzzleName = "Paint by Numbers";
		reorderPuzzleList(puzzleName);
		initConstraints();
		toggleBorder(true);
		isToggleable(true);
		setBorders(top, new String[0], left, new String[0]);
	}


	@Override
	public boolean constraintsSatisfied() {
		boolean empty = true;
		outerloop: //label Syntax adapted from: http://stackoverflow.com/questions/886955/breaking-out-of-nested-loops-in-java
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				if (!allSpots()[x][y].getValue().equals(" ")) {
					empty = false;
					break outerloop;
				}
			}
		}
		if (empty)
			return false;
		for (Constraint c: constraints) {
			if (!c.satisfied())
				return false;
		}
		return true;
	}
	
	public boolean isShaded (Spot s) {
		if (s.getValue().equals(" ") || s.getValue().equals("+")) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean toggleSpot (Spot s) {
		boolean b = !(isShaded(s));
		if (b) {
			s.setValue ("X");
		} else {
			s.setValue("+");
		}
		return b;
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
	private void initConstraints() {
		//we need the following constraints:
		//number of shaded blocks per row/column
		//length of shaded blocks in row/column
		int index = 0;
		for (int x = 0; x < getColumns(); x++) {
			Spot[] s = new Spot[getRows()];
			for (int y = 0; y < getRows(); y++) {
				s[y] = allSpots()[x][y];
			}
			constraints[index] = new Shaded(this, left[x]);
			constraints[index++].setSpots(s);
		}
		for (int y = 0; y < getRows(); y++) {
			Spot[] s = new Spot[getRows()];
			for (int x = 0; x < getRows(); x++) {
				s[x] = allSpots()[x][y];
			}
			constraints[index] = new Shaded(this, top[y]);
			constraints[index++].setSpots(s);
		}
	}

}
