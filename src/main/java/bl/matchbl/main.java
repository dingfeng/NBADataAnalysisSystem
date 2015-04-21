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
//			MatchesPO[] allMatchpos = match.getAllMatches();
//			System.out.println("size of matches : "+ allMatchpos.length);
//			System.out.println("last date : "+allMatchpos[allMatchpos.length-1].getDate());
//				match.update1();
//				MatchesPO[] allMatchpos1 = match.getAllMatches();
//				System.out.println("size of matches : "+ allMatchpos1.length);
//				System.out.println("last date : "+allMatchpos1[allMatchpos1.length-1].getDate());
			Team team = new Team();
			TeamMatchVO[] allTeams = team.getAllAveTeams();
			match.update1();
			for (int i = 0; i < allTeams.length; i++)
			{
				System.out.println(allTeams[i]);
			}
			System.out.println("-------------------------");
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
