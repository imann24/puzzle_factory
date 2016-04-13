package imann.puzzlefactory.puzzles.components.constraints;

import imann.puzzlefactory.puzzles.components.Puzzle;

public class AllValues extends Constraint {
	private String[] allValues;
	private boolean[] valueSeen;
	public AllValues(Puzzle p) {
		super(p, "AllValues");
		allValues = new String [p.getPossibleValues().length-1];
		System.arraycopy(p.getPossibleValues(), 1, allValues, 0, p.getPossibleValues().length -1);
		valueSeen = new boolean [allValues.length];
	}

	@Override
	public boolean satisfied() {
		for (int i = 0; i < allValues.length; i++) {
			for (int j = 0; j < getSpots().length; j++) {
				if (getSpots()[j].equals(" "))
					return true;
				if (allValues[i].equals(getSpots()[j].getValue()))
					valueSeen[i] = true;
			}
		}
		for (int i = 0; i < valueSeen.length; i++)
			if (!valueSeen[i])
				return false;
		return true;
	}

}
