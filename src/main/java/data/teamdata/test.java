package data.teamdata;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import po.TeamPO;


public class test 
{
   public static void main(String[] args)
   {
//	   long old_time = oldTeamTest();
//	   long new_time = newTeamTest();
//	   System.out.println("newTeamTest : "+new_time);
//	   System.out.println("old_time : "+old_time);
	   printTeamName();
   }
   
   public  static String filename = "C:/NBAData";
   
   //test oldMatchTest return the time
   public static long oldTeamTest()
   {
	   TeamData_old teamData = new TeamData_old();
	   long start_time = System.currentTimeMillis();
	   teamData.getAllTeamData();
	   long end_time = System.currentTimeMillis();
	   long margin = end_time - start_time;
	   return margin;
   }
   
   public static long newTeamTest()
   {
	   TeamData newTeam = new TeamData(filename);
	   long start_time = System.currentTimeMillis();
	   newTeam.getAllTeamData();
	   long end_time = System.currentTimeMillis();
	   long margin_time = end_time - start_time;
	   
	   return margin_time;
   }
   public static void printTeamName()
   {
	   TeamData t = new TeamData(filename+"/teams");
	   TeamPO[] teams = t.getAllTeamData();
	   int i = 0;
	   for (TeamPO t1 : teams)
	   {
//		    JFrame frame = new JFrame();
//		    frame.setBackground(Color.blue);
//		    frame.setBounds(0, 0, 500, 500);
//		    JPanel panel = new JPanel();
//
//		    panel.setLayout(null);
//		    
//		    frame.setLayout(new BorderLayout());
//		    frame.add(panel,BorderLayout.CENTER);
//		    frame.setVisible(true);frame.repaint();
//		    Graphics2D g = (Graphics2D) panel.getGraphics();
//		    g.drawImage(Toolkit.getDefaultToolkit().getImage("data/teams/ATL.png"),0,0,100,100,frame);
//		    panel.paint(g);
//		    panel.repaint();
		    break;
	   }
   }
   
}
