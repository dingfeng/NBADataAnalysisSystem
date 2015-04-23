package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import ui.mainui.DateChooseButton;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import ui.mainui.UneditableTextField;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.SortType;
import vo.TeamMatchVO;
import vo.TeamSortBy;
import bl.matchbl.MatchController;
import bl.matchbl.TeamPlayer;
import bl.playerbl.PlayerController;
import bl.teambl.Team;
import bl.teambl.TeamController;

public class MatchPanel extends JPanel {

	public MatchesPO[] matches;

	MatchController matchController = new MatchController();
	TeamController teamController = new TeamController();
	PlayerController playerController = new PlayerController();

	JPanel welcomePanel = new JPanel();
	JPanel header = new JPanel();
	JPanel showPanel = new JPanel();

	DateChooseButton dateButton1;
	DateChooseButton dateButton2;
	JComboBox<String> timeBox;
	JComboBox<String> teamBox;
	JComboBox<String> playerBox;

	DefaultTableModel matchTable;
	JScrollPane matchScrollPane;
	MyTable myMatchTable;

	DefaultTableModel scoreTable;
	MyTable myScoreTable;
	JScrollPane scoreScrollPane;

	DefaultTableModel player1Table = new DefaultTableModel();
	MyTable myPlayer1Table = new MyTable(player1Table);
	JScrollPane player1ScrollPane = new JScrollPane();

	DefaultTableModel player2Table;
	MyTable myPlayer2Table;
	JScrollPane player2ScrollPane;

	UneditableTextField score1 = new UneditableTextField();
	UneditableTextField score2 = new UneditableTextField();
	UneditableTextField teamName1 = new UneditableTextField();
	UneditableTextField teamName2 = new UneditableTextField();
	JLabel teamImage1 = new JLabel();
	JLabel teamImage2 = new JLabel();

	public MatchPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		matches = matchController.getAllMatches();
		setMatchTable(matches, 0);
		setHeader();
		this.add(matchScrollPane);
		this.add(header);
		this.add(showPanel);
	}

	/** 设置查找栏 */
	private void setHeader() {
		header.setLayout(null);
		header.setBounds(0, 0, FrameSize.width, FrameSize.height / 12);
		header.setBackground(FrameSize.backColor);
        TeamPlayer teamPlayer = new TeamPlayer();
		 String[] team = Team.teamnames; 
		ArrayList<String> teamName = new ArrayList<String>();
		for (int i = 0; i < team.length; i++) {
			teamName.add(team[i]);
		}

//		PlayerMatchVO[] player = playerController.sortTotalPlayers(
//				PlayerSortBy.name, SortType.ASEND);
		ArrayList<String> playerName = teamPlayer.getAllPlayer();
//		for (int i = 0; i < player.length; i++) {
//			playerName.add(player[i].getName());
//		}

		dateButton1 = new DateChooseButton();
		dateButton1.setBounds(50, 10, 200, 35);
		dateButton1.setEnd(this);
		header.add(dateButton1);

		dateButton2 = new DateChooseButton();
		dateButton2.setBounds(300, 10, 200, 35);
		dateButton2.setEnd(this);
		header.add(dateButton2);
        
		teamBox = new MyComboBox("球队", teamName);
		teamBox.setBounds(FrameSize.width - 450, 10, 100, 35);
		header.add(teamBox);
		teamBox.addActionListener(e -> setPlayerBox());

		playerBox = new MyComboBox("球员", playerName);
		playerBox.setBounds(FrameSize.width - 300, 10, 160, 35);
		playerBox.addActionListener(e -> findMatchConfirmClick());
		header.add(playerBox);

	}

	/** 查找比赛 */
	public void findMatchConfirmClick() {
		Date date1 = dateButton1.getDate();
		Date date2 = dateButton2.getDate();
		if (date1.compareTo(date2) > 0) {
			JOptionPane.showMessageDialog(this, "起始时间应不晚于终止时间");
			return;
		}
		String team = teamBox.getSelectedItem().toString();
		if (team.equals("球队")) {
			team = null;
		}
		String player = playerBox.getSelectedItem().toString();
		if (player.equals("球员")) {
			player = null;
		}
		if (team == null && player == null) {
			findMatchAccordingDate(date1, date2);
		} else {
				matches = matchController.getTime_TeamMatches(date1, date2,
						team, player);
				if(matches.length==0){
					JOptionPane.showMessageDialog(null, "未查到该段时间内的比赛", "查找失败",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				this.remove(matchScrollPane);
				showPanel.remove(scoreScrollPane);
				showPanel.remove(player1ScrollPane);
				showPanel.remove(player2ScrollPane);
				this.remove(showPanel);
				matchScrollPane.setVisible(false);
				setMatchTable(matches, 0);
				matchScrollPane.setVisible(true);
				showPanel.add(scoreScrollPane);
				showPanel.add(player1ScrollPane);
				showPanel.add(player2ScrollPane);
				this.add(showPanel);
				this.add(matchScrollPane);
				this.repaint();
			
		}
	}

	/** 按时间查找比赛 */
	public void findMatchAccordingDate(Date date1, Date date2) {

			matches = matchController.getTimeMatches(date1, date2);
             if (matches == null)
			{
            	 JOptionPane.showMessageDialog(null, "未查到该段时间内的比赛", "查找失败",
					JOptionPane.ERROR_MESSAGE);
            	 return;
			}
			this.remove(matchScrollPane);
			showPanel.remove(scoreScrollPane);
			showPanel.remove(player1ScrollPane);
			showPanel.remove(player2ScrollPane);
			this.remove(showPanel);
			matchScrollPane.setVisible(false);
			setMatchTable(matches, 0);
			matchScrollPane.setVisible(true);
			showPanel.add(scoreScrollPane);
			showPanel.add(player1ScrollPane);
			showPanel.add(player2ScrollPane);
			this.add(showPanel);
			this.add(matchScrollPane);
			this.repaint();
		
	}

	/** 按球队查找比赛 */
	public void findMatchAccordingTeam(String team) {
		this.remove(matchScrollPane);
		showPanel.remove(scoreScrollPane);
		showPanel.remove(player1ScrollPane);
		showPanel.remove(player2ScrollPane);
		this.remove(showPanel);
		matches = matchController.getTeamMatches(team);
		matchScrollPane.setVisible(false);
		setMatchTable(matches, 0);
		matchScrollPane.setVisible(true);
		showPanel.add(scoreScrollPane);
		showPanel.add(player1ScrollPane);
		showPanel.add(player2ScrollPane);
		this.add(showPanel);
		this.add(matchScrollPane);
		this.repaint();
	}

	/** 按球员查找比赛 */
	public void findMatchAccordingPlayer(String player) {
		this.remove(matchScrollPane);
		showPanel.remove(scoreScrollPane);
		showPanel.remove(player1ScrollPane);
		showPanel.remove(player2ScrollPane);
		this.remove(showPanel);
		matches = matchController.getPlayerMatches(player);
		matchScrollPane.setVisible(false);
		setMatchTable(matches, 0);
		matchScrollPane.setVisible(true);
		showPanel.add(scoreScrollPane);
		showPanel.add(player1ScrollPane);
		showPanel.add(player2ScrollPane);
		this.add(showPanel);
		this.add(matchScrollPane);
		this.repaint();
	}

	/** 根据比赛设置比赛表格,给teamPanel和playerPanel转换使用 */
	public void findMatchAccordingMatch(MatchesPO[] match, int rowNum) {
		this.remove(matchScrollPane);
		showPanel.remove(scoreScrollPane);
		showPanel.remove(player1ScrollPane);
		showPanel.remove(player2ScrollPane);
		this.remove(showPanel);
		matches = match;
		matchScrollPane.setVisible(false);
		setMatchTable(matches, rowNum);
		matchScrollPane.setVisible(true);
		showPanel.add(scoreScrollPane);
		showPanel.add(player1ScrollPane);
		showPanel.add(player2ScrollPane);
		this.add(showPanel);
		this.add(matchScrollPane);
		this.repaint();
	}

	/** 根据球队选择球员 */
	private void setPlayerBox() {
		if (!teamBox.getSelectedItem().equals("球队")) {
			playerBox.setVisible(false);
			header.remove(playerBox);
			String team = (String) teamBox.getSelectedItem();
			String player[] = teamController.getPlayers(team);
			playerBox = new MyComboBox("球员", player);
			playerBox.repaint();
			header.add(playerBox);
			playerBox.setBounds(FrameSize.width - 300, 10, 160, 35);
			playerBox.addActionListener(e -> findMatchConfirmClick());
			playerBox.setVisible(true);
			header.repaint();
			this.repaint();
			findMatchConfirmClick();
		}
	}

	/** 设置每场比赛具体信息显示 */
	public void setShowPanel(MatchTeamPO team1, MatchTeamPO team2) {
		showPanel.setLayout(null);
		showPanel.setBounds(FrameSize.width / 4, FrameSize.height / 12,
				FrameSize.width * 3 / 4, FrameSize.height * 19 / 24);
		showPanel.setBackground(FrameSize.backColor);

		int panelWidth = FrameSize.width * 3 / 4;
		int panelHeight = FrameSize.height * 19 / 24;

		teamImage1.setIcon(scaleImage(
				new ImageIcon(teamController.getTeamData(team1.getName())
						.getImage()), panelWidth / 8, panelWidth / 8));
		teamImage2.setIcon(scaleImage(
				new ImageIcon(teamController.getTeamData(team2.getName())
						.getImage()), panelWidth / 8, panelWidth / 8));
		teamImage1.setBackground(FrameSize.buttonbackColor);
		teamImage2.setBackground(FrameSize.buttonbackColor);
		teamImage1.setBounds(panelWidth * 3 / 15, panelHeight / 20,
				panelWidth / 8, panelWidth / 8);
		teamImage2.setBounds(panelWidth * 81 / 120, panelHeight / 20,
				panelWidth / 8, panelWidth / 8);
		showPanel.add(teamImage1);
		showPanel.add(teamImage2);
		teamImage1.addMouseListener((new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				MyFrame.teampanel.findClick(team1.getName());
				MyFrame.card.show(MyFrame.mainpanel, "team");
				MyFrame.locationlable.setText("当前位置：球队");
			}
		}));
		teamImage2.addMouseListener((new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				MyFrame.teampanel.findClick(team2.getName());
				MyFrame.card.show(MyFrame.mainpanel, "team");
				MyFrame.locationlable.setText("当前位置：球队");
			}
		}));

		teamName1.setText(team1.getName());
		teamName2.setText(team2.getName());
		teamName1.setBounds(panelWidth * 3 / 15 - 30, panelHeight * 2 / 20, 25,
				10);
		teamName2.setBounds(panelWidth * 81 / 120 + panelWidth / 8 + 5,
				panelHeight * 2 / 20, 25, 10);
		showPanel.add(teamName1);
		showPanel.add(teamName2);

		// score1 = new UneditableTextField(team1.getTotalScores());
		score1.setText(String.valueOf(team1.getTotalScores()));
		// score2 = new UneditableTextField(team2.getTotalScores());
		score2.setText(String.valueOf(team2.getTotalScores()));
		score1.setBounds(panelWidth * 3 / 15 - 30, panelHeight * 4 / 20, 25, 10);
		score2.setBounds(panelWidth * 81 / 120 + panelWidth / 8 + 5,
				panelHeight * 4 / 20, 25, 10);
		showPanel.add(score1);
		showPanel.add(score2);

		// 设置比分表格
		setScoreTable(team1, team2);
		myScoreTable.setRowHeight(16);
		scoreScrollPane.setBounds(panelWidth * 39 / 120, panelHeight * 2 / 20,
				panelWidth * 42 / 120, 55);
		resizeTable(false, scoreScrollPane, myScoreTable);
		scoreScrollPane.setVisible(false);
		scoreScrollPane.repaint();
		scoreScrollPane.setVisible(true);
		showPanel.add(scoreScrollPane);

		// 设置每个球队的球员表现的表格
		setPlayerTable(team1.getPlayers(), team2.getPlayers());
		player1ScrollPane
				.setBounds(
						5,
						(panelHeight * 19 / 20 - panelWidth / 8) / 2 < ((myPlayer1Table
								.getRowCount() + 1)
								* myPlayer1Table.getRowHeight() + 23) ? (panelHeight
								/ 20 + panelWidth / 8 + 5)
								: panelHeight
										/ 20
										+ panelWidth
										/ 8
										+ ((panelHeight * 19 / 20 - panelWidth / 8) / 2 - ((myPlayer1Table
												.getRowCount() + 1)
												* myPlayer1Table.getRowHeight() + 23))
										/ 2,
						panelWidth - 10,
						(panelHeight * 19 / 20 - panelWidth / 8) / 2 < ((myPlayer1Table
								.getRowCount() + 1)
								* myPlayer1Table.getRowHeight() + 23) ? ((panelHeight * 19 / 20 - panelWidth / 8) / 2 - 10)
								: ((myPlayer1Table.getRowCount() + 1)
										* myPlayer1Table.getRowHeight() + 23));
		showPanel.add(player1ScrollPane);
		player2ScrollPane
				.setBounds(
						5,
						(panelHeight * 19 / 20 - panelWidth / 8) / 2 < ((myPlayer2Table
								.getRowCount() + 1)
								* myPlayer2Table.getRowHeight() + 23) ? (panelHeight
								/ 20
								+ panelWidth
								/ 8
								+ (panelHeight * 19 / 20 - panelWidth / 8) / 2 + 5)
								: panelHeight
										/ 20
										+ panelWidth
										/ 8
										+ (panelHeight * 19 / 20 - panelWidth / 8)
										/ 2
										+ ((panelHeight * 19 / 20 - panelWidth / 8) / 2 - ((myPlayer2Table
												.getRowCount() + 1)
												* myPlayer2Table.getRowHeight() + 23))
										/ 2,
						panelWidth - 10,
						(panelHeight * 19 / 20 - panelWidth / 8) / 2 < ((myPlayer2Table
								.getRowCount() + 1)
								* myPlayer2Table.getRowHeight() + 23) ? ((panelHeight * 19 / 20 - panelWidth / 8) / 2 - 10)
								: ((myPlayer2Table.getRowCount() + 1)
										* myPlayer2Table.getRowHeight() + 23));
		showPanel.add(player2ScrollPane);
		showPanel.repaint();
		showPanel.validate();
		this.add(showPanel);
		this.repaint();
		this.validate();
	}

	/** 设置比赛表格 */
	public void setMatchTable(MatchesPO[] matches, int rowNum) {
		// System.out.print(matches[0].getDate());
		Vector columnsName = new Vector();
		columnsName.add("时间");
		columnsName.add("球队-球队");
		columnsName.add("比分");

		Vector data = new Vector();
		for (int i = matches.length - 1; i >= 0; i--) {
			Vector rowData = new Vector();
			rowData.add(matches[i].getDate());
			rowData.add(matches[i].getTeam1().getName() + "-"
					+ matches[i].getTeam2().getName());
			rowData.add(matches[i].getTeam1().getTotalScores() + ":"
					+ matches[i].getTeam2().getTotalScores());
			data.add(rowData);
		}
		matchTable = new DefaultTableModel(data, columnsName);
		myMatchTable = new MyTable(matchTable);
		matchScrollPane = new JScrollPane(myMatchTable);
		matchScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// matchScrollPane
		// .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		matchScrollPane.setBounds(0, FrameSize.height / 12,
				FrameSize.width / 4, FrameSize.height * 19 / 24);
		matchScrollPane.setBackground(FrameSize.backColor);
		matchScrollPane.getViewport().setOpaque(false);
		resizeTable(false, matchScrollPane, myMatchTable);

		setShowPanel(matches[rowNum].getTeam1(), matches[rowNum].getTeam2());

		myMatchTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					showPanel.setVisible(false);
					showPanel.remove(scoreScrollPane);
					showPanel.remove(player1ScrollPane);
					showPanel.remove(player2ScrollPane);
					setShowPanel(
							matches[myMatchTable.getSelectedRow()].getTeam1(),
							matches[myMatchTable.getSelectedRow()].getTeam2());
					showPanel.add(scoreScrollPane);
					showPanel.add(player1ScrollPane);
					showPanel.add(player2ScrollPane);
					showPanel.repaint();
					showPanel.validate();
					showPanel.setVisible(true);
				}

			}

		});
	}

	/** 设置比分表格 */
	private void setScoreTable(MatchTeamPO team1, MatchTeamPO team2) {

		showPanel.setVisible(false);
		Vector columnsName = new Vector();
		columnsName.add("球队");
		columnsName.add("1");
		columnsName.add("2");
		columnsName.add("3");
		columnsName.add("4");
		for (int i = 4; i < team1.getScores().length; i++) {
			columnsName.add("+");
		}

		Vector data = new Vector();
		Vector rowData1 = new Vector();
		rowData1.add(team1.getName());
		for (int i = 0; i < team1.getScores().length; i++) {
			rowData1.add(team1.getScores()[i]);
		}
		data.add(rowData1);

		Vector rowData2 = new Vector();
		rowData2.add(team2.getName());
		for (int i = 0; i < team2.getScores().length; i++) {
			rowData2.add(team2.getScores()[i]);
		}
		data.add(rowData2);

		scoreTable = new DefaultTableModel(data, columnsName);
		myScoreTable = new MyTable(scoreTable);
		myScoreTable.updateUI();
		scoreScrollPane = new JScrollPane(myScoreTable);
		// scoreScrollPane
		// .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// matchScrollPane
		// .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scoreScrollPane.setBackground(FrameSize.backColor);
		scoreScrollPane.getViewport().setOpaque(false);
		resizeTable(false, scoreScrollPane, myScoreTable);
		// scoreScrollPane.repaint();
		// scoreScrollPane.validate();
		scoreScrollPane.setVisible(false);
		showPanel.add(scoreScrollPane);
		showPanel.repaint();
		scoreScrollPane.setVisible(true);
		showPanel.setVisible(true);
		this.add(showPanel);
		this.repaint();
	}

	/** 设置球员表现的表格 */
	private void setPlayerTable(MatchPlayerPO[] players1,
			MatchPlayerPO[] players2) {

		Vector columnsName = new Vector();
		columnsName.add("球员");
		columnsName.add("位置");
		columnsName.add("在场时间");
		columnsName.add("命中");
		columnsName.add("出手");
		columnsName.add("三分命中");
		columnsName.add("三分出手");
		columnsName.add("罚球命中");
		columnsName.add("罚球出手");
		columnsName.add("进攻");
		columnsName.add("防守");
		columnsName.add("篮板");
		columnsName.add("助攻");
		columnsName.add("抢断");
		columnsName.add("盖帽");
		columnsName.add("失误");
		columnsName.add("犯规");
		columnsName.add("得分");

		Vector data1 = new Vector();
		for (int i = 0; i < players1.length; i++) {
			Vector rowData = new Vector();
			rowData.add(players1[i].getName());
			rowData.add(players1[i].getLocation());
			rowData.add(FrameSize.roundForNumber(players1[i].getTime()));
			rowData.add(FrameSize.roundForNumber(players1[i].getHitNo()));
			rowData.add(FrameSize.roundForNumber(players1[i].getHandNo()));
			rowData.add(FrameSize.roundForNumber(players1[i].getThreeHitNo()));
			rowData.add(FrameSize.roundForNumber(players1[i].getThreeHandNo()));
			rowData.add(FrameSize.roundForNumber(players1[i].getPenaltyHitNo()));
			rowData.add(FrameSize.roundForNumber(players1[i].getPenaltyHandNo()));
			rowData.add(FrameSize.roundForNumber(players1[i].getOffenseRebs()));
			rowData.add(FrameSize.roundForNumber(players1[i].getDefenceRebs()));
			rowData.add(FrameSize.roundForNumber(players1[i].getRebs()));
			rowData.add(FrameSize.roundForNumber(players1[i].getHelp()));
			rowData.add(FrameSize.roundForNumber(players1[i].getStealsNo()));
			rowData.add(FrameSize.roundForNumber(players1[i].getBlockNo()));
			rowData.add(FrameSize.roundForNumber(players1[i].getMistakesNo()));
			rowData.add(FrameSize.roundForNumber(FrameSize.roundForNumber(players1[i].getFoulsNo())));
			rowData.add(FrameSize.roundForNumber(players1[i].getPoints()));
			data1.add(rowData);
		}
		player1Table = new DefaultTableModel(data1, columnsName);
		myPlayer1Table = new MyTable(player1Table);
		myPlayer1Table.setRowSorter(new TableRowSorter<TableModel>(player1Table));
		myPlayer1Table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					MyFrame.playerpanel.findPlayerClick((String) myPlayer1Table
							.getModel().getValueAt(
									myPlayer1Table.getSelectedRow(), 0));
					MyFrame.card.show(MyFrame.mainpanel, "player");
					MyFrame.locationlable.setText("当前位置：球员");
				}
			}

		});
		player1ScrollPane = new JScrollPane(myPlayer1Table);
		player1ScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		player1ScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		resizeTable(false, player1ScrollPane, myPlayer1Table);
		resizeTable(true, player1ScrollPane, myPlayer1Table);
		player1ScrollPane.repaint();
		player1ScrollPane.validate();

		Vector data2 = new Vector();
		for (int i = 0; i < players2.length; i++) {
			Vector rowData = new Vector();
			rowData.add(players2[i].getName());
			rowData.add(players2[i].getLocation());
			rowData.add(FrameSize.roundForNumber(players2[i].getTime()));
			rowData.add(FrameSize.roundForNumber(players2[i].getHitNo()));
			rowData.add(FrameSize.roundForNumber(players2[i].getHandNo()));
			rowData.add(FrameSize.roundForNumber(players2[i].getThreeHitNo()));
			rowData.add(FrameSize.roundForNumber(players2[i].getThreeHandNo()));
			rowData.add(FrameSize.roundForNumber(players2[i].getPenaltyHitNo()));
			rowData.add(FrameSize.roundForNumber(players2[i].getPenaltyHandNo()));
			rowData.add(FrameSize.roundForNumber(players2[i].getOffenseRebs()));
			rowData.add(FrameSize.roundForNumber(players2[i].getDefenceRebs()));
			rowData.add(FrameSize.roundForNumber(players2[i].getRebs()));
			rowData.add(FrameSize.roundForNumber(players2[i].getHelp()));
			rowData.add(FrameSize.roundForNumber(players2[i].getStealsNo()));
			rowData.add(FrameSize.roundForNumber(players2[i].getBlockNo()));
			rowData.add(FrameSize.roundForNumber(players2[i].getMistakesNo()));
			rowData.add(FrameSize.roundForNumber(players2[i].getFoulsNo()));
			rowData.add(FrameSize.roundForNumber(players2[i].getPoints()));
			data2.add(rowData);
		}
		player2Table = new DefaultTableModel(data2, columnsName);
		myPlayer2Table = new MyTable(player2Table);
		myPlayer2Table.setRowSorter(new TableRowSorter<TableModel>(player2Table));
		myPlayer2Table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					MyFrame.card.show(MyFrame.mainpanel, "player");
					MyFrame.locationlable.setText("当前位置：球员");
					MyFrame.playerpanel.findPlayerClick((String) myPlayer2Table
							.getModel().getValueAt(
									myPlayer2Table.getSelectedRow(), 0));
				}
			}

		});
		player2ScrollPane = new JScrollPane(myPlayer2Table);
		player2ScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		player2ScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		resizeTable(false, player2ScrollPane, myPlayer2Table);
		resizeTable(true, player2ScrollPane, myPlayer2Table);
		player2ScrollPane.repaint();
		player2ScrollPane.validate();
		
		TableRowSorter rowSorter1 = (TableRowSorter) myPlayer1Table.getRowSorter();
		Comparator<Number> numberComparator1 = new Comparator<Number>() {
			@Override
			public int compare(Number o1, Number o2) {
				if (o1 == null) {
					return -1;
				}
				if (o2 == null) {
					return 1;
				}
				if (o1.doubleValue() < o2.doubleValue()) {
					return -1;
				}
				if (o1.doubleValue() > o2.doubleValue()) {
					return 1;
				}
				return 0;
			}
		};
		for (int col = 2; col < player1Table.getColumnCount(); col++) {
			rowSorter1.setComparator(col, numberComparator1);
		}
		
		TableRowSorter rowSorter2 = (TableRowSorter) myPlayer2Table.getRowSorter();
		Comparator<Number> numberComparator2 = new Comparator<Number>() {
			@Override
			public int compare(Number o1, Number o2) {
				if (o1 == null) {
					return -1;
				}
				if (o2 == null) {
					return 1;
				}
				if (o1.doubleValue() < o2.doubleValue()) {
					return -1;
				}
				if (o1.doubleValue() > o2.doubleValue()) {
					return 1;
				}
				return 0;
			}
		};
		for (int col = 2; col < player1Table.getColumnCount(); col++) {
			rowSorter2.setComparator(col, numberComparator2);
		}
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
