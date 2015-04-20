package ui;

public class HelpUtil {
 public static void startHelp()
 {
	 String projectDir = System.getProperty("user.dir");
	 projectDir.replace("\\", "/");
	 String url = "file:\\"+projectDir+"/help_doc/index.htm";
	 BareBonesBrowserLaunch.openURL(url);
 }
 
 public static void main(String[] args)
 {
	 startHelp();
 }
 /*
  * helpbutton.addActionListener(new ActionListener()
  * {
  * public void actionPerformed(ActionEvent e)
  * {
  * HelpUtil.startHelp();
  * }
  * }
  * )
  */
}
