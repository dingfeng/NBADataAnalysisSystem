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
		PlayerMatchVO[] players = player.sortAvePlayers(PlayerSortBy.defenceRebsEfficiency, SortType.DESEND);
		for (int i = 0; i < players.length; i++)
		{
			if(players[i].getName().equals("Tim Duncan"))
			{
				System.out.println(players[i].getUseEfficiency());
				System.out.println("points : "+players[i].getPoints());
				System.out.println(players[i].getHitEfficiency());
			}
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
		MatchPlayerPO[] hotPlayers = player.getDayHotPlayer(PlayerSortBy.block);
//		System.out.println("block hot : ");
//		for (MatchPlayerPO po : hotPlayers)
//		{
//			System.out.println(po.getBlockNo());
//		}
//		System.out.println("points : ");
//		hotPlayers = player.getDayHotPlayer(PlayerSortBy.points);
//		for (MatchPlayerPO po : hotPlayers)
//		{
//			System.out.println(po.getPoints());
//		}
//		System.out.println("rebs : ");
//		hotPlayers = player.getDayHotPlayer(PlayerSortBy.rebs);
//		for (MatchPlayerPO po : hotPlayers)
//		{
//			System.out.println(po.getRebs());
//		}
//		System.out.println("help : ");
//		hotPlayers = player.getDayHotPlayer(PlayerSortBy.assist);
//		for (MatchPlayerPO po : hotPlayers)
//		{
//			System.out.println(po.getHelp());
//		}
//		System.out.println("steals : ");
//		hotPlayers = player.getDayHotPlayer(PlayerSortBy.steal);
//		for (MatchPlayerPO po : hotPlayers)
//		{
//			System.out.println(po.getStealsNo());
//		}
	}

	@Test
	public void testGetSeasonHotPlayer() throws IOException {
		PlayerMatchVO[] playervos = player.getSeasonHotPlayer(PlayerSortBy.block);
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
