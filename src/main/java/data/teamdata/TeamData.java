package data.teamdata;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;

import dataservice.teamdataservice.TeamDataService;
import po.TeamPO;
import vo.Area;

public class TeamData implements TeamDataService
{
   String filename;
   String svg_uri;
   final static int TEAM_NUM = 30;
   
  public TeamData (String filename)
  {
	  this.filename = filename;
	  svg_uri = "file:\\"+filename+"/";
  }
  
  public TeamPO[] getAllTeamData()
  {
	TeamPO[] teampos  = new TeamPO[TEAM_NUM];
	String parser = XMLResourceDescriptor.getXMLParserClassName();
    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
    Document doc = null;
	try
	{
	BufferedReader reader = new BufferedReader(new FileReader(filename+"/teams")  );
	String tempStr = reader.readLine();
	String[] items = null;
	for (int i  = 0; i < 30; i++)
	{
	tempStr = reader.readLine();
	items = dealWithLine(tempStr);
	doc = f.createDocument( svg_uri+ items[1]+ ".svg");
	teampos[i] = new TeamPO(doc,items[0],items[1],items[2],
	items[3],convToPlayerArea(items[4]),items[5],Integer.parseInt(items[6]));
	}
	
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return teampos;
  }
  
  
  private  String[] dealWithLine(String tempStr)
  {
	  
	  int str_len = tempStr.length();
	  char ch = 0;
	  int j = 1;
	  char ch_in = 0;
	  int i = 0;
	  String [] items = new String[7];
	  int item_index = 0;
	  while (i < str_len)
	  {
		  ch = tempStr.charAt(i);		  
		  if (item_index <= 5)
		  {
			  if (('A' <= ch && ch <= 'Z') || ('a' <= ch &&  ch <= 'z'))
			  {
				  j = 1;
				  ch_in = tempStr.charAt(i+j);
				  while (('A' <= ch_in && ch_in < 'Z') || ('a' <= ch_in &&  ch_in <= 'z')
						  || ch_in == ' ')
				  {
					  ch_in = tempStr.charAt(i + (++j));
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
