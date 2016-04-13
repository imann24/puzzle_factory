package imann.puzzlefactory.puzzles.components.constraints;

import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;

import java.util.ArrayList;

public class Shaded extends Constraint {
	private static String type = "Shaded";	
	private int numBlocks;
	private int [] blockLength;
	public Shaded(Puzzle p, String constraints) {
		super(p, type);
		parseString(constraints);
		
	}

	@Override
	public boolean satisfied() {
		ArrayList<Integer> blockLengths = new ArrayList<Integer>(); 
		for (int i = 0; i < getSpots().length; i++) {
			if (getSpots()[i].equals(" "))
				return true;
		}
		boolean inBlock = false;
		int blocksEncountered = 0;
		int index = -1;
		for (int i = 0; i < getSpots().length; i++) {
			if (inBlock && notShaded(getSpots()[i]))
				inBlock = false;
			else if (!inBlock && getSpots()[i].getValue().equals("X")) {
				inBlock = true;
				blocksEncountered++;
				blockLengths.add(1);
				index++;
			} else if (inBlock && getSpots()[i].getValue().equals("X")){
				blockLengths.set(index, blockLengths.get(index)+1);
			}
		}
		if (numBlocks == blocksEncountered && checkLengths(blockLengths)) {
			return true;
		} else return false;
	}
	
	public String toString () {
		String s = "";
		s += type + " ";
		for (int i = 0; i < blockLength.length; i++) {
			s+= blockLength[i] + " ";
		}
		s +=" is " + satisfied() + " for ";
		if (getSpots() != null) {
		for (Spot l : getSpots())
			if (l.getValue().equals(" "))
				s += "S ";
			else s += l.getValue() + " ";
		}
		return s;
	}
	
	private void parseString (String s) {
		if (s.length() == 1) {
			numBlocks = 1;
			blockLength = new int [1];
			blockLength[0] = Integer.parseInt(s);
		} else if (s.length() == 3) {
			numBlocks = 2;
			blockLength = new int [2];
			blockLength[0] = Integer.parseInt(s.charAt(0) + "");
			blockLength[1] = Integer.parseInt(s.charAt(2) + "");
		} else if (s.length() == 5) {
			numBlocks = 3;
			blockLength = new int [3];
			blockLength[0] = Integer.parseInt(s.charAt(0) + "");
			blockLength[1] = Integer.parseInt(s.charAt(2) + "");
			blockLength[2] = Integer.parseInt(s.charAt(4) + "");
		}
			
	}

	private boolean notShaded (Spot s) {
		if (s.getValue().equals(" ") || s.getValue().equals("+"))
			return true;
		else return false;
	}
	
	private boolean checkLengths (ArrayList<Integer> blocks) {
		if (blocks.size() != blockLength.length)
			return false;
		else {
			for (int i = 0; i < blockLength.length; i++) {
				if (blocks.get(i) != blockLength[i])
					return false;
			}
		}
		return true;
	}
}
