package bl.teambl;

import vo.SortType;
import vo.TeamMatchVO;
import vo.TeamSortBy;

public class main {
public static void main(String[] args)
{
   Team team = new Team();
}
 public static  String getMatchArea(String playerArea)
 {
	 String result = null;
	 switch (playerArea)
	 {
	 case "Central":
		 result = "E";
		 break;
	 case "Atlantic":
		 result  = "E";
		 break;
     case "Pacific":
    	 result = "W";
    	 break;
	 case "Southwest":
		 result = "W";
		 break;
	 case "Southeast":
		 result = "E";
		 break;
	 case "Northwest":
		 result = "W";
		 break;
	 }
	 return result;
 }
}
