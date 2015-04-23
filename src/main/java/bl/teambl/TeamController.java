package bl.teambl;

import java.util.Iterator;

import po.PlayerPO;
import po.TeamPO;
import bl.playerbl.Player;
import blservice.teamblservice.Teamblservice;
import vo.Area;
import vo.PlayerMatchVO;
import vo.SortType;
import vo.TeamMatchVO;
import vo.TeamSortBy;
import vo.TeamVO;

public class TeamController implements Teamblservice{
    Team team = new Team();
    Player player = new Player();
    //获得热门对球队
	public synchronized  TeamMatchVO[] getHotTeams(TeamSortBy sortby) {
		return team.getHotTeams(sortby);
	}

	//获得场均排序后的球队
	public synchronized TeamMatchVO[] getSortedAveTeams(TeamSortBy sortby, SortType type) {
		return team.getSortedAveTeams(sortby, type);
	}
    
	//获得赛季数据排序后的球队
	public synchronized TeamMatchVO[] getSortedTotalTeams(TeamSortBy sortby , SortType type)
	{
		return team.getSortedTotalTeams(sortby, type);
	}
	//获得该球队的所有球员名
	public synchronized String[] getPlayers(String team) {
		return  this.team.getPlayers(team);
	}

	//获得该球队的赛季数据
	public synchronized TeamMatchVO getTotalTeam(String teamname) {
		return this.team.getTotalTeam(teamname);
	}

	//获得该球队的场均数据
	public synchronized TeamMatchVO getAveTeam(String teamname) {
		return team.getAveTeam(teamname);
	}

	//模糊查找该球队的场均数据
	public synchronized Iterator<String> fuzzilyFind(String info) {
		return this.team.fuzzilyFind(info);
	}
	//获得球队的基本信息
	public synchronized TeamPO getTeamData(String team) {
		if (team.equals("NOH"))
			team = "NOP";
		return this.team.getTeamData(team);
	}

	@Override
	public synchronized PlayerMatchVO[] getAllPlayerMatchAve(String teamname) {
		String[] players = team.getPlayers(teamname);
		if (players == null) return null;
		PlayerMatchVO[] playerMatches = new PlayerMatchVO[players.length];
		for (int i = 0; i < players.length; i++)
		{
			playerMatches[i] = player.findPlayerMatchAve(players[i]);
		}
		return  playerMatches;
	}

	@Override
	public synchronized PlayerMatchVO[] getAllPlayerMatchTotal(String teamname) {
		String[] players = team.getPlayers(teamname);
		if (players == null) return null;
		PlayerMatchVO[] playerMatches = new PlayerMatchVO[players.length];
		for (int i = 0; i < players.length; i++)
		{
			playerMatches[i] = player.findPlayerMatchTotal(players[i]);
		}
		return  playerMatches;
	}

	@Override
	public synchronized PlayerPO getPlayerBase(String playername) {
		return player.findPlayer(playername);
	}

	@Override
	public synchronized  String[] getTeamNames() {
		return team.getSearchItems();
	}
	
}
