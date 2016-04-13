package imann.puzzlefactory.puzzles.components.constraints;
import imann.puzzlefactory.puzzles.components.Puzzle;


import java.util.ArrayList;

public class HitoriUnique extends Constraint {
	private static String type = "HitoriUnique";
	private Puzzle p;
	private int min;
	public HitoriUnique(Puzzle p, int min) {
		super(p, type);
		this.p = p;
	}

	@Override
	public boolean satisfied() {
		for (int i = 0; i < getSpots().length; i++) {
			if (getSpots()[i].getValue().length() != 2 || getSpots()[i].getValue().charAt(1) == ' ')
				return true;
		}
		ArrayList<Character> spots = new ArrayList<Character>();
		for (int i = 0; i < getSpots().length; i++) {
			if (getSpots()[i].getValue().charAt(1) == ' ' || getSpots()[i].getValue().charAt(1) == '+') {
				spots.add(getSpots()[i].getValue().charAt(0));
			}
		}
		for (int i = 0; i < spots.size(); i++) {
			for (int j = 0; j < spots.size(); j++ ) {
				if (i != j && spots.get(i) == spots.get(j))
					return false;
			}
		}
 		return true;
	}
}
