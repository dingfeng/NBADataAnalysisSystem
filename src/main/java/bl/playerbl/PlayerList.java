package bl.playerbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import po.PlayerPO;
import data.playerdata.PlayerData;
import dataservice.playerdataservice.PlayerDataService;

/**
 * 保留所有player信息并负责更新player信息的类（单件）
 * @author cyn13
 *
 */
public class PlayerList {
	private static HashMap<String, PlayerPO> players;//player列表
	private PlayerDataService dataservice = new PlayerData();
	
	//构造方法为私有
	private PlayerList(){
		
	}
	
	/**
	 * 唯一得到PlayerList的方法
	 * @return PlayerList对象
	 */
	public PlayerList getPlayserListInstance(){
		if(players == null){
			players = dataservice.getAllPlayerData();
		}
		return this;
	}
	
	/**
	 * 得到match信息更新player数据
	 * @return boolean
	 */
	public boolean updateMatchPlayerData(MatchesPO[] matches){
		for(MatchesPO m: matches){
			MatchTeamPO team1 = m.getTeam1();
			MatchTeamPO team2 = m.getTeam2();
			ArrayList<MatchPlayerPO> playerteam1 = team1.getPlayers();
			ArrayList<MatchPlayerPO> playerteam2 = team2.getPlayers();
			Iterator<MatchPlayerPO> iteratorTeam1 = playerteam1.iterator();
			Iterator<MatchPlayerPO> iteratorTeam2 = playerteam2.iterator();
			while(iteratorTeam1.hasNext()){
				
			}
		}
		return true;
	}
}
