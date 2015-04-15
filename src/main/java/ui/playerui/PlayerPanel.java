package ui.playerui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import po.PlayerPO;
import ui.mainui.EditableTextField;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import ui.mainui.UneditableTextField;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.PlayerVO;
import vo.SortType;
import bl.playerbl.PlayerController;

public class PlayerPanel extends JPanel {

	JPanel welcomePanel = new JPanel();
	PlayerMatchPanel playerMatchPanel;
	boolean matchpanel=false;
	DefaultTableModel table;
	JScrollPane jScrollPane;
	JTextField searchField = new JTextField();
	JPanel sortPanel = new JPanel();
	JPanel findPanel = new JPanel();
	JPanel screenPanel = new JPanel();
	MyTable mytable;

	JComboBox<String> sortPlayerBox;

	JTextField nameText = new UneditableTextField();
	JTextField numText = new UneditableTextField();
	JTextField positionText = new UneditableTextField();
	JTextField teamText = new UneditableTextField();
	JTextField birthText = new UneditableTextField();
	JTextField ageText = new UneditableTextField();
	JTextField expText = new UneditableTextField();
	JTextField schoolText = new UneditableTextField();
	JTextField heightText = new UneditableTextField();
	JButton matchButton;
	JButton playerTeamButton;
	JLabel actionLabel = new JLabel();
	JLabel portraitLabel = new JLabel();

	JComboBox<String> positionBox;
	JComboBox<String> playerZoneBox;
	JComboBox<String> playerAreaBox;
	JComboBox<String> screenPlayerBox;
	JComboBox<String> dataType;
	boolean sortType = true;

	PlayerController playerController = new PlayerController();
	
	public PlayerPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		JPanel header = headerPanel();
		new Thread() {
			public void run() {
				setTable(playerController.sortTotalPlayers(PlayerSortBy.name,
						SortType.ASEND));
			}
		}.start();
		;

		setSort();
		setFind();
		setScreen();
		setWelcome();

		this.add(welcomePanel);
		this.add(header);
		this.repaint();
	}

	/** 设置表格 */
	private void setTable(PlayerMatchVO[] playerMatchVOs) {
		Vector columnsName = new Vector();
		columnsName.add("球员名称");
		columnsName.add("球衣号码");
		columnsName.add("位置");
		columnsName.add("身高");
		columnsName.add("生日");
		columnsName.add("年龄");
		columnsName.add("球龄");
		columnsName.add("毕业学校");
		columnsName.add("所属球队");
		columnsName.add("参赛场数");
		columnsName.add("先发场数");
		columnsName.add("篮板");
		columnsName.add("助攻");
		columnsName.add("在场时间");

		columnsName.add("盖帽数");
		columnsName.add("得分/篮板/助攻");
		columnsName.add("抢断");
		columnsName.add("犯规");
		columnsName.add("失误");
		columnsName.add("分钟");
		columnsName.add("投篮");
		columnsName.add("三分");
		columnsName.add("罚球");
		columnsName.add("两双");

		columnsName.add("投篮(%)");
		columnsName.add("三分(%)");
		columnsName.add("罚球(%)");
		columnsName.add("进攻");
		columnsName.add("防守");
		columnsName.add("抢断");
		columnsName.add("盖帽");
		columnsName.add("失误");
		columnsName.add("犯规");
		columnsName.add("得分");
		columnsName.add("效率");
		columnsName.add("GmSc效率");
		columnsName.add("真实命中率(%)");
		columnsName.add("投篮效率");
		columnsName.add("篮板率");
		columnsName.add("进攻篮板率");
		columnsName.add("防守篮板率");
		columnsName.add("助攻率(%)");
		columnsName.add("抢断率");
		columnsName.add("盖帽率(%)");
		columnsName.add("失误率(%)");
		columnsName.add("使用率(%)");

		Vector data = new Vector();
		for(int i=0;i<playerMatchVOs.length;i++){
			PlayerMatchVO playerVO = playerMatchVOs[i];
			Vector rowData = new Vector();
			rowData.add(playerVO.getName());
			// int playerNum = playerVO.getNumber();
			// String number = playerNum != -1 ? String.valueOf(playerNum) :
			// "-";
			// rowData.add(number);
			// rowData.add(playerVO.getPosition());
			// rowData.add(playerVO.getHeightfeet() + "-"
			// + playerVO.getHeightinch());
			// rowData.add(playerVO.getBirth());
			// rowData.add(playerVO.getAge());
			// rowData.add(playerVO.getExp());
			// rowData.add(playerVO.getSchool());
			rowData.add(playerVO.getTeam());
			rowData.add(playerVO.getMatchNo());
			rowData.add(playerVO.getFirstServiceNo());
			rowData.add(String.format("%.3f", playerVO.getRebs()));
			rowData.add(String.format("%.3f", playerVO.getAssistNo()));
			rowData.add(String.format("%.3f", playerVO.getTime()));

			rowData.add(String.format("%.3f", playerVO.getBlockNo()));
			rowData.add(String.format("%.3f",
					playerVO.getScoring_rebound_assist()));
			rowData.add(String.format("%.3f", playerVO.getStealsNo()));
			rowData.add(String.format("%.3f", playerVO.getFoulsNo()));
			rowData.add(String.format("%.3f", playerVO.getMistakesNo()));
			rowData.add(String.format("%.3f", playerVO.getMinute()));
			rowData.add(String.format("%.3f", playerVO.getHandNo()));
			rowData.add(String.format("%.3f", playerVO.getThree_points()));
			rowData.add(String.format("%.3f", playerVO.getBlockNo()));
			rowData.add(String.format("%.3f", playerVO.getTwoPair()));
			double hitRate = playerVO.getHitRate() * 100;
			if (hitRate >= 0)
				rowData.add(String.format("%.3f", hitRate));
			else
				rowData.add("-");
			double threeHitRate = playerVO.getThreeHitRate();
			if (threeHitRate >= 0)
				rowData.add(String.format("%.3f", threeHitRate * 100));
			else
				rowData.add("-");
			double penaltyHitRate = playerVO.getPenaltyHitRate();
			if (penaltyHitRate >= 0)
				rowData.add(String.format("%.3f", penaltyHitRate * 100));
			else
				rowData.add("-");
			rowData.add(String.format("%.3f", playerVO.getOffendNo()));
			rowData.add(String.format("%.3f", playerVO.getDefenceNo()));
			rowData.add(String.format("%.3f", playerVO.getStealsNo()));
			rowData.add(String.format("%.3f", playerVO.getBlockNo()));
			rowData.add(playerVO.getMistakesNo());
			rowData.add(String.format("%.3f", playerVO.getFoulsNo()));
			rowData.add(String.format("%.3f", playerVO.getPoints()));
			rowData.add(String.format("%.3f", playerVO.getEfficiency()));
			rowData.add(String.format("%.3f", playerVO.getGmScEfficiency()));
			rowData.add(String.format("%.3f", playerVO.getTrueHitRate() * 100));
			rowData.add(String.format("%.3f", playerVO.getHitEfficiency()));
			rowData.add(String.format("%.3f", playerVO.getRebEfficiency()));
			rowData.add(String.format("%.3f",
					playerVO.getOffenseRebsEfficiency()));
			rowData.add(String.format("%.3f",
					playerVO.getDefenceRebsEfficiency()));
			rowData.add(String.format("%.3f",
					playerVO.getAssistEfficiency() * 100));
			rowData.add(String.format("%.3f", playerVO.getStealsEfficiency()));
			rowData.add(String.format("%.3f",
					playerVO.getBlockEfficiency() * 100));
			rowData.add(String.format("%.3f",
					playerVO.getMistakeEfficiency() * 100));
			rowData.add(String.format("%.3f", playerVO.getUseEfficiency() * 100));

			data.add(rowData);
		}
		table = new DefaultTableModel(data, columnsName);
		mytable = new MyTable(table);
		jScrollPane = new JScrollPane(mytable);
		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(FrameSize.width / 3, FrameSize.height / 12,
				2 * FrameSize.width / 3, FrameSize.height * 7 / 8
						- FrameSize.height / 12);
		resizeTable(false, jScrollPane, mytable);
		this.add(jScrollPane);

		mytable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					showOne((String) mytable.getModel().getValueAt(
							mytable.getSelectedRow(), 0));
				}
			}

		});
	}

	/**显示一个球队的所有球员*/
	public void showTeamPlayers(PlayerMatchVO[] playerMatchVO){
		this.remove(welcomePanel);
		this.remove(sortPanel);
		this.remove(screenPanel);
		jScrollPane.setVisible(false);
		setTable(playerMatchVO);
		jScrollPane.setVisible(true);
		showOne(playerMatchVO[0].getName());
		this.add(jScrollPane);
		this.repaint();
	}

	/** 在findPanel上显示一个球员的信息 */
	private void showOne(String playerInfo) {
		if(matchpanel){
			this.remove(playerMatchPanel);
			matchpanel=false;
		}
		this.remove(welcomePanel);
		this.remove(sortPanel);
		this.remove(screenPanel);
		findPanel.remove(actionLabel);

		matchButton = new JButton(new ImageIcon("image/showMatch.jpg"));
		playerTeamButton = new JButton("team");
		playerTeamButton.setBounds(FrameSize.width/3-45, 11*FrameSize.height /16, 40,40);
		matchButton.setBounds(FrameSize.width/3-90, 11*FrameSize.height /16, 40,40);
		matchButton.addActionListener(e->setMatch());
		playerTeamButton.addActionListener(e->showPlayerTeam(teamText.getText()));

		
		PlayerPO playerVO = playerController.findPlayer(playerInfo);
		PlayerMatchVO playerMatchVO=playerController.findPlayerMatchAve(playerInfo);
		nameText.setText(playerVO.getName());
		numText.setText(String.valueOf(playerVO.getNumber()));
		positionText.setText(String.valueOf(playerVO.getPosition()));
		teamText.setText(playerMatchVO.getTeam());
		birthText.setText(playerVO.getBirth());
		ageText.setText(String.valueOf(playerVO.getAge()));
		expText.setText(String.valueOf(playerVO.getExp()));
		schoolText.setText(playerVO.getSchool());
		heightText.setText(String.valueOf(playerVO.getHeightfeet()) + "-"
				+ String.valueOf(playerVO.getHeightinch()));

		portraitLabel.setIcon(scaleImage(new ImageIcon(playerVO.getPortrait()),
				115, 92));
		actionLabel.setIcon(scaleImage(new ImageIcon(playerVO.getAction()),
				170, 272));

		findPanel.add(actionLabel);
		findPanel.add(matchButton);
		findPanel.add(playerTeamButton);
		this.add(findPanel);
		this.repaint();
	}

	private void showPlayerTeam(String teamName) {
		MyFrame.teampanel.findClick(teamName);
		MyFrame.card.show(MyFrame.mainpanel,"team");
	}

	private void setMatch() {
//		System.out.println(teamText.getText());
		if(matchpanel){
			this.remove(playerMatchPanel);
		}
		matchpanel=true;
		playerMatchPanel = new PlayerMatchPanel(nameText.getText(),teamText.getText());
		this.remove(jScrollPane);
		this.add(playerMatchPanel);
		this.repaint();
	}

	/** 筛选Panel */
	private void setScreen() {

		screenPanel.setLayout(null);
		screenPanel.setBackground(FrameSize.backColor);
		screenPanel.setBounds(0, FrameSize.height / 12, FrameSize.width / 3,
				11 * FrameSize.height / 12);

		JLabel positionLabel = new JLabel("球员位置");
		positionLabel.setForeground(Color.white);
		positionLabel.setBounds(FrameSize.width / 30, FrameSize.height / 12,
				100, 40);
		positionLabel.setFont(new Font("微软雅黑", Font.BOLD, 17));
		screenPanel.add(positionLabel);

		positionBox = new JComboBox<String>(new String[] { "F", "C", "G" });
		positionBox.setBounds(FrameSize.width / 8, FrameSize.height / 12, 150,
				40);
		positionBox.setFont(new Font("宋体", Font.PLAIN, 12));
		screenPanel.add(positionBox);

		JLabel playerZoneLabel = new JLabel("赛区");
		playerZoneLabel.setForeground(Color.white);
		playerZoneLabel.setBounds(FrameSize.width / 30,
				FrameSize.height / 3 - 60, 100, 40);
		playerZoneLabel.setFont(new Font("微软雅黑", Font.BOLD, 17));
		screenPanel.add(playerZoneLabel);

		playerZoneBox = new JComboBox<String>(new String[] { "东部", "西部" });
		playerZoneBox.setBounds(FrameSize.width / 8, FrameSize.height / 3 - 60,
				150, 40);
		playerZoneBox.setFont(new Font("宋体", Font.PLAIN, 12));
		screenPanel.add(playerZoneBox);
		playerZoneBox.addActionListener(e -> setPlayerAreaBox());

		JLabel playerAreaLabel = new JLabel("分区");
		playerAreaLabel.setForeground(Color.white);
		playerAreaLabel.setBounds(FrameSize.width / 30,
				FrameSize.height / 3 + 20, 100, 40);
		playerAreaLabel.setFont(new Font("微软雅黑", Font.BOLD, 17));
		screenPanel.add(playerAreaLabel);

		playerAreaBox = new JComboBox<String>(new String[] { "ATLANTIC",
				"CENTRAL", "SOUTHEAST" });
		playerAreaBox.setBounds(FrameSize.width / 8, FrameSize.height / 3 + 20,
				150, 40);
		playerAreaBox.setFont(new Font("宋体", Font.PLAIN, 12));
		screenPanel.add(playerAreaBox);

		JLabel screenPlayerLabel = new JLabel("筛选依据");
		screenPlayerLabel.setForeground(Color.white);
		screenPlayerLabel.setBounds(FrameSize.width / 30,
				FrameSize.height / 2 + 10, 100, 40);
		screenPlayerLabel.setFont(new Font("微软雅黑", Font.BOLD, 17));
		screenPanel.add(screenPlayerLabel);

		screenPlayerBox = new JComboBox<String>(new String[] { "得分", "篮板",
				"助攻", "得分/篮板/助攻", "盖帽", "抢断", "犯规", "失误", "分钟", "效率", "投篮",
				"三分", "罚球", "两双" });
		screenPlayerBox.setBounds(FrameSize.width / 8,
				FrameSize.height / 2 + 10, 150, 40);
		screenPlayerBox.setFont(new Font("宋体", Font.PLAIN, 12));
		screenPanel.add(screenPlayerBox);

		JButton yesButton = new JButton(new ImageIcon("image/yes.png"));
		yesButton.setBounds(FrameSize.width / 4, 2 * FrameSize.height / 3, 50,
				50);
		yesButton.setBackground(Color.white);
		yesButton.addActionListener(e -> screenPlayerConfirmClick());
		screenPanel.add(yesButton);

	}

	/** 点击筛选确认按钮 */
	private void screenPlayerConfirmClick() {
		jScrollPane.setVisible(false);
		String position = (positionBox.getSelectedItem().toString());

		Area area = null;
		String selectArea = playerAreaBox.getSelectedItem().toString();
		if (selectArea.equals("ATLANTIC")) {
			area = Area.ATLANTIC;
		} else if (selectArea.equals("CENTRAL")) {
			area = Area.CENTRAL;
		} else if (selectArea.equals("NORTHWEST")) {
			area = Area.NORTHWEST;
		} else if (selectArea.equals("PACIFIC")) {
			area = Area.PACIFIC;
		} else if (selectArea.equals("SOUTHWEST")) {
			area = Area.SOUTHWEST;
		} else if (selectArea.equals("SOUTHEAST")) {
			area = Area.SOUTHEAST;
		}

		PlayerSortBy playerSortBy = null;
		String sortBy = (String) screenPlayerBox.getSelectedItem();
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
		PlayerMatchVO[] screenPlayer;
		if(dataType.getSelectedItem().equals("赛季总数据"))
			screenPlayer = playerController.screenTotalPlayers(position, area, playerSortBy);
		else
			screenPlayer = playerController.screenAvePlayers(position, area, playerSortBy);
		setTable(screenPlayer);
		jScrollPane.repaint();
		jScrollPane.setVisible(true);
		this.add(jScrollPane);
		this.repaint();

	}

	/** 按照赛区显示分区 */
	private void setPlayerAreaBox() {
		screenPanel.remove(playerAreaBox);
		if (playerZoneBox.getSelectedItem().equals("东部")) {
			playerAreaBox = new JComboBox<String>(new String[] { "ATLANTIC",
					"CENTRAL", "SOUTHEAST" });
			playerAreaBox.setBounds(FrameSize.width / 8,
					FrameSize.height / 3 + 20, 150, 40);
			playerAreaBox.setFont(new Font("宋体", Font.PLAIN, 12));
		}

		else {
			playerAreaBox = new JComboBox<String>(new String[] { "SOUTHWEST",
					"NORTHWEST", "PACIFIC" });
			playerAreaBox.setBounds(FrameSize.width / 8,
					FrameSize.height / 3 + 20, 150, 40);
			playerAreaBox.setFont(new Font("宋体", Font.PLAIN, 12));
		}
		playerAreaBox.repaint();
		playerAreaBox.setVisible(true);
		screenPanel.add(playerAreaBox);
		screenPanel.repaint();
	}

	/** 查找Panel */
	private void setFind() {

		findPanel.setBackground(FrameSize.backColor);
		findPanel.setBounds(0, FrameSize.height / 12, FrameSize.width / 3,
				11 * FrameSize.height / 12);
		findPanel.setLayout(null);

		// JLabel nameLabel = new JLabel("名称");
		JLabel numLabel = new JLabel("号码");
		JLabel positionLabel = new JLabel("位置");
		JLabel teamLabel = new JLabel("球队");
		JLabel birthLabel = new JLabel("生日");
		JLabel ageLabel = new JLabel("年龄");
		JLabel expLabel = new JLabel("球龄");
		JLabel schoolLabel = new JLabel("学校");
		JLabel heightLabel = new JLabel("身高");

		numLabel.setForeground(Color.white);
		positionLabel.setForeground(Color.white);
		teamLabel.setForeground(Color.white);
		birthLabel.setForeground(Color.white);
		ageLabel.setForeground(Color.white);
		expLabel.setForeground(Color.white);
		schoolLabel.setForeground(Color.white);
		heightLabel.setForeground(Color.white);

		// nameLabel.setBounds(5,FrameSize.height/8-50,60,30);
		numLabel.setBounds(5, FrameSize.height / 8 + 132, 60, 30);
		positionLabel.setBounds(5, FrameSize.height / 8 + 192, 60, 30);
		teamLabel.setBounds(5, FrameSize.height / 8 + 252, 60, 30);
		ageLabel.setBounds(5, FrameSize.height / 8 + 302, 60, 30);
		expLabel.setBounds(5, FrameSize.height / 8 + 362, 60, 30);
		birthLabel.setBounds(FrameSize.width / 7 + 15,
				FrameSize.height / 8 + 252, 60, 30);
		schoolLabel.setBounds(FrameSize.width / 7 + 15,
				FrameSize.height / 8 + 302, 60, 30);
		heightLabel.setBounds(FrameSize.width / 7 + 15,
				FrameSize.height / 8 + 362, 60, 30);

		portraitLabel.setBounds(5, FrameSize.height / 8 + 10, 115, 92);// 230,185
		// portraitLabel.setBorder(BorderFactory.createLineBorder(new Color(157,
		// 157, 255)));
		actionLabel.setBounds(FrameSize.width / 7, FrameSize.height / 8 - 50,
				170, 272);// 440,700
		// actionLabel.setBorder(BorderFactory.createLineBorder(new Color(157,
		// 157, 255)));
		nameText.setBounds(5, FrameSize.height / 8 - 50, 115, 30);
		numText.setBounds(45, FrameSize.height / 8 + 132, 60, 30);
		positionText.setBounds(45, FrameSize.height / 8 + 192, 60, 30);
		teamText.setBounds(45, FrameSize.height / 8 + 252, 60, 30);
		ageText.setBounds(45, FrameSize.height / 8 + 302, 60, 30);
		expText.setBounds(45, FrameSize.height / 8 + 362, 60, 30);
		birthText.setBounds(FrameSize.width / 7 + 55,
				FrameSize.height / 8 + 252, 100, 30);
		schoolText.setBounds(FrameSize.width / 7 + 55,
				FrameSize.height / 8 + 302, 100, 30);
		heightText.setBounds(FrameSize.width / 7 + 55,
				FrameSize.height / 8 + 362, 100, 30);
		nameText.setBorder(null);
		numText.setBorder(null);
		positionText.setBorder(null);
		teamText.setBorder(null);
		birthText.setBorder(null);
		ageText.setBorder(null);
		expText.setBorder(null);
		schoolText.setBorder(null);
		heightText.setBorder(null);

		// findPanel.add(nameLabel);
		findPanel.add(numLabel);
		findPanel.add(positionLabel);
		findPanel.add(teamLabel);
		findPanel.add(birthLabel);
		findPanel.add(ageLabel);
		findPanel.add(expLabel);
		findPanel.add(schoolLabel);
		findPanel.add(heightLabel);

		findPanel.add(nameText);
		findPanel.add(numText);
		findPanel.add(positionText);
		findPanel.add(teamText);
		findPanel.add(birthText);
		findPanel.add(ageText);
		findPanel.add(expText);
		findPanel.add(schoolText);
		findPanel.add(heightText);
		findPanel.add(portraitLabel);
		findPanel.add(actionLabel);

	}

	/** 排序Panel */
	private void setSort() {

		sortPanel.setLayout(null);
		sortPanel.setBackground(FrameSize.backColor);
		sortPanel.setBounds(0, FrameSize.height / 12, FrameSize.width / 3,
				11 * FrameSize.height / 12);

		JLabel sortPanelby = new JLabel("排序依据");
		sortPanelby.setForeground(Color.white);
		sortPanelby.setBounds(FrameSize.width / 30, FrameSize.height / 5, 100,
				40);
		sortPanelby.setFont(new Font("微软雅黑", Font.BOLD, 17));
		sortPanel.add(sortPanelby);

		sortPlayerBox = new MyComboBox(new String[] { "球员名称", "所属球队", "参赛场数",
				"先发场数", "篮板数", "助攻数", "在场时间", "投篮命中率", "三分命中率", "罚球命中率", "进攻数",
				"防守数", "抢断数", "盖帽数", "失误数", "犯规数", "得分", "效率", "GmSc效率值",
				"真实命中率", "投篮效率", "篮板率", "进攻篮板率", "防守篮板率", "助攻率", "抢断率", "盖帽率",
				"失误率", "使用率" });
		sortPlayerBox.setBounds(FrameSize.width / 8, FrameSize.height / 5, 150,
				40);
		sortPlayerBox.setFont(new Font("宋体", Font.PLAIN, 12));
		sortPanel.add(sortPlayerBox);

		JButton yes = new JButton(new ImageIcon("image/yes.png"));
		yes.setBounds(FrameSize.width / 4, 3 * FrameSize.height / 5, 50, 50);
		yes.setBackground(Color.white);
		yes.addActionListener(e -> sortPlayerConfirmClick());
		sortPanel.add(yes);

		JLabel bigLabel = new JLabel(new ImageIcon("image/升序.png"));
		JRadioButton upRadioButton = new JRadioButton();
		upRadioButton.setBackground(FrameSize.backColor);
		upRadioButton.addActionListener(e -> sortType = true);
		JLabel smallLabel = new JLabel(new ImageIcon("image/降序.png"));
		JRadioButton downRadioButton = new JRadioButton();
		downRadioButton.setBackground(FrameSize.backColor);
		downRadioButton.addActionListener(e -> sortType = false);
		bigLabel.setBounds(FrameSize.width / 10, FrameSize.height / 3 + 40, 50,
				50);
		upRadioButton.setBounds(FrameSize.width / 10 - 30,
				FrameSize.height / 3 + 50, 30, 30);
		smallLabel.setBounds(FrameSize.width / 10 + 120,
				FrameSize.height / 3 + 40, 50, 50);
		downRadioButton.setBounds(FrameSize.width / 10 + 80,
				FrameSize.height / 3 + 50, 30, 30);
		bigLabel.setBackground(Color.white);
		smallLabel.setBackground(Color.white);
		ButtonGroup bg = new ButtonGroup();
		bg.add(upRadioButton);
		bg.add(downRadioButton);
		sortPanel.add(bigLabel);
		sortPanel.add(smallLabel);
		sortPanel.add(upRadioButton);
		sortPanel.add(downRadioButton);

	}

	/** 标题Panel */
	private JPanel headerPanel() {

		JPanel panel = new JPanel();
		// panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBounds(0, 0, FrameSize.width, FrameSize.height / 12);
		panel.setBackground(FrameSize.backColor);

		searchField = new EditableTextField();
		searchField.setBounds(2 * FrameSize.width / 3, 10, FrameSize.width / 9,
				35);
		panel.add(searchField);
		searchField.addKeyListener(new KeyAdapter(){ 
		      public void keyPressed(KeyEvent e)    
		      {    
		        if(e.getKeyChar()==KeyEvent.VK_ENTER )   //按回车键执行相应操作; 
		        { 
		        	findPlayerClick(searchField.getText());
		        } 
		      } 
		    });

		JButton findButton = new JButton(new ImageIcon("image\\find.png"));
		findButton.setBounds(4 * FrameSize.width / 5, 10, 35, 35);
		findButton.setBackground(new Color(68, 68, 68));
		findButton.setToolTipText("查找");
		findButton.addActionListener(e -> findPlayerClick(searchField.getText()));
		panel.add(findButton);

		JButton sortButton = new JButton(new ImageIcon("image\\sort.png"));
		sortButton.setBounds(4 * FrameSize.width / 5 + 40, 10, 35, 35);
		sortButton.setBackground(new Color(68, 68, 68));
		sortButton.setToolTipText("排序");
		sortButton.addActionListener(e -> sortPlayerClick());
		panel.add(sortButton);

		JButton screenButton = new JButton(new ImageIcon("image\\screen.png"));
		screenButton.setBounds(4 * FrameSize.width / 5 + 80, 10, 35, 35);
		screenButton.setBackground(new Color(68, 68, 68));
		screenButton.setToolTipText("筛选");
		screenButton.addActionListener(e -> screenPlayerClick());
		panel.add(screenButton);

		dataType = new MyComboBox(new String[] { "赛季总数据", "场均数据" });
		dataType.setBounds(20, 10, 100, 35);
		panel.add(dataType);

		JButton allButton = new JButton(new ImageIcon("image\\show.png"));
		allButton.setBounds(140, 10, 45, 35);
		allButton.setBackground(new Color(68, 68, 68));
		allButton.setToolTipText("显示");
		allButton.addActionListener(e -> showAllData());
		panel.add(allButton);

		return panel;

	}

	/** 显示所有总数据/场均数据 */
	private void showAllData() {
		jScrollPane.setVisible(false);
		if (dataType.getSelectedItem().equals("赛季总数据"))
			setTable(playerController.sortTotalPlayers(PlayerSortBy.name,
					SortType.ASEND));
		else
			setTable(playerController.sortAvePlayers(PlayerSortBy.name,
					SortType.ASEND));
		jScrollPane.repaint();
		jScrollPane.setVisible(true);
		this.add(jScrollPane);

		this.remove(findPanel);
		this.remove(sortPanel);
		this.remove(screenPanel);
		this.add(welcomePanel);

		this.repaint();

	}

	/** 欢迎Panel */
	private void setWelcome() {
		welcomePanel.setLayout(null);
		welcomePanel.setBackground(FrameSize.backColor);
		welcomePanel.setBounds(0, FrameSize.height / 12, FrameSize.width / 3,
				11 * FrameSize.height / 12);
		JLabel nba = new JLabel(new ImageIcon("image/nba.png"));
		nba.setBounds(FrameSize.width / 12, FrameSize.height / 8,
				FrameSize.width / 6, 200);
		welcomePanel.add(nba);
	}

	/** 点击筛选按钮 */
	private void screenPlayerClick() {
		this.remove(welcomePanel);
		this.remove(findPanel);
		this.remove(sortPanel);
		this.add(screenPanel);
		this.repaint();
	}

	/** 点击排序按钮 */
	private void sortPlayerClick() {
		this.remove(welcomePanel);
		this.remove(findPanel);
		this.remove(screenPanel);
		this.add(sortPanel);
		this.repaint();
	}

	/** 点击排序确认按钮 */
	private void sortPlayerConfirmClick() {

		jScrollPane.setVisible(false);

		SortType type = null;

		if (sortType == true) {
			type = SortType.ASEND;
		} else {
			type = SortType.DESEND;
		}

		PlayerSortBy playerSortBy = null;
		String sortBy = (String) sortPlayerBox.getSelectedItem();
		if (sortBy.equals("球员名称")) {
			playerSortBy = PlayerSortBy.name;
		} else if (sortBy.equals("所属球队")) {
			playerSortBy = PlayerSortBy.team;
		} else if (sortBy.equals("参赛场数")) {
			playerSortBy = PlayerSortBy.matchNo;
		} else if (sortBy.equals("先发场数")) {
			playerSortBy = PlayerSortBy.firstServiceNo;
		} else if (sortBy.equals("篮板数")) {
			playerSortBy = PlayerSortBy.rebs;
		} else if (sortBy.equals("助攻数")) {
			playerSortBy = PlayerSortBy.assistNo;
		} else if (sortBy.equals("在场时间")) {
			playerSortBy = PlayerSortBy.time;
		} else if (sortBy.equals("投篮命中率")) {
			playerSortBy = PlayerSortBy.hitRate;
		} else if (sortBy.equals("三分命中率")) {
			playerSortBy = PlayerSortBy.threeHitRate;
		} else if (sortBy.equals("罚球命中率")) {
			playerSortBy = PlayerSortBy.penaltyHitRate;
		} else if (sortBy.equals("进攻数")) {
			playerSortBy = PlayerSortBy.offendNo;
		} else if (sortBy.equals("防守数")) {
			playerSortBy = PlayerSortBy.defenceNo;
		} else if (sortBy.equals("抢断数")) {
			playerSortBy = PlayerSortBy.stealsNo;
		} else if (sortBy.equals("盖帽数")) {
			playerSortBy = PlayerSortBy.blockNo;
		} else if (sortBy.equals("失误数")) {
			playerSortBy = PlayerSortBy.mistakesNo;
		} else if (sortBy.equals("犯规数")) {
			playerSortBy = PlayerSortBy.foulsNo;
		} else if (sortBy.equals("得分")) {
			playerSortBy = PlayerSortBy.points;
		} else if (sortBy.equals("效率")) {
			playerSortBy = PlayerSortBy.efficiency;
		} else if (sortBy.equals("GmSc效率值")) {
			playerSortBy = PlayerSortBy.gmScEfficiency;
		} else if (sortBy.equals("真实命中率")) {
			playerSortBy = PlayerSortBy.trueHitRate;
		} else if (sortBy.equals("投篮效率")) {
			playerSortBy = PlayerSortBy.hitEfficiency;
		} else if (sortBy.equals("篮板率")) {
			playerSortBy = PlayerSortBy.rebEfficiency;
		} else if (sortBy.equals("进攻篮板率")) {
			playerSortBy = PlayerSortBy.offenseRebsEfficiency;
		} else if (sortBy.equals("防守篮板率")) {
			playerSortBy = PlayerSortBy.defenceRebsEfficiency;
		} else if (sortBy.equals("助攻率")) {
			playerSortBy = PlayerSortBy.assistEfficiency;
		} else if (sortBy.equals("抢断率")) {
			playerSortBy = PlayerSortBy.stealsEfficiency;
		} else if (sortBy.equals("盖帽率")) {
			playerSortBy = PlayerSortBy.blockEfficiency;
		} else if (sortBy.equals("失误率")) {
			playerSortBy = PlayerSortBy.mistakeEfficiency;
		} else if (sortBy.equals("使用率")) {
			playerSortBy = PlayerSortBy.useEfficiency;
		}
		PlayerMatchVO[] sortPlayer ;
		if(dataType.getSelectedItem().equals("赛季总数据")){
		sortPlayer = playerController.sortTotalPlayers(
					playerSortBy, type);
		}else{
		sortPlayer = playerController.sortAvePlayers(
				playerSortBy, type);
		}
		setTable(sortPlayer);
		jScrollPane.repaint();
		jScrollPane.setVisible(true);

		this.add(jScrollPane);
		this.repaint();
	}

	/** 点击查找按钮 */
	public void findPlayerClick(String playerInfo) {
		if (playerInfo.equals("")) {
			JOptionPane.showMessageDialog(this, "请输入查找球员的名字");
			return;
		} else if (playerController.findPlayer(playerInfo) == null) {
			JOptionPane.showMessageDialog(this, "未查到球员信息");
			return;
		}

//		String playerInfo = searchField.getText();
		showOne(playerInfo);

		jScrollPane.setVisible(false);
		PlayerMatchVO[] onePlayer = new PlayerMatchVO[1];
		onePlayer[0]=playerController.findPlayerMatchAve(playerInfo);
		setTable(onePlayer);
		jScrollPane.repaint();
		jScrollPane.setVisible(true);
		this.add(jScrollPane);

		this.repaint();
	}

	private void resizeTable(boolean bool, JScrollPane jsp, JTable table) {
		Dimension containerwidth = null;
		if (!bool) {
			// 初始化时，父容器大小为首选大小，实际大小为0
			containerwidth = jsp.getPreferredSize();
		} else {
			// 界面显示后，如果父容器大小改变，使用实际大小而不是首选大小
			containerwidth = jsp.getSize();
		}
		// 计算表格总体宽度 getTable().
		int allwidth = table.getIntercellSpacing().width;
		for (int j = 0; j < table.getColumnCount(); j++) {
			// 计算该列中最长的宽度
			int max = 0;
			for (int i = 0; i < table.getRowCount(); i++) {
				int width = table
						.getCellRenderer(i, j)
						.getTableCellRendererComponent(table,
								table.getValueAt(i, j), false, false, i, j)
						.getPreferredSize().width;
				if (width > max) {
					max = width;
				}
			}
			// 计算表头的宽度
			int headerwidth = table
					.getTableHeader()
					.getDefaultRenderer()
					.getTableCellRendererComponent(
							table,
							table.getColumnModel().getColumn(j).getIdentifier(),
							false, false, -1, j).getPreferredSize().width;
			// 列宽至少应为列头宽度
			max += headerwidth;
			// 设置列宽
			table.getColumnModel().getColumn(j).setPreferredWidth(max);
			// 给表格的整体宽度赋值，记得要加上单元格之间的线条宽度1个像素
			allwidth += max + table.getIntercellSpacing().width;
		}
		allwidth += table.getIntercellSpacing().width;
		// 如果表格实际宽度大小父容器的宽度，则需要我们手动适应；否则让表格自适应
		if (allwidth > containerwidth.width) {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		} else {
			table.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		}
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
