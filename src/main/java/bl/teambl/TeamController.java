package bl.teambl;

import java.util.Iterator;

import blservice.teamblservice.Teamblservice;
import vo.Area;
import vo.SortType;
import vo.TeamSortBy;
import vo.TeamVO;

public class TeamController implements Teamblservice{
	public Iterator<TeamVO> sortTeams(TeamSortBy teamSortBy, SortType sortType) {
		return null;
	}

	public void setAverage(boolean average)
	{
	}

	public TeamVO findTeam(String name) {
		return null;
	}
	
	public Iterator<TeamVO> getAllTeams()
	{
		return null;
	}

	public TeamVO getRecentData(String teamName, int matchNo) {
		return null;
	}
    
	public Iterator<TeamVO> getHotTeams(TeamSortBy sortBy) {
		return null;
	}


}
