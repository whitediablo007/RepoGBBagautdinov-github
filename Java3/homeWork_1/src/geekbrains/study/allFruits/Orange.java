package geekbrains.study.allFruits;

public class Orange extends Fruit implements FruitService {

    public Orange() {
        super(1.5f,"Апельсин");
    }

    @Override
    public float getWeight() {
        return weight;
    }

}
