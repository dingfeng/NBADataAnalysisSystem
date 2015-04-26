package test;

import java.util.Arrays;

import vo.PlayerSortBy;
import vo.SortType;

public class PlayerCommand 
{
	int avg_total = 0; //0代表场均数据，1代表赛季总数据。默认为场均数据
	int all_hot_king = 0; //0代表all所有球员数据，1代表hot热门球员信息（均为场均数据）,2代表king获得数据王
	int season_daily = 0; //0代表赛季数据王，1代表当日数据王
	int n = -1;          //-1代表默认值
	int low_high = 0 ; //0代表基本数据low，1代表高阶数据high
	int filter = 0;    //0代表非筛选操作，1代表筛选操作
	String position = "All"; //F（前锋）,G（后卫）,C（中锋），All 所有的 （默认）
	String league = "All"; //West(西部联盟),East(东部联盟),All(所有，默认)
	int year = -1;          //0(<=22),1(22<X<=25),2(25<X<=30),3(X>30); -1(all default)
	int sort =  0 ;   //0表示选择默认排序操作，基础数据的默认排序依据为 -sort score.desc,高阶数据的默认排序依据为-sort winRate.desc
	                  //对于排序属性的值相同的按球队名的升序排序
	PlayerSortBy[]  sorts = new PlayerSortBy[10];
	SortType[] sortType = new SortType[10];
	int sort_len = -1;
	
	String hotPlayerSort;
	String kingPlayerSort;


	
	public void readCommand(String[] commands)
	{
		int len = commands.length;
		String temp = null;
		for (int i = 0; i < len; i++)
		{
		  switch (commands[i])
		  {
		  case "-avg":
			  avg_total = 0;
			  break;
		  case "-total":
			  avg_total = 1;
			  break;
		  case "-all":
			  all_hot_king = 0;
			  break;
		  case "-hot":
			  all_hot_king = 1;
			  ++i;
			  hotPlayerSort = commands[i];
			  break;
		  case  "-king":
			  all_hot_king = 2;
			  ++i;
			  kingPlayerSort = commands[i];
			  break;
		  case  "-season":
			  season_daily = 0;
			  break;
		  case "-daily":
			  season_daily = 1;
			  break;
		  case "-n":
			  ++i;
			  n = Integer.parseInt(commands[i]);
			  break;
		  case "-high":
			  low_high = 1;
			  break;
		  case "-filter":
			  filter = 1;
			  ++i;
			  String filter_choice = commands[i];
			  String[] choices = null;
			  String[] filter_choice_list = filter_choice.split(",");
			  for (int k = 0;k < filter_choice_list.length; k++)
			  {
				  choices = filter_choice_list[k].split("\\.");
				  switch (choices[0])
				  {
				  case "position":
					  position = choices[1];
					  break;
				  case "league":
					  league  = choices[1];
					  break;
				  case "age":
					  switch (choices[1])
					  {
					  case "<=22":
						  year = 0;
						  break;
					  case "22<X<=25":
						  year = 1;
						  break;
					  case "25<X<=30":
						  year = 2;
						  break;
					  case  ">30":
						  year =3;
						  break;
					  case  "All":
						  year = -1;
						  break;
					  }
					  break;
				  }
			  }
			  break;
		  case "-sort":
			  sort = 1;
			  ++i;
			  String choice = null;
			  while ( i < len)
			  {
				choice = commands[i];
				if (!choice.equals(","))
				{
				dealWithOneSortField(choice);
				}
				++i;
			  }
			  break;
		  }
		}
	}

	
	private void dealWithOneSortField(String field)
	{
		String[] choices = field.split("\\.");
		++sort_len;
		PlayerSortBy sortBy = null;
		SortType sortType = null;
		switch (choices[0])
		{
		
		//得分
		case "point":
		    sortBy = PlayerSortBy.points;
			break;
	    //篮板
		case "rebound":
			sortBy = PlayerSortBy.rebound;
			break;
		//助攻
		case  "assist":
			sortBy = PlayerSortBy.assistNo;
			break;
		//盖帽
		case  "blockShot":
			sortBy = PlayerSortBy.blockNo;
			break;
		//抢断
		case  "steal":
			sortBy = PlayerSortBy.stealsNo;
			break;
		//犯规
		case  "foul":
			sortBy = PlayerSortBy.foulsNo;
			break;
		//失误
		case  "fault":
			sortBy = PlayerSortBy.mistakesNo;
			break;
		//分钟（上场时间）
		case  "minute":
			sortBy = PlayerSortBy.minute;
			break;
		//效率
		case  "efficient":
			sortBy = PlayerSortBy.efficiency;
			break;
	   //投篮命中率
		case  "shot":
			sortBy = PlayerSortBy.hitRate;
			break;
		//三分球命中率
		case  "three":
			sortBy = PlayerSortBy.threeHitRate;
			break;
		//罚球命中率
		case  "penalty":
			sortBy = PlayerSortBy.penaltyHitRate;
			break;
		//两双
		case  "doubleTwo":
			sortBy = PlayerSortBy.twoPair;
			break;
		//真实投篮命中率
		case   "realShot":
			sortBy = PlayerSortBy.trueHitRate;
			break;
		//gmsc效率值
		case  "GmSc":
			sortBy = PlayerSortBy.gmScEfficiency;
			break;
		//投篮效率
		case  "shotEfficient":
			sortBy = PlayerSortBy.hitEfficiency;
			break;
		//篮板效率
		case  "reboundEfficient":
			sortBy = PlayerSortBy.rebEfficiency;
			break;
		//进攻篮板率
		case  "offendReboundEfficient":
			sortBy = PlayerSortBy.offenseRebsEfficiency;
			break;
		//防守篮板率
		case  "defendReboundEfficient":
			sortBy = PlayerSortBy.defenceRebsEfficiency;
			break;
		//助攻率
		case  "assistEfficient":
			sortBy = PlayerSortBy.assistEfficiency;
			break;
		//抢断率
		case   "stealEfficient":
			sortBy = PlayerSortBy.stealsEfficiency;
			break;
		//盖帽率
		case   "blockShotEfficient":
			sortBy = PlayerSortBy.blockEfficiency;
			break;
		//失误率
		case  "faultEfficient":
			sortBy = PlayerSortBy.mistakeEfficiency;
			break;
		//使用率
		case  "frequency":
			sortBy = PlayerSortBy.useEfficiency;
			break;
		}
		switch (choices[1])
		{
		case  "desc":
			sortType = SortType.DESEND;
			break;
		case  "asc":
			sortType = SortType.ASEND;
			break;
		}
		sorts[sort_len] = sortBy;
		this.sortType[sort_len] = sortType;
	}
	public int getAvg_total() {
		return avg_total;
	}

	public int getAll_hot_king() {
		return all_hot_king;
	}

	public int getSeason_daily() {
		return season_daily;
	}

	public int getN() {
		return n;
	}

	public int getLow_high() {
		return low_high;
	}

	public int getFilter() {
		return filter;
	}

	public String getPositon() {
		return position;
	}

	public String getLeague() {
		return league;
	}

	public int getYear() {
		return year;
	}

	public int getSort() {
		return sort;
	}

	public PlayerSortBy[] getSorts() {
		return Arrays.copyOf(sorts, sort_len+1);
	}

	public SortType[] getSortType() {
		return Arrays.copyOf(sortType, sort_len+1);
	}

	
	public String getHotPlayerSort() {
		return hotPlayerSort;
	}
	
	public String getKingPlayerSort()
	{
		return kingPlayerSort;
	}
	public int getSortLen()
	{
		return sort_len;
	}
}
