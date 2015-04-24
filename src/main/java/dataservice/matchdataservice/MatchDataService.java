package dataservice.matchdataservice;

import java.util.ArrayList;
import po.MatchesPO;

public interface MatchDataService {
	// 得到所有比赛中球员的数据
	public MatchesPO[] getAllMatches();
	public MatchesPO[] getTodayMatches();
//	public MatchesPO[] getRecentPlayerMatches(String playerName, int num);
//	public MatchesPO[] getRecentTeamMatches(String teamName, int num);
	public boolean changed();
	public MatchesPO[] getNewMatches();
	public void updateData();
}
