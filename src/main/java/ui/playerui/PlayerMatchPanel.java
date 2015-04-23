package ui.playerui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import ui.mainui.FrameSize;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import bl.matchbl.MatchController;

public class PlayerMatchPanel extends JPanel {

	MatchController matchController = new MatchController();
	String playerName;
	String teamName;

	public PlayerMatchPanel(String playerName, String teamName) {
		this.setLayout(null);
		this.setBounds(FrameSize.width / 3, FrameSize.height / 12,
				2 * FrameSize.width / 3, FrameSize.height * 7 / 8
						- FrameSize.height / 12);
		this.setBackground(FrameSize.backColor);
		this.playerName = playerName;
		this.teamName = teamName;

		setText();
		setRecentTable();
		setPastTable();
		this.repaint();
	}

	/** 设置界面提示文字 */
	void setText() {
		JLabel recent = new JLabel("近期比赛");
		JLabel past = new JLabel("过往查询");
		JLabel playername = new JLabel("球员");
		JLabel player = new JLabel();
		player.setText(playerName);

		playername.setBounds(10, 20, 50, 50);
		player.setBounds(80, 20, 300, 50);
		recent.setBounds(10, 100, 2 * FrameSize.width / 3 - 20, 50);
		past.setBounds(10, 253, 2 * FrameSize.width / 3 - 20, 50);

		recent.setOpaque(true);
		past.setOpaque(true);
		recent.setBackground(Color.black);
		past.setBackground(Color.black);
		recent.setForeground(Color.white);
		past.setForeground(Color.white);
		playername.setForeground(Color.white);
		player.setForeground(Color.white);

		this.add(player);
		this.add(playername);
		this.add(past);
		this.add(recent);

	}

	/** 近期比赛 */
	void setRecentTable() {
		Vector<String> columnsName = new Vector<String>();
		columnsName.add("日期");
		columnsName.add("对阵队伍");
		columnsName.add("比分");
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
		try {
			MatchesPO[] match = matchController.getRecentPlayerMatches(
					playerName, 5);
			Vector data = new Vector();
			for (int i = 0; i < match.length; i++) {
				Vector rowData = new Vector();
				MatchTeamPO teamPO;
				rowData.add(match[i].getDate());
				rowData.add(match[i].getTeam1().getName() + "-"
						+ match[i].getTeam2().getName());
				rowData.add(match[i].getTeam1().getTotalScores() + "-"
						+ match[i].getTeam2().getTotalScores());
				if (match[i].getTeam2().getName().equals(teamName)) {
					teamPO = match[i].getTeam2();
				} else {
					teamPO = match[i].getTeam1();
				}
				MatchPlayerPO[] playerPO = teamPO.getPlayers();
				for (int j = 0; j < playerPO.length; j++) {
					if (playerPO[j].getName().equals(playerName)) {
						rowData.add(playerPO[j].getTime());
						rowData.add(playerPO[j].getHitNo());
						rowData.add(playerPO[j].getHandNo());
						rowData.add(playerPO[j].getThreeHitNo());
						rowData.add(playerPO[j].getThreeHandNo());
						rowData.add(playerPO[j].getPenaltyHitNo());
						rowData.add(playerPO[j].getPenaltyHandNo());
						rowData.add(playerPO[j].getOffenseRebs());
						rowData.add(playerPO[j].getDefenceRebs());
						rowData.add(playerPO[j].getRebs());
						rowData.add(playerPO[j].getHelp());
						rowData.add(playerPO[j].getStealsNo());
						rowData.add(playerPO[j].getBlockNo());
						rowData.add(playerPO[j].getMistakesNo());
						rowData.add(playerPO[j].getFoulsNo());
						rowData.add(playerPO[j].getPoints());
					}
				}
				data.add(rowData);
			}

			DefaultTableModel table = new DefaultTableModel(data, columnsName);
			MyTable recenttable = new MyTable(table);
			JScrollPane jScrollPane = new JScrollPane(recenttable);

			jScrollPane.setBounds(10, 150, 2 * FrameSize.width / 3 - 20, 103);
			jScrollPane.setOpaque(false);
			jScrollPane.getViewport().setOpaque(false);
			 resizeTable(false,jScrollPane,recenttable);
			jScrollPane
					.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			jScrollPane
					.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

			recenttable.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						MyFrame.matchpanel.findMatchAccordingMatch(match,
								recenttable.getSelectedRow());
						MyFrame.card.show(MyFrame.mainpanel, "match");
						MyFrame.locationlable.setText("当前位置：比赛");
					}
				}

			});

			this.add(jScrollPane);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "未查到该球员的比赛", "查找失败",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	/** 过往查询 */
	void setPastTable() {
		Vector<String> columnsName = new Vector<String>();
		columnsName.add("日期");
		columnsName.add("对阵队伍");
		columnsName.add("比分");
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
		
		try{
		MatchesPO[] match = matchController.getPlayerMatches(playerName);
		Vector data = new Vector();
		for (int i = match.length-6; i >=0; i--) {
			Vector rowData = new Vector();
			MatchTeamPO teamPO;
			rowData.add(match[i].getDate());
			rowData.add(match[i].getTeam1().getName() + "-"
					+ match[i].getTeam2().getName());
			rowData.add(match[i].getTeam1().getTotalScores() + "-"
					+ match[i].getTeam2().getTotalScores());
			if (match[i].getTeam1().getName().equals(teamName)) {
				teamPO = match[i].getTeam1();
			} else {
				teamPO = match[i].getTeam2();
			}
			MatchPlayerPO[] playerPO = teamPO.getPlayers();
			for (int j = 0; j < playerPO.length; j++) {
				if (playerPO[j].getName().equals(playerName)) {
					rowData.add(playerPO[j].getTime());
					rowData.add(playerPO[j].getHitNo());
					rowData.add(playerPO[j].getHandNo());
					rowData.add(playerPO[j].getThreeHitNo());
					rowData.add(playerPO[j].getThreeHandNo());
					rowData.add(playerPO[j].getPenaltyHitNo());
					rowData.add(playerPO[j].getPenaltyHandNo());
					rowData.add(playerPO[j].getOffenseRebs());
					rowData.add(playerPO[j].getDefenceRebs());
					rowData.add(playerPO[j].getRebs());
					rowData.add(playerPO[j].getHelp());
					rowData.add(playerPO[j].getStealsNo());
					rowData.add(playerPO[j].getBlockNo());
					rowData.add(playerPO[j].getMistakesNo());
					rowData.add(playerPO[j].getFoulsNo());
					rowData.add(playerPO[j].getPoints());
				}
			}
			data.add(rowData);
		}

		DefaultTableModel table = new DefaultTableModel(data, columnsName);
		MyTable pasttable = new MyTable(table);
		JScrollPane pastjScrollPane = new JScrollPane(pasttable);
		pastjScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		pastjScrollPane.setBounds(10, 303, 2 * FrameSize.width / 3 - 20,
				FrameSize.height * 7 / 8 - FrameSize.height / 12 - 320);
		pastjScrollPane.setOpaque(false);
		pastjScrollPane.getViewport().setOpaque(false);
		 resizeTable(false,pastjScrollPane,pasttable);
		pastjScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		pastjScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		pasttable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {

					MyFrame.matchpanel.findMatchAccordingMatch(match,
							pasttable.getSelectedRow());
					MyFrame.card.show(MyFrame.mainpanel, "match");
					MyFrame.locationlable.setText("当前位置：比赛");
				}
			}

		});

		this.add(pastjScrollPane);
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "未查到该球员的比赛","查找失败",JOptionPane.ERROR_MESSAGE);
			return;
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

}
