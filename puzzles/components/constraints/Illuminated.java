package imann.puzzlefactory.puzzles.components.constraints;

import imann.puzzlefactory.puzzles.components.Puzzle;
import imann.puzzlefactory.puzzles.components.Spot;

public class Illuminated extends Constraint {
	private static String type = "Illuminated";
	private Puzzle p;
	public Illuminated(Puzzle p) {
		super(p, type);
		this.p = p;
	}

	@Override
	
	public boolean satisfied() {
		//find spot that matches in row and column
		int yIndex = -1;
		int xIndex = -1;
		boolean rowIncomplete = false;
		boolean columnIncomplete = false;
		Spot [] row = new Spot[p.getRows()];
		Spot [] column = new Spot[p.getColumns()];
		System.arraycopy(getSpots(), 0, column, 0, p.getColumns());
		System.arraycopy(getSpots(), p.getColumns(), row, 0, p.getRows());
		for (int x = 0; x < p.getColumns(); x++) {
			for (int y = 0; y < p.getRows(); y++) {
				if (row[x].getValue().equals(" "))
					rowIncomplete = true;
				if (column[y].equals(" "))
					columnIncomplete = true;
				if (row[x] == column[y]) {
					xIndex = x;
					yIndex = y;
				}
					
			}
		}
		String s = row[xIndex].getValue();
		//if there is no matching spot between the row and column
		if (xIndex == -1 || isLight(s) || isWall(s)) {
			return true;
		}
		
		boolean left = false, right = false, up = false, down = false;
		for (int x = xIndex; x < p.getColumns(); x++) {
			if (row[x].getValue() == "Lit") {
				if (right)
					return false; //there are two lights in the same row
				else right = true;
			} else if (isWall(row[x].getValue())) {
				break;
			}
		}
		for (int x = xIndex; x >= 0; x--) {
			if (row[x].getValue() == "Lit") {
				if (left)
					return false; //there are two lights in the same row
				else left = true;
			} else if (isWall(row[x].getValue())) {
				break;
			}
		}
		//if the spot is receiving light from both sides
		if (left&&right) {
			return false;
		}
		
		for (int y = yIndex; y < p.getRows(); y++) {
			if (column[y].getValue() == "Lit") {
				if (down)
					return false; //there are two lights in the same row
				else down = true;
			} else if (isWall(column[y].getValue())) {
				break;
			}
		}
		for (int y = yIndex; y >= 0; y--) {
			if (column[y].getValue() == "Lit") {
				if (up)
					return false; //there are two lights in the same row
				else up = true;
			} else if (isWall(column[y].getValue())) {
				break;
			}
		}
		
		if (down&&up) {
			return false;
		} else if (down || up || left || right || rowIncomplete || columnIncomplete)
			return true;
		//spot array will be organized: horizontal row of 8 then vertical column of 8
		//checking one spot at a time
		//check all the way to your left and right (stopping if you hit a wall), did you see a light? set horizontal 
		//check all the way to your top and bottom (stopping if you hit a wall), did you see a light? set vertical
		//return vertical || horizontal
		return false;
	}
	
	private boolean isWall (String s) {
		if (s.equals("X") || s.equals("0") || s.equals("1") || s.equals("2") || s.equals("3"))
			return true;
		else return false;
	}
	
	private boolean isLight (String s) {
		if (s.equals("Lit"))
			return true;
		else return false;
	}

}
