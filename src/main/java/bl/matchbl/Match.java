package bl.matchbl;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;
import dataservice.matchdataservice.MatchDataService;
import po.MatchesPO;

public class Match 
{
   private	MatchDataService match_data;
   private	TIntObjectMap<Queue> team;
   private	TIntObjectMap<Queue> player;
   private	boolean inited = false;
   private final static int match_num = 82;
   public Match()
	{
		NBADataFactory factory = DataFactoryImp.instance();
		match_data = factory.getMatchData();
		team = new TIntObjectHashMap<Queue>();
		player = new TIntObjectHashMap<Queue>();
		init();
	}
	
	private void init()
	{
		MatchesPO[] allMatches = match_data.getAllMatches();
		if (allMatches == null)
		{
          return;			
		}
		for (String s : teamNames)
		{
			team.put(s.hashCode(), new Queue(match_num,s));
		}
		for (MatchesPO m : allMatches)
		{
			dealWithOneMatch(m);
		}
		inited = true;
	}
	
	private void dealWithOneMatch(MatchesPO match)
	{
		
		
	}
	
	public void update()
	{
	 if (!inited)
	 {
		 init();
	 }
	}
	
	public boolean changed()
	{
		return match_data.changed();
	}
	
    public MatchesPO[] getTodayMatches()
    {
		return match_data.getTodayMatches();
    }
	public MatchesPO[] getAllMatches()
	{
		return match_data.getAllMatches();
	}
	public MatchesPO[] getRecentPlayerMatches(String playerName, int num) {
		return match_data.getRecentPlayerMatches(playerName, num);
	}
	public MatchesPO[] getRecentTeamMatches(String teamName, int num) {
		return match_data.getRecentTeamMatches(teamName, num);
	}
	
    private final static String[] teamNames =new String[]
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
