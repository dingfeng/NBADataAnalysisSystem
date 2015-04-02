package data.playerdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import po.PlayerPO;
import dataservice.playerdataservice.PlayerDataService;

public class PlayerData implements PlayerDataService{
	
    private String action_file;
    private String info_file;
	private String portrait_file;
    
	public PlayerData(String filename)
	{
		action_file = filename + "/action";
		info_file = filename + "/info";
		portrait_file = filename + "/portrait";
	}
	
	
	public PlayerPO[] getAllPlayerData()
	{
		File file = new File(info_file);
		File[] file_children = file.listFiles();
		
		return null;
	}
	private String[] readInfo(File file)
	{
		String[] player_infos = new String[9];
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
			String tempStr = null;
			char ch  ;
			int m = 0;
			int n = 0;
			int j = 0;
			for (int i = 0 ; i < 9; i++)
			{
				br.readLine();
				tempStr = br.readLine();
				while(j < tempStr.length())
				{
					ch = tempStr.charAt(j);
					if (m == 0)
					{
					 while ((ch <= 'z' && ch >= 'a') || (ch<='Z' && ch >= 'A'))
						ch = tempStr.charAt(++j);
					 ++m;
					}
					else 
					{
					    n = 1;
						while ((ch <= 'z' && ch >= 'a') || (ch<='Z' && ch >= 'A')
						|| (ch <= '9' && ch >= '0')
						|| (ch == ' ') || (ch == ',') || (ch == '/'))
						{
							ch = tempStr.charAt(j + n);
							++n;
						}
						
					}
				}
				
				++j;
				
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
