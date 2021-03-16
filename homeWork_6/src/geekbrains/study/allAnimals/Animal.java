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
            System.out.println(getName() + " не может бегать отрицательное количество метров");
        } else if ((length > 0) && length <= getMAX_RUN()) {
            System.out.println(getName() + " пробежал " + length + " метров.");
        } else {
            System.out.println(getName() + " не может бегать больше " + getMAX_RUN() + " метров.");
        }
    }

    abstract void swim(int length);

    public String getName() {
        return name;
    }

    abstract int getMAX_RUN();

    public static int getCounter() {
        return counter;
    }
}