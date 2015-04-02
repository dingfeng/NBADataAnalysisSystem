package data.playerdata;

import po.PlayerPO;

public class test 
{
	
	public static void main(String[] args)
	{
		long total_old = 0;
		long total_new = 0;
		total_old = getAllPlayers_old();
		total_new = getAllPlayers_new();
		System.out.println("total_old : "+total_old);
		System.out.println("total_new : "+ total_new);
		
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
