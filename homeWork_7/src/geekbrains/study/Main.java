package geekbrains.study;

import geekbrains.study.animal.Cat;
import geekbrains.study.food.Plate;

public class Main {

    public static void main(String[] args) {
        Cat[] cats = {
                new Cat("Барсик", 35),
                new Cat("Мурзик", 45),
                new Cat("Том", 20),
                new Cat("Стич", 25)};
        Plate plate = new Plate(100);

        System.out.println("Кошки начали есть:");
        System.out.println("-----------------------------------------------------");

        for (Cat cat : cats) {
            if (plate.getAmountOfFood() < cat.getAppetite()) {
                plate.increaseFood();
                System.out.println("Еды осталось мало, добавлена еда в тарелку.");
                System.out.println(plate);
                System.out.println("-------------------------------------------------------");
            }
            cat.eat(plate);
            System.out.println(cat);
            System.out.println(plate);
            System.out.println("-----------------------------------------------------");
        }
    }
}
