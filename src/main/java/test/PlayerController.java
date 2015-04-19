package test;
import java.io.PrintStream;

import vo.PlayerSortBy;
import vo.SortType;

public class PlayerController
{
	public void  execute(PrintStream out,String[] command)
	{
		PlayerCommand playerCommand = new PlayerCommand();
		playerCommand.readCommand(command);
		int avg_total = playerCommand.getAvg_total(); //0代表场均数据，1代表赛季总数据。默认为场均数据
		int all_hot_king = playerCommand.getAll_hot_king(); //0代表all所有球员数据，1代表hot热门球员信息（均为场均数据）,2代表king获得数据王
		int season_daily = playerCommand.getSeason_daily(); //0代表赛季数据王，1代表当日数据王
		int n = playerCommand.getN();          //放回一定数目的球员信息，默认为50
		int low_high = playerCommand.getLow_high(); //0代表基本数据low，1代表高阶数据high
		int filter = playerCommand.getFilter();    //0代表非筛选操作，1代表筛选操作
		String position = playerCommand.getPositon(); //F（前锋）,G（后卫）,C（中锋），All 所有的 （默认）
		String league = playerCommand.getLeague(); //West(西部联盟),East(东部联盟),All(所有，默认)
		int year = playerCommand.getYear();          //0(<=22),1(22<X<=25),2(25<X<=30),3(X>30); -1(all default)
		int sort = playerCommand.getSort();   //0表示选择默认排序操作，基础数据的默认排序依据为 -sort score.desc,高阶数据的默认排序依据为-sort winRate.desc
		                  //对于排序属性的值相同的按球队名的升序排序
		PlayerSortBy[]  sorts = playerCommand.getSorts();
		SortType[] sortType = playerCommand.getSortType();
		int sort_len = playerCommand.getSortLen();
		
		PlayerSortBy hotPlayerSort = playerCommand.getHotPlayerSort();
		PlayerSortBy kingPlayerSort = playerCommand.getKingPlayerSort();
		//处理热门球员
		if (all_hot_king == 1)
		{
			
		}
		//处理数据王
		else if (all_hot_king == 2)
		{
			
		}
		//处理所有球员的数据
		else 
		{
			
		}
		
	}
}
