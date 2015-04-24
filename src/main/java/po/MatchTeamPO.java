package po;

import java.util.ArrayList;
import java.util.Iterator;

public class MatchTeamPO {
       private MatchPlayerPO[] players;
       private int[] scores;
       private int totalScores;
       private String name;
       private int time;
       public MatchTeamPO(MatchPlayerPO[] player,int [] scores,int totalScores,String name, int time)
       {
    	   this.players = player;
    	   this.scores = scores;
    	   this.totalScores = totalScores;
    	   this.name = name;
    	   this.time = time;
    	   if (name.equals("NOH"))
    	   {
    		   this.name = "NOP";
    	   }
    	   if (player != null)
    	   {
    		   for (MatchPlayerPO p : player)
    		   {
    			   p.setTeamnameAbridge(name);
    		   }
    	   }
       }
    public int getTime()
    {
    	return time;
    }
    
	public MatchPlayerPO[] getPlayers()
	{
		return players;
	}
	
	public int[] getScores()
	{
		return scores;
	}
	public int getTotalScores() {
		return totalScores;
	}
	public String getName() {
		return name;
	}
    public String toString()
    {
    	StringBuilder sb = new StringBuilder();
    	sb.append(name + "---");
    	sb.append(totalScores);
    	for (int score : scores)
    	{
    		sb.append(score);
    		sb.append("  ");
    	}
    	sb.append("  ");
    	sb.append("\n");
    	for (MatchPlayerPO player : players)
    	{
    		sb.append(player.toString());
    	}
        return sb.toString();
    }
    
} 
