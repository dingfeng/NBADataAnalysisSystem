package bl.matchbl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class TeamPlayer {
	  static HashMap<String,String[]> map;
	  public TeamPlayer()
	  {
		  if (map == null)
		  {
			  try {
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teamPlayerMap/teamplayer"));
				try {
					map = (HashMap<String, String[]>) ois.readObject();
					ois.close();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
	  }
      public String[] getAllTeam()
      {
    	  Iterator<String> itr = map.keySet().iterator();
    	  String[] teams = new String[map.size()];
    	  int i = -1;
    	  while (itr.hasNext())
    	  {
    		  teams[++i] = itr.next();
    	  }
		return teams;
    	  
      }
      public ArrayList<String> getAllPlayer()
      {
    	  String[] teams = getAllTeam();
    	  ArrayList<String> list = new ArrayList<String>();
    	  for (String team : teams)
    	  {
    		  String[] allPlayers = map.get(team);
    		  for (String p : allPlayers)
    		  {
    			  list.add(p);
    		  }
    	  }
    	  
		return  list;
      }
}
