package blservice.teamblservice;

import java.util.Iterator;

import vo.Area;
import vo.SortType;
import vo.TeamSortBy;
import vo.TeamVO;

public interface Teamblservice
{
	 public Iterator<TeamVO> sortTeams(TeamSortBy TeamSortBy, SortType sortType);
	 public void setAverage(boolean average);
     public TeamVO findTeam(String name);
     public Iterator<TeamVO>  getAllTeams();
     
}
