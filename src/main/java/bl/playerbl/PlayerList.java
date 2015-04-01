package bl.playerbl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import bl.matchbl.MatchPlayer;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import po.PlayerPO;
import data.playerdata.PlayerData;
import dataservice.playerdataservice.PlayerDataService;

/**
 * 保留所有player信息并负责更新player信息的类（单件）
 * 
 * @author cyn13
 *
 */
public class PlayerList {
	private static PlayerList instance;
	private HashMap<String, Player> players;// player列表
	private static PlayerDataService dataservice = new PlayerData();

	// 构造方法为私有
	private PlayerList() {
		if (players == null) {
			PlayerPO[] allPlayerPOs = dataservice.getAllPlayerData();
			for (PlayerPO p : allPlayerPOs) {
				// 在playerlist列表中添加一个新的player对象，key为球员的名字
				players.put(p.getName(), new Player(p));
			}
		}
	}

	/**
	 * 唯一得到PlayerList的方法
	 * 
	 * @return PlayerList对象
	 */
	public static PlayerList getPlayserListInstance() {
		if(instance == null){
			instance = new PlayerList();
		}
		return instance;
	}

	/**
	 * 得到match信息更新player数据
	 * 
	 * @return boolean
	 */
	public boolean updateMatchPlayerData(MatchesPO[] matches) {
		for (MatchesPO m : matches) {
			MatchTeamPO team1 = m.getTeam1();
			MatchTeamPO team2 = m.getTeam2();
			// 改成数组实现后需要修改
			ArrayList<MatchPlayerPO> playerteam1 = team1.getPlayers();
			ArrayList<MatchPlayerPO> playerteam2 = team2.getPlayers();
			Iterator<MatchPlayerPO> iteratorTeam1 = playerteam1.iterator();
			Iterator<MatchPlayerPO> iteratorTeam2 = playerteam2.iterator();

			while (iteratorTeam1.hasNext()) {
				MatchPlayerPO thisMatchPlayer = iteratorTeam1.next();
				String name = thisMatchPlayer.getName();
				Player playerToAddInfo = players.get(name);
				// 添加到相应的player的数据中
				boolean thisresult = playerToAddInfo.addMatchInformation(m, thisMatchPlayer);
				// 如果信息添加失败，则返回false，并停止此次信息的添加，待修改
				if (thisresult != true) {
					return false;
				}
			}
		}
		// 数据正常添加完毕，返回true
		return true;
	}

	/**
	 * 得到player的最新对象
	 * 
	 * @param name
	 * @return 相应名字的player的对象，如果没有此player则返回null
	 */
	public Player getPlayerData(String name) {
		Player result = players.get(name);
		return result;
	}
}
