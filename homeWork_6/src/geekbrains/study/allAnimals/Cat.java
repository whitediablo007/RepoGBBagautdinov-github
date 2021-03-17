package geekbrains.study.allAnimals;


public class Cat extends Animal {
    private final int MAX_LENGTH_RUN = 200;

    private static int catCounter = 0;

    public Cat(String name, String color, int age) {
        super(name, color, age);

        catCounter++;
    }

    @Override
    public void swim(int length) {
        System.out.println(getName() + " не умеет плавать.");
    }

    @Override
    int getMaxRun() {
        return MAX_LENGTH_RUN;
    }

    public static int getCatCounter() {
        return catCounter;
    }
}