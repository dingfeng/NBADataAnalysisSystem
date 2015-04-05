package datatest.matchdatatest;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import po.MatchesPO;
import data.matchdata.MatchData;

public class MatchAllDataTest {

	@Test
	public void test() {
		MatchData data = new MatchData();
		File file = new File("G:/NBAData/matches");
		File[] files = file.listFiles();
		System.out.println("Hell0 ");
		for (File  f : files)
		{
			System.out.println("------------------file " + f.getName());
			System.out.println(data.getMatchPO(f));
		}
	}

}
