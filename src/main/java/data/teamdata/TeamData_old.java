package data.teamdata;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;

import dataservice.teamdataservice.TeamDataService;
import po.TeamPO;
import vo.Area;

public class TeamData_old  {
	private String filename = "data/teams/teams";
	private static HashMap<String, TeamPO> map = new HashMap<String, TeamPO>();
	public static  String projectDir = System.getProperty("user.dir");
	
	
	/**
	 * 得到所有队伍的基本信息
	 */
	public Iterator<TeamPO> getAllTeamData() 
	{
		//如果数据未读过
		if(map.size() == 0)
		{
			this.read();
		}
		return map.values().iterator();
	}

	/**
	 * 得到一个队伍的基本信息,若没有此队伍则返回null
	 */
	public TeamPO getOneTeamData(String teamName) {
		//如果数据未读过，则读数据
		if(map.size() == 0){
			this.read();
		}
		//找到相应的数据
		return map.get(teamName);
	}

	/**
	 * 读方法
	 * @return	文件读出解析后的TeamPO对象
	 */
	private void  read(){
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(filename)  );
			String tempStr = reader.readLine();
			while(!(tempStr = reader.readLine()).equals("╚═══════════════╧═══╧═══════════════════╧═╧═════════════╧═══════════════════════════════╧════╝")){
				//去掉数据中的空格
				Pattern p = Pattern.compile("\\s*|\t|\r|\n");
	            Matcher m = p.matcher(tempStr);
	            tempStr = m.replaceAll("");
				String[] str = tempStr.split("\\│");
				String Name = str[0].substring(1);//将数据行前的"||"去掉
				//得到相应的图片
				Image icon;// = new ImageIcon("data\\teams\\" + str[1] + ".svg\\", "TeamIcon");
				icon = Toolkit.getDefaultToolkit().getImage("data\\teams\\" + str[1] + ".svg\\");
				String parser = XMLResourceDescriptor.getXMLParserClassName();
    		    SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
    		     projectDir.replace("\\", "/");
    		    String uri = "file:\\"+projectDir+"\\data\\teams\\"+str[1]+".svg";
    		    Document doc = f.createDocument(uri);
				//得到分区
				Area area;
				if(str[4].equals("Atlantic")){
					area = Area.ATLANTIC;
				}else if(str[4].equals("Central")){
					area = Area.CENTRAL;
				}else if(str[4].equals("Southeast")){
					area = Area.SOUTHEAST;
				}else if(str[4].equals("Southwest")){
					area = Area.SOUTHWEST;
				}else if(str[4].equals("Northwest")){
					area = Area.NORTHWEST;
				}else{
					area = Area.PACIFIC;
				}
				//得到建立时间
				int foundYear = Integer.parseInt(str[6].substring(0, 4));
				TeamPO newTeam = new TeamPO(doc, Name, str[1], str[2], str[3], area, str[5], foundYear);
				map.put(str[1], newTeam);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
