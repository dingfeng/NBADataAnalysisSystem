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

import ui.mainui.EditableTextField;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyTable;
import vo.PlayerVO;

public class MatchPanel extends JPanel {

	JPanel welcomePanel = new JPanel();
	JPanel header = new JPanel();
	JPanel showPanel = new JPanel();

	JComboBox<String> timeBox;
	JComboBox<String> teamBox;
	JComboBox<String> playerBox;

	DefaultTableModel matchTable;
	JScrollPane matchScrollPane;
	MyTable myMatchTable;

	public MatchPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height*7/8);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		setMatchTable();
		setHeader();
		this.add(matchScrollPane);
		this.add(header);
	}

	/** 欢迎界面 */
	private void setWelcome() {
		welcomePanel.setLayout(null);
		welcomePanel.setBackground(FrameSize.backColor);
		welcomePanel.setBounds(0, FrameSize.height / 16, FrameSize.width / 3,485);
		JLabel nba = new JLabel(new ImageIcon("image/nba.png"));
		nba.setBounds(FrameSize.width / 12, FrameSize.height / 8,
				FrameSize.width / 6, 200);
		welcomePanel.add(nba);
	}

	/** */
	private void setHeader() {
		header.setLayout(null);
		header.setBounds(0, 0, FrameSize.width, FrameSize.height/ 16);
		header.setBackground(FrameSize.backColor);

		timeBox = new MyComboBox(new String[] { "比赛时间", "df", "df", "df" });
		timeBox.setBounds(50, 10, 150, 35);
		header.add(timeBox);

		teamBox = new MyComboBox(
				new String[] { "队伍", "Ning", "Ning", "Ning" });
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

	/** 设置表格 */
	private void setMatchTable() {

		Vector columnsName = new Vector();
		columnsName.add("时间");
		columnsName.add("球队1");
		columnsName.add("球队2");

		Vector data = new Vector();
		// while (true) {
		for(int i=0;i<100;i++){
		Vector rowData = new Vector();
		rowData.add("df");
		rowData.add("nn");
		rowData.add("Ning");
		data.add(rowData);
		}
		// }
		matchTable = new DefaultTableModel(data, columnsName);
		myMatchTable = new MyTable(matchTable);
		matchScrollPane = new JScrollPane(myMatchTable);
		matchScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// matchScrollPane
		// .setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		matchScrollPane.setBounds(0, FrameSize.height / 16,
				FrameSize.width / 3, FrameSize.height*13/16);
		resizeTable(false, matchScrollPane, myMatchTable);
		this.add(matchScrollPane);

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
