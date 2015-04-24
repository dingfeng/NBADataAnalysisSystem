package test;

import test.data.TeamHighInfo;
import test.data.TeamHotInfo;
import test.data.TeamNormalInfo;
import vo.SortType;
import vo.TeamSortBy;

public class TeamTest {
public static void main(String[] args)
{
	System.out.println("normal ave:");
	testNormalAve();
	System.out.println("normal total :");
	testNormalTotal();
	System.out.println("high : ");
	testHigh();
	System.out.println("hot : ");
	testHot();
}

public static void testNormalAve()
{
	TeamCoreService team = new TeamCore();
	TeamNormalInfo[]  normalInfos = team.getTeamNormalAve(TeamSortBy.points, SortType.DESEND, 6);
	for (TeamNormalInfo f : normalInfos)
	{
		System.out.print(f);
	}
}

public static void testNormalTotal()
{
	TeamCoreService team = new TeamCore();
	TeamNormalInfo[]  normalInfos = team.getTeamNormalTotal(TeamSortBy.points, SortType.DESEND, 6);
	for (TeamNormalInfo f : normalInfos)
	{
		System.out.print(f);
	}
}

public static void testHigh()
{

	TeamCoreService team = new TeamCore();
	TeamHighInfo[]  normalInfos = team.getTeamHighInfo(TeamSortBy.defenceEfficiency, SortType.DESEND, 6);
	for (TeamHighInfo f : normalInfos)
	{
		System.out.print(f);
	}
}

public static void testHot()
{
	TeamCoreService team = new TeamCore();
	TeamHotInfo[]  normalInfos = team.getTeamHotInfo("assist",5);
	for (TeamHotInfo f : normalInfos)
	{
		System.out.print(f);
	}
}

}
