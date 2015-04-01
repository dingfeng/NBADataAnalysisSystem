package DataFactory;

import data.matchdata.MatchData;
import data.playerdata.PlayerData;
import data.teamdata.TeamData_old;
import dataservice.matchdataservice.MatchDataService;
import dataservice.playerdataservice.PlayerDataService;
import dataservice.teamdataservice.TeamDataService;
import DataFactoryService.NBADataFactory;

public class DataFactoryImp implements NBADataFactory
{
	private static NBADataFactory factory = null;
	private static  String dataSource = null;
	
	public static void  setDataSource(String dataSource0)
	{
		dataSource = dataSource0;
	}
	
	public static NBADataFactory instance()
	{
		if (dataSource == null)
		{
			return null;
		}
		if (factory == null)
		{
			return new DataFactoryImp();
		}
		else return factory ;
	}
	
	public  MatchDataService getMatchData() {
		//return new MatchData(dataSource + "\\matches\\");
		return null;
	}

	public PlayerDataService getPlayerData() {
		//return new PlayerData(dataSource + "\\players\\");
		return null;
	}

	public TeamDataService getTeamData() {
		//return new TeamData(dataSource + "/teams/");
		return null;
	}

}
