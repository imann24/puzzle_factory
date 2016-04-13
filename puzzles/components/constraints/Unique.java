package imann.puzzlefactory.puzzles.components.constraints;
import imann.puzzlefactory.puzzles.components.Puzzle;

import java.util.ArrayList;
import java.util.Arrays;

public class Unique extends Constraint {

	public Unique(Puzzle p) {
		super(p, "Unique");
	}

	@Override
	public boolean satisfied() {
		for (int i = 0; i < getSpots().length; i++) {
			if (getSpots()[i].getValue().equals(" "))
				return true;
			for (int j = 0; j < getSpots().length; j++) {
				if (i != j && getSpots()[i].getValue().charAt(0) == getSpots()[j].getValue().charAt(0))
					return false;
			}
		}
		return true;
	}

}
