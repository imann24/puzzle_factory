package imann.puzzlefactory.puzzles.components.constraints;

import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;

public class MatchingValue extends Constraint {
	private static String type = "MatchingValue";
	private Spot s;
	private String str;
	
	public MatchingValue(Puzzle p, Spot s, String str) {
		super(p, type);
		this.s = s;
		this.str = str;
	}

	@Override
	public boolean satisfied() {
		if (s.getValue().charAt(0) == str.charAt(0))
			return true;
		else return false;
	}
	
	public String toString () {
		Spot [] temp = {s, new Spot(str)};
		this.setSpots(temp);
		return super.toString();
	}

}
