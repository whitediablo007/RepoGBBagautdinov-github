package geekbrains.study.core;

import geekbrains.study.core.domain.MatrixCoordinate;
import geekbrains.study.enums.DotType;

public interface GameService {

    void humanTurn(int rowIndex, int columnIndex);

    MatrixCoordinate aiTurn();

    boolean isMapFull();

    boolean checkWin(DotType dotType);

}
