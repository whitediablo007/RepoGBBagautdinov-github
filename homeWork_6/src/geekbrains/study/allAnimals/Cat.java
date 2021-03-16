package geekbrains.study.allAnimals;


public class Cat extends Animal {
    private final int MAX_LENGTH_RUN = 200;

    public Cat(String name, String color, int age) {
        super(name, color, age);
    }

    @Override
    public void swim(int length) {
        System.out.println("Кошки не умеют плавать.");
    }

    @Override
    int getMAX_RUN() {
        return MAX_LENGTH_RUN;
    }

    public int catCounter(int counter){
        counter++;
        return counter;
    }
}