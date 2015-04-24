package test;

import test.data.PlayerHighInfo;
import test.data.PlayerHotInfo;
import test.data.PlayerKingInfo;
import test.data.PlayerNormalInfo;
import vo.PlayerSortBy;
import vo.SortType;


public interface PlayerCoreService {
    public PlayerHighInfo[] getPlayerHighInfos(PlayerSortBy[] sortbys,SortType[] sortTypes,int n);
    public PlayerHotInfo[] getPlayerHotInfos(String field, int n );
    public PlayerKingInfo[] getPlayerSeasonKingInfo(String field, int n);
    public PlayerKingInfo[] getPlayerDailyKingInfo(String field, int n);
    public PlayerNormalInfo[] getPlayerAveNormalInfos(PlayerSortBy[] sortbys, SortType[] sortTypes,int n, String position, String league, int age);
    public PlayerNormalInfo[] getPlayerTotalNormalInfos(PlayerSortBy[] sortbys, SortType[] sortTypes,int n, String position, String league, int age);
}
