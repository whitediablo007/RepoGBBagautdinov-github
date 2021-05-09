package geekbrains.study.animal;

import geekbrains.study.food.Plate;

public class Cat {
    private String name;
    private int appetite;
    private boolean catWellFed;


    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.catWellFed = false;
    }

    @Override
    public String toString() {
        return "Cat{ " +
                "name = " + name + '\'' +
                ", appetite = " + appetite +
                ", catWellFed = " + catWellFed +
                '}';
    }

    public int getAppetite() {
        return appetite;w
    }

    public void eat(Plate dose) {
        if (dose.getAmountOfFood() >= appetite) {
            dose.decreaseFood(appetite);
            catWellFed = true;
        }
    }

}
