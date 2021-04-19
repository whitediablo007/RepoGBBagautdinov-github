package geekbrains.study;

public class FillerForThreads implements Runnable {
    private float[] array;
    private int offset;

    public FillerForThreads(float[] array, int offset) {
        this.array = array;
        this.offset = offset;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] *
                    Math.sin(0.2f + (i + offset) / 5f) *
                    Math.cos(0.2f + (i + offset) / 5f) *
                    Math.cos(0.4f + (i + offset) / 2f));
        }

    }
}
