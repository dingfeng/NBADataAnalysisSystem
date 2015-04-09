package vo;

import java.awt.Image;

public class PlayerVO
{
	private String name;// 球员名称
	private Image action;//大头图片
	private Image portrait;//全身图片
	private int number;//球衣号码
	private String position;//位置 
	private int heightfeet;//身高的英尺
	private int heightinch;//身高的英寸
	private String birth;//生日
	private int age;//年龄
	private int exp;//球龄
	private String school;//毕业学校
	//-----------------------------------------------
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
	
	public String toString()
	{
		String area =  null;
		StringBuilder sb = new StringBuilder();
		  sb.append("name"+name+" ;");// 球员名称
		  sb.append("number"+number+" ;");//球衣号码
		  sb.append("position"+position+" ;");//位置 
		  sb.append("heightfeet"+heightfeet+" ;");//身高的英尺
		  sb.append("heightinch"+heightinch+" ;");//身高的英寸
		  sb.append("birth"+birth+" ;");//生日
		  sb.append("age"+age+" ;");//年龄
		  sb.append("exp"+exp+" ;");//球龄
		  sb.append("school"+school+" ;");//毕业学校
		//-----------------------------------------------
		// data calculated
		  sb.append("team"+team+" ;");// 所属球队
		  sb.append("matchNo"+matchNo+" ;");// 参赛场数
		  sb.append("firstServiceNo"+firstServiceNo+" ;");// 先发场数
		  sb.append("rebs"+rebs+" ;");// 篮板数
		  sb.append("assistNo"+assistNo+" ;");// 助攻数
		  sb.append("time"+time+" ;");// 在场时间
		  sb.append("hitRate"+hitRate+" ;");// 投篮命中率
		  sb.append("threeHitRate"+threeHitRate+" ;");// 三分命中率
		  sb.append("penaltyHitRate"+penaltyHitRate+" ;");// 罚球命中率
		  sb.append("offendNo"+offendNo+" ;");// 进攻数
		  sb.append("defenceNo"+defenceNo+" ;");// 防守数
		  sb.append("stealsNo"+stealsNo+" ;");// 抢断数
		  sb.append("blockNo"+blockNo+" ;");// 盖帽数
		  sb.append("mistakesNo"+mistakesNo+" ;");// 失误数
		  sb.append("foulsNo"+foulsNo+" ;");// 犯规数
		  sb.append("points"+points+" ;");// 得分
		  sb.append("efficiency"+efficiency+" ;");// 效率
		  sb.append("GmScEfficiency"+GmScEfficiency+" ;");// GmSc效率值
		  sb.append("trueHitRate"+trueHitRate+" ;");// 真实命中率
		  sb.append("hitEfficiency"+hitEfficiency+" ;");// 投篮效率
		  sb.append("rebEfficiency"+rebEfficiency+" ;");// 篮板率
		  sb.append("offenseRebsEfficiency"+offenseRebsEfficiency+" ;");// 进攻篮板率
		  sb.append("defenceRebsEfficiency"+defenceRebsEfficiency+" ;");// 防守篮板率
		  sb.append("assistEfficiency"+assistEfficiency+" ;");// 助攻率
		  sb.append("stealsEfficiency"+stealsEfficiency+" ;");// 抢断率
		  sb.append("blockEfficiency"+blockEfficiency+" ;");// 盖帽率
		  sb.append("mistakeEfficiency"+mistakeEfficiency+" ;");// 失误率
		  sb.append("useEfficiency"+useEfficiency+" ;");// 使用率
		  sb.append("block"+block+" ;");//盖帽
		  sb.append("scoring_rebound_assist"+scoring_rebound_assist+" ;");//得分/篮板/助攻（加权比1：1：1）
		  sb.append("assist"+assist+" ;");//助攻
		  sb.append("rebound"+rebound+" ;");//篮板
		  sb.append("steal"+steal+" ;");//抢断
		  sb.append("foul"+foul+" ;");//犯规
		  sb.append("mistake"+mistake+" ;");//失误
		  sb.append("minute"+minute+" ;");//分钟
		  sb.append("shot"+shot+" ;");//投篮
		  sb.append("three_points"+three_points+" ;");//三分
		  sb.append("freeThrow"+freeThrow+" ;");//罚球
		  sb.append("twoPair"+twoPair+" ;");//两双
		  return sb.toString();
		
	}
	public double getTwoPair() {
		return twoPair;
	}
	public Area getPlayerArea() {
		return playerArea;
	}
	public PlayerVO(Image action, Image portrait,  int number,
			String position, int heightfeet, int heightinch, String birth,
			int age, int exp, String school,String name, String team, int matchNo, double firstServiceNo,
			double rebs, double assistNo, double time, double hitRate,
			double threeHitRate, double penaltyHitRate, double offendNo,
			double defenceNo, double stealsNo, double blockNo, double mistakesNo,
			double foulsNo, double points, double efficiency, double gmScEfficiency,
			double trueHitRate, double hitEfficiency, double rebEfficiency,
			double offenseRebsEfficiency, double defenceRebsEfficiency,
			double assistEfficiency, double stealsEfficiency,
			double blockEfficiency, double mistakeEfficiency,
			double useEfficiency,
           Area playerArea,
			double rebound,//篮板
			double assist,//助攻
			double scoring_rebound_assist,//得分/篮板/助攻（加权比1：1：1）
			 double block,//盖帽
			 double steal,//抢断
			 double foul,//犯规
			 double mistake,//失误
			 double minute2,//分钟
			 double shot,//投篮
			 double three_points,//三分
			 double freeThrow,//罚球
			 double twoPair) {
		this.name =name;
		this.action = action;
		this.portrait = portrait;
		this.number = number;
		this.position = position;
		this.heightfeet = heightfeet;
		this.heightinch = heightinch;
		this.birth = birth;
		this.age = age;
		this.exp = exp;
		this.school = school;
		//-----------------------------
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
		
	     this.rebound=rebound;//篮板
	     this.assist=assist;//助攻
	     this.scoring_rebound_assist=scoring_rebound_assist;//得分/篮板/助攻（加权比1：1：1）
	     this.block=block;//盖帽
	     this.steal=steal;//抢断
	     this.foul=foul;//犯规
	     this.mistake=mistake;//失误
	     this.minute=minute2;//分钟
	     this.shot=shot;//投篮
	     this.three_points=three_points;//三分
	     this.freeThrow=freeThrow;//罚球
	     this.twoPair=twoPair;//两双
	     
		this.playerArea = playerArea;
	}
    
	public String getName() {
		return name;
	}
	public Image getAction() {
		return action;
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
	public Image getPortrait() {
		return portrait;
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
}
