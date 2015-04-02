package blservice.matchblservice;

import java.sql.Date;
import java.util.Iterator;

import vo.MatchVO;

public interface Matchblservice 
{
  public Iterator<MatchVO> getAllMatches();
  public Iterator<MatchVO> findMatches(Date date1, Date date2);
}
