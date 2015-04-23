package ui.playerui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import po.PlayerPO;
import ui.mainui.EditableTextField;
import ui.mainui.FrameSize;
import ui.mainui.MyButton;
import ui.mainui.MyComboBox;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import ui.mainui.UneditableTextField;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.PlayerVO;
import vo.SortType;
import bl.matchbl.MatchController;
import bl.playerbl.PlayerController;

public class PlayerPanel extends JPanel {

	JPanel welcomePanel = new JPanel();
	JPanel playerMessagePanel = new JPanel();
	PlayerMatchPanel playerMatchPanel;
	boolean matchpanel = false;
	DefaultTableModel table;
	JScrollPane jScrollPane;
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

	PlayerMatchVO[] allPlayers;
	boolean isPlayer = false;

	PlayerController playerController = new PlayerController();
	MatchController matchController = new MatchController();
	String[] allPlayerNames;
	JComboBox searchBox;

	JTextField[] playerText = new UneditableTextField[84];

	Vector columnsName = new Vector();

	public PlayerPanel() {
		allPlayers = playerController.sortTotalPlayers(PlayerSortBy.name,
				SortType.ASEND);
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height);
		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		searchBox = new MyComboBox(playerController.getAllPlayerNames());
		searchBox.setBorder(null);
		searchBox.setBounds(2 * FrameSize.width / 3, 10, FrameSize.width / 9,
				35);
		searchBox.setMaximumRowCount(15);
		AutoCompleteDecorator.decorate(searchBox);
		JPanel header = headerPanel();
		new Thread() {
			public void run() {
				setTable(allPlayers);
			}
		}.start();
		;

		setColumnsName();
		setFind();
		setScreen();
		setWelcome();
		setOnePlayerMessagePanel();
		showOne(playerController.getAllPlayerNames()[0]);
		this.add(header);
		this.repaint();
	}

	private void setColumnsName() {
		columnsName.add("球员名称");
		// columnsName.add("球衣号码");
		// columnsName.add("位置");
		// columnsName.add("身高");
		// columnsName.add("生日");
		// columnsName.add("年龄");
		// columnsName.add("球龄");
		// columnsName.add("毕业学校");
		columnsName.add("所属球队");
		columnsName.add("参赛场数");
		columnsName.add("先发场数");
		columnsName.add("篮板");
		columnsName.add("助攻");
		columnsName.add("在场时间");

		columnsName.add("盖帽数");
		columnsName.add("得分/篮板/助攻");
		columnsName.add("犯规");
		columnsName.add("失误");
		columnsName.add("分钟");
		columnsName.add("两双");

		columnsName.add("投篮出手数");
		columnsName.add("投篮命中数");
		columnsName.add("投篮(%)");
		columnsName.add("三分出手数");
		columnsName.add("三分命中数");
		columnsName.add("三分(%)");
		columnsName.add("罚球出手数");
		columnsName.add("罚球命中数");
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

	}

	/** 设置表格 */
	private void setTable(PlayerMatchVO[] playerMatchVOs) {

		Vector data = new Vector();
		for (int i = 0; i < playerMatchVOs.length; i++) {
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
			rowData.add(FrameSize.roundForNumber(playerVO.getMatchNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getFirstServiceNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getRebs()));
			rowData.add(FrameSize.roundForNumber(playerVO.getAssistNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getTime()));

			rowData.add(FrameSize.roundForNumber(playerVO.getBlockNo()));
			rowData.add(FrameSize.roundForNumber(playerVO
					.getScoring_rebound_assist()));
			rowData.add(FrameSize.roundForNumber(playerVO.getFoulsNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getMistakesNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getMinute()));
			rowData.add(FrameSize.roundForNumber(playerVO.getTwoPair()));

			rowData.add(FrameSize.roundForNumber(playerVO.getHandNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getHitNo()));
			double hitRate = playerVO.getHitRate() * 100;
			if (hitRate >= 0)
				rowData.add(FrameSize.roundForNumber(hitRate));
			else
				rowData.add("-");
			rowData.add(FrameSize.roundForNumber(playerVO.getThreeHandNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getThreeHitNo()));
			double threeHitRate = playerVO.getThreeHitRate();
			if (threeHitRate >= 0)
				rowData.add(FrameSize.roundForNumber(threeHitRate * 100));
			else
				rowData.add("-");
			rowData.add(FrameSize.roundForNumber(playerVO.getPenaltyHandNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getPenaltyHitNo()));
			double penaltyHitRate = playerVO.getPenaltyHitRate();
			if (penaltyHitRate >= 0)
				rowData.add(FrameSize.roundForNumber(penaltyHitRate * 100));
			else
				rowData.add("-");
			rowData.add(FrameSize.roundForNumber(playerVO.getOffendNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getDefenceNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getStealsNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getBlockNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getMistakesNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getFoulsNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getPoints()));
			rowData.add(FrameSize.roundForNumber(playerVO.getEfficiency()));
			rowData.add(FrameSize.roundForNumber(playerVO.getGmScEfficiency()));
			rowData.add(FrameSize.roundForNumber(playerVO.getTrueHitRate() * 100));
			rowData.add(FrameSize.roundForNumber(playerVO.getHitEfficiency()));
			rowData.add(FrameSize.roundForNumber(playerVO.getRebEfficiency()));
			rowData.add(FrameSize.roundForNumber(playerVO
					.getOffenseRebsEfficiency()));
			rowData.add(FrameSize.roundForNumber(playerVO
					.getDefenceRebsEfficiency()));
			rowData.add(FrameSize.roundForNumber(playerVO.getAssistEfficiency() * 100));
			rowData.add(FrameSize.roundForNumber(playerVO.getStealsEfficiency() * 100));
			rowData.add(FrameSize.roundForNumber(playerVO.getBlockEfficiency() * 100));
			rowData.add(FrameSize.roundForNumber(playerVO
					.getMistakeEfficiency() * 100));
			rowData.add(FrameSize.roundForNumber(playerVO.getUseEfficiency() * 100));

			data.add(rowData);
		}
		table = new DefaultTableModel(data, columnsName);
		mytable = new MyTable(table);
		mytable.setRowSorter(new TableRowSorter<TableModel>(table));
		TableRowSorter rowSorter = (TableRowSorter) mytable.getRowSorter();
		Comparator<Number> numberComparator = new Comparator<Number>() {
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
		for (int col = 2; col < table.getColumnCount(); col++) {
			rowSorter.setComparator(col, numberComparator);
		}
		jScrollPane = new JScrollPane(mytable);
		jScrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		jScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setBounds(FrameSize.width / 3, FrameSize.height / 12,
				2 * FrameSize.width / 3, FrameSize.height * 7 / 8
						- FrameSize.height / 12);
		jScrollPane.setBackground(FrameSize.backColor);
		jScrollPane.getViewport().setOpaque(false);
		resizeTable(false, jScrollPane, mytable);
		this.add(jScrollPane);

		mytable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					try {
						showOne((String) mytable.getModel().getValueAt(
								mytable.getSelectedRow(), 0));
					} catch (NullPointerException e1) {
						MyFrame.playerpanel.remove(findPanel);
						MyFrame.playerpanel.add(welcomePanel);
						MyFrame.playerpanel.repaint();
						JOptionPane.showMessageDialog(null, "未找到该球员的个人信息",
								"查找失败", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

		});
	}

	/** 显示一个球队的所有球员 */
	public void showTeamPlayers(PlayerMatchVO[] playerMatchVO) {
		this.remove(welcomePanel);
		this.remove(screenPanel);
		this.remove(playerMessagePanel);
		jScrollPane.setVisible(false);
		setTable(playerMatchVO);
		jScrollPane.setVisible(true);
		this.add(jScrollPane);
		for (int i = 0; i < playerMatchVO.length; i++) {
			if (playerController.findPlayer(playerMatchVO[i].getName())==null) {
				i++;
			}else{
				showOne(playerMatchVO[i].getName());
				this.repaint();
				break;
			}
		}

	}

	/** 在findPanel上显示一个球员的信息 */
	private void showOne(String playerInfo) {
		if (matchpanel) {
			this.remove(playerMatchPanel);
			matchpanel = false;
		}
		this.remove(welcomePanel);
		this.remove(screenPanel);
		findPanel.remove(actionLabel);

		matchButton = new MyButton("比赛", Color.black, Color.DARK_GRAY);
		playerTeamButton = new MyButton("球队", Color.black, Color.DARK_GRAY);
		matchButton.setFont(new Font("幼圆", Font.BOLD, 12));
		playerTeamButton.setFont(new Font("幼圆", Font.BOLD, 12));
		matchButton.setForeground(Color.white);
		playerTeamButton.setForeground(Color.white);
		playerTeamButton.setBounds(FrameSize.width / 3 - 60,
				11 * FrameSize.height / 16, 55, 30);
		matchButton.setBounds(FrameSize.width / 3 - 130,
				11 * FrameSize.height / 16, 58, 30);
		matchButton.addActionListener(e -> setMatch());
		playerTeamButton.addActionListener(e -> showPlayerTeam(teamText
				.getText()));

		PlayerPO playerVO = playerController.findPlayer(playerInfo);
		PlayerMatchVO playerMatchVO = playerController
				.findPlayerMatchAve(playerInfo);
		nameText.setText(playerVO.getName());
		numText.setText(String.valueOf(playerVO.getNumber()));
		positionText.setText(String.valueOf(playerVO.getPosition()));
		try{
		teamText.setText(playerMatchVO.getTeam());
		}catch (Exception e){teamText.setText("");}
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
		MyFrame.card.show(MyFrame.mainpanel, "team");
		MyFrame.locationlable.setText("当前位置：球队");
	}

	private void setMatch() {
		// System.out.println(teamText.getText());
		if (matchpanel && playerMatchPanel != null) {
			this.remove(playerMatchPanel);
		}
		matchpanel = true;
		try {
			playerMatchPanel = new PlayerMatchPanel(nameText.getText(),
					teamText.getText());
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "暂无该球员的比赛信息", "查找失败",
					JOptionPane.ERROR_MESSAGE);
			this.add(jScrollPane);
		}
		this.remove(jScrollPane);
		this.remove(playerMessagePanel);
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

		positionBox = new MyComboBox(new String[] { "F", "C", "G" });
		positionBox.setBounds(FrameSize.width / 8, FrameSize.height / 12, 150,
				40);
		positionBox.setFont(new Font("宋体", Font.PLAIN, 12));
		positionBox.addActionListener(e -> screenPlayerConfirmClick());
		screenPanel.add(positionBox);

		JLabel playerZoneLabel = new JLabel("赛区");
		playerZoneLabel.setForeground(Color.white);
		playerZoneLabel.setBounds(FrameSize.width / 30, FrameSize.height / 4,
				100, 40);
		playerZoneLabel.setFont(new Font("微软雅黑", Font.BOLD, 17));
		screenPanel.add(playerZoneLabel);

		playerZoneBox = new MyComboBox(new String[] { "东部", "西部" });
		playerZoneBox.setBounds(FrameSize.width / 8, FrameSize.height / 4, 150,
				40);
		playerZoneBox.setFont(new Font("宋体", Font.PLAIN, 12));
		screenPanel.add(playerZoneBox);
		playerZoneBox.addActionListener(e -> setPlayerAreaBox());

		JLabel playerAreaLabel = new JLabel("分区");
		playerAreaLabel.setForeground(Color.white);
		playerAreaLabel.setBounds(FrameSize.width / 30,
				FrameSize.height * 5 / 12, 100, 40);
		playerAreaLabel.setFont(new Font("微软雅黑", Font.BOLD, 17));
		screenPanel.add(playerAreaLabel);

		playerAreaBox = new MyComboBox(new String[] { "ATLANTIC", "CENTRAL",
				"SOUTHEAST" });
		playerAreaBox.setBounds(FrameSize.width / 8, FrameSize.height * 5 / 12,
				150, 40);
		playerAreaBox.setFont(new Font("宋体", Font.PLAIN, 12));
		playerAreaBox.addActionListener(e -> screenPlayerConfirmClick());
		screenPanel.add(playerAreaBox);

		JLabel screenPlayerLabel = new JLabel("筛选依据");
		screenPlayerLabel.setForeground(Color.white);
		screenPlayerLabel.setBounds(FrameSize.width / 30,
				FrameSize.height * 7 / 12, 100, 40);
		screenPlayerLabel.setFont(new Font("微软雅黑", Font.BOLD, 17));
		screenPanel.add(screenPlayerLabel);

		screenPlayerBox = new MyComboBox(new String[] { "得分", "篮板", "助攻",
				"得分/篮板/助攻", "盖帽", "抢断", "犯规", "失误", "分钟", "效率", "投篮", "三分",
				"罚球", "两双" });
		screenPlayerBox.setBounds(FrameSize.width / 8,
				FrameSize.height * 7 / 12, 150, 40);
		screenPlayerBox.setFont(new Font("宋体", Font.PLAIN, 12));
		screenPlayerBox.addActionListener(e -> screenPlayerConfirmClick());
		screenPanel.add(screenPlayerBox);

		// JButton yesButton = new MyButton(new
		// ImageIcon("image/yes.png"),Color.white,Color.gray);
		// yesButton.setBounds(FrameSize.width / 4, 2 * FrameSize.height / 3,
		// 50,
		// 50);
		// // yesButton.setBackground(Color.white);
		// yesButton.addActionListener(e -> screenPlayerConfirmClick());
		// screenPanel.add(yesButton);

	}

	/** 点击筛选确认按钮 */
	private void screenPlayerConfirmClick() {
		if (matchpanel && playerMatchPanel != null) {
			this.remove(playerMatchPanel);
			matchpanel = false;
		}
		if (matchpanel)
		{
			matchpanel = false;
		}
		this.remove(playerMessagePanel);
		String position = (positionBox.getSelectedItem().toString());

		Area area = Area.ATLANTIC;
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
			playerSortBy = PlayerSortBy.hitRate;
		} else if (sortBy.equals("三分")) {
			playerSortBy = PlayerSortBy.threeHitRate;
		} else if (sortBy.equals("罚球")) {
			playerSortBy = PlayerSortBy.penaltyHitRate;
		} else if (sortBy.equals("两双")) {
			playerSortBy = PlayerSortBy.twoPair;
		}
		PlayerMatchVO[] screenPlayer;
		try {
			if (dataType.getSelectedItem().equals("赛季总数据"))
				screenPlayer = playerController.screenTotalPlayers(position,
						area, playerSortBy);
			else
				screenPlayer = playerController.screenAvePlayers(position,
						area, playerSortBy);
			jScrollPane.setVisible(false);
			setTable(screenPlayer);
			jScrollPane.repaint();
			jScrollPane.setVisible(true);
			this.add(jScrollPane);
			this.repaint();
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "无该条件球员", "筛选失败",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/** 按照赛区显示分区 */
	private void setPlayerAreaBox() {
		screenPanel.remove(playerAreaBox);
		if (playerZoneBox.getSelectedItem().equals("东部")) {
			playerAreaBox = new MyComboBox(new String[] { "ATLANTIC",
					"CENTRAL", "SOUTHEAST" });
			playerAreaBox.setBounds(FrameSize.width / 8,
					FrameSize.height * 5 / 12, 150, 40);
			playerAreaBox.setFont(new Font("宋体", Font.PLAIN, 12));

		}

		else {
			playerAreaBox = new MyComboBox(new String[] { "SOUTHWEST",
					"NORTHWEST", "PACIFIC" });
			playerAreaBox.setBounds(FrameSize.width / 8,
					FrameSize.height * 5 / 12, 150, 40);
			playerAreaBox.setFont(new Font("宋体", Font.PLAIN, 12));
		}
		playerAreaBox.repaint();
		playerAreaBox.setVisible(true);
		screenPanel.add(playerAreaBox);
		screenPanel.repaint();
		screenPlayerConfirmClick();
		playerAreaBox.addActionListener(e -> screenPlayerConfirmClick());
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

	/** 标题Panel */
	private JPanel headerPanel() {

		JPanel panel = new JPanel();
		// panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBounds(0, 0, FrameSize.width, FrameSize.height / 12);
		panel.setBackground(FrameSize.backColor);
		panel.add(searchBox);

		JButton searchButton = new MyButton(new ImageIcon("image\\find.png"),
				Color.GRAY, Color.LIGHT_GRAY);
		searchButton.setBounds(4 * FrameSize.width / 5, 10, 35, 35);
		searchButton.setToolTipText("查找");
		searchButton.addActionListener(e -> findPlayerClick(searchBox
				.getSelectedItem().toString()));
		panel.add(searchButton);

		searchBox.getEditor().getEditorComponent()
				.addKeyListener(new KeyAdapter() {
					@Override
					public void keyPressed(KeyEvent e) {
						if (e.getKeyChar() == KeyEvent.VK_ENTER) // 按回车键执行相应操作;
						{
							try {
								findPlayerClick(searchBox.getSelectedItem()
										.toString());
							} catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}
				});

		JButton refresh = new MyButton(new ImageIcon("image\\refresh.png"),
				FrameSize.buttonbackColor, Color.LIGHT_GRAY);
		refresh.setBounds(FrameSize.width / 6, 10, 35, 35);
		refresh.setToolTipText("刷新");
		refresh.addActionListener(e -> update());
		panel.add(refresh);

		JButton screenButton = new MyButton(new ImageIcon("image\\screen.png"),
				Color.GRAY, Color.LIGHT_GRAY);
		screenButton.setBounds(4 * FrameSize.width / 5 + 40, 10, 35, 35);
		// screenButton.setBackground(new Color(68, 68, 68));
		screenButton.setToolTipText("筛选");
		screenButton.addActionListener(e -> screenPlayerClick());
		panel.add(screenButton);

		dataType = new MyComboBox(new String[] { "赛季总数据", "场均数据" });
		// dataType.setBounds(20, 10, 100, 35);
		dataType.setBounds(FrameSize.width / 60, 10, FrameSize.width / 12, 35);
		panel.add(dataType);

		JButton allButton = new MyButton(new ImageIcon("image\\show.png"),
				Color.GRAY, Color.LIGHT_GRAY);
		allButton.setBounds(7 * FrameSize.width / 60, 10, 45, 35);
		// allButton.setBounds(140, 10, 45, 35);
		// allButton.setBackground(new Color(68, 68, 68));
		allButton.setToolTipText("显示");
		allButton.addActionListener(e -> showAllData());
		panel.add(allButton);

		return panel;

	}

	/** 实时更新 */
	private void update() {
		PlayerMatchVO playerresult = null;
		if (dataType.getSelectedItem().equals("赛季总数据")) {
			updateTable(playerController.sortTotalPlayers(PlayerSortBy.name,
					SortType.ASEND));
		} else {
			updateTable(playerController.sortAvePlayers(PlayerSortBy.name,
					SortType.ASEND));
		}
		updateOne();
		jScrollPane.repaint();
		this.repaint();
	}

	private void updateTable(PlayerMatchVO[] player) {
		Vector data = new Vector();
		for (int i = 0; i < player.length; i++) {
			PlayerMatchVO playerVO = player[i];
			if (playerVO == null) {
				continue;
			}
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
			rowData.add(FrameSize.roundForNumber(playerVO.getMatchNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getFirstServiceNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getRebs()));
			rowData.add(FrameSize.roundForNumber(playerVO.getAssistNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getTime()));

			rowData.add(FrameSize.roundForNumber(playerVO.getBlockNo()));
			rowData.add(FrameSize.roundForNumber(playerVO
					.getScoring_rebound_assist()));
			rowData.add(FrameSize.roundForNumber(playerVO.getFoulsNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getMistakesNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getMinute()));
			rowData.add(FrameSize.roundForNumber(playerVO.getTwoPair()));

			rowData.add(FrameSize.roundForNumber(playerVO.getHandNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getHitNo()));
			double hitRate = playerVO.getHitRate() * 100;
			if (hitRate >= 0)
				rowData.add(FrameSize.roundForNumber(hitRate));
			else
				rowData.add("-");
			rowData.add(FrameSize.roundForNumber(playerVO.getThreeHandNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getThreeHitNo()));
			double threeHitRate = playerVO.getThreeHitRate();
			if (threeHitRate >= 0)
				rowData.add(FrameSize.roundForNumber(threeHitRate * 100));
			else
				rowData.add("-");
			rowData.add(FrameSize.roundForNumber(playerVO.getPenaltyHandNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getPenaltyHitNo()));
			double penaltyHitRate = playerVO.getPenaltyHitRate();
			if (penaltyHitRate >= 0)
				rowData.add(FrameSize.roundForNumber(penaltyHitRate * 100));
			else
				rowData.add("-");
			rowData.add(FrameSize.roundForNumber(playerVO.getOffendNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getDefenceNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getStealsNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getBlockNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getMistakesNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getFoulsNo()));
			rowData.add(FrameSize.roundForNumber(playerVO.getPoints()));
			rowData.add(FrameSize.roundForNumber(playerVO.getEfficiency()));
			rowData.add(FrameSize.roundForNumber(playerVO.getGmScEfficiency()));
			rowData.add(FrameSize.roundForNumber(playerVO.getTrueHitRate() * 100));
			rowData.add(FrameSize.roundForNumber(playerVO.getHitEfficiency()));
			rowData.add(FrameSize.roundForNumber(playerVO.getRebEfficiency()));
			rowData.add(FrameSize.roundForNumber(playerVO
					.getOffenseRebsEfficiency()));
			rowData.add(FrameSize.roundForNumber(playerVO
					.getDefenceRebsEfficiency()));
			rowData.add(FrameSize.roundForNumber(playerVO.getAssistEfficiency() * 100));
			rowData.add(FrameSize.roundForNumber(playerVO.getStealsEfficiency() * 100));
			rowData.add(FrameSize.roundForNumber(playerVO.getBlockEfficiency() * 100));
			rowData.add(FrameSize.roundForNumber(playerVO
					.getMistakeEfficiency() * 100));
			rowData.add(FrameSize.roundForNumber(playerVO.getUseEfficiency() * 100));

			data.add(rowData);

		}
		table.setDataVector(data, columnsName);
		TableRowSorter rowSorter = (TableRowSorter) mytable.getRowSorter();
		Comparator<Number> numberComparator = new Comparator<Number>() {
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
		for (int col = 2; col < table.getColumnCount(); col++) {
			rowSorter.setComparator(col, numberComparator);
		}
		resizeTable(false, jScrollPane, mytable);

	}

	private void updateOne() {

		PlayerMatchVO playerVO = null;
		if (dataType.getSelectedItem().equals("赛季总数据")) {
			playerVO = playerController.findPlayerTotal(searchBox
					.getSelectedItem().toString());
		} else {
			playerVO = playerController.findPlayerMatchAve(searchBox
					.getSelectedItem().toString());
		}
		playerText[1].setText(String.valueOf(playerVO.getMatchNo()));
		playerText[3].setText(String.valueOf(playerVO.getFirstServiceNo()));
		playerText[5].setText(String.format("%.1f", playerVO.getRebs()));
		playerText[7].setText(String.format("%.1f", playerVO.getAssistNo()));
		playerText[9].setText(String.format("%.1f", playerVO.getTime()));

		playerText[11].setText(String.format("%.1f", playerVO.getBlockNo()));
		playerText[13].setText(String.format("%.1f",
				playerVO.getScoring_rebound_assist()));
		playerText[15].setText(String.format("%.1f", playerVO.getStealsNo()));
		playerText[17].setText(String.format("%.1f", playerVO.getFoulsNo()));
		playerText[19].setText(String.format("%.1f", playerVO.getMistakesNo()));
		playerText[21].setText(String.format("%.1f", playerVO.getMinute()));
		playerText[23].setText(String.format("%.1f", playerVO.getHandNo()));
		playerText[25]
				.setText(String.format("%.1f", playerVO.getThree_points()));
		playerText[27].setText(String.format("%.1f", playerVO.getBlockNo()));
		playerText[29].setText(String.format("%.1f", playerVO.getTwoPair()));
		playerText[31].setText(String.format("%.1f", playerVO.getHandNo()));
		playerText[33].setText(String.format("%.1f", playerVO.getHitNo()));
		double hitRate = playerVO.getHitRate() * 100;
		if (hitRate >= 0)
			playerText[35].setText(String.format("%.1f", hitRate));
		else
			playerText[35].setText("-");
		playerText[37]
				.setText(String.format("%.1f", playerVO.getThreeHandNo()));
		playerText[39].setText(String.format("%.1f", playerVO.getThreeHitNo()));
		double threeHitRate = playerVO.getThreeHitRate();
		if (threeHitRate >= 0)
			playerText[41].setText(String.format("%.1f", threeHitRate * 100));
		else
			playerText[41].setText("-");
		playerText[43].setText(String.format("%.1f",
				playerVO.getPenaltyHandNo()));
		playerText[45]
				.setText(String.format("%.1f", playerVO.getPenaltyHitNo()));
		double penaltyHitRate = playerVO.getPenaltyHitRate();
		if (penaltyHitRate >= 0)
			playerText[47].setText(String.format("%.1f", penaltyHitRate * 100));
		else
			playerText[47].setText("-");
		playerText[49].setText(String.format("%.1f", playerVO.getOffendNo()));
		playerText[51].setText(String.format("%.1f", playerVO.getDefenceNo()));
		playerText[53].setText(String.format("%.1f", playerVO.getBlockNo()));
		playerText[55].setText(String.format("%.1f", playerVO.getMistakesNo()));
		playerText[57].setText(String.format("%.1f", playerVO.getFoulsNo()));
		playerText[59].setText(String.format("%.1f", playerVO.getPoints()));
		playerText[61].setText(String.format("%.1f", playerVO.getEfficiency()));
		playerText[63].setText(String.format("%.1f",
				playerVO.getGmScEfficiency()));
		playerText[65].setText(String.format("%.1f",
				playerVO.getTrueHitRate() * 100));
		playerText[67].setText(String.format("%.1f",
				playerVO.getHitEfficiency()));
		playerText[69].setText(String.format("%.1f",
				playerVO.getRebEfficiency()));
		playerText[71].setText(String.format("%.1f",
				playerVO.getOffenseRebsEfficiency()));
		playerText[73].setText(String.format("%.1f",
				playerVO.getDefenceRebsEfficiency()));
		playerText[75].setText(String.format("%.1f",
				playerVO.getAssistEfficiency() * 100));
		playerText[77].setText(String.format("%.1f",
				playerVO.getStealsEfficiency() * 100));
		playerText[79].setText(String.format("%.1f",
				playerVO.getBlockEfficiency() * 100));
		playerText[81].setText(String.format("%.1f",
				playerVO.getMistakeEfficiency() * 100));
		playerText[83].setText(String.format("%.1f",
				playerVO.getUseEfficiency() * 100));

		playerMessagePanel.repaint();
	}

	private void setOnePlayerMessagePanel() {
		playerMessagePanel.setLayout(new GridLayout(14, 6, -1, -1));
		playerMessagePanel.setBackground(FrameSize.backColor);
		playerMessagePanel.setBounds(FrameSize.width / 3,
				FrameSize.height / 12, 2 * FrameSize.width / 3,
				FrameSize.height * 7 / 8 - FrameSize.height / 12);
		for (int i = 0; i < 84; i++) {
			playerText[i] = new UneditableTextField();
			playerMessagePanel.add(playerText[i]);
			playerText[i].setFont(new Font("", Font.PLAIN, 15));
			playerText[i]
					.setBorder(BorderFactory.createLineBorder(Color.white));
		}
		playerText[0].setText("参赛场数");
		playerText[2].setText("先发场数");
		playerText[4].setText("篮板");
		playerText[6].setText("助攻");
		playerText[8].setText("在场时间");
		playerText[10].setText("盖帽数");
		playerText[12].setText("得分/篮板/助攻");
		playerText[14].setText("抢断");
		playerText[16].setText("犯规");
		playerText[18].setText("失误");
		playerText[20].setText("分钟");
		playerText[22].setText("投篮");
		playerText[24].setText("三分");
		playerText[26].setText("罚球");
		playerText[28].setText("两双");
		playerText[30].setText("投篮出手数");
		playerText[32].setText("投篮命中数");
		playerText[34].setText("投篮(%)");
		playerText[36].setText("三分出手数");
		playerText[38].setText("三分命中数");
		playerText[40].setText("三分(%)");
		playerText[42].setText("罚球出手数");
		playerText[44].setText("罚球命中数");
		playerText[46].setText("罚球(%)");
		playerText[48].setText("进攻");
		playerText[50].setText("防守");
		playerText[52].setText("盖帽");
		playerText[54].setText("失误");
		playerText[56].setText("犯规");
		playerText[58].setText("得分");
		playerText[60].setText("效率");
		playerText[62].setText("GmSc效率");
		playerText[64].setText("真实命中率(%)");
		playerText[66].setText("投篮效率");
		playerText[68].setText("篮板率");
		playerText[70].setText("进攻篮板率");
		playerText[72].setText("防守篮板率");
		playerText[74].setText("助攻率(%)");
		playerText[76].setText("抢断率");
		playerText[78].setText("盖帽率(%)");
		playerText[80].setText("失误率(%)");
		playerText[82].setText("使用率(%)");
	}

	/** 显示所有总数据/场均数据 */
	private void showAllData() {
		if (matchpanel && playerMatchPanel != null) {
			this.remove(playerMatchPanel);
			matchpanel = false;
		}
		if (matchpanel)
		{
			matchpanel = false;
		}
		this.remove(playerMessagePanel);
		jScrollPane.setVisible(false);
		if (dataType.getSelectedItem().equals("赛季总数据"))
			setTable(playerController.sortTotalPlayers(PlayerSortBy.name,
					SortType.ASEND));
		else
			setTable(playerController.sortAvePlayers(PlayerSortBy.name,
					SortType.ASEND));
		this.remove(playerMessagePanel);
		jScrollPane.repaint();
		jScrollPane.setVisible(true);
		this.add(jScrollPane);

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
		showAllData();
		this.remove(welcomePanel);
		this.remove(findPanel);
		// this.remove(playerMessagePanel);
		this.add(screenPanel);
		this.repaint();
		screenPlayerConfirmClick();
	}

	/** 点击查找按钮 */
	public void findPlayerClick(String playerInfo) {
		isPlayer = false;
		if (playerInfo.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入查找球员的名字", "查找失败",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			for (int i = 0; i < allPlayers.length; i++) {
				if (playerInfo.equals(allPlayers[i].getName())) {
					isPlayer = true;
					break;
				}
			}
			if (!isPlayer) {
				PlayerMatchVO onePlayer = new PlayerMatchVO(playerInfo);
				try {

					jScrollPane.setVisible(false);
					setPlayerMessage(onePlayer);
					this.add(playerMessagePanel);
					this.repaint();
					System.out.println("sdfsf");
				} catch (NullPointerException e) {
					showAllData();
				}
				return;
			} else if (playerController.findPlayer(playerInfo) == null) {
				JOptionPane.showMessageDialog(null, "未找到该球员的个人信息", "查找失败",
						JOptionPane.ERROR_MESSAGE);
				MyFrame.playerpanel.remove(findPanel);
				MyFrame.playerpanel.add(welcomePanel);
				MyFrame.playerpanel.repaint();
			} else {
				showOne(playerInfo);
			}
			PlayerMatchVO onePlayer;
			try {
				if (dataType.getSelectedItem().equals("赛季总数据")) {
					onePlayer = playerController.findPlayerTotal(playerInfo);
				} else {
					onePlayer = playerController.findPlayerMatchAve(playerInfo);
				}
				jScrollPane.setVisible(false);
				setPlayerMessage(onePlayer);
				this.add(playerMessagePanel);
				this.repaint();
			} catch (NullPointerException e) {
				showAllData();
			}
			// jScrollPane.repaint();
			// jScrollPane.setVisible(true);
			// this.add(jScrollPane);
		}
	}

	/** 一个球员信息（右侧） */
	private void setPlayerMessage(PlayerMatchVO playerVO) {
		playerMessagePanel.setVisible(false);

		playerText[1].setText(String.valueOf(playerVO.getMatchNo()));
		playerText[3].setText(String.valueOf(playerVO.getFirstServiceNo()));
		playerText[5].setText(String.format("%.1f", playerVO.getRebs()));
		playerText[7].setText(String.format("%.1f", playerVO.getAssistNo()));
		playerText[9].setText(String.format("%.1f", playerVO.getTime()));

		playerText[11].setText(String.format("%.1f", playerVO.getBlockNo()));
		playerText[13].setText(String.format("%.1f",
				playerVO.getScoring_rebound_assist()));
		playerText[15].setText(String.format("%.1f", playerVO.getStealsNo()));
		playerText[17].setText(String.format("%.1f", playerVO.getFoulsNo()));
		playerText[19].setText(String.format("%.1f", playerVO.getMistakesNo()));
		playerText[21].setText(String.format("%.1f", playerVO.getMinute()));
		playerText[23].setText(String.format("%.1f", playerVO.getHandNo()));
		playerText[25]
				.setText(String.format("%.1f", playerVO.getThree_points()));
		playerText[27].setText(String.format("%.1f", playerVO.getBlockNo()));
		playerText[29].setText(String.format("%.1f", playerVO.getTwoPair()));
		playerText[31].setText(String.format("%.1f", playerVO.getHandNo()));
		playerText[33].setText(String.format("%.1f", playerVO.getHitNo()));
		double hitRate = playerVO.getHitRate() * 100;
		if (hitRate >= 0)
			playerText[35].setText(String.format("%.1f", hitRate));
		else
			playerText[35].setText("-");
		playerText[37]
				.setText(String.format("%.1f", playerVO.getThreeHandNo()));
		playerText[39].setText(String.format("%.1f", playerVO.getThreeHitNo()));
		double threeHitRate = playerVO.getThreeHitRate();
		if (threeHitRate >= 0)
			playerText[41].setText(String.format("%.1f", threeHitRate * 100));
		else
			playerText[41].setText("-");
		playerText[43].setText(String.format("%.1f",
				playerVO.getPenaltyHandNo()));
		playerText[45]
				.setText(String.format("%.1f", playerVO.getPenaltyHitNo()));
		double penaltyHitRate = playerVO.getPenaltyHitRate();
		if (penaltyHitRate >= 0)
			playerText[47].setText(String.format("%.1f", penaltyHitRate * 100));
		else
			playerText[47].setText("-");
		playerText[49].setText(String.format("%.1f", playerVO.getOffendNo()));
		playerText[51].setText(String.format("%.1f", playerVO.getDefenceNo()));
		playerText[53].setText(String.format("%.1f", playerVO.getBlockNo()));
		playerText[55].setText(String.format("%.1f", playerVO.getMistakesNo()));
		playerText[57].setText(String.format("%.1f", playerVO.getFoulsNo()));
		playerText[59].setText(String.format("%.1f", playerVO.getPoints()));
		playerText[61].setText(String.format("%.1f", playerVO.getEfficiency()));
		playerText[63].setText(String.format("%.1f",
				playerVO.getGmScEfficiency()));
		playerText[65].setText(String.format("%.1f",
				playerVO.getTrueHitRate() * 100));
		playerText[67].setText(String.format("%.1f",
				playerVO.getHitEfficiency()));
		playerText[69].setText(String.format("%.1f",
				playerVO.getRebEfficiency()));
		playerText[71].setText(String.format("%.1f",
				playerVO.getOffenseRebsEfficiency()));
		playerText[73].setText(String.format("%.1f",
				playerVO.getDefenceRebsEfficiency()));
		playerText[75].setText(String.format("%.1f",
				playerVO.getAssistEfficiency() * 100));
		playerText[77].setText(String.format("%.1f",
				playerVO.getStealsEfficiency() * 100));
		playerText[79].setText(String.format("%.1f",
				playerVO.getBlockEfficiency() * 100));
		playerText[81].setText(String.format("%.1f",
				playerVO.getMistakeEfficiency() * 100));
		playerText[83].setText(String.format("%.1f",
				playerVO.getUseEfficiency() * 100));

		jScrollPane.setVisible(false);
		this.remove(jScrollPane);
		this.add(playerMessagePanel);
		playerMessagePanel.setVisible(true);
		playerMessagePanel.repaint();
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
