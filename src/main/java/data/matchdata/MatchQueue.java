package data.matchdata;

import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;

public class MatchQueue 
{
 final static int SIZE = 6000;
 String season;
 MatchesPO[] matches;
 int lenth = -1;
 int newStart = -1;
 public MatchQueue(String season)
 {
	 this.season = season;
	 matches = new MatchesPO[SIZE];
	 newStart = lenth + 1;
 }
 public MatchQueue()
 {
	 this("");
 }
 
 public MatchesPO[] getAllMatches()
 {
	 if (lenth == -1)
	 {
		 return null;
	 }
	 MatchesPO[] matchespos = new MatchesPO[lenth+1];
     for (int i = 0; i <= lenth; i++)
     {
    	 matchespos[i] = matches[i];
     }
     return matchespos;
 }
 
 public void enQueue(MatchesPO match)
 {
	 if (match.getTeam1().getPlayers().length != 0)
	  matches[++lenth] = match; 
 }
// 
// 
// public MatchesPO[] getRecentTeamMatches(String teamName, int num)
// {
//    int count = -1;
//	int[] indexs = new int[num];
//	int size = lenth;
//	while (size > -1)
//	{
//		if (hasTeam(teamName , matches[size]))
//		{
//			indexs[++count] = size;
//		}
//		--size;
//	}
//	if (count == -1)
//		return null;
//	MatchesPO[] matchespos = new MatchesPO[count+1];
//	for (int i = 0 ; i < count+1; i++)
//	{
//		matchespos[i] = matches[indexs[i]];
//	}
//	
//	return matchespos;
// }
// 
// public MatchesPO[] getRecentPlayerMatches(String playerName, int num)
// {
//	int count = -1;
//	int[] indexs = new int[num];
//	int size = lenth;
//	while (size > -1)
//	{
//		if (hasPlayer(playerName , matches[size]))
//		{
//			indexs[++count] = size;
//		}
//		--size;
//	}
//	if (count == -1)
//	{
//		return null;
//	}
//	MatchesPO[] matchespos = new MatchesPO[count+1];
//	for (int i = 0 ; i < count+1; i++)
//	{
//		matchespos[i] = matches[indexs[i]];
//	}
//	
//	return matchespos;
// }
// 
 private boolean hasPlayer(String playerName, MatchesPO match)
 {
	MatchTeamPO team1 = match.getTeam1();
	MatchTeamPO team2 = match.getTeam2();
	MatchPlayerPO[] players1 = team1.getPlayers();
	MatchPlayerPO[] players2 = team2.getPlayers();
	boolean result = hasPlayer(players1,playerName) 
			|| hasPlayer(players2,playerName);
	return result;
 }
 
 private boolean hasPlayer(MatchPlayerPO[] players, String playerName)
 {
	 boolean result = false;
	 for (MatchPlayerPO player : players)
	 {
		 if (player.getName().equals(playerName))
		 {
			 result = true;
			 break;
		 }
	 }
	 return result;
 }
 
 private boolean hasTeam(String teamName, MatchesPO match)
 {

	MatchTeamPO team1 = match.getTeam1();
	MatchTeamPO team2 = match.getTeam2();
	boolean result 
	= team1.getName().equals(teamName) ||
	team2.getName().equals(teamName);
	return result;
	
 }
 
 public MatchesPO[] getNewMatches()
 {
	
	int size = lenth + 1 - newStart;
	if (size == 0)
	{
		return null;
	}
	MatchesPO[]  newMatches = new MatchesPO[size];
	for (int i = newStart; i <= lenth; i++)
	{
		newMatches[i - newStart] = matches[i];
	}
	newStart = lenth + 1;
	return newMatches;
 }
  
}
