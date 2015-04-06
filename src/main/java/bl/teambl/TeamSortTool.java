package bl.teambl;

import vo.SortType;

public class TeamSortTool  implements Comparable<TeamSortTool>
{
  private double data;
  private SortType type;
  public TeamSortTool(double data, SortType type)
  {
	  this.data = data;
	  this.type = type;
  }
  
  public double getData()
  {
	  return data;
  }
  
 public int compareTo(TeamSortTool e) {
	if (type == SortType.ASEND)
	{
		double data1 = e.getData();
		if(data > data1 ) return 1;
		else if (data < data1 ) return -1;
		else return 0;
	}
	else 
	{

		double data1 = e.getData();
		if(data > data1 ) return -1;
		else if (data < data1 ) return 1;
		else return 0;
	}
}
  
}
