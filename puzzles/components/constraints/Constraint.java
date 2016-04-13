package imann.puzzlefactory.puzzles.components.constraints;

import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;

public abstract class Constraint {
	private Puzzle p;
	private Spot[] spots;
	private String type;
	public Constraint(Puzzle p, String s) {
		this.p = p;
		type = s;
	}
	
	public abstract boolean satisfied();
	
	public void setSpots (Spot[] s) {
		spots = s;
	}
	
	public Spot[] getSpots () {
		return spots;
	}
	
	public String toString () {
		String s = "";
		s += type + " is " + satisfied() + " for ";
		if (spots != null) {
		for (Spot l : spots)
			if (l.getValue().equals(" "))
				s += "S ";
			else s += l.getValue() + " ";
		}
		return s;
	}
}
