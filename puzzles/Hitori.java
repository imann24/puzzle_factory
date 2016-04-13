package imann.puzzlefactory.puzzles;

import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;
import imann.puzzlefactory.puzzles.components.constraints.Constraint;
import imann.puzzlefactory.puzzles.components.constraints.HitoriUnique;
import imann.puzzlefactory.puzzles.components.constraints.MatchingValue;
import imann.puzzlefactory.puzzles.components.constraints.NotAdjacent;

import java.util.ArrayList;


public class Hitori extends Puzzle {
	public int minShaded = 0;
	private String [] r1 = {"1 ", "4 ", "1 ", "2 ", "4 "};
	private String [] r2 = {"2 ", "4 ", "3 ", "4 ", "1 "};
	private String [] r3 = {"4 ", "1 ", "4 ", "3 ", "3 "};
	private String [] r4 = {"3 ", "1 ", "4 ", "5 ", "2 "};
	private String [] r5 = {"5 ", "2 ", "1 ", "4 ", "3 "};
	private String [][] spots = {r1, r2, r3, r4, r5};
	private String [] s1 = {"1 ", "4 ", "1X", "2 ", "4X"};
	private String [] s2 = {"2 ", "4X", "3 ", "4 ", "1 "};
	private String [] s3 = {"4 ", "1 ", "4X", "3 ", "3X"};
	private String [] s4 = {"3 ", "1X", "4 ", "5 ", "2 "};
	private String [] s5 = {"5 ", "2 ", "1 ", "4X", "3 "};
	private String [][] solution = {s1, s2, s3, s4, s5};
	private Constraint [] Constraints;
	private static String [] DefaultValues = {"1 ", "2 ", "3 ", "4 ", "5 "};
	private static String [] PosValues  = {"1+", "1X", "2+", "2X", "3+", "3X", "4+", "4X", "5+", "5X"};
	private int minShadedInRow [];
	private int minShadedInColumn [];
	private boolean debugging = false;
	public Hitori() {
		super(5, 5, PosValues);
		puzzleName = "Hitori";
		reorderPuzzleList(puzzleName);
		setValues();		
		isToggleable(true);
		minShadedInRow = new int [getRows()];
		minShadedInColumn = new int [getColumns()];
		minimumCrossedOut();
		initConstraints();		
	}
	
	@Override
	public String [] getPossibleValues () {
		return PosValues;
	}
	@Override
	public boolean constraintsSatisfied() {
		if (unstarted())
			return false;
		for (Constraint c: Constraints) {
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
	public void restart () {
		setValues();
	}
	
	public boolean noneCrossedOut () {
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				if (allSpots()[x][y].getValue().charAt(1) == 'X') {
					return false;
				}
			}
		}
		return true;
	}
	
	@Override
	public String defaultValue (int x, int y) {
		return spots[x][y];
	}
	
	@Override 
	public boolean isDefaultValue (String s) {
		for (int i = 0; i < DefaultValues.length; i++) {
			if (s.equals(DefaultValues[i]))
				return true;
		}
		return false;
	}
	
	public boolean isShaded (Spot s) {
		if (s.getValue().charAt(1) == ' ' || s.getValue().charAt(1) == '+') {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean toggleSpot (Spot s) {
		boolean b = !(isShaded(s));
		if (b) {
			s.setValue (s.getValue().charAt(0) + "X");
		} else {
			s.setValue(s.getValue().charAt(0) + "+");
		}
		return b;
	}
	
	private boolean unstarted () {
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				if (allSpots()[x][y].getValue().length() == 2) {
					if (allSpots()[x][y].getValue().charAt(1) == '+' || allSpots()[x][y].getValue().charAt(1) == 'X')
						return false;
				}
			}
		}
		return true;
	}
	
	private void initConstraints () {
		//one HitoriUnique for every row and every column (10)
		//one NotAdjacent for every spot (25)
		//one MatchingValue for every spot (25)
		int index = 0;
		Constraints = new Constraint [getRows() + getColumns() + 2 * getRows() * getColumns()];
		for (int i = 0; i < getRows(); i++) {
			Constraints[index] = new HitoriUnique (this, minShadedInRow[i]);
			Constraints[index++].setSpots(allSpots()[i]);
		}
		for (int i = 0; i < getColumns(); i++) {
			Spot[] s = new Spot[getRows()];
			for (int y = 0; y < getRows(); y++) {
				s[y] = allSpots()[y][i];
			}
			Constraints[index] = new HitoriUnique(this, minShadedInColumn[i]);
			Constraints[index++].setSpots(s);
		}
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				Constraints[index] = new NotAdjacent(this);
				Constraints[index++].setSpots(addCross(allSpots()[x][y], x, y).toArray(new Spot[0]));
				Constraints[index++] = new MatchingValue(this, allSpots()[x][y], spots[x][y]);
			}
		}
	}
	//for this implementation, if a number has an "X" after it, it is filled in, if not it is not
	//there will need to be a 2d array of numbers that stores the origin values for comparison
	private void setValues () {
		for (int x = 0; x < getColumns(); x++) {
			for(int y = 0; y < getRows(); y++) {
				allSpots()[x][y].setValue(spots[x][y]);
			}
		}
	}
	
	private void minimumCrossedOut () {
		ArrayList<Character> temp = new ArrayList<Character>();
		for (int x = 0; x < getColumns(); x++) {
			int count = 0;
			temp.clear();
			for (int y = 0; y < getRows(); y++) {
				char c = allSpots()[x][y].getValue().charAt(0);
				if (!temp.contains(c)) {
					temp.add(c);
				} else {
					count++;
				}
			}
			minShadedInRow[x] = count;
		}
		for (int y = 0; y < getRows(); y++) {
			int count = 0;
			temp.clear();
			for (int x = 0; x < getColumns(); x++) {
				Spot spot = allSpots()[x][y];
				char c = spot.getValue().charAt(0);
				if (!temp.contains(c)) {
					temp.add(c);
				} else {
					count++;
				}
				
			}
			minShadedInColumn[y] = count;
		}
	}
	
}
