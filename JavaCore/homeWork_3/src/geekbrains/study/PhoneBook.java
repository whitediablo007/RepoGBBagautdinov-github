package geekbrains.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    Map<String, String> phoneBook;

    PhoneBook() {
        phoneBook = new HashMap<>();
    }

    void add(String surname, String phone) {
        phoneBook.put(phone, surname);
    }

    List<String> get(String surname) {
        List<String> list = new ArrayList<>();
        phoneBook.forEach((key, value) -> {
            if (surname.equals(value))
                list.add(key);
        });
        return list;
    }

    @Override
    public String toString() {
        return phoneBook.toString();
    }
}
