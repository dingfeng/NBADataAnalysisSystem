package ui.mainui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MyButton extends JButton {

	Color backcolor = null;
	Color focuscolor = null;

	public MyButton(ImageIcon image, Color back, Color focus) {
		this.backcolor=back;
		this.focuscolor=focus;
		this.setBorder(null);
//		this.setContentAreaFilled(false);
		this.setIcon(image);
		this.setBackground(back);
		this.addMouseListener(new button());
	}
	
	public MyButton(String text, Color back, Color focus) {
		this.backcolor=back;
		this.focuscolor=focus;
		this.setBorder(null);
//		this.setContentAreaFilled(false);
		this.setText(text);
		this.setBackground(back);
		this.addMouseListener(new button());
	}

	class button implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			 ((JButton)e.getSource()).setBackground(backcolor);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			((JButton) e.getSource()).setBackground(focuscolor);

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			((JButton) e.getSource()).setBackground(backcolor);
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
