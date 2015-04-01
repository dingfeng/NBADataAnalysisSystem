package data.playerdata;

import po.PlayerPO;

public class test 
{
	
	public static void main(String[] args)
	{
		long total_old = 0;
//		for (int i = 0; i < 100; i++)
//		{
//			total_old += getAllPlayers_old();
			System.out.println(getAllPlayers_old());
//		}
//		System.out.println("old "+ total_old/100);
//		System.out.println("new player data efficiency : " + getAllPlayers_new());
		
	}
	
	public static  long getAllPlayers_old()
	{
		
		long start_time = System.currentTimeMillis();
		PlayerData_old player_data = new PlayerData_old();
		player_data.getAllPlayerData();
		long end_time = System.currentTimeMillis();
		long margin_time = end_time - start_time;
		return margin_time;
		
	}
	
	public static long getAllPlayers_new()
	{
		long start_time = System.currentTimeMillis();
		PlayerData playerData = new PlayerData("G:/NBAData/players");
		playerData.getAllPlayerData();
		long end_time = System.currentTimeMillis();
		long margin_time = end_time - start_time;
		return margin_time;
	}
}
