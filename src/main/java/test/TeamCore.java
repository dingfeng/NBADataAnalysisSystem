package test;

import java.util.LinkedList;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import bl.matchbl.Match;
import bl.matchbl.TeamQueue;
import po.TeamPO;
import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;
import dataservice.teamdataservice.TeamDataService;
import test.data.TeamHighInfo;
import test.data.TeamHotInfo;
import test.data.TeamNormalInfo;
import tool.MinBinaryHeap;
import vo.SortType;
import vo.TeamMatchVO;
import vo.TeamSortBy;

public class TeamCore  implements TeamCoreService
{
  static TeamDataService teamData;
   
  TIntObjectMap<TeamQueue> team_map;
  TeamQueue[] queues;
  Match match;
  static TeamPO[] teampos;
  static TIntObjectMap<TeamPO> po_map;
  static
  {
	  po_map = new TIntObjectHashMap<TeamPO>(30);
	  NBADataFactory factory = DataFactoryImp.instance();
	  teamData = factory.getTeamData();
	  teampos = teamData.getAllTeamData();
	  for (TeamPO po : teampos)
	    {
	    	po_map.put(po.getNameAbridge().hashCode(), po);
	    }
  }
  public TeamCore()
  {
	  
	  
	  match = Match.instance();
	  team_map = match.getTeam_map();
	  queues = new TeamQueue[team_map.size()];
	  team_map.values(queues);
	  
	 
  }
  
@Override
public TeamNormalInfo[] getTeamNormalAve(TeamSortBy sortBy, SortType sortType,
		int n) {
	MinBinaryHeap<TeamMatchVO>  heap = new MinBinaryHeap(30);
	TeamMatchVO teamvo = null;
	int num = 0;
	for (TeamQueue q : queues)
	{
		teamvo = q.getTeamvoAverage();
		if (teamvo == null)
			continue;
		setSortBy(teamvo, sortBy,sortType);
		heap.insert(teamvo);
		++num;
	}
	if (num > n)
	{
		num = n;
	}
	if (num != 0)
	{
		TeamNormalInfo[]  resultInfos = new TeamNormalInfo[num];
		TeamNormalInfo temp = null;
		TeamMatchVO  teamMatchVO_temp = null;
		for (int i = 0; i < resultInfos.length; i++)
		{
			temp = new TeamNormalInfo();
			teamMatchVO_temp = heap.deleteMin();
			temp.setAssist(teamMatchVO_temp.getAssistNo());
			temp.setBlockShot(teamMatchVO_temp.getBlockNo());
			temp.setDefendRebound(teamMatchVO_temp.getDefenceRebs());
			temp.setFault(teamMatchVO_temp.getMistakesNo());
			temp.setFoul(teamMatchVO_temp.getFoulsNo());
			temp.setNumOfGame(teamMatchVO_temp.getMatchNo());
			temp.setOffendRebound(teamMatchVO_temp.getOffenseRebs());
			temp.setPenalty(teamMatchVO_temp.getPenaltyHitRate());
			temp.setPoint(teamMatchVO_temp.getPoints());
			temp.setRebound(teamMatchVO_temp.getRebs());
			temp.setShot(teamMatchVO_temp.getHitRate());
			temp.setSteal(teamMatchVO_temp.getStealsNo());
			temp.setTeamName(teamMatchVO_temp.getName());
			temp.setThree(teamMatchVO_temp.getThreeHitRate());
			resultInfos[i] = temp;
		}
		return resultInfos;
	}
	return null;
}

@Override
public TeamNormalInfo[] getTeamNormalTotal(TeamSortBy sortBy,
		SortType sortType, int n) {
	MinBinaryHeap<TeamMatchVO>  heap = new MinBinaryHeap(30);
	TeamMatchVO teamvo = null;
	int num = 0;
	for (TeamQueue q : queues)
	{
		teamvo = q.getTeamvoTotal();
		if (teamvo == null)
			continue;
		setSortBy(teamvo, sortBy,sortType);
		heap.insert(teamvo);
		++num;
	}
	if (num > n)
	{
		num = n;
	}
	if (num != 0)
	{
		TeamNormalInfo[]  resultInfos = new TeamNormalInfo[num];
		TeamNormalInfo temp = null;
		TeamMatchVO  teamMatchVO_temp = null;
		for (int i = 0; i < resultInfos.length; i++)
		{
			temp = new TeamNormalInfo();
			teamMatchVO_temp = heap.deleteMin();
			temp.setAssist(teamMatchVO_temp.getAssistNo());
			temp.setBlockShot(teamMatchVO_temp.getBlockNo());
			temp.setDefendRebound(teamMatchVO_temp.getDefenceRebs());
			temp.setFault(teamMatchVO_temp.getMistakesNo());
			temp.setFoul(teamMatchVO_temp.getFoulsNo());
			temp.setNumOfGame(teamMatchVO_temp.getMatchNo());
			temp.setOffendRebound(teamMatchVO_temp.getOffenseRebs());
			temp.setPenalty(teamMatchVO_temp.getPenaltyHitRate());
			temp.setPoint(teamMatchVO_temp.getPoints());
			temp.setRebound(teamMatchVO_temp.getRebs());
			temp.setShot(teamMatchVO_temp.getHitRate());
			temp.setSteal(teamMatchVO_temp.getStealsNo());
			temp.setTeamName(teamMatchVO_temp.getName());
			temp.setThree(teamMatchVO_temp.getThreeHitRate());
			resultInfos[i] = temp;
		}
		return resultInfos;
	}
	return null;
}

@Override
public TeamHighInfo[] getTeamHighInfo(TeamSortBy sortBy,SortType sortType, int n) {
	MinBinaryHeap<TeamMatchVO>  heap = new MinBinaryHeap(30);
	TeamMatchVO teamvo = null;
	int num = 0;
	for (TeamQueue q : queues)
	{
		teamvo = q.getTeamvoAverage();
		if (teamvo == null)
			continue;
		setSortBy(teamvo, sortBy,sortType);
		heap.insert(teamvo);
		++num;
	}
	if (num > n)
	{
		num = n;
	}
	if (num != 0)
	{
		TeamHighInfo[]  resultInfos = new TeamHighInfo[num];
		TeamHighInfo temp = null;
		TeamMatchVO  teamMatchVO_temp = null;
		for (int i = 0; i < resultInfos.length; i++)
		{
			temp = new TeamHighInfo();
			teamMatchVO_temp = heap.deleteMin();
			temp.setAssistEfficient(teamMatchVO_temp.getAssistEfficiency());
			temp.setDefendEfficient(teamMatchVO_temp.getDefenceEfficiency());
			temp.setDefendReboundEfficient(teamMatchVO_temp.getdRebsEfficiency());
			temp.setOffendEfficient(teamMatchVO_temp.getOffenseEfficiency());
			temp.setOffendReboundEfficient(teamMatchVO_temp.getoRebsEfficiency());
			temp.setOffendRound(teamMatchVO_temp.getOffenseRound());
			temp.setStealEfficient(teamMatchVO_temp.getStealsEfficiency());
			temp.setTeamName(teamMatchVO_temp.getName());
			temp.setWinRate(teamMatchVO_temp.getWinRate());
			resultInfos[i] = temp;
		}
		return resultInfos;
	}
	return null;
}

@Override
public TeamHotInfo[] getTeamHotInfo(String field, int n) {
	MinBinaryHeap<TeamMatchVO>  heap = new MinBinaryHeap(30);
	TeamMatchVO teamvo = null;
	int num = 0;
	for (TeamQueue q : queues)
	{
		teamvo = q.getTeamvoAverage();
		if (teamvo == null)
			continue;
		setSortBy(teamvo, getHotSortBy(field),SortType.DESEND);
		heap.insert(teamvo);
		++num;
	}
	if (num > n)
	{
		num = n;
	}
	if (num != 0)
	{
		TeamHotInfo[]  resultInfos = new TeamHotInfo[num];
		TeamHotInfo temp = null;
		TeamMatchVO  teamMatchVO_temp = null;
		String teamName = null;
	    TeamPO teampo = null;
		for (int i = 0; i < resultInfos.length; i++)
		{
			temp = new TeamHotInfo();
			teamMatchVO_temp = heap.deleteMin();
			temp.setField(field);
			temp.setValue(teamMatchVO_temp.getHotData());
			
			teamName = teamMatchVO_temp.getName();
			teampo = findTeam(teamName);
			
			temp.setLeague(teampo.getMatchArea());
			temp.setTeamName(teamName);
			resultInfos[i] = temp;
		}
		return resultInfos;
	}
	return null;
}


public  static TeamPO findTeam(String teamName)
{
	return po_map.get(teamName.hashCode());
}

private void setSortBy(TeamMatchVO team, TeamSortBy sortby, SortType type)
{
	double doubleSort = 0;
	switch (sortby)
	{

	case	matchNo: // 比赛场数
		doubleSort = team.getMatchNo();
		break;
	case hitNo:// 投篮命中数
		doubleSort = team.getHitNo();
		break;
	case	handNo: // 投篮出手次数
		doubleSort = team.getHandNo();
		break;
	case threeHitNo: // 三分命中数
		doubleSort = team.getThreeHitNo();
		break;
	case threeHandNo: // 三分出手数
		doubleSort = team.getThreeHandNo();
		break;
	case penaltyHitNo:// 罚球命中数
		doubleSort = team.getPenaltyHitNo();
		break;
	case	penaltyHandNo: // 罚球出手数
		doubleSort = team.getPenaltyHandNo();
		break;
	case	 offenseRebs: // 进攻篮板数
		doubleSort = team.getOffenseRebs();
		break;
	case defenceRebs: // 防守篮板数
		doubleSort = team.getDefenceRebs();
		break;
	case rebs: // 篮板数
		doubleSort = team.getRebs();
		break;
	case assistNo:// 助攻数
		doubleSort = team.getAssistNo();
		break;
	case stealsNo: // 抢断数
		doubleSort = team.getStealsNo();
		break;
	case	blockNo: // 盖帽数
		doubleSort = team.getBlockNo();
		break;
	case	 mistakesNo: // 失误数
		doubleSort = team.getMistakesNo();
		break;
	case foulsNo: // 犯规数
		doubleSort = team.getFoulsNo();
		break;
	case points: // 比赛得分
		doubleSort = team.getPoints();
		break;
	case hitRate: // 投篮命中率
		doubleSort = team.getHitRate();
		break;
	case threeHitRate:// 三分命中率
		doubleSort = team.getThreeHitRate();
		break;
	case penaltyHitRate:// 罚球命中率
		doubleSort = team.getPenaltyHitRate();
		break;
	case winRate: // 胜率
		doubleSort = team.getWinRate();
		break;
	case offenseRound: // 进攻回合
		doubleSort = team.getOffenseRound();
		break;
	case offenseEfficiency:// 进攻效率
		doubleSort = team.getOffenseEfficiency();
		break;
	case defenceEfficiency:// 防守效率
		doubleSort = team.getDefenceEfficiency();
		break;
	case orebsEfficiency:// 进攻篮板效率
		doubleSort = team.getoRebsEfficiency();
		break;
	case drebsEfficiency: //防守篮板效率
		doubleSort = team.getdRebsEfficiency();
		break;
	case stealsEfficiency:// 抢断效率
		doubleSort = team.getStealsEfficiency();
		break;
	case assistEfficiency:// 助攻率
		doubleSort = team.getAssistEfficiency();
		break;
	}
	team.setTeamSortTool(doubleSort, type);
}



private TeamSortBy getHotSortBy(String field)
{
	TeamSortBy sortBy = null;
	switch (field)
	{
	case "score":
		sortBy = TeamSortBy.points;
		break;
	case "rebound":
		sortBy = TeamSortBy.rebs;
		break;
	case  "assist":
		sortBy = TeamSortBy.assistNo;
		break;
	case  "blockShot":
		sortBy = TeamSortBy.blockNo;
		break;
	case  "steal":
		sortBy = TeamSortBy.stealsNo;
		break;
	case  "foul":
		sortBy = TeamSortBy.foulsNo;
		break;
	case  "fault":
		sortBy = TeamSortBy.mistakesNo;
		break;
	case  "shot":
		sortBy = TeamSortBy.hitRate;
		break;
	case  "three":
		sortBy = TeamSortBy.threeHitRate;
		break;
	case  "penalty":
		sortBy = TeamSortBy.penaltyHitRate;
		break;
	case   "defendRebound" :
		sortBy = TeamSortBy.defenceRebs;
		break;
	case   "offendRebound":
		sortBy = TeamSortBy.offenseRebs;
		break;
	}
	return sortBy;
}

}
