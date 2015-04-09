package blservice.playerblservice;


import po.MatchesPO;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.PlayerVO;
import vo.SortType;

public interface PlayerBlService {
	public PlayerMatchVO[] sortPlayers(PlayerSortBy playerSortBy, SortType sortType);
	public PlayerMatchVO[] sreenPlayers(char playerPosition, Area playerArea, PlayerSortBy sortBy);
	public PlayerMatchVO[] getDayHotPlayer(PlayerSortBy sortby);
	public PlayerMatchVO[] getSeasonHotPlayer(PlayerSortBy sortby);
	public PlayerMatchVO[] getPromotePlayer(PlayerSortBy sortby);
	public boolean updatePlayerInformation(MatchesPO[] matches);
	public void setAverage(boolean isAverage);
    public PlayerVO findPlayer(String info);
}
