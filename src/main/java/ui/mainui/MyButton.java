package ui.mainui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton{
	

	public MyButton(ImageIcon image){
		this.setBorder(null);
		this.setContentAreaFilled(false);
		this.setIcon(image);
		this.addMouseListener(new button());
	}

	class button implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
//			((JButton)e.getSource()).setBackground(Color.gray);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			((JButton)e.getSource()).setBackground(Color.DARK_GRAY);
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			((JButton)e.getSource()).setBackground(Color.black);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
