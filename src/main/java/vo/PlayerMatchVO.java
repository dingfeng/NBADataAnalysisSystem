package vo;

import bl.playerbl.SortBy;

public class PlayerMatchVO implements Comparable<PlayerMatchVO>{
	private String name;// 球员名称
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
	
	private double block;//盖帽
	private double scoring_rebound_assist;//得分/篮板/助攻（加权比1：1：1）
	private Area playerArea; // 
	private double assist;//助攻
	private double rebound;//篮板
	private double steal;//抢断
	private double foul;//犯规
	private double mistake;//失误
	private double minute;//分钟
    private double shot;//投篮
    private double three_points;//三分
    private double freeThrow;//罚球
	private double twoPair;//两双
	
	private SortBy sortby;
	
	public PlayerMatchVO(String name, String team, int matchNo,
			double firstServiceNo, double rebs, double assistNo, double time,
			double hitRate, double threeHitRate, double penaltyHitRate,
			double offendNo, double defenceNo, double stealsNo, double blockNo,
			double mistakesNo, double foulsNo, double points,
			double efficiency, double gmScEfficiency, double trueHitRate,
			double hitEfficiency, double rebEfficiency,
			double offenseRebsEfficiency, double defenceRebsEfficiency,
			double assistEfficiency, double stealsEfficiency,
			double blockEfficiency, double mistakeEfficiency,
			double useEfficiency, double block, double scoring_rebound_assist,
			Area playerArea, double assist, double rebound, double steal,
			double foul, double mistake, double minute, double shot,
			double three_points, double freeThrow, double twoPair, SortBy sortby) {
		super();
		this.name = name;
		this.team = team;
		this.matchNo = matchNo;
		this.firstServiceNo = firstServiceNo;
		this.rebs = rebs;
		this.assistNo = assistNo;
		this.time = time;
		this.hitRate = hitRate;
		this.threeHitRate = threeHitRate;
		this.penaltyHitRate = penaltyHitRate;
		this.offendNo = offendNo;
		this.defenceNo = defenceNo;
		this.stealsNo = stealsNo;
		this.blockNo = blockNo;
		this.mistakesNo = mistakesNo;
		this.foulsNo = foulsNo;
		this.points = points;
		this.efficiency = efficiency;
		GmScEfficiency = gmScEfficiency;
		this.trueHitRate = trueHitRate;
		this.hitEfficiency = hitEfficiency;
		this.rebEfficiency = rebEfficiency;
		this.offenseRebsEfficiency = offenseRebsEfficiency;
		this.defenceRebsEfficiency = defenceRebsEfficiency;
		this.assistEfficiency = assistEfficiency;
		this.stealsEfficiency = stealsEfficiency;
		this.blockEfficiency = blockEfficiency;
		this.mistakeEfficiency = mistakeEfficiency;
		this.useEfficiency = useEfficiency;
		this.block = block;
		this.scoring_rebound_assist = scoring_rebound_assist;
		this.playerArea = playerArea;
		this.assist = assist;
		this.rebound = rebound;
		this.steal = steal;
		this.foul = foul;
		this.mistake = mistake;
		this.minute = minute;
		this.shot = shot;
		this.three_points = three_points;
		this.freeThrow = freeThrow;
		this.twoPair = twoPair;
		this.sortby = sortby;
	}

	public SortBy getSortby(){
		return sortby;
	}
	
	public String getName() {
		return name;
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
	public double getBlock() {
		return block;
	}
	public double getScoring_rebound_assist() {
		return scoring_rebound_assist;
	}
	public Area getPlayerArea() {
		return playerArea;
	}
	public double getAssist() {
		return assist;
	}
	public double getRebound() {
		return rebound;
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
	@Override
	public int compareTo(PlayerMatchVO arg0) {
		return sortby.compareTo(arg0.getSortby());
	}
}