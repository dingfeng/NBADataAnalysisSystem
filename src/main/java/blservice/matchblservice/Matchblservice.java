package blservice.matchblservice;

import gnu.trove.map.TIntObjectMap;

import java.util.Date;

import po.MatchesPO;
import bl.matchbl.PlayerQueue;
import bl.matchbl.TeamQueue;

public interface Matchblservice 
{
	public void update();
	public boolean changed();
    public MatchesPO[] getTodayMatches();
	public MatchesPO[] getAllMatches();
	public MatchesPO[] getRecentPlayerMatches(String playerName, int num);
	public MatchesPO[] getRecentTeamMatches(String teamName, int num);
	public MatchesPO[] getPlayerMatches(String playername);
	public MatchesPO[] getTeamMatches(String teamname);
    public MatchesPO[] getTimeMatches(Date date1, Date date2);
    public MatchesPO[] getTime_TeamMatches(Date date1, Date date2, String teamname, String playername);
    public void update1();
}
