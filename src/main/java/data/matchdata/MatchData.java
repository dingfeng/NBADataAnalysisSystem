package data.matchdata;

import java.io.File;
import java.util.Arrays;

import po.MatchesPO;

public class MatchData 
{
  private  String matchesPath;
  private  MatchQueue matches;
  private final static int MAX_SIZE = 6000;
  private final static int MAX_TODAY_MATCH = 20;
  private  MatchesPO[] todayMatches = new MatchesPO[MAX_TODAY_MATCH];
  private  int todayMatchesLength = -1;
  private  int  length = -1;
  private String today;
  public  MatchData(String path)
  {
	  matchesPath = path + "/matches";
	  matches = new MatchQueue();
	  initData();
  }
  
  private void initData()
  {
	  File file = new File(matchesPath);
	  File[] file_children = file.listFiles();
	  Arrays.sort(file_children);
	  File file_tmp = file_children[file_children.length-1];
	  String last_file_name = file_tmp.getName();
	  //13-14_01-01
	  today = last_file_name.substring(0, 11);
	  int i = length - 1;
	  MatchesPO matchpo = null;
	  while (file_tmp.getName().startsWith(today))
	  {
		  matchpo = getMatchPO(file_tmp);
		  todayMatches[++todayMatchesLength] = matchpo;
		  matches.enQueue(matchpo);
		  if ( i == 0)
		  {
			  break;
		  }
		  file_tmp = file_children[--i];
	  }
	  
	  if (i != 0)
	  {
		  matches.enQueue(matchpo);
	  }
	  while (i > -1)
	  {
		 matches.enQueue(getMatchPO(file_children[i--]));
	  }
	  
  }
  
  private void updateData()
  {
   
  }
  
  private MatchesPO getMatchPO(File file)
  {
	return null;
  }
  
  public MatchesPO[] getTodayMatches()
  {
	  updateData();
	return  todayMatches;
  }
  
  public MatchesPO[] getAllMatches()
  {
	  updateData();
	return  matches.getAllMatches();
  }
  
  public MatchesPO[] getRecentPlayerMatches(String playername, int num)
  {
	  return matches.getRecentPlayerMatches(playername, num);
  }
  
  public MatchesPO[] getRecentTeamMatches(String teamname, int num)
  {
	  return matches.getRecentTeamMatches(teamname, num);
  }
  
  
}
