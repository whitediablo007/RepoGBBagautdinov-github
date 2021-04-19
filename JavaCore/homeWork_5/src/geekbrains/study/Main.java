package geekbrains.study;

import java.util.Arrays;

public class Main {
    static final int SIZE = 10000000;
    static final int HALF_SIZE = SIZE / 2;
    static final int QUARTER_SIZE = SIZE / 4;


    public static void main(String[] args) {
        System.out.println("Работает один поток:");
        calculateWithoutThreads(getArray());
        System.out.println("Работает два потока:");
        calculateWithTwoThreads(getArray());
    }

    public static void calculateWithoutThreads(float[] array) {
        long currentTime = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            array[i] = (float) (array[i] *
                    Math.sin(0.2f + i / 5f) *
                    Math.cos(0.2f + i / 5f) *
                    Math.cos(0.4f + i / 2f));
        }
        System.err.println("Массив заполнен за: " +
                (System.currentTimeMillis() - currentTime) / 1000f + " секунд.");
    }

    public static float[] getArray() {
        float[] fullArray = new float[SIZE];
        Arrays.fill(fullArray, 1);
        return fullArray;
    }

    public static void calculateWithTwoThreads(float[] array) {
        long currentTime = System.currentTimeMillis();

        float[] arrayOne = new float[HALF_SIZE];
        float[] arrayTwo = new float[HALF_SIZE];

        System.arraycopy(array, 0, arrayOne, 0, HALF_SIZE);
        System.arraycopy(array, HALF_SIZE, arrayTwo, 0, HALF_SIZE);

        Thread firstThread = new Thread(new FillerForThreads(arrayOne, 0));
        Thread secondThread = new Thread(new FillerForThreads(arrayTwo, HALF_SIZE));

        firstThread.start();
        secondThread.start();

        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.arraycopy(arrayOne, 0, array, 0, HALF_SIZE);
        System.arraycopy(arrayTwo, 0, array, HALF_SIZE, HALF_SIZE);

        System.err.println("Массив заполнен за: " +
                (System.currentTimeMillis() - currentTime) / 1000f + " секунд.");

    }

// Coming soon for quarter thread with collections...
}
