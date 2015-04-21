package bl.matchbl;

import java.util.Timer;
import java.util.TimerTask;

public class main {
 public static void main(String[] args)
 {
	 Match match = Match.instance();
	 Timer timer = new Timer();
	 TimerTask task = new TimerTask()
	 {

		public void run() 
		{
			if (match.changed())
			{
				match.update();
				System.out.println("size of matches : "+ match.getAllMatches().length);
			}
		}
	 };
	 timer.schedule(task, 2,5000);
 }
}
