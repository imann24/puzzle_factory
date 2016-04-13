package imann.puzzlefactory.puzzles.components.constraints;

import imann.puzzlefactory.puzzles.components.Puzzle;

public class NumberOfLights extends Constraint {
	private static String type = "NumberOfLights";
	public NumberOfLights(Puzzle p) {
		super(p, type);
	}

	@Override
	public boolean satisfied() {
		//gets spots are five spots: first is the top, second is the left, third is the number
		//forth is the right and fifth is the bottom
		int lightsExpected = -1;
		int lightCount = 0;
		for (int i = 0; i < getSpots().length; i++) {
			try {
				lightsExpected = Integer.parseInt(getSpots()[i].getValue());
			} catch (NumberFormatException e) {
				continue;
			}
		}
		if (lightsExpected == -1)
			return true;
		for (int i = 0; i < getSpots().length; i++) {
			if (getSpots()[i].getValue().equals(" "))
				return true;
			else if (getSpots()[i].getValue().equals("Lit"))
				lightCount++;
		}
		//spots length/2 (the middle) will always be the one we're checking 
		// TODO Auto-generated method stub
		if (lightsExpected == lightCount)
			return true;
		else return false;
	}

}
