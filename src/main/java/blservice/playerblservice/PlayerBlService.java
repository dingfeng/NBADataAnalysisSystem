package blservice.playerblservice;

import java.util.Iterator;

import po.MatchesPO;
import vo.Area;
import vo.PlayerSortBy;
import vo.PlayerVO;
import vo.SortType;

public interface PlayerBlService {
	public Iterator<PlayerVO> sortPlayers(PlayerSortBy playerSortBy, SortType sortType);
	public Iterator<PlayerVO> sreenPlayers(char playerPosition, Area playerArea, PlayerSortBy sortBy);
	public Iterator<PlayerVO> getDayHotPlayer(PlayerSortBy sortby);
	public Iterator<PlayerVO> getSeasonHotPlayer(PlayerSortBy sortby);
	public Iterator<PlayerVO> getPromotePlayer(PlayerSortBy sortby);
	public boolean updatePlayerInformation(MatchesPO[] matches);
	public void setAverage(boolean isAverage);
    public PlayerVO findPlayer(String info);
}
