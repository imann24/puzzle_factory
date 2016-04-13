package imann.puzzlefactory.puzzles.components.constraints;

import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;

public class Sum extends Constraint {
	private static String type = "Sum";
	private int sum;
	public Sum(Puzzle p, String s) {
		super(p, type);
		sum = Integer.parseInt(s);
	}

	@Override
	public boolean satisfied() {
		int temp = 0;
		for (int i = 0; i < getSpots().length; i++) {
			if (getSpots()[i].equals(" "))
				return true;
			else if (getSpots()[i].equals("X"))
				temp+= (i + 1);
		}
		if (temp == sum) {
			return true;
		} else return false;
	}
	
	public String toString () {
		String s = "";
		s += type + " " + sum + " is " + satisfied() + " for ";
		if (getSpots() != null) {
		for (Spot l : getSpots())
			if (l.getValue().equals(" "))
				s += "S ";
			else s += l.getValue() + " ";
		}
		return s;
	}

}
