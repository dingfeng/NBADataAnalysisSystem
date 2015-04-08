package ui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.teamui.TeamMatchPanel;
import ui.teamui.UneditableTextField;

public class HotPanel extends JPanel {

	JPanel tag = new JPanel();
	JPanel show=new JPanel();
	public HotPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
		this.setOpaque(false);
		TeamMatchPanel team=new TeamMatchPanel();
		setTag();
		setShow();
		this.add(tag);
		this.add(team);
		this.repaint();
		
	} 

	void setTag() {

		tag.setLayout(null);
		tag.setBounds(0, 0, FrameSize.width / 5,
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
		show.setLayout(null);
		show.setBackground(FrameSize.backColor);
		show.setBounds(FrameSize.width / 5,0, 4*FrameSize.width / 5,
				11 * FrameSize.height / 12);
		
		JComboBox<String> choose;
		choose = new MyComboBox(new String[] { "得分","篮板","助攻","抢断","盖帽" });
		choose.setBounds(10, 5, 80, 30);
		
		JLabel portrait_1=new JLabel("头像");
//		portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_1.setBackground(FrameSize.buttonbackColor);
		portrait_1.setOpaque(true);
		portrait_1.setBounds(FrameSize.width/12, 80,185 ,123);
		
		JLabel portrait_2=new JLabel("头像");
//		portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_2.setBackground(FrameSize.buttonbackColor);
		portrait_2.setOpaque(true);
		portrait_2.setBounds(FrameSize.width/3-100, 240,57 ,46);

		JLabel portrait_3=new JLabel("头像");
//		portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_3.setBackground(FrameSize.buttonbackColor);
		portrait_3.setOpaque(true);
		portrait_3.setBounds(FrameSize.width/3-100, 320,57 ,46);

		JLabel portrait_4=new JLabel("头像");
//		portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_4.setBackground(FrameSize.buttonbackColor);
		portrait_4.setOpaque(true);
		portrait_4.setBounds(FrameSize.width/3-100, 400,57 ,46);

		JLabel portrait_5=new JLabel("头像");
//		portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_5.setBackground(FrameSize.buttonbackColor);
		portrait_5.setOpaque(true);
		portrait_5.setBounds(FrameSize.width/3-100, 480,57 ,46);

		
		UneditableTextField num_1=new UneditableTextField();
		UneditableTextField num_2=new UneditableTextField();
		UneditableTextField num_3=new UneditableTextField();
		UneditableTextField num_4=new UneditableTextField();
		UneditableTextField num_5=new UneditableTextField();
		
		num_1.setBounds(FrameSize.width/3, 80, 200, 80);
		num_2.setBounds(FrameSize.width/3, 240, 200, 50);
		num_3.setBounds(FrameSize.width/3, 320, 200, 50);
		num_4.setBounds(FrameSize.width/3, 400, 200, 50);
		num_5.setBounds(FrameSize.width/3, 480, 200, 50);
		
		num_1.setBackground(FrameSize.buttonbackColor);
		num_2.setBackground(FrameSize.buttonbackColor);
		num_3.setBackground(FrameSize.buttonbackColor);
		num_4.setBackground(FrameSize.buttonbackColor);
		num_5.setBackground(FrameSize.buttonbackColor);
		
		show.add(choose);
		show.add(portrait_1);
		show.add(portrait_2);
		show.add(portrait_3);
		show.add(portrait_4);
		show.add(portrait_5);
		show.add(num_1);
		show.add(num_2);
		show.add(num_3);
		show.add(num_4);
		show.add(num_5);
	
	}

	private ImageIcon scaleImage(ImageIcon icon, int iconWidth, int iconHeight) { 
		int width = icon.getIconWidth(); 
		int height = icon.getIconHeight(); 

		if (width == iconWidth && height == iconHeight) { 
		return icon; 
		} 
		Image image = icon.getImage(); 
		image = image.getScaledInstance(iconWidth, iconHeight, Image.SCALE_DEFAULT); 

		return new ImageIcon(image); 
		}
}

