package data.matchdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import dataservice.matchdataservice.MatchDataService;
import po.MatchPlayerPO;
import po.MatchTeamPO;
import po.MatchesPO;

public class MatchData  implements MatchDataService
{
  private  String matchesPath;
  private   MatchQueue matches;
  private final static int MAX_TODAY_MATCH = 50;
  private final static int MAX_SECTION_COUNT = 10;
  private final static int PLAYER_INFO_NUM = 18;
  private final static int FIRST_LINE_SIZE = 3;
  private final static int MAX_MATCH_PLAYERS = 20;
  private final static int MAX_TEAM_ABB = 8;
  private final static int MAX_NEW_MATCH = 100;
  private MatchesPO[] todayMatches = new MatchesPO[MAX_TODAY_MATCH];
  private MatchesPO[] newMatches = new MatchesPO[MAX_NEW_MATCH];
  private int newMatchesLength = -1;
  private  int todayMatchesLength = -1;
  private String today;
  private int latest_index = -1;
  private boolean inited = false;
  private String first_file_name;
  File file ;
  String lastfilename;
  int file_num;
  boolean first_change = true;
  public MatchData(){}
  public  MatchData(String path)
  {
	  matchesPath = path  ;
	  file = new File(matchesPath);
	  matches = new MatchQueue();
	  initData();
  }
  
  //第一次添加数据
  private void initData()
  {
	  File[] file_children = file.listFiles();
	  if (file_children.length == 0)
		  return;
	  Arrays.sort(file_children);
	  file_num = file_children.length;
	  File file_tmp = file_children[file_num-1];
	  File first_file = file_children[0];
	  first_file_name = first_file.getName();
	  String last_file_name = file_tmp.getName();
	  //13-14_01-01
	  //无跨年数据
	  if (isRightOrder(first_file_name, last_file_name))    
	  {
	  today = last_file_name.substring(0, 11);
	  int i = file_num - 1;
	  MatchesPO matchpo = null;
	  while (file_tmp.getName().startsWith(today))
	  {
		  if ( i == 0)
		  {
			  break;
		  }
		  file_tmp = file_children[--i];
	  }
	  for (int j = 0; j <= i ; j++)
	  {
		 matches.enQueue(getMatchPO(file_children[j]));
	  }
	  
	  for (int n = i+1; n < file_num; n++)
	  {
		  file_tmp = file_children[n];
		  matchpo = getMatchPO(file_tmp);
		  todayMatches[++todayMatchesLength] = matchpo;
		  matches.enQueue(matchpo);
	  }
	  today = last_file_name.substring(6, 11);
	  latest_index = file_num -1;
	  }
	  else 
	  {
		  //存在跨年数据
		  int i = 0;
		  char ch = first_file_name.charAt(6);
		  while (ch == '0')
		  {
			  ++i;
			  ch =  file_children[i].getName().charAt(6);
		  }
		  //搜索到最新添加的一场比赛
		  String latest_file_name = file_children[i-1].getName();
		  char day = latest_file_name.charAt(10);
		  int n =  i  - 1;
		  //确定最近一天的比赛数据与其他数据的分界
		  while (n >= 0 && file_children[n].getName().charAt(10) == day)  --n;
		  n += 1;
		  today = latest_file_name.substring(6, 11);
		  latest_index = i - 1;
		  for (int j = i ; j < file_num; j++)
		  {
			  matches.enQueue(getMatchPO(file_children[j]));
		  }
		  for (int j = 0; j < n ; j++)
		  {
			  matches.enQueue(getMatchPO(file_children[j]));
		  }
		  MatchesPO matchpo = null;
		  for (int j = n; j < latest_index + 1; j++)
		  {
			  matchpo = getMatchPO(file_children[j]);
			  matches.enQueue(matchpo);
			  todayMatches[++todayMatchesLength] = matchpo; 
		  }
		  
 	  }
	  lastfilename = file_children[latest_index].getName();
	  inited = true;
  }
  
  
  private void updateData()
  {
	 
	if (!inited)
	{
		initData();
		return;
	}
	File[] file_children = file.listFiles();
	if (file_children.length == 0||file_num == file_children.length) return;
	Arrays.sort(file_children);
	
	String first_f_name = file_children[0].getName();
	int n = latest_index;
	String latest_index_filename = lastfilename;
	if (isRightOrder(first_file_name, first_f_name))               //未产生跨年数据添加
	{
		//搜索新添加的数据，考虑到已经产生过跨年数据添加和未产生过跨年数据添加两种情况
		while (n+1 < file_num&&isRightOrder(latest_index_filename,file_children[++n].getName()));  
		int old_latest_index = latest_index;
		latest_index = n -1;
		latest_index_filename = file_children[latest_index].getName();
		int m = latest_index;
		//搜索最最近添加数据和最近添加的数据的分界
		while (m - 1 >= 0&&isSameDay(file_children[--m].getName(), latest_index_filename));   
		MatchesPO match = null;
		//第一次发生跨年数据添加后的递归调用
		if (old_latest_index !=  -1)
		{
			newMatchesLength = -1;
		}
		//添加最近添加数据
		for (int k = old_latest_index+1; k <= m;k++)
		{
			match = getMatchPO(file_children[k]);
			matches.enQueue(match);
			newMatches[++newMatchesLength] = match;
		}
		 //最近一天的比赛数据，假设为今天的比赛数据
		todayMatchesLength = -1;
		for (int k = m+1; k <= latest_index; k++)
		{
			 match = getMatchPO(file_children[k]);
			matches.enQueue(match);
			todayMatches[++todayMatchesLength] = match;
			newMatches[++newMatchesLength] = match;
		}
		file_num = file_children.length;
	}
	else 
	{
		//发生跨年添加数据的情况
		int length = file_num;
		while (!file_children[--length].getName().equals(lastfilename));
		MatchesPO match = null;
		for (int i = length + 1; i < file_num; i++)
		{
			match = getMatchPO(file_children[i]);
			matches.enQueue(match);
			newMatches[++newMatchesLength] = match;
		}
		latest_index = -1;
		first_file_name = first_f_name;
		lastfilename = first_f_name;
		//准备新的参数后，递归更新数据
		updateData();
	}
	
  }
  
  public MatchesPO[] getNewMatches()
  {
	  if (newMatchesLength == -1)
	  {
		  return null;
	  }
	  MatchesPO[] ma = new MatchesPO[newMatchesLength+1];
	  for (int i = 0; i <= newMatchesLength; i++)
	  {
		  ma[i] = newMatches[i];
	  }
	  return ma;
  }
  
  public boolean changed()
  {
	  File[] files = file.listFiles();
	  int file_len = files.length;
	  if (file_len  == file_num)
	  {
		  return false;
	  }
	  else return true;
  }
  
  private void dealWithDirtyTime(MatchPlayerPO[] players, int time)
  {
	  int count = 0;
	  int player_times = 0;
	  MatchPlayerPO player = null;
	  MatchPlayerPO temp = null;
	  for (int i = 0; i < players.length; i++)
	  {
		  temp = players[i];
		  if (temp.getTime() == -1)
		  {
			  ++count;
			  player = temp;
		  }
		  else 
		  {
			  player_times += temp.getTime();
		  }
	  }
	  if (count == 0)
		  return;
	  if (count == 1)
	  {
		  player.setTime(time * 5 - player_times);
	  }
	  if (count > 1 )
	  {
		 for (MatchPlayerPO p : players)
		 {
			 if (p.getTime() == -1)
			 {
				 p.settIimeDirty();
			 }
		 }
	  }
  }
  public MatchesPO getMatchPO(File file) 
  {
	  MatchesPO matchpo = null;
	  BufferedReader reader =null;
	  String[] first_line = new String[FIRST_LINE_SIZE];
	  int first_line_index = -1;
	  String[] sections = new String[MAX_SECTION_COUNT];
	  int sections_index = -1;
	  String team1 = null;
	  String team2 = null;
	  MatchPlayerPO [] team1_players = new MatchPlayerPO[MAX_MATCH_PLAYERS];
	  int player1_index = -1;
	  MatchPlayerPO [] team2_players = new MatchPlayerPO[MAX_MATCH_PLAYERS];
	  int player2_index = -1;
	  String line = null;
	  char[] content_list = null;
	  int first = 0;
	  int last  = 0;
	  char check = 0;
	  try{
	  reader = new BufferedReader(new FileReader(file)  );
	  
	  line = reader.readLine();                 //read first line 
	  content_list = line.toCharArray();
	  while(first < content_list.length)
	  {
		  last = first + 1;
		  check = content_list[last];
		  while (check != ';') check = content_list[++last];
		  first_line[++first_line_index] = line.substring(first, last);
		  first = last + 1;
	  }
	  first = 0;
	  last = 0;
	  line = reader.readLine();                 // read second line
	  content_list = line.toCharArray();
	  while (first < content_list.length)
	  {
		  last = first + 1;
		  check = content_list[last];
		  while (check != ';') check = content_list[++last];
		  sections[++sections_index] = line.substring(first, last);
		  first = last + 1;
	  }
	  team1 = reader.readLine();
	  line = reader.readLine();
	  while (line.length() > MAX_TEAM_ABB)
	  {
		  team1_players[++player1_index] = dealWithLine(line);
		  line = reader.readLine();
	  }
	  
	  team2 = line;
	  while ((line = reader.readLine())!= null)
	  {
		team2_players[++player2_index] = dealWithLine(line);
	  }
	  int time = 2880 + ( sections_index -3 ) * 300;
	  
	  MatchPlayerPO[] team_player1 = new MatchPlayerPO[player1_index+1];
	  for (int i = 0; i <= player1_index; i++)
	  {
		  team_player1[i] = team1_players[i];
	  }
	  
	  MatchPlayerPO[] team_player2 = new MatchPlayerPO[player2_index+1];
	  for (int i = 0; i <= player2_index; i++)
	  {
		  team_player2[i] = team2_players[i];
	  }
	  String stos = first_line[2];
	  int index  = stos.indexOf('-');
	  int score1 =  Integer.parseInt(stos.substring(0,index));
	  int score2 = Integer.parseInt(stos.substring(index+1, stos.length()));
	  int[] section1 = new int[sections_index + 1];
	  int[] section2 = new int[sections_index + 1];
	  String section_scores = null;
	  for (int i = 0; i <= sections_index; i++)
	  {
		  section_scores = sections[i];
		  index = section_scores.indexOf('-');
		  section1[i] = Integer.parseInt(section_scores.substring(0,index));
		  section2[i] = Integer.parseInt(section_scores.substring(index+1, section_scores.length()));
	  }
	  dealWithDirtyTime(team_player1 , time);
	  dealWithDirtyTime(team_player2, time);
      MatchTeamPO  match_team1 = new MatchTeamPO(team_player1, section1, 
    		  score1, team1, time);
      MatchTeamPO match_team2 = new MatchTeamPO(team_player2, section2,
    		  score2, team2, time);
      matchpo = new MatchesPO(match_team1, match_team2, first_line[0]);
	  reader.close();
	  }catch (Exception e){return getMatchPO(file);}
	return matchpo;
  }
  
  
  public MatchPlayerPO dealWithLine(String line)
  {
	String[] player_info  = new String[PLAYER_INFO_NUM];
	int index = -1;
	char[] line_chs = line.toCharArray();
	char ch = 0;
	int start = 0;
	int end = 0;
	while ( start < line_chs.length)
	{
		if (line_chs[start] == ';')
		{
			player_info[++index] = null;
			++start;
			continue;
		}
		end = start + 1;
		ch = line_chs[end];
		while (ch != ';')  ch = line_chs[++end];
		player_info[++index] = line.substring(start, end);
		start = end + 1;
	}
	
	int total_time = -1;
	int i = -1;
	String time_str = player_info[2];
	if (time_str != null)
	{
		i = time_str.indexOf(':');
		if ( i != -1)
		{	
		total_time = 60 * Integer.parseInt(time_str.substring(0,i))+
				Integer.parseInt(time_str.substring(i+1, time_str.length()));
		}
	}
	return new MatchPlayerPO(player_info[0],player_info[1],total_time,
			Integer.parseInt(player_info[3]),Integer.parseInt(player_info[4]),Integer.parseInt(player_info[5]),Integer.parseInt(player_info[6]),
			Integer.parseInt(player_info[7]),Integer.parseInt(player_info[8]),Integer.parseInt(player_info[9]),Integer.parseInt(player_info[10]),
			Integer.parseInt(player_info[11]),Integer.parseInt(player_info[12]),Integer.parseInt(player_info[13]),Integer.parseInt(player_info[14]),
			Integer.parseInt(player_info[15]),Integer.parseInt(player_info[16]));
  }
  public MatchesPO[] getTodayMatches()
  {
	  updateData();
	  MatchesPO[] today_matchpos = new MatchesPO[todayMatchesLength+1];
	  for (int i = 0;i <= todayMatchesLength; i++)
	  {
		 today_matchpos[i] = todayMatches[i];
	  }
	return  today_matchpos;
  }
  
  public MatchesPO[] getAllMatches()
  {
	  updateData();
	return  matches.getAllMatches();
  }
  
  
  
  public MatchesPO[] getRecentPlayerMatches(String playername, int num)
  {
	  return matches.getRecentPlayerMatches(playername, num);
  }
  
  public MatchesPO[] getRecentTeamMatches(String teamname, int num)
  {
	  return matches.getRecentTeamMatches(teamname, num);
  }
  
  private boolean isRightOrder(String start_file, String end_file)
  {
	  return start_file.charAt(6) == end_file.charAt(6);
  }
  private boolean isSameDay(String filename1, String filename2)
  {
	return filename1.substring(6, 11).equals(filename2.substring(6, 11));
  }
  
}
