package geekbrains.study;

public class MyArrayDataException extends NumberFormatException {
    private String exceptionMessage;

    public MyArrayDataException(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    @Override
    public String toString() {
        return "geekBrains.study.MyArrayDataException: " + exceptionMessage;
    }
}
