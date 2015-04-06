package bl.matchbl;

import bl.teambl.Team;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;
import dataservice.matchdataservice.MatchDataService;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;

public class Match 
{
   private	MatchDataService match_data;
   private	TIntObjectMap<TeamQueue> team_map;
   private	TIntObjectMap<PlayerQueue> player_map;
   private	boolean inited = false;
   private final static int match_num = 82;
   private static  Match match;
   private  Match()
   {
		NBADataFactory factory = DataFactoryImp.instance();
		match_data = factory.getMatchData();
		team_map = new TIntObjectHashMap<TeamQueue>();
		player_map = new TIntObjectHashMap<PlayerQueue>();
		init();
	}
    
	public static Match instance()
	{
		if (match == null)
		{
			match = new Match();
		}
		return match;
	}
    
	private void init()
	{
		MatchesPO[] allMatches = match_data.getAllMatches();
		if (allMatches == null)
		{
          return;			
		}
		
		for (String s : Team.teamnames)
		{
			team_map.put(s.hashCode(), new TeamQueue(match_num,s));
		}
		for (MatchesPO m : allMatches)
		{
			dealWithOneMatch(m);
		}
		inited = true;
	}
	
	private void dealWithOneMatch(MatchesPO match)
	{
		MatchTeamPO team1 = match.getTeam1();
		MatchTeamPO team2 = match.getTeam2();
		Queue team1_q = team_map.get(team1.getName().hashCode());
		Queue team2_q = team_map.get(team2.getName().hashCode());
		team1_q.enQueue(match);
		team2_q.enQueue(match);
		team1_q.update();
		team2_q.update();
		MatchPlayerPO[] player_team1 = team1.getPlayers();
		MatchPlayerPO[] player_team2 = team2.getPlayers();
		int key = -1;
        PlayerQueue q = null;
        for (MatchPlayerPO p : player_team1)
        {
            key = p.getName().hashCode();
            if (!player_map.containsKey(key))
            {
            	q = new PlayerQueue(match_num,p.getName());
            	player_map.put(key, q);
            }
            else 
            {
        	   q = player_map.get(p.getName().hashCode());
            }
        	q.enQueue(match);
        	q.update();
        }
        
        for (MatchPlayerPO p : player_team2)
      {
            key = p.getName().hashCode();
            if (!player_map.containsKey(key))
            {
            	q = new PlayerQueue(match_num,p.getName());
            	player_map.put(key, q);
            }
            else 
            {
        	   q = player_map.get(p.getName().hashCode());
            }
        	q.enQueue(match);
        	q.update();
        }
	}
	
	public void update()
	{
	 if (!inited)
	 {
		 init();
	 }
	 if (match_data.changed())
	 {
		 MatchesPO[] matches = match_data.getNewMatches();
		 for (MatchesPO  m : matches)
		 {
			 dealWithOneMatch(m);
		 }
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
    
    public TIntObjectMap<TeamQueue> getTeam_map() {
    	return team_map;
    }

    public TIntObjectMap<PlayerQueue> getPlayer_map() {
    	return player_map;
    }
}
