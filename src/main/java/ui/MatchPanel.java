package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

	MatchController matchController = new MatchController();

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

	DefaultTableModel player1Table;
	MyTable myPlayer1Table;
	JScrollPane player1ScrollPane;

	DefaultTableModel player2Table;
	MyTable myPlayer2Table;
	JScrollPane player2sScrollPane;

	public MatchPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		setShowPanel();
		// setMatchTable();
		setHeader();
//		this.add(matchScrollPane);
		this.add(header);
		this.add(showPanel);
	}

	/**欢迎界面
	 *  private void setWelcome() { welcomePanel.setLayout(null);
	 * welcomePanel.setBackground(FrameSize.backColor);
	 * welcomePanel.setBounds(0, FrameSize.height / 16, FrameSize.width / 4,
	 * FrameSize.height * 13 / 16); JLabel nba = new JLabel(new
	 * ImageIcon("image/nba.png")); nba.setBounds(FrameSize.width / 12,
	 * FrameSize.height / 8, FrameSize.width / 6, 200); welcomePanel.add(nba); }
	 */

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

	private void setShowPanel() {
		showPanel.setLayout(null);
		showPanel.setBounds(FrameSize.width / 4, FrameSize.height / 12,
				FrameSize.width * 3 / 4, FrameSize.height * 19 / 24);
		showPanel.setBackground(FrameSize.backColor);

		int panelWidth = FrameSize.width * 3 / 4;
		int panelHeight = FrameSize.height * 19 / 24;

		JSVGCanvas team1 = new JSVGCanvas();
		JSVGCanvas team2 = new JSVGCanvas();
		team1.setBackground(Color.yellow);
		team2.setBackground(Color.red);
		team1.setBounds(panelWidth * 3 / 15, panelHeight / 20, panelWidth / 8,
				panelWidth / 8);
		team2.setBounds(panelWidth * 81 / 120, panelHeight / 20,
				panelWidth / 8, panelWidth / 8);
		showPanel.add(team1);
		showPanel.add(team2);

		UneditableTextField teamName1 = new UneditableTextField("ABC");
		UneditableTextField teamName2 = new UneditableTextField("DEF");
		teamName1.setBounds(panelWidth * 3 / 15 - 30, panelHeight * 2 / 20, 25,
				10);
		teamName2.setBounds(panelWidth * 81 / 120 + panelWidth / 8 + 5,
				panelHeight * 2 / 20, 25, 10);
		showPanel.add(teamName1);
		showPanel.add(teamName2);

		UneditableTextField score1 = new UneditableTextField("100");
		UneditableTextField score2 = new UneditableTextField("200");
		score1.setBounds(panelWidth * 3 / 15 - 30, panelHeight * 4 / 20, 25, 10);
		score2.setBounds(panelWidth * 81 / 120 + panelWidth / 8 + 5,
				panelHeight * 4 / 20, 25, 10);
		showPanel.add(score1);
		showPanel.add(score2);

		/**
		// 设置比分表格
		 setScoreTable();
		scoreScrollPane.setBounds(panelWidth * 39 / 120, panelHeight * 2 / 20,
				panelWidth * 42 / 120, 55);
		resizeTable(false, scoreScrollPane, myScoreTable);
		showPanel.add(scoreScrollPane);
		*/

		// 设置每个球队的球员表现的表格
		setPlayerTable();
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
			rowData.add(matches[i].getTeam1() + "-" + matches[i].getTeam2());
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
					System.out.println(myMatchTable.getSelectedRow());
					// showOne((String) mytable.getModel().getValueAt(
					// mytable.getSelectedRow(), 0));
				}
			}

		});
	}

	/** 设置比分表格 */
	private void setScoreTable(MatchTeamPO team1, MatchTeamPO team2) {

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
		scoreScrollPane = new JScrollPane(myScoreTable);
		// scoreScrollPane
		// .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// matchScrollPane
		// .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
	}

	/**设置球员表现的表格*/
	private void setPlayerTable(/**MatchPlayerPO player*/) {

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
		columnsName.add("投篮出手");
		columnsName.add("进攻");
		columnsName.add("防守");
		columnsName.add("篮板");
		columnsName.add("助攻");
		columnsName.add("抢断");
		columnsName.add("失误");
		columnsName.add("犯规");
		columnsName.add("得分");

		Vector data = new Vector();
		// while (true) {
		for (int i = 0; i < 10; i++) {
			Vector rowData = new Vector();
			rowData.add("ABC");
			rowData.add("100");
			rowData.add("200");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");
			rowData.add("100");

			data.add(rowData);
		}
		// }
		player1Table = new DefaultTableModel(data, columnsName);
		myPlayer1Table = new MyTable(player1Table);
		player1ScrollPane = new JScrollPane(myPlayer1Table);
		// scoreScrollPane
		// .setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// matchScrollPane
		// .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

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
}
