package bl.playerbl;

import java.util.Iterator;

import po.MatchesPO;
import blservice.playerblservice.PlayerBlService;
import vo.Area;
import vo.PlayerSortBy;
import vo.PlayerVO;
import vo.SortType;

public class PlayerController implements PlayerBlService{
	private PlayerList player;

	public PlayerController() {
		player = player.getPlayserListInstance();
	}

	public Iterator<PlayerVO> sortPlayer(PlayerSortBy playerSortBy,
			SortType sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<PlayerVO> sreenPlayer(char playerPosition, Area playerArea,
			PlayerSortBy sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<PlayerVO> getDayHotPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<PlayerVO> getSeasonHotPlayer() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<PlayerVO> getPromotePlayer() {
		// TODO Auto-generated method stub
		return null;
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
}
