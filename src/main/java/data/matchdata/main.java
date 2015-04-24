package data.matchdata;

import java.util.Timer;
import java.util.TimerTask;

import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;

public class main {
public static void main(String[] args)
{
	MatchData match = new MatchData("C:/NBAData/matches");
	Timer timer = new Timer();
	TimerTask task = new TimerTask()
	{
		int len = 0;
		public void run() {
			   match.updateData();
			   MatchesPO[] matches = match.getAllMatches();
			   if (matches !=  null)
			   {
				   len += matches.length;
				   System.out.println("len : "+len);
			   }
		}
		
	};
    timer.schedule(task, 1000,4000);	
}


}
