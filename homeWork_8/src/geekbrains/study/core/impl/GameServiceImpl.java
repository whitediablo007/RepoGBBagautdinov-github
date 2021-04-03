package geekbrains.study.core.impl;


import geekbrains.study.core.domain.MatrixCoordinate;
import geekbrains.study.core.GameService;
import geekbrains.study.enums.DotType;

import java.util.Random;

public class GameServiceImpl implements GameService {
    private int dotsToWin;
    private int size;

    public DotType[][] map;
    public DotType playerType;
    public DotType aiType;

    public Random rand = new Random();

    public GameServiceImpl(int mapSize, DotType playerType) {
        this.size = mapSize;
        this.dotsToWin = mapSize;

        this.playerType = playerType;
        this.aiType = DotType.getEnemyType(playerType);

        initMap();
    }

    public boolean checkWin(DotType dotType) {
        int lastIndex = map.length - 1;

        int mainDiagonal = 0;
        int sideDiagonal = 0;
        for (int i = 0; i < map.length; i++) {

            int rowCount = 0;
            int columnCount = 0;

            for (int j = 0; j < map.length; j++) {
                rowCount = getWinValue(i, j, rowCount, dotType);
                columnCount = getWinValue(j, i, columnCount, dotType);

                if (rowCount == dotsToWin || columnCount == dotsToWin) {
                    return true;
                }
            }

            mainDiagonal = getWinValue(i, i, mainDiagonal, dotType);
            sideDiagonal = getWinValue(i, lastIndex - i, sideDiagonal, dotType);

            if (mainDiagonal == dotsToWin || sideDiagonal == dotsToWin) {
                return true;
            }
        }

        return false;
    }

    public int getWinValue(int indRow, int indCol, int value, DotType dotType) {
        if (map[indRow][indCol] == dotType) {
            return value + 1;
        }

        return 0;
    }

    public boolean isMapFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == DotType.EMPTY) return false;
            }
        }
        return true;
    }

    public MatrixCoordinate aiTurn() {
        int x, y;
        do {
            y = rand.nextInt(size);
            x = rand.nextInt(size);
        } while (!isCellValid(x, y));

        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = aiType;
        printMap();
        return new MatrixCoordinate(y, x);
    }

    public boolean isCellValid(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) return false;
        return map[y][x] == DotType.EMPTY;
    }

    public void humanTurn(int rowIndex, int columnIndex) {
        map[rowIndex][columnIndex] = playerType;
        printMap();
    }

    public void initMap() {
        map = new DotType[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = DotType.EMPTY;
            }
        }
    }

    public void printMap() {
        for (int i = 0; i <= size; i++) {
            System.out.print(i + " ");
        }

        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}