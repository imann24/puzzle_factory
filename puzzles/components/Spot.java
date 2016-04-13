package imann.puzzlefactory.puzzles.components;
public class Spot {
	private String value;

	public Spot (String v) {
		value = v;
	}

	public Spot () {
		value = "-1";
	}

	public String getValue () {
		return value;
	}

	public void setValue (String v) {
		value = v;
	}

	public void setValue (int v) {
		value = Integer.toString(v);
	}
	
	public boolean equals (Spot s) {
		if (this.value.equals(s.getValue()))
			return true;
		else return false;
	}
	
	public boolean equals (String s) {
		if (this.value.equals(s))
			return true;
		else return false;
	}

}