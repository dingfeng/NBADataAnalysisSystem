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
        int matchNum = match.matchNum() ;
		public void run() 
		{
			match.update();
			if (matchNum != match.matchNum())
			{
				matchNum = match.matchNum();
				System.out.println("matchNum : "+matchNum);
			}
		}
	 };
	 timer.schedule(task, 1000,4000);
//	 Match match = Match.instance();
//	 MatchesPO[] allMatches = match.getAllMatches();
//	 for (int i = 0 ; i < allMatches.length; i++)
//	 {
//		 System.out.println(allMatches[i].getDate());
//	 }
//	 System.out.println(allMatches.length);
 }
}
