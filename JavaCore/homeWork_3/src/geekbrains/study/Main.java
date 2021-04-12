package geekbrains.study;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static final String FILE_NAME = "resources\\textFile.txt";
    static final int PLUS_WORD = 1;

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getStringArray()));
        System.out.println("-------------------------------------------------");
        wordCounter(getStringArray());
        System.out.println("-------------------------------------------------");
        newPhoneBook();
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

    private static void wordCounter(String[] strings) {
        Integer number;
        Map<String, Integer> hashMap = new HashMap<>();
        for (String word : strings) {
            number = hashMap.get(word);
            hashMap.put(word, ((number == null) ? PLUS_WORD : number + PLUS_WORD));
            // вторая реализация
            //hm.put(word, hashMap.getOrDefault(word, 0) + PLUS_WORD);
        }
        System.out.println(hashMap);
        System.out.println("В массиве " + strings.length + " слова " +
                hashMap.size() + " неповторяющихся слов");
    }

    static void newPhoneBook() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Марков", "5533");
        phoneBook.add("Антонов", "5534");
        phoneBook.add("Иванов", "5535");
        phoneBook.add("Ильин", "5536");
        phoneBook.add("Иванов", "5537");
        phoneBook.add("Иванов", "5538");

        System.out.println("Номера Иванова: " + phoneBook.get("Иванов"));
        System.out.println(phoneBook);
    }
}
