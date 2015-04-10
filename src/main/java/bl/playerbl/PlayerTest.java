package bl.playerbl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.SortType;

public class PlayerTest {
	Player player ;
    @Before
    public void setup()
    {
    	player = new Player();
    }
	@Test
	public void testSortAvePlayers() {
		PlayerMatchVO[] players = player.sortAvePlayers(PlayerSortBy.assist, SortType.ASEND);
		for (int i = 0; i < players.length; i++)
		{
			System.out.println(players[i].getAssistNo());
		}
	}

	@Test
	public void testSortTotalPlayers() {
		player.sortTotalPlayers(PlayerSortBy.assistEfficiency, SortType.DESEND);
	}

	@Test
	public void testScreenAvePlayers() {
		player.screenAvePlayers("F", Area.ATLANTIC, PlayerSortBy.assistEfficiency);
	}

	@Test
	public void testScreenTotalPlayers() {
		player.screenTotalPlayers("G", Area.NORTHWEST, PlayerSortBy.block);
	}

	@Test
	public void testGetDayHotPlayer() {
		player.getDayHotPlayer(PlayerSortBy.block);
	}

	@Test
	public void testGetSeasonHotPlayer() {
		player.getSeasonHotPlayer(PlayerSortBy.blockNo);
	}

	@Test
	public void testGetPromotePlayer() {
		player.getPromotePlayer(PlayerSortBy.defenceNo);
	}

	@Test
	public void testFuzzilyFind() {
		player.fuzzilyFind("A");
	}

	@Test
	public void testFindPlayer() {
		player.findPlayer("Kobe Bryant");
	}

}
