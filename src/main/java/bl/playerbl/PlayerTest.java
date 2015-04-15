package bl.playerbl;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import po.MatchPlayerPO;
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
	public void testSortAvePlayers() throws IOException {
		PlayerMatchVO[] players = player.sortAvePlayers(PlayerSortBy.rebs_uprate, SortType.DESEND);
		for (int i = 0; i < players.length; i++)
		{
//			System.out.println(players[i].getRebs_uprate());
//			if (players[i].getName().equals("James Harden"))
//				{System.out.println(players[i]);
//				System.out.println(players[i].getEfficiency());
//				}
//			if (players[i].getName().equals("John Wall"))
//			System.out.println(players[i]);
//			System.out.println(players[i]);
		}
	}

	@Test
	public void testSortTotalPlayers() throws IOException {
		PlayerMatchVO[] players = player.sortTotalPlayers(PlayerSortBy.efficiency, SortType.ASEND);
		for (PlayerMatchVO p : players)
		{
//			if (p.getName().equals("Kobe Bryant"))
			{
//				System.out.println(p);
//				System.out.println("mistakeRate : "+p.getMistakeEfficiency());
			}
//			if (p.getName().equals("Kevin Durant"))
//			{
//				System.out.println("total points : "+p.getPoints());
//			}
//				System.out.println(p);
//				System.out.println(p.getEfficiency());
//				System.out.println("points : " +p.getPoints());
		}
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
	public void testGetDayHotPlayer() throws IOException {
		MatchPlayerPO[] hotPlayers = player.getDayHotPlayer(PlayerSortBy.blockNo);
		for (MatchPlayerPO po : hotPlayers)
		{
			System.out.println(po.getBlockNo());
		}
	}

	@Test
	public void testGetSeasonHotPlayer() throws IOException {
		PlayerMatchVO[] playervos = player.getSeasonHotPlayer(PlayerSortBy.blockNo);
		for (PlayerMatchVO vo : playervos)
		{
//			System.out.println(vo);
		}
	}

	@Test
	public void testGetPromotePlayer() throws IOException {
	       PlayerMatchVO[] vos =player.getPromotePlayer(PlayerSortBy.help_uprate);
	       for (PlayerMatchVO vo : vos)
	       {
//	    	   System.out.println(vo.getHelp_uprate());
	       }
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
