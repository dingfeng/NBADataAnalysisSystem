package ui.mainui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

public class TextFieldAction implements ActionListener, FocusListener{

	public void actionPerformed(ActionEvent arg0) {
	}

	public void focusGained(FocusEvent e) {
		
			((JTextField) e.getSource()).selectAll();
		
	}

	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
