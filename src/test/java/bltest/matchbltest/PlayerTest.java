package bltest.matchbltest;

import static org.junit.Assert.*;

import org.junit.Test;

import vo.Area;
import vo.PlayerSortBy;
import vo.SortType;
import bl.playerbl.Player;

public class PlayerTest {

	@Test
	public void test() {
		Player player = new Player();
		try{
		player.findPlayer("");
	}catch(Exception e ){}
		try{
		player.findPlayerMatchAve("Kobe Bryant");
	}catch(Exception e ){}
		try{
		player.findPlayerMatchTotal("Kobe Bryant");
	}catch(Exception e ){}
		try{
		player.fuzzilyFind("Kobe");
	}catch(Exception e ){}
		try{
		player.getDayHotPlayer(PlayerSortBy.assist);
	}catch(Exception e ){}
		try{
		player.getPromotePlayer(PlayerSortBy.help_uprate);
	}catch(Exception e ){}
		try{
		player.getSearchItems();
	}catch(Exception e ){}
		try{
		player.getSeasonHotPlayer(PlayerSortBy.assist);
	}catch(Exception e ){}
		try{
		player.screenAvePlayers("F", Area.ATLANTIC, PlayerSortBy.assist);
		}catch(Exception e ){}
		try{
		player.screenTotalPlayers("F", Area.ATLANTIC, PlayerSortBy.assist);
	}catch(Exception e ){}
		try{
		player.sortAvePlayers(PlayerSortBy.assist, SortType.ASEND);
}catch(Exception e ){}
		try{
		player.sortTotalPlayers(PlayerSortBy.assist, SortType.ASEND);
	}catch(Exception e ){}
	}

}
