package imann.puzzlefactory.puzzles.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
	private int rows;
	private int columns;
	private Scanner s;
	private String [][] values;
	private String[] posValues;
	public ReadFile(String fileName) throws FileNotFoundException{
		Scanner s = new Scanner(new File(fileName));
		columns = Integer.parseInt(s.nextLine());
		rows = Integer.parseInt(s.nextLine());
		posValues = s.nextLine().split(",");
		values = new String[columns][rows];
		int y = 0;
		while (s.hasNextLine() && y < rows) {
			String temp = s.nextLine();
			String [] vals;
			vals = temp.split(",");
			values[y] = vals;
			y++;
		}
		
	}
	
	public int getRows () {
		return rows;
	}
	
	public int getColumns () {
		return columns;
	}
	
	public String[][] getValues () {
		return values;
	}
	
	public String [] getPosValues () {
		return posValues;
	}

}
