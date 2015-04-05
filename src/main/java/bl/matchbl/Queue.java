package bl.matchbl;

import po.MatchesPO;

public class Queue 
{
  protected MatchesPO[] matches;
  private int max;
  private int lenth;
  public Queue(int max)
  {
	  this.max = max;
	  matches = new MatchesPO[max];
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
  
  
}
