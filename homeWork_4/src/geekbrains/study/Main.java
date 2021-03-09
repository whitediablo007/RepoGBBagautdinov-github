package geekbrains.study;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static char[][] map;
    public static final int SIZE_Y = 5;
    public static final int SIZE_X = 5;
    public static final int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '*';
    public static final char DOT_X = 'X';
    public static final char DOT_0 = '0';
    public static final String SPACE = "|";

    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
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
            aiTurn();
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
        System.out.println("Игра закончена!");
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
        if (map[0][0] == dot && map[0][1] == dot && map[0][2] == dot) return true;
        if (map[1][0] == dot && map[1][1] == dot && map[1][2] == dot) return true;
        if (map[2][0] == dot && map[2][1] == dot && map[2][2] == dot) return true;
        if (map[0][0] == dot && map[1][0] == dot && map[2][0] == dot) return true;
        if (map[0][1] == dot && map[1][1] == dot && map[2][1] == dot) return true;
        if (map[0][2] == dot && map[1][2] == dot && map[2][2] == dot) return true;
        if (map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) return true;
        return map[2][0] == dot && map[1][1] == dot && map[0][2] == dot;
    }

    public static void aiTurn() {
        int x, y;

        do {
            x = random.nextInt(SIZE_X);
            y = random.nextInt(SIZE_Y);
        } while (isCellValid(x, y));
        System.out.println("Компьютер сделал ход в точку " + "[" + (x + 1) + "]" + " " + "[" + (y + 1) + "]");
        setSymbol(y, x, DOT_0);
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты X (от 1 до " + SIZE_X + ")   Y  (от 1 до " + SIZE_Y + ")");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (isCellValid(x, y));
        setSymbol(y, x, DOT_X);
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE_X || y < 0 || y >= SIZE_Y) {
            System.out.println("Вы ввели некорректную точку.");
            return true;
        }
        if (map[y][x] == DOT_0 || map[y][x] == DOT_X) {
            System.out.println("Эта точка занята!!!");
            return true;
        }
        if (map[y][x] == DOT_EMPTY) return false;
        return true;
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
