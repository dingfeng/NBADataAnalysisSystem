package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.batik.swing.JSVGCanvas;

import bl.matchbl.MatchController;
import bl.teambl.TeamController;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import ui.mainui.EditableTextField;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyTable;
import ui.mainui.UneditableTextField;
import vo.PlayerVO;

public class MatchPanel extends JPanel {
	
	MatchesPO[] matches ;

	MatchController matchController = new MatchController();
	TeamController teamController = new TeamController();

	JPanel welcomePanel = new JPanel();
	JPanel header = new JPanel();
	JPanel showPanel = new JPanel();

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
	
	UneditableTextField score1; 
	UneditableTextField score2;
	UneditableTextField teamName1; 
	UneditableTextField teamName2;
	JLabel teamImage1 = new JLabel();
	JLabel teamImage2 = new JLabel();
	
	public MatchPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		matches = matchController.getAllMatches();
		setMatchTable(matches);
		setHeader();
		setShowPanel(matches[0].getTeam1(),matches[0].getTeam2());
		this.add(matchScrollPane);
		this.add(header);
		this.add(showPanel);
	}

	/** 设置查找栏 */
	private void setHeader() {
		header.setLayout(null);
		header.setBounds(0, 0, FrameSize.width, FrameSize.height / 12);
		header.setBackground(FrameSize.backColor);

		timeBox = new MyComboBox(new String[] { "比赛时间", "df", "df", "df" });
		timeBox.setBounds(50, 10, 150, 35);
		header.add(timeBox);

		teamBox = new MyComboBox(new String[] { "队伍", "Ning", "Ning", "Ning" });
		teamBox.setBounds(230, 10, 150, 35);
		header.add(teamBox);

		playerBox = new MyComboBox(new String[] { "球员", "nn", "nn", "nn" });
		playerBox.setBounds(590, 10, 150, 35);
		header.add(playerBox);

		JButton yesButton = new JButton(new ImageIcon("image/yes.png"));
		yesButton.setBounds(770, 10, 30, 30);
		yesButton.setBackground(Color.white);
		// yesButton.addActionListener(e -> screenPlayerConfirmClick());
		header.add(yesButton);
	}

	/**设置每场比赛具体信息显示*/
	private void setShowPanel(MatchTeamPO team1,MatchTeamPO team2) {
		showPanel.setLayout(null);
		showPanel.setBounds(FrameSize.width / 4, FrameSize.height / 12,
				FrameSize.width * 3 / 4, FrameSize.height * 19 / 24);
		showPanel.setBackground(FrameSize.backColor);

		int panelWidth = FrameSize.width * 3 / 4;
		int panelHeight = FrameSize.height * 19 / 24;

	
		teamImage1.setIcon(scaleImage(new ImageIcon(teamController.getTeamData(team1.getName()).getImage()), panelWidth/8,panelWidth/8));
		teamImage2.setIcon(scaleImage(new ImageIcon(teamController.getTeamData(team2.getName()).getImage()), panelWidth/8,panelWidth/8));
		teamImage1.setBackground(FrameSize.buttonbackColor);
		teamImage2.setBackground(FrameSize.buttonbackColor);
		teamImage1.setBounds(panelWidth * 3 / 15, panelHeight / 20, panelWidth / 8,
				panelWidth / 8);
		teamImage2.setBounds(panelWidth * 81 / 120, panelHeight / 20,
				panelWidth / 8, panelWidth / 8);
		showPanel.add(teamImage1);
		showPanel.add(teamImage2);

		teamName1 = new UneditableTextField(team1.getName());
		teamName2 = new UneditableTextField(team2.getName());
		teamName1.setBounds(panelWidth * 3 / 15 - 30, panelHeight * 2 / 20, 25,
				10);
		teamName2.setBounds(panelWidth * 81 / 120 + panelWidth / 8 + 5,
				panelHeight * 2 / 20, 25, 10);
		showPanel.add(teamName1);
		showPanel.add(teamName2);

		score1 = new UneditableTextField(team1.getTotalScores());
		score2 = new UneditableTextField(team2.getTotalScores());
		score1.setBounds(panelWidth * 3 / 15 - 30, panelHeight * 4 / 20, 25, 10);
		score2.setBounds(panelWidth * 81 / 120 + panelWidth / 8 + 5,
				panelHeight * 4 / 20, 25, 10);
		showPanel.add(score1);
		showPanel.add(score2);

		// 设置比分表格
		setScoreTable(team1,team2);
		scoreScrollPane.setBounds(panelWidth * 39 / 120, panelHeight * 2 / 20,
				panelWidth * 42 / 120, 55);
		resizeTable(false, scoreScrollPane, myScoreTable);
		 scoreScrollPane.repaint();
		showPanel.add(scoreScrollPane);


		// 设置每个球队的球员表现的表格
		setPlayerTable(team1.getPlayers(),team2.getPlayers());
		player1ScrollPane.setBounds(5,(panelHeight*19/20-panelWidth/8)/2<((myPlayer1Table.getRowCount()+1)*myPlayer1Table.getRowHeight()+23)?(panelHeight/20+panelWidth/8+5):panelHeight/20+panelWidth/8+((panelHeight*19/20-panelWidth/8)/2-((myPlayer1Table.getRowCount()+1)*myPlayer1Table.getRowHeight()+23))/2,panelWidth-10,(panelHeight*19/20-panelWidth/8)/2<((myPlayer1Table.getRowCount()+1)*myPlayer1Table.getRowHeight()+23)?((panelHeight*19/20-panelWidth/8)/2-10):((myPlayer1Table.getRowCount()+1)*myPlayer1Table.getRowHeight()+23));
		showPanel.add(player1ScrollPane);
		player2ScrollPane.setBounds(5,(panelHeight*19/20-panelWidth/8)/2<((myPlayer2Table.getRowCount()+1)*myPlayer2Table.getRowHeight()+23)?(panelHeight/20+panelWidth/8+(panelHeight*19/20-panelWidth/8)/2+5):panelHeight/20+panelWidth/8+(panelHeight*19/20-panelWidth/8)/2+((panelHeight*19/20-panelWidth/8)/2-((myPlayer2Table.getRowCount()+1)*myPlayer2Table.getRowHeight()+23))/2,panelWidth-10,(panelHeight*19/20-panelWidth/8)/2<((myPlayer2Table.getRowCount()+1)*myPlayer2Table.getRowHeight()+23)?((panelHeight*19/20-panelWidth/8)/2-10):((myPlayer2Table.getRowCount()+1)*myPlayer2Table.getRowHeight()+23));
		showPanel.add(player2ScrollPane);
		showPanel.repaint();
		showPanel.validate();
		this.add(showPanel);
		this.repaint();
		this.validate();
	}

	/** 设置比赛表格 */
	private void setMatchTable(MatchesPO[] matches) {

		Vector columnsName = new Vector();
		columnsName.add("时间");
		columnsName.add("球队-球队");
		columnsName.add("比分");

		Vector data = new Vector();
		for (int i = 0; i < matches.length; i++) {
			Vector rowData = new Vector();
			rowData.add(matches[i].getDate());
			rowData.add(matches[i].getTeam1().getName() + "-" + matches[i].getTeam2().getName());
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
		resizeTable(false, matchScrollPane, myMatchTable);

		myMatchTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					showPanel.setVisible(false);
					showPanel.remove(scoreScrollPane);
					showPanel.remove(player1ScrollPane);
					showPanel.remove(player2ScrollPane);
					setShowPanel(matches[myMatchTable.getSelectedRow()].getTeam1(),matches[myMatchTable.getSelectedRow()].getTeam2());
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
		resizeTable(false,scoreScrollPane,myScoreTable);
//		scoreScrollPane.repaint();
//		scoreScrollPane.validate();
		scoreScrollPane.setVisible(false);
		showPanel.add(scoreScrollPane);
		showPanel.repaint();
		scoreScrollPane.setVisible(true);
		showPanel.setVisible(true);
		this.add(showPanel);
		this.repaint();
	}

	/**设置球员表现的表格*/
	private void setPlayerTable(MatchPlayerPO[] players1,MatchPlayerPO[] players2) {

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
		for (int i = 0; i <players1.length; i++) {
			Vector rowData = new Vector();
			rowData.add(players1[i].getName());
			rowData.add(players1[i].getLocation());
			rowData.add(players1[i].getTime());
			rowData.add(players1[i].getHitNo());
			rowData.add(players1[i].getHandNo());
			rowData.add(players1[i].getThreeHitNo());
			rowData.add(players1[i].getThreeHandNo());
			rowData.add(players1[i].getPenaltyHitNo());
			rowData.add(players1[i].getPenaltyHandNo());
			rowData.add(players1[i].getOffenseRebs());
			rowData.add(players1[i].getDefenceRebs());
			rowData.add(players1[i].getRebs());
			rowData.add(players1[i].getHelp());
			rowData.add(players1[i].getStealsNo());
			rowData.add(players1[i].getBlockNo());
			rowData.add(players1[i].getMistakesNo());
			rowData.add(players1[i].getFoulsNo());
			rowData.add(players1[i].getPoints());
			data1.add(rowData);
		}
		player1Table = new DefaultTableModel(data1, columnsName);
		myPlayer1Table = new MyTable(player1Table);
		myPlayer1Table.updateUI();
		player1ScrollPane = new JScrollPane(myPlayer1Table);
		player1ScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		player1ScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		resizeTable(false,player1ScrollPane,myPlayer1Table);
		player1ScrollPane.repaint();
		player1ScrollPane.validate();
		
		Vector data2 = new Vector();
		for (int i = 0; i <players2.length; i++) {
			Vector rowData = new Vector();
			rowData.add(players2[i].getName());
			rowData.add(players2[i].getLocation());
			rowData.add(players2[i].getTime());
			rowData.add(players2[i].getHitNo());
			rowData.add(players2[i].getHandNo());
			rowData.add(players2[i].getThreeHitNo());
			rowData.add(players2[i].getThreeHandNo());
			rowData.add(players2[i].getPenaltyHitNo());
			rowData.add(players2[i].getPenaltyHandNo());
			rowData.add(players2[i].getOffenseRebs());
			rowData.add(players2[i].getDefenceRebs());
			rowData.add(players2[i].getRebs());
			rowData.add(players2[i].getHelp());
			rowData.add(players2[i].getStealsNo());
			rowData.add(players2[i].getBlockNo());
			rowData.add(players2[i].getMistakesNo());
			rowData.add(players2[i].getFoulsNo());
			rowData.add(players2[i].getPoints());
			data2.add(rowData);
		}
		player2Table = new DefaultTableModel(data2, columnsName);
		myPlayer2Table = new MyTable(player2Table);
		player2ScrollPane = new JScrollPane(myPlayer2Table);
		player2ScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		player2ScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		resizeTable(false,player2ScrollPane,myPlayer2Table);
		player2ScrollPane.repaint();
		player2ScrollPane.validate();
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
		image = image.getScaledInstance(iconWidth, iconHeight, Image.SCALE_DEFAULT); 

		return new ImageIcon(image); 
		}
}
