package geekbrains.study.allAnimals;

public abstract class Animal {

    private String name;
    private String color;
    private int age;

    private static int counter = 0;

    Animal(String name, String color, int age) {
        this.name = name;
        this.color = color;
        this.age = age;

        counter++;
    }

    public void run(int length) {
        if (length < 0) {
            System.out.println(name + " не может бегать отрицательное количество метров");
        } else if ((length > 0) && length <= getMaxRun()) {
            System.out.println(name + " пробежал " + length + " метров.");
        } else {
            System.out.println(name + " не может бегать больше " + getMaxRun() + " метров.");
        }
    }

    abstract void swim(int length);

    public String getName() {
        return name;
    }

    abstract int getMaxRun();

    public static int getCounter() {
        return counter;
    }
}