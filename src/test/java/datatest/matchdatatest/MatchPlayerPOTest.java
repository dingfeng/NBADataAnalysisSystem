package datatest.matchdatatest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import po.MatchPlayerPO;
import data.matchdata.MatchData;

public class MatchPlayerPOTest {

	@Test
	public void test() 
	{
		MatchData match_data = new MatchData();
		MatchPlayerPO matchPlayerpo = match_data.dealWithLine("Anthony Tolliver;F;37:01;4;11;3;10;0;0;0;3;3;1;0;0;0;1;11;");
		System.out.println(matchPlayerpo.toString());
	}
    
}
