package test;

import java.io.PrintStream;

import test.data.TeamHighInfo;
import test.data.TeamHotInfo;
import test.data.TeamNormalInfo;
import vo.SortType;
import vo.TeamSortBy;
import bl.teambl.Team;

public class TeamController
{
//	 private int ave_total = 0;   //默认为0为ave,1代表total
//	  private int all_hot = -1;   //默认小于零代表所有球队的信息，当大于零时代表获得热门球队，数值为命令数组的下标
//	  private int num = -1;      //默认值为-1，代表无此命令，值为30.当num为正数时，代表命令数组的下标
//	  private int base_high = 0; //默认值为0代表基本数据类型，1代表高阶数据
//	  private int sort = -1;     //-1代表没有，当它大于1时代表命令数组的下标
	public void  execute(PrintStream out,String[] command)
	{
		try{
		TeamCore teamCore = new TeamCore();
		TeamCommand team_command = new TeamCommand();
		team_command.readCommand(command);
		int ave_total = team_command.getAve_total();   //默认为0为ave,1代表total
		int all_hot = team_command.getAll_hot();   //默认小于零代表所有球队的信息，当大于零时代表获得热门球队，数值为命令数组的下标
		int num = team_command.getNum();      //默认值为-1，代表无此命令，值为30.当num为正数时，代表命令数组的下标
		int base_high = team_command.getBase_high(); //默认值为0代表基本数据类型，1代表高阶数据
		int sort = team_command.getSort();     //-1代表没有，当它大于1时代表命令数组的下标
		
		if (base_high == 1)
		{
			TeamSortBy  sortby = TeamSortBy.winRate;
			SortType sortType = null;
			if (sort!=-1)
			{
				String sortStr = command[sort+1];
				 String[] sort_array = sortStr.split("\\.");
				 sortby = getSortBy(sort_array[0]);
				 if (sort_array[1].equals("asc"))
				 {
					 sortType = SortType.ASEND;
				 }
				 else sortType = SortType.DESEND;
			}
			 int n = 30;
			 if (num != -1)
			 {
				 n = Integer.parseInt(command[num+1]);
			 }
			 if (n > 30)  n =30;
			TeamHighInfo[] teamHighInfos = teamCore.getTeamHighInfo(sortby, sortType, n);
			for (TeamHighInfo info : teamHighInfos)
			{
				out.append(info.toString());
			}
		}
		//处理热门球队
		else if (all_hot > 0)
		{
			String field = command[all_hot+1];
			
			int n = 5;
			if (num != -1)
			{
				n = Integer.parseInt(command[num+1]);
			}
			if (n > 30) n = 30;
			//获得热门球队 
			TeamHotInfo[] hotInfos = teamCore.getTeamHotInfo(field, n);
			for (TeamHotInfo info : hotInfos)
			{
				out.append(info.toString());
			}
			
		}
		else if (ave_total == 0)  //场均数据
		{
		 int n = 30;
		 if (num != -1)
		 {
			 n = Integer.parseInt(command[num+1]);
		 }
		 if (n > 30)  n =30;
		 TeamSortBy sortby = null;
		 SortType sortType = SortType.DESEND;
		 if (sort == -1)
		 {
			 //排序依据默认值
		    if (base_high == 0)
		    {
			  sortby = TeamSortBy.points;
		    }
		    else 
		    {
			 sortby = TeamSortBy.winRate;
		    }
		   
		 }
		 else
		 {
			 String sortStr = command[sort+1];
			 String[] sort_array = sortStr.split("\\.");
			 sortby = getSortBy(sort_array[0]);
			 if (sort_array[1].equals("asc"))
			 {
				 sortType = SortType.ASEND;
			 }
			 else sortType = SortType.DESEND;
		 }
		 TeamNormalInfo[]  teams = teamCore.getTeamNormalAve(sortby, sortType, n);
		    for (TeamNormalInfo info : teams)
		    {
		    	out.append(info.toString());
		    }
		}
		else       //赛季数据
		{
			int n = 30;
			 if (num != -1)
			 {
				 n = Integer.parseInt(command[num+1]);
			 }
			 if (n > 30)  n =30;
			 TeamSortBy sortby = null;
			 SortType sortType = SortType.DESEND;
			 if (sort == -1)
			 {
				 //排序依据默认值
			    if (base_high == 0)
			    {
				  sortby = TeamSortBy.points;
			    }
			    else 
			    {
				 sortby = TeamSortBy.winRate;
			    }
			 }
			 else
			 {
				 String sortStr = command[sort+1];
				 String[] sort_array = sortStr.split(".");
				 sortby = getSortBy(sort_array[0]);
				 if (sort_array[1].equals("asc"))
				 {
					 sortType = SortType.ASEND;
				 }
				 else sortType = SortType.DESEND;
			 }
			
			 TeamNormalInfo[]  teams = teamCore.getTeamNormalTotal(sortby, sortType, n);
			    for (TeamNormalInfo info : teams)
			    {
			    	out.append(info.toString());
			    }
		}
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private TeamSortBy getSortBy(String field)
	{
		TeamSortBy sortBy = null;
		switch (field)
		{
		//基础数据
		case "point":                   //得分
			 sortBy = TeamSortBy.points;
			break;                  
		case "rebound":                 //篮板
			sortBy = TeamSortBy.rebs;
			break;
		case  "assist":                 //助攻
			sortBy = TeamSortBy.assistNo;
			break;
		case  "blockShot":              //盖帽
			sortBy = TeamSortBy.blockNo;
			break; 
		case  "steal":                  //抢断
			sortBy = TeamSortBy.stealsNo;
			break;
		case  "foul":                   //犯规
			sortBy = TeamSortBy.foulsNo;
			break;
		case  "fault":                  //失误
			sortBy = TeamSortBy.mistakesNo;
			break;
		case  "shot":                   //投篮命中率
			sortBy = TeamSortBy.hitRate;
			break;
		case  "three":                 //三分球命中率
			sortBy = TeamSortBy.threeHitRate;
			break;
		case  "penalty" :              //罚球命中率
			sortBy = TeamSortBy.penaltyHitRate;
			break;
		case  "defendRebound":         //防守篮板数
			sortBy = TeamSortBy.defenceRebs;
			break;
		case  "offendRebound":         //进攻篮板数
			sortBy = TeamSortBy.offenseRebs;
			break;
	   // 高阶数据
		case  "winRate":              //胜率
			sortBy = TeamSortBy.winRate;
			break;
		case  "offendRound":          //进攻回合
			sortBy = TeamSortBy.offenseRound;
			break;
		case  "offendEfficient":      //进攻效率
			sortBy = TeamSortBy.offenseEfficiency;
			break;
		case  "defendEfficient":      //防守效率
			sortBy = TeamSortBy.defenceEfficiency;
			break;
		case  "offendReboundEfficient":    //进攻篮板效率
			sortBy = TeamSortBy.orebsEfficiency;
			break;
		case  "defendReboundEfficient":     //防守篮板效率
			sortBy = TeamSortBy.drebsEfficiency;
			break;
		case  "stealEfficient":            //抢断效率
			sortBy = TeamSortBy.stealsEfficiency;
			break;
		case  "assistEfficient":           //助攻效率
			sortBy = TeamSortBy.assistEfficiency;
			break;
		}
		return sortBy;
	}
	
}
