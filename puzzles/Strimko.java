package imann.puzzlefactory.puzzles;
import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;
import imann.puzzlefactory.puzzles.components.constraints.Constraint;
import imann.puzzlefactory.puzzles.components.constraints.Unique;

import java.awt.Color;


public class Strimko extends Puzzle {
	private static String [] PosVals3 = {" ", "1", "2", "3"};
	private static String [] PosVals = PosVals3;
	private static Constraint[] Constraints= new Constraint [(PosVals.length-1)*3];
	public Strimko() {
		super(3, 3, PosVals);
		puzzleName = "Strimko";
		reorderPuzzleList(puzzleName);
		setColors();
		hasColors(true);
		initConstraints();
	}

	@Override
	public boolean constraintsSatisfied() {
		for (Constraint c : Constraints) 
			if (!c.satisfied())
				return false;
		return true;
	}
	
	private void setColors () {
		Color [] col1 = {Color.RED, Color.RED, Color.BLUE};
		Color [] col2 = {Color.RED, Color.BLUE, Color.GREEN};
		Color [] col3 = {Color.BLUE, Color.GREEN, Color.GREEN};
		Color[][] all = {col1, col2, col3};
		super.setColors(all);
	}
	
	private void initConstraints () {
		int count = 0;
		//for each column
		for (int i = 0; i < PosVals.length-1; i++) {
			Constraints[i] = new Unique(this);
			Constraints[i].setSpots(allSpots()[i]);
			count++;
		}
		//for each row
		for (int i = 0; i < PosVals.length-1; i++) {
			Spot[]s = new Spot [PosVals.length-1];
			for (int y = 0; y < getRows(); y++) {
				s[y] = allSpots()[i][y]; 
			}
			Constraints[i+count] = new Unique(this);
			Constraints[i+count].setSpots(s);
		}
		//for the groups
		int index = count * 2;
		Spot[] s1 = {allSpots()[0][0], allSpots()[0][1], allSpots()[1][0]};
		Constraints[index] = new Unique(this);
		Constraints[index++].setSpots(s1);
		Spot[] s2= {allSpots()[2][0], allSpots()[1][1], allSpots()[0][2]};
		Constraints[index] = new Unique(this);
		Constraints[index++].setSpots(s2);
		Spot[] s3 = {allSpots()[2][2], allSpots()[1][2], allSpots()[2][1]};
		Constraints[index] = new Unique(this);
		Constraints[index++].setSpots(s3);
	}
}
