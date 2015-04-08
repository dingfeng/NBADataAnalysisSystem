package bltest.matchbltest;

import po.MatchesPO;
import bl.matchbl.Match;

public class main {
public static void main(String[] args)
{
	Match match = Match.instance();
	MatchesPO[] allMatches = match.getAllMatches();
	for (int i = 0; i < allMatches.length; i++)
	{
		System.out.println(allMatches[i]);
	}
}
}
