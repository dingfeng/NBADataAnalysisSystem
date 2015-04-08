package bl.matchbl;

import gnu.trove.map.TIntObjectMap;

import java.util.Date;

import po.MatchesPO;
import blservice.matchblservice.Matchblservice;

public class MatchController implements Matchblservice
{
    Match match = Match.instance();
	@Override
	public void update() {
		match.update();
	}

	@Override
	public boolean changed() {
		return match.changed();
	}

	@Override
	public MatchesPO[] getTodayMatches() {
		return match.getTodayMatches();
	}

	@Override
	public MatchesPO[] getAllMatches() {
		return match.getAllMatches();
	}

	@Override
	public MatchesPO[] getRecentPlayerMatches(String playerName, int num) {
		return  match.getRecentPlayerMatches(playerName, num);
	}

	@Override
	public MatchesPO[] getRecentTeamMatches(String teamName, int num) {
		return match.getRecentTeamMatches(teamName, num);
	}

	@Override
	public MatchesPO[] getPlayerMatches(String playername) {
		return match.getPlayerMatches(playername);
	}

	@Override
	public MatchesPO[] getTeamMatches(String teamname) {
		return match.getTeamMatches(teamname);
	}

	@Override
	public TIntObjectMap<TeamQueue> getTeam_map() {
		return match.getTeam_map();
	}

	@Override
	public TIntObjectMap<PlayerQueue> getPlayer_map() {
		return match.getPlayer_map();
	}

	@Override
	public MatchesPO[] getTimeMatches(Date date1, Date date2) {
		return match.getTimeMatches(date1, date2);
	}

}
