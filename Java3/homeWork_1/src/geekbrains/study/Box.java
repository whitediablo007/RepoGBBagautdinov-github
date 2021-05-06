package geekbrains.study;

import geekbrains.study.allFruits.Fruit;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private final ArrayList<T> fruitBox;

    public Box() {
        fruitBox = new ArrayList<>();
    }

    public void add(T fruit) {
        fruitBox.add(fruit);
    }

    public void addFruits(ArrayList<T> fruit) {
        this.fruitBox.addAll(fruit);
    }

    public ArrayList<T> getContentOfFruitBox() {
        return fruitBox;
    }

    public float getWeight() {
        float totalWeight = 0.0f;
        for (T fruit : fruitBox) {
            totalWeight += fruit.getWeight();
        }
        return totalWeight;
    }

    public boolean compare(Box<?> box) {
        return Float.compare(getWeight(), box.getWeight()) == 0;
    }

    public void moveToOtherBox(Box<T> box) {
        box.addFruits(getContentOfFruitBox());
        fruitBox.clear();
    }
}
