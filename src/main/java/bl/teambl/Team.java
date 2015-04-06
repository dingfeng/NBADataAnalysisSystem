package bl.teambl;

import dataservice.teamdataservice.TeamDataService;
import vo.TeamVO;
import gnu.trove.map.TIntObjectMap;
import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;
import bl.matchbl.Match;
import bl.matchbl.Queue;

public class Team 
{
	private	TIntObjectMap<Queue> team_map;
	private Match match;
	private TeamDataService teamData;
	
	public Team()
	{
		match = Match.instance();
		team_map = match.getTeam_map();
		NBADataFactory factory = DataFactoryImp.instance();
		teamData = factory.getTeamData();
	}
	
	//球队的缩写名
	public String[] getPlayers(String team)
	{
		return null;
	}
	
	public TeamVO getTeamData(String team)
	{
		return null;
	}
	
	private void update()
	{
		
	}
	public static String[] teamnames = new String[]
			{
		"ATL",
    	"BKN",
    	"BOS",
    	"CHA",
    	"CHI",
    	"CLE",
    	"DAL",
    	"DEN",
    	"DET",
    	"GSW",
    	"HOU",
    	"IND",
    	"LAC",
    	"LAL",
    	"MEM",
    	"MIA",
    	"MIL",
    	"MIN",
    	"NOH",
    	"NYK",
    	"OKC",
    	"ORL",
    	"PHI",
    	"PHX",
    	"POR",
    	"SAC",
    	"SAS",
    	"TOR",
    	"UTA",
    	"WAS"
			};
}
