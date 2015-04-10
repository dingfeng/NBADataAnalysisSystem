package ui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;

import org.apache.batik.swing.JSVGCanvas;

import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import vo.PlayerSortBy;
import vo.TeamMatchVO;
import vo.TeamSortBy;
import bl.teambl.TeamController;

public class HotPanel extends JPanel {

	JPanel tag = new JPanel();
	JPanel show = new JPanel();

	JLabel name_1=new JLabel();
	JLabel name_2=new JLabel();
	JLabel name_3=new JLabel();
	JLabel name_4=new JLabel();
	JLabel name_5=new JLabel();

	JLabel portrait_1=new JLabel();
	JLabel portrait_2=new JLabel();
	JLabel portrait_3=new JLabel();
	JLabel portrait_4=new JLabel();
	JLabel portrait_5=new JLabel();

	JLabel score_1 = new JLabel();
	JLabel score_2 = new JLabel();
	JLabel score_3 = new JLabel();
	JLabel score_4 = new JLabel();
	JLabel score_5 = new JLabel();

	JComboBox<String> choose;
	TeamController tc = new TeamController();
	int hottype = 0;

	public HotPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setOpaque(false);

		setTag();
		setShow();
		this.add(tag);
		this.add(show);
		this.repaint();

	}

	/** 设置左侧选择框 */
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

		today_player.addActionListener(e -> showchoose(1));
		season_player.addActionListener(e -> showchoose(2));
		season_team.addActionListener(e -> showchoose(3));
		fast_player.addActionListener(e -> showchoose(4));

		tag.add(today_player);
		tag.add(season_player);
		tag.add(season_team);
		tag.add(fast_player);
	}

	/** 设置右侧显示panel */
	void setShow() {
		show.setLayout(null);
		show.setBackground(FrameSize.backColor);
		show.setBounds(FrameSize.width / 6, 0, 5 * FrameSize.width / 6,
				11 * FrameSize.height / 12);

		choose = new MyComboBox(new String[] { "得分", "篮板", "助攻", "得分/篮板/助攻",
				"盖帽", "抢断", "犯规", "失误", "分钟", "效率", "投篮", "三分", "罚球", "两双" });
		choose.setBounds(10, 50, 80, 30);


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

	/** 设置下拉框 */
	void showchoose(int type) {
		hottype = type;
		choose.setVisible(false);
		switch (type) {
		case 3:
			choose = new MyComboBox(new String[] { "球队名称", "比赛场数", "投篮命中数",
					"投篮出手次数", "三分命中数", "三分出手数", "罚球命中数", "罚球出手数", "进攻篮板数",
					"防守篮板数", "篮板数", "助攻数", "抢断数", "盖帽数", "失误数", "犯规数", "比赛得分",
					"投篮命中率", "三分命中率", "罚球命中率", "胜率", "进攻回合", "进攻效率", "防守效率",
					"进攻篮板效率", "防守篮板效率", "抢断效率", "助攻率" });
			choose.addActionListener(e -> showMessage_team());
			break;
		default:
			choose = new MyComboBox(new String[] { "得分", "篮板", "助攻",
					"得分/篮板/助攻", "盖帽", "抢断", "犯规", "失误", "分钟", "效率", "投篮", "三分",
					"罚球", "两双" });
			choose.addActionListener(e -> showMessage_player());

			break;
		}
		choose.setBounds(10, 50, 150, 30);
		choose.repaint();
		choose.setVisible(true);
		show.add(choose);
		show.repaint();
		this.repaint();
	}

	/** 热点球员 */
	void showMessage_player() {

		String sortBy = (String) choose.getSelectedItem();
		PlayerSortBy playerSortBy = sortby(sortBy);

		// portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_1.setBackground(FrameSize.buttonbackColor);
		portrait_1.setOpaque(true);
		portrait_1.setBounds(FrameSize.width / 3 - 228, FrameSize.height / 10,
				185, 123);

		// portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_2.setBackground(FrameSize.buttonbackColor);
		portrait_2.setOpaque(true);
		portrait_2.setBounds(FrameSize.width / 3 - 100,
				3 * FrameSize.height / 10, 57, 46);

		// portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_3.setBackground(FrameSize.buttonbackColor);
		portrait_3.setOpaque(true);
		portrait_3.setBounds(FrameSize.width / 3 - 100,
				4 * FrameSize.height / 10, 57, 46);

		// portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_4.setBackground(FrameSize.buttonbackColor);
		portrait_4.setOpaque(true);
		portrait_4.setBounds(FrameSize.width / 3 - 100,
				5 * FrameSize.height / 10, 57, 46);

		// portraitLabel.setIcon(scaleImage(new ImageIcon(),115,92));
		portrait_5.setBackground(FrameSize.buttonbackColor);
		portrait_5.setOpaque(true);
		portrait_5.setBounds(FrameSize.width / 3 - 100,
				6 * FrameSize.height / 10, 57, 46);

		show.add(portrait_1);
		show.add(portrait_2);
		show.add(portrait_3);
		show.add(portrait_4);
		show.add(portrait_5);
	}

	/** 赛季热点球队 */
	void showMessage_team() {
		TeamSortBy teamSortBy = null;
		String sortby = (String) choose.getSelectedItem();
		if (sortby.equals("球队名称")) {
			teamSortBy = TeamSortBy.name;
		} else if (sortby.equals("比赛场数")) {
			teamSortBy = TeamSortBy.matchNo;
		} else if (sortby.equals("投篮命中数")) {
			teamSortBy = TeamSortBy.hitNo;
		} else if (sortby.equals("投篮出手次数")) {
			teamSortBy = TeamSortBy.handNo;
		} else if (sortby.equals("三分命中数")) {
			teamSortBy = TeamSortBy.threeHitNo;
		} else if (sortby.equals("三分出手数")) {
			teamSortBy = TeamSortBy.threeHandNo;
		} else if (sortby.equals("罚球命中数")) {
			teamSortBy = TeamSortBy.penaltyHitNo;
		} else if (sortby.equals("罚球出手数")) {
			teamSortBy = TeamSortBy.penaltyHandNo;
		} else if (sortby.equals("进攻篮板数")) {
			teamSortBy = TeamSortBy.offenseRebs;
		} else if (sortby.equals("防守篮板数")) {
			teamSortBy = TeamSortBy.defenceRebs;
		} else if (sortby.equals("篮板数")) {
			teamSortBy = TeamSortBy.rebs;
		} else if (sortby.equals("助攻数")) {
			teamSortBy = TeamSortBy.assistNo;
		} else if (sortby.equals("抢断数")) {
			teamSortBy = TeamSortBy.stealsNo;
		} else if (sortby.equals("盖帽数")) {
			teamSortBy = TeamSortBy.blockNo;
		} else if (sortby.equals("失误数")) {
			teamSortBy = TeamSortBy.mistakesNo;
		} else if (sortby.equals("犯规数")) {
			teamSortBy = TeamSortBy.foulsNo;
		} else if (sortby.equals("比赛得分")) {
			teamSortBy = TeamSortBy.points;
		} else if (sortby.equals("投篮命中率")) {
			teamSortBy = TeamSortBy.hitRate;
		} else if (sortby.equals("三分命中率")) {
			teamSortBy = TeamSortBy.threeHitRate;
		} else if (sortby.equals("罚球命中率")) {
			teamSortBy = TeamSortBy.penaltyHitRate;
		} else if (sortby.equals("胜率")) {
			teamSortBy = TeamSortBy.winRate;
		} else if (sortby.equals("进攻回合")) {
			teamSortBy = TeamSortBy.offenseRound;
		} else if (sortby.equals("进攻效率")) {
			teamSortBy = TeamSortBy.offenseEfficiency;
		} else if (sortby.equals("防守效率")) {
			teamSortBy = TeamSortBy.defenceEfficiency;
		} else if (sortby.equals("进攻篮板效率")) {
			teamSortBy = TeamSortBy.drebsEfficiency;
		} else if (sortby.equals("防守篮板效率")) {
			teamSortBy = TeamSortBy.orebsEfficiency;
		} else if (sortby.equals("抢断效率")) {
			teamSortBy = TeamSortBy.stealsEfficiency;
		} else if (sortby.equals("助攻率")) {
			teamSortBy = TeamSortBy.assistEfficiency;
		}

		TeamMatchVO[] hotteam = tc.getHotTeams(teamSortBy);

		name_1.setText(tc.getTeamData(hotteam[0].getName()).getName() + "   "
				+ hotteam[0].getName());
		name_2.setText(tc.getTeamData(hotteam[1].getName()).getName() + "   "
				+ hotteam[1].getName());
		name_3.setText(tc.getTeamData(hotteam[2].getName()).getName() + "   "
				+ hotteam[2].getName());
		name_4.setText(tc.getTeamData(hotteam[3].getName()).getName() + "   "
				+ hotteam[3].getName());
		name_5.setText(tc.getTeamData(hotteam[4].getName()).getName() + "   "
				+ hotteam[4].getName());

		name_1.setFont(new Font("Comic Sans MS", Font.BOLD, 24));

		portrait_1.setIcon(scaleImage(new ImageIcon(tc.getTeamData(hotteam[0].getName()).getImage()),FrameSize.width/8,FrameSize.width/8));
		portrait_2.setIcon(scaleImage(new ImageIcon(tc.getTeamData(hotteam[1].getName()).getImage()),FrameSize.width/12,FrameSize.width/12));
		portrait_3.setIcon(scaleImage(new ImageIcon(tc.getTeamData(hotteam[2].getName()).getImage()),FrameSize.width/12,FrameSize.width/12));
		portrait_4.setIcon(scaleImage(new ImageIcon(tc.getTeamData(hotteam[3].getName()).getImage()),FrameSize.width/12,FrameSize.width/12));
		portrait_5.setIcon(scaleImage(new ImageIcon(tc.getTeamData(hotteam[4].getName()).getImage()),FrameSize.width/12,FrameSize.width/12));
		
		portrait_1.setBounds(13*FrameSize.width / 60, FrameSize.height / 10,
				FrameSize.width/8,FrameSize.width/8);
		portrait_2.setBounds(7*FrameSize.width / 30,
				3 * FrameSize.height / 10, FrameSize.width/12,FrameSize.width/12);
		portrait_3.setBounds(7*FrameSize.width / 30,
				4* FrameSize.height / 10, FrameSize.width/12,FrameSize.width/12);
		portrait_4.setBounds(7*FrameSize.width / 30,
				5 * FrameSize.height / 10, FrameSize.width/12,FrameSize.width/12);
		portrait_5.setBounds(7*FrameSize.width / 30,
				6 * FrameSize.height / 10, FrameSize.width/12,FrameSize.width/12);

		
		show.add(portrait_1);
		show.add(portrait_2);
		show.add(portrait_3);
		show.add(portrait_4);
		show.add(portrait_5);

		show.repaint();
		this.repaint();
	}

	private PlayerSortBy sortby(String sortBy) {
		PlayerSortBy playerSortBy = null;

		if (sortBy.equals("得分")) {
			playerSortBy = PlayerSortBy.points;
		} else if (sortBy.equals("篮板")) {
			playerSortBy = PlayerSortBy.rebound;
		} else if (sortBy.equals("助攻")) {
			playerSortBy = PlayerSortBy.assist;
		} else if (sortBy.equals("得分/篮板/助攻")) {
			playerSortBy = PlayerSortBy.scoring_rebound_assist;
		} else if (sortBy.equals("盖帽")) {
			playerSortBy = PlayerSortBy.block;
		} else if (sortBy.equals("抢断")) {
			playerSortBy = PlayerSortBy.steal;
		} else if (sortBy.equals("犯规")) {
			playerSortBy = PlayerSortBy.foul;
		} else if (sortBy.equals("失误")) {
			playerSortBy = PlayerSortBy.mistake;
		} else if (sortBy.equals("分钟")) {
			playerSortBy = PlayerSortBy.minute;
		} else if (sortBy.equals("效率")) {
			playerSortBy = PlayerSortBy.efficiency;
		} else if (sortBy.equals("投篮")) {
			playerSortBy = PlayerSortBy.shot;
		} else if (sortBy.equals("三分")) {
			playerSortBy = PlayerSortBy.three_points;
		} else if (sortBy.equals("罚球")) {
			playerSortBy = PlayerSortBy.freeThrow;
		} else if (sortBy.equals("两双")) {
			playerSortBy = PlayerSortBy.twoPair;
		}
		return playerSortBy;

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
