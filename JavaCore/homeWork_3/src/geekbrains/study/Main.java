package geekbrains.study;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main {

    static final String FILE_NAME = "resources\\textFile.txt";

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getStringArray()));
    }

    private static String[] getStringArray() {
        String[] strings = new String[0];
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME), Charset.forName("windows-1251"));
            strings = new String[lines.size()];
            strings = lines.toArray(strings);

            for (int i = 0; i < lines.size(); i++) {
                strings[i] = lines.get(i).toLowerCase(Locale.ROOT);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return strings;
    }
}
