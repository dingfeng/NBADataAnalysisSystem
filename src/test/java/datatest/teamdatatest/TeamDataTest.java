package datatest.teamdatatest;

import static org.junit.Assert.*;

import org.junit.Test;

import po.TeamPO;
import data.teamdata.TeamData;

public class TeamDataTest {
     public static String filename = "G:/NBAData/teams";
	@Test
	public void test() 
	{
		TeamData  teamData = new TeamData(filename);
		TeamPO[] teamdatas = teamData.getAllTeamData();
		for (int i = 0;i < teamdatas.length; i++)
		{
			System.out.println(teamdatas[i]);
		}
	}

}
