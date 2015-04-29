package data.matchdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
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
  private MatchesPO[] todayMatches = new MatchesPO[MAX_TODAY_MATCH];
  private  int todayMatchesLength = -1;
  private String today;
  private int latest_index = -1;
  private String first_file_name;
  File file ;
  String lastfilename;
  int file_num;
  int fileNum = 0;
  public MatchData(){}
  public  MatchData(String path)
  {
	  matchesPath = path  ;
	  file = new File(matchesPath);
	  matches = new MatchQueue();
	  File[] files = file.listFiles();
	  fileNum = files.length;
	  Arrays.sort(files);
	  initData(files);
  }
  
  //第一次添加数据
  public  void initData(File[] file_children)
  {
	 
	  if (file_children.length == 0)
		  return;
	  todayMatchesLength = -1;
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
	  
	  if (i == 0)
	  {
		  --i;
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
  }
  
  
  public void updateData()
  {
	  File[] allFiles = file.listFiles();
	  Arrays.sort(allFiles);
	  int newFileNum = allFiles.length;
	  if (newFileNum == fileNum)
	  {
		  return;
	  }
	  int firstFileIndex = -1;
	  String filename = null;
	  for (int i = 0; i < allFiles.length; i++)
	  {
		  filename = allFiles[i].getName();
		  if (filename.charAt(i) == '1')
		  {
			  firstFileIndex = i;
			  break;
		  }
	  }
	  //没有比赛
	  if (firstFileIndex == -1)
	  {
		  return;
	  }
	  int margin = newFileNum  - fileNum;
	  File[] files =  new File[margin];
	  int file_start = newFileNum - firstFileIndex - fileNum;
	  if (file_start > 0)
	  {
		  for (int i = 0; i < files.length; i++)
		  {
			  if (fileNum + i < newFileNum)
			  files[i] = allFiles[fileNum+i];
			  else 
			  {
				  files[i] = allFiles[fileNum + i - newFileNum];
			  }
		  }
	  }
	  else 
	  {
		  int index = file_start * (-1) - 1;
		  for (int i = 0; i < files.length; i++)
		  {
			  files[i] = allFiles[index+i];
		  }
	  }
	  initData(files);
	  fileNum = newFileNum;
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
  
  //处理球员中有脏数据
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
  
  //解析文件中构成对象
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
	  reader = new BufferedReader(new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))  );
	  
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
  
  //获得一行数据，产生一个球员的比赛信息
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
  static int l =0;
  //获得今日的比赛数据
  public MatchesPO[] getTodayMatches()
  {
	  MatchesPO[] today_matchpos = new MatchesPO[todayMatchesLength+1];
	  for (int i = 0;i <= todayMatchesLength; i++)
	  {
		 today_matchpos[i] = todayMatches[i];
	  }
	return  today_matchpos;
  }
  
  public MatchesPO[] getAllMatches()
  {
	return  matches.getNewMatches();
  }
  
  
  
//  public MatchesPO[] getRecentPlayerMatches(String playername, int num)
//  {
//	  return matches.getRecentPlayerMatches(playername, num);
//  }
//  
//  public MatchesPO[] getRecentTeamMatches(String teamname, int num)
//  {
//	  return matches.getRecentTeamMatches(teamname, num);
//  }
  
  private boolean isRightOrder(String start_file, String end_file)
  {
	  return start_file.charAt(6) == end_file.charAt(6);
  }
  private boolean isSameDay(String filename1, String filename2)
  {
	return filename1.substring(6, 11).equals(filename2.substring(6, 11));
  }
  
public MatchesPO[] getNewMatches() {
	return matches.getNewMatches();
}
  
}