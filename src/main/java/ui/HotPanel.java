package ui;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.mainui.FrameSize;
import ui.teamui.UneditableTextField;

public class HotPanel extends JPanel {

	JPanel tag = new JPanel();
	JPanel show=new JPanel();
	public HotPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
		this.setOpaque(false);
		setTag();
		this.add(tag);
		this.repaint();
		
	} 

	void setTag() {

		tag.setLayout(null);
		tag.setBounds(0, FrameSize.height / 12, FrameSize.width / 5,
				11 * FrameSize.height / 12);
		tag.setBackground(FrameSize.backColor);
		JButton today_player = new JButton("当天热点球员");
		JButton season_player = new JButton("赛季热点球员");
		JButton season_team = new JButton("赛季热点球队");
		JButton fast_player = new JButton("进步最快球员");

		today_player.setBounds(10, FrameSize.height / 12 , 150, 50);
		season_player.setBounds(10, FrameSize.height / 12 + 100, 150, 50);
		season_team.setBounds(10, FrameSize.height / 12 + 200, 150, 50);
		fast_player.setBounds(10, FrameSize.height / 12 + 300, 150, 50);
		
		today_player.setBackground(FrameSize.buttonbackColor);
		season_player.setBackground(FrameSize.buttonbackColor);
		season_team.setBackground(FrameSize.buttonbackColor);
		fast_player.setBackground(FrameSize.buttonbackColor);

		today_player.setForeground(Color.white);
		season_player.setForeground(Color.white);
		season_team.setForeground(Color.white);
		fast_player.setForeground(Color.white);

		
		tag.add(today_player);
		tag.add(season_player);
		tag.add(season_team);
		tag.add(fast_player);
	}

	void setShow(){
		JLabel portrait=new JLabel();
		UneditableTextField num_1=new UneditableTextField();
		UneditableTextField num_2=new UneditableTextField();
		UneditableTextField num_3=new UneditableTextField();
		UneditableTextField num_4=new UneditableTextField();
		UneditableTextField num_5=new UneditableTextField();
		
		num_1.setBounds(FrameSize.width/3, FrameSize.height/6, 200, 100);
		num_2.setBounds(FrameSize.width/3, FrameSize.height/6, 200, 100);
		num_3.setBounds(FrameSize.width/3, FrameSize.height/6, 200, 100);
		num_4.setBounds(FrameSize.width/3, FrameSize.height/6, 200, 100);
		num_5.setBounds(FrameSize.width/3, FrameSize.height/6, 200, 100);
	}
}

