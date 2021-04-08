package geekbrains.study;

public class Main {

    private static final int ROW_INDEX = 4;
    private static final int COLUMN_INDEX = 4;

    public static void main(String[] args) {

////        String[][] stringArray = {
////                {"1", "2", "3", "4"},
////                {"5", "6", "7", "8"},
////                {"9", "10", "11", "12"},
////                {"13", "14", "15", "16"}
////        };
//
//        String[][] stringArray = {
//                {"1", "2", "3"},
//                {"5", "6", "7",},
//                {"9", "10", "11",}
//        };

        String[][] stringArray = {
                {"1", "2", "3", "4"},
                {"5", "D", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        System.out.println(sumOfElements(stringArray));
    }

    private static void checkArraySizeException(String[][] string) {
        for (String[] strings : string) {
            for (int j = 0; j < strings.length; j++) {
                if (string.length < ROW_INDEX && string[j].length < COLUMN_INDEX) {
                    throw new MyArraySizeException("Несоответсвие размера матрицы 4x4");
                }
            }
        }
        System.out.println("Размер матрицы 4х4");
    }


    static int sumOfElements(String[][] string) {
        checkArraySizeException(string);

        int sum = 0;

        for (int i = 0, sLength = string.length; i < sLength; i++) {
            String[] row1 = string[i];
            for (int j = 0, s1Length = row1.length; j < s1Length; j++) {
                String row2 = row1[j];
                try {
                    sum += Integer.parseInt(row2);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("В строке " + i + ","
                            + " столбце " + j + " матрицы, лежит нечисловое значение.");
                }
            }
        }
        return sum;
    }
}


