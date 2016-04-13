package imann.puzzlefactory.puzzles.components.constraints;

import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;

public class NotAdjacent extends Constraint {
	private static String type = "NotAdjacent";
	public NotAdjacent(Puzzle p) {
		super(p, type);
	}

	@Override
	public boolean satisfied() {
		int count = 0;
		for (int i = 0; i < getSpots().length; i++) {
			if (getSpots()[i].getValue().length() != 2) 
				return true;
		}
		if (getSpots()[0].getValue().charAt(1) != 'X') //if the center spot is not crossed out
			return true;
		for (Spot s: getSpots()) {
			if (s.getValue().charAt(1)=='X')
				count++;
		}
		if (count > 1)
			return false;
		else return true;
	}

}
