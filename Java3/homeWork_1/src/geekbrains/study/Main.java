package geekbrains.study;

import geekbrains.study.allFruits.Apple;
import geekbrains.study.allFruits.Orange;

import java.util.*;

public class Main {
    static final int NUMBER_OF_FRUITS = 5;

    public static void main(String[] args) {
        //Task 1,2

        ArrayHandler<String> string = new ArrayHandler<>();
        String[] stringArr = {"Hello", "My", "Darling"};
        System.out.println(string.convertArrayToList(stringArr));
        string.changeIndex(stringArr, 0, 1);
        System.out.println("----------------------------------------");

        ArrayHandler<Integer> num = new ArrayHandler<>();
        Integer[] numArray = {1, 2, 3, 4, 5};
        System.out.println(num.convertArrayToList(numArray));
        num.changeIndex(numArray, 1, 5);
        System.out.println("----------------------------------------");

        ArrayHandler<Character> character = new ArrayHandler<>();
        Character[] charArr = {'h', 'e', 'l', 'l', 'o'};
        System.out.println(character.convertArrayToList(charArr));
        character.changeIndex(charArr, 0, 2);
        System.out.println("----------------------------------------");

        //Task 3

        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        addFruitsInBox(appleBox, orangeBox);
        System.out.println(appleBox.getContentOfFruitBox() + " общий вес яблок в коробке " + appleBox.getWeight());
        System.out.println("----------------------------------------");
        System.out.println(orangeBox.getContentOfFruitBox() + " общий вес апельсинов в коробке " + orangeBox.getWeight());

        Box<Apple> appleBox1 = new Box<>();
        System.out.println("В новой коробке: " + appleBox1.getWeight());
        System.out.println("В старой коробке: " + appleBox.getWeight());
        appleBox.moveToOtherBox(appleBox1);
        System.out.println("После перемещения: ");
        System.out.println("В новой коробке: " + appleBox1.getWeight());
        System.out.println("В старой коробке: " + appleBox.getWeight());

        System.out.println(appleBox.compare(appleBox1));
        System.out.println(appleBox.compare(orangeBox));
    }

    private static void addFruitsInBox(Box<Apple> appleBox, Box<Orange> orangeBox) {
        for (int i = 0; i < NUMBER_OF_FRUITS; i++) {
            appleBox.add(new Apple());
            orangeBox.add(new Orange());
        }
    }
}
