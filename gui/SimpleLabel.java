package imann.puzzlefactory.gui;

import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimpleLabel extends JPanel {

	public SimpleLabel(String s) {
		this.add(new JLabel(s));
	}

	public SimpleLabel(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public SimpleLabel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public SimpleLabel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
