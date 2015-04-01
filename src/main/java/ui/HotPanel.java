package ui;

import javax.swing.JPanel;

import ui.mainui.FrameSize;

public class HotPanel extends JPanel{

	public HotPanel(){
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
		this.setOpaque(false);
		this.repaint();
	}
}
