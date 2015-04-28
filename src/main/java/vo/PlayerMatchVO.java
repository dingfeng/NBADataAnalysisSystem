package vo;

import po.MatchPlayerPO;
import bl.teambl.SortTool;


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
	private double offendRebsNo;// 进攻篮板数
	private double defenceRebsNo;// 防守篮板数
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
	
	
	private double scoring_rebound_assist;//得分/篮板/助攻（加权比1：1：1）
	private double minute;//分钟
    private double handNo;//投篮出手数
    private double three_points;//三分出手数
    private double penaltyHandNo;//罚球出手数
	private double twoPair;//两双
	
	private double points_uprate; //得分提升率
	private double rebs_uprate; //篮板提升率
	private double help_uprate; //助攻提升率
	private double hotData;    //热点数据
	
	
	private String location;//位置
	private double hitNo; // 投篮命中数
	private double threeHitNo; // 三分命中数
	private double threeHandNo; // 三分出手数
	private double penaltyHitNo; // 罚球命中数
	private double offenseRebs; // 进攻篮板数
	private double defenceRebs; // 防守篮板数
	private double help;//总篮板数
	private SortTool sortTool; //排序工具
	private SortType type;
	public PlayerMatchVO(String name)
	{
		this.name = name;
	}
	public PlayerMatchVO(String name, String team, int matchNo,
			double firstServiceNo, double rebs, double assistNo, double time,
			double hitRate, double threeHitRate, double penaltyHitRate,
			double offendRebsNo, double defenceRebsNo, double stealsNo, double blockNo,
			double mistakesNo, double foulsNo, double points,double points_uprate, double rebs_uprate, double help_uprate,
			double efficiency, double gmScEfficiency, double trueHitRate,
			double hitEfficiency, double rebEfficiency,
			double offenseRebsEfficiency, double defenceRebsEfficiency,
			double assistEfficiency, double stealsEfficiency,
			double blockEfficiency, double mistakeEfficiency,
			double useEfficiency,  double scoring_rebound_assist,
			double penaltyHandNo,
			double minute, double handNo,
			double three_points,  double twoPair, double penaltyHitNo, double hitNo,double threeHitNo) {
		super();
		
		this.penaltyHitNo = penaltyHitNo;
		this.hitNo = hitNo;
		this.threeHitNo = threeHitNo;
		
		this.handNo = handNo;
		this.minute = minute;
		this.three_points = three_points;
		this.threeHandNo = three_points;
		this.points_uprate = points_uprate;
		this.rebs_uprate = rebs_uprate;
		this.help_uprate = help_uprate;
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
		this.offendRebsNo = offendRebsNo;
		this.defenceRebsNo = defenceRebsNo;
		this.stealsNo = stealsNo;
		this.blockNo = blockNo;
		this.mistakesNo = mistakesNo;
		this.foulsNo = foulsNo;
		this.points = points;
		this.efficiency = efficiency;
		this.GmScEfficiency = gmScEfficiency;
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
		this.scoring_rebound_assist = scoring_rebound_assist;
		this.twoPair = twoPair;
		this.penaltyHandNo = penaltyHandNo;
		
	}

	public PlayerMatchVO ( MatchPlayerPO playerpo )
	{
		name = playerpo.getName();
		team = playerpo.getTeamnameAbridge();
		this.time = playerpo.getTime();
		this.hitNo = playerpo.getHitNo();
		this.handNo = playerpo.getHandNo();
		this.threeHitNo = playerpo.getThreeHitNo();
		this.threeHandNo = playerpo.getThreeHandNo();
		this.penaltyHandNo = playerpo.getPenaltyHitNo();
		this.penaltyHitNo = playerpo.getPenaltyHitNo();
		this.offendRebsNo = playerpo.getOffenseRebs();
		this.defenceRebs = playerpo.getDefenceRebs();
		this.rebs = playerpo.getRebs();
		this.help = playerpo.getHelp();
		this.stealsNo = playerpo.getStealsNo();
		this.blockNo = playerpo.getBlockNo();
		this.mistakesNo = playerpo.getMistakesNo();
		this.foulsNo = playerpo.getFoulsNo();
		this.points = playerpo.getPoints();
		this.hotData = playerpo.getHotData();
	}

	
	
	public double getHelp()
	{
		return help;
	}
	public double getDefenceRebs()
	{
		return defenceRebs;
	}
	
	public double getOffenseRebs()
	{
		return offenseRebs;
	}
	public  double getPenaltyHitNo()
	{
		return penaltyHitNo;
	}
	
	public String getLocation()
	{
		return location;
	}
	public double getHitNo()
	{
		return hitNo;
	}
	public double getThreeHitNo()
	{
		return this.threeHitNo;
	}
	public double getThreeHandNo()
	{
		return threeHandNo;
	}
	
	public double getOffendRebsNo() {
		return  offendRebsNo;
	}

	public double getDefenceRebsNo() {
		return defenceRebsNo;
	}

	public double getHandNo() {
		return handNo;
	}

	public double getPenaltyHandNo() {
		return penaltyHandNo;
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
		return offendRebsNo;
	}
	public double getDefenceNo() {
		return defenceRebsNo;
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
	public double getScoring_rebound_assist() {
		return scoring_rebound_assist;
	}
	public double getMinute() {
		return minute;
	}
	public double getThree_points() {
		return three_points;
	}
	public double getTwoPair() {
		return twoPair;
	}
	public double getPoints_uprate() {
		return points_uprate;
	}

	public double getRebs_uprate() {
		return rebs_uprate;
	}

	public double getHelp_uprate() {
		return help_uprate;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		  sb.append("name : \t"+name);// 球员名称
		  sb.append(" team : \t"+team);// 所属球队
		  sb.append("matchNo : \t"+matchNo);// 参赛场数
		  sb.append("firstServiceNo : \t"+firstServiceNo);// 先发场数
		  sb.append("rebs : \t"+rebs);// 篮板数
		  sb.append("assistNo : \t"+assistNo);// 助攻数
		  sb.append("time : \t"+time);// 在场时间
		  sb.append("hitRate : \t"+hitRate);// 投篮命中率
		  sb.append("threeHitRate : \t"+threeHitRate);// 三分命中率
		  sb.append("penaltyHitRate :\t "+penaltyHitRate);// 罚球命中率
		  sb.append( "offendRebsNo :\t "+offendRebsNo);// 进攻篮板数
		  sb.append("defenceRebsNo : \t"+defenceRebsNo);// 防守篮板数
		  sb.append( "stealsNo : \t"+stealsNo);// 抢断数
		  sb.append("blockNo : \t"+blockNo);// 盖帽数
		  sb.append( "mistakesNo : \t"+mistakesNo);// 失误数
		  sb.append("foulsNo : \t"+foulsNo);// 犯规数
		  sb.append( "points : \t"+points);// 得分
		  sb.append("efficiency : \t"+efficiency);// 效率
		  sb.append("GmScEfficiency : \t"+GmScEfficiency);// GmSc效率值
		  sb.append( "trueHitRate : \t"+trueHitRate);// 真实命中率
		  sb.append("hitEfficiency : \t"+hitEfficiency);// 投篮效率
		  sb.append( "rebEfficiency : \t"+rebEfficiency);// 篮板率
		  sb.append("offenseRebsEfficiency : \t"+offenseRebsEfficiency);// 进攻篮板率
		  sb.append("defenceRebsEfficiency : \t"+defenceRebsEfficiency);// 防守篮板率
		  sb.append("assistEfficiency : \t"+assistEfficiency);// 助攻率
		  sb.append(" stealsEfficiency : \t"+stealsEfficiency);// 抢断率
		  sb.append( "blockEfficiency : \t"+blockEfficiency);// 盖帽率
		  sb.append( "mistakeEfficiency : \t"+mistakeEfficiency);// 失误率
		  sb.append("useEfficiency : \t"+useEfficiency);// 使用率
		
		  sb.append( "scoring_rebound_assist :\t "+scoring_rebound_assist);//得分/篮板/助攻（加权比1：1：1）
		  sb.append(  "minute : \t"+minute);//分钟
		  sb.append( "handNo : \t"+handNo);//投篮
		  sb.append( "three_points : \t"+three_points);//三分
		  sb.append( "penaltyHandNo :\t "+penaltyHandNo);//罚球
		  sb.append(  "twoPair : \t"+twoPair);//两双
		
		  sb.append( "points_uprate : \t"+points_uprate); //得分提升率
		  sb.append( "rebs_uprate : \t"+rebs_uprate); //篮板提升率
		  sb.append( "help_uprate : \t"+help_uprate); //助攻提升率
		  return sb.toString();
	}
	
	public double getHotData()
	{
		return  hotData;
	}
	
	public void setSortTool(SortTool sortTool)
	{
		this.sortTool = sortTool;
		this.hotData = sortTool.getData();
	}
	
	public SortTool getSortTool()
	{
		return sortTool;
	}
	
	@Override
	public int compareTo(PlayerMatchVO e) {
		int cpr = sortTool.compareTo(e.getSortTool());
		if (cpr == 0)
		{
			cpr = name.compareTo(e.getName());
		}
		return cpr;
	}
}
