package bl.matchbl;

import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import bl.teambl.Team;
import gnu.trove.map.TIntObjectMap;
import vo.TeamMatchVO;
import vo.TeamVO;

public class TeamQueue extends Queue{  
	private TeamMatchVO   teamvo_total;
	private TeamMatchVO teamvo_average;
	private String[] players = new String[25];
	private int len = -1;
	public TeamQueue(int max, String name) 
	{
		super(max, name);
		
	}
	
	private void addPlayer(String playername)
	{
		for (int i = 0; i <= len; i++)
		{
			
			if (players[i].equals(playername)) return;
		}
		players[++len] = playername;
	}
	
	public  String[] getAllPlayers()
	{
		if (len == -1)
			return null;
		String[] pls = new String[len+1];
		for (int i = 0; i <= len; i++)
		{
			pls[i] = players[i];
		}
		return pls;
	}
	
    public TeamMatchVO getTeamvoTotal()
    {
    	return teamvo_total;
    }
    
    public TeamMatchVO getTeamvoAverage()
    {
    	return teamvo_average;
    }
	
	public void update()
	{
		
		 int matchNo = 0; // 比赛场数
		 double hitNo = 0; // 投篮命中数
		 double handNo = 0; // 投篮出手次数
		 double threeHitNo = 0; // 三分命中数
		 double threeHandNo = 0; // 三分出手数
		 double penaltyHitNo = 0; // 罚球命中数
		 double penaltyHandNo = 0; // 罚球出手数
		 double offenseRebs = 0; // 进攻篮板数
		 double defenceRebs = 0; // 防守篮板数
		 double rebs = 0; // 篮板数
		 double assistNo = 0; // 助攻数
		 double stealsNo = 0; // 抢断数
		 double blockNo = 0; // 盖帽数
		 double mistakesNo = 0; // 失误数
		 double foulsNo = 0; // 犯规数
		 double points = 0; // 比赛得分
		 int win = 0;
		 double defenceRound = 0; //防守回合
		 double offenseRound = 0; // 进攻回合
		 int yourPoints  = 0;     //
         int yourOffenseRebs = 0;
         int yourDefenceRebs = 0;
		MatchTeamPO team1 = null;
		MatchTeamPO team2 = null;
		MatchTeamPO temp = null;
		MatchPlayerPO [] players = null;
		for (MatchesPO match : matches)
		{
			++matchNo;
			team1 = match.getTeam1();
			team2 = match.getTeam2();
			if (team2.getName().equals(name))
			{
				temp = team1;
				team1 = team2;
				team2 = temp;
			}
			points += team1.getTotalScores();
			yourPoints += team2.getTotalScores();
			if ( team1.getTotalScores() > team2.getTotalScores())
			{
				++win;
			}
			players = team1.getPlayers();
			 int hitNo0 = 0; // 投篮命中数
			 int handNo0 = 0; // 投篮出手次数
			 int penaltyHandNo0 = 0; // 罚球出手数
			 int offenseRebs0 = 0; // 进攻篮板数
			 int mistakesNo0 = 0; // 失误数
			 int defenceRebs0 = 0;
			for (MatchPlayerPO player : players)
			{
				  addPlayer(player.getName());
				  hitNo0 += player.getHitNo(); // 投篮命中数
				  handNo0 += player.getHandNo(); // 投篮出手次数
				  threeHitNo += player.getThreeHitNo(); // 三分命中数
				  threeHandNo += player.getThreeHandNo(); // 三分出手数
				  penaltyHitNo += player.getPenaltyHitNo(); // 罚球命中数
				  penaltyHandNo0 += player.getPenaltyHandNo(); // 罚球出手数
				  offenseRebs0 += player.getOffenseRebs(); // 进攻篮板数
				  defenceRebs0 += player.getDefenceRebs(); // 防守篮板数
				  rebs += player.getRebs(); // 篮板数
				  assistNo += player.getHelp(); // 助攻数
				  stealsNo += player.getStealsNo(); // 抢断数
				  blockNo += player.getBlockNo(); // 盖帽数
				  mistakesNo0 += player.getMistakesNo(); // 失误数
				  foulsNo += player.getFoulsNo(); // 犯规数
			}
			defenceRebs += defenceRebs0;
			hitNo += hitNo0;
			handNo += handNo0;
			penaltyHandNo += penaltyHandNo0;
			offenseRebs += offenseRebs0;
			mistakesNo += mistakesNo0;
	    	 int yourHitNo = 0;
	         int yourHandNo = 0;
	         int yourPenaltyNo = 0;
	         int yourOffenseRebs0 = 0;
	         int yourDefenceRebs0 = 0; 
	         int yourMistakeNo = 0;
			players = team2.getPlayers();
			for (MatchPlayerPO player : players)
			{
				yourOffenseRebs0 += player.getOffenseRebs();
				yourDefenceRebs0 += player.getDefenceRebs();
				yourHitNo += player.getHitNo();
				yourHandNo += player.getHandNo();
				yourMistakeNo += player.getMistakesNo();
				yourPenaltyNo += player.getPenaltyHandNo();
			}
			yourOffenseRebs += yourOffenseRebs0;
			yourDefenceRebs += yourDefenceRebs0;
			defenceRound += yourHandNo + 0.4 * yourPenaltyNo -
		    1.07 * (1.0 * yourOffenseRebs0 / (yourOffenseRebs0 + defenceRebs0) * (yourHandNo - yourHitNo))
		    + 1.07 * yourMistakeNo;
			offenseRound += handNo0 + 0.4 * penaltyHandNo0 -
					1.07 * (1.0 * offenseRebs0/(offenseRebs0+defenceRebs0)*(handNo0-hitNo0))
			        + 1.07 * mistakesNo0;
		}
		 double hitRate =  hitNo / handNo; // 投篮命中率
		 double threeHitRate = threeHitNo / threeHandNo;// 三分命中率
		 double penaltyHitRate = penaltyHitNo / penaltyHandNo;// 罚球命中率
		 double winRate = 1.0 * win / matchNo; // 胜率
		 double offenseEfficiency = 100 *  points /  offenseRound ;// 进攻效率
		 double defenceEfficiency = 100 * yourPoints / defenceRound;// 防守效率
		 double orebsEfficiency = offenseRebs / (offenseRebs + yourDefenceRebs);// 篮板效率
		 double drebsEfficiency =  defenceRebs / (defenceRebs + yourOffenseRebs);
		 double stealsEfficiency = 100 * stealsNo / defenceRound;// 抢断效率
		 double assistEfficiency = 100 * assistNo / offenseRound;// 助攻率
		 
		 teamvo_total = new TeamMatchVO( name,  matchNo,  hitNo,  handNo,
					 threeHitNo,  threeHandNo,  penaltyHitNo,
					 penaltyHandNo,  offenseRebs,  defenceRebs,  rebs,
					 assistNo,  stealsNo,  blockNo,  mistakesNo,
					 foulsNo,  points,  hitRate,  threeHitRate,
					 penaltyHitRate,  winRate,  offenseRound,
					 offenseEfficiency,  defenceEfficiency,
					 orebsEfficiency, drebsEfficiency , stealsEfficiency,
					 assistEfficiency);
		 teamvo_average = new TeamMatchVO(name,  matchNo,  hitNo / matchNo,  handNo/ matchNo,
				 threeHitNo/ matchNo,  threeHandNo/ matchNo,  penaltyHitNo/ matchNo,
				 penaltyHandNo/ matchNo,  offenseRebs/ matchNo,  defenceRebs/ matchNo,  rebs/ matchNo,
				 assistNo/ matchNo,  stealsNo/ matchNo,  blockNo/ matchNo,  mistakesNo/ matchNo,
				 foulsNo/ matchNo,  points/ matchNo,  hitRate,  threeHitRate,
				 penaltyHitRate,  winRate,  offenseRound,
				 offenseEfficiency,  defenceEfficiency,
				 orebsEfficiency, drebsEfficiency , stealsEfficiency,
				 assistEfficiency);
	}
	
}
