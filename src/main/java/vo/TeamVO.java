package vo;

import java.awt.Image;

import javax.swing.ImageIcon;

import org.w3c.dom.Document;

public class TeamVO {
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
	private double podoubles; // 比赛得分
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
	private double foundYear; // 建立时间
	
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
		  sb.append("podoubles : "+podoubles); // 比赛得分
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
	public TeamVO(String name, int matchNo, double hitNo, double handNo,
			double threeHitNo, double threeHandNo, double penaltyHitNo,
			double penaltyHandNo, double offenseRebs, double defenceRebs, double rebs,
			double assistNo, double stealsNo, double blockNo, double mistakesNo,
			double foulsNo, double podoubles, double hitRate, double threeHitRate,
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
	 double foundYear)// 建立时间) 
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
		this.podoubles = podoubles;
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

	public double getHitNo() {
		return hitNo;
	}

	public void setHitNo(double hitNo) {
		this.hitNo = hitNo;
	}

	public double getHandNo() {
		return handNo;
	}

	public void setHandNo(double handNo) {
		this.handNo = handNo;
	}

	public double getThreeHitNo() {
		return threeHitNo;
	}

	public void setThreeHitNo(double threeHitNo) {
		this.threeHitNo = threeHitNo;
	}

	public double getThreeHandNo() {
		return threeHandNo;
	}

	public void setThreeHandNo(double threeHandNo) {
		this.threeHandNo = threeHandNo;
	}

	public double getPenaltyHitNo() {
		return penaltyHitNo;
	}

	public void setPenaltyHitNo(double penaltyHitNo) {
		this.penaltyHitNo = penaltyHitNo;
	}

	public double getPenaltyHandNo() {
		return penaltyHandNo;
	}

	public void setPenaltyHandNo(double penaltyHandNo) {
		this.penaltyHandNo = penaltyHandNo;
	}

	public double getOffenseRebs() {
		return offenseRebs;
	}

	public void setOffenseRebs(double offenseRebs) {
		this.offenseRebs = offenseRebs;
	}

	public double getDefenceRebs() {
		return defenceRebs;
	}

	public void setDefenceRebs(double defenceRebs) {
		this.defenceRebs = defenceRebs;
	}

	public double getRebs() {
		return rebs;
	}

	public void setRebs(double rebs) {
		this.rebs = rebs;
	}

	public double getAssistNo() {
		return assistNo;
	}

	public void setAssistNo(double assistNo) {
		this.assistNo = assistNo;
	}

	public double getStealsNo() {
		return stealsNo;
	}

	public void setStealsNo(double stealsNo) {
		this.stealsNo = stealsNo;
	}

	public double getBlockNo() {
		return blockNo;
	}

	public void setBlockNo(double blockNo) {
		this.blockNo = blockNo;
	}

	public double getMistakesNo() {
		return mistakesNo;
	}

	public void setMistakesNo(double mistakesNo) {
		this.mistakesNo = mistakesNo;
	}

	public double getFoulsNo() {
		return foulsNo;
	}

	public void setFoulsNo(double foulsNo) {
		this.foulsNo = foulsNo;
	}

	public double getPodoubles() {
		return podoubles;
	}

	public void setPodoubles(double podoubles) {
		this.podoubles = podoubles;
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
	
	public double getFoundYear() {
		return foundYear;
	}
}
