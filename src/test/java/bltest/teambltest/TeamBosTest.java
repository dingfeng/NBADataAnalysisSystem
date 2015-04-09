package bltest.teambltest;

import static org.junit.Assert.*;

import org.junit.Test;

import vo.TeamMatchVO;
import bl.teambl.Team;

public class TeamBosTest 
{

	@Test
	public void test()
	{
		Team team = new Team();
		TeamMatchVO vo = team.getAveTeam("BOS");
		System.out.println(vo.toString());
	}

}
