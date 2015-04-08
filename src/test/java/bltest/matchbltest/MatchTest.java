package bltest.matchbltest;

import static org.junit.Assert.*;

import org.junit.Test;

import po.MatchesPO;
import bl.matchbl.Match;

public class MatchTest {

	@Test
	public void testGetTodayMatches() {
		Match match = Match.instance();
	    MatchesPO[] allMatches = match.getTodayMatches();
	    for (int i = 0; i < allMatches.length; i++)
	    {
	    	System.out.println(allMatches[i]);
	    }
	}
//
//	@Test
//	public void testGetAllMatches() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetRecentPlayerMatches() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetRecentTeamMatches() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetPlayerMatches() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetTeamMatches() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetTimeMatches() {
//		fail("Not yet implemented");
//	}
//  
//	
}
