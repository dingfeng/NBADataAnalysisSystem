package ui.mainui;

import javax.swing.JTextField;

public class EditableTextField extends JTextField {

	public EditableTextField(){
		setFeature();
	}
	
	public EditableTextField(String text){
		setText(text);
		setFeature();
	}
	
	private void setFeature(){
		this.setBorder(null);
		this.addFocusListener(new TextFieldAction());
	}
}
