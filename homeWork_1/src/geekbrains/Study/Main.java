package geekbrains.Study;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // сделал чтобы можно было поработать с методами.
        Scanner input = new Scanner(System.in);
        System.out.println("Введите четыре числа: ");
        float num1 = input.nextFloat();
        float num2 = input.nextFloat();
        float num3 = input.nextFloat();
        float num4 = input.nextFloat();
        System.out.println(calculateNumbers(num1, num2, num3, num4));
        System.out.println("Ведите два числа для определения входа их суммы в диапазон от 10 до 20: ");
        int inputNum1 = input.nextInt();
        int inputNum2 = input.nextInt();
        System.out.println(sumOfNumbersInRange(inputNum1, inputNum2));
        System.out.println("Введите число: ");
        int inputNum3 = input.nextInt();
        positiveOrNegative(inputNum3);
        System.out.println("Введите число: ");
        int inputNum4 = input.nextInt();
        System.out.println("Отрицательно ли число? " + numberIsNegative(inputNum4));
        System.out.println("Введите имя: ");
        String inputName = input.next();
        sayHello(inputName);
        System.out.println("Введите год, узнать високосный он или нет: ");
        int inputYear = input.nextInt();
        leapYearOrNonLeapYear(inputYear);
        input.close();
    }

    private static float calculateNumbers(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }

    private static boolean sumOfNumbersInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    private static void positiveOrNegative(int inputNumber) {
        if (inputNumber >= 0) {
            System.out.println("Вы ввели положительное число.");
        } else {
            System.out.println("Вы ввели отрицательное число.");
        }
    }

    private static boolean numberIsNegative(int inputNumber) {
        return inputNumber < 0;
    }

    private static void sayHello(String inputName) {
        System.out.println("Hello " + inputName + "!");
    }

    private static void leapYearOrNonLeapYear(int inputYear) {
        if (inputYear % 4 == 0 || (inputYear % 400 == 0 && inputYear % 100 == 0)) {
            System.out.println("Год " + inputYear + " високосный.");
        } else {
            System.out.println("Год " + inputYear + " не високосный.");
        }
    }
}
