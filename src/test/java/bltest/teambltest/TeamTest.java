package bltest.teambltest;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import po.TeamPO;
import vo.SortType;
import vo.TeamMatchVO;
import vo.TeamSortBy;
import bl.teambl.Team;

public class TeamTest {
	Team team;
	@Before
	public void setup()
	{
		team = new Team();
	}
	@Test
	public void testGetAllTotalTeams()
	{
		TeamMatchVO[]  totalTeams =  team.getAllTotalTeams();
//		for (int i = 0 ;i < totalTeams.length; i++)
//		{
//			System.out.println(totalTeams[i]);
//		}
		assertEquals(totalTeams.length, 30);
	}

	@Test
	public void testGetAllAveTeams() 
	{
		TeamMatchVO[]  totalTeams =  team.getAllAveTeams();
//		for (int i = 0 ;i < totalTeams.length; i++)
//		{
//			System.out.println(totalTeams[i]);
//		}
		assertEquals(totalTeams.length, 30);
	}

	@Test
	public void testGetHotTeams()
	{
		TeamMatchVO[] hotTeams =  team.getHotTeams(TeamSortBy.assistNo);
//		for (int i = 0;i < hotTeams.length; i++)
//		{
//			System.out.println(hotTeams[i]);
//		}
		assertEquals(hotTeams.length, 5);
	}

	@Test
	public void testGetSortedAveTeams()
	{
		TeamMatchVO[] sortedAveTeams = team.getSortedAveTeams(TeamSortBy.assistEfficiency, SortType.DESEND);
	}

	@Test
	public void testGetSortedTotalTeams() {
		TeamMatchVO[] sortedTotalTeams = team.getSortedTotalTeams(TeamSortBy.blockNo, SortType.DESEND);
	}

	@Test
	public void testGetPlayers() {
		String[] players = team.getPlayers("LAL");
	}

	@Test
	public void testGetTotalTeam() {
		TeamMatchVO totalTeam = team.getTotalTeam("LAL");
	}

	@Test
	public void testGetAveTeam() {
		TeamMatchVO aveTeam = team.getAveTeam("LAL");
	}

	@Test
	public void testGetTeamData() {
		TeamPO po = team.getTeamData("LAL");
	}

	@Test
	public void testFuzzilyFindAve() {
		System.out.println("test: testFuzzilyFindAve");
		Iterator<TeamMatchVO> teamItr =	team.fuzzilyFindAve("M");
//		while (teamItr.hasNext())
//		{
//			TeamMatchVO teamvo = teamItr.next();
//			System.out.println(teamvo.toString());
//		}
//		System.out.println("test end !");
	}

	@Test
	public void testFuzzilyFindTotal() {
		team.fuzzilyFindTotal("b");
	}

}
