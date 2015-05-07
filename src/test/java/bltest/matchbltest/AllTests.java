package bltest.matchbltest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({  MatchPlayerMapTest_ave.class,
		MatchPlayerMapTest_total.class, MatchPlayerPOTest.class,
		MatchTest.class, PlayerTest.class, TeamBosTest.class, TeamTest.class })
public class AllTests {

}
