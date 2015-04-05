package bl.matchbl;

import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;
import dataservice.matchdataservice.MatchDataService;
import po.MatchesPO;

public class Match implements MatchDataService
{
	MatchDataService match_data;
	public Match()
	{
		NBADataFactory factory = DataFactoryImp.instance();
		match_data = factory.getMatchData();
	}
	
    public MatchesPO[] getTodayMatches()
    {
		return match_data.getTodayMatches();
    }
	@Override
	public MatchesPO[] getAllMatches() 
	{
		return match_data.getAllMatches();
	}
	@Override
	public MatchesPO[] getRecentPlayerMatches(String playerName, int num) {
		return match_data.getRecentPlayerMatches(playerName, num);
	}
	@Override
	public MatchesPO[] getRecentTeamMatches(String teamName, int num) {
		return match_data.getRecentTeamMatches(teamName, num);
	}
}
