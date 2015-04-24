package bl.teambl;

import vo.SortType;

public class SortTool  implements Comparable<SortTool>
{
  private double[] data;
  private SortType[] type;
  public SortTool(double[] data, SortType[] type)
  {
	  this.data = data;
	  this.type = type;
  }
  
  public double getData()
  {
	  return data[0];
  }
  
  public double[] getDataList()
  {
	  return data;
  }
  
  
 public int compareTo(SortTool e) {
	double[] yourDatas = e.getDataList();
	for (int i = 0; i < data.length; i++)
	{
		if (data[i] != yourDatas[i])
		{
			if (type[i] == SortType.ASEND)
			{
				if (data[i] > yourDatas[i])
				{
					return 1;
				}
				else if (data[i] < yourDatas[i])
				{
					return -1;
				}
				else return 0;
			}
			else 
			{
				if (data[i] > yourDatas[i])
				{
					return -1;
				}
				else if (data[i] < yourDatas[i])
				{
					return 1;
				}
				else return 0;
			}
		}
	}
	return 0;
}
  
}
