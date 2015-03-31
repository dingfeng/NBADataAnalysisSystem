package DataFactoryService;

import dataservice.Matchdataservice.MatchDataService;
import dataservice.Playerdataservice.PlayerDataService;
import dataservice.Teamdataservice.TeamDataService;

public interface  NBADataFactory
{
	public MatchDataService getMatchData();
	public PlayerDataService getPlayerData();
	public TeamDataService getTeamData();

}
