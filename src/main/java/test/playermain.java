package test;

import org.junit.Test;

import test.data.PlayerHighInfo;
import test.data.PlayerHotInfo;
import test.data.PlayerKingInfo;
import test.data.PlayerNormalInfo;
import vo.PlayerSortBy;
import vo.SortType;

public class playermain {
   public static void main(String[] args)
   {
//	   testGetPlayerHighInfos();
//	   testGetPlayerHotInfos();
//	   testGetPlayerSeasonKingInfo();		   
//	   testGetPlayerDailyKingInfo();
//	   testGetPlayerAveNormalInfos();
//	   testGetPlayerTotalNormalInfos();
   }
   
	
	public  static void testGetPlayerHighInfos() {
		PlayerCore playerCore = new PlayerCore();
		PlayerSortBy[] sortbys = new PlayerSortBy[]{PlayerSortBy.gmScEfficiency};
		SortType[] sortTypes = new SortType[]{SortType.DESEND};
		PlayerHighInfo[] result=playerCore.getPlayerHighInfos(sortbys, sortTypes, 4);
        for (PlayerHighInfo info : result)
        {
        	System.out.print(info);
        }
	}

	
	public static  void testGetPlayerHotInfos() {
		PlayerCore playerCore = new PlayerCore();
		PlayerHotInfo[] infos = playerCore.getPlayerHotInfos("score", 5);
		for (int i = 0 ;i < infos.length; i++)
		{
		 System.out.println("playername : "+infos[i].getName()+"  score : "+ infos[i].getUpgradeRate());
		}
	}

	
	public  static void testGetPlayerSeasonKingInfo() {
	  PlayerCore playerCore = new PlayerCore();
	  PlayerKingInfo[] infos = playerCore.getPlayerSeasonKingInfo("score", 5);
	  for (PlayerKingInfo info : infos)
	  {
		  System.out.println("playername : "+info.getName()+" score "+info.getValue());
	  }
	}

	
	public static void testGetPlayerDailyKingInfo() {
		 PlayerCore playerCore = new PlayerCore();
		 PlayerKingInfo[] infos =playerCore.getPlayerDailyKingInfo("score", 5);
		 for (PlayerKingInfo info : infos)
		 {
			 System.out.println("playername : "+info.getName()+" score : "+info.getValue());
		 }
	}

	
	public static void testGetPlayerAveNormalInfos() {
		 PlayerCore playerCore = new PlayerCore();
		 PlayerSortBy[] sortbys = new PlayerSortBy[]{PlayerSortBy.assistNo};
		 SortType[] sortTypes = new SortType[]{SortType.DESEND}; 
		 PlayerNormalInfo[] normalInfos = playerCore.getPlayerAveNormalInfos(sortbys, sortTypes, 5, "F", "West", 1);
		 for (PlayerNormalInfo info : normalInfos )
		 {
			 System.out.println("playername : "+info.getName()+" value : "+info.getAssist() );
		 }
	}

	
	public static void testGetPlayerTotalNormalInfos() {
		 PlayerCore playerCore = new PlayerCore();
		 PlayerSortBy[] sortbys = new PlayerSortBy[]{PlayerSortBy.assistNo};
		 SortType[] sortTypes = new SortType[]{SortType.DESEND}; 
		 PlayerNormalInfo[] infos =  playerCore.getPlayerTotalNormalInfos(sortbys, sortTypes, 5, "F", "West", 0);
		 for (PlayerNormalInfo info : infos )
		 {
			 System.out.println("playername : "+info.getName()+" value : "+info.getAssist() );
		 }
	}

}
