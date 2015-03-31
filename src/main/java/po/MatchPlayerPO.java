package po;

public class MatchPlayerPO
{
	private String name;// 球员名称
	private String location;//位置
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
	private int help;//总篮板数
	private int stealsNo;// 抢断数
	private int blockNo;// 盖帽数
	private int mistakesNo;// 失误数
	private int foulsNo;// 犯规数
	private int points;// 得分
	private boolean dirty;  //脏数据
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(name + " ");// 球员名称
		sb.append(location+" ");//位置
		sb.append(time+" ");// 在场时间
		sb.append(hitNo+" "); // 投篮命中数
		sb.append(handNo+" "); // 投篮出手次数
		sb.append(threeHitNo+" "); // 三分命中数
		sb.append(threeHandNo+" "); // 三分出手数
		sb.append(penaltyHitNo+" "); // 罚球命中数
		sb.append(penaltyHandNo+" "); // 罚球出手数
		sb.append(offenseRebs+" "); // 进攻篮板数
		sb.append(defenceRebs+" "); // 防守篮板数
		sb.append(rebs+" "); // 篮板数
		sb.append(help+" ");//总篮板数
		sb.append(stealsNo+" ");// 抢断数
		sb.append(blockNo+" ");// 盖帽数
		sb.append(mistakesNo+" ");// 失误数
		sb.append(foulsNo+" ");// 犯规数
		sb.append(points+" ");// 得分
		return sb.toString();
	}
	public MatchPlayerPO(String name, String location, double time, int hitNo,
			int handNo, int threeHitNo, int threeHandNo, int penaltyHitNo,
			int penaltyHandNo, int offenseRebs, int defenceRebs, int rebs,
			int help, int stealsNo, int blockNo, int mistakesNo,
			int foulsNo)
	{
		super();
		this.name = name;
		this.location = location;
		this.time = time;
		this.hitNo = hitNo;
		this.handNo = handNo;
		this.threeHitNo = threeHitNo;
		this.threeHandNo = threeHandNo;
		this.penaltyHitNo = penaltyHitNo;
		this.penaltyHandNo = penaltyHandNo;
		this.offenseRebs = offenseRebs;
		this.defenceRebs = defenceRebs;
		this.rebs = rebs;
		this.help = help;
		this.stealsNo = stealsNo;
		this.blockNo = blockNo;
		this.mistakesNo = mistakesNo;
		this.foulsNo = foulsNo;
		this.points = hitNo * 2+threeHitNo * 3 +penaltyHitNo;
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
	public int getHelp() {
		return help;
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
	public boolean isDirty()
	{
		return dirty;
	}
	public void setDirty()
	{
		dirty = true;
	}
}