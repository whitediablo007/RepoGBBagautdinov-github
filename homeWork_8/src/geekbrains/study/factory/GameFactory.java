package geekbrains.study.factory;

import geekbrains.study.core.GameService;
import geekbrains.study.core.impl.GameServiceImpl;
import geekbrains.study.enums.DotType;

public class GameFactory {
    public static GameService getGameService(int mapSize, DotType playerType){
        return new GameServiceImpl(mapSize, playerType);
    }
}
