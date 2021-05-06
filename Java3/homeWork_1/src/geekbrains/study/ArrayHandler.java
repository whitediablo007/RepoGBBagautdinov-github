package geekbrains.study;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayHandler<T> {
    public void changeIndex(T[] array, int firstInd, int secondInd) {
        if ((firstInd >= 0 && firstInd < array.length) && (secondInd >= 0 && secondInd < array.length)) {
            T arrayIndCopy = array[firstInd];
            array[firstInd] = array[secondInd];
            array[secondInd] = arrayIndCopy;
            System.out.println(Arrays.toString(array));
        } else {
            System.out.println("Вы вышли за пределы индексов массива!!!");
        }
    }

    public ArrayList<T> convertArrayToList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }
}

