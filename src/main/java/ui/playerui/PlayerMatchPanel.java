package ui.playerui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import po.MatchesPO;
import ui.mainui.FrameSize;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import bl.matchbl.MatchController;

public class PlayerMatchPanel extends JPanel{

	MatchController matchController = new MatchController();
	String playerName;
	
	public PlayerMatchPanel(String playerName){
		this.setLayout(null);
		this.setBounds(FrameSize.width / 3, FrameSize.height / 12,
				2 * FrameSize.width / 3, FrameSize.height*7/8- FrameSize.height / 12);
		this.setBackground(FrameSize.backColor);
		this.playerName=playerName;
	}
	
	/**设置界面提示文字*/
	void setText(){
		JLabel recent =new JLabel("近期比赛");
		JLabel past=new JLabel("过往查询");
		JLabel playername =new JLabel("球员");
		JLabel player =new JLabel();
		player.setText(playerName);
		
		playername.setBounds(10, 20, 50,50);
		player.setBounds(80, 20, 50,50);
		recent.setBounds(10, 100, 2 * FrameSize.width / 3-20, 50);
		past.setBounds(10, 253, 2 * FrameSize.width / 3-20, 50);
		
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

	/**近期比赛*/
	void setRecentTable(){
		Vector<String> columnsName = new Vector<String>();
		columnsName.add("日期");
		columnsName.add("对阵队伍");
		columnsName.add("比分");
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
		MatchesPO[] match=matchController.getRecentPlayerMatches(playerName,5);
		Vector data = new Vector();
		for(int i=0;i<match.length;i++){
			Vector rowdata=new Vector();
			rowdata.add(match[i].getDate());
			rowdata.add(match[i].getTeam1().getName()+"-"+match[i].getTeam2().getName());
			rowdata.add(match[i].getTeam1().getTotalScores()+"-"+match[i].getTeam2().getTotalScores());
			
			data.add(data);
		}
		
		DefaultTableModel table = new DefaultTableModel(data, columnsName);
		MyTable recenttable = new MyTable(table);
		JScrollPane jScrollPane = new JScrollPane(recenttable);
		
		jScrollPane.setBounds(10, 150, 2 * FrameSize.width / 3-20, 103);
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);
		
		recenttable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					
					MyFrame.matchpanel.findMatchAccordingMatch(match,recenttable.getSelectedRow());
					MyFrame.card.show(MyFrame.mainpanel, "match");
				}
			}

		});
		
		this.add(jScrollPane);
	}
}
