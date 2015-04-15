package bl.playerbl;

import java.util.Iterator;

import vo.Area;
import vo.PlayerMatchVO;
import vo.PlayerSortBy;

public class main {
public static void main(String[] args)
{
	Player player = new Player();
//	player.getPromotePlayer(PlayerSortBy.assistEfficiency);
//	 Iterator<PlayerMatchVO> itr = player.screenTotalPlayers("G", Area.NORTHWEST, PlayerSortBy.block);
//	 while (itr.hasNext())
//	 {
//		 itr.next();
////		 System.out.println(itr.next());
//	 }
	 PlayerMatchVO[] vos =player.getPromotePlayer(PlayerSortBy.help_uprate);
     for (PlayerMatchVO vo : vos)
     {
  	   System.out.println(vo.getHelp_uprate());
     }
}

}
