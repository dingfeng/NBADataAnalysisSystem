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
import ui.mainui.UneditableTextField;
import ui.teamui.TeamMatchPanel;

public class HotPanel extends JPanel {

	JPanel tag = new JPanel();
	JPanel show = new JPanel();

	public HotPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height*7/8);
		this.setOpaque(false);
		TeamMatchPanel team = new TeamMatchPanel();
		setTag();
		setShow();
		this.add(tag);
		this.add(team);
		this.repaint();

	}

	void setTag() {

		tag.setLayout(null);
		tag.setBounds(0, 0, FrameSize.width / 6, 11 * FrameSize.height / 12);
		int height = 11 * FrameSize.height / 12;
		tag.setBackground(FrameSize.backColor);
		JButton today_player = new JButton("当天热点球员");
		JButton season_player = new JButton("赛季热点球员");
		JButton season_team = new JButton("赛季热点球队");
		JButton fast_player = new JButton("进步最快球员");

		today_player.setBounds(10, 40, 150, 50);
		season_player.setBounds(10, 40 + height / 4, 150, 50);
		season_team.setBounds(10, 40 + height / 2, 150, 50);
		fast_player.setBounds(10, 40 + 3 * height / 4, 150, 50);

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

	void setShow() {
		show.setLayout(null);
		show.setBackground(FrameSize.backColor);
		show.setBounds(FrameSize.width / 6, 0, 5 * FrameSize.width / 6,
				FrameSize.height*7/8- FrameSize.height / 12);

		JComboBox<String> choose;
		choose = new MyComboBox(new String[] { "得分", "篮板", "助攻", "抢断", "盖帽" });
		choose.setBounds(10, 50, 80, 30);

		JLabel portrait_1 = new JLabel("头像");
		// portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_1.setBackground(FrameSize.buttonbackColor);
		portrait_1.setOpaque(true);
		portrait_1.setBounds(FrameSize.width / 3 - 228, 80, 185, 123);

		JLabel portrait_2 = new JLabel("头像");
		// portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_2.setBackground(FrameSize.buttonbackColor);
		portrait_2.setOpaque(true);
		portrait_2.setBounds(FrameSize.width / 3 - 100, 240, 57, 46);

		JLabel portrait_3 = new JLabel("头像");
		// portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_3.setBackground(FrameSize.buttonbackColor);
		portrait_3.setOpaque(true);
		portrait_3.setBounds(FrameSize.width / 3 - 100, 320, 57, 46);

		JLabel portrait_4 = new JLabel("头像");
		// portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_4.setBackground(FrameSize.buttonbackColor);
		portrait_4.setOpaque(true);
		portrait_4.setBounds(FrameSize.width / 3 - 100, 400, 57, 46);

		JLabel portrait_5 = new JLabel("头像");
		// portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_5.setBackground(FrameSize.buttonbackColor);
		portrait_5.setOpaque(true);
		portrait_5.setBounds(FrameSize.width / 3 - 100, 480, 57, 46);

		UneditableTextField name_1 = new UneditableTextField();
		UneditableTextField name_2 = new UneditableTextField();
		UneditableTextField name_3 = new UneditableTextField();
		UneditableTextField name_4 = new UneditableTextField();
		UneditableTextField name_5 = new UneditableTextField();

		name_1.setBounds(FrameSize.width / 3, 80, 200, 80);
		name_2.setBounds(FrameSize.width / 3, 240, 200, 50);
		name_3.setBounds(FrameSize.width / 3, 320, 200, 50);
		name_4.setBounds(FrameSize.width / 3, 400, 200, 50);
		name_5.setBounds(FrameSize.width / 3, 480, 200, 50);

		name_1.setBackground(FrameSize.buttonbackColor);
		name_2.setBackground(FrameSize.buttonbackColor);
		name_3.setBackground(FrameSize.buttonbackColor);
		name_4.setBackground(FrameSize.buttonbackColor);
		name_5.setBackground(FrameSize.buttonbackColor);

		UneditableTextField score_1 = new UneditableTextField();
		UneditableTextField score_2 = new UneditableTextField();
		UneditableTextField score_3 = new UneditableTextField();
		UneditableTextField score_4 = new UneditableTextField();
		UneditableTextField score_5 = new UneditableTextField();

		score_1.setBounds(FrameSize.width / 3 + 250, 80, 100, 100);
		score_2.setBounds(FrameSize.width / 3 + 250,
				FrameSize.height / 7 + 100, 80, 80);
		score_3.setBounds(FrameSize.width / 3 + 250,
				2 * FrameSize.height / 7 + 100, 80, 80);
		score_4.setBounds(FrameSize.width / 3 + 250,
				3 * FrameSize.height / 7 + 100, 80, 80);
		score_5.setBounds(FrameSize.width / 3 + 250,
				4 * FrameSize.height / 7 + 100, 80, 80);

		score_1.setBackground(FrameSize.buttonbackColor);
		score_2.setBackground(FrameSize.buttonbackColor);
		score_3.setBackground(FrameSize.buttonbackColor);
		score_4.setBackground(FrameSize.buttonbackColor);
		score_5.setBackground(FrameSize.buttonbackColor);

		show.add(choose);
		show.add(portrait_1);
		show.add(portrait_2);
		show.add(portrait_3);
		show.add(portrait_4);
		show.add(portrait_5);
		show.add(name_1);
		show.add(name_2);
		show.add(name_3);
		show.add(name_4);
		show.add(name_5);
		show.add(score_1);
		show.add(score_2);
		show.add(score_3);
		show.add(score_4);
		show.add(score_5);

	}

	private ImageIcon scaleImage(ImageIcon icon, int iconWidth, int iconHeight) {
		int width = icon.getIconWidth();
		int height = icon.getIconHeight();

		if (width == iconWidth && height == iconHeight) {
			return icon;
		}
		Image image = icon.getImage();
		image = image.getScaledInstance(iconWidth, iconHeight,
				Image.SCALE_DEFAULT);

		return new ImageIcon(image);
	}
}
