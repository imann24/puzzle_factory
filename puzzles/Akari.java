package imann.puzzlefactory.puzzles;

import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;
import imann.puzzlefactory.puzzles.components.constraints.Constraint;
import imann.puzzlefactory.puzzles.components.constraints.Illuminated;
import imann.puzzlefactory.puzzles.components.constraints.NumberOfLights;

import java.util.ArrayList;

public class Akari extends Puzzle {
	private static String [] PosValues = {" ", "Lit", "Unlit"};
	private static String [] StaticValues = {"S","X", "0", "1", "2", "3"};
	private String[] r1 = {" ", "Lit", "2", "Lit", "Unlit", "2", "Lit", " "};
	private String[] r2 = {"Lit", "3", "Unlit", " ", " ", "Lit", " ", "Unlit"};
	private String[] r3 = {" ", "Lit", "X", "X", "Lit", "3", "Unlit", "0"};
	private String[] r4 = {" ", " ", " ", " ", " ", "Lit", "X", "Unlit"};
	private String[] r5 = {" ", "X", "Lit", " ", " ", " ", " ", " "};
	private String[] r6 = {"2", "Lit", "3", "Lit", "X", "X", "Lit", " "};
	private String[] r7 = {"Lit", " ", "Unlit", " ", " ", "Unlit", "3", "Lit"};
	private String[] r8 = {" ", " ", "X", " ", "Lit", "2", "Lit", " "};
	private String[][] solution = {r1, r2, r3, r4, r5, r6, r7, r8};
	private boolean debugging = false;
	private ArrayList<Constraint> Constraints; //easier to use an array list because constraint layout changes based on the puzzle layout
	public Akari() {
		super(8, 8, PosValues);
		puzzleName = "Akari";
		reorderPuzzleList(puzzleName);
		hasStaticValues(true);
		setSpots();
		setStaticValues(StaticValues);
		initConstraints();
		
	}

	@Override
	public boolean constraintsSatisfied() {
		boolean empty = true;
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				if (!allSpots()[x][y].getValue().equals(" ") && !isStaticValue(allSpots()[x][y].getValue())) {
					empty = false;
				}
			}
		}
		if (empty) {
			return false;
		}
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
		setSpots();
	}
	
	private void initConstraints () {
		Constraints = new ArrayList<Constraint>();
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				if (allSpots()[x][y].getValue().equals(" ") || allSpots()[x][y].getValue().equals("S")){
					Spot[] s = new Spot[getRows() + getColumns()];
					for (int i = 0; i < getColumns(); i++) {
						s[i] = allSpots()[i][y];
					}
					for (int i = getColumns(); i < getColumns() + getRows(); i++) {
						s[i] = allSpots()[x][i-getColumns()];
					}
					Constraint c = new Illuminated(this);
					c.setSpots(s);
					Constraints.add(c);
				}
				if (isInt(allSpots()[x][y].getValue())) {
					ArrayList<Spot> s = addCross(allSpots()[x][y], x, y);
					Constraint c = new NumberOfLights(this);
					c.setSpots(s.toArray(new Spot[0]));
					Constraints.add(c);
				}
			}
		}			//allSpots()
	}
	
	//number check code adapted from: http://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-a-numeric-type-in-java
	private boolean isInt(String str)  {  
	  try  {  
	    int d = Integer.parseInt(str);  
	  }  catch(NumberFormatException nfe)  {  
	    return false;  
	  }  
	  return true;  
	}
	
	private void setSpots () {
//		String[] r1 = {" ", " ", "2", " ", " ", "2", " ", " "};
//		String[] r2 = {" ", "3", " ", " ", " ", " ", " ", " "};
//		String[] r3 = {" ", " ", "X", "X", " ", "3", " ", "0"};
//		String[] r4 = {" ", " ", " ", " ", " ", " ", "X", " "};
//		String[] r5 = {" ", "X", " ", " ", " ", " ", " ", " "};
//		String[] r6 = {"2", " ", "3", " ", "X", "X", " ", " "};
//		String[] r7 = {" ", " ", " ", " ", " ", " ", "3", " "};
//		String[] r8 = {" ", " ", "X", " ", " ", "2", " ", " "};
		
		
		String[] r1 = {"S", " ", "2", " ", " ", "2", " ", "S"};
		String[] r2 = {" ", "3", " ", "S", "S", " ", "S", " "};
		String[] r3 = {"S", " ", "X", "X", " ", "3", " ", "0"};
		String[] r4 = {"S", "S", "S", "S", "S", " ", "X", " "};
		String[] r5 = {" ", "X", " ", "S", "S", "S", "S", "S"};
		String[] r6 = {"2", " ", "3", " ", "X", "X", " ", "S"};
		String[] r7 = {" ", "S", " ", "S", "S", " ", "3", " "};
		String[] r8 = {"S", "S", "X", "S", " ", "2", " ", "S"};
		String[][] spots = {r1, r2, r3, r4, r5, r6, r7, r8};
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
					allSpots()[x][y].setValue(spots[x][y]);
			}
		}
		
	}

}
