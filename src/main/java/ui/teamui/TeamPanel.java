package ui.teamui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

import org.apache.batik.swing.JSVGCanvas;

import po.TeamPO;
import ui.mainui.EditableTextField;
import ui.mainui.FrameSize;
import ui.mainui.MyComboBox;
import ui.mainui.MyTable;
import ui.mainui.UneditableTextField;
import vo.SortType;
import vo.TeamMatchVO;
import vo.TeamSortBy;
import bl.teambl.TeamController;

public class TeamPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel header = new JPanel();
	DefaultTableModel table;
	JScrollPane jScrollPane;
	
	JTextField searchField;
	JPanel sort = new JPanel();
	JPanel find = new JPanel();
	JPanel welcome = new JPanel();
	MyTable mytable;
	JComboBox<String> box;
	JComboBox<String> dataType;
	boolean sorttype;
	JSVGCanvas svgCanvas = new JSVGCanvas();
	JTextField nameresult = new UneditableTextField();// 队伍名称
	JTextField nameAbridgeresult = new UneditableTextField();// 名称缩写
	JTextField addressresult = new UneditableTextField();// 所在地
	JTextField matchArearesult = new UneditableTextField();// 赛区
	JTextField playerArearesult = new UneditableTextField();// 分区
	JTextField manageresult = new UneditableTextField();// 主场
	JTextField foundYearresult = new UneditableTextField();// 建立时间
	JButton match;
	
	TeamController tc = new TeamController();

	public TeamPanel() {
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
		this.setOpaque(false);
		new Thread() {
			public void run() {
				setTable(tc.getSortedTotalTeams(TeamSortBy.name, SortType.ASEND));
			}
		}.start();

		setSort();
		setHeader();
		setFind();
		setWelcome();
		this.add(welcome);
		this.add(header);
		this.repaint();
	}

	/** 设置表格 */
	void setTable(TeamMatchVO[] team) {
		Vector<String> columnsName = new Vector<String>();
		columnsName.add("球队全名");
		columnsName.add("比赛场数");
		columnsName.add("投篮命中数");
		columnsName.add("投篮出手次数");
		columnsName.add("三分命中数");
		columnsName.add("三分出手数");
		columnsName.add("罚球命中数");
		columnsName.add("罚球出手数");
		columnsName.add("进攻篮板数");
		columnsName.add("防守篮板数");
		columnsName.add("篮板数");
		columnsName.add("助攻数");
		columnsName.add("抢断数");
		columnsName.add("盖帽数");
		columnsName.add("失误数");
		columnsName.add("犯规数");
		columnsName.add("比赛得分");
		columnsName.add("投篮命中率(%)");
		columnsName.add("三分命中率(%)");
		columnsName.add("罚球命中率(%)");
		columnsName.add("胜率(%)");
		columnsName.add("进攻回合");
		columnsName.add("进攻效率");
		columnsName.add("防守效率");
		columnsName.add("进攻篮板效率");
		columnsName.add("防守篮板效率");
		columnsName.add("抢断效率");
		columnsName.add("助攻率");

		// allteam = tc.getAllTeams();
		Vector rowimage = new Vector();
		for(int i=0;i<team.length;i++) {
			TeamMatchVO str = team[i];
			Vector data = new Vector();
			data.add(str.getName());
			data.add(str.getMatchNo());
			data.add(str.getHitNo());
			data.add(str.getHandNo());
			data.add(str.getThreeHitNo());
			data.add(str.getThreeHandNo());
			data.add(str.getPenaltyHitNo());
			data.add(str.getPenaltyHandNo());
			data.add(str.getOffenseRebs());
			data.add(str.getDefenceRebs());
			data.add(str.getRebs());
			data.add(str.getAssistNo());
			data.add(str.getStealsNo());
			data.add(str.getBlockNo());
			data.add(str.getMistakesNo());
			data.add(str.getFoulsNo());
			data.add(str.getPoints());
			data.add(String.format("%.3f", str.getHitRate() * 100));
			data.add(String.format("%.3f", str.getThreeHitRate() * 100));
			data.add(String.format("%.3f", str.getPenaltyHitRate() * 100));
			data.add(String.format("%.3f", str.getWinRate() * 100));
			data.add(String.format("%.3f", str.getOffenseRound()));
			data.add(String.format("%.3f", str.getOffenseEfficiency()));
			data.add(String.format("%.3f", str.getDefenceEfficiency()));
			data.add(String.format("%.3f", str.getoRebsEfficiency()));
			data.add(String.format("%.3f", str.getdRebsEfficiency()));
			data.add(String.format("%.3f", str.getStealsEfficiency()));
			data.add(String.format("%.3f", str.getAssistEfficiency()));

			rowimage.add(data);
		}

		table = new DefaultTableModel(rowimage, columnsName);
		mytable = new MyTable(table);
		mytable.updateUI();
		// table.setEnabled(false);
		// table.setFillsViewportHeight(true);
		// table.setSize(2*FrameSize.width/3, 9*FrameSize.height/12);
		jScrollPane = new JScrollPane(mytable);
		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(FrameSize.width / 3, FrameSize.height / 12,
				2*FrameSize.width / 3, FrameSize.height*7/8- FrameSize.height / 12);
		jScrollPane.setOpaque(false);

		resizeTable(false, jScrollPane, mytable);

		mytable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					showOne((String) mytable.getModel().getValueAt(
							mytable.getSelectedRow(), 0));
				}
			}

		});

		this.add(jScrollPane);
		this.repaint();
	}

	/** 设置标题 */
	void setHeader() {
		header.setLayout(null);
		header.setBounds(0, 0, FrameSize.width, FrameSize.height / 12);
		header.setBackground(FrameSize.backColor);

		searchField = new EditableTextField();
		searchField.setBounds(2 * FrameSize.width / 3, 10, FrameSize.width / 9,
				35);
		header.add(searchField);

		JButton searchButton = new JButton(new ImageIcon("image\\find.png"));
		searchButton.setBounds(4 * FrameSize.width / 5, 10, 35, 35);
		searchButton.setBackground(FrameSize.buttonbackColor);
		searchButton.setToolTipText("查找");
		searchButton.addActionListener(e -> findClick());
		header.add(searchButton);

		JButton sortButton = new JButton(new ImageIcon("image\\sort.png"));
		sortButton.setBounds(4 * FrameSize.width / 5 + 40, 10, 35, 35);
		sortButton.setBackground(FrameSize.buttonbackColor);
		sortButton.setToolTipText("排序");
		sortButton.addActionListener(e -> sortClick());
		header.add(sortButton);

		dataType = new MyComboBox(new String[] { "赛季总数据", "场均数据" });
		dataType.setBounds(20, 10, 100, 35);
		header.add(dataType);

		JButton allButton = new JButton(new ImageIcon("image\\show.png"));
		allButton.setBounds(140, 10, 45, 35);
		allButton.setBackground(FrameSize.buttonbackColor);
		allButton.setToolTipText("显示数据");
		allButton.addActionListener(e -> showAllData());
		header.add(allButton);
	}

	/** 显示场均数据/总数据 */
	void showAllData() {
		jScrollPane.setVisible(false);
		if (dataType.getSelectedItem().equals("赛季总数据")) {
			setTable(tc.getSortedTotalTeams(TeamSortBy.name, SortType.ASEND));
		} else {
			setTable(tc.getSortedAveTeams(TeamSortBy.name, SortType.ASEND));
		}

		
		jScrollPane.repaint();
		jScrollPane.setVisible(true);
		this.add(jScrollPane);

		this.remove(find);
		this.remove(sort);
		this.add(welcome);

		this.repaint();

	}

	/** 排序 */
	void setSort() {
		sort.setLayout(null);
		sort.setBackground(FrameSize.backColor);
		sort.setBounds(0, FrameSize.height / 12, FrameSize.width / 3,
				FrameSize.height*7/8- FrameSize.height / 12);

		JLabel sortby = new JLabel("排序依据");
		sortby.setBounds(FrameSize.width / 30, FrameSize.height / 5, FrameSize.width / 12, FrameSize.height /20);
		sortby.setFont(new Font("微软雅黑", Font.BOLD, 17));
		sortby.setForeground(Color.white);
		sort.add(sortby);

		box = new MyComboBox(new String[] { "球队名称", "比赛场数", "投篮命中数", "投篮出手次数",
				"三分命中数", "三分出手数", "罚球命中数", "罚球出手数", "进攻篮板数", "防守篮板数", "篮板数",
				"助攻数", "抢断数", "盖帽数", "失误数", "犯规数", "比赛得分", "投篮命中率", "三分命中率",
				"罚球命中率", "胜率", "进攻回合", "进攻效率", "防守效率", "进攻篮板效率","防守篮板效率", "抢断效率", "助攻率" });
		box.setBounds(FrameSize.width / 8, FrameSize.height / 5, 150, 40);
		box.setFont(new Font("宋体", Font.PLAIN, 12));
		sort.add(box);

		JButton yes = new JButton(new ImageIcon("image/yes.png"));
		yes.setBounds(FrameSize.width / 4, 3 * FrameSize.height / 5, 50, 50);
		yes.setBackground(FrameSize.buttonbackColor);

		sort.add(yes);

		JLabel big = new JLabel(new ImageIcon("image/升序.png"));
		JRadioButton up = new JRadioButton();
		up.setBackground(FrameSize.buttonbackColor);
		JLabel small = new JLabel(new ImageIcon("image/降序.png"));
		JRadioButton down = new JRadioButton();
		down.setBackground(FrameSize.buttonbackColor);
		up.addActionListener(e -> sorttype = true);
		down.addActionListener(e -> sorttype = false);
		big.setBounds(FrameSize.width / 10, FrameSize.height / 3 + 40, 50, 50);
		up.setBounds(FrameSize.width / 10 - 30, FrameSize.height / 3 + 50, 30,
				30);
		small.setBounds(FrameSize.width / 10 + 120, FrameSize.height / 3 + 40,
				50, 50);
		down.setBounds(FrameSize.width / 10 + 80, FrameSize.height / 3 + 50,
				30, 30);
		ButtonGroup bg = new ButtonGroup();
		yes.addActionListener(e -> confirmClick());
		bg.add(up);
		bg.add(down);
		sort.add(big);
		sort.add(small);
		sort.add(up);
		sort.add(down);
	}

	/** 搜索 */
	void setFind() {
		find.setBackground(FrameSize.backColor);
		find.setBounds(0, FrameSize.height / 12, FrameSize.width / 3,
				11 * FrameSize.height / 12);
		find.setLayout(null);

		JLabel name = new JLabel("队名");// 队伍名称
		JLabel nameAbridge = new JLabel("缩写");// 名称缩写
		JLabel address = new JLabel("所在地");// 所在地
		JLabel matchArea = new JLabel("赛区");// 赛区
		JLabel playerArea = new JLabel("分区");// 分区
		JLabel manage = new JLabel("主场");// 主场
		JLabel foundYear = new JLabel("建立时间");// 建立时间

		name.setForeground(Color.white);
		nameAbridge.setForeground(Color.white);
		address.setForeground(Color.white);
		matchArea.setForeground(Color.white);
		playerArea.setForeground(Color.white);
		manage.setForeground(Color.white);
		foundYear.setForeground(Color.white);

		name.setBounds(FrameSize.width/40, FrameSize.height / 8 - 50, FrameSize.width/12, 30);
		nameAbridge.setBounds(FrameSize.width / 5, FrameSize.height / 8 - 50,
				FrameSize.width/24, 30);
		address.setBounds(FrameSize.width/40, FrameSize.height / 8 + 130, FrameSize.width/12, 30);
		matchArea.setBounds(FrameSize.width/40, FrameSize.height / 8 + 190, FrameSize.width/12, 30);
		playerArea.setBounds(FrameSize.width/40, FrameSize.height / 8 + 250, FrameSize.width/12, 30);
		manage.setBounds(FrameSize.width/40, FrameSize.height / 8 + 310, FrameSize.width/12, 30);
		foundYear.setBounds(FrameSize.width/40, FrameSize.height / 8 + 370, FrameSize.width/12, 30);

		find.add(name);
		find.add(nameAbridge);
		find.add(address);
		find.add(matchArea);
		find.add(playerArea);
		find.add(manage);
		find.add(foundYear);

	}

	/** 欢迎页面 */
	void setWelcome() {
		welcome.setLayout(null);
		welcome.setBounds(0, FrameSize.height / 12, FrameSize.width / 3, 11 * FrameSize.height / 12);
		welcome.setBackground(FrameSize.backColor);
		JLabel nba = new JLabel(new ImageIcon("image/nba.png"));
		nba.setBounds(FrameSize.width / 12, FrameSize.height / 8,
				FrameSize.width / 6, 200);
		welcome.add(nba);
	}

	/** 点击排序按钮 */
	void sortClick() {
		this.remove(welcome);
		this.remove(find);
		sort.repaint();
		this.add(sort);
		this.repaint();

	}

	/** 点击排序确认按钮 */
	void confirmClick() {
		SortType type = null;
		

		jScrollPane.setVisible(false);
		if (sorttype == true) {
			type = SortType.ASEND;
		} else {
			type = SortType.DESEND;
		}
		TeamSortBy teamSortBy = null;
		String sortby = (String) box.getSelectedItem();
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
		}else if (sortby.equals("助攻率")) {
			teamSortBy = TeamSortBy.assistEfficiency;
		}
		TeamMatchVO[] sortteam;
		if (dataType.getSelectedItem().equals("赛季总数据")) {
			sortteam = tc.getSortedTotalTeams(teamSortBy, type);
		} else {
			sortteam = tc.getSortedAveTeams(teamSortBy, type);
		}
		

		setTable(sortteam);

		jScrollPane.repaint();
		jScrollPane.setVisible(true);

		this.add(jScrollPane);
		this.repaint();
	}

	/** 在findPanel上显示一个球队的信息 */
	private void showOne(String teamname) {
		this.remove(welcome);
		this.remove(sort);
		TeamPO teamresult = tc.getTeamData(teamname);
		svgCanvas.setDocument(teamresult.getImage());
		nameresult.setText(teamresult.getName());// 队伍名称
		nameAbridgeresult.setText(teamresult.getNameAbridge());// 名称缩写
		addressresult.setText(teamresult.getAddress());// 所在地
		matchArearesult.setText(teamresult.getMatchArea());// 赛区
		playerArearesult.setText(teamresult.getPlayerArea().toString());// 分区
		manageresult.setText(teamresult.getManage());// 主场
		foundYearresult.setText(String.valueOf(teamresult.getFoundYear()));// 建立时间
		match=new JButton("");
		
		svgCanvas.setOpaque(false);
		
		match.setBounds(FrameSize.width/3-45, FrameSize.height /40, 40,40);
		svgCanvas.setBounds(FrameSize.width /40, FrameSize.height / 8, FrameSize.width / 4, 3*FrameSize.height /20);
		nameresult.setBounds(FrameSize.width /20, FrameSize.height / 8 - 50, 100, 3*FrameSize.height/80 );
		nameAbridgeresult.setBounds(FrameSize.width / 4,
				FrameSize.height / 8 - 50, 50, 30);
		addressresult.setBounds(FrameSize.width /10, FrameSize.height / 8 + 130, FrameSize.width /8, 3*FrameSize.height/80);
		matchArearesult.setBounds(FrameSize.width /10, FrameSize.height / 8 + 190, FrameSize.width /8, 3*FrameSize.height/80);
		playerArearesult.setBounds(FrameSize.width /10, FrameSize.height / 8 + 250, FrameSize.width /8, 3*FrameSize.height/80);
		manageresult.setBounds(FrameSize.width /10, FrameSize.height / 8 + 310, FrameSize.width /8, 3*FrameSize.height/80);
		foundYearresult.setBounds(FrameSize.width /10, FrameSize.height / 8 + 370, FrameSize.width /8, 3*FrameSize.height/80);
		
		match.addActionListener(e->setMatch());
		
		find.add(match);
		find.add(svgCanvas);
		find.add(nameresult);
		find.add(nameAbridgeresult);
		find.add(addressresult);
		find.add(matchArearesult);
		find.add(playerArearesult);
		find.add(manageresult);
		find.add(foundYearresult);
		find.setVisible(true);
		find.repaint();
		this.add(find);
		this.repaint();
		this.validate();
		svgCanvas.setVisible(true);
		svgCanvas.repaint();

	}

	/** 点击查找按钮 */
	void findClick() {
		if (searchField.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "请输入查找球队的缩写");
			return;
		} else if (tc.getTotalTeam(searchField.getText()) == null) {
			JOptionPane.showMessageDialog(this, "未查到球队信息");
			return;
		}

		jScrollPane.setVisible(false);
		
		TeamMatchVO []teamresult = new TeamMatchVO [1];
		
		find.setVisible(false);
		String teamname = searchField.getText();
		showOne(teamname);
		if (dataType.getSelectedItem().equals("赛季总数据")) {
			teamresult[0]=tc.getTotalTeam(teamname);
		} else {
			teamresult[0]=tc.getAveTeam(teamname);
		}
		
		setTable(teamresult);
		jScrollPane.repaint();
		jScrollPane.setVisible(true);
		this.add(jScrollPane);

	}

	/**点击查看比赛按钮*/
	void setMatch(){
		TeamMatchPanel teammatch=new TeamMatchPanel(nameAbridgeresult.getText());
		this.remove(jScrollPane);
		this.add(teammatch);
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

}
