package test;

import static org.junit.Assert.*;

import java.io.PrintStream;

import org.junit.Test;

public class TeamControllerTest {

	@Test
	public void testExecute() {
		TeamController teamController = new TeamController();
		String[] commands = new String[]{"-team"};
		PrintStream print = System.out;
		teamController.execute(print, commands);
		print.flush();
	}

}
