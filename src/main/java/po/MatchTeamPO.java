package po;

import java.util.ArrayList;
import java.util.Iterator;

public class MatchTeamPO {
       private ArrayList<MatchPlayerPO> players = new ArrayList<MatchPlayerPO>();
       private int[] scores;
       private int totalScores;
       private String name;
       public MatchTeamPO(ArrayList<MatchPlayerPO> player,int [] scores,int totalScores,String name)
       {
    	   this.players = player;
    	   this.scores = scores;
    	   this.totalScores = totalScores;
    	   this.name = name;
       }
	public ArrayList<MatchPlayerPO> getPlayers() {
		return players;
	}
	public int[] getScores() {
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
    	sb.append(name + " ");
    	sb.append(totalScores);
    	for (int score : scores)
    	{
    		sb.append(score);
    		sb.append(" ");
    	}
    	sb.append(totalScores);
    	sb.append("  ");
    	sb.append("\n");
        Iterator<MatchPlayerPO> playersItr = players.iterator();
        while (playersItr.hasNext())
        {
        	MatchPlayerPO match = playersItr.next();
        	sb.append(match.toString());
        	sb.append("  ");
        }
        return sb.toString();
    }
    
} 
