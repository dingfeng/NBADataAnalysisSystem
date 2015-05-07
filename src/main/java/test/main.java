package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class main {
public static void main(String[] args) throws IOException
{
	testAll();
//	outTestCase();
}

static String[] commands = new String[]
		{
		 "--datasource K:/NBAData",
		 "-team -all -total",
		 "-team",
		 "-team -all -n 10",
		 "-team -all -n 3",
		 "-team -hot assist -n 5",
		 "-team -hot score -n 5",
		 "-team -hot rebound -n 5",
		 "-team -hot  blockShot -n 5",
		 "-team -hot  steal -n 5",
		 "-team -hot foul -n 5",
		 "-team -hot fault -n 5",
		 "-team -hot shot -n 5",
		 "-team -hot three -n 5",
		 "-team -hot penalty -n 5",
		 "-team -hot defendRebound -n 9",
		 "-team -hot offendRebound -n 5",
		 "-team -hot  -n 5",
		 "-team -avg -n 5 -sort shot.asc",
		 "-team -total -all -n 10 -sort shot.desc",
		 "-team -total -all -n 30 -sort point.desc",
		 "-team -total -all -n 5 -sort assist.desc",
		 "-team -total -all -n 23 -sort rebound.desc",
		 "-team -total -all -n 10 -sort blockShot.desc",
		 "-team -total -all -n 24 -sort steal.desc",
		 "-team -total -all -n 45 -sort foul.desc",
		 "-team -total -all -n 60 -sort fault.desc",
		 "-team -total -all -n 16 -sort shot.desc",
		 "-team -total -all -n 10 -sort three.desc",
		 "-team -total -all -n 10 -sort penalty.desc",
		 "-team -total -all -n 10 -sort defendRebound.desc",
		 "-team -total -all -n 10 -sort offendRebound.desc",
		 "-team -total -all -n 10 -sort shot.asc",
		 "-team -total -all -n 30 -sort point.asc",
		 "-team -total -all -n 10 -sort assist.asc",
		 "-team -total -all -n 1 -sort rebound.asc",
		 "-team -total -all -n 14 -sort blockShot.asc",
		 "-team -total -all -n 10 -sort steal.asc",
		 "-team -total -all -n 43 -sort foul.asc",
		 "-team -total -all -n 23 -sort fault.asc",
		 "-team -total -all -n 10 -sort shot.asc",
		 "-team -total -all -n 10 -sort three.asc",
		 "-team -total -all -n 34 -sort penalty.asc",
		 "-team -total -all -n 10 -sort defendRebound.asc",
		 "-team -total -all -n 10 -sort offendRebound.asc",
		 "-team -avg -all -n 10 -sort shot.desc",
		 "-team -avg -all -n 30 -sort point.desc",
		 "-team -avg -all -n 5 -sort assist.desc",
		 "-team -avg -all -n 23 -sort rebound.desc",
		 "-team -avg -all -n 10 -sort blockShot.desc",
		 "-team -avg -all -n 24 -sort steal.desc",
		 "-team -avg -all -n 45 -sort foul.desc",
		 "-team -avg -all -n 60 -sort fault.desc",
		 "-team -avg -all -n 16 -sort shot.desc",
		 "-team -avg -all -n 10 -sort three.desc",
		 "-team -avg -all -n 10 -sort penalty.desc",
		 "-team -avg -all -n 10 -sort defendRebound.desc",
		 "-team -avg -all -n 10 -sort offendRebound.desc",
		 "-team -avg -all -n 10 -sort shot.asc",
		 "-team -avg -all -n 30 -sort point.asc",
		 "-team -avg -all -n 10 -sort assist.asc",
		 "-team -avg -all -n 1 -sort rebound.asc",
		 "-team -avg -all -n 14 -sort blockShot.asc",
		 "-team -avg -all -n 10 -sort steal.asc",
		 "-team -avg -all -n 43 -sort foul.asc",
		 "-team -avg -all -n 23 -sort fault.asc",
		 "-team -avg -all -n 10 -sort shot.asc",
		 "-team -avg -all -n 10 -sort three.asc",
		 "-team -avg -all -n 34 -sort penalty.asc",
		 "-team -avg -all -n 10 -sort defendRebound.asc",	
		 "-team -avg -all -n 10 -sort offendRebound.asc",
		 "-team -high -n 5 -sort stealEfficient.asc",
		 "-team -high -n 15 -sort winRate.desc",
		 "-team -high -n 60 -sort offendRound.desc",
		 "-team -high -n 1 -sort offendEfficient.desc",
		 "-team -high -n 4 -sort defendEfficient.desc",
		 "-team -high -n 30 -sort offendReboundEfficient.desc",
		 "-team -high -n 8 -sort defendReboundEfficient.desc",
		 "-team -high -n 32 -sort stealEfficient.desc",
		 "-team -high -n 30 -sort assistEfficient.desc",
		 "-player",
		 "-player -all -n 10",
		 "-player -high -n 10 -sort frequency.desc",
		 "-player -high -n 10 ",
		 "-player -high",
		 "-player -hot rebound -n 5",
		 "-player -hot assist -n 5",
		 "-player -hot score -n 5",
		 "-player -hot rebound -n 10",
		 "-player -hot assist -n 10",
		 "-player -hot score -n 10",
		 "-player -king assist -season",
		 "-player -king score -season",
		 "-player -king rebound -season",
		 "-player -avg -n 5 -filter position.F,league.West,age.<=22",
		 "-player -avg -n 5 -filter position.F,league.East,age.22<X<=25",
		 "-player -avg -n 5 -filter position.F,league.All,age.25<X<=30",
		 "-player -avg -n 5 -filter position.F,league.West,age.>30",
		 "-player -total -all -n 10 -filter position.G,league.West -sort shot.desc",
		 "-player -avg -n 5 -filter position.C,league.West,age.All",
		 "-player -avg -n 5 -filter position.C,league.West",
		 "-player -avg -n 5 -filter league.West",
		 "-player -avg -n 5 -sort point.desc",
		 "-player -avg -n 5 -sort rebound.desc",
		 "-player -avg -n 5 -sort assist.desc",
		 "-player -avg -n 5 -sort blockShot.desc",
		 "-player -avg -n 5 -sort steal.desc",
		 "-player -avg -n 5 -sort foul.desc",
		 "-player -avg -n 5 -sort fault.desc",
		 "-player -avg -n 5 -sort shot.desc",
		 "-player -avg -n 5 -sort three.desc",
		 "-player -avg -n 5 -sort penalty.desc",
		 "-player -avg -n 5 -sort defendRebound.desc",
		 "-player -avg -n 5 -sort offendRebound.desc",
		 "-player -avg -n 5 -sort rebound.asc",
		 "-player -avg -n 5 -sort point.asc",
		 "-player -avg -n 5 -sort rebound.asc",
		 "-player -avg -n 5 -sort assist.asc",
		 "-player -avg -n 5 -sort blockShot.asc",
		 "-player -avg -n 5 -sort steal.asc",
		 "-player -avg -n 5 -sort foul.asc",
		 "-player -avg -n 5 -sort fault.asc",
		 "-player -avg -n 5 -sort shot.asc",
		 "-player -avg -n 5 -sort three.asc",
		 "-player -avg -n 5 -sort penalty.asc",
		 "-player -avg -n 5 -sort defendReboundEfficient.asc",
		 "-player -avg -n 5 -sort offendReboundEfficient.asc",
		 "-player -avg -n 5 -sort rebound.asc",
		 "-player -total -n 5 -sort point.desc",
		 "-player -total -n 5 -sort rebound.desc",
		 "-player -total -n 5 -sort assist.desc",
		 "-player -total -n 5 -sort blockShot.desc",
		 "-player -total -n 5 -sort steal.desc",
		 "-player -total -n 5 -sort foul.desc",
		 "-player -total -n 5 -sort fault.desc",
		 "-player -total -n 5 -sort shot.desc",
		 "-player -total -n 5 -sort three.desc",
		 "-player -total -n 5 -sort penalty.desc",
		 "-player -total -n 5 -sort defendReboundEfficient.desc",
		 "-player -total -n 5 -sort offendReboundEfficient.desc",
		 "-player -total -n 5 -sort rebound.asc",
		 "-player -total -n 5 -sort point.asc",
		 "-player -total -n 5 -sort rebound.asc",
		 "-player -total -n 5 -sort assist.asc",
		 "-player -total -n 5 -sort blockShot.asc",
		 "-player -total -n 5 -sort steal.asc",
		 "-player -total -n 5 -sort foul.asc",
		 "-player -total -n 5 -sort fault.asc",
		 "-player -total -n 5 -sort shot.asc",
		 "-player -total -n 5 -sort three.asc",
		 "-player -total -n 5 -sort penalty.asc",
		 "-player -high -total -n 5 -sort defendReboundEfficient.asc",
		 "-player  -total -n 5 -sort offendReboundEfficient.asc",
		 "-player -total -n 5 -sort rebound.asc",
		 "-player -king rebound -daily",
		 "-player -king assist -daily",
		 "-player -king score -daily",
		 
		};

public static void testAll()
{

//	    Timer  timer = new Timer();
//	    TimerTask task = new TimerTask()
//	    {

//			public void run() {
				Console console = new Console();
				PrintStream out = System.out;
				long start = System.currentTimeMillis();
				for (String c : commands)
				{
					System.out.println(c);
					console.execute(out, c.split(" "));
				}
				long end = System.currentTimeMillis();
				System.out.println("time : "+(end - start));
//			}
	    	
//	    };
//	    timer.schedule(task, 400,6000);
			
}

public static void outTestCase() throws IOException
{
 String temp = null;
 BufferedWriter bw = new BufferedWriter(new FileWriter("k:/1.txt"));
 String[] begins =new String[]{
		 "<?xml version=\"1.0\" encoding=\"UTF-8\"?>",
		 "<autotest>",
		 "    <config>",
		 "        <output>",
		 "            <extension>.out</extension>",
		 "        </output>",
		 "    </config>",
		 "    <tests>"
 };
 String[] end = new String[]
		 {
		 "    </tests>",
		 "</autotest>"
		 };

 for (String s : begins )
 {
	 bw.write(s);
	 bw.newLine();
 }
 
 
 for (int i = 0; i < commands.length; i++)
 {
	 
	 bw.write("        <test-case name=\""+(i+1)+"\">");
	 bw.newLine();
	 bw.write("            <command>"+commands[i]+"</command>");
	 bw.newLine();
	 bw.write("            <description>"+commands[i]+"</description>");
	 bw.newLine();
	 bw.write("            <time-limit>1000</time-limit>");
	 bw.newLine();
	 bw.write("        </test-case>");
	 bw.newLine();
 }
 for (String s : end)
 {
	 bw.write(s);
	 bw.newLine();
 }
 bw.close();
}

public static void testTeam()
{
	String[] commands = new String[]
	{
	 "--datasource C:/NBAData",
	 "-team",
	 "-team -all -n 10",
	 "-team -hot assist -n 5",
	 "-team -avg -n 5 -sort shot.asc",
	 "-team -total -all -n 10 -sort shot.desc",
	 "-team -high -n 5 -sort stealEfficient.asc",
   
	};
	Console console = new Console();
	PrintStream out = System.out;
	for (String c : commands)
	{
		
		console.execute(out, c.split(" "));
	}
	out.close();
}

public static void testPlayer()
{
	String[] commands = new String[]
			{
			"--datasource C:/NBAData",
			"-player",
			"-player -all -n 10",
			"-player -high -n 10 -sort frequency.desc",
			"-player -hot assist -n 5",
			"-player -king score -season",
			"-player -avg -n 5 -filter position.F",
			"-player -total -all -n 10 -filter position.F,league.West -sort shot.desc"
			};
			PrintStream out = System.out;
			Console console = new Console();
			for (String c : commands)
			{
				try{
				console.execute(out, c.split(" "));
				out.flush();
				}catch (Exception e )
				{
					e.printStackTrace();
				}
			}
			out.close();
}

}
