package ui.mainui;

import java.awt.Color;

import javax.swing.JComboBox;

public class MyComboBox extends JComboBox{
	
	public MyComboBox(){
		this.setBackground(new Color(68,68,68));
		this.setForeground(Color.white);
	}
	
	public MyComboBox(String [] a){
		for(int i=0;i<a.length;i++){
			this.addItem(a[i]);
			this.setBackground(new Color(68,68,68));
			this.setForeground(Color.white);
		}
	}
}
