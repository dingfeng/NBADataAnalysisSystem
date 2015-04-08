package ui.teamui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.mainui.FrameSize;

public class TeamMatchPanel extends JPanel{

	public TeamMatchPanel() {
		this.setLayout(null);
		this.setBounds(FrameSize.width / 3, FrameSize.height / 12,
				2 * FrameSize.width / 3, FrameSize.height*15/16-FrameSize.height / 12);
		this.setBackground(FrameSize.backColor);;
		setText();
		this.repaint();
		
	} 
	
	void setText(){
		JLabel recent =new JLabel("近期比赛");
		JLabel past=new JLabel("过往查询");
		
		recent.setBounds(10, 100, 2 * FrameSize.width / 3-20, 50);
		past.setBounds(10, 300, 2 * FrameSize.width / 3-20, 50);
		
		recent.setOpaque(true);
		past.setOpaque(true);
		recent.setBackground(Color.black);
		past.setBackground(Color.black);
		recent.setForeground(Color.white);
		past.setForeground(Color.white);
		
		this.add(past);
		this.add(recent);
	}
	
	void setRecentTable(){
		Vector<String> columnsName = new Vector<String>();
		columnsName.add("时间");
		columnsName.add("对阵队伍");
		columnsName.add("比分");
	}
}
