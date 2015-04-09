package bl.matchbl;

public class PlayerYourInfo {
	private double teamTotalTime;  //球队比赛时间
	private int yourRebs;          //对手篮板数
	private int totalHit;          //
	private double yourAttackNO;
	private int teamHand;
	private int teamPenalty;
	private int teamMistakes;
	private int myRebs;
	private int twoPoints;
	private String team1;
	private String team2;
	public PlayerYourInfo(int twoPoints,double teamTotalTime, int yourRebs, int totalHit,
			 double yourAttackNO, int teamHand,
			int teamPenalty, int teamMistakes,  int myRebs, String team1, String team2, String date) {
		super();
		this.teamTotalTime = teamTotalTime;
		this.yourRebs = yourRebs;
		this.totalHit = totalHit;
		this.yourAttackNO = yourAttackNO;
		this.teamHand = teamHand;
		this.teamPenalty = teamPenalty;
		this.teamMistakes = teamMistakes;
		this.myRebs = myRebs;
		this.twoPoints = twoPoints;
		this.team1 = team1;
		this.team2 = team2;
	}
	public int getTwoPoints()
	{
		return twoPoints;
	}
	public double getTeamTotalTime() {
		return teamTotalTime;
	}
	public int getYourRebs() {
		return yourRebs;
	}
	public int getTotalHit() {
		return totalHit;
	}
	public double getYourAttackNO() {
		return yourAttackNO;
	}
	public int getTeamHand() {
		return teamHand;
	}
	public int getTeamPenalty() {
		return teamPenalty;
	}
	public int getTeamMistakes() {
		return teamMistakes;
	}
	public int getMyRebs() {
		return myRebs;
	}
	public String getTeam1()
	{
		return team1;
	}
	public String getTeam2()
	{
		return team2;
	}
}
