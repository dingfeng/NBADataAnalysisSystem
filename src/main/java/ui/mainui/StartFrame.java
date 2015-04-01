package ui.mainui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ui.playerui.PlayerPanel;
import ui.teamui.TeamPanel;

public class StartFrame extends JFrame{
	
	public static void main(String [] args){
		new StartFrame();
	}
	
    
	private Point origin = new Point();
	StartFrame()
	{  
		MyFrame my=new MyFrame("NBA Analysis System");
		my.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e) {
				// 当鼠标按下的时候获得窗口当前的位置
				origin.x = e.getX();
				origin.y = e.getY();
			}
		});
		my.addMouseMotionListener(new MouseMotionAdapter()
		{
			public void mouseDragged(MouseEvent e) {
				// 当鼠标拖动时获取窗口当前位置
				Point p = my.getLocation();
				// 设置窗口的位置
				// 窗口当前的位置 + 鼠标当前在窗口的位置 - 鼠标按下的时候在窗口的位置
				my.setLocation(p.x + e.getX() - origin.x, p.y + e.getY()- origin.y);
			}
		});
		my.setLayout(null);
		JTabbedPane jtp = new JTabbedPane();
		jtp.setBounds(0, FrameSize.height/16+50, FrameSize.width, FrameSize.height*15/16);
		JPanel playerPanel = new PlayerPanel();
		JPanel teamPanel = new TeamPanel();
		jtp.setVisible(false);
		jtp.add("player",playerPanel);
		jtp.add("team",teamPanel);
		jtp.setSelectedIndex(1);
		jtp.setSelectedIndex(0);
		jtp.setVisible(true);
//		my.frame.add(jtp);
		jtp.repaint();
		my.frame.repaint();
		my.repaint();
	}
}