package geekbrains.study.allAnimals;

public class Dog extends Animal {
    private final int MAX_LENGTH_RUN = 500;
    private final int MAX_LENGTH_SWIM = 10;

    public Dog(String name, String color, int age) {
        super(name, color, age);
    }

    @Override
    public void swim(int length) {
        if (length < 0) {
            System.out.println(getName() + " не может бегать отрицательное количество метров");
        } else if ((length > 0) && length <= MAX_LENGTH_SWIM) {
            System.out.println(getName() + " проплыл " + length + " метров.");
        }
    }

    @Override
    int getMAX_RUN() {
        return MAX_LENGTH_RUN;
    }
}
