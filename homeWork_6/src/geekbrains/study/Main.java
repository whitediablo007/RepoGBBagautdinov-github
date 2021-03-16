package geekbrains.study;

import geekbrains.study.allAnimals.Cat;
import geekbrains.study.allAnimals.Dog;

public class Main {

    public static void main(String[] args) {
        Cat cat = new Cat("Барсик", "Черный", 3);
        Dog dog = new Dog("Арчи", "Белый", 5);

        cat.swim(200);
        dog.swim(5);

        cat.run(300);
        dog.run(600);

    }
}
