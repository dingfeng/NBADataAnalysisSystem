package bl.playerbl;

import java.awt.Image;
import java.util.ArrayList;

import bl.teambl.Team;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import po.PlayerPO;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.PlayerVO;
import vo.SortType;

public class Player {
	// private Matchblservice match = new Match(); //得到比赛逻辑层服务
	// private PayerDataService playerData = new PlayerData(); //得到球员数据层服务
	final int SCREENSIZE = 50;

	// personal information
	private String name;// 球员名称
	private Image action;// 大头图片
	private Image portrait;// 全身图片
	private int number;// 球衣号码
	private String position;// 位置
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
	private double defenceNo;// 防守数
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

	/*
	public Player(PlayerPO playerPo) {
		this.name = playerPo.getName();
		this.action = playerPo.getAction();
		this.portrait = playerPo.getPortrait();
		this.number = playerPo.getNumber();
		this.position = playerPo.getPosition();
		this.heightfeet = playerPo.getHeightfeet();
		this.heightinch = playerPo.getHeightinch();
		this.birth = playerPo.getBirth();
		this.age = playerPo.getAge();
		this.exp = playerPo.getExp();
		this.school = playerPo.getSchool();
		// 得到playerlist
		PlayerList playerlist = PlayerList.getPlayserListInstance();
		// 得到相应名字的player对象以初始化此player
		Player data = playerlist.getPlayerData(name);
		if (data != null) {
			this.team = data.getTeam();
			Team team1 = new Team();
			// playerArea = team1.getMatchArea(team);
			this.GmScEfficiency = data.getGmScEfficiency();
			this.matchNo = data.getMatchNo();
			this.firstServiceNo = data.getFirstServiceNo();
			this.rebs = data.getRebs();
			this.assistNo = data.getAssistNo();
			this.time = data.getTime();
			this.hitRate = data.getHitRate();
			this.threeHitRate = data.getThreeHitRate();
			this.penaltyHitRate = data.getPenaltyHitRate();
			this.offendNo = data.getOffendNo();
			this.defenceNo = data.getDefenceNo();
			this.stealsNo = data.getStealsNo();
			this.blockNo = data.getBlockNo();
			this.mistakesNo = data.getMatchNo();
			this.foulsNo = data.getFoulsNo();
			this.points = data.getPoints();
			this.efficiency = data.getEfficiency();
			this.trueHitRate = data.getTrueHitRate();
			this.hitEfficiency = data.getHitEfficiency();
			this.rebEfficiency = data.getRebEfficiency();
			this.offenseRebsEfficiency = data.getOffenseRebsEfficiency();
			this.defenceRebsEfficiency = data.getDefenceRebsEfficiency();
			this.assistEfficiency = data.getAssistEfficiency();
			this.stealsEfficiency = data.getEfficiency();
			this.blockEfficiency = data.getBlockEfficiency();
			this.mistakeEfficiency = data.getMistakeEfficiency();
			this.useEfficiency = data.getUseEfficiency();
			this.rebound = data.getRebound();// 篮板
			this.assist = data.getAssist();// 助攻
			this.scoring_rebound_assist = data.getScoring_rebound_assist();// 得分/篮板/助攻（加权比1：1：1）
			this.block = data.getBlock();// 盖帽
			this.steal = data.getSteal();// 抢断
			this.foul = data.getFoul();// 犯规
			this.mistake = data.getMistake();// 失误
			this.minute = data.getMinute();// 分钟
			this.shot = data.getShot();// 投篮
			this.three_points = data.getThree_points();// 三分
			this.freeThrow = data.getFreeThrow();// 罚球
			this.twoPair = data.getTwoPair();// 两双
		}
	}
	*/

	/**
	 * 向player对象中添加一个match的数据
	 * 
	 * @param match
	 * @return boolean
	 */
	public boolean addMatchInformation(MatchesPO match,
			MatchPlayerPO matchPlayer, MatchTeamPO rival, String TeamName) {
		// 向相应的player对象中添加一个match数据
		matches.add(match);

		String location;// 位置
		double time;// 在场时间
		int hitNo = 0; // 投篮命中数0
		int handNo = 0; // 投篮出手次数
		int threeHitNo = 0; // 三分命中数
		int threeHandNo = 0; // 三分出手数
		int penaltyHitNo = 0; // 罚球命中数
		int penaltyHandNo = 0; // 罚球出手数
		int offenseRebs = 0; // 进攻篮板数
		int defenceRebs = 0; // 防守篮板数
		int rebs = 0; // 篮板数
		int help = 0;// 总篮板数
		int stealsNo = 0;// 抢断数
		int blockNo = 0;// 盖帽数
		int mistakesNo = 0;// 失误数
		int foulsNo = 0;// 犯规数
		int points;// 得分

		int myRebs = 0;
		int teamTotalTime = 0;
		int yourRebs = 0;
		int totalHit = 0;
		int yourTwoPoints = 0;
		double yourAttackNO = 0;
		int teamHand = 0;
		int teamPenalty = 0;
		int teamMistakes = 0;
		int i = 0;
		int teamDefenceNo = 0;

		location = matchPlayer.getLocation();// 位置
		time = matchPlayer.getTime();// 在场时间
		hitNo += matchPlayer.getHitNo();
		handNo += matchPlayer.getHandNo();
		threeHitNo += matchPlayer.getThreeHitNo();
		threeHandNo += matchPlayer.getThreeHandNo();
		penaltyHitNo += matchPlayer.getPenaltyHitNo();
		penaltyHandNo += matchPlayer.getPenaltyHandNo();
		offenseRebs += matchPlayer.getOffenseRebs();
		defenceRebs += matchPlayer.getDefenceRebs();
		rebs += matchPlayer.getRebs();
		help += matchPlayer.getHelp();
		stealsNo += matchPlayer.getStealsNo();
		blockNo += matchPlayer.getBlockNo();
		mistakesNo += matchPlayer.getMistakesNo();
		foulsNo += matchPlayer.getFoulsNo();
		points = matchPlayer.getPoints();// 得分
		totalHit += hitNo;
		teamTotalTime += time;
		teamHand += handNo;
		teamPenalty += penaltyHandNo;
		teamMistakes += mistakesNo;
		myRebs += rebs;
		teamDefenceNo += defenceRebs;

		/**
		 * if (!location.equalsIgnoreCase("?")){
		 * matchplayer.setLocation(location); } if(i < 6){
		 * matchplayer.addFirstServiceNo(1); } i ++; matchplayer.addMatchNO(1);
		 * if(time == -1){ matchplayer.addDirtyData(1); } else{
		 * matchplayer.addTime(time); } matchplayer.addDate(TeamName,
		 * match.getDate()); matchplayer.addHitNo(hitNo);
		 * matchplayer.addHandNo(handNo);
		 * matchplayer.addThreeHitNo(threeHitNo);;
		 * matchplayer.addThreeHandNo(threeHandNo);
		 * matchplayer.addPenaltyHandNo(penaltyHandNo);
		 * matchplayer.addPenaltyHitNo(penaltyHitNo);;
		 * matchplayer.addOffenseRebs(offenseRebs);
		 * matchplayer.addDefenceRebs(defenceRebs); matchplayer.addRebs(rebs);
		 * matchplayer.addHelp(help); matchplayer.addStealsNo(stealsNo);
		 * matchplayer.addBlockNo(blockNo);
		 * matchplayer.addMistakesNo(mistakesNo);
		 * matchplayer.addFoulsNo(foulsNo); matchplayer.addPoints(points);
		 * if(matchPlayer.isDirty()){ matchplayer.addDirtyData(); } //计算两双 int j
		 * = 0; if(points > 9){ j ++; } if(rebs > 9){ j ++; } if(help > 9){ j
		 * ++; } if(stealsNo > 9){ j ++; } if(blockNo > 9){ j ++; } if(j >=2){
		 * matchplayer.addTwoPair(1); }
		 */
		return true;
	}

	/**
	 * 将球员信息打包成VO
	 * 
	 * @return
	 */
	public PlayerVO toVo() {
		return new PlayerVO(action, portrait, number, position, heightfeet,
				heightinch, birth, age, exp, school, name, team, matchNo,
				firstServiceNo, rebs, assistNo, time, hitRate, threeHitRate,
				penaltyHitRate, offendNo, defenceNo, stealsNo, blockNo,
				mistakesNo, foulsNo, points, efficiency, GmScEfficiency,
				trueHitRate, hitEfficiency, rebEfficiency,
				offenseRebsEfficiency, defenceRebsEfficiency, assistEfficiency,
				stealsEfficiency, blockEfficiency, mistakeEfficiency,
				useEfficiency, playerArea, rebound,// 篮板
				assist,// 助攻
				scoring_rebound_assist,// 得分/篮板/助攻（加权比1：1：1）
				block,// 盖帽
				steal,// 抢断
				foul,// 犯规
				mistake,// 失误
				minute,// 分钟
				shot,// 投篮
				three_points,// 三分
				freeThrow,// 罚球
				twoPair);
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

	public String getPosition() {
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

	public void setSortBy(PlayerMatchVO player, PlayerSortBy PlayerSortBy,
			SortType sortType) {
		String strSort = null;
		double doubleSort = -1;
		switch (PlayerSortBy) {
		case name:
			strSort = player.getName();
			break;
		case team:
			strSort = player.getTeam();
			break;
		case matchNo:
			doubleSort = player.getMatchNo();
			break;
		case firstServiceNo:
			doubleSort = player.getFirstServiceNo();
			break;
		case rebs:
			doubleSort = player.getRebs();
			break;
		case assistNo:
			doubleSort = player.getAssistNo();
			break;
		case time:
			doubleSort = player.getTime();
			break;
		case hitRate:
			doubleSort = player.getHitRate();
			break;
		case threeHitRate:
			doubleSort = player.getThreeHitRate();
			break;
		case penaltyHitRate:
			doubleSort = player.getPenaltyHitRate();
			break;
		case offendNo:
			doubleSort = player.getOffendNo();
			break;
		case defenceNo:
			doubleSort = player.getDefenceNo();
			break;
		case stealsNo:
			doubleSort = player.getStealsNo();
			break;
		case blockNo:
			doubleSort = player.getBlockNo();
			break;
		case mistakesNo:
			doubleSort = player.getMistakesNo();
			break;
		case foulsNo:
			doubleSort = player.getFoulsNo();
			break;
		case points:
			doubleSort = player.getPoints();
			break;
		case efficiency:
			doubleSort = player.getEfficiency();
			break;
		case gmScEfficiency:
			doubleSort = player.getGmScEfficiency();
			break;
		case trueHitRate:
			doubleSort = player.getTrueHitRate();
			break;
		case hitEfficiency:
			doubleSort = player.getHitEfficiency();
			break;
		case rebEfficiency:
			doubleSort = player.getRebEfficiency();
			break;
		case offenseRebsEfficiency:
			doubleSort = player.getOffenseRebsEfficiency();
			break;
		case defenceRebsEfficiency:
			doubleSort = player.getDefenceRebsEfficiency();
			break;
		case assistEfficiency:
			doubleSort = player.getAssistEfficiency();
			break;
		case stealsEfficiency:
			doubleSort = player.getStealsEfficiency();
			break;
		case blockEfficiency:
			doubleSort = player.getBlockEfficiency();
			break;
		case mistakeEfficiency:
			doubleSort = player.getMistakeEfficiency();
			break;
		case useEfficiency:
			doubleSort = player.getUseEfficiency();
			break;

		case rebound:// 篮板
			doubleSort = player.getRebs();
			break;
		case assist:// 助攻
			doubleSort = player.getAssistNo();
			break;
		case scoring_rebound_assist:// 得分/篮板/助攻（加权比1：1：1）
			doubleSort = player.getScoring_rebound_assist();
			break;
		case block:// 盖帽
			doubleSort = player.getBlockNo();
			break;
		case steal:// 抢断
			doubleSort = player.getStealsNo();
			break;
		case foul:// 犯规
			doubleSort = player.getFoulsNo();
			break;
		case mistake:// 失误
			doubleSort = player.getMistakesNo();
			break;
		case minute:// 分钟
			doubleSort = player.getMinute();
			break;
		case shot:// 投篮
			doubleSort = player.getHandNo();
			break;
		case three_points:// 三分
			doubleSort = player.getThree_points();
			break;
		case freeThrow:// 罚球
			doubleSort = player.getPenaltyHandNo();
			break;
		case twoPair:// 两双
			doubleSort = player.getTwoPair();
			break;
		}
		if (doubleSort != -1) {
			sortBy = new SortBy(doubleSort, sortType);
		} else {
			sortBy = new SortBy(strSort, sortType);
		}
	}
}
