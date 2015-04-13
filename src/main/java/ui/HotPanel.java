package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyFrame;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.TeamMatchVO;
import vo.TeamSortBy;
import bl.playerbl.PlayerController;
import bl.teambl.TeamController;

public class HotPanel extends JPanel {

	JPanel tag = new JPanel();
	JPanel show = new JPanel();

	JLabel[] name=new JLabel[5];
//	JLabel name_2=new JLabel();
//	JLabel name_3=new JLabel();
//	JLabel name_4=new JLabel();
//	JLabel name_5=new JLabel();

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
	PlayerController pc=new PlayerController();
	int hottype = 1;

	HotPanel hotpanel=this;
	public HotPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setOpaque(false);
		
		setTag();
		setShow();
		showchoose(1);
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

		choose = new MyComboBox(new String[] { "得分", "篮板", "助攻",
				"得分/篮板/助攻", "盖帽", "抢断", "犯规", "失误", "分钟", "效率", "投篮", "三分",
				"罚球", "两双" });
		
		
		
		for(int i=0;i<5;i++){
			name[i]=new JLabel();
			name[i].setBounds(FrameSize.width / 3, (i+1)*FrameSize.height / 7 , FrameSize.width / 6, FrameSize.height/16);
			name[i].setForeground(Color.white);
			show.add(name[i]);
		}

		name[0].setBounds(FrameSize.width / 3, FrameSize.height/10, FrameSize.width / 5, FrameSize.height/10);
		name[0].setFont(new Font("Comic Sans MS", Font.BOLD, 24));
		
		score_1.setBounds(FrameSize.width / 3 + 250, FrameSize.height/10, 100, 100);
		score_2.setBounds(FrameSize.width / 3 + 250,
				2*FrameSize.height / 7 , 80, 80);
		score_3.setBounds(FrameSize.width / 3 + 250,
				3 * FrameSize.height / 7 , 80, 80);
		score_4.setBounds(FrameSize.width / 3 + 250,
				4 * FrameSize.height / 7 , 80, 80);
		score_5.setBounds(FrameSize.width / 3 + 250,
				5 * FrameSize.height / 7 , 80, 80);

		score_1.setForeground(Color.white);
		score_2.setForeground(Color.white);
		score_3.setForeground(Color.white);
		score_4.setForeground(Color.white);
		score_5.setForeground(Color.white);


//		show.add(choose);
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
			choose = new MyComboBox(new String[] { "比赛场数", "投篮命中数",
					"投篮出手次数", "三分命中数", "三分出手数", "罚球命中数", "罚球出手数", "进攻篮板数",
					"防守篮板数", "篮板数", "助攻数", "抢断数", "盖帽数", "失误数", "犯规数", "比赛得分",
					"投篮命中率", "三分命中率", "罚球命中率", "胜率", "进攻回合", "进攻效率", "防守效率",
					"进攻篮板效率", "防守篮板效率", "抢断效率", "助攻率" });
			choose.setSelectedIndex(0);
			showMessage_team();
			choose.addActionListener(e -> showMessage_team());
			break;
		case 4:
			choose = new MyComboBox(new String[] { "得分提升率", "篮板提升率", "助攻提升率"});
			choose.setSelectedIndex(0);
			showMessage_player();
			choose.addActionListener(e -> showMessage_player());
			break;
		default:
			choose = new MyComboBox(new String[] { "得分", "篮板", "助攻",
					"得分/篮板/助攻", "盖帽", "抢断", "犯规", "失误", "分钟", "效率", "投篮", "三分",
					"罚球", "两双" });
			choose.setSelectedIndex(0);
			showMessage_player();
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
		PlayerMatchVO[] players = new PlayerMatchVO[5];
		switch(hottype){
		case 1:players=pc.getDayHotPlayer(playerSortBy);
		break;
		case 2:players=pc.getSeasonHotPlayer(playerSortBy);
		break;
		case 4:players=pc.getPromotePlayer(playerSortBy);
		}
		
		portrait_1.setIcon(scaleImage(new ImageIcon(pc.findPlayer(players[0].getName()).getPortrait()),115,92));
		portrait_1.setBounds(FrameSize.width / 3 - 228, FrameSize.height / 10,
				185, 123);

		portrait_2.setIcon(scaleImage(new ImageIcon(pc.findPlayer(players[1].getName()).getPortrait()),69, 55));
		portrait_2.setBounds(FrameSize.width / 3 - 100,
				2*FrameSize.height / 7 , 69, 55);

		portrait_3.setIcon(scaleImage(new ImageIcon(pc.findPlayer(players[2].getName()).getPortrait()),69, 55));
		portrait_3.setBounds(FrameSize.width / 3 - 100,
				3*FrameSize.height / 7 , 69, 55);

		portrait_4.setIcon(scaleImage(new ImageIcon(pc.findPlayer(players[3].getName()).getPortrait()),69, 55));
		portrait_4.setBounds(FrameSize.width / 3 - 100,
				4*FrameSize.height / 7 , 69, 55);

		portrait_5.setIcon(scaleImage(new ImageIcon(pc.findPlayer(players[4].getName()).getPortrait()),69, 55));
		portrait_5.setBounds(FrameSize.width / 3 - 100,
				5*FrameSize.height / 7 , 69, 55);
		
		for(int i=0;i<5;i++){
			name[i].setText(players[i].getName());
			name[i].addMouseListener(new showPlayer());
		}		
		
		score_1.setText(String.format("%.3f",players[0].getHotData()));
		score_2.setText(String.format("%.3f",players[1].getHotData()));
		score_3.setText(String.format("%.3f",players[2].getHotData()));
		score_4.setText(String.format("%.3f",players[3].getHotData()));
		score_5.setText(String.format("%.3f",players[4].getHotData()));
		
		show.add(portrait_1);
		show.add(portrait_2);
		show.add(portrait_3);
		show.add(portrait_4);
		show.add(portrait_5);
		
		show.repaint();
		this.repaint();
	}
	/**热点球队*/
	/** 赛季热点球队 */
	void showMessage_team() {
		TeamSortBy teamSortBy = null;
		String sortby = (String) choose.getSelectedItem();
		if (sortby.equals("比赛场数")) {
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

		for(int i=0;i<5;i++){
			name[i].setText(tc.getTeamData(hotteam[i].getName()).getName() + "|"
				+ hotteam[i].getName());
			name[i].addMouseListener(new showTeam());
		}


		portrait_1.setIcon(scaleImage(new ImageIcon(tc.getTeamData(hotteam[0].getName()).getImage()),FrameSize.width/8,FrameSize.width/8));
		portrait_2.setIcon(scaleImage(new ImageIcon(tc.getTeamData(hotteam[1].getName()).getImage()),FrameSize.width/12,FrameSize.width/12));
		portrait_3.setIcon(scaleImage(new ImageIcon(tc.getTeamData(hotteam[2].getName()).getImage()),FrameSize.width/12,FrameSize.width/12));
		portrait_4.setIcon(scaleImage(new ImageIcon(tc.getTeamData(hotteam[3].getName()).getImage()),FrameSize.width/12,FrameSize.width/12));
		portrait_5.setIcon(scaleImage(new ImageIcon(tc.getTeamData(hotteam[4].getName()).getImage()),FrameSize.width/12,FrameSize.width/12));
		
		portrait_1.setBounds(13*FrameSize.width / 60, FrameSize.height / 10,
				FrameSize.width/8,FrameSize.width/8);
		portrait_2.setBounds(7*FrameSize.width / 30,
				2*FrameSize.height / 7 , FrameSize.width/12,FrameSize.width/12);
		portrait_3.setBounds(7*FrameSize.width / 30,
				3*FrameSize.height / 7 , FrameSize.width/12,FrameSize.width/12);
		portrait_4.setBounds(7*FrameSize.width / 30,
				4*FrameSize.height / 7 , FrameSize.width/12,FrameSize.width/12);
		portrait_5.setBounds(7*FrameSize.width / 30,
				5*FrameSize.height / 7 , FrameSize.width/12,FrameSize.width/12);

		score_1.setText(String.format("%.3f",hotteam[0].getHotData()));
		score_2.setText(String.format("%.3f",hotteam[1].getHotData()));
		score_3.setText(String.format("%.3f",hotteam[2].getHotData()));
		score_4.setText(String.format("%.3f",hotteam[3].getHotData()));
		score_5.setText(String.format("%.3f",hotteam[4].getHotData()));
		
		show.add(portrait_1);
		show.add(portrait_2);
		show.add(portrait_3);
		show.add(portrait_4);
		show.add(portrait_5);

		show.repaint();
		this.repaint();
	}
	/**跳转到team*/
	class showTeam implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			String teamname=((JLabel) e.getSource()).getText();
		    MyFrame.teampanel.findClick(teamname.substring(teamname.length()-3));
			MyFrame.card.show(MyFrame.mainpanel, "team");
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			((JLabel)e.getSource()).setOpaque(true);
			((JLabel)e.getSource()).setBackground(Color.gray);
			show.repaint();
			hotpanel.repaint();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			((JLabel)e.getSource()).setOpaque(false);
			((JLabel)e.getSource()).repaint();
			show.repaint();
			hotpanel.repaint();
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
	/**跳转到player*/
	class showPlayer implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			String playerInfo=((JLabel) arg0.getSource()).getText();
			MyFrame.playerpanel.findPlayerClick(playerInfo);
			MyFrame.card.show(MyFrame.mainpanel, "player");
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			((JLabel)arg0.getSource()).setOpaque(true);
			((JLabel)arg0.getSource()).setBackground(Color.gray);
			show.repaint();
			hotpanel.repaint();
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			((JLabel)arg0.getSource()).setOpaque(false);
			((JLabel)arg0.getSource()).repaint();
			show.repaint();
			hotpanel.repaint();
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
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
		} else if (sortBy.equals("得分提升率")) {
			playerSortBy = PlayerSortBy.points_uprate;
		} else if (sortBy.equals("篮板提升率")) {
			playerSortBy = PlayerSortBy.rebs_uprate;
		} else if (sortBy.equals("助攻提升率")) {
			playerSortBy = PlayerSortBy.help_uprate;
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
