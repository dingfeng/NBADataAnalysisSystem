package data.playerdata;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import dataservice.playerdataservice.PlayerDataService;
import po.PlayerPO;


public class PlayerData_old    {
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	HashMap<String,PlayerPO> allData ;
     //init the dataSource 
	public PlayerData_old()
	{
	}
	//获得所有的球员数据
	public HashMap<String,PlayerPO> getAllPlayerData() {
		if(allData==null)
			allData=readAll();
		return allData;
	}

	public PlayerPO getOnePlayerData(String playerName) {
	if(allData==null)
		allData=readAll();
	return allData.get(playerName);
	}
	// 得到文件目录下的所有子目录
	private File[] getAllFile(File f) {

		File[] paths = null;

		if (f.isDirectory())
			paths = f.listFiles();
        
		return paths;
	}

	// 读出所有球员的信息
	private HashMap<String,PlayerPO> readAll() {
        HashMap<String,PlayerPO>  allPlayerData = new HashMap<String,PlayerPO>();
		File[] info = getAllFile(new File("data\\players\\info\\"));
		for (int i = 0; i < info.length; i++) {
			PlayerPO playerPO = readOne(info[i]);
			allPlayerData.put(playerPO.getName(), playerPO);
		}
		return allPlayerData;

	}
    
	// 读出单个球员的信息
	private PlayerPO readOne(File filename) {
		PlayerPO playerPO = null;
			Image action = null;
			Image portrait=null;
			try {
			 BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
	    	  int i = 0;
	    	  String line = null;
	    	  int m = 0;
	    	  String [] usingStr = new String[18];;
	    	  while ((line = br.readLine()) != null)
	    	  {
	    		  i++;
	    		  if (i % 2 != 0)
	    			  continue;
	              String [] lines = line.split("[│\t\n║\r]");
	              
	              for (int j = 0; j < lines.length; j++ )
	              {
	            	  if (!lines[j].equals(""))
	            	  {
	            		  usingStr[m] = lines[j];
	            		  ++m;
	            	  }
	              }
	    	  }
	    	    String name = usingStr[1];
	    	    String numStr = usingStr[3];
	    		int number = -1;
	    	    if (!numStr.equals("N/A"))
	    	    	number = Integer.parseInt(numStr);
				char position = usingStr[5].charAt(0);
				String [] heights = usingStr[7].split("-");
				int heightfeet = Integer.parseInt(heights[0]);
				int heightinch = Integer.parseInt(heights[1]);
				int weight = Integer.parseInt(usingStr[9]);
				String birth = usingStr[11];
				String ageStr = usingStr[13];
				int age = Integer.parseInt(usingStr[13]);
				String ballAge = usingStr[15];
				int exp = 0;
				if (!ballAge.equals("R"))
				exp = Integer.parseInt(usingStr[15]);
				String school = usingStr[17];

			//读取两张图片		
				String [] imagePaths = filename.toString().split("\\\\");
			try
			{
			 action = toolkit.getImage(imagePaths[0]+"/"+imagePaths[1]+"/"+"action"+"/"+imagePaths[3]+".png");
			}catch (Exception e){
				e.printStackTrace();
			}
		    try{
		    	portrait = toolkit.getImage(imagePaths[0]+"/"+imagePaths[1]+"/"+"portrait"+"/"+imagePaths[3]+".png");
		    }catch (Exception e)
		    {
		    	e.printStackTrace();
		    }
			playerPO = new PlayerPO(action,portrait,name,number,String.valueOf(position),heightfeet,heightinch,weight,birth,age,exp,school);
			br.close();
		return playerPO;
			}catch (Exception e){e.printStackTrace();}
		return null;

	}

}
