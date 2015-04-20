package ui.mainui;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import ui.playerui.PlayerPanel;
import ui.teamui.TeamPanel;

public class StartFrame extends JFrame{
	
	public static void main(String [] args){
		
		new StartFrame();
	}
	
    
	private Point origin = new Point();
	public StartFrame()
	{  
		
		MyFrame my=new MyFrame();
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
		my.frame.repaint();
		my.repaint();
	}
}
