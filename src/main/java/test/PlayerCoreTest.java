package test;

import static org.junit.Assert.*;

import org.junit.Test;

import vo.PlayerSortBy;
import vo.SortType;

public class PlayerCoreTest {

	@Test
	public void testGetPlayerHighInfos() {
		PlayerCore playerCore = new PlayerCore();
		PlayerSortBy[] sortbys = new PlayerSortBy[]{PlayerSortBy.gmScEfficiency};
		SortType[] sortTypes = new SortType[]{SortType.ASEND};
		playerCore.getPlayerHighInfos(sortbys, sortTypes, 4);
	}

	@Test
	public void testGetPlayerHotInfos() {
		PlayerCore playerCore = new PlayerCore();
		playerCore.getPlayerHotInfos("score", 5);
	}

	@Test
	public void testGetPlayerSeasonKingInfo() {
	  PlayerCore playerCore = new PlayerCore();
	  playerCore.getPlayerSeasonKingInfo("score", 5);
	}

	@Test
	public void testGetPlayerDailyKingInfo() {
		 PlayerCore playerCore = new PlayerCore();
		 playerCore.getPlayerDailyKingInfo("score", 5);
	}

	@Test
	public void testGetPlayerAveNormalInfos() {
		 PlayerCore playerCore = new PlayerCore();
		 PlayerSortBy[] sortbys = new PlayerSortBy[]{PlayerSortBy.assistNo};
		 SortType[] sortTypes = new SortType[]{SortType.DESEND}; 
		 playerCore.getPlayerAveNormalInfos(sortbys, sortTypes, 5, "F", "West", 0);
	}

	@Test
	public void testGetPlayerTotalNormalInfos() {
		 PlayerCore playerCore = new PlayerCore();
		 PlayerSortBy[] sortbys = new PlayerSortBy[]{PlayerSortBy.assistNo};
		 SortType[] sortTypes = new SortType[]{SortType.DESEND}; 
		 playerCore.getPlayerTotalNormalInfos(sortbys, sortTypes, 5, "F", "West", 0);
	}

}
