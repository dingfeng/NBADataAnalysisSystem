package bl.matchbl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import vo.Area;

public class MatchPlayer
{
	private HashMap<String, Date> map         =   new HashMap<String, Date>();
	private HashMap<String, Date> locationMap =   new HashMap<String, Date>();
	private int dirtyData = 0;
	private String name;
	private String location;// 位置
	private double time;// 在场时间
	private int hitNo; // 投篮命中数
	private int handNo; // 投篮出手次数
	private int threeHitNo; // 三分命中数
	private int threeHandNo; // 三分出手数
	private int penaltyHitNo; // 罚球命中数
	private int penaltyHandNo; // 罚球出手数
	private int offenseRebs; // 进攻篮板数
	private int defenceRebs; // 防守篮板数
	private int rebs; // 篮板数
	private int help; // 助攻数
	private int stealsNo;// 抢断数
	private int blockNo;// 盖帽数
	private int mistakesNo;// 失误数
	private int foulsNo;// 犯规数
	private int points;// 得分
	private int matchNO; // 比赛场数
	private int firstServiceNo;
	private int myRebs;
	private int teamTotalTime;
	private int yourRebs;
	private int totalHit;
	private int yourTwoPoints;
	private double yourAttackNO;
	private int teamHand;
	private int teamPenalty;
	private int teamMistakes;
	private int twoPair;
	private Area playerArea;
	private double average = 1;

	public Area getPlayerArea() {
		return playerArea;
	}

	public void setPlayerArea(Area playerArea) 
	{
		this.playerArea = playerArea;
	}

	public void addDirtyData(int num) 
	{
		dirtyData += num;
	}

	public int getDirtyData() {
		return dirtyData;
	}

	public void addHelp(int help)
	{
		this.help += help;
	}

	public int getHelp() {
		return help;
	}

	public void setLocation(String location, Date date)
	{
		locationMap.put(location, date);
	}

	public void addFirstServiceNo(int num) {
		firstServiceNo += num;
	}

	public void addTeamTotalTime(int num) {
		teamTotalTime += num;
	}

	public void addMyRebs(int num) {
		myRebs += num;
	}

	public void addYourRebs(int num) {
		yourRebs += num;
	}

	public void addTotalHit(int num) {
		totalHit += num;
	}

	public void addYourTwoPoints(int num) {
		yourTwoPoints += num;
	}

	public void addYourAttackNO(double num) {
		yourAttackNO += num;
	}

	public void addTeamHand(int num) {
		teamHand += num;
	}

	public void addTeamPenalty(int num) 
	{
		teamPenalty += num;
	}

	public void addTeamMistakes(int num) {
		teamMistakes += num;
	}

	public void addTwoPair(int num) {
		twoPair += num;
	}

	public int getMatchNO() {
		return matchNO;
	}

	public PlayerCalculatedData getAveragePlayerData() {
		average = matchNO;
		PlayerCalculatedData playerData = getPlayerData();
		average = 1;
		return playerData;
	}

	public PlayerCalculatedData getPlayerData() {
		Iterator<String> teamNames = map.keySet().iterator();
		String teamName = teamNames.next();
		Date date = map.get(teamName);
		while (teamNames.hasNext()) {
			String name = teamNames.next();
			Date date1 = map.get(name);
			if (date1.compareTo(date) > 0) {
				date = date1;
				teamName = name;
			}
		}
		String team = teamName; // 所属球队
		int matchNO = this.matchNO; // 参赛场数
		int firstServiceNo = this.firstServiceNo;// 先发场数
		int rebs = this.rebs; // 篮板数
		int assistNo = this.help; // 助攻数
		double time = this.time; // 在场时间
		double hitRate = -1;
		if (handNo != 0)
	         hitRate = 1.0 * hitNo / handNo;// 投篮命中率
		double threeHitRate = -1;// 三分命中率
		if (threeHandNo != 0)
			threeHitRate = 1.0 * threeHitNo / threeHandNo;
		double penaltyHitRate = -1;
		if (penaltyHandNo != 0)
		penaltyHitRate = 1.0 * penaltyHitNo / penaltyHandNo;// 罚球命中率
		int offendNo = offenseRebs;// 进攻数
		int defenceNo = defenceRebs;// 防守数
		int stealsNo = this.stealsNo; // 抢断数
		int blockNo = this.blockNo; // 盖帽数
		int mistakesNo = this.mistakesNo; // 失误数
		int foulsNo = this.foulsNo; // 犯规数
		int points = this.points; // 得分
		double efficiency = (points + rebs + help + stealsNo + blockNo)
				- (handNo - hitNo) - mistakesNo;// 效率
		double GmScEfficiency = points + 0.4 * hitNo - 0.7 * handNo - 0.4
				* (penaltyHandNo - penaltyHitNo) + 0.7 * offenseRebs + 0.3
				* defenceRebs + stealsNo + 0.7 * help + 0.7 * blockNo - 0.4
				* foulsNo - mistakesNo;
		; // GmSc效率值
		double trueHitRate = points / (2 * (handNo + 0.44 * penaltyHandNo)); // 真实命中率
		double hitEfficiency = (hitNo + 0.5 * threeHitNo) / handNo; // 投篮效率
		double rebEfficiency = rebs * (1.0 * teamTotalTime / 5) / time
				/ (myRebs + yourRebs); // 篮板率
		double offenseRebsEfficiency = offenseRebs * (1.0 * teamTotalTime / 5)
				/ time / (myRebs + yourRebs);
		; // 进攻篮板率
		double defenceRebsEfficiency = defenceRebs * (1.0 * teamTotalTime / 5)
				/ time / (myRebs + yourRebs);
		; // 防守篮板率
		double assistEfficiency = 1.0 * help
				/ (time / (teamTotalTime / 5) * totalHit - hitNo); // 助攻率
		double stealsEfficiency = 1.0 * stealsNo * (1.0 * teamTotalTime / 5) / time
				/ yourAttackNO; // 抢断率
		double blockEfficiency = 1.0 * blockNo * (1.0 * teamTotalTime / 5) / time
				/ yourAttackNO; // 盖帽率
		double mistakeEfficiency = 1.0
				* mistakesNo
				/ (handNo - threeHandNo - penaltyHandNo + 0.44 * penaltyHandNo + mistakesNo);// 失误率
		double useEfficiency = (rebs + help + stealsNo + handNo + blockNo
				+ 0.44 * penaltyHandNo + mistakesNo)
				* (1.0 * teamTotalTime / 5)
				/ time
				/ (teamHand + 0.44 * teamPenalty + teamMistakes);// 使用率

		double scoring_rebound_assist = (points + rebs + help) / 3;// 得分/篮板/助攻（加权比1：1：1）
		int block = blockNo;// 盖帽
		int steal = stealsNo;// 抢断
		int foul = foulsNo;// 犯规
		int mistake = mistakesNo;// 失误
		double minute = time / 60;// 分钟
		int shot = hitNo;// 投篮
		int three_points = threeHitNo;// 三分
		int freeThrow = penaltyHitNo;// 罚球
		int twoPair = this.twoPair;// 两双

		PlayerCalculatedData playerData = new PlayerCalculatedData(name, team,
				matchNO, firstServiceNo, rebs / average, assistNo / average,
				time / average, hitRate, threeHitRate, penaltyHitRate, offendNo
						/ average, defenceNo / average, stealsNo / average,
				blockNo / average, mistakesNo / average, foulsNo / average,
				points / average, efficiency, GmScEfficiency, trueHitRate,
				hitEfficiency, rebEfficiency, offenseRebsEfficiency,
				defenceRebsEfficiency, assistEfficiency, stealsEfficiency,
				blockEfficiency, mistakeEfficiency, useEfficiency, rebs
						/ average,// 篮板
				assistNo / average,// 助攻
				scoring_rebound_assist / average,// 得分/篮板/助攻（加权比1：1：1）
				block / average,// 盖帽
				steal / average,// 抢断
				foul / average,// 犯规
				mistake / average,// 失误
				minute / average,// 分钟
				shot / average,// 投篮
				three_points / average,// 三分
				 freeThrow / average,// 罚球
				twoPair / average, playerArea);
		return playerData;
	}

	public void addDate(String teamName, Date date) {
		map.put(teamName, date);
	}

	public void addName(String name) {
		this.name = name;
	}

	public void addTime(double time) {
		this.time += time;
	}

	public void addHitNo(int hitNo) {
		this.hitNo += hitNo;
	}

	public void addHandNo(int handNo) {
		this.handNo += handNo;
	}

	public void addThreeHitNo(int threeHitNo) {
		this.threeHitNo += threeHitNo;
	}

	public void addThreeHandNo(int threeHandNo) {
		this.threeHandNo += threeHandNo;
	}

	public void addPenaltyHitNo(int penaltyHitNo) {
		this.penaltyHitNo += penaltyHitNo;
	}

	public void addPenaltyHandNo(int penaltyHandNo) {
		this.penaltyHandNo += penaltyHandNo;
	}

	public void addOffenseRebs(int offenseRebs) {
		this.offenseRebs += offenseRebs;
	}

	public void addDefenceRebs(int defenceRebs) {
		this.defenceRebs += defenceRebs;
	}

	public void addRebs(int rebs) {
		this.rebs += rebs;
	}

	public void addStealsNo(int stealsNo) {
		this.stealsNo += stealsNo;
	}

	public void addBlockNo(int blockNo) {
		this.blockNo += blockNo;
	}

	public void addMistakesNo(int mistakesNo) {
		this.mistakesNo += mistakesNo;
	}

	public void addFoulsNo(int foulsNo) {
		this.foulsNo += foulsNo;
	}

	public void addPoints(int points) {
		this.points += points;
	}

	public void addMatchNO(int matchNO) {
		this.matchNO += matchNO;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public double getTime() {
		return time;
	}

	public int getHitNo() {
		return hitNo;
	}

	public int getHandNo() {
		return handNo;
	}

	public int getThreeHitNo() {
		return threeHitNo;
	}

	public int getThreeHandNo() {
		return threeHandNo;
	}

	public int getPenaltyHitNo() {
		return penaltyHitNo;
	}

	public int getPenaltyHandNo() {
		return penaltyHandNo;
	}

	public int getOffenseRebs() {
		return offenseRebs;
	}

	public int getDefenceRebs() {
		return defenceRebs;
	}

	public int getRebs() {
		return rebs;
	}

	public int getStealsNo() {
		return stealsNo;
	}

	public int getBlockNo() {
		return blockNo;
	}

	public int getMistakesNo() {
		return mistakesNo;
	}

	public int getFoulsNo() {
		return foulsNo;
	}

	public int getPoints() {
		return points;
	}

	public int getYourTwoPoints() {
		return yourTwoPoints;
	}

	public void setYourTwoPoints(int yourTwoPoints) {
		this.yourTwoPoints = yourTwoPoints;
	}
	public void setLocation(String location) {
		this.location = location;
	}
   public void addDirtyData()
   {
	   dirtyData++;
   }
}
