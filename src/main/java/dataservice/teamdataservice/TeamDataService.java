package dataservice.teamdataservice;

import java.util.ArrayList;
import java.util.Iterator;

import po.TeamPO;

public interface TeamDataService {
	//得到所有球队数据
	public Iterator<TeamPO> getAllTeamData();
	//得到单个球队数据
	public TeamPO getOneTeamData(String teamName);
}
