package bl.matchbl;

import po.MatchPlayerPO;
import vo.PlayerMatchVO;
import vo.PlayerVO;

public class PlayerQueue {
    private MatchPlayerPO[] match_datas;
    private int len = -1;
    private String name;
    private PlayerMatchVO playervo_total;
    private PlayerMatchVO playervo_ave ;
    private String teamname;
	private double teamTotalTime;
	private int yourRebs;
	private int totalHit;
	private int yourTwoPoints;
	private double yourAttackNO;
	private int teamHand;
	private int teamPenalty;
	private int teamMistakes;
    private int low ;	
	private int firstServiceNo;
	private int myRebs;
	private int time_dirty;
	public PlayerQueue(int max, String name) 
	{
		match_datas = new MatchPlayerPO[max];
		this.name = name;
	}
	
	public void enqueue(MatchPlayerPO player, double teamTotalTime
			,int yourRebs, int totalHit,int  yourTwoPoints, double yourAttackNO,
			int teamHand, int teamPenalty, int teamMistakes, int firstServiceNo, int myRebs, String teamname
			)
			{
		   match_datas[++len] = player;
		   this.teamname = teamname;
		   this.teamTotalTime += teamTotalTime;
		   this.yourRebs += yourRebs;
		   this.totalHit += totalHit;
		   this.yourTwoPoints += yourTwoPoints;
		   this.yourAttackNO += yourAttackNO;
		   this.teamHand += teamHand;
		   this.teamPenalty += teamPenalty;
		   this.teamMistakes += teamMistakes;
		   this.firstServiceNo += firstServiceNo;
		   this.myRebs += myRebs;
			}
	public String getName()
	{
		return name;
	}
	
	public void update() 
	{
		if (len  == -1)
			return;
		     double time = 0;// 在场时间
			 int hitNo = 0; // 投篮命中数
			 int handNo = 0; // 投篮出手次数
			 int threeHitNo = 0 ; // 三分命中数
			 int threeHandNo = 0 ; // 三分出手数
			 int penaltyHitNo = 0; // 罚球命中数
			 int penaltyHandNo = 0; // 罚球出手数
			 int offenseRebs =0 ; // 进攻篮板数
			 int defenceRebs = 0; // 防守篮板数
			 int rebs = 0; // 篮板数
			 int help = 0; // 助攻数
			 int stealsNo = 0;// 抢断数
			 int blockNo = 0;// 盖帽数
			 int mistakesNo = 0;// 失误数
			 int foulsNo = 0;// 犯规数
			 int points = 0;// 得分
			 MatchPlayerPO player = null;
			 int twoPair = 0;
			 int time_num=0;
		for (int i = len; i >= low; --i)
		{
			player = match_datas[i];
			if (player.getTime() != -1)
			 {
				time += player.getTime();
			 }
			else ++time_num;
			hitNo += player.getHitNo();
			handNo += player.getHandNo();
			threeHitNo +=  player.getThreeHitNo(); // 三分命中数
			threeHandNo += player.getThreeHandNo() ; // 三分出手数
			penaltyHitNo += player.getPenaltyHitNo(); // 罚球命中数
			penaltyHandNo += player.getPenaltyHandNo(); // 罚球出手数
			offenseRebs += player.getOffenseRebs() ; // 进攻篮板数
			defenceRebs += player.getDefenceRebs(); // 防守篮板数
			rebs += player.getRebs(); // 篮板数
			help += player.getHelp(); // 助攻数
			stealsNo += player.getStealsNo();// 抢断数
			blockNo += player.getBlockNo();// 盖帽数
			mistakesNo += player.getMistakesNo();// 失误数
			foulsNo += player.getFoulsNo();// 犯规数
			points += player.getPoints();// 得分
			int j = 0;
			  if (player.getPoints() > 9)
				  j++;
			  if (player.getRebs() > 9)
				  j++;
			  if (player.getHelp() > 9)
				  j++;
			  if (player.getStealsNo() > 9)
				  j++;
			  if (player.getBlockNo() > 9)
				  j++;
			  if ( j >= 2)
				  ++twoPair;
        }
		double hitRate = 0;
		if (handNo != 0)
	         hitRate = 1.0 * hitNo / handNo;// 投篮命中率
		double threeHitRate = 0;// 三分命中率
		if (threeHandNo != 0)
			threeHitRate = 1.0 * threeHitNo / threeHandNo;
		double penaltyHitRate = 0;
		if (penaltyHandNo != 0)
		penaltyHitRate = 1.0 * penaltyHitNo / penaltyHandNo;// 罚球命中率
		int offendNo = offenseRebs;// 进攻数
		int defenceNo = defenceRebs;// 防守数
		double efficiency = (points + rebs + help + stealsNo + blockNo)
				- (handNo - hitNo) - mistakesNo;// 效率
		double gmScEfficiency = points + 0.4 * hitNo - 0.7 * handNo - 0.4
				* (penaltyHandNo - penaltyHitNo) + 0.7 * offenseRebs + 0.3
				* defenceRebs + stealsNo + 0.7 * help + 0.7 * blockNo - 0.4
				* foulsNo - mistakesNo;
		; // GmSc效率值
		double trueHitRate = points / (2 * (handNo + 0.44 * penaltyHandNo)); // 真实命中率
		double hitEfficiency = (hitNo + 0.5 * threeHitNo) / handNo; // 投篮效率
		double rebEfficiency = rebs * (1.0 * teamTotalTime) / time
				/ (myRebs + yourRebs); // 篮板率
		double offenseRebsEfficiency = offenseRebs * (1.0 * teamTotalTime )
				/ time / (myRebs + yourRebs);
		; // 进攻篮板率
		double defenceRebsEfficiency = defenceRebs * (1.0 * teamTotalTime)
				/ time / (myRebs + yourRebs);
		; // 防守篮板率
		double assistEfficiency = 1.0 * help
				/ (time / (teamTotalTime) * totalHit - hitNo); // 助攻率
		double stealsEfficiency = 1.0 * stealsNo * (1.0 * teamTotalTime) / time
				/ yourAttackNO; // 抢断率
		double blockEfficiency = 1.0 * blockNo * (1.0 * teamTotalTime) / time
				/ yourAttackNO; // 盖帽率
		double mistakeEfficiency = 1.0
				* mistakesNo
				/ (handNo - threeHandNo - penaltyHandNo + 0.44 * penaltyHandNo + mistakesNo);// 失误率
		double useEfficiency = (rebs + help + stealsNo + handNo + blockNo
				+ 0.44 * penaltyHandNo + mistakesNo)
				* (1.0 * teamTotalTime)
				/ time
				/ (teamHand + 0.44 * teamPenalty + teamMistakes);// 使用率

		double scoring_rebound_assist = (points + rebs + help) / 3;// 得分/篮板/助攻（加权比1：1：1）
		
		playervo_total = new  PlayerMatchVO( name,  teamname,  len+1,
				 firstServiceNo,  rebs,  help,  time,
				 hitRate,  threeHitRate,  penaltyHitRate,
				 offendNo,  defenceNo,  stealsNo,  blockNo,
				 mistakesNo,  foulsNo,  points,  upRate,
				 efficiency,  gmScEfficiency,  trueHitRate,
				 hitEfficiency,  rebEfficiency,
				 offenseRebsEfficiency,  defenceRebsEfficiency,
				 assistEfficiency,  stealsEfficiency,
				 blockEfficiency,  mistakeEfficiency,
				 stealsNo,   mistakesNo,  time / 60,
				 handNo,  threeHandNo,  penaltyHandNo,  twoPair);
		        
	}
	
	public void  update(int num)
	{
		
	}
    
	public PlayerMatchVO getPlayervo()
	{
		return null;
	}
	
	public PlayerMatchVO getTotalPlayer()
	{
		return playervo_total;
	}
	public PlayerMatchVO getAvePlayer()
	{
		return playervo_ave;
	}
}
