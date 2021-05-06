package geekbrains.study.allFruits;

public class Apple extends Fruit implements FruitService {

    public Apple() {
        super(1.0f, "Яблоко");

    }

    @Override
    public float getWeight() {
        return weight;
    }

}
