package vo;

import bl.teambl.TeamSortTool;

public class TeamMatchVO implements Comparable<TeamMatchVO>{
	private String name; // 球队名称
	private int matchNo; // 比赛场数
	private double hitNo; // 投篮命中数
	private double handNo; // 投篮出手次数
	private double threeHitNo; // 三分命中数
	private double threeHandNo; // 三分出手数
	private double penaltyHitNo; // 罚球命中数
	private double penaltyHandNo; // 罚球出手数
	private double offenseRebs; // 进攻篮板数
	private double defenceRebs; // 防守篮板数
	private double rebs; // 篮板数
	private double assistNo; // 助攻数
	private double stealsNo; // 抢断数
	private double blockNo; // 盖帽数
	private double mistakesNo; // 失误数
	private double foulsNo; // 犯规数
	private double points; // 比赛得分
	private double hitRate; // 投篮命中率
	private double threeHitRate;// 三分命中率
	private double penaltyHitRate;// 罚球命中率
	private double winRate; // 胜率
	private double offenseRound; // 进攻回合
	private double offenseEfficiency;// 进攻效率
	private double defenceEfficiency;// 防守效率
	private double orebsEfficiency;// 进攻篮板效率
	private double drebsEfficiency;// 防守篮板效率
	private double stealsEfficiency;// 抢断效率
	private double assistEfficiency;// 助攻率
	private TeamSortTool tool;
	public TeamMatchVO(String name, int matchNo, double hitNo, double handNo,
			double threeHitNo, double threeHandNo, double penaltyHitNo,
			double penaltyHandNo, double offenseRebs, double defenceRebs, double rebs,
			double assistNo, double stealsNo, double blockNo, double mistakesNo,
			double foulsNo, double points, double hitRate, double threeHitRate,
			double penaltyHitRate, double winRate, double offenseRound,
			double offenseEfficiency, double defenceEfficiency,
			double orebsEfficiency,double drebsEfficiency ,double stealsEfficiency,
			double assistEfficiency) {
		super();
		this.name = name;
		this.matchNo = matchNo;
		this.hitNo = hitNo;
		this.handNo = handNo;
		this.threeHitNo = threeHitNo;
		this.threeHandNo = threeHandNo;
		this.penaltyHitNo = penaltyHitNo;
		this.penaltyHandNo = penaltyHandNo;
		this.offenseRebs = offenseRebs;
		this.defenceRebs = defenceRebs;
		this.rebs = rebs;
		this.assistNo = assistNo;
		this.stealsNo = stealsNo;
		this.blockNo = blockNo;
		this.mistakesNo = mistakesNo;
		this.foulsNo = foulsNo;
		this.points = points;
		this.hitRate = hitRate;
		this.threeHitRate = threeHitRate;
		this.penaltyHitRate = penaltyHitRate;
		this.winRate = winRate;
		this.offenseRound = offenseRound;
		this.offenseEfficiency = offenseEfficiency;
		this.defenceEfficiency = defenceEfficiency;
		this.orebsEfficiency = orebsEfficiency;
		this.drebsEfficiency = drebsEfficiency;
		this.stealsEfficiency = stealsEfficiency;
		this.assistEfficiency = assistEfficiency;
	}
	public String getName() {
		return name;
	}
	public int getMatchNo() {
		return matchNo;
	}
	public double getHitNo() {
		return hitNo;
	}
	public double getHandNo() {
		return handNo;
	}
	public double getThreeHitNo() {
		return threeHitNo;
	}
	public double getThreeHandNo() {
		return threeHandNo;
	}
	public double getPenaltyHitNo() {
		return penaltyHitNo;
	}
	public double getPenaltyHandNo() {
		return penaltyHandNo;
	}
	public double getOffenseRebs() {
		return offenseRebs;
	}
	public double getDefenceRebs() {
		return defenceRebs;
	}
	public double getRebs() {
		return rebs;
	}
	public double getAssistNo() {
		return assistNo;
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
	public double getHitRate() {
		return hitRate;
	}

	public double getThreeHitRate() {
		return threeHitRate;
	}
	public double getPenaltyHitRate() {
		return penaltyHitRate;
	}
	public double getWinRate() {
		return winRate;
	}
	public double getOffenseRound() {
		return offenseRound;
	}
	public double getOffenseEfficiency() {
		return offenseEfficiency;
	}
	public double getDefenceEfficiency() {
		return defenceEfficiency;
	}
	public double getoRebsEfficiency() {
		return orebsEfficiency;
	}
	public double getdRebsEfficiency()
	{
		return drebsEfficiency;
	}
	public double getStealsEfficiency() {
		return stealsEfficiency;
	}
	public double getAssistEfficiency() {
		return assistEfficiency;
	}
	public void setTeamSortTool(double data, SortType type)
	{
		tool = new TeamSortTool(data,type);
	}
	public TeamSortTool getTeamSortTool()
	{
		return tool;
	}
	@Override
	public int compareTo(TeamMatchVO o) {
		return tool.compareTo(o.getTeamSortTool());
	}
}