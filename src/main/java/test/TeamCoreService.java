package test;

import test.data.*;
import vo.SortType;
import vo.TeamSortBy;

public interface TeamCoreService {
    public TeamNormalInfo[] getTeamNormalAve(TeamSortBy sortBy, SortType sortType,int n);
    public TeamNormalInfo[] getTeamNormalTotal(TeamSortBy sortBy, SortType sortType,int n);
    public TeamHighInfo[]  getTeamHighInfo(TeamSortBy sortBy,SortType sortType,int n);
    public TeamHotInfo[]   getTeamHotInfo(String field, int n);
}
