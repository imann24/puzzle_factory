package imann.puzzlefactory.puzzles;
import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.ReadFile;
import imann.puzzlefactory.puzzles.components.Spot;
import imann.puzzlefactory.puzzles.components.constraints.AllValues;
import imann.puzzlefactory.puzzles.components.constraints.ApartmentsVisible;
import imann.puzzlefactory.puzzles.components.constraints.Constraint;
import imann.puzzlefactory.puzzles.components.constraints.Unique;

public class Skyscraper extends Puzzle {
	private String[] top = {"3", "2", "3", "1", "2"};
	private String[] bottom = {"2", "1", "3", "4", "3"};
	private String[] left = {"3", "4", "2", "1", "2"};
	private String[] right = {"2", "1", "2", "4", "3"};
	private static String[] PosVals = {" ", "1", "2", "3", "4", "5"};
	String [] s1 = {"2", "1", "3", "5", "4"};
	String [] s2 = {"4", "3", "1", "2", "5"};
	String [] s3 = {"1", "2", "5", "4", "3"};
	String [] s4 = {"5", "4", "2", "3", "1"};
	String [] s5 = {"3", "5", "4", "1", " "};
	private String [][] solution = {s1, s2, s3, s4, s5};
	private Constraint [] unique;
	private Constraint [] allValues;
	private Constraint [] seen;
	private Constraint [][] allConstraints = {unique, allValues, seen};
	private boolean debugging = false;
	public Skyscraper() {
		super(5, 5, PosVals);
		toggleBorder(true);
		puzzleName = "Sky Scrapers";
		reorderPuzzleList(puzzleName);
		toggleBorder(true);
		setBorders(top, bottom, left, right);
		initConstraints();
	}
	
	public boolean constraintsSatisfied () {
		boolean empty = true;
		for (int x = 0; x < getColumns(); x++) {
			for (int y = 0; y < getRows(); y++) {
				if (!allSpots()[x][y].getValue().equals(" ")) {
					empty = false;
				}
			}
		}
		if (empty)
			return false;
		for (int i = 0; i < allConstraints.length; i++) {
			for (Constraint c : allConstraints[i]) {
				if (!c.satisfied())
					return false;
			}
		}
		return true;
	}
	
	private void initConstraints () {
		int size = getColumns() + getRows();
		unique = new Constraint [size];
		allValues = new Constraint [size];
		seen = new Constraint [2*size];
		int i = 0;
		//adds the constraints for the rows
		for (int x = 0; x < getColumns(); x++) {
			Spot[] s = allSpots()[x];
			Spot[] sReverse = new Spot[s.length];
			//System.arraycopy(s, 0, sReverse, 0, s.length);
			for (int j = 0; j < s.length; j++) {
				//sReverse[j] = sReverse[s.length - j - 1];
				sReverse[s.length - j - 1] = s[j];
			}
			unique[x] = new Unique(this);
			allValues[x] = new AllValues(this);
			seen[x] = new ApartmentsVisible(this, left[x]);
			seen[x+getColumns()] = new ApartmentsVisible(this, right[x]);
			unique[x].setSpots(s);
			allValues[x].setSpots(s);
			seen[x].setSpots(s);
			seen[x+getColumns()].setSpots(sReverse);
			i++;
		}
		//adds the constraints for the columns
		//i is used as an offset 
		//so that we can store the vertical and the horizontal constraints in one 2d array
		for (int y = 0; y < getRows(); y++) {
			Spot[] s = new Spot[getRows()];
			int n = 0;
			for (int x = 0; x < getColumns(); x++)
				s[n++] = allSpots()[x][y];
			Spot[] sReverse = new Spot[s.length];
			for (int j = 0; j < s.length; j++) {
				sReverse[s.length - j - 1] = s[j];

			}
			unique[i+y] = new Unique(this);
			allValues[i+y] = new AllValues(this);
			seen[2*i+y] = new ApartmentsVisible(this, top[y]);
			int b = i + y + getRows();
			seen[2*i+y+getRows()] = new ApartmentsVisible(this, bottom[y]);
			unique[i+y].setSpots(s);
			allValues[i+y].setSpots(s);
			seen[2*i+y].setSpots(s);
			seen[2*i+y+getRows()].setSpots(sReverse);
		}
		allConstraints[0] = unique;
		allConstraints[1] = seen;
		allConstraints[2] = allValues;
		setConstraints(allConstraints);
	}
	//debugging only
	public void solve () {
		if (debugging) {
			for (int x = 0; x < getColumns(); x++) 
				for (int y = 0; y < getRows(); y++)
					allSpots()[x][y].setValue(solution[x][y]);
			debugging = false;
		} else super.solve();
	}
}
