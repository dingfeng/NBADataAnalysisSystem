package ui.mainui;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.HotPanel;
import ui.IndexPanel;
import ui.MatchPanel;
import ui.playerui.PlayerPanel;
import ui.teamui.TeamPanel;
import ui.mainui.MyButton;

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
	JPanel frame = new JPanel()
	{
		protected void paintComponent(Graphics g) {  
            java.awt.Image img = Toolkit.getDefaultToolkit().getImage("image/kobe1.jpg");  
            g.drawImage(img, 0, 0, this.getWidth(),this.getHeight(),this);  
            // 细致渲染、绘制背景，可控制截取图片，显示于指定的JPanel位置  
//          g.drawImage(img, 0, 0, frameSize.width, frameSize.height,   
//                      0, 0, icon.getIconWidth(), icon.getIconHeight(), icon.getImageObserver());  
        }  
	};
	MyFrame thisFrame = this;
	public static JPanel mainpanel=new JPanel();
	public static CardLayout card = new CardLayout();
	
	IndexPanel indexpanel=new IndexPanel();
	public static TeamPanel teampanel=new TeamPanel();
	public static PlayerPanel playerpanel=new PlayerPanel();
	public static HotPanel hotpanel=new HotPanel();
	public static MatchPanel matchpanel=new MatchPanel();
	
	JLabel locationlable=new JLabel();
	
	public MyFrame(){
		this.setUndecorated(true);
	    
//		this.setBackground(new Color(0.0f,0.0f,0.0f,0.0f));
		//修改图标
		Image image = Toolkit.getDefaultToolkit().getImage("image/basketball.png");
		this.setIconImage(image);
		

		setFrame();
        
		this.repaint();
	}
	
	void setFrame(){
		
		ImageIcon image = new ImageIcon("image/NBAbackground.png");
		
		frame.setLayout(null);
		frame.setBackground(new Color(0, 0, 0, 0));
		
		mainpanel.setBounds(0, FrameSize.height/8, FrameSize.width, FrameSize.height*7/8);
		mainpanel.setLayout(card);
		mainpanel.add(indexpanel,"index");
		mainpanel.add(playerpanel,"player");
		mainpanel.add(teampanel,"team");
		mainpanel.add(matchpanel,"match");
		mainpanel.add(hotpanel,"hot");
		mainpanel.setOpaque(false);
		setExit();
		setMini();
		setTitle();
		setHeadButton();
		int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setBounds((screenWidth-FrameSize.width)/2, (screenHeight-FrameSize.height)/2, FrameSize.width, FrameSize.height);
		this.setTitle("NBA Analysis System");
		CardLayout card = new CardLayout();
		this.setLayout(new BorderLayout());
		this.add(frame, BorderLayout.CENTER);
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
		JButton index =new MyButton(new ImageIcon("image/index.png"),Color.black,Color.DARK_GRAY);
		JButton playerbutton=new MyButton(new ImageIcon("image/player.png"),Color.black,Color.DARK_GRAY);
		JButton teambutton=new MyButton(new ImageIcon("image/team.png"),Color.black,Color.DARK_GRAY);
		JButton hotbutton=new MyButton(new ImageIcon("image/hot.png"),Color.black,Color.DARK_GRAY);
		JButton matchbutton=new MyButton(new ImageIcon("image/match.png"),Color.black,Color.DARK_GRAY);
		JButton helpbutton=new MyButton(new ImageIcon("image/help.png"),Color.black,Color.DARK_GRAY);

		
		index.setBounds(10, 30,50,50);
		hotbutton.setBounds(80, 30, 50, 50);
		playerbutton.setBounds(150, 30, 50, 50);
		teambutton.setBounds(220,30,50, 50);
		matchbutton.setBounds(290, 30, 50, 50);
		helpbutton.setBounds(360, 30, 50, 50);		
		
		locationlable.setText("当前位置：主页");
		locationlable.setOpaque(false);
		locationlable.setForeground(Color.white);
		locationlable.setBounds(FrameSize.width/2,FrameSize.height/25,150,30);
		locationlable.setFont(new Font("微软雅黑",Font.BOLD,20));
		
		index.addActionListener(e->setIndex());
		playerbutton.addActionListener(e->setPlayer());
		teambutton.addActionListener(e->setTeam());
		hotbutton.addActionListener(e->setHot());
		matchbutton.addActionListener(e->setMatch());
		
		frame.add(locationlable);
		frame.add(helpbutton);
		frame.add(index);
		frame.add(playerbutton);
		frame.add(teambutton);
		frame.add(hotbutton);
		frame.add(matchbutton);
	}
	
	void setIndex(){
		card.show(mainpanel, "index");
		locationlable.setText("当前位置：主页");
	}
	
	void setPlayer(){
		card.show(mainpanel, "player");
		locationlable.setText("当前位置：球员");
	}
	
	public void setTeam(){
		card.show(mainpanel, "team");
		locationlable.setText("当前位置：球队");
	}
	
	void setHot(){
		card.show(mainpanel, "hot");
		locationlable.setText("当前位置：热点");
	}
	
	void setMatch(){
		card.show(mainpanel, "match");
		locationlable.setText("当前位置：比赛");
	}
	
	void setExit(){
		JButton el = new MyButton(new ImageIcon("image/close.png"),Color.black,Color.gray);
//		JLabel el = new JLabel();
//		ImageIcon image = new ImageIcon("image/close.png");
//		el.setIcon(image);
		el.setBounds(FrameSize.width-50, -8, 40, 40);
		el.addMouseListener(new ExitButtonAction());
		frame.add(el);
		//this.add(el);
	}

	void setMini(){
		JButton el = new MyButton(new ImageIcon("image/mini.png"),Color.black,Color.gray);
//		JLabel el = new JLabel();
//		ImageIcon image = new ImageIcon("image/mini.png");
//		el.setIcon(image);
		el.setBounds(FrameSize.width-90, -8, 40, 40);
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

