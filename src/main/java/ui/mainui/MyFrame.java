package ui.mainui;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.HotPanel;
import ui.MatchPanel;
import ui.playerui.PlayerPanel;
import ui.teamui.TeamPanel;

public class MyFrame extends JFrame {
	
//	public static void main(String[] args) {
//		MyFrame m=new MyFrame("NBA Analysis System");
//	}
	boolean flag = false;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String titleText = new String();
	public JPanel mainPanel;
	JLabel frame;
	MyFrame thisFrame = this;
	JPanel mainpanel=new JPanel();
	TeamPanel teampanel=new TeamPanel();
	PlayerPanel playerpanel=new PlayerPanel();
	HotPanel hotpanel=new HotPanel();
	MatchPanel matchpanel=new MatchPanel();
	
	int width=900;
	int height=600;
	public MyFrame(String titleText){
		this.setUndecorated(true);
	    
//		this.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		this.titleText = titleText;
		
		

		setFrame();
        
		this.repaint();
	}
	
	void setFrame(){
		
		ImageIcon image = new ImageIcon("image/NBAbackground.png");
		frame = new JLabel(image);
		frame.setIcon(image);
		frame.setBackground(new Color(0, 0, 0, 0));
		
		mainpanel.setBounds(0, FrameSize.height/6, FrameSize.width, FrameSize.height*15/16);
		mainpanel.setOpaque(false);
		setExit();
		setMini();
		setTitle();
		setHeadButton();
		int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setBounds((screenWidth-width)/2, (screenHeight-height)/2, FrameSize.width, FrameSize.height);
		this.setTitle("NBA Analysis System");
		this.add(frame);
		frame.add(mainpanel);
		this.setVisible(true);
		
	}
	
	public void setTitle(){
		JLabel title = new JLabel(new ImageIcon("image/basketball.png"));
//		title.setFont(new Font("微软雅黑", Font.BOLD, 17));
		title.setBounds(5, -15, 24, 59);
		frame.add(title);
	}
	
	void setHeadButton(){
		JButton index =new JButton(new ImageIcon("image/index.png"));
		JButton playerbutton=new JButton(new ImageIcon("image/player.png"));
		JButton teambutton=new JButton(new ImageIcon("image/team.png"));
		JButton hotbutton=new JButton(new ImageIcon("image/hot.png"));
		JButton matchbutton=new JButton(new ImageIcon("image/match.png"));
		
		index.setBounds(10, 30,50,50);
		playerbutton.setBounds(80, 30, 50, 50);
		teambutton.setBounds(150, 30, 50, 50);
		hotbutton.setBounds(220,30,50, 50);
		matchbutton.setBounds(290, 30, 50, 50);
		
		index.setBackground(Color.black);
		playerbutton.setBackground(Color.black);
		teambutton.setBackground(Color.black);
		hotbutton.setBackground(Color.black);
		matchbutton.setBackground(Color.black);
		
		index.addActionListener(e->setIndex());
		playerbutton.addActionListener(e->setPlayer());
		teambutton.addActionListener(e->setTeam());
		hotbutton.addActionListener(e->setHot());
		matchbutton.addActionListener(e->setMatch());
		
		frame.add(index);
		frame.add(playerbutton);
		frame.add(teambutton);
		frame.add(hotbutton);
		frame.add(matchbutton);
	}
	
	void setIndex(){
		mainpanel.remove(teampanel);
		mainpanel.remove(playerpanel);
		mainpanel.remove(matchpanel);
		mainpanel.remove(hotpanel);
		mainpanel.repaint();
	}
	
	void setPlayer(){
		mainpanel.remove(teampanel);
		mainpanel.add(playerpanel);
		mainpanel.repaint();
	}
	
	void setTeam(){
		mainpanel.remove(playerpanel);
		mainpanel.remove(matchpanel);
		mainpanel.remove(hotpanel);
		mainpanel.add(teampanel);
		mainpanel.repaint();
	}
	
	void setHot(){
		mainpanel.remove(playerpanel);
		mainpanel.remove(teampanel);
		mainpanel.remove(matchpanel);
		mainpanel.add(hotpanel);
		mainpanel.repaint();
	}
	
	void setMatch(){
		mainpanel.remove(playerpanel);
		mainpanel.remove(teampanel);
		mainpanel.remove(hotpanel);
		mainpanel.add(matchpanel);
		mainpanel.repaint();
	}
	
	void setExit(){
		JLabel el = new JLabel();
		ImageIcon image = new ImageIcon("image/close.png");
		el.setIcon(image);
		el.setBounds(width-50, -8, 40, 40);
		el.addMouseListener(new ExitButtonAction());
		frame.add(el);
		//this.add(el);
	}
	
	void setMini(){
		JLabel el = new JLabel();
		ImageIcon image = new ImageIcon("image/mini.png");
		el.setIcon(image);
		el.setBounds(width-90, -8, 40, 40);
		el.addMouseListener(new MiniButtonAction());
		frame.add(el);
	}
	
	void addElements(){
		/*JLabel psisL = new JLabel();
		psisL.setBounds(150, 80, 400, 300);
		psisL.setIcon(new ImageIcon("psis.png"));
		mainPanel.add(psisL);
		repaint();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		psisL.setLocation(350, 80);
		repaint();*/
	}
	
	class ExitButtonAction implements MouseListener{
		public void mouseClicked(MouseEvent arg0) {
			thisFrame.dispose();
			System.exit(DISPOSE_ON_CLOSE);
		}
		public void mouseEntered(MouseEvent arg0) {
		}
		public void mouseExited(MouseEvent arg0) {
		}
		public void mousePressed(MouseEvent arg0) {
		}
		public void mouseReleased(MouseEvent arg0) {
		}	
	}
	
	class MiniButtonAction implements MouseListener{
		public void mouseClicked(MouseEvent e) {
			thisFrame.setExtendedState(JFrame.ICONIFIED);;
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}
		public void mousePressed(MouseEvent e) {
		}
		public void mouseReleased(MouseEvent e) {
		}
	}

}

