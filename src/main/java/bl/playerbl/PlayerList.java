package bl.playerbl;

import java.util.ArrayList;
import java.util.Arrays;

import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;
import bl.matchbl.Match;
import bl.matchbl.MatchController;
import bl.matchbl.PlayerQueue;
import blservice.matchblservice.Matchblservice;
import gnu.trove.iterator.TIntObjectIterator;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;
import po.PlayerPO;
import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.PlayerVO;
import vo.SortType;
import dataservice.playerdataservice.PlayerDataService;

/**
 * 保留所有player信息并负责更新player信息的类（单件）
 * 
 * @author cyn13
 *
 */
public class PlayerList {
	private Matchblservice matchservice;
	private static PlayerList instance;
	//player比赛信息的列表
	private TIntObjectMap<PlayerQueue> player_map;
	private TIntObjectMap<PlayerPO> players;// player基本信息列表
	private static PlayerDataService dataservice;

	// 构造方法为私有
	private PlayerList() {
		matchservice = new MatchController();
		//得到数据层的服务
		NBADataFactory factory = DataFactoryImp.instance();
		dataservice = factory.getPlayerData();
		Match match = Match.instance();
		players = new TIntObjectHashMap<PlayerPO>();
		//得到playerQueue的map
		player_map = match.getPlayer_map();
		PlayerPO[] allPlayerPOs = dataservice.getAllPlayerData();
		for (PlayerPO p : allPlayerPOs) {
			// 在playerlist列表中添加一个新的player对象，key为球员的名字
			players.put(p.getName().hashCode(), p);
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
	
	
	/*
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
				//playerToAddInfo = players.get(name.hashCode());
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
				//playerToAddInfo = players.get(name.hashCode());
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
	*/
	
	
	public PlayerPO findPlayerInfo(String name){
		PlayerPO result = players.get(name.hashCode());
		return result;
	}

	/**
	 * 得到当日num个热点球员
	 * 
	 * @return
	 */
	public PlayerMatchVO[] getDayHotPlayers(PlayerSortBy sortby, int num){
		MatchesPO[] todayMatches = matchservice.getTodayMatches();
		int players = 0;
		for(MatchesPO m : todayMatches){
			MatchTeamPO team1 = m.getTeam1();
			MatchTeamPO team2 = m.getTeam2();
			MatchPlayerPO[] team1players = team1.getPlayers();
			MatchPlayerPO[] team2players = team2.getPlayers();
			players += (team1players.length + team2players.length);
		}
		return null;
	}

	/**
	 * 得到当前赛季num个热点球员
	 * 
	 * @return
	 */
	public PlayerMatchVO[] getSeasonHotPlayers(PlayerSortBy sortby, int num) {
		PlayerQueue[] playerqueue = (PlayerQueue[])player_map.values();
		int length = playerqueue.length;
		PlayerMatchVO[] playermatches = new PlayerMatchVO[length];
		for(int i = 0; i < length; i ++){
			playermatches[i] = playerqueue[i].getAvePlayer();
			setSortBy(playermatches[i], sortby, SortType.DESEND);
		}
		Arrays.sort(playerqueue);
		PlayerMatchVO[] result = new PlayerMatchVO[5];
		for(int i = 0; i < num; i ++){
			result[i] = playermatches[i];
		}
		return result;
	}

	/**
	 * 得到近num场比赛进步最快球员
	 * 
	 * @return
	 */
	public PlayerMatchVO[] getPromotePlayer(PlayerSortBy sortby, int num) {
		TIntObjectIterator<PlayerQueue> it = player_map.iterator();
		ArrayList<PlayerMatchVO> playerforsort = new ArrayList<PlayerMatchVO>(500);
		double sortDouble = 0;
		while(it.hasNext()){
			it.advance();
			PlayerQueue playerqueue = it.value();
			MatchPlayerPO[] recentMatches = playerqueue.getRecentPlayerInfo(5);
			if(recentMatches.length == 5){
				for(MatchPlayerPO m : recentMatches){
					switch(sortby){
					case points:
						sortDouble = m.getPoints();
						break;
					case rebs:
						sortDouble = m.getRebs();
						break;
					case assistNo:
						sortDouble = m.getHelp();
						break;
					}
					PlayerMatchVO playermatch = playerqueue.getAvePlayer();
					SortBy sortBy = new SortBy(sortDouble, SortType.DESEND);
					playermatch.setSortBy(sortBy, SortType.DESEND);
				}
			}
		}
		return null;
	}
	
	/**
	 * 返回按照依据排序的结果数组
	 * @param playerSortBy
	 * @param sortType
	 * @param num
	 * @return
	 */
	public PlayerMatchVO[] sortPlayers(PlayerSortBy playerSortBy,
			SortType sortType, int num){
		return null;
	}
	
	/**
	 * 返回num个筛选条件
	 * @param playerPosition
	 * @param playerArea
	 * @param sortBy
	 * @param num
	 * @return
	 */
	public PlayerMatchVO[] sreenPlayers(String playerPosition, Area playerArea,
			PlayerSortBy sortBy, int num){
		return null;
	}

	/**
	 * 得到player的最新对象
	 * 
	 * @param name
	 * @return 相应名字的player的对象，如果没有此player则返回null
	 */
	public PlayerPO getPlayerData(String name) {
		PlayerPO result = players.get(name.hashCode());
		return result;
	}
	
	/**
	 * 用于排序
	 * @param player
	 * @param PlayerSortBy
	 * @param sortType
	 */
	private void setSortBy(PlayerMatchVO player, PlayerSortBy PlayerSortBy,
			SortType sortType) {
		String strSort = null;
		double doubleSort = -1;
		switch (PlayerSortBy) {
		case name:
			strSort = player.getName();
			break;
		case team:
			strSort = player.getTeam();
			break;
		case matchNo:
			doubleSort = player.getMatchNo();
			break;
		case firstServiceNo:
			doubleSort = player.getFirstServiceNo();
			break;
		case rebs:
			doubleSort = player.getRebs();
			break;
		case assistNo:
			doubleSort = player.getAssistNo();
			break;
		case time:
			doubleSort = player.getTime();
			break;
		case hitRate:
			doubleSort = player.getHitRate();
			break;
		case threeHitRate:
			doubleSort = player.getThreeHitRate();
			break;
		case penaltyHitRate:
			doubleSort = player.getPenaltyHitRate();
			break;
		case offendNo:
			doubleSort = player.getOffendNo();
			break;
		case defenceNo:
			doubleSort = player.getDefenceNo();
			break;
		case stealsNo:
			doubleSort = player.getStealsNo();
			break;
		case blockNo:
			doubleSort = player.getBlockNo();
			break;
		case mistakesNo:
			doubleSort = player.getMistakesNo();
			break;
		case foulsNo:
			doubleSort = player.getFoulsNo();
			break;
		case points:
			doubleSort = player.getPoints();
			break;
		case efficiency:
			doubleSort = player.getEfficiency();
			break;
		case gmScEfficiency:
			doubleSort = player.getGmScEfficiency();
			break;
		case trueHitRate:
			doubleSort = player.getTrueHitRate();
			break;
		case hitEfficiency:
			doubleSort = player.getHitEfficiency();
			break;
		case rebEfficiency:
			doubleSort = player.getRebEfficiency();
			break;
		case offenseRebsEfficiency:
			doubleSort = player.getOffenseRebsEfficiency();
			break;
		case defenceRebsEfficiency:
			doubleSort = player.getDefenceRebsEfficiency();
			break;
		case assistEfficiency:
			doubleSort = player.getAssistEfficiency();
			break;
		case stealsEfficiency:
			doubleSort = player.getStealsEfficiency();
			break;
		case blockEfficiency:
			doubleSort = player.getBlockEfficiency();
			break;
		case mistakeEfficiency:
			doubleSort = player.getMistakeEfficiency();
			break;
		case useEfficiency:
			doubleSort = player.getUseEfficiency();
			break;

		case rebound:// 篮板
			doubleSort = player.getRebs();
			break;
		case assist:// 助攻
			doubleSort = player.getAssistNo();
			break;
		case scoring_rebound_assist:// 得分/篮板/助攻（加权比1：1：1）
			doubleSort = player.getScoring_rebound_assist();
			break;
		case block:// 盖帽
			doubleSort = player.getBlockNo();
			break;
		case steal:// 抢断
			doubleSort = player.getStealsNo();
			break;
		case foul:// 犯规
			doubleSort = player.getFoulsNo();
			break;
		case mistake:// 失误
			doubleSort = player.getMistakesNo();
			break;
		case minute:// 分钟
			doubleSort = player.getMinute();
			break;
		case shot:// 投篮
			doubleSort = player.getHandNo();
			break;
		case three_points:// 三分
			doubleSort = player.getThree_points();
			break;
		case freeThrow:// 罚球
			doubleSort = player.getPenaltyHandNo();
			break;
		case twoPair:// 两双
			doubleSort = player.getTwoPair();
			break;
		}
		if (doubleSort != -1) {
			SortBy sortby = new SortBy(doubleSort, sortType);
			//将playerMatchVO对象中的sortby改为最新的sortby
			player.setSortBy(sortby, sortType);
		} else {
			SortBy sortby = new SortBy(strSort, sortType);
			player.setSortBy(sortby, sortType);
		}
	}
}
