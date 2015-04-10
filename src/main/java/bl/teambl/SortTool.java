package bl.teambl;

import vo.SortType;

public class SortTool  implements Comparable<SortTool>
{
  private double data;
  private SortType type;
  public SortTool(double data, SortType type)
  {
	  this.data = data;
	  this.type = type;
  }
  
  public double getData()
  {
	  return data;
  }
  
 public int compareTo(SortTool e) {
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
