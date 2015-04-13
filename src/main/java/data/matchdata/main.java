package data.matchdata;

import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;

public class main {
public static void main(String[] args)
{
	MatchData match = new MatchData("C:/NBAData/matches");
	System.out.println(match.getAllMatches().length);
	MatchesPO[] allMatches = match.getAllMatches();
	
	for (MatchesPO po : allMatches)
	{
		MatchTeamPO team1 = po.getTeam1();
		f(team1);
		f(po.getTeam2());
	}
}

public static  void f (MatchTeamPO team)
{
	MatchPlayerPO[] allPlayers = team.getPlayers();
	for (MatchPlayerPO po : allPlayers)
	{
		if (po.getName().equals("Kobe Bryant")){
			System.out.println("sdfs");
		}
	}
}

}
