package bl.playerbl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;
import bl.matchbl.Match;
import bl.matchbl.PlayerQueue;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import po.PlayerPO;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.PlayerVO;
import vo.SortType;
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
	private TIntObjectMap<PlayerQueue> player_map;
	private TIntObjectMap<Player> players;// player列表
	private static PlayerDataService dataservice;

	// 构造方法为私有
	private PlayerList() {
		//得到数据层的服务
		NBADataFactory factory = DataFactoryImp.instance();
		dataservice = factory.getPlayerData();
		Match match = Match.instance();
		players = new TIntObjectHashMap<Player>();
		//得到playerQueue的map
		player_map = match.getPlayer_map();
		PlayerPO[] allPlayerPOs = dataservice.getAllPlayerData();
		for (PlayerPO p : allPlayerPOs) {
			// 在playerlist列表中添加一个新的player对象，key为球员的名字
			players.put(p.getName().hashCode(), new Player(p));
		}
	}

	/**
	 * 唯一得到PlayerList的方法
	 * 
	 * @return PlayerList对象
	 */
	public static PlayerList getPlayserListInstance() {
		if (instance == null) {
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
			MatchPlayerPO[] playerteam1 = team1.getPlayers();
			MatchPlayerPO[] playerteam2 = team2.getPlayers();

			Player playerToAddInfo;

			// 添加第一个队伍的数据
			for (MatchPlayerPO thisMatchPlayer : playerteam1) {
				String name = thisMatchPlayer.getName();
				playerToAddInfo = players.get(name.hashCode());
				// 添加到相应的player的数据中
				boolean thisresult = playerToAddInfo.addMatchInformation(m,
						thisMatchPlayer, team2, team1.getName());
				// 如果信息添加失败，则返回false，并停止此次信息的添加，待修改
				if (thisresult != true) {
					return false;
				}
			}
			// 添加第二个队伍的数据
			for (MatchPlayerPO thisMatchPlayer : playerteam2) {
				String name = thisMatchPlayer.getName();
				playerToAddInfo = players.get(name.hashCode());
				// 添加到相应的player的数据中
				boolean thisresult = playerToAddInfo.addMatchInformation(m,
						thisMatchPlayer, team1, team2.getName());
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
	 * 得到当日热点球员
	 * 
	 * @return
	 */
	public PlayerMatchVO[] getDayHotPlayers(PlayerSortBy sortby){
		return null;
	}

	/**
	 * 得到当前赛季热点球员
	 * 
	 * @return
	 */
	public PlayerMatchVO[] getSeasonHotPlayers(PlayerSortBy sortby) {
		PlayerQueue[] playerqueue = (PlayerQueue[])player_map.values();
		int length = playerqueue.length;
		PlayerMatchVO[] playermatches = new PlayerMatchVO[length];
		for(int i = 0; i < length; i ++){
			playermatches[i] = playerqueue[i].getPlayervo();
			Player player = players.get(playermatches[i].getName().hashCode());
			player.setSortBy(playermatches[i], sortby, SortType.DESEND);
		}
		Arrays.sort(playerqueue);
		PlayerMatchVO[] result = new PlayerMatchVO[5];
		for(int i = 0; i < 5; i ++){
			result[i] = playermatches[i];
		}
		return result;
	}

	/**
	 * 得到近五场比赛进步最快球员
	 * 
	 * @return
	 */
	public PlayerMatchVO[] getPromotePlayer(PlayerSortBy sortby) {
		return null;
	}

	/**
	 * 得到player的最新对象
	 * 
	 * @param name
	 * @return 相应名字的player的对象，如果没有此player则返回null
	 */
	public Player getPlayerData(String name) {
		Player result = players.get(name.hashCode());
		return result;
	}
}
