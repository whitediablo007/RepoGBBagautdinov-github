package geekbrains.study.allFruits;

public abstract class Fruit implements FruitService {
    protected float weight;
    protected String name;

    public Fruit(float weight, String name) {
        this.weight = weight;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " массой: " + weight + "\n";
    }
}
