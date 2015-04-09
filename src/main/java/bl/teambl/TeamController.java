package bl.teambl;

import java.util.Iterator;

import po.TeamPO;
import blservice.teamblservice.Teamblservice;
import vo.Area;
import vo.SortType;
import vo.TeamMatchVO;
import vo.TeamSortBy;
import vo.TeamVO;

public class TeamController implements Teamblservice{
    Team team = new Team();
    //获得热门对球队
	public TeamMatchVO[] getHotTeams(TeamSortBy sortby) {
		return team.getHotTeams(sortby);
	}

	//获得场均排序后的球队
	public TeamMatchVO[] getSortedAveTeams(TeamSortBy sortby, SortType type) {
		return team.getSortedAveTeams(sortby, type);
	}
    
	//获得赛季数据排序后的球队
	public TeamMatchVO[] getSortedTotalTeams(TeamSortBy sortby , SortType type)
	{
		return team.getSortedTotalTeams(sortby, type);
	}
	//获得该球队的所有球员名
	public String[] getPlayers(String team) {
		return  this.team.getPlayers(team);
	}

	//获得该球队的赛季数据
	public TeamMatchVO getTotalTeam(String teamname) {
		return this.team.getTotalTeam(teamname);
	}

	//获得该球队的场均数据
	public TeamMatchVO getAveTeam(String teamname) {
		return this.getAveTeam(teamname);
	}

	//模糊查找该球队的场均数据
	public Iterator<String> fuzzilyFind(String info) {
		return this.team.fuzzilyFind(info);
	}
	//获得球队的基本信息
	public TeamPO getTeamData(String team) {
		return this.team.getTeamData(team);
	}
	
}
