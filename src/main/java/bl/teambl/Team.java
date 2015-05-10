package bl.teambl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import po.TeamPO;
import dataservice.teamdataservice.TeamDataService;
import vo.Area;
import vo.SortType;
import vo.TeamMatchVO;
import vo.TeamSortBy;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;
import bl.matchbl.Match;
import bl.matchbl.TeamQueue;
import bl.playerbl.SearchItemProvider;
import blservice.teamblservice.Teamblservice;

public class Team   implements SearchItemProvider
{
	private	TIntObjectMap<TeamQueue> team_map;
	private Match match;
	private TeamDataService teamData;
	private TIntObjectMap<TeamPO> po_map;
	private TeamPO[] teams;
	private final static int TEAM_NUM = 30;
	ArrayList<String> list = new ArrayList<String>();
	public Team()
	{
		match = Match.instance();
		team_map = match.getTeam_map();
		NBADataFactory factory = DataFactoryImp.instance();
		teamData = factory.getTeamData();
	    po_map = new TIntObjectHashMap<TeamPO>();
	    teams = teamData.getAllTeamData();
	    Arrays.sort(teams);
	    for (TeamPO po : teams)
	    {
	    	po_map.put(po.getNameAbridge().hashCode(), po);
	    }
	}
	
	//获得球员的赛季总数居
	public TeamMatchVO[] getAllTotalTeams()
	{
		
		TeamQueue[] team_queues = new TeamQueue[team_map.size()];
		team_map.values(team_queues);
		TeamMatchVO[] allMatchVOs = new TeamMatchVO[team_queues.length];
		for (int i = 0; i < team_queues.length; i++)
		{
			allMatchVOs[i] = team_queues[i].getTeamvoTotal();
		}
		return allMatchVOs;
	}
	//获得场均数据
	public TeamMatchVO[] getAllAveTeams()
	{
		TeamQueue[] team_queues = new TeamQueue[team_map.size()];
		team_map.values(team_queues);
		TeamMatchVO[] allMatchVOs = new TeamMatchVO[team_queues.length];
		for (int i = 0; i < team_queues.length; i++)
		{
			allMatchVOs[i] = team_queues[i].getTeamvoAverage();
		}
		return allMatchVOs;
	}
	//获得热点球队
	public  TeamMatchVO[] getHotTeams(TeamSortBy sortby)
	{
		 return getHotTeams(sortby,5);
	}
     
	//获得一定数量的热点球队
	public TeamMatchVO[] getHotTeams(TeamSortBy sortby, int num)
	{
		TeamMatchVO[] teams = getSortedAveTeams(sortby,SortType.DESEND);
        TeamMatchVO[] result = new TeamMatchVO[num];
        for (int i = 0; i < num; i++)
        {
        	result[i] = teams[i];
        }
		return result;
	}
	
	//对球队的场均的数据进行排序
	public TeamMatchVO[] getSortedAveTeams(TeamSortBy sortby, SortType type)
	{
		TeamQueue[] teams = new TeamQueue[team_map.size()];
		team_map.values(teams);
		TeamMatchVO[] team_ms = new TeamMatchVO[TEAM_NUM];
		if (sortby == TeamSortBy.name)
		{
			if (type == SortType.ASEND)
			{
				for ( int i = 0; i < this.teams.length; i++)
				{
					String teamname = this.teams[i].getNameAbridge();
					if (teamname.equals("NOP"))
						teamname = "NOH";
					team_ms[i] = getAveTeam(teamname);
				}
			}
			else 
			{
				for (int i = this.teams.length - 1; i >= 0; --i)
				{
					String teamname = this.teams[i].getNameAbridge();
					if (teamname.equals("NOP"))
						teamname = "NOH";
					team_ms[this.teams.length-1-i] = getAveTeam(teamname);
				}
			}
			return team_ms;
		}
		
		for (int i = 0; i < TEAM_NUM; i++)
		{
			team_ms[i] = teams[i].getTeamvoAverage();
			setSortBy(team_ms[i],sortby,type);
		}
        Arrays.sort(team_ms);
		return team_ms;
	}

	//对球队的赛季数据进行排序
	public TeamMatchVO[] getSortedTotalTeams(TeamSortBy sortby, SortType type) {
		TeamQueue[] teams = new TeamQueue[team_map.size()];
		team_map.values(teams);
		TeamMatchVO[] team_ms = new TeamMatchVO[TEAM_NUM];
		if (sortby == TeamSortBy.name)
		{
			if (type == SortType.ASEND)
			{
				for ( int i = 0; i < this.teams.length; i++)
				{
					String teamname = this.teams[i].getNameAbridge();
					if (teamname.equals("NOP"))
						teamname = "NOH";
					team_ms[i] = getTotalTeam(teamname);
				}
			}
			else 
			{
				for (int i = this.teams.length - 1; i >= 0; --i)
				{
					String teamname = this.teams[i].getNameAbridge();
					if (teamname.equals("NOP"))
						teamname = "NOH";
					team_ms[this.teams.length-1-i] = getTotalTeam(teamname);
				}
			}
			return team_ms;
		}
		
		for (int i = 0; i < TEAM_NUM; i++)
		{
			team_ms[i] = teams[i].getTeamvoTotal();
			setSortBy(team_ms[i],sortby,type);
		}
        Arrays.sort(team_ms);
		return team_ms;
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
			break;		case drebsEfficiency: //防守篮板效率
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
	
	//球队的缩写名
	public String[] getPlayers(String team)
	{
		return team_map.get(team.hashCode()).getAllPlayers();
	}
	
//	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException
//	{
//		Team team = new Team();
//		HashMap<String, String[]> map = new HashMap<String, String[]>();
//		for (String t : teamnames)
//		{
//			map.put(t, team.getPlayers(t));
//		}
//		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("teamPlayerMap/teamplayer"));
//		oos.writeObject(map);
//		oos.close();
//		ObjectInputStream ois  = new ObjectInputStream(new FileInputStream("teamPlayerMap/teamplayer"));
//		map = (HashMap<String, String[]>) ois.readObject();
//		ois.close();
//		for (String t : teamnames)
//		{
//			String[] players = map.get(t);
//			for (int i = 0; i < players.length; i++)
//			{
//				System.out.println(players[i]);
//			}
//		}
//	}
//	
	
	public TeamMatchVO getTotalTeam(String teamname)
	{
		return team_map.get(teamname.hashCode()).getTeamvoTotal();
	}
	
	public  TeamMatchVO getAveTeam(String teamname)
	{
		return team_map.get(teamname.hashCode()).getTeamvoAverage();
	}
	
	//获得某球队的基本信息
	public TeamPO getTeamData(String team)
	{
		TeamPO teampo = null;
		for (int i = 0; i < teams.length; i++)
		{
			teampo = teams[i];
			if (teampo.getName().equals(team) || teampo.getNameAbridge().equals(team))
			{
				return teampo;
			}
		}
		return  null;
	}
	
	//模糊查找球队
	public Iterator<String> fuzzilyFind(String info) {
		LinkedList<String>  team_list = new LinkedList<String>();
		for (TeamPO team : teams)
		{
			if (team.getName().startsWith(info))
			{
				team_list.add(team.getName());
			}
		}
		return team_list.iterator();
	}

	//获得球员的赛区
	public String getPlayerArea(String playername)
	{
		String teamname = null;
		for (TeamPO t : teams)
		{
			teamname = t.getNameAbridge();
			if (teamname.equals("NOP"))
				teamname = "NOH";
			if (team_map.get(teamname.hashCode()).hasPlayer(playername))
			{
			  return t.getPlayerArea();
			}
		}
		return null;
	}

	//获得搜索选项
	public String[] getSearchItems() {
		String[] teamNames = new String[teamnames.length *2];
		for (int i = 0;i < teamnames.length; i++)
		{
			teamNames[i] = team_total_names[i];
			teamNames[i+teamnames.length] = teamnames[i];  
		}
		
		return teamNames;
	}
	

	public static String[] teamnames = new String[]
			{
		"ATL",
    	"BKN",
    	"BOS",
    	"CHA",
    	"CHI",
    	"CLE",
    	"DAL",
    	"DEN",
    	"DET",
    	"GSW",
    	"HOU",
    	"IND",
    	"LAC",
    	"LAL",
    	"MEM",
    	"MIA",
    	"MIL",
    	"MIN",
    	"NOH",
    	"NYK",
    	"OKC",
    	"ORL",
    	"PHI",
    	"PHX",
    	"POR",
    	"SAC",
    	"SAS",
    	"TOR",
    	"UTA",
    	"WAS"
			};
	
	public static String[] team_total_names = new String[]
			{
		"Hawks",
		"Nets",
		"Celtics",
		"Hornets",
		"Bulls",
		"Cavaliers",
		"Mavericks",
		"Nuggets",
		"Pistons",
		"Warriors",
		"Rockets",
		"Pacers",
		"Clippers",
		"Lakers",
		"Grizzlies",
		"Heat",
		"Bucks",
		"Timberwolves",
		"Pelicans",
		"Knicks",
		"Thunder",
		"Magic",
		"ers",
		"Suns",
		"Trail Blazers",
		"Kings",
		"Spurs",
		"Raptors",
		"Jazz",
		"Wizards"
			};
	
	
}
