package ui.mainui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class MyComboBox extends JComboBox{
	
	public MyComboBox(){
		this.setBackground(new Color(68,68,68));
		this.setForeground(Color.white);
		this.setBorder(null);
	}
	
	public MyComboBox(String [] a){
		for(int i=0;i<a.length;i++){
			this.addItem(a[i]);
		}
		this.setBackground(new Color(68,68,68));
		this.setForeground(Color.white);
	}
	
	public MyComboBox(String text,ArrayList<String> a){
		this.addItem(text);
		for(int i=0;i<a.size();i++){
			this.addItem(a.get(i));
		}
		this.setBackground(new Color(68,68,68));
		this.setForeground(Color.white);
	}
	
	public MyComboBox(String text,String[] a){
		this.addItem(text);
		if (a != null)
		for(int i=0;i<a.length;i++){
			this.addItem(a[i]);
		}
		this.setBackground(new Color(68,68,68));
		this.setForeground(Color.white);
	}
}
