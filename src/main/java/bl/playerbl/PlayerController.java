package bl.playerbl;

import java.util.Iterator;

import po.MatchesPO;
import po.PlayerPO;
import bl.matchbl.Match;
import bl.matchbl.MatchController;
import blservice.matchblservice.Matchblservice;
import blservice.playerblservice.PlayerBlService;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.PlayerVO;
import vo.SortType;

public class PlayerController implements PlayerBlService{
	private PlayerList player;
	private Matchblservice matchservice = new MatchController();

	public PlayerController() {
		player = PlayerList.getPlayserListInstance();
	}

	public void setAverage(boolean isAverage) {
		// TODO Auto-generated method stub
	}

	public PlayerPO findPlayer(String info) {
		return player.findPlayerInfo(info);
	}

	@Override
	public boolean updatePlayerInformation() {
		return true;
	}

	@Override
	public PlayerMatchVO[] sortPlayers(PlayerSortBy playerSortBy,
			SortType sortType) {
		return player.sortPlayers(playerSortBy, sortType, 500);
	}

	@Override
	public PlayerMatchVO[] sreenPlayers(String playerPosition, Area playerArea,
			PlayerSortBy sortBy) {
		//默认为前50名
		return player.sreenPlayers(playerPosition, playerArea, sortBy, 50);
	}

	@Override
	public PlayerMatchVO[] getDayHotPlayer(PlayerSortBy sortby) {
		return player.getDayHotPlayers(sortby, 5);
	}

	@Override
	public PlayerMatchVO[] getSeasonHotPlayer(PlayerSortBy sortby) {
		//热点球员默认为5个
		return player.getSeasonHotPlayers(sortby, 5);
	}

	@Override
	public PlayerMatchVO[] getPromotePlayer(PlayerSortBy sortby) {
		return player.getPromotePlayer(sortby, 5);
	}

	@Override
	public Iterator<PlayerMatchVO> fuzzilyFindAve(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<PlayerMatchVO> fuzzilyFindTotal(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Match[] getRecentPlayerMatches(String playername, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Match[] getAllPlayerMatches(String playername, int num) {
		// TODO Auto-generated method stub
		return null;
	}
}
