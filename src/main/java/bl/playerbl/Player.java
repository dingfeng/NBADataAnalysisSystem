package bl.playerbl;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import java.util.Arrays;
import java.util.Iterator;

import dataservice.playerdataservice.PlayerDataService;
import po.PlayerPO;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.SortType;
import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;
import bl.matchbl.Match;
import bl.matchbl.PlayerQueue;
import blservice.playerblservice.PlayerBlService;

public class Player implements PlayerBlService{

	private	TIntObjectMap<PlayerQueue> player_map;
	private PlayerDataService playerData;
	private TIntObjectMap<PlayerPO>  player_base_map;
	public Player()
	{
	  Match match = Match.instance();
	  player_map  = match.getPlayer_map();
	  
	  NBADataFactory factory = DataFactoryImp.instance();
	  playerData = factory.getPlayerData();
	  PlayerPO[] allPlayerpos = playerData.getAllPlayerData();
	  player_base_map = new TIntObjectHashMap<PlayerPO>();
	  for ( PlayerPO p : allPlayerpos)
	  {
		  player_base_map.put(p.getName().hashCode(), p);
	  }
	  
	}
	@Override
	public PlayerMatchVO[] sortAvePlayers(PlayerSortBy playerSortBy,
			SortType sortType) {
		PlayerMatchVO[] playervos = new PlayerMatchVO[player];
		if (playerSortBy == PlayerSortBy.name)
		{
			
		}
		else 
		{
		PlayerQueue[] matchPlayers = new PlayerQueue[player_map.size()];
		PlayerMatchVO[] playervos = new PlayerMatchVO[matchPlayers.length];
		player_map.values(matchPlayers);
		PlayerMatchVO playervo = null;
		for (int i = 0; i < matchPlayers.length; i++)
		{
			playervo = matchPlayers[i].getAvePlayer();
			setSortTool(playervo, playerSortBy, sortType);
		}
		Arrays.sort(playervos);
		}
		return ;
	}
    
	private void setSortTool(PlayerMatchVO playervo, PlayerSortBy sortby, SortType sortType)
	{
		double 
	}
	
	@Override
	public PlayerMatchVO[] sortTotalPlayers(PlayerSortBy playerSortBy,
			SortType sortType) {
		return null;
	}

	@Override
	public PlayerMatchVO[] screenAvePlayers(String playerPosition,
			Area playerArea, PlayerSortBy sortBy) {
		return null;
	}

	@Override
	public PlayerMatchVO[] screenTotalPlayers(String playerPosition,
			Area playerArea, PlayerSortBy sortBy) {
		return null;
	}

	@Override
	public PlayerMatchVO[] getDayHotPlayer(PlayerSortBy sortby) {
		return null;
	}

	@Override
	public PlayerMatchVO[] getSeasonHotPlayer(PlayerSortBy sortby) {
		return null;
	}

	@Override
	public PlayerMatchVO[] getPromotePlayer(PlayerSortBy sortby) {
		return null;
	}

	@Override
	public Iterator<String> fuzzilyFind(String info) {
		return null;
	}

	@Override
	public PlayerPO findPlayer(String info) {
		return null;
	}

}
