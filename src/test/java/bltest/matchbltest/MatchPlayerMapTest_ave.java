package bltest.matchbltest;

import static org.junit.Assert.*;
import gnu.trove.map.TIntObjectMap;

import org.junit.Test;

import bl.matchbl.Match;
import bl.matchbl.PlayerQueue;

public class MatchPlayerMapTest_ave {

	@Test
	public void test() {
		System.out.println("player average data: ");
		Match match = Match.instance();
		TIntObjectMap<PlayerQueue> player_map = match.getPlayer_map();
		PlayerQueue[] players = new PlayerQueue[player_map.size()];
		for (PlayerQueue  q : players)
		{
			System.out.println(q.getAvePlayer());
		}
	}

}
