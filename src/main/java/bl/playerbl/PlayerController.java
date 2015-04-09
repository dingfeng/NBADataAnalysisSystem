package bl.playerbl;

import po.MatchesPO;
import blservice.playerblservice.PlayerBlService;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.PlayerVO;
import vo.SortType;

public class PlayerController implements PlayerBlService{
	private PlayerList player;

	public PlayerController() {
		player = player.getPlayserListInstance();
	}

	public void setAverage(boolean isAverage) {
		// TODO Auto-generated method stub
	}

	public PlayerVO findPlayer(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePlayerInformation(MatchesPO[] matches) {
		boolean result = player.updateMatchPlayerData(matches);
		return result;
	}

	@Override
	public PlayerMatchVO[] sortPlayers(PlayerSortBy playerSortBy,
			SortType sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerMatchVO[] sreenPlayers(char playerPosition, Area playerArea,
			PlayerSortBy sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerMatchVO[] getDayHotPlayer(PlayerSortBy sortby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerMatchVO[] getSeasonHotPlayer(PlayerSortBy sortby) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlayerMatchVO[] getPromotePlayer(PlayerSortBy sortby) {
		// TODO Auto-generated method stub
		return null;
	}

}
