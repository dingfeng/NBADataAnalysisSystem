package bl.matchbl;

import java.util.Timer;
import java.util.TimerTask;

import bl.teambl.Team;
import po.MatchesPO;
import vo.TeamMatchVO;

public class main {
 public static void main(String[] args)
 {
	 Match match = Match.instance();
	 Timer timer = new Timer();
	 TimerTask task = new TimerTask()
	 {

		public void run() 
		{
		 MatchesPO[] pos = match.getTodayMatches();match.update1();
		 System.out.println("today :　");
		 for (MatchesPO po : pos)
		 {
			 
			 System.out.println(po.getDate());
		 }
		}
	 };
	 timer.schedule(task, 2,5000);
//	 Match match = Match.instance();
//	 MatchesPO[] allMatches = match.getAllMatches();
//	 for (int i = 0 ; i < allMatches.length; i++)
//	 {
//		 System.out.println(allMatches[i].getDate());
//	 }
//	 System.out.println(allMatches.length);
 }
}
