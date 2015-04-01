package ui.teamui;

import java.awt.Color;

import javax.swing.JTextField;

import ui.mainui.TextFieldAction;

public class UneditableTextField extends JTextField {

	public UneditableTextField() {
		setFeature();
	}

	public UneditableTextField(String text) {
		setText(text);
		setFeature();
	}

	private void setFeature() {
		this.setBorder(null);
		this.setEditable(false);
		this.addFocusListener(new TextFieldAction());
		this.setBackground(new Color(68, 68, 68));
		this.setForeground(Color.white);
	}
}
