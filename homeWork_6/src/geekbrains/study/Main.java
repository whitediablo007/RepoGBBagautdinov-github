package geekbrains.study;

import geekbrains.study.allAnimals.Animal;
import geekbrains.study.allAnimals.Cat;
import geekbrains.study.allAnimals.Dog;

public class Main {

    public static void main(String[] args) {
        Cat cat = new Cat("Барсик", "Черный", 3);
        Dog dog = new Dog("Арчи", "Белый", 5);

        Cat cat1 = new Cat("Oro", "Red", 6);

        cat.swim(200);
        dog.swim(5);

        cat.run(20);
        dog.run(-2);

        System.out.println("Создано " + Animal.getCounter() + " животных.");
        System.out.println("Создано " + Cat.getCatCounter() + " котов.");
        System.out.println("Создано " + Dog.getDogCounter() + " собак.");

    }
}
