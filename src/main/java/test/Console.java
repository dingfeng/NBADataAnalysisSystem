package test;

import java.io.PrintStream;

import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;

public class Console {
 TeamController tcon;
 PlayerController pcon;
 public void execute(PrintStream out, String[] args)
 {
	 if (args[0].equals("--datasource"))
	 {
		 NBADataFactory factory = DataFactoryImp.instance();
		 DataFactoryImp.setDataSource(args[1]);
		 tcon = new TeamController();
		 pcon = new PlayerController();
	 }
	 else if (args[0].equals("-player"))
	 {
		 pcon.execute(out, args);
	 }
	 else if (args[0].equals("-team"))
	 {
		 tcon.execute(out, args);
	 }
 }
}
