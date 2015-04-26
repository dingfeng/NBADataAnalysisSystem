package test;

import java.io.PrintStream;
import java.util.Arrays;

public class main {
public static void main(String[] args)
{
	testAll();
}

public static void testAll()
{
	String[] commands = new String[]
			{
			 "--datasource K:/NBAData",
			 "-team -all -total",
			 "-team",
			 "-team -all -n 10",
			 "-team -hot assist -n 5",
			 "-team -avg -n 5 -sort shot.asc",
			 "-team -total -all -n 10 -sort shot.desc",
			 "-team -high -n 5 -sort stealEfficient.asc",
			 "-player",
				"-player -all -n 10",
				"-player -high -n 10 -sort frequency.desc",
				"-player -hot assist -n 5",
				"-player -king score -season",
				"-player -avg -n 5 -filter position.F",
				"-player -total -all -n 10 -filter position.F,league.West -sort shot.desc"
			};
			Console console = new Console();
			PrintStream out = System.out;
			long start = System.currentTimeMillis();
			for (String c : commands)
			{
				
				console.execute(out, c.split(" "));
			}
			long end = System.currentTimeMillis();
			System.out.println("time : "+(end - start));
			out.close();
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
