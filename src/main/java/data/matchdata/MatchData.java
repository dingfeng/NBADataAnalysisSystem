package data.matchdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import dataservice.matchdataservice.MatchDataService;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;

public class MatchData implements MatchDataService 
{
	private static String filename = "data\\matches\\";
	
	//获得所有的比赛数据
	public ArrayList<MatchesPO> getAllMatchData() 
	{
		ArrayList<String> allFiles = getfile(filename);
		ArrayList<MatchesPO> allMatches = new ArrayList<MatchesPO>(allFiles.size());
		Iterator<String> fileItr = allFiles.iterator();
		while (fileItr.hasNext())
		{
			String fileName = fileItr.next();
			try
			{
				allMatches.add(getMatch(fileName));
			} catch (IOException | ParseException e)
			{
				e.printStackTrace();
			}
		}
		return allMatches;
	}
	
	//根据路径获得po
	@SuppressWarnings({ "resource", "unused" })
	private MatchesPO getMatch(String path) throws IOException, ParseException
	{
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(path));
		String one = br.readLine();
		String[] oneInfo = one.split(";");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
		Date date = dateFormat.parse(oneInfo[0]);
		String[] teamNames = oneInfo[1].split("-");
		String[] totalScoresStr = oneInfo[2].split("-");
		int[] totalScoresInt = new int[2];
		totalScoresInt[0] = Integer.parseInt(totalScoresStr[0]);
		totalScoresInt[1] = Integer.parseInt(totalScoresStr[1]);

		String two = br.readLine();
		String[] smallScores = two.split(";");
		int[][] smallScoresInt = new int[2][smallScores.length];
		
		for (int i = 0; i < smallScores.length; i++)
		{
	     String score = smallScores[i];
	     String[] scoreSplit = score.split("-");
	     smallScoresInt[0][i] = Integer.parseInt(scoreSplit[0]);
	     smallScoresInt[1][i] = Integer.parseInt(scoreSplit[1]);
 		}
		
		String[] names = new String[2];
		ArrayList<MatchPlayerPO> matchPlayer1 = new ArrayList<MatchPlayerPO>();
		ArrayList<MatchPlayerPO> matchPlayer2 = new ArrayList<MatchPlayerPO>();
		names[0] = br.readLine();
		for (int i = 0; i < 2; i++)
		{
		String strline = null;
		while ((strline = br.readLine()) != null) 
		{
			if (!strline.contains(";"))
			{
				break;
			}
			String[] playerInfo = strline.split(";");
			MatchPlayerPO matchPlayer = null;
	        if (playerInfo.length == 18)
	        {
	        	int totalTime = -1;
	        	try
	        	{
	        	String[] timeStr = playerInfo[2].split(":");
	        	totalTime = Integer.parseInt(timeStr[0]) * 60 + Integer.parseInt(timeStr[1]); 
	        	}catch (Exception  e){}
	            matchPlayer = 
	            new MatchPlayerPO(playerInfo[0], playerInfo[1],
								totalTime, Integer.parseInt(playerInfo[3]),
								Integer.parseInt(playerInfo[4]),
								Integer.parseInt(playerInfo[5]),
								Integer.parseInt(playerInfo[6]),
								Integer.parseInt(playerInfo[7]),
								Integer.parseInt(playerInfo[8]),
								Integer.parseInt(playerInfo[9]),
								Integer.parseInt(playerInfo[10]),
								Integer.parseInt(playerInfo[11]),
								Integer.parseInt(playerInfo[12]),
								Integer.parseInt(playerInfo[13]),
								Integer.parseInt(playerInfo[14]),
								Integer.parseInt(playerInfo[15]),
								Integer.parseInt(playerInfo[16])
								);
	        	
	        }
	        else 
	        {
	        	int totalTime = -1;
	        	try
	        	{
	        	String[] timeStr = playerInfo[2].split(":");
	        	totalTime = Integer.parseInt(timeStr[0]) * 60 + Integer.parseInt(timeStr[1]); 
	        	}
	        	catch (Exception e){ }
	        	 matchPlayer = 
	     	            new MatchPlayerPO(playerInfo[0],"?",
	     								totalTime, Integer.parseInt(playerInfo[2]),
	     								Integer.parseInt(playerInfo[3]),
	     								Integer.parseInt(playerInfo[4]),
	     								Integer.parseInt(playerInfo[5]),
	     								Integer.parseInt(playerInfo[6]),
	     								Integer.parseInt(playerInfo[7]),
	     								Integer.parseInt(playerInfo[8]),
	     								Integer.parseInt(playerInfo[9]),
	     								Integer.parseInt(playerInfo[10]),
	     								Integer.parseInt(playerInfo[11]),
	     								Integer.parseInt(playerInfo[12]),
	     								Integer.parseInt(playerInfo[13]),
	     								Integer.parseInt(playerInfo[14]),
	     								Integer.parseInt(playerInfo[15])
	     								);
	        }
	      
	        if (i == 0)
	        {
	        	matchPlayer1.add(matchPlayer);
	        }
	        else 
	        {
	        	matchPlayer2.add(matchPlayer);
	        }
		  }
		  if (strline != null)
	        {
	        	names[1] = strline;
	        }
		}
//		System.out.println(path);
//		System.out.println(names[0]+"  "+matchPlayer1.size());
//		System.out.println(names[1]+"  "+matchPlayer2.size());
		MatchTeamPO team1 = new MatchTeamPO(matchPlayer1,smallScoresInt[0],totalScoresInt[0],names[0]);
		MatchTeamPO team2 = new MatchTeamPO(matchPlayer2,smallScoresInt[1],totalScoresInt[1],names[1]);
		MatchesPO matchpo = new MatchesPO(team1,team2,date);
		return matchpo; 
	}
	
	/** 获取比赛文件 */
	private  ArrayList<String> getfile(String filePath) {
		ArrayList<String> filelist = new ArrayList<String>();
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files)
		{
		 filelist.add(file.getAbsolutePath());
		}

		return filelist;
	}

}
