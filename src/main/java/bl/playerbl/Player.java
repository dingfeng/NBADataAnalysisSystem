package bl.playerbl;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;

import dataservice.playerdataservice.PlayerDataService;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import po.PlayerPO;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.SortType;
import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;
import bl.matchbl.Match;
import bl.matchbl.PlayerQueue;
import bl.teambl.SortTool;
import bl.teambl.Team;
import blservice.playerblservice.PlayerBlService;

public class Player  {

	private	TIntObjectMap<PlayerQueue> player_map;
	private PlayerDataService playerData;
	private TIntObjectMap<PlayerPO>  player_base_map;
	private PlayerPO[] allPlayerpos;
	private Match  match ;
	public Player()
	{
	  match = Match.instance();
	  player_map  = match.getPlayer_map();
	  
	  NBADataFactory factory = DataFactoryImp.instance();
	  playerData = factory.getPlayerData();
	  allPlayerpos = playerData.getAllPlayerData();
	  player_base_map = new TIntObjectHashMap<PlayerPO>();
	  for ( PlayerPO p : allPlayerpos)
	  {
		  player_base_map.put(p.getName().hashCode(), p);
	  }
	}
	public PlayerMatchVO[] sortAvePlayers(PlayerSortBy playerSortBy,
			SortType sortType) {
		PlayerQueue[] matchPlayers = new PlayerQueue[player_map.size()];
		player_map.values(matchPlayers);
		
		PlayerMatchVO playervo = null;
		String[] playerNames = new String[matchPlayers.length];
		for (int i = 0; i < matchPlayers.length; i++)
		{
			playervo = matchPlayers[i].getAvePlayer();
			playerNames[i] = playervo.getName();
		}
		
		if (playerSortBy == PlayerSortBy.name)
		{
			PlayerMatchVO[] playerMatchvos = new PlayerMatchVO[matchPlayers.length];
			Arrays.sort(playerNames);
			if (sortType == SortType.ASEND)
			{
				for (int i = 0; i < playerNames.length; i++)
				{
					playerMatchvos[i] = player_map.get(playerNames[i].hashCode()).getAvePlayer();
				}
			}
			else 
			{
				for (int i = playerNames.length - 1; i >= 0; --i)
				{
					playerMatchvos[playerNames.length-1-i] = player_map.get(playerNames[i].hashCode()).getAvePlayer();
				}
			}
			return  playerMatchvos;
		}
		else 
		{
        PlayerMatchVO[] playerMatchvos = new PlayerMatchVO[matchPlayers.length];
        
        for (int i = 0; i < playerMatchvos.length; i++)
        {
        	playervo = matchPlayers[i].getAvePlayer();
        	playerMatchvos[i] = playervo;
        	setSortTool(playervo,playerSortBy, sortType);
        }
		Arrays.sort(playerMatchvos);
		return playerMatchvos;
		}
	}

	public PlayerMatchVO[] sortTotalPlayers(PlayerSortBy playerSortBy,
			SortType sortType) {
		PlayerQueue[] matchPlayers = new PlayerQueue[player_map.size()];
		player_map.values(matchPlayers);
		
		PlayerMatchVO playervo = null;
		String[] playerNames = new String[matchPlayers.length];
		for (int i = 0; i < matchPlayers.length; i++)
		{
			playervo = matchPlayers[i].getTotalPlayer();
			playerNames[i] = playervo.getName();
		}
		
		if (playerSortBy == PlayerSortBy.name)
		{
			PlayerMatchVO[] playerMatchvos = new PlayerMatchVO[matchPlayers.length];
			Arrays.sort(playerNames);
			if (sortType == SortType.ASEND)
			{
				for (int i = 0; i < playerNames.length; i++)
				{
					playerMatchvos[i] = player_map.get(playerNames[i].hashCode()).getTotalPlayer();
				}
			}
			else 
			{
				for (int i = playerNames.length - 1; i >= 0; --i)
				{
					playerMatchvos[playerNames.length-1-i] = player_map.get(playerNames[i].hashCode()).getTotalPlayer();
				}
			}
			return  playerMatchvos;
		}
		else 
		{
        PlayerMatchVO[] playerMatchvos = new PlayerMatchVO[matchPlayers.length];
        
        for (int i = 0; i < playerMatchvos.length; i++)
        {
        	playervo = matchPlayers[i].getTotalPlayer();
        	playerMatchvos[i] = playervo;
        	setSortTool(playervo,playerSortBy, sortType);
        }
		Arrays.sort(playerMatchvos);
		return playerMatchvos;
		}
	}

	public Iterator<PlayerMatchVO> screenAvePlayers(String playerPosition,
			Area playerArea, PlayerSortBy sortBy) {
		Team team = new Team();
	   ArrayList<PlayerMatchVO> screen_players = new ArrayList<PlayerMatchVO>(500);
	   
	   for (PlayerPO p : allPlayerpos)
	   {
		   if (p.getPosition().contains(playerPosition) && team.getPlayerArea(p.getName()) == playerArea)
		   {
			   screen_players.add(player_map.get(p.getName().hashCode()).getAvePlayer());
		   }
	   }
		
		return screen_players.iterator();
	}

	public Iterator<PlayerMatchVO> screenTotalPlayers(String playerPosition,
			Area playerArea, PlayerSortBy sortBy) {
		
		Team team = new Team();
		   ArrayList<PlayerMatchVO> screen_players = new ArrayList<PlayerMatchVO>(500);
		   
		   for (PlayerPO p : allPlayerpos)
		   {
			   if (p.getPosition().contains(playerPosition) && team.getPlayerArea(p.getName()) == playerArea)
			   {
				   screen_players.add(player_map.get(p.getName().hashCode()).getTotalPlayer());
			   }
		   }
			
			return screen_players.iterator();
	}

	public  MatchPlayerPO[] getDayHotPlayer(PlayerSortBy sortby) {
		MatchesPO[]  todayMatches = match.getTodayMatches();
		ArrayList<MatchPlayerPO> todayPlayers = new ArrayList<MatchPlayerPO>(500);
		MatchTeamPO team = null;
		MatchPlayerPO[] teamPlayers = null;
		for (MatchesPO m : todayMatches)
		{
           teamPlayers = m.getTeam1().getPlayers();
           for (MatchPlayerPO p : teamPlayers)
           {
        	   setHotSortTool(p, sortby);
        	   todayPlayers.add(p);
           }
           teamPlayers = m.getTeam2().getPlayers();
           for (MatchPlayerPO p : teamPlayers)
           {
        	   setHotSortTool(p, sortby);
        	   todayPlayers.add(p);
           }
		}
		Collections.sort(todayPlayers);
		int size = todayPlayers.size() > 5 ? 5 : todayPlayers.size();
		MatchPlayerPO[] m_players = new MatchPlayerPO[size];
		for (int i = 0; i < size; i++)
		{
			m_players[i] = todayPlayers.get(i);
		}
		return m_players;
	}

	public PlayerMatchVO[] getSeasonHotPlayer(PlayerSortBy sortby) {
		PlayerMatchVO[] playerMatchVOs = sortAvePlayers(sortby,SortType.DESEND);
		int size = 5;
		int len = playerMatchVOs.length;
		if (len < 5)
		{
			size = len;
		}
        PlayerMatchVO[] resultMatches = new PlayerMatchVO[size];
        for (int i = 0; i < size; i++)
        {
        	resultMatches[i] = playerMatchVOs[i];
        }
		return resultMatches;
	}

	public PlayerMatchVO[] getPromotePlayer(PlayerSortBy sortby) {
		PlayerMatchVO[] playerMatchVOs = sortAvePlayers(sortby,SortType.DESEND);
		int size = 5;
		int len = playerMatchVOs.length;
		if (len < 5)
		{
			size = len;
		}
        PlayerMatchVO[] resultMatches = new PlayerMatchVO[size];
        for (int i = 0; i < size; i++)
        {
        	resultMatches[i] = playerMatchVOs[i];
        }
		return resultMatches;
	}

	public Iterator<String> fuzzilyFind(String info) {
		return null;
	}

	public PlayerPO findPlayer(String info) 
	{
		return player_base_map.get(info.hashCode());
	}
	
    
	private void setHotSortTool(MatchPlayerPO player, PlayerSortBy sortby)
	{
		double data = -1;
		switch (sortby)
		{
		case points:
			data = player.getPoints();
			break;
		case rebs:
			data = player.getRebs();
			break;
		case assistNo:
			data = player.getHelp();
			break;
		case blockNo:
			data = player.getBlockNo();
			break;
		case  stealsNo:
			data = player.getStealsNo();
			break;
		}
		player.setHotData(data);
	}
	
	private void setSortTool(PlayerMatchVO playervo, PlayerSortBy sortby, SortType sortType)
	{
		double data = -1;
		switch (sortby)
		{
		case   firstServiceNo:
			data =  playervo.getFirstServiceNo();
			break;
		case    rebs:
			data = playervo.getRebs();
			break;
		case	assistNo:
			data = playervo.getAssistNo();
			break;
		case	time:
			data = playervo.getTime();
			break;
		case	hitRate:
			data = playervo.getHitRate();
			break;
		case	threeHitRate:
			data = playervo.getThreeHitRate();
			break;
		case	penaltyHitRate:
			data = playervo.getPenaltyHitRate();
			break;
		case	offendNo:
			data = playervo.getOffendNo();
			break;
		case	defenceNo:
			data = playervo.getDefenceNo();
			break;
		case	stealsNo:
			data = playervo.getStealsNo();
			break;
		case	blockNo:
			data = playervo.getBlockNo();
			break;
		case	mistakesNo:
			data = playervo.getMistakesNo();
			break;
		case	foulsNo:
			data = playervo.getFoulsNo();
			break;
		case	points:
			data = playervo.getPoints();
			break;
		case	efficiency:
			data = playervo.getEfficiency();
			break;
		case   gmScEfficiency:
			data = playervo.getGmScEfficiency();
			break;
		case	trueHitRate:
			data = playervo.getTrueHitRate();
			break;
		case	hitEfficiency:
			data = playervo.getHitEfficiency();
			break;
		case	rebEfficiency:
			data = playervo.getRebEfficiency();
			break;
		case	offenseRebsEfficiency:
			data = playervo.getOffenseRebsEfficiency();
			break;
		case    defenceRebsEfficiency:
			data = playervo.getDefenceRebsEfficiency();
			break;
		case	 assistEfficiency:
			data =  playervo.getAssistEfficiency();
			break;
		case	stealsEfficiency:
			data = playervo.getStealsEfficiency();
			break;
		case	blockEfficiency:
			data =  playervo.getBlockEfficiency();
			break;
		case	mistakeEfficiency:
			data = playervo.getMistakeEfficiency();
			break;
		case	useEfficiency:
			data = playervo.getUseEfficiency();
			break;
		case	points_uprate:
			data = playervo.getPoints_uprate();
			break; //得分提升率
		case	rebs_uprate :
			data = playervo.getRebs_uprate();
			break;  //篮板提升率
		case	help_uprate :
			data =  playervo.getHelp_uprate();
			break;//助攻提升率
		
		case    rebound:
			data = playervo.getRebs();
			break;//篮板
		case	assist:
			data = playervo.getAssistNo();
			break;//助攻
		case	scoring_rebound_assist:
			data = playervo.getScoring_rebound_assist();
			break;//得分/篮板/助攻（加权比1：1：1）
		case	block:
			data = playervo.getBlockNo();
			break;//盖帽
		case	steal:
			data = playervo.getStealsNo();
			break;//抢断
		case	foul:
			data = playervo.getFoulsNo();
			break;//犯规
		case	mistake:
			data = playervo.getMistakesNo();
			break;//失误
		case	minute:
			data = playervo.getMinute();
			break;//分钟
		case	shot:
			data = playervo.getHandNo();
			break;//投篮
		case	three_points:
			data = playervo.getThree_points();
			break;//三分
		case	freeThrow:
			data = playervo.getPenaltyHandNo();
			break;//罚球
		case	twoPair:
			data = playervo.getTwoPair();
			break;//两双
		}
		playervo.setSortTool(new SortTool(data,sortType));
	}
	
}
