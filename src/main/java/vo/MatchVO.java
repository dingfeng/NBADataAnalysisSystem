package vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import po.MatchTeamPO;

public class MatchVO 
{
	 private MatchTeamPO team1;
     private  MatchTeamPO team2;
     private Date date;
     public MatchVO(MatchTeamPO team1,MatchTeamPO team2,
  		   Date date)
  		   {
  	            this.team1 = team1;
  	            this.team2 = team2;
  	            this.date = date;
  		   }
	public MatchTeamPO getTeam1() {
		return team1;
	}
	public MatchTeamPO getTeam2() 
	{
		return team2;
	}
	public Date getDate() 
	{
		return date;
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("team1 : ");
		sb.append(team1.toString());
		sb.append("\n");
		sb.append("team2 : ");
		sb.append(team2.toString());
		sb.append("\n");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
		String dateStr = dateFormat.format(date);
		sb.append(dateStr);
		return sb.toString();
	}
     
}
