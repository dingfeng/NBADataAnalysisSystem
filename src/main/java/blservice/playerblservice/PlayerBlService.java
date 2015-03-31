package blservice.playerblservice;

import java.util.Iterator;

import vo.Area;

public interface PlayerBlService {
	public Iterator<PlayerVO> sortPlayer(PlayerSortBy playerSortBy, SortType sortType);
	public Iterator<PlayerVO> sreenPlayer(char playerPosition, Area playerArea, PlayerSortBy sortBy);
	public Iterator<PlayerVO> getDayHotPlayer();
	public Iterator<PlayerVO> getSeasonHotPlayer();
	public Iterator<PlayerVO> getPromotePlayer();
	public void setAverage(boolean isAverage);
    public PlayerVO findPlayer(String info);
}
