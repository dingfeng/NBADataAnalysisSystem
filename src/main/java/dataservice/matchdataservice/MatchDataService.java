package dataservice.matchdataservice;

import java.util.ArrayList;
import po.MatchesPO;

public interface MatchDataService {
	// 得到所有比赛中球员的数据
	public MatchesPO[] getAllMatchData();
	public MatchesPO[] getRecentMatchData();
}
