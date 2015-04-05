package bl.matchbl;

import po.MatchesPO;

public class Queue 
{
  protected MatchesPO[] matches;
  private int max;
  private int lenth;
  //如果是球队则使用缩写
  private String name;
  public Queue(int max,String name)
  {
	  this.max = max;
	  matches = new MatchesPO[max];
  }
  
  public String getName()
  {
	 return name;
  }
  public MatchesPO[] getAllMatches()
  {
 	 if (lenth == -1)
 	 {
 		 return null;
 	 }
 	 MatchesPO[] matchespos = new MatchesPO[lenth+1];
      for (int i = 0; i <= lenth; i++)
      {
     	 matchespos[i] = matches[i];
      }
      return matchespos;
  }
  
  public void enQueue(MatchesPO match)
  {
	 if (lenth == max -1)
		 enlarge();
 	 matches[++lenth] = match; 
  }
  
  private void enlarge()
  {
	  int temp_max = max * 2;
	  MatchesPO[] temp = new MatchesPO[temp_max];
	  for (int i = 0 ; i < max; i++)
	  {
		  temp[i] = matches[i];
	  }
	  max = temp_max;
	  matches = temp;
  }
  
  public MatchesPO[] getRecentMatches(int num)
  {
	  MatchesPO[] matches0 = null;
	  if (num > lenth + 1)
	  {
		  matches0 = this.matches;
	  }
	  else 
	  {
		  matches0 = new MatchesPO[num];
		  for (int i = 0; i < num; i++)
		  { 
			  matches0[i] = matches[lenth-num+i+1];
		  }
	  }
	return matches0;
  }
  
}
