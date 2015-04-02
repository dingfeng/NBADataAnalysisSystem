package data.playerdata;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import po.PlayerPO;
import dataservice.playerdataservice.PlayerDataService;

public class PlayerData implements PlayerDataService{
	private final static int PLAYER_NUM = 448;
    private String action_file;
    private String info_file;
	private String portrait_file;
    private Toolkit kit = Toolkit.getDefaultToolkit();
	public PlayerData(String filename)
	{
		action_file = filename + "/action";
		info_file = filename + "/info";
		portrait_file = filename + "/portrait";
	}
	
	
	public PlayerPO[] getAllPlayerData()
	{
		PlayerPO[] playerpos = new PlayerPO[PLAYER_NUM];
		int player_index = -1;
		File file = new File(info_file);
		File[] file_children = file.listFiles();
		String [] strs = null;
		String playerName = null;
		Image action = null;
		Image portrait = null;
		int margin = 0 - '0';
		for (File f : file_children)
		{
			strs = readInfo(f);
			playerName = strs[0];
			action = kit.getImage(action_file+"/"+playerName+".png");
			portrait = kit.getImage(portrait_file+"/"+playerName+".png");
			playerpos[++player_index] = new PlayerPO(action, portrait,
			playerName,strs[1].equals("N/A")?-1:Integer.parseInt(strs[1]),
					strs[2],strs[3].charAt(0) + margin, strs[3].charAt(2)+margin,
					Integer.parseInt(strs[4]),strs[5],Integer.parseInt(strs[6]),
					strs[7].equals("R")?0:Integer.parseInt(strs[7]),strs[8]
					);
		}
		
		return playerpos;
	}
	
	private String[] readInfo(File file)
	{
		String[] info = new String[9];
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
				m=0;
				j=0;
				while(j < tempStr.length())
				{
					ch = tempStr.charAt(j);
					if ((ch <= 'z' && ch >= 'a') || (ch<='Z' && ch >= 'A')
							|| (ch <= '9' && ch >= '0')
							|| (ch == ' ') || (ch == ',') || (ch == '/') || (ch == '-'))
					if (m == 0)
					{
					 while ((ch <= 'z' && ch >= 'a') || (ch<='Z' && ch >= 'A'))
						ch = tempStr.charAt(++j);
					 ++m;
					}
					else 
					{
					    n = 1;
					    ch = tempStr.charAt(j + n);
						while ((ch <= 'z' && ch >= 'a') || (ch<='Z' && ch >= 'A')
						|| (ch <= '9' && ch >= '0')
						|| (ch == ' ') || (ch == ',') || (ch == '/') || (ch == '-'))
						{
							++n;
							ch = tempStr.charAt(j + n);
						}
						info[i] = tempStr.substring(j, j + n);
						break;
					}
					++j;
				}
				
			}
			br.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return info;
	}
}
