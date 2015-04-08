package data.matchdata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import po.MatchesPO;

public class main {
public static void main(String[] args) throws IOException
{ 
	
//	long result = 0;
//	for ( int i = 0; i < 30; i++)
//		result += testMatchTime_new();
//	System.out.println("old : "+result /30);
//      System.out.println("old : "+ testMatchTime_old());
//      System.out.println("old : "+ testMatchTime_old());
//       System.out.println("new : "+testMatchTime_new());
	System.out.println(testMatchTime_new());
}

public static void print(File[] files)
{
	for (File f : files)
	{
		System.out.print(f.getName());
		System.out.print(";");
	}
}

public static void testMatchPO() throws IOException
{
	StringBuilder  sb = new StringBuilder();
	MatchData data = new MatchData("G:/NBAData");
    PrintWriter p = new PrintWriter(new FileWriter("G:/test.txt"));
    MatchesPO[] matches = data.getAllMatches();
    for (MatchesPO m : matches)
    {
    	p.append(m.getDate()+"\n");
    }
	p.close();
	MatchesPO[] today_matches = data.getTodayMatches();
	for (MatchesPO p1 : today_matches)
	{
		System.out.println(p1);
	}
	System.out.println(matches.length);
}

public static long  testMatchTime_new()
{
	
	long start = System.currentTimeMillis();
	MatchData data = new MatchData("G:/NBAData");
    MatchesPO[] matches = 	data.getAllMatches();
	long end = System.currentTimeMillis();
	return end - start;
}

public static long testMatchTime_old()
{
	
	long start = System.currentTimeMillis();
	MatchData_old data = new MatchData_old();
	data.getAllMatchData();
	long end = System.currentTimeMillis();
	return end - start;
}

public static void vExecute()
{
	new Thread()
	{
		PrintWriter print;
		public void run()
		{
			
			try {
			 print = new PrintWriter(new FileWriter("G:/match_log.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			MatchData data = new MatchData("G:/NBAData");
			
		   for (int i = 0; i < 10000; i++)
		   {
			   try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			   if (i != 0 )
			   {
				   if (!data.changed()) continue;
			   }
			   MatchesPO[] ms = null;
			   if ( i ==0 )
			   {
			   ms = data.getAllMatches();
			   if (ms == null)
				   continue;
			   print.println("all matches : ");
			   for (MatchesPO p : ms)
			   {
				   print.println(p.toString());
			   }
			   }
			   ms = data.getTodayMatches();
			   if (ms == null) continue;
			   print.println("today : ");
			   for (MatchesPO p : ms)
			   {
				   print.println(p.toString());
			   }
			   print.flush();
		   }
		   print.close();
		}
	}.start();
}

}
