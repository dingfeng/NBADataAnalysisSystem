package test;

import java.util.ArrayList;

import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import po.MatchPlayerPO;
import po.MatchesPO;
import po.PlayerPO;
import po.TeamPO;
import DataFactory.DataFactoryImp;
import DataFactoryService.NBADataFactory;
import bl.matchbl.Match;
import bl.matchbl.PlayerQueue;
import bl.teambl.SortTool;
import dataservice.playerdataservice.PlayerDataService;
import test.data.PlayerHighInfo;
import test.data.PlayerHotInfo;
import test.data.PlayerKingInfo;
import test.data.PlayerNormalInfo;
import tool.MinBinaryHeap;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;
import vo.SortType;

public class PlayerCore implements PlayerCoreService
{
	private	TIntObjectMap<PlayerQueue> player_map;
	private PlayerQueue[] playerQueues;
	private PlayerDataService playerData;
	private TIntObjectMap<PlayerPO>  player_base_map;
	private PlayerPO[] allPlayerpos;
	private Match  match ;
	public PlayerCore()
	{
	  match = Match.instance();
	  player_map  = match.getPlayer_map();
	  NBADataFactory factory = DataFactoryImp.instance();
	  playerData = factory.getPlayerData();
	  allPlayerpos = playerData.getAllPlayerData();
	  player_base_map = new TIntObjectHashMap<PlayerPO>();
	  for ( PlayerPO p : allPlayerpos)
	  {
		  player_base_map.put(p.getName().hashCode(), p);
	  }
	  playerQueues = new PlayerQueue[player_map.size()];
	  player_map.values(playerQueues);
	}
	@Override
	public PlayerHighInfo[] getPlayerHighInfos(PlayerSortBy[] sortbys,
			SortType[] sortTypes, int n) {
		MinBinaryHeap<PlayerMatchVO> heap = new MinBinaryHeap<PlayerMatchVO>(800);
		PlayerMatchVO playerMatchvo = null;
		for (int i = 0; i < playerQueues.length; i++)
		{
			playerMatchvo = playerQueues[i].getAvePlayer();
			setSortTools(playerMatchvo, sortbys, sortTypes);
			heap.insert(playerMatchvo);
		}
		int num = playerQueues.length;
		if (n > num)
		{
			n = num;
		}
		PlayerHighInfo[] playerHighInfos = new PlayerHighInfo[n];
		PlayerMatchVO playerMatchVO = null;
		PlayerHighInfo highinfo = null;
	    String teamName = null;
	    String playerName = null;
	    PlayerPO playerpo;
	    TeamPO teampo = null;
	    String position = null;
		for (int i = 0; i < n; i++)
		{
			playerMatchVO =  heap.deleteMin();
			System.out.println("hot : "+playerMatchVO.getHotData());
			highinfo = new PlayerHighInfo();
			highinfo.setAssistEfficient(playerMatchVO.getAssistEfficiency());
			highinfo.setBlockShotEfficient(playerMatchVO.getBlockEfficiency());
			highinfo.setDefendReboundEfficient(playerMatchVO.getDefenceRebsEfficiency());
			highinfo.setFaultEfficient(playerMatchVO.getMistakeEfficiency());
			highinfo.setFrequency(playerMatchVO.getUseEfficiency());
			highinfo.setGmSc(playerMatchVO.getGmScEfficiency());
			playerName = playerMatchVO.getName();
			highinfo.setName(playerName);
			highinfo.setOffendReboundEfficient(playerMatchVO.getOffenseRebsEfficiency());
			highinfo.setRealShot(playerMatchVO.getTrueHitRate());
			highinfo.setReboundEfficient(playerMatchVO.getRebEfficiency());
			highinfo.setShotEfficient(playerMatchVO.getHitEfficiency());
			highinfo.setStealEfficient(playerMatchVO.getStealsEfficiency());
			
			teamName =  playerMatchVO.getTeam();
			highinfo.setTeamName(teamName);
			
			teampo = TeamCore.findTeam(teamName);
			
			highinfo.setLeague(teampo.getMatchArea().equals("W") ? "West":"East");
			playerpo = player_base_map.get(playerName.hashCode());
			if (playerpo != null)
			{
				position = playerpo.getPosition();
			}
			else 
			{
				position = playerMatchVO.getLocation();
			}
			if (position == null || position.contains("-"))
			{
				position = "All";
			}
			highinfo.setPosition(position);
			playerHighInfos[i] = highinfo;
		}
		
		return playerHighInfos; 
	}
	@Override
	public PlayerHotInfo[] getPlayerHotInfos(String field, int n) {
		PlayerSortBy sortby = getHotPlayerSortBy(field);
		MinBinaryHeap<PlayerMatchVO> heap = new MinBinaryHeap<PlayerMatchVO>(800);
		PlayerMatchVO playerMatchvo = null;
		PlayerSortBy[] sortbys = new PlayerSortBy[1];
		sortbys[0] = sortby;
		SortType[]  sortType = new SortType[1];
		sortType[0] = SortType.DESEND;
		for (int i = 0; i < playerQueues.length; i++)
		{
			playerMatchvo = playerQueues[i].getAvePlayer();
			setSortTools(playerMatchvo, sortbys, sortType);
			heap.insert(playerMatchvo);
		}
		int num = playerQueues.length;
		if (n > num)
		{
			n = num;
		}
		PlayerHotInfo[] infos = new PlayerHotInfo[n];
        PlayerHotInfo hotinfo = null;
        PlayerPO playerpo = null;
        String playername = null;
        String position = null;
		for (int i = 0; i < n; i++)
		{
			playerMatchvo = heap.deleteMin();
			hotinfo = new PlayerHotInfo();
			hotinfo.setField(field);
			playername = playerMatchvo.getName();
			hotinfo.setName(playername);
			hotinfo.setTeamName(playerMatchvo.getTeam());
			
			playerpo = player_base_map.get(playername.hashCode());
			if (playerpo != null)
			{
				position = playerpo.getPosition();
			}
			else 
			{
				position = playerMatchvo.getLocation();
			}
			if (position == null || position.contains("-"))
			{
				position = "All";
			}
			hotinfo.setPosition(position);
			hotinfo.setUpgradeRate(playerMatchvo.getHotData());
			hotinfo.setValue(getHotValue(playerMatchvo,field));
			infos[i] = hotinfo;
		}
		return infos;
	}
	
	@Override
	public PlayerKingInfo[] getPlayerSeasonKingInfo(String field, int n) {
		PlayerSortBy sortby = getKingPlayerSortBy(field);
		MinBinaryHeap<PlayerMatchVO> heap = new MinBinaryHeap<PlayerMatchVO>(800);
		PlayerMatchVO playerMatchvo = null;
		PlayerSortBy[] sortbys = new PlayerSortBy[1];
		sortbys[0] = sortby;
		SortType[]  sortType = new SortType[1];
		sortType[0] = SortType.DESEND;
		for (int i = 0; i < playerQueues.length; i++)
		{
			playerMatchvo = playerQueues[i].getAvePlayer();
			setSortTools(playerMatchvo, sortbys, sortType);
			heap.insert(playerMatchvo);
		}
		int num = playerQueues.length;
		if (n > num)
		{
			n = num;
		}
		PlayerKingInfo[] infos = new PlayerKingInfo[n];
		PlayerKingInfo playerInfo = null;
		PlayerPO playerpo = null;
		String playername = null;
		String position = null;
		for (int i = 0; i < n; i++)
		{
			playerMatchvo = heap.deleteMin();
			playername =  playerMatchvo.getName();
			playerpo = player_base_map.get(playername.hashCode());
			playerInfo = new PlayerKingInfo();
			playerInfo.setField(field);
			playerInfo.setName(playername);
			playerpo = player_base_map.get(playername.hashCode());
			if (playerpo != null)
			{
				position = playerpo.getPosition();
			}
			else 
			{
				position = playerMatchvo.getLocation();
			}
			if (position == null || position.contains("-"))
			{
				position = "All";
			}
			playerInfo.setPosition(position);
			playerInfo.setTeamName(playerMatchvo.getTeam());
			playerInfo.setValue(playerMatchvo.getHotData());
			infos[i] = playerInfo;
		}
		return infos;
	}
	@Override
	public PlayerKingInfo[] getPlayerDailyKingInfo(String field, int n) {
		MatchesPO[] todayMatches = match.getTodayMatches();
		PlayerSortBy sortby = getKingPlayerSortBy(field);
		MinBinaryHeap<MatchPlayerPO> heap = new MinBinaryHeap<MatchPlayerPO>(300);
		MatchPlayerPO matchPlayerpo = null;
		MatchPlayerPO[] teamPlayers  = null;
		int i = 0;
		for (MatchesPO mpo : todayMatches)
		{
			teamPlayers = mpo.getTeam1().getPlayers();
			for (MatchPlayerPO ppo : teamPlayers)
			{
				setDailyKingSort(ppo,sortby);
				heap.insert(ppo);
				++i;
			}
			teamPlayers = mpo.getTeam2().getPlayers();
			for (MatchPlayerPO ppo : teamPlayers)
			{
				setDailyKingSort(ppo,sortby);
				heap.insert(ppo);
				++i;
			}
		}
		int num = i;
		if (n > num)
		{
			n = num;
		}
		PlayerKingInfo[] playerInfos = new PlayerKingInfo[n];
		PlayerKingInfo  temp = null;
		PlayerPO playerpo = null;
		String playername = null;
		String position = null;
		for (int j = 0; j < n; j++)
		{
			matchPlayerpo = heap.deleteMin();
			temp = new PlayerKingInfo();
			playername =  matchPlayerpo.getName();
			
			temp.setField(field);
			temp.setName(playername);
			
			playerpo = player_base_map.get(playername.hashCode());
			if (playerpo != null)
			{
				position = playerpo.getPosition();
			}
			else 
			{
				position = matchPlayerpo.getLocation();
			}
			if (position == null || position.contains("-"))
			{
				position = "All";
			}
			
			temp.setPosition(position);
			temp.setTeamName(matchPlayerpo.getTeamnameAbridge());
			temp.setValue(matchPlayerpo.getHotData());
			playerInfos[j] = temp;
		}
		return playerInfos;
	}
	
	
	
	@Override
	public PlayerNormalInfo[] getPlayerAveNormalInfos(PlayerSortBy[] sortbys,
			SortType[] sortTypes, int n, String position, String league, int age) {
		MinBinaryHeap<PlayerMatchVO> heap = new MinBinaryHeap<PlayerMatchVO>(500);
		PlayerMatchVO playerMatchvo = null;
		String position1 = null;
		String playername = null;
		String teamName = null;
		TeamPO teampo = null;
		PlayerPO playerpo = null;
		
		boolean position_judge = false;
		boolean league_judge = false;
		boolean age_judge = false;
		
		int count = 0;
		for (int i = 0; i < playerQueues.length; i++)
		{
		  playerMatchvo = playerQueues[i].getAvePlayer();
		  playername = playerMatchvo.getName();
		  playerpo = player_base_map.get(playername.hashCode());
		  teamName = playerMatchvo.getTeam();
		  position_judge = false;
		  league_judge = false;
		  age_judge =false;
		  if (position.equals("All"))
		  {
			  position_judge = true;
		  }
		  else
		  {
			  if (playerpo == null)
			  {
				  String postion1 = playerMatchvo.getLocation();
				  if (position.equals(position1))
				  {
					  position_judge = true;
				  }
			  }
			  else 
			  {
				  if (playerpo.getPosition().contains(position))
				  {
					  position_judge = true;
				  }
			  }
		  }
		  
		  if (league.equals("All"))
		  {
			  league_judge = true;
		  }
		  else
		  {
			  teampo = TeamCore.findTeam(teamName);
			  if (league.contains(teampo.getMatchArea()))
			  {
				  league_judge = true;
			  }
		  }
		  
		  if (age == -1)
		  {
			  age_judge = true;
		  }
		  else 
		  {
		 if (playerpo != null)
		 {
		  int age_temp = playerpo.getAge();
    	  if (age == 0)
	      {
			  if (age_temp <= 22)
			  {
				  age_judge = true;
			  }
		  }
		  else if (age == 1)
		  {
			  if (age_temp > 22 && age_temp <= 25)
			  {
				  age_judge = true;
			  }
		  }
		  else if (age == 2)
		  {
			  if (age_temp > 25 && age_temp <= 30)
			  {
				  age_judge = true;
			  }
		  }
		  else if (age == 3)
		  {
			  if (age_temp > 30)
			  {
				  age_judge = true;
			  }
		  }
		 }
		 }
		  if (position_judge && league_judge&& age_judge)
		  {
			  setSortTools(playerMatchvo,sortbys,sortTypes);
			  heap.insert(playerMatchvo);
			  ++count;
		  }
		}
		
		if (n > count)
		{
			n = count;
		}
		
		PlayerNormalInfo[] infos = new PlayerNormalInfo[n];
		PlayerNormalInfo info = null;
		
		for (int i = 0 ;i < n; i++)
		{
			info = new PlayerNormalInfo();
			playerMatchvo = heap.deleteMin();
			playername = playerMatchvo.getName();
			info.setAge(player_base_map.get(playername.hashCode()).getAge());
			info.setAssist(playerMatchvo.getAssistNo());
			info.setBlockShot(playerMatchvo.getBlockNo());
			info.setDefend(playerMatchvo.getDefenceNo());
			info.setEfficiency(playerMatchvo.getEfficiency());
			info.setFault(playerMatchvo.getMistakesNo());
			info.setFoul(playerMatchvo.getFoulsNo());
			info.setMinute(playerMatchvo.getMinute());
			info.setName(playername);
			info.setNumOfGame(playerMatchvo.getMatchNo());
			info.setOffend(playerMatchvo.getOffendNo());
			info.setPenalty(playerMatchvo.getPenaltyHitRate());
			info.setPoint(playerMatchvo.getPoints());
			info.setRebound(playerMatchvo.getRebs());
			info.setShot(playerMatchvo.getHitRate());
			info.setStart((int) playerMatchvo.getFirstServiceNo());
			info.setSteal(playerMatchvo.getStealsNo());
			info.setTeamName(teamName);
			info.setThree(playerMatchvo.getThreeHitRate());
			infos[i] = info;
		}
		
		return infos;
	}
	@Override
	public PlayerNormalInfo[] getPlayerTotalNormalInfos(PlayerSortBy[] sortbys,
			SortType[] sortTypes, int n, String position, String league, int age) {
		
		MinBinaryHeap<PlayerMatchVO> heap = new MinBinaryHeap<PlayerMatchVO>(500);
		PlayerMatchVO playerMatchvo = null;
		String position1 = null;
		String playername = null;
		String teamName = null;
		TeamPO teampo = null;
		PlayerPO playerpo = null;
		
		boolean position_judge = false;
		boolean league_judge = false;
		boolean age_judge = false;
		
		int count = 0;
		for (int i = 0; i < playerQueues.length; i++)
		{
		  playerMatchvo = playerQueues[i].getTotalPlayer();
		  playername = playerMatchvo.getName();
		  playerpo = player_base_map.get(playername.hashCode());
		  teamName = playerMatchvo.getTeam();
		  position_judge = false;
		  league_judge = false;
		  age_judge =false;
		  if (position.equals("All"))
		  {
			  position_judge = true;
		  }
		  else
		  {
			  if (playerpo == null)
			  {
				  String postion1 = playerMatchvo.getLocation();
				  if (position.equals(position1))
				  {
					  position_judge = true;
				  }
			  }
			  else 
			  {
				  if (playerpo.getPosition().contains(position))
				  {
					  position_judge = true;
				  }
			  }
		  }
		  
		  if (league.equals("All"))
		  {
			  league_judge = true;
		  }
		  else
		  {
			  teampo = TeamCore.findTeam(teamName);
			  if (league.contains(teampo.getMatchArea()))
			  {
				  league_judge = true;
			  }
		  }
		  
		  if (age == -1)
		  {
			  age_judge = true;
		  }
		  else 
		  {
		 if (playerpo != null)
		 {
		  int age_temp = playerpo.getAge();
    	  if (age == 0)
	      {
			  if (age_temp <= 22)
			  {
				  age_judge = true;
			  }
		  }
		  else if (age == 1)
		  {
			  if (age_temp > 22 && age_temp <= 25)
			  {
				  age_judge = true;
			  }
		  }
		  else if (age == 2)
		  {
			  if (age_temp > 25 && age_temp <= 30)
			  {
				  age_judge = true;
			  }
		  }
		  else if (age == 3)
		  {
			  if (age_temp > 30)
			  {
				  age_judge = true;
			  }
		  }
		 }
		 }
		  if (position_judge && league_judge&& age_judge)
		  {
			  setSortTools(playerMatchvo,sortbys,sortTypes);
			  heap.insert(playerMatchvo);
			  ++count;
		  }
		}
		
		if (n > count)
		{
			n = count;
		}
		
		PlayerNormalInfo[] infos = new PlayerNormalInfo[n];
		PlayerNormalInfo info = null;
		
		for (int i = 0 ;i < n; i++)
		{
			info = new PlayerNormalInfo();
			playerMatchvo = heap.deleteMin();
			playername = playerMatchvo.getName();
			info.setAge(player_base_map.get(playername.hashCode()).getAge());
			info.setAssist(playerMatchvo.getAssistNo());
			info.setBlockShot(playerMatchvo.getBlockNo());
			info.setDefend(playerMatchvo.getDefenceNo());
			info.setEfficiency(playerMatchvo.getEfficiency());
			info.setFault(playerMatchvo.getMistakesNo());
			info.setFoul(playerMatchvo.getFoulsNo());
			info.setMinute(playerMatchvo.getMinute());
			info.setName(playername);
			info.setNumOfGame(playerMatchvo.getMatchNo());
			info.setOffend(playerMatchvo.getOffendNo());
			info.setPenalty(playerMatchvo.getPenaltyHitRate());
			info.setPoint(playerMatchvo.getPoints());
			info.setRebound(playerMatchvo.getRebs());
			info.setShot(playerMatchvo.getHitRate());
			info.setStart((int) playerMatchvo.getFirstServiceNo());
			info.setSteal(playerMatchvo.getStealsNo());
			info.setTeamName(teamName);
			info.setThree(playerMatchvo.getThreeHitRate());
			infos[i] = info;
		}
		
		return infos;
		
	}
	
	private void setSortTools(PlayerMatchVO playervo, PlayerSortBy[] playerSortBys, SortType[] sortTypes)
	{
		double[] data = new double[playerSortBys.length];
		for (int i =0 ;i < data.length; i++)
		{
		 switch (playerSortBys[i])
		 {
		 case   firstServiceNo:
				data[i] =  playervo.getFirstServiceNo();
				break;
			case    rebs:
				data[i] = playervo.getRebs();
				break;
			case	assistNo:
				data[i] = playervo.getAssistNo();
				break;
			case	time:
				data[i] = playervo.getTime();
				break;
			case	hitRate:
				data[i] = playervo.getHitRate();
				break;
			case	threeHitRate:
				data[i] = playervo.getThreeHitRate();
				break;
			case	penaltyHitRate:
				data[i] = playervo.getPenaltyHitRate();
				break;
			case	offendNo:
				data[i] = playervo.getOffendNo();
				break;
			case	defenceNo:
				data[i] = playervo.getDefenceNo();
				break;
			case	stealsNo:
				data[i] = playervo.getStealsNo();
				break;
			case	blockNo:
				data[i] = playervo.getBlockNo();
				break;
			case	mistakesNo:
				data[i] = playervo.getMistakesNo();
				break;
			case	foulsNo:
				data[i] = playervo.getFoulsNo();
				break;
			case	points:
				data[i] = playervo.getPoints();
				break;
			case	efficiency:
				data[i] = playervo.getEfficiency();
				break;
			case   gmScEfficiency:
				data[i] = playervo.getGmScEfficiency();
				break;
			case	trueHitRate:
				data[i] = playervo.getTrueHitRate();
				break;
			case	hitEfficiency:
				data[i] = playervo.getHitEfficiency();
				break;
			case	rebEfficiency:
				data[i] = playervo.getRebEfficiency();
				break;
			case	offenseRebsEfficiency:
				data[i] = playervo.getOffenseRebsEfficiency();
				break;
			case    defenceRebsEfficiency:
				data[i] = playervo.getDefenceRebsEfficiency();
				break;
			case	 assistEfficiency:
				data[i] =  playervo.getAssistEfficiency();
				break;
			case	stealsEfficiency:
				data[i] = playervo.getStealsEfficiency();
				break;
			case	blockEfficiency:
				data[i] =  playervo.getBlockEfficiency();
				break;
			case	mistakeEfficiency:
				data[i] = playervo.getMistakeEfficiency();
				break;
			case	useEfficiency:
				data[i] = playervo.getUseEfficiency();
				break;
			case	points_uprate:
				data[i] = playervo.getPoints_uprate();
				break; //得分提升率
			case	rebs_uprate :
				data[i] = playervo.getRebs_uprate();
				break;  //篮板提升率
			case	help_uprate :
				data[i] =  playervo.getHelp_uprate();
				break;//助攻提升率
			
			case    rebound:
				data[i] = playervo.getRebs();
				break;//篮板
			case	assist:
				data[i] = playervo.getAssistNo();
				break;//助攻
			case	scoring_rebound_assist:
				data[i] = playervo.getScoring_rebound_assist();
				break;//得分/篮板/助攻（加权比1：1：1）
			case	block:
				data[i] = playervo.getBlockNo();
				break;//盖帽
			case	steal:
				data[i] = playervo.getStealsNo();
				break;//抢断
			case	foul:
				data[i] = playervo.getFoulsNo();
				break;//犯规
			case	mistake:
				data[i] = playervo.getMistakesNo();
				break;//失误
			case	minute:
				data[i] = playervo.getMinute();
				break;//分钟
			case	shot:
				data[i] = playervo.getHandNo();
				break;//投篮
			case	three_points:
				data[i] = playervo.getThree_points();
				break;//三分
			case	freeThrow:
				data[i] = playervo.getPenaltyHandNo();
				break;//罚球
			case	twoPair:
				data[i] = playervo.getTwoPair();
				break;//两双
		 }
		}
		playervo.setSortTool(new SortTool(data, sortTypes));
	}
	
	private PlayerSortBy getHotPlayerSortBy(String field)
	{
		PlayerSortBy  sortBy = null;
		switch (field)
		{
		case "score":
			sortBy = PlayerSortBy.points_uprate;
			break;
		case "rebound":
			sortBy = PlayerSortBy.rebs_uprate;
			break;
		case "assist":
			sortBy = PlayerSortBy.help_uprate;
			break;
		}
		return sortBy;
	}
	
	private double getHotValue(PlayerMatchVO playerMatchVo, String field)
	{
		double data = -1;
		switch(field)
		{
		case "score":
			data = playerMatchVo.getPoints();
			break;
		case "rebound":
			data = playerMatchVo.getRebs();
			break;
		case "assist":
			data = playerMatchVo.getAssistNo();
			break;
		}
		return data;
	}
	
	private PlayerSortBy getKingPlayerSortBy(String field)
	{
		PlayerSortBy  sortBy = null;
		switch (field)
		{
		case "score":
			sortBy = PlayerSortBy.points;
			break;
		case "rebound":
			sortBy = PlayerSortBy.rebs;
			break;
		case "assist":
			sortBy = PlayerSortBy.assist;
			break;
		}
		return sortBy;
	}
	
	private void setDailyKingSort(MatchPlayerPO  playerMatches, PlayerSortBy sortby)
	{
		double data = 0;
		switch (sortby)
		{
		case  points:
			data = playerMatches.getPoints();
			break;
		case rebs:
			data = playerMatches.getRebs();
			break;
		case assist:
			data = playerMatches.getHelp();
			break;
		}
       playerMatches.setHotData(data);		
	}
}