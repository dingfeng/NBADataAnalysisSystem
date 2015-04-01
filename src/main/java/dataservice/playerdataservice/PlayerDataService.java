package dataservice.playerdataservice;

import java.util.HashMap;

import po.PlayerPO;

public interface PlayerDataService {
	//得到所有球员数据
	public PlayerPO[] getAllPlayerData();
}
