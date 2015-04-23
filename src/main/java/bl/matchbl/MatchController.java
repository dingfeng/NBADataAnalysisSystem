package bl.matchbl;

import gnu.trove.map.TIntObjectMap;

import java.util.Date;

import po.MatchesPO;
import blservice.matchblservice.Matchblservice;

public class MatchController implements Matchblservice
{
    Match match = Match.instance();
    //更新数据
	public synchronized void update() {
		match.update();
	}
    //数据是否改变
	public synchronized  boolean changed() {
		return match.changed();
	}

	//获得今日的比赛数据
	public synchronized MatchesPO[] getTodayMatches() {
		return match.getTodayMatches();
	}

    //获得所有的比赛数据
	public synchronized MatchesPO[] getAllMatches() {
		return match.getAllMatches();
	}

	//获得某球员最近的几场比赛数据
	public synchronized MatchesPO[] getRecentPlayerMatches(String playerName, int num) {
		return  match.getRecentPlayerMatches(playerName, num);
	}

	//获得某球队最近的几场比赛数据
	public synchronized MatchesPO[] getRecentTeamMatches(String teamName, int num) {
		return match.getRecentTeamMatches(teamName, num);
	}

	//获得某球员所有的比赛数据
	public synchronized MatchesPO[] getPlayerMatches(String playername) {
		return match.getPlayerMatches(playername);
	}

	//获得某球队所有的比赛数据
	public synchronized MatchesPO[] getTeamMatches(String teamname) {
		return match.getTeamMatches(teamname);
	}

	
	//获得在某一时间区间内的所有比赛信息
	public synchronized MatchesPO[] getTimeMatches(Date date1, Date date2) {
		return match.getTimeMatches(date1, date2);
	}
	
	public synchronized MatchesPO[] getTime_TeamMatches(Date date1, Date date2,
			String teamname , String playername) {
		return match.getTime_TeamMatches(date1, date2, teamname, playername);
	}
	@Override
	public synchronized  void update1() {
     match.update1();		
	}

}
