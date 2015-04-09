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

	@Test
	public void testGetAllMatches() {
		Match match = Match.instance();
	    MatchesPO[] allMatches = match.getAllMatches();
	    assertEquals(allMatches.length, 1230);
	}

	@Test
	public void testGetRecentPlayerMatches() 
	{
		Match match = Match.instance();
	    MatchesPO[] allMatches = match.getRecentPlayerMatches("Andre Iguodala", 5);
	    assertEquals(allMatches.length, 5);
	}

	@Test
	public void testGetRecentTeamMatches() {
		Match match = Match.instance();
	    MatchesPO[] allMatches = match.getRecentTeamMatches("POR", 5);
	    assertEquals(allMatches.length, 5);
	}
	 

	@Test
	public void testGetPlayerMatches() {
		Match match = Match.instance();
	    MatchesPO[] allMatches = match.getPlayerMatches("Devin Harris");
	    assertEquals(allMatches.length, 58);
	}

	@Test
	public void testGetTeamMatches() {
		Match match = Match.instance();
	    MatchesPO[] allMatches = match.getTeamMatches("DEN");
	    assertEquals(allMatches.length, 82);
	}

	@Test
	public void testGetTimeMatches() throws ParseException {
		String time1 = "2013-01-01";
		String time2 = "2013-01-02";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = format.parse(time1);
		Date date2 = format.parse(time2);
		Match match = Match.instance();
		MatchesPO[] matches = match.getTimeMatches(date1, date2);
		for (int i = 0; i < matches.length; i++)
		{
			System.out.println("date : " + matches[i].getDate());
		}
		assertEquals(matches.length,18);
	}
  
	
}
