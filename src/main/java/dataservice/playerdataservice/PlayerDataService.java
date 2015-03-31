package dataservice.playerdataservice;

import java.util.HashMap;

import po.PlayerPO;

public interface PlayerDataService {
	//得到所有球员数据
	public HashMap<String,PlayerPO> getAllPlayerData();
	//得到单个球员数据
	public PlayerPO getOnePlayerData(String playerName);	
}
