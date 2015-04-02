package ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;

import ui.mainui.FrameSize;

public class HotPanel extends JPanel {

	JPanel tag = new JPanel();

	public HotPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
//		this.setOpaque(false);
		this.setBackground(Color.white);
		setTag();
		this.add(tag);
		this.repaint();
		System.out.print("hotpanel");
	} 

	void setTag() {
		System.out.print("hotpanel");
		tag.setLayout(null);
		tag.setBounds(0, FrameSize.height / 12, FrameSize.width / 8,
				11 * FrameSize.height / 12);
		tag.setBackground(FrameSize.backColor);
		JButton today_player = new JButton("当天热点球员");
		JButton season_player = new JButton("赛季热点球员");
		JButton season_team = new JButton("赛季热点球队");
		JButton fast_player = new JButton("进步最快球员");

		today_player.setBounds(10, FrameSize.height / 12 + 50, 150, 50);
		season_player.setBounds(10, FrameSize.height / 12 + 150, 150, 50);
		season_team.setBounds(10, FrameSize.height / 12 + 250, 150, 50);
		fast_player.setBounds(10, FrameSize.height / 12 + 350, 150, 50);
		
		today_player.setBackground(FrameSize.buttonbackColor);
		season_player.setBackground(FrameSize.buttonbackColor);
		season_team.setBackground(FrameSize.buttonbackColor);
		fast_player.setBackground(FrameSize.buttonbackColor);

		
		tag.add(today_player);
		tag.add(season_team);
		tag.add(season_team);
		tag.add(fast_player);
	}
}
