package blservice.playerblservice;


import java.util.Iterator;

import bl.matchbl.Match;
import po.PlayerPO;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.SortType;

public interface PlayerBlService {
	public PlayerMatchVO[] sortPlayers(PlayerSortBy playerSortBy, SortType sortType);
	public PlayerMatchVO[] sreenPlayers(String playerPosition, Area playerArea, PlayerSortBy sortBy);
	public PlayerMatchVO[] getDayHotPlayer(PlayerSortBy sortby);
	public PlayerMatchVO[] getSeasonHotPlayer(PlayerSortBy sortby);
	public PlayerMatchVO[] getPromotePlayer(PlayerSortBy sortby);
	public boolean updatePlayerInformation();
	//模糊查找
	public Iterator<PlayerMatchVO> fuzzilyFindAve(String info);
	public Iterator<PlayerMatchVO> fuzzilyFindTotal(String info);
	public Match[] getRecentPlayerMatches(String playername, int num);
	public Match[] getAllPlayerMatches(String playername, int num);
	public void setAverage(boolean isAverage);
    public PlayerPO findPlayer(String info);
}
