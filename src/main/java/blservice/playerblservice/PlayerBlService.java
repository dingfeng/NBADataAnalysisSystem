package blservice.playerblservice;

import java.util.Iterator;

import po.MatchesPO;
import vo.Area;
import vo.PlayerSortBy;
import vo.PlayerVO;
import vo.SortType;

public interface PlayerBlService {
	public Iterator<PlayerVO> sortPlayer(PlayerSortBy playerSortBy, SortType sortType);
	public Iterator<PlayerVO> sreenPlayer(char playerPosition, Area playerArea, PlayerSortBy sortBy);
	public Iterator<PlayerVO> getDayHotPlayer();
	public Iterator<PlayerVO> getSeasonHotPlayer();
	public Iterator<PlayerVO> getPromotePlayer();
	public boolean updatePlayerInformation(MatchesPO[] matches);
	public void setAverage(boolean isAverage);
    public PlayerVO findPlayer(String info);
}
