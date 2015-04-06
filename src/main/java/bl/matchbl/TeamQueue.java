package bl.matchbl;

import bl.teambl.Team;
import gnu.trove.map.TIntObjectMap;
import vo.TeamVO;

public class TeamQueue extends Queue{
    
	private TeamVO   teamvo;	
	public TeamQueue(int max, String name) 
	{
		super(max, name);
	}
	
    public TeamVO getTeamvo()
    {
    	return teamvo;
    }
	
	public void update()
	{
		
	}
	
}
