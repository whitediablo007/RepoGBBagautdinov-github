package geekbrains.study;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Random random = new Random();

    static final int ATTEMPT_SIZE = 3;
    static final int RANDOM_RANGE = 10;

    public static void main(String[] args) {
        int randomNumber = random.nextInt(RANDOM_RANGE);
        guessNumber(randomNumber);
        guessWord();
        input.close();
    }

    public static void guessNumber(int randomNumber) {
        int playerAttempt = ATTEMPT_SIZE;
        System.out.println("Введите число от 0 до " + (RANDOM_RANGE - 1) + ": ");

        while (playerAttempt > 0) {
            int inputNumber = input.nextInt();

            if (inputNumber > (RANDOM_RANGE - 1) || inputNumber < 0) {
                System.out.println("Введенное вами число некорректно");
                System.out.println("Введите число от 0 до " + (RANDOM_RANGE - 1) + ": ");
            } else {
                if (randomNumber == inputNumber) {
                    System.out.println("Вы угадали число: " + inputNumber);
                    break;
                } else if (inputNumber < randomNumber) {
                    System.out.println("Вы не угадали, загаданное число больше.");
                } else {
                    System.out.println("Вы не угадали, загаданное число меньше.");
                }
                playerAttempt--;
            }
        }
        System.out.println("Число которое загадал компьютер: " + randomNumber);
    }

    public static void guessWord() {

        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli",
                "carrot", "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom",
                "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        int indexOfWord = random.nextInt(words.length);
        String randomWord = words[indexOfWord];
        int lengthOfRandomWord = randomWord.length();
        System.out.println("ИИ загадал слово, попробуйте отгадать его");
        System.out.println(randomWord);

        do {
            System.out.println("Введи свой ответ: \n" +
                    "\n[Enter] - выход\n");
            String inputAnswer = input.next();
            if (inputAnswer.equals("")) break;
            else if (randomWord.equals(inputAnswer)) {
                System.out.println("Вы угадали!!!");
                break;
            }
            char[] charAnswer = inputAnswer.toCharArray();
            for (int i = 0; i < lengthOfRandomWord; i++) {
                if (i >= charAnswer.length) break;
                if (randomWord.charAt(i) != charAnswer[i]) {
                    charAnswer[i] = '#';
                }
                if (charAnswer.length > lengthOfRandomWord) {
                    for (int j = lengthOfRandomWord; j < charAnswer.length; j++) {
                        charAnswer[j] = '#';

                    }
                }
            }
            StringBuilder hint = new StringBuilder(String.valueOf(charAnswer));
            hint.append("#".repeat(Math.max(0, 15 - hint.length())));
            System.out.println(hint);
        }
        while (true);
    }
}
