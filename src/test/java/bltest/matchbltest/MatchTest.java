package bltest.matchbltest;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import po.MatchesPO;
import bl.matchbl.Match;

public class MatchTest {

	@Test
	public void testGetTodayMatches() {
		Match match = Match.instance();
	    MatchesPO[] allMatches = match.getTodayMatches();
	    assertEquals(allMatches.length, 15);
	    
	}

}