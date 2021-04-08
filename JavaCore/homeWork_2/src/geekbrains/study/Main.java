package geekbrains.study;

public class Main {

    private static final int SPACE_NUMBER = 3;

    public static void main(String[] args) {

        //String string = "1 2 3 4\n5 6 7 8\n9 10 11 12\n13 14 15 16";
        //String string = "1 2 3\n4 5 6\n7 8 9\n10 11 12";
        String string = "1 1 2 3\n4 d 6 7\n8 9 10 11\n12 13 14 15";

        String[][] stringArray = convertStringToArray(string);
        System.out.println(sumOfElements(stringArray));
    }

    private static String[][] convertStringToArray(String string) {
        checkArraySizeException(string);

        int columnNumber = string.split("\n").length;

        String[][] resultArray = new String[columnNumber][columnNumber];

        String[] clrRegex = string.split("\n");

        for (int i = 0; i < columnNumber; i++) {
            String[] clearSpaces = clrRegex[i].split(" ");
            System.arraycopy(clearSpaces, 0, resultArray[i], 0, columnNumber);
        }

        return resultArray;
    }

    private static void checkArraySizeException(String string) {
        String buffer = string + "\n";

        int indexCounter = 0, spaceCounter = 0;

        while (indexCounter < buffer.length()) {
            if (buffer.charAt(indexCounter) == ' ') {
                spaceCounter++;
            }
            if (buffer.charAt(indexCounter) == '\n') {
                if (spaceCounter == SPACE_NUMBER) {
                    spaceCounter = 0;
                } else {
                    throw new MyArraySizeException("Несоответсвие размера матрицы 4x4");
                }
            }
            indexCounter++;
        }
    }


    static int sumOfElements(String[][] string) {
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


