package bl.playerbl;

import vo.Area;
import vo.PlayerSortBy;

public class main {
public static void main(String[] args)
{
	Player player = new Player();
//	player.getPromotePlayer(PlayerSortBy.assistEfficiency);
	player.screenTotalPlayers("G", Area.NORTHWEST, PlayerSortBy.block);
}
}
