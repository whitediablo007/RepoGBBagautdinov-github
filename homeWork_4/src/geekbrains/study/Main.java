package geekbrains.study;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static char[][] map;
    public static final int SIZE_Y = 3;
    public static final int SIZE_X = 3;
    public static final int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_0 = '0';
    public static final String SPACE = "|";
    public static int CHANGE_DIFFICULT;

    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
        platyGame();
    }

    public static void platyGame() {
        do {
            System.out.println("Выберите сложность игры: \n [1] - Простая \n \n [2] - ИГРА СО СВЕРХРАЗУМОМ\n");
            CHANGE_DIFFICULT = sc.nextInt();
            initMap();
            printMap();
            while (true) {
                humanTurn();
                printMap();

                if (checkWin(DOT_X)) {
                    System.out.println("Вы победили!");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    break;
                }


                if (CHANGE_DIFFICULT != 1) aiTurn();
                else aiTurnPast();
                printMap();

                if (checkWin(DOT_0)) {
                    System.out.println("Победил СВЕРХРАЗУМ!");
                    break;
                }
                if (isMapFull()) {
                    System.out.println("Ничья");
                    break;
                }
            }
            System.out.println("---------------------------------");
            System.out.print("Хотите ли сыграть снова? (y/n) -> ");

        } while (sc.next().equals("y"));
        sc.close();

    }

    public static void setSymbol(int y, int x, char symbol) {
        map[y][x] = symbol;
    }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkWin(char dot) {
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                if (verifyLine(i, j, 0, 1, dot)) return true;
                if (verifyLine(i, j, 1, 1, dot)) return true;
                if (verifyLine(i, j, 1, 0, dot)) return true;
                if (verifyLine(i, j, -1, 1, dot)) return true;
            }
        }
        return false;
    }

    public static boolean verifyLine(int indexY, int indexX, int freezeOrRunY, int freezeOrRunX, char symbol) {
        int wayY = indexY + (DOTS_TO_WIN - 1) * freezeOrRunY;
        int wayX = indexX + (DOTS_TO_WIN - 1) * freezeOrRunX;
        if (wayX < 0 || wayY < 0 || wayX > SIZE_X - 1 || wayY > SIZE_Y - 1) return false;
        for (int i = 0; i < DOTS_TO_WIN; i++) {
            int elementY = indexY + i * freezeOrRunY;
            int elementX = indexX + i * freezeOrRunX;
            if (map[elementY][elementX] != symbol) return false;
        }
        return true;
    }

    public static void aiTurn() {
        for (int i = 0; i < SIZE_Y; i++)
            for (int j = 0; j < SIZE_X; j++) {
                if (isCellValid(i, j)) {
                    setSymbol(i, j, DOT_0);
                    if (checkWin(DOT_0)) {
                        return;
                    }
                    setSymbol(i, j, DOT_EMPTY);
                }
            }
        for (int i = 0; i < SIZE_Y; i++)
            for (int j = 0; j < SIZE_X; j++) {
                if (isCellValid(i, j)) {
                    setSymbol(i, j, DOT_X);
                    if (checkWin(DOT_X)) {
                        setSymbol(i, j, DOT_0);
                        return;
                    }
                    setSymbol(i, j, DOT_EMPTY);
                }
            }
        int x;
        int y;
        do {
            x = random.nextInt(SIZE_X);
            y = random.nextInt(SIZE_Y);
        } while (!isCellValid(y, x));
        setSymbol(y, x, DOT_0);
    }

    public static void aiTurnPast() {
        int x;
        int y;
        do {
            x = random.nextInt(SIZE_X);
            y = random.nextInt(SIZE_Y);
        } while (!isCellValid(y, x));
        setSymbol(y, x, DOT_0);
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты X (от 1 до " + SIZE_X + ")   Y  (от 1 до " + SIZE_Y + ")");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));
        setSymbol(y, x, DOT_X);
    }

    public static boolean isCellValid(int y, int x) {
        if (x < 0 || y < 0 || x > SIZE_X - 1 || y > SIZE_Y - 1) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void initMap() {
        map = new char[SIZE_Y][SIZE_X];
        for (int i = 0; i < SIZE_Y; i++) {
            for (int j = 0; j < SIZE_X; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE_Y; i++) {
            System.out.print(i + SPACE);
        }
        System.out.println();
        for (int i = 0; i < SIZE_Y; i++) {
            System.out.print((i + 1) + SPACE);
            for (int j = 0; j < SIZE_X; j++) {
                System.out.print(map[i][j] + SPACE);
            }
            System.out.println();
        }
        System.out.println();
    }
}
