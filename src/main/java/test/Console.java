package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

import po.TeamPO;
import data.teamdata.TeamData;
import dataservice.teamdataservice.TeamDataService;
import bl.matchbl.Match;
import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;

public class Console {
 static TeamController tcon;
 static PlayerController pcon;
  static Match match;
 public void execute(PrintStream out, String[] args)
 {
	 try{
	 if (args[0].equals("--datasource"))
	 {
		 NBADataFactory factory = DataFactoryImp.instance();
		 DataFactoryImp.setDataSource(args[1]);
		 match = Match.instance();
		 tcon = new TeamController();
		 pcon = new PlayerController();
	 }
	 else if (args[0].equals("-player"))
	 {
		 match.update();
		 pcon.execute(out, args);
	 }
	 else if (args[0].equals("-team"))
	 {
		 match.update();
		 tcon.execute(out, args);
	 }
	 }catch (Exception e)
	 {
		e.printStackTrace();
	 }
 }
}
