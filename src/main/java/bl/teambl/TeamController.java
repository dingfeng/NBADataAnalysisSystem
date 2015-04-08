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
	@Override
	public TeamMatchVO[] getHotTeams(TeamSortBy sortby) {
		return team.getHotTeams(sortby);
	}

	@Override
	public TeamMatchVO[] getSortedTeams(TeamSortBy sortby, SortType type) {
		return team.getSortedTeams(sortby, type);
	}

	@Override
	public String[] getPlayers(String team) {
		return  this.team.getPlayers(team);
	}

	@Override
	public TeamMatchVO getTotalTeam(String teamname) {
		return this.team.getTotalTeam(teamname);
	}

	@Override
	public TeamMatchVO getAveTeam(String teamname) {
		return this.getAveTeam(teamname);
	}

	@Override
	public Iterator<TeamMatchVO> fuzzilyFindAve(String info) {
		return this.team.fuzzilyFindAve(info);
	}

	@Override
	public Iterator<TeamMatchVO> fuzzilyFindTotal(String info) {
		return this.team.fuzzilyFindTotal(info);
	}

	@Override
	public TeamPO getTeamData(String team) {
		return this.team.getTeamData(team);
	}
	
}
