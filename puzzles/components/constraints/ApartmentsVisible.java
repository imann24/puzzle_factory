package imann.puzzlefactory.puzzles.components.constraints;

import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;

public class ApartmentsVisible extends Constraint {
	private int visible;
	public ApartmentsVisible(Puzzle p, String s) {
		super(p, "ApartmentsVisible");
		visible = Integer.parseInt(s);
	}

	@Override
	public boolean satisfied() {
		int [] values = new int[getSpots().length];
		int seen = 0;
		int tallest = 0;
		for (int i = 0; i < getSpots().length; i++) {
			try {
				values[i] = Integer.parseInt(getSpots()[i].getValue());
			} catch (NumberFormatException n) {
				return true;
			}
		}
		for (int i = 0; i < getSpots().length; i++) {
			if (tallest < values[i]) {
				tallest = values[i];
				seen++;
			}
		}
		if (seen == visible)
			return true;
		else return false;
	}
	
	public String toString () {
		String s = "";
		s += "ApartmentsVisible " + visible + " is " + satisfied() + " for ";
		for (Spot l : getSpots())
			s += l.getValue() + " ";
		return s;
	}

}
