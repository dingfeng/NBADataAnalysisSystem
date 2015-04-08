package blservice.teamblservice;

import java.util.Iterator;

import po.TeamPO;
import vo.Area;
import vo.SortType;
import vo.TeamMatchVO;
import vo.TeamSortBy;
import vo.TeamVO;

public interface Teamblservice
{
	public  TeamMatchVO[] getHotTeams(TeamSortBy sortby);
	public TeamMatchVO[] getSortedTeams(TeamSortBy sortby, SortType type);
	public String[] getPlayers(String team);
	public TeamMatchVO getTotalTeam(String teamname);
	public  TeamMatchVO getAveTeam(String teamname);
	public Iterator<TeamMatchVO> fuzzilyFindAve(String info);
	public Iterator<TeamMatchVO> fuzzilyFindTotal(String info);
	public TeamPO getTeamData(String team);
}
