package data.playerdata;

import java.io.BufferedReader;
import java.io.File;

public class main
{
	public static void main(String[] args)
	{
		String path = "G:/学习课件/软件工程与计算3/CSEIII data/NBAData/players/info";
		File file = new File(path);
		File[] file_children = file.listFiles();
	    for (File file0 : file_children)
	    {
	    	System.out.println(file0.getPath());
	    }
 	}

}
