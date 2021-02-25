package geekbrains.study;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Введите количествол элементов массива для инвертирования значений: ");
        int inputNum = input.nextInt();
        System.out.println("Сгенерирован одномерный массив с " + inputNum + " элементами: ");
        invertElements(numberOfElements(inputNum));
        System.out.println("Введите количествол элементов массива равное 8: ");
        int inputNum_1 = input.nextInt();
        arrayStuff(inputNum_1);
        System.out.println("Введите количествол элементов массива: ");
        int inputNum_2 = input.nextInt();
        System.out.println("Сгенерирован массив с " + inputNum_2 + " элементами: ");
        multipleNumbersLess6(numberOfElements1(inputNum_2));
        System.out.println("Введите размерность двумерного массива, чтобы заполнить его диагональные элементы 1: ");
        int inputNum_3 = input.nextInt();
        diagonalElements(inputNum_3);
        System.out.println("Введите количествол элементов массива для определения максимума и минимума: ");
        int inputNum_4 = input.nextInt();
        maxMinElementsOfArray(numberOfElements1(inputNum_4));
        System.out.println("Введите количество элементов массива для определения границы деления элементов: ");
        int inputNum_5 = input.nextInt();
        System.out.println("Разделить массив на равные суммы элементов: " + checkBalance(numberOfElements(inputNum_5)));
        System.out.println("Введите количество элементов массива: ");
        int inputNum_6 = input.nextInt();
        System.out.println("Введите число для сдвига: ");
        int inputNum_7 = input.nextInt();
        shiftElement(numberOfElements1(inputNum_6), inputNum_7);
        input.close();
    }

    public static int[] numberOfElements(int inputNumber) {
        int[] array = new int[inputNumber];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 2);
        }
        System.out.println(Arrays.toString(array));
        return array;
    }

    public static int[] numberOfElements1(int inputNumber) {
        int[] array = new int[inputNumber];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 12);
        }
        System.out.println(Arrays.toString(array));
        return array;
    }

    //Задание 1
    public static void invertElements(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                array[i] = 0;
            } else {
                array[i] = 1;
            }
            //array[i] = (array[i] + 1) % 2; //так проще, но в задании сказано через условие)))
        }
        System.out.println("Массив с инвертированными элементами имеет вид: ");
        System.out.println(Arrays.toString(array));
    }

    //Задание 2
    public static void arrayStuff(int inputNumber) {
        int[] array = new int[inputNumber];
        for (int i = 0; i < array.length; i++) {
            array[i] = i * 3;
        }
        System.out.println(Arrays.toString(array));
    }

    //Задание 3
    public static void multipleNumbersLess6(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
        System.out.println("Массив с элементами меньше 6, умноженными на 2 имеет вид: ");
        System.out.println(Arrays.toString(array));
    }

    //Задание 4
    public static void diagonalElements(int inputNumber) {
        int[][] array = new int[inputNumber][inputNumber];
        System.out.println("Получили массив " + inputNumber + "x" + inputNumber + " с диагональными элементами = 1: ");
        for (int i = 0; i < inputNumber; i++) {
            for (int j = 0; j < inputNumber; j++) {
                if (i == j) {
                    array[i][j] = 1;
                } else if (j == inputNumber - 1 - i) {
                    array[i][j] = 1;
                }
            }
            System.out.println(Arrays.toString(array[i]));
        }
    }

    //Задание 5
    public static void maxMinElementsOfArray(int[] array) {
        int min = array[0];
        int max = array[0];
        for (int j : array) {
            if (j < min) min = j;
            if (j > max) max = j;
        }
        System.out.println("Максимальный элемент массива = " + max);
        System.out.println("Минимальный элемент массива = " + min);
    }

    // Задание 6
    public static boolean checkBalance(int[] array) {
        int sumLeft, sumRight;
        for (int i = 0; i < array.length + 1; i++) {
            sumLeft = 0;
            sumRight = 0;
            for (int j = 0; j < i; j++) {
                sumLeft += array[j];
            }
            for (int j = i; j < array.length; j++) {
                sumRight += array[j];
            }
            if (sumLeft == sumRight) return true;
        }
        return false;
    }

    // Задание 7
    public static void shiftElement(int[] array, int n) {
        if (n < 0) {
            for (int i = 0; i < Math.abs(n); i++) {
                int buffer = array[0];
                for (int j = 0; j < array.length - 1; j++) {
                    array[j] = array[j + 1];
                    if (j == array.length - 2) array[array.length - 1] = buffer;
                }
            }
        }
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                int buffer = array[array.length - 1];
                for (int j = array.length - 1; j > 0; j--) {
                    array[j] = array[j - 1];
                    if (j == 1) array[0] = buffer;
                }
            }
        }
        System.out.println("Массив, сдвингутый на " + n + " имеет вид: ");
        System.out.println(Arrays.toString(array));
    }
}


