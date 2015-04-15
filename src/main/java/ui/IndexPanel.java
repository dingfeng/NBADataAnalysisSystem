package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import po.MatchesPO;
import ui.mainui.FrameSize;
import ui.mainui.MyFrame;
import bl.matchbl.MatchController;

public class IndexPanel extends JPanel{

	MatchController mc=new MatchController();
	IndexPanel ip=this;
	
	public IndexPanel(){
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
//		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		setDate();
		
		this.repaint();
	}
	
	void setDate(){
		
		
		String Date=mc.getTodayMatches()[0].getDate();
		String month=Date.split("-")[0];
		String day=Date.split("-")[1];
		int year = 2012;
		
		if(Integer.parseInt(month)<9){
			year++;
		}
		
		// 获得日期
		String date = "今天是" + year + "年" + month + "月" + day + "日";
		
		JLabel today=new JLabel(date);
		today.setBounds(FrameSize.width/3, FrameSize.height/6, FrameSize.width/2, FrameSize.height/20);
		today.setFont(new Font("幼圆",Font.BOLD,40));
		today.setForeground(Color.white);
		
		JLabel lantern=new JLabel();
		lantern.setIcon(new ImageIcon("image/lantern.png"));
		lantern.setBounds(FrameSize.width/8, FrameSize.height/8, 128, 128);
		
		JLabel note=new JLabel("今日比赛：");
		note.setBounds(FrameSize.width/3,FrameSize.height/4,FrameSize.width/10, FrameSize.height/40);
		note.setForeground(Color.white);
		
		MatchesPO[] matches=mc.getTodayMatches();
		JLabel[] match=new JLabel[matches.length];
		for(int i=0;i<match.length;i++){
			match[i]=new JLabel(i+1+"."+matches[i].getDate()+"  |  "+matches[i].getTeam1().getName()+"-"+matches[i].getTeam2().getName()+"  |  "+matches[i].getTeam1().getTotalScores()+"-"+matches[i].getTeam2().getTotalScores());
			match[i].setBounds(FrameSize.width/3,3*FrameSize.height/10+i*FrameSize.height/30,FrameSize.width/5,FrameSize.height/40);
			match[i].setForeground(Color.white);
			match[i].addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					int num=Integer.parseInt(((JLabel)e.getSource()).getText().split("\\.")[0])-1;
					MyFrame.matchpanel.findMatchAccordingMatch(matches,num);
					MyFrame.card.show(MyFrame.mainpanel, "match");
				}
				
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					((JLabel)e.getSource()).setOpaque(true);
					((JLabel)e.getSource()).setBackground(Color.gray);
					ip.repaint();
					
				}
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					((JLabel)e.getSource()).setOpaque(false);
					((JLabel)e.getSource()).repaint();
					ip.repaint();
					
				}

			});
			this.add(match[i]);
		}
//		Vector<String> columnsName = new Vector<String>();
//		columnsName.add("日期");
//		columnsName.add("对阵队伍");
//		columnsName.add("比分");
//		
//		Vector rowimage = new Vector();
//		for(int i=0;i<match.length;i++){
//			Vector data=new Vector();
//			data.add(match[i].getDate());
//			data.add(match[i].getTeam1().getName()+"-"+match[i].getTeam2().getName());
//			data.add(match[i].getTeam1().getTotalScores()+"-"+match[i].getTeam2().getTotalScores());
//			
//			rowimage.add(data);
//		}
//		JTable table=new JTable(rowimage, columnsName);
//		table.setOpaque(false);
//		DefaultTableCellRenderer render = new DefaultTableCellRenderer();   
//        render.setOpaque(false);
//        table.setDefaultRenderer(Object.class,render);  
//		table.isCellEditable(1, 1);
//        
//        JScrollPane jScrollPane = new JScrollPane();
//		
//		jScrollPane.setBounds(FrameSize.width/3,3*FrameSize.height/10,FrameSize.width/5,FrameSize.height/2);
//		jScrollPane.setOpaque(false);
//		jScrollPane.getViewport().setOpaque(false);
//		jScrollPane.setViewportView(table);
//		this.add(jScrollPane);
		
		this.add(note);
		this.add(today);
		this.add(lantern);
	}
	
}
