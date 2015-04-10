package vo;

public enum PlayerSortBy {
	name,
	team,
	matchNo,
	firstServiceNo,
	rebs,
	assistNo,
	time,
	hitRate,
	threeHitRate,
	penaltyHitRate,
	offendNo,
	defenceNo,
	stealsNo,
	blockNo,
	mistakesNo,
	foulsNo,
	points,
	efficiency,
	gmScEfficiency,
	trueHitRate,
	hitEfficiency,
	rebEfficiency,
	offenseRebsEfficiency,
    defenceRebsEfficiency,
	 assistEfficiency,
	stealsEfficiency,
	blockEfficiency,
	mistakeEfficiency,
	useEfficiency,
	
	points_uprate, //得分提升率
	rebs_uprate, //篮板提升率
	help_uprate, //助攻提升率
	
    rebound,//篮板
	assist,//助攻
	scoring_rebound_assist,//得分/篮板/助攻（加权比1：1：1）
	block,//盖帽
	steal,//抢断
	foul,//犯规
	mistake,//失误
	minute,//分钟
	shot,//投篮
	three_points,//三分
	freeThrow,//罚球
	twoPair//两双
}
