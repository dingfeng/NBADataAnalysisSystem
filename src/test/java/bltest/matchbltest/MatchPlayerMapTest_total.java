package bltest.matchbltest;

import static org.junit.Assert.*;
import gnu.trove.map.TIntObjectMap;

import org.junit.Test;

import bl.matchbl.Match;
import bl.matchbl.PlayerQueue;

public class MatchPlayerMapTest_total {

	@Test
	public void test() {
		Match match = Match.instance();
		TIntObjectMap<PlayerQueue> player_map = match.getPlayer_map();
		PlayerQueue[]  players = new PlayerQueue[player_map.size()];
		player_map.values(players);
		for (PlayerQueue q : players)
		{
//			if(q.getName().equals("Kobe Bryant"))
			   System.out.println(q.getTotalPlayer().getBlockEfficiency());
		}
	}

}
