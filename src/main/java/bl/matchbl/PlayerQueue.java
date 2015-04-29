package bl.matchbl;

import po.MatchPlayerPO;
import po.MatchesPO;
import vo.PlayerMatchVO;
import vo.PlayerVO;

public class PlayerQueue {
	private final static double MIN_DOUBLE = -1000;
    private MatchPlayerPO[] match_datas;
    private int len = -1;
    private String name;
    private PlayerMatchVO playervo_total;
    private PlayerMatchVO playervo_ave ;
    private String teamname;
	private double teamTotalTime;
	private int yourRebs;
	private int totalHit;
	private double yourAttackNO;
	private int teamHand;
	private int teamPenalty;
	private int teamMistakes;
    private int low  =0;	
	private int firstServiceNo;
	private int myRebs;
	private int yourtwoPoints;
	private int yourOffenseRebs;
	private int yourDefenceRebs;
	private int teamDefenceRebs;
	private int teamOffenseRebs;
//	private PlayerYourInfo[] yourInfos;
	private MatchesPO [] matches = new MatchesPO[100];
	private int matchlen = -1;
	private String position = null;
	boolean recent = false;
	public PlayerQueue(int max, String name) 
	{
		match_datas = new MatchPlayerPO[max];
//		yourInfos = new PlayerYourInfo[max];
		this.name = name;
		playervo_total = new PlayerMatchVO(name);
		playervo_ave = new PlayerMatchVO(name);
	}
	//增加赛事
	public void enqueue(MatchesPO match,MatchPlayerPO player , String teamname,int twoPoints,double teamTotalTime
			,int yourRebs, int totalHit, double yourAttackNO,
			int teamHand, int teamPenalty, int teamMistakes, int firstServiceNo, int myRebs,String team1, String team2,String date,
			int yourOffenseRebs,int yourDefenceRebs,int teamDefenceRebs,int teamOffenseRebs
			)
			{
		recent = false;
		this.teamDefenceRebs += teamDefenceRebs;
		this.teamOffenseRebs += teamOffenseRebs;
		this.yourDefenceRebs += yourDefenceRebs;
		this.yourOffenseRebs += yourOffenseRebs;
		
		   String position_temp =  player.getLocation();
		   if (position_temp != null)
		   {
			   position = position_temp;
		   }
		   
		   matches[++matchlen] = match;
		   match_datas[++len] = player;
		   this.teamname = teamname;
		   this.teamTotalTime += teamTotalTime;
		   this.yourRebs += yourRebs;
		   this.totalHit += totalHit;
		   this.yourAttackNO += yourAttackNO;
		   
//		   if (name.equals("Russell Westbrook"))
//		   {
//			   StringBuilder sb = new StringBuilder();
//			   sb.append("日期:\t"+match.getDate()+"\t进攻回合:\t"+yourAttackNO);
//			   System.out.println(sb.toString());
//			   System.out.println(teamname);
//		   }
		   
		   this.teamHand += teamHand;
		   this.teamPenalty += teamPenalty;
		   this.teamMistakes += teamMistakes;
		   this.firstServiceNo += firstServiceNo;
		   this.myRebs += myRebs;
		   this.yourtwoPoints += twoPoints;
//		   yourInfos[len] = new PlayerYourInfo( twoPoints,teamTotalTime,  yourRebs,  totalHit,
//					   yourAttackNO,  teamHand,
//					 teamPenalty,  teamMistakes,   myRebs, team1, team2, date);
			}
	public String getName()
	{
		return name;
	}
	//更新数据
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
			 
			 //points_uprate,  rebs_uprate,  help_uprate
			 
			 int mid =  len - 4;
			 double points1 = 0;
			 double points2 = 0;
			 double rebs1 =  0;
			 double rebs2 = 0;
			 double help1 = 0;
			 double help2 = 0;
			 
			 
		for (int i = len; i >= low; --i)
		{
			
			player = match_datas[i];
			if (player.getTime() != -1)
			 {
				time += player.getTime();
			 }
			else ++time_num;
			
//			PlayerYourInfo player_info = yourInfos[i];
			
//			 int yourTwoPoints0 = player_info.getTwoPoints();
			double hitNo0 = player.getHitNo();
			double handNo0 = player.getHandNo();
			double	threeHitNo0 =  player.getThreeHitNo(); // 三分命中数
			double	threeHandNo0 = player.getThreeHandNo() ; // 三分出手数
			double	penaltyHitNo0 = player.getPenaltyHitNo(); // 罚球命中数
			double	penaltyHandNo0 = player.getPenaltyHandNo(); // 罚球出手数
			double	offenseRebs0 = player.getOffenseRebs() ; // 进攻篮板数
			double	defenceRebs0 = player.getDefenceRebs(); // 防守篮板数
			double	rebs0 = player.getRebs(); // 篮板数
			double	help0 = player.getHelp(); // 助攻数
			double	stealsNo0 = player.getStealsNo();// 抢断数
			double	blockNo0 = player.getBlockNo();// 盖帽数
			double	mistakesNo0 = player.getMistakesNo();// 失误数
			double	foulsNo0 = player.getFoulsNo();// 犯规数
			double	points0 = player.getPoints();// 得分
			
			if ( i >= mid)
			{
				points2 += points0;
				rebs2 += rebs0;
				help2 += help0;
			}
			else 
			{
				points1 += points0;
				rebs1 += rebs0;
				help1 += help0;
			}
			hitNo += hitNo0;
			handNo += handNo0;
			threeHitNo +=  threeHitNo0; // 三分命中数
			threeHandNo += threeHandNo0 ; // 三分出手数
			penaltyHitNo += penaltyHitNo0; // 罚球命中数
			penaltyHandNo += penaltyHandNo0; // 罚球出手数
			offenseRebs += offenseRebs0; // 进攻篮板数
			defenceRebs += defenceRebs0; // 防守篮板数
			rebs += rebs0; // 篮板数
			help += help0; // 助攻数
			stealsNo += stealsNo0;// 抢断数
			blockNo += blockNo0;// 盖帽数
			mistakesNo += mistakesNo0;// 失误数
			foulsNo += foulsNo0;// 犯规数
			points += points0;// 得分
			
			
			 
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
		double efficiency = (points + rebs + help + stealsNo + blockNo)-(penaltyHandNo - penaltyHitNo)
				- (handNo - hitNo) - mistakesNo;// 效率
		double gmScEfficiency = points + 0.4 * hitNo - 0.7 * handNo - 0.4
				* (penaltyHandNo - penaltyHitNo) + 0.7 * offenseRebs + 0.3
				* defenceRebs + stealsNo + 0.7 * help + 0.7 * blockNo - 0.4
				* foulsNo - mistakesNo;
		; // GmSc效率值
		double trueHitRate = (handNo + 0.44 * penaltyHandNo) == 0 ? 0 : points / (2 * (handNo + 0.44 * penaltyHandNo)); // 真实命中率
		double hitEfficiency = handNo == 0 ? 0 : (hitNo + 0.5 * threeHitNo) / handNo; // 投篮效率
		double rebEfficiency  = 0;
		if (time != 0 && (myRebs+yourRebs) != 0)
		 rebEfficiency = rebs * (1.0 * teamTotalTime) / time
				/ (myRebs + yourRebs); // 篮板率
		double offenseRebsEfficiency = 0;
		if (time != 0 && (teamOffenseRebs + yourRebs) != 0)
		offenseRebsEfficiency = offenseRebs * (1.0 * teamTotalTime )
				/ time / (teamOffenseRebs + yourOffenseRebs);
		
		; // 进攻篮板率
		double defenceRebsEfficiency  = 0;
		if (time != 0 && (teamDefenceRebs + yourRebs) != 0)
		 defenceRebsEfficiency = defenceRebs * (1.0 * teamTotalTime)
				/ time / (teamDefenceRebs + yourDefenceRebs);
		; // 防守篮板率
		
		double assistEfficiency = 0;
		if ( teamTotalTime != 0 && time / (teamTotalTime) * totalHit - hitNo != 0)
			assistEfficiency = 1.0 * help
				/ (time / teamTotalTime * totalHit - hitNo); // 助攻率
		double stealsEfficiency = 0;
		double blockEfficiency = 0;
		if (time != 0 )
		{
			if (yourAttackNO != 0)
			stealsEfficiency = 1.0 * stealsNo * (1.0 * teamTotalTime) / time
				/ yourAttackNO; // 抢断率
			if (yourtwoPoints!= 0)
		   blockEfficiency = 1.0 * blockNo * (1.0 * teamTotalTime) / time
				/ yourtwoPoints; // 盖帽率
		}
		double mistakeEfficiency = 0;
		if (handNo - threeHandNo  + 0.44 * penaltyHandNo + mistakesNo != 0)
		 mistakeEfficiency = 1.0
				* mistakesNo
				/ (handNo - threeHandNo  + 0.44 * penaltyHandNo + mistakesNo);// 失误率
	
		
		double useEfficiency = 0;
		if (time != 0 &&  teamHand + 0.44 * teamPenalty + teamMistakes != 0)
		 useEfficiency = (handNo 
				+ 0.44 * penaltyHandNo + mistakesNo)
				* (1.0 * teamTotalTime)
				/ time
				/ (teamHand + 0.44 * teamPenalty + teamMistakes);// 使用率

		double scoring_rebound_assist = (points + rebs + help) / 3;// 得分/篮板/助攻（加权比1：1：1）
		
		double matchNO = len +  1;
		double points_uprate = MIN_DOUBLE;
		double rebs_uprate = MIN_DOUBLE;
		double help_uprate = MIN_DOUBLE;
		if (len > 4)
		{
		if (points1  != 0)
		   points_uprate = (points2 / 5 - points1 / (len -4)) / (points1 / (len - 4));
	    if (rebs1 != 0)
		   rebs_uprate = (rebs2 / 5 - rebs1 / (len -4)) / (rebs1 / (len - 4));
	    if (help1 != 0)
		 help_uprate = (help2 / 5 - help1 / (len -4)) / (help1 / (len - 4));
		}
		
		//赛季数据更新
		playervo_total = new  PlayerMatchVO(  name,  teamname,  len + 1,
				 firstServiceNo,  rebs,  help,  time,
				 hitRate,  threeHitRate,  penaltyHitRate,
				 offenseRebs,  defenceRebs,  stealsNo,  blockNo,
				 mistakesNo,  foulsNo,  points, points_uprate,  rebs_uprate,  help_uprate,
				 efficiency / matchNO,  gmScEfficiency / matchNO,  trueHitRate,
				 hitEfficiency,  rebEfficiency,
				 offenseRebsEfficiency,  defenceRebsEfficiency ,
				 assistEfficiency ,  stealsEfficiency ,
				 blockEfficiency,  mistakeEfficiency ,
				 useEfficiency,   scoring_rebound_assist,
				   penaltyHandNo,
				time / 60,  handNo,
				threeHandNo,   twoPair, penaltyHitNo, hitNo, threeHitNo);
		
		//场均数据更新
	    playervo_ave = new PlayerMatchVO(name,  teamname,  len + 1,
				 firstServiceNo,  rebs / matchNO,  help/ matchNO,  time/ (matchNO -time_num),
				 hitRate,  threeHitRate,  penaltyHitRate,
				 offenseRebs/ matchNO,  defenceRebs/ matchNO,  stealsNo/ matchNO,  blockNo/ matchNO,
				 mistakesNo/ matchNO,  foulsNo/ matchNO,  points/ matchNO, points_uprate,  rebs_uprate,  help_uprate,
				 efficiency/ matchNO,  gmScEfficiency/ matchNO,  trueHitRate,
				 hitEfficiency,  rebEfficiency,
				 offenseRebsEfficiency,  defenceRebsEfficiency,
				 assistEfficiency,  stealsEfficiency,
				 blockEfficiency,  mistakeEfficiency,
				 useEfficiency,   scoring_rebound_assist/ matchNO,
				  penaltyHandNo,
				time / 60/ (matchNO -time_num),  handNo/ matchNO,
				threeHandNo/ matchNO,   twoPair/ matchNO, penaltyHitNo / matchNO, hitNo / matchNO, threeHitNo / matchNO);
	   // double penaltyHitNo, double hitNo,doubltr threeHitNo
		recent = true;
	}
	//更新
	public void  update(int num)
	{
		low = len - num + 1;
		update();
		low = 0;
	}
    
	//获得球队名
	public String  getTeamName()
	{
		return teamname;
	}
	
	public PlayerMatchVO getTotalPlayer()
	{
		if (!recent)
		{
			update();
		}
		return playervo_total;
	}
	
	public PlayerMatchVO getAvePlayer()
	{
		if (!recent)
		{
			update();
		}
		return playervo_ave;
	}
	public MatchesPO[] getAllMatches()
	{
		MatchesPO[] temp = new MatchesPO[matchlen+1];
		                                
		for (int i = 0 ; i < matchlen + 1; i++)
		{
		 temp[i] = matches[i];	
		}
		return temp;
	}
	public MatchesPO[] getRecentPlayerMatches(int num)
	{
		if (matchlen == -1) return null;
		int size = (num < matchlen+1) ? num : (matchlen + 1);
		MatchesPO[] temp_matches = new MatchesPO[size];
		for (int i = 0; i < size; i++)
		{
			temp_matches[i] = matches[matchlen - i];
		}
		return temp_matches;
	}
	
	/**
	 * 得到该球员最近num场数据
	 * @param num
	 * @return
	 */
	public MatchPlayerPO[] getRecentPlayerInfo(int num){
		if (matchlen == -1) return null;
		int size = (num < matchlen+1) ? num : (matchlen + 1);
		MatchPlayerPO[] temp_MatchPlayers = new MatchPlayerPO[size];
		for(int i = 0; i < size; i ++){
			temp_MatchPlayers[i] = match_datas[matchlen - i];
		}
		return temp_MatchPlayers;
	}
	
	public String getPosition()
	{
		return position;
	}
}
