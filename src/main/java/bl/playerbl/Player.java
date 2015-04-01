package bl.playerbl;

import java.awt.Image;
import java.util.ArrayList;

import po.MatchesPO;
import vo.Area;

public class Player {
	// private Matchblservice match = new Match(); //得到比赛逻辑层服务
	// private PlayerDataService playerData = new PlayerData(); //得到球员数据层服务
	final int SCREENSIZE = 50;

	// personal information
	private String name;// 球员名称
	private Image action;// 大头图片
	private Image portrait;// 全身图片
	private int number;// 球衣号码
	private char position;// 位置
	private int heightfeet;// 身高的英尺
	private int heightinch;// 身高的英寸
	private String birth;// 生日
	private int age;// 年龄
	private int exp;// 球龄
	private String school;// 毕业学校
	// -----------------------------------------------
	// data calculated
	private String team;// 所属球队
	private int matchNo;// 参赛场数
	private double firstServiceNo;// 先发场数
	private double rebs;// 篮板数
	private double assistNo;// 助攻数
	private double time;// 在场时间
	private double hitRate;// 投篮命中率
	private double threeHitRate;// 三分命中率
	private double penaltyHitRate;// 罚球命中率
	private double offendNo;// 进攻数
	private double defenceNo;// 防守数5
	private double stealsNo;// 抢断数
	private double blockNo;// 盖帽数
	private double mistakesNo;// 失误数
	private double foulsNo;// 犯规数
	private double points;// 得分
	private double efficiency;// 效率
	private double GmScEfficiency;// GmSc效率值
	private double trueHitRate;// 真实命中率
	private double hitEfficiency;// 投篮效率
	private double rebEfficiency;// 篮板率
	private double offenseRebsEfficiency;// 进攻篮板率
	private double defenceRebsEfficiency;// 防守篮板率
	private double assistEfficiency;// 助攻率
	private double stealsEfficiency;// 抢断率
	private double blockEfficiency;// 盖帽率
	private double mistakeEfficiency;// 失误率
	private double useEfficiency;// 使用率

	private double rebound;// 篮板
	private double assist;// 助攻
	private double scoring_rebound_assist;// 得分/篮板/助攻（加权比1：1：1）
	private double block;// 盖帽
	private double steal;// 抢断
	private double foul;// 犯规
	private double mistake;// 失误
	private double minute;// 分钟
	private double shot;// 投篮
	private double three_points;// 三分
	private double freeThrow;// 罚球
	private double twoPair;// 两双
	public static boolean isAverage = false;
	private Area playerArea = Area.ATLANTIC; // 分区

	private SortBy sortBy;
	private ArrayList<MatchesPO> matches = new ArrayList<MatchesPO>(82);// 球员参加的比赛的信息

	/**
	 * 向player对象中添加一个match的数据
	 * 
	 * @param match
	 * @return boolean
	 */
	public boolean addMatchInformation(MatchesPO match) {
		// 向相应的player对象中添加一个match数据
		matches.add(match);

		String name;
		String location;// 位置
		double time;// 在场时间
		int hitNo; // 投篮命中数
		int handNo; // 投篮出手次数
		int threeHitNo; // 三分命中数
		int threeHandNo; // 三分出手数
		int penaltyHitNo; // 罚球命中数
		int penaltyHandNo; // 罚球出手数
		int offenseRebs; // 进攻篮板数
		int defenceRebs; // 防守篮板数
		int rebs; // 篮板数
		int help; // 助攻数
		int stealsNo;// 抢断数
		int blockNo;// 盖帽数
		int mistakesNo;// 失误数
		int foulsNo;// 犯规数
		int points;// 得分

		int myRebs = 0;
		int teamTotalTime = 0;
		int yourRebs = 0;
		int totalHit = 0;
		int yourTwoPoints = 0;
		int yourAttackNO = 0;
		int teamHand = 0;
		int teamPenalty = 0;
		int teamMistakes = 0;

		/*
		 * name = matchPlayerpo.getName();// 球员名称 location =
		 * matchPlayerpo.getLocation();// 位置 time = matchPlayerpo.getTime();//
		 * 在场时间 hitNo = matchPlayerpo.getHitNo(); // 投篮命中数 handNo =
		 * matchPlayerpo.getHandNo(); // 投篮出手次数 threeHitNo =
		 * matchPlayerpo.getThreeHitNo(); // 三分命中数 threeHandNo =
		 * matchPlayerpo.getThreeHandNo(); // 三分出手数 penaltyHitNo =
		 * matchPlayerpo.getPenaltyHitNo(); // 罚球命中数 penaltyHandNo =
		 * matchPlayerpo.getPenaltyHandNo(); // 罚球出手数 offenseRebs =
		 * matchPlayerpo.getOffenseRebs(); // 进攻篮板数 defenceRebs =
		 * matchPlayerpo.getDefenceRebs(); // 防守篮板数 rebs =
		 * matchPlayerpo.getRebs(); // 篮板数 help = matchPlayerpo.getHelp();//
		 * 总篮板数 stealsNo = matchPlayerpo.getStealsNo();// 抢断数 blockNo =
		 * matchPlayerpo.getBlockNo();// 盖帽数 mistakesNo =
		 * matchPlayerpo.getMistakesNo();// 失误数 foulsNo =
		 * matchPlayerpo.getFoulsNo();// 犯规数 points =
		 * matchPlayerpo.getPoints();// 得分
		 * 
		 * totalHit += hitNo; teamTotalTime += time; teamHand += handNo;
		 * teamPenalty += penaltyHandNo; teamMistakes += mistakesNo; myRebs +=
		 * rebs;
		 * 
		 * if (!location.equalsIgnoreCase("?"))
		 * matchPlayer.setLocation(location); if (i < 6)
		 * matchPlayer.addFirstServiceNo(1); i++; matchPlayer.addMatchNO(1); if
		 * (time == -1) { matchPlayer.addDirtyData(1); } else {
		 * matchPlayer.addTime(time); } matchPlayer.addDate(teamName,
		 * matchpo.getDate()); matchPlayer.addHitNo(hitNo);
		 * matchPlayer.addHandNo(handNo);
		 * matchPlayer.addThreeHandNo(threeHandNo);
		 * matchPlayer.addThreeHitNo(threeHitNo);
		 * matchPlayer.addPenaltyHandNo(penaltyHandNo);
		 * matchPlayer.addPenaltyHitNo(penaltyHitNo);
		 * matchPlayer.addOffenseRebs(offenseRebs);
		 * matchPlayer.addDefenceRebs(defenceRebs); matchPlayer.addRebs(rebs);
		 * matchPlayer.addHelp(help); matchPlayer.addStealsNo(stealsNo);
		 * matchPlayer.addBlockNo(blockNo);
		 * matchPlayer.addMistakesNo(mistakesNo);
		 * matchPlayer.addFoulsNo(foulsNo); matchPlayer.addPoints(points); if
		 * (matchPlayerpo.isDirty()) matchPlayer.addDirtyData(); int j = 0; if
		 * (points > 9) j++; if (rebs > 9) j++; if (help > 9) j++; if (stealsNo
		 * > 9) j++; if (blockNo > 9) j++; if (j >= 2)
		 * matchPlayer.addTwoPair(1);
		 * 
		 * Iterator<MatchPlayerPO> playerItr2 = teamPlayer2.iterator(); while
		 * (playerItr2.hasNext()) { MatchPlayerPO matchPlayerpo =
		 * playerItr2.next(); yourRebs += matchPlayerpo.getRebs(); yourTwoPoints
		 * += matchPlayerpo.getHitNo() - matchPlayerpo.getThreeHitNo() -
		 * matchPlayerpo.getPenaltyHitNo(); yourAttackNO +=
		 * matchPlayerpo.getOffenseRebs(); } playerItr = teamPlayer.iterator();
		 * while (playerItr.hasNext()) { MatchPlayerPO player =
		 * playerItr.next(); String playerName = player.getName(); MatchPlayer
		 * matchPlayer1 = playerDatas.get(playerName);
		 * matchPlayer1.addTotalHit(totalHit);
		 * matchPlayer1.addTeamTotalTime(teamTotalTime);
		 * matchPlayer1.addTeamPenalty(teamPenalty);
		 * matchPlayer1.addTeamMistakes(teamMistakes);
		 * matchPlayer1.addMyRebs(myRebs); matchPlayer1.addTeamHand(teamHand);
		 * matchPlayer1.addYourAttackNO(yourAttackNO);
		 * matchPlayer1.addYourRebs(yourRebs);
		 * matchPlayer1.addYourTwoPoints(yourTwoPoints); }
		 */
		return true;
	}

	public int getSCREENSIZE() {
		return SCREENSIZE;
	}

	public String getName() {
		return name;
	}

	public Image getAction() {
		return action;
	}

	public Image getPortrait() {
		return portrait;
	}

	public int getNumber() {
		return number;
	}

	public char getPosition() {
		return position;
	}

	public int getHeightfeet() {
		return heightfeet;
	}

	public int getHeightinch() {
		return heightinch;
	}

	public String getBirth() {
		return birth;
	}

	public int getAge() {
		return age;
	}

	public int getExp() {
		return exp;
	}

	public String getSchool() {
		return school;
	}

	public String getTeam() {
		return team;
	}

	public int getMatchNo() {
		return matchNo;
	}

	public double getFirstServiceNo() {
		return firstServiceNo;
	}

	public double getRebs() {
		return rebs;
	}

	public double getAssistNo() {
		return assistNo;
	}

	public double getTime() {
		return time;
	}

	public double getHitRate() {
		return hitRate;
	}

	public double getThreeHitRate() {
		return threeHitRate;
	}

	public double getPenaltyHitRate() {
		return penaltyHitRate;
	}

	public double getOffendNo() {
		return offendNo;
	}

	public double getDefenceNo() {
		return defenceNo;
	}

	public double getStealsNo() {
		return stealsNo;
	}

	public double getBlockNo() {
		return blockNo;
	}

	public double getMistakesNo() {
		return mistakesNo;
	}

	public double getFoulsNo() {
		return foulsNo;
	}

	public double getPoints() {
		return points;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public double getGmScEfficiency() {
		return GmScEfficiency;
	}

	public double getTrueHitRate() {
		return trueHitRate;
	}

	public double getHitEfficiency() {
		return hitEfficiency;
	}

	public double getRebEfficiency() {
		return rebEfficiency;
	}

	public double getOffenseRebsEfficiency() {
		return offenseRebsEfficiency;
	}

	public double getDefenceRebsEfficiency() {
		return defenceRebsEfficiency;
	}

	public double getAssistEfficiency() {
		return assistEfficiency;
	}

	public double getStealsEfficiency() {
		return stealsEfficiency;
	}

	public double getBlockEfficiency() {
		return blockEfficiency;
	}

	public double getMistakeEfficiency() {
		return mistakeEfficiency;
	}

	public double getUseEfficiency() {
		return useEfficiency;
	}

	public double getRebound() {
		return rebound;
	}

	public double getAssist() {
		return assist;
	}

	public double getScoring_rebound_assist() {
		return scoring_rebound_assist;
	}

	public double getBlock() {
		return block;
	}

	public double getSteal() {
		return steal;
	}

	public double getFoul() {
		return foul;
	}

	public double getMistake() {
		return mistake;
	}

	public double getMinute() {
		return minute;
	}

	public double getShot() {
		return shot;
	}

	public double getThree_points() {
		return three_points;
	}

	public double getFreeThrow() {
		return freeThrow;
	}

	public double getTwoPair() {
		return twoPair;
	}

	public static boolean isAverage() {
		return isAverage;
	}

	public Area getPlayerArea() {
		return playerArea;
	}

	public SortBy getSortBy() {
		return sortBy;
	}

	public int compareTo(Player arg0) {
		return sortBy.compareTo(arg0.getSortBy());
	}
}