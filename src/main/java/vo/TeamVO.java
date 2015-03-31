package vo;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.w3c.dom.Document;

public class TeamVO {
	private String name; // 球队名称
	private int matchNo; // 比赛场数
	private int hitNo; // 投篮命中数
	private int handNo; // 投篮出手次数
	private int threeHitNo; // 三分命中数
	private int threeHandNo; // 三分出手数
	private int penaltyHitNo; // 罚球命中数
	private int penaltyHandNo; // 罚球出手数
	private int offenseRebs; // 进攻篮板数
	private int defenceRebs; // 防守篮板数
	private int rebs; // 篮板数
	private int assistNo; // 助攻数
	private int stealsNo; // 抢断数
	private int blockNo; // 盖帽数
	private int mistakesNo; // 失误数
	private int foulsNo; // 犯规数
	private int points; // 比赛得分
	private double hitRate; // 投篮命中率
	private double threeHitRate;// 三分命中率
	private double penaltyHitRate;// 罚球命中率
	private double winRate; // 胜率
	private double offenseRound; // 进攻回合
	private double offenseEfficiency;// 进攻效率
	private double defenceEfficiency;// 防守效率
	private double rebsEfficiency;// 篮板效率
	private double stealsEfficiency;// 抢断效率
	private double assistEfficiency;// 助攻率
	private Document image; // 队伍图标
	private String nameAbridge; // 名称缩写
	private String address; // 所在地
	private String matchArea; // 赛区
	private Area playerArea;// 分区
	private String manage; // 主场
	private int foundYear; // 建立时间
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		  sb.append("name : "+name); // 球队名称
		  sb.append("matchNo : "+matchNo); // 比赛场数
		  sb.append("hitNo : "+hitNo); // 投篮命中数
		  sb.append("handNo : "+handNo); // 投篮出手次数
		  sb.append("threeHitNo : "+threeHitNo); // 三分命中数
		  sb.append("threeHandNo : "+threeHandNo); // 三分出手数
		  sb.append("penaltyHitNo : "+penaltyHitNo); // 罚球命中数
		  sb.append("penaltyHandNo : "+penaltyHandNo); // 罚球出手数
		  sb.append("offenseRebs : "+offenseRebs); // 进攻篮板数
		  sb.append("defenceRebs : "+defenceRebs); // 防守篮板数
		  sb.append("rebs : "+rebs); // 篮板数
		  sb.append("assistNo : "+assistNo); // 助攻数
		  sb.append("stealsNo : "+stealsNo); // 抢断数
		  sb.append("blockNo : "+blockNo); // 盖帽数
		  sb.append("mistakesNo : "+mistakesNo); // 失误数
		  sb.append("foulsNo : "+foulsNo); // 犯规数
		  sb.append("points : "+points); // 比赛得分
		  sb.append("hitRate : "+hitRate); // 投篮命中率
		  sb.append("threeHitRate : "+threeHitRate);// 三分命中率
		  sb.append("penaltyHitRate : "+penaltyHitRate);// 罚球命中率
		  sb.append("winRate : "+winRate); // 胜率
		  sb.append("offenseRound : "+offenseRound); // 进攻回合
		  sb.append("offenseEfficiency : "+offenseEfficiency);// 进攻效率
		  sb.append("defenceEfficiency : "+defenceEfficiency);// 防守效率
		  sb.append("rebsEfficiency : "+rebsEfficiency);// 篮板效率
		  sb.append("stealsEfficiency : "+stealsEfficiency);// 抢断效率
		  sb.append("assistEfficiency : "+assistEfficiency);// 助攻率
		  sb.append("nameAbridge : "+nameAbridge); // 名称缩写
		  sb.append("address : "+address); // 所在地
		  sb.append("matchArea : "+matchArea); // 赛区
		  sb.append("manage : "+manage); // 主场
		  sb.append("foundYear : "+foundYear); // 建立时间
		
		return sb.toString();
	}
	public TeamVO(String name, int matchNo, int hitNo, int handNo,
			int threeHitNo, int threeHandNo, int penaltyHitNo,
			int penaltyHandNo, int offenseRebs, int defenceRebs, int rebs,
			int assistNo, int stealsNo, int blockNo, int mistakesNo,
			int foulsNo, int points, double hitRate, double threeHitRate,
			double penaltyHitRate, double winRate, double offenseRound,
			double offenseEfficiency, double defenceEfficiency,
			double rebsEfficiency, double stealsEfficiency,
			double assistEfficiency,
			 Document image,// 队伍图标
	 String nameAbridge, // 名称缩写
	 String address,// 所在地
	 String matchArea, // 赛区
	 Area playerArea,// 分区
	 String manage, // 主场
	 int foundYear)// 建立时间) 
	 {
		this.image = image;
		this.nameAbridge = nameAbridge;
		this.address  = address;
		this.matchArea = matchArea;
		this.playerArea = playerArea;
		this.manage = manage;
		this.foundYear = foundYear;
		
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
		this.rebsEfficiency = rebsEfficiency;
		this.stealsEfficiency = stealsEfficiency;
		this.assistEfficiency = assistEfficiency;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMatchNo() {
		return matchNo;
	}

	public void setMatchNo(int matchNo) {
		this.matchNo = matchNo;
	}

	public int getHitNo() {
		return hitNo;
	}

	public void setHitNo(int hitNo) {
		this.hitNo = hitNo;
	}

	public int getHandNo() {
		return handNo;
	}

	public void setHandNo(int handNo) {
		this.handNo = handNo;
	}

	public int getThreeHitNo() {
		return threeHitNo;
	}

	public void setThreeHitNo(int threeHitNo) {
		this.threeHitNo = threeHitNo;
	}

	public int getThreeHandNo() {
		return threeHandNo;
	}

	public void setThreeHandNo(int threeHandNo) {
		this.threeHandNo = threeHandNo;
	}

	public int getPenaltyHitNo() {
		return penaltyHitNo;
	}

	public void setPenaltyHitNo(int penaltyHitNo) {
		this.penaltyHitNo = penaltyHitNo;
	}

	public int getPenaltyHandNo() {
		return penaltyHandNo;
	}

	public void setPenaltyHandNo(int penaltyHandNo) {
		this.penaltyHandNo = penaltyHandNo;
	}

	public int getOffenseRebs() {
		return offenseRebs;
	}

	public void setOffenseRebs(int offenseRebs) {
		this.offenseRebs = offenseRebs;
	}

	public int getDefenceRebs() {
		return defenceRebs;
	}

	public void setDefenceRebs(int defenceRebs) {
		this.defenceRebs = defenceRebs;
	}

	public int getRebs() {
		return rebs;
	}

	public void setRebs(int rebs) {
		this.rebs = rebs;
	}

	public int getAssistNo() {
		return assistNo;
	}

	public void setAssistNo(int assistNo) {
		this.assistNo = assistNo;
	}

	public int getStealsNo() {
		return stealsNo;
	}

	public void setStealsNo(int stealsNo) {
		this.stealsNo = stealsNo;
	}

	public int getBlockNo() {
		return blockNo;
	}

	public void setBlockNo(int blockNo) {
		this.blockNo = blockNo;
	}

	public int getMistakesNo() {
		return mistakesNo;
	}

	public void setMistakesNo(int mistakesNo) {
		this.mistakesNo = mistakesNo;
	}

	public int getFoulsNo() {
		return foulsNo;
	}

	public void setFoulsNo(int foulsNo) {
		this.foulsNo = foulsNo;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public double getHitRate() {
		return hitRate;
	}

	public void setHitRate(double hitRate) {
		this.hitRate = hitRate;
	}

	public double getThreeHitRate() {
		return threeHitRate;
	}

	public void setThreeHitRate(double threeHitRate) {
		this.threeHitRate = threeHitRate;
	}

	public double getPenaltyHitRate() {
		return penaltyHitRate;
	}

	public void setPenaltyHitRate(double penaltyHitRate) {
		this.penaltyHitRate = penaltyHitRate;
	}

	public double getWinRate() {
		return winRate;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	public double getOffenseRound() {
		return offenseRound;
	}

	public void setOffenseRound(double offenseRound) {
		this.offenseRound = offenseRound;
	}

	public double getOffenseEfficiency() {
		return offenseEfficiency;
	}

	public void setOffenseEfficiency(double offenseEfficiency) {
		this.offenseEfficiency = offenseEfficiency;
	}

	public double getDefenceEfficiency() {
		return defenceEfficiency;
	}

	public void setDefenceEfficiency(double defenceEfficiency) {
		this.defenceEfficiency = defenceEfficiency;
	}

	public double getRebsEfficiency() {
		return rebsEfficiency;
	}

	public void setRebsEfficiency(double rebsEfficiency) {
		this.rebsEfficiency = rebsEfficiency;
	}

	public double getStealsEfficiency() {
		return stealsEfficiency;
	}

	public void setStealsEfficiency(double stealsEfficiency) {
		this.stealsEfficiency = stealsEfficiency;
	}

	public double getAssistEfficiency() {
		return assistEfficiency;
	}

	public void setAssistEfficiency(double assistEfficiency) {
		this.assistEfficiency = assistEfficiency;
	}
	
	public Document getImage() {
		return image;
	}
	public String getNameAbridge() {
		return nameAbridge;
	}
	public String getAddress() {
		return address;
	}
	public String getMatchArea() {
		return matchArea;
	}
	public Area getPlayerArea() {
		return playerArea;
	}
	public String getManage() {
		return manage;
	}
	
	public int getFoundYear() {
		return foundYear;
	}
}
