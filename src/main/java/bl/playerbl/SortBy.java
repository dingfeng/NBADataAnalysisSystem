package bl.playerbl;

import vo.SortType;

public class SortBy implements Comparable<SortBy>{
    String strSort;            //排序依据为字符串类型的数据
    double doubleSort = -1;    //排序依据为double类型的数据
    SortType sortType;         //排序类型
    public SortBy(String strSort, SortType sortType)
    {
    	this.strSort = strSort;
    	this.sortType = sortType;
    }
    
    public SortBy(double doubleSort, SortType sortType)
    {
    	this.doubleSort = doubleSort;
    	this.sortType = sortType;
    }
    public String getStrSort()
    {
      return strSort;	
    }
    
    public double getDoubleSort()
    {
    	return doubleSort;
    }
    
    //实现排序
	public int compareTo(SortBy e) {
		
	
		switch(sortType)
		{
		
		case ASEND:
			if (strSort == null && doubleSort == -1)
			{
				return 1;
			}
			
		if (strSort != null)
		{
			
			if (e.getStrSort() == null)
				return -1;
			return strSort.compareTo(e.getStrSort());
		}
		
		else 
		{
			if (doubleSort > e.getDoubleSort())
			{
				return 1;
			}
			else if (doubleSort < e.getDoubleSort())
			{
				return -1;
			}
			else return 0;
		}
		
		case DESEND:
			if (strSort == null && doubleSort == -1)
			{
				return 1;
			}
			
	    if (strSort != null)
	    {
	    	String strSort2 = e.getStrSort();
	    	if (strSort2 == null || strSort.compareTo(strSort2) > 0)
	    	{
	    		return -1;
	    	}
	    	else if (strSort.compareTo(strSort2) <0)
	    	{
	    		return 1;
	    	}
	    	else return 0;
	    }
	    else 
	    {

			if (doubleSort > e.getDoubleSort())
			{
				return -1;
			}
			else if (doubleSort < e.getDoubleSort())
			{
				return 1;
			}
			else return 0;
	    }
			
		}
		
		return 0;
	}

}
