package ui.teamui;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.apache.batik.swing.JSVGCanvas;

import ui.mainui.FrameSize;
import ui.mainui.MyTable;

public class TeamMatchPanel extends JPanel{

	
	public TeamMatchPanel() {
		this.setLayout(null);
		this.setBounds(FrameSize.width / 3, FrameSize.height / 12,
				2 * FrameSize.width / 3, FrameSize.height*7/8- FrameSize.height / 12);
		this.setBackground(FrameSize.backColor);;
		setText();
		setRecentTable();
		setPastTable();
		this.repaint();
		
	} 
	
	void setText(){
		JLabel recent =new JLabel("近期比赛");
		JLabel past=new JLabel("过往查询");
		JLabel teamname =new JLabel("队名");
		JSVGCanvas svgCanvas = new JSVGCanvas();
		
		teamname.setBounds(10, 20, 50,50);
		recent.setBounds(10, 100, 2 * FrameSize.width / 3-20, 50);
		past.setBounds(10, 253, 2 * FrameSize.width / 3-20, 50);
		
		recent.setOpaque(true);
		past.setOpaque(true);
		recent.setBackground(Color.black);
		past.setBackground(Color.black);
		recent.setForeground(Color.white);
		past.setForeground(Color.white);
		teamname.setForeground(Color.white);
		
		this.add(teamname);
		this.add(past);
		this.add(recent);

	}
	
	void setRecentTable(){
		Vector<String> columnsName = new Vector<String>();
		columnsName.add("时间");
		columnsName.add("对阵队伍");
		columnsName.add("比分");
		
		Vector rowimage = new Vector();
		for(int i=0;i<5;i++){
			Vector data=new Vector();
			data.add("ball");
			data.add("lala");
			data.add("df");
			
			rowimage.add(data);
		}
		
		DefaultTableModel table = new DefaultTableModel(rowimage, columnsName);
		MyTable recenttable = new MyTable(table);
		JScrollPane jScrollPane = new JScrollPane(recenttable);
		
		jScrollPane.setBounds(10, 150, 2 * FrameSize.width / 3-20, 103);
		jScrollPane.setOpaque(false);
		jScrollPane.getViewport().setOpaque(false);
		this.add(jScrollPane);
	}
	
	void setPastTable(){
		Vector<String> columnsName = new Vector<String>();
		columnsName.add("时间");
		columnsName.add("对阵队伍");
		columnsName.add("比分");
		
		Vector rowimage = new Vector();
		for(int i=0;i<100;i++){
			Vector data=new Vector();
			data.add("ball");
			data.add("lala");
			data.add("df");
			
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
		this.add(pastjScrollPane);
	}
	
}
