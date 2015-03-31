package DataFactory;

import data.matchdata.MatchData;
import data.playerdata.PlayerData;
import data.teamdata.TeamData;
import dataservice.matchdataservice.MatchDataService;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.teamdataservice.TeamDataService;
import DataFactoryService.NBADataFactory;

public class DataFactoryImp implements NBADataFactory
{
	public  MatchDataService getMatchData() {
		
		return new MatchData();
	}

	public PlayerDataService getPlayerData() {
		return new PlayerData();
	}

	public TeamDataService getTeamData() {
		return new TeamData();
	}

}
