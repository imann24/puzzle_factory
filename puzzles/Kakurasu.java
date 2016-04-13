package imann.puzzlefactory.puzzles;

import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;
import imann.puzzlefactory.puzzles.components.constraints.Constraint;
import imann.puzzlefactory.puzzles.components.constraints.Sum;


public class Kakurasu extends Puzzle {
	private String [] top = {"1", "2", "3", "4", "5"};
	private String [] left = {"1", "2", "3", "4", "5"};
	private String [] bottom = {"10", "6", "7", "8", "11"};
	private String [] right = {"8", "8", "9", "1", "15"};
	private static String [] PosValues = {" ", "+", "X"};
	private String [] s1 = {"X", "X", "+", "+", "X"};
	private String [] s2 = {"+", "+", "X", "+", "X"};
	private String [] s3 = {"+", "+", "+", "X", "X"};
	private String [] s4 = {"X", "+", "+", "+", "+"};
	private String [] s5 = {"X", "X", "X", "X", "X"};
	private String [][] solution = {s1, s2, s3, s4, s5};
	private Constraint [] constraints;
	private boolean debugging = false;
	public Kakurasu() {
		super(5, 5, PosValues);
		puzzleName = "Kakurasu";
		reorderPuzzleList(puzzleName);
		toggleBorder(true);
		setBorders(top, bottom, left, right);
		isToggleable(true);
		constraints = new Constraint[getRows() + getColumns()];
		initConstraints();
	}

	@Override
	public boolean constraintsSatisfied() {
		boolean empty = true;
		outerloop:
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				if (!allSpots()[x][y].equals(" ")) {
					empty = false;
					break outerloop;
				}
			}
		}
		if (empty)
			return false;
		for (int i = 0; i < constraints.length; i++) {
			if (!constraints[i].satisfied())
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
	private void initConstraints () {
		int index = 0;
		for (int x = 0; x < getColumns(); x++) {
			Spot[]s = new Spot[getRows()];
			for (int y = 0; y < getRows(); y++) {
				s[y] = allSpots()[x][y];
			}
			constraints[index] = new Sum(this, right[x]);
			constraints[index++].setSpots(s);
		}
		
		for (int y = 0; y < getRows(); y++) {
			Spot[]s = new Spot[getRows()];
			for (int x = 0; x < getColumns(); x++) {
				s[x] = allSpots()[x][y];
			}
			constraints[index] = new Sum(this, bottom[y]);
			constraints[index++].setSpots(s);
		}
	}
}
