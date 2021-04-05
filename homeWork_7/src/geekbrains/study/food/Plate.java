package geekbrains.study.food;

public class Plate {
    private int amountOfFood;

    private final int REFILL = 100;

    public Plate(int amountOfFood) {
        this.amountOfFood = amountOfFood;
    }

    @Override
    public String toString() {
        return "Plate{ " +
                "amountOfFood = " + amountOfFood +
                '}';
    }

    public void decreaseFood(int countDecreaseFood) {
        if (amountOfFood >= countDecreaseFood) {
            amountOfFood -= countDecreaseFood;
        }
    }

    public int getAmountOfFood() {
        return amountOfFood;
    }

    public void increaseFood() {
        amountOfFood += REFILL;
    }
}
