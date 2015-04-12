package ui.teamui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import po.MatchesPO;
import ui.mainui.FrameSize;
import ui.mainui.MyFrame;
import ui.mainui.MyTable;
import bl.matchbl.MatchController;

public class TeamMatchPanel extends JPanel{

	MatchController mc=new MatchController();
	String teamName;
	
	public TeamMatchPanel(String teamname) {
		this.setLayout(null);
		this.setBounds(FrameSize.width / 3, FrameSize.height / 12,
				2 * FrameSize.width / 3, FrameSize.height*7/8- FrameSize.height / 12);
		this.setBackground(FrameSize.backColor);;
		this.teamName=teamname;
		
		setText();
		setRecentTable();
		setPastTable();
		this.repaint();
		
	} 
	/**设置界面提示文字*/
	void setText(){
		JLabel recent =new JLabel("近期比赛");
		JLabel past=new JLabel("过往查询");
		JLabel teamname =new JLabel("队名");
		JLabel team =new JLabel();
		team.setText(teamName);
		
		teamname.setBounds(10, 20, 50,50);
		team.setBounds(80, 20, 50,50);
		recent.setBounds(10, 100, 2 * FrameSize.width / 3-20, 50);
		past.setBounds(10, 253, 2 * FrameSize.width / 3-20, 50);
		
		recent.setOpaque(true);
		past.setOpaque(true);
		recent.setBackground(Color.black);
		past.setBackground(Color.black);
		recent.setForeground(Color.white);
		past.setForeground(Color.white);
		teamname.setForeground(Color.white);
		team.setForeground(Color.white);
		
		this.add(team);
		this.add(teamname);
		this.add(past);
		this.add(recent);

	}
	/**近期比赛*/
	void setRecentTable(){
		Vector<String> columnsName = new Vector<String>();
		columnsName.add("日期");
		columnsName.add("对阵队伍");
		columnsName.add("比分");
		MatchesPO[] match=mc.getRecentTeamMatches(teamName, 5);
		Vector rowimage = new Vector();
		for(int i=0;i<5;i++){
			Vector data=new Vector();
			data.add(match[i].getDate());
			data.add(match[i].getTeam1().getName()+"-"+match[i].getTeam2().getName());
			data.add(match[i].getTeam1().getTotalScores()+"-"+match[i].getTeam2().getTotalScores());
			
			rowimage.add(data);
		}
		
		DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
		MyTable recenttable = new MyTable(table);
		JScrollPane jScrollPane = new JScrollPane(recenttable);
		
		jScrollPane.setBounds(10, 150, 2 * FrameSize.width / 3-20, 103);
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);
		
		recenttable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					MatchesPO[] onematch=new MatchesPO[1];
					onematch[0]=match[recenttable.getSelectedRow()];
					MyFrame.matchpanel.setMatchTable(onematch);
					MyFrame.card.show(MyFrame.mainpanel, "match");
				}
			}

		});
		
		this.add(jScrollPane);
	}
	/**过往查询*/
	void setPastTable(){
		Vector<String> columnsName = new Vector<String>();
		columnsName.add("日期");
		columnsName.add("对阵队伍");
		columnsName.add("比分");
		
		MatchesPO[] match=mc.getTeamMatches(teamName);
		Vector rowimage = new Vector();
		for(int i=0;i<match.length;i++){
			Vector data=new Vector();
			data.add(match[i].getDate());
			data.add(match[i].getTeam1().getName()+"-"+match[i].getTeam2().getName());
			data.add(match[i].getTeam1().getTotalScores()+"-"+match[i].getTeam2().getTotalScores());
			
			rowimage.add(data);
		}
		
		DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
		MyTable pasttable = new MyTable(table);
		JScrollPane pastjScrollPane = new JScrollPane(pasttable);
		pastjScrollPane
		.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		pastjScrollPane.setBounds(10, 303, 2 * FrameSize.width / 3-20, FrameSize.height*7/8- FrameSize.height / 12-320);
		pastjScrollPane.setOpaque(false);
		pastjScrollPane.getViewport().setOpaque(false);
		
		pasttable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					MatchesPO[] onematch=new MatchesPO[1];
					onematch[0]=match[pasttable.getSelectedRow()];
					MyFrame.matchpanel.setMatchTable(onematch);
					MyFrame.card.show(MyFrame.mainpanel, "match");
				}
			}

		});
		
		this.add(pastjScrollPane);
	}
	
}
