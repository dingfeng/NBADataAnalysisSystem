package ui.mainui;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.apache.batik.svggen.font.Font;

public class UneditableTextField extends JTextField {

	public UneditableTextField() {
		setFeature();
	}

	public UneditableTextField(String text) {
		setText(text);
		setFeature();
	}
	
	public UneditableTextField(int text){
		setText(String.valueOf(text));
		setFeature();
	}

	private void setFeature() {
		this.setBorder(null);
		this.setEditable(false);
		this.addFocusListener(new TextFieldAction());
		this.setBackground(new Color(68, 68, 68));
		this.setForeground(Color.white);
		this.setHorizontalAlignment(SwingConstants.CENTER);//居中
	}

	public void setFont(Font font) {
		// TODO Auto-generated method stub
		this.setFont(font);
	}
}
