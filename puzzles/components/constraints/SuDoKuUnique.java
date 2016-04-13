package imann.puzzlefactory.puzzles.components.constraints;

import imann.puzzlefactory.puzzles.components.Puzzle;

public class SuDoKuUnique extends Constraint {

	public SuDoKuUnique(Puzzle p) {
		super(p, "SuDoKuUnique");
	}

	@Override
	public boolean satisfied() {
//		for (int i = 0; i < getSpots().length; i++) {
//			if (getSpots()[i].equals(" "))
//				return true;
//		}
		for (int i = 0; i < getSpots().length; i++) {
			for (int j = 0; j < getSpots().length; j++) {
				if (i != j && getSpots()[i].getValue().charAt(0) == getSpots()[j].getValue().charAt(0) && !getSpots()[j].equals(" "))
					return false;
			}
		}
		return true;
	}

}
