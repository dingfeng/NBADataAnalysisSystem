package bl.playerbl;

import java.util.ArrayList;
import java.util.Iterator;

import po.MatchPlayerPO;
import po.PlayerPO;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.SortType;
import blservice.playerblservice.PlayerBlService;

public class PlayerController implements PlayerBlService{
    Player player = new Player();
    //排序球员数据 场均
	public synchronized PlayerMatchVO[] sortAvePlayers(PlayerSortBy playerSortBy,
			SortType sortType) {
		return player.sortAvePlayers(playerSortBy, sortType);
	}
	//排序球员数据  赛季数据
	public  synchronized PlayerMatchVO[] sortTotalPlayers(PlayerSortBy playerSortBy,
			SortType sortType) {
		return player.sortTotalPlayers(playerSortBy, sortType);
	}
	//筛选球员数据  场均  
	public  synchronized PlayerMatchVO[] screenAvePlayers(String playerPosition,
			Area playerArea, PlayerSortBy sortBy) {
		Iterator<PlayerMatchVO> itr = player.screenAvePlayers(playerPosition, playerArea, sortBy);
		ArrayList<PlayerMatchVO> list = new ArrayList<PlayerMatchVO>();
		while (itr.hasNext())
		{
			list.add(itr.next());
		}
		PlayerMatchVO[] players = new PlayerMatchVO[list.size()];
		list.toArray(players);
		return players;
	}
	//筛选球员数据  赛季
	public synchronized PlayerMatchVO[] screenTotalPlayers(String playerPosition,
			Area playerArea, PlayerSortBy sortBy) {
		Iterator<PlayerMatchVO> itr =  player.screenTotalPlayers(playerPosition, playerArea, sortBy);
		ArrayList<PlayerMatchVO> list = new ArrayList<PlayerMatchVO>();
		while (itr.hasNext())
		{
			list.add(itr.next());
		}
		PlayerMatchVO[] players = new PlayerMatchVO[list.size()];
		list.toArray(players);
		return players;
	}
	//获得当天热点球员   
	public  synchronized PlayerMatchVO[] getDayHotPlayer(PlayerSortBy sortby) {
	    MatchPlayerPO[] pos = player.getDayHotPlayer(sortby);
	    PlayerMatchVO[] vos = new PlayerMatchVO[pos.length];
	    for (int i = 0; i < vos.length; i++)
	    {
	    	vos[i] = new PlayerMatchVO(pos[i]);
	    }
		return vos;
	}
	//获得赛季热点球员
	public synchronized PlayerMatchVO[] getSeasonHotPlayer(PlayerSortBy sortby) {
		return player.getSeasonHotPlayer(sortby);
	}
	//获得进步最快球员
	public synchronized PlayerMatchVO[] getPromotePlayer(PlayerSortBy sortby) {
		return player.getPromotePlayer(sortby);
	}
	//模糊查找球员
	public synchronized  Iterator<String> fuzzilyFind(String info) {
		return player.fuzzilyFind(info);
	}
	//根据球员名字查找球员
	public synchronized PlayerPO findPlayer(String info) {
		return player.findPlayer(info);
	}
	@Override
	public synchronized PlayerMatchVO findPlayerMatchAve(String playername) {
		return player.findPlayerMatchAve(playername);
	}
	@Override
	public synchronized PlayerMatchVO findPlayerTotal(String playername) {
		return player.findPlayerMatchTotal(playername);
	}
	@Override
	public synchronized String[] getAllPlayerNames() {
		return player.getSearchItems();
	}

}
