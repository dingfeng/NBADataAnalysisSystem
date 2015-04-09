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
		player = PlayerList.getPlayserListInstance();
	}

	public void setAverage(boolean isAverage) {
		// TODO Auto-generated method stub
	}

	public PlayerVO findPlayer(String info) {
		return player.findPlayerInfo(info);
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
		return player.getDayHotPlayers(sortby);
	}

	@Override
	public PlayerMatchVO[] getSeasonHotPlayer(PlayerSortBy sortby) {
		return player.getSeasonHotPlayers(sortby);
	}

	@Override
	public PlayerMatchVO[] getPromotePlayer(PlayerSortBy sortby) {
		return player.getPromotePlayer(sortby);
	}
}
