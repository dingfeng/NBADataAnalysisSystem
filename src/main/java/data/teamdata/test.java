package data.teamdata;

import po.TeamPO;


public class test 
{
   public static void main(String[] args)
   {
//	   long old_time = oldTeamTest();
//	   long new_time = newTeamTest();
//	   System.out.println("newTeamTest : "+new_time);
//	   System.out.println("old_time : "+old_time);
	   printTeamName();
   }
   
   public  static String filename = "G:/NBAData/teams";
   
   //test oldMatchTest return the time
   public static long oldTeamTest()
   {
	   TeamData_old teamData = new TeamData_old();
	   long start_time = System.currentTimeMillis();
	   teamData.getAllTeamData();
	   long end_time = System.currentTimeMillis();
	   long margin = end_time - start_time;
	   return margin;
   }
   
   public static long newTeamTest()
   {
	   TeamData newTeam = new TeamData(filename);
	   long start_time = System.currentTimeMillis();
	   newTeam.getAllTeamData();
	   long end_time = System.currentTimeMillis();
	   long margin_time = end_time - start_time;
	   
	   return margin_time;
   }
   public static void printTeamName()
   {
	   TeamData t = new TeamData(filename);
	   TeamPO[] teams = t.getAllTeamData();
	   for (TeamPO t1 : teams)
	   {
		   System.out.println("\""+t1.getNameAbridge()+"\",");
	   }
   }
   
}
