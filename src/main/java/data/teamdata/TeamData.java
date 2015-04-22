package data.teamdata;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;

import dataservice.teamdataservice.TeamDataService;
import po.TeamPO;
import vo.Area;

public class TeamData implements TeamDataService
{
   private String filename;
   private final static int TEAM_NUM = 30;
   private Toolkit tool = Toolkit.getDefaultToolkit();
  public TeamData (String filename)
  {
	  this.filename = filename;
  }
  
  //获得所有球队的今本信息
  public TeamPO[] getAllTeamData()
  {
	TeamPO[] teampos  = new TeamPO[TEAM_NUM];
    BufferedReader reader= null;
	try
	{
    reader = new BufferedReader(new FileReader(filename+"/teams")  );
	String tempStr = reader.readLine();
	String[] items = null;
	Image image = null;
	for (int i  = 0; i < 30; i++)
	{
	tempStr = reader.readLine();
	items = dealWithLine(tempStr);
    image = tool.getImage(filename +"/"+items[1]+".png");
	teampos[i] = new TeamPO(image,items[0],items[1],items[2],
	items[3],convToPlayerArea(items[4]),items[5],Integer.parseInt(items[6]));
	}
	reader.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	return teampos;
  }
  
  //处理球队数据一行
  private  String[] dealWithLine(String tempStr)
  {
	  char [] content_list = tempStr.toCharArray();
	  int str_len = content_list.length;
	  char ch = 0;
	  int j = 1;
	  char ch_in = 0;
	  int i = 0;
	  String [] items = new String[7];
	  int item_index = 0;
	  while (i < str_len)
	  {
		  ch = content_list[i];		  
		  if (item_index <= 5)
		  {
			  if (('A' <= ch && ch <= 'Z') || ('a' <= ch &&  ch <= 'z'))
			  {
				  j = 1;
				  ch_in = content_list[i+j];
				  while (('A' <= ch_in && ch_in < 'Z') || ('a' <= ch_in &&  ch_in <= 'z')
						  || ch_in == ' ')
				  {
					  ch_in = content_list[i + (++j)];
				  }
				  
				  items[item_index] = tempStr.substring(i, i+j);
				  
				  i += j;
				  ++item_index;
			  }
		  }
		  
		  else if (item_index == 6)
		  {
			  if (ch < '9' && ch > '0')
			  {
				  items[item_index] = tempStr.substring(i, i + 4);
				  break;
			  }
		  }
		  i++;
	  }
	  return items;
  }
  
  private Area convToPlayerArea(String area)
  {

	  Area playerArea = null;
	  switch (area)
	  {
	  case "Atlantic" :
		  playerArea = Area.ATLANTIC;
		  break;
	  case "Central":
		  playerArea = Area.CENTRAL;
		  break;
	  case "Southeast":
		  playerArea = Area.SOUTHEAST;
		  break;
	  case "Southwest":
		  playerArea = Area.SOUTHWEST;
		  break;
	  case "Northwest":
		  playerArea = Area.NORTHWEST;
		  break;
	  case "Pacific" : 
		  playerArea = Area.PACIFIC;
		  break;
	  }
	  return playerArea;
  }
}
