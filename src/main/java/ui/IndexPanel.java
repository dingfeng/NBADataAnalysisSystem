package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;
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
	JLabel[] match;
	JLabel today=new JLabel();
	MatchesPO[] matches;
	public IndexPanel(){
		this.setLayout(null);
		this.setBounds(0, 0, FrameSize.width, FrameSize.height * 7 / 8);
//		this.setBackground(FrameSize.backColor);
		this.setOpaque(false);
		setDate();
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){

			public void run() {
				try{
				 update();
				}catch(Exception e){}
			}
			
		}, 10000,7000);
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
		
		today.setText(date);
		today.setBounds(FrameSize.width/3, FrameSize.height/6, FrameSize.width/2, FrameSize.height/20);
		today.setFont(new Font("微软雅黑",Font.BOLD,40));
		today.setForeground(Color.white);
	
		JLabel lantern=new JLabel();
		lantern.setIcon(new ImageIcon("image/indexpic.png"));
		lantern.setBounds(FrameSize.width/8, FrameSize.height/8, 128, 128);
		
		JLabel note=new JLabel("今日比赛：");
		note.setFont(new Font("微软雅黑",Font.PLAIN,18));
		note.setBounds(FrameSize.width/3,FrameSize.height/4,FrameSize.width/10, FrameSize.height/40);
		note.setForeground(Color.white);
		
		 matches=mc.getTodayMatches();
		match=new JLabel[20];
		for(int n=0;n<20;n++){
			match[n]=new JLabel();
		}
		int labelnum=10;
		if(matches.length<20){
			labelnum=matches.length;
		}
		for(int i=0;i<labelnum;i++){
			match[i].setText(i+1+"."+matches[i].getDate()+"  |  "+matches[i].getTeam1().getName()+"-"+matches[i].getTeam2().getName()+"  |  "+matches[i].getTeam1().getTotalScores()+"-"+matches[i].getTeam2().getTotalScores());
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
		if(matches.length<10){
			for(int j=matches.length;j<10;j++){
				match[j].setVisible(false);
			}
		}

		
		this.add(note);
		this.add(today);
		this.add(lantern);
	}

	public void update(){
		mc.update1();
		String Date=mc.getTodayMatches()[0].getDate();
		String month=Date.split("-")[0];
		String day=Date.split("-")[1];
		int year = 2012;
		
		if(Integer.parseInt(month)<9){
			year++;
		}
		
		// 获得日期
		String date = "今天是" + year + "年" + month + "月" + day + "日";
		today.setText(date);
		matches=mc.getTodayMatches();
		int labelnum=20;
		if(matches.length<20){
			labelnum=matches.length;
		}
		for(int i=0;i<labelnum;i++){
			match[i].setText(i+1+"."+matches[i].getDate()+"  |  "+matches[i].getTeam1().getName()+"-"+matches[i].getTeam2().getName()+"  |  "+matches[i].getTeam1().getTotalScores()+"-"+matches[i].getTeam2().getTotalScores());
			match[i].setVisible(true);
			this.add(match[i]);
		}
		if(matches.length<20){
			for(int j=matches.length;j<20;j++){
				match[j].setVisible(false);
				
			}
		}
		this.repaint();
		this.validate();
	}

}
