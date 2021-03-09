package geekbrains.study;

import geekbrains.study.persons.Employees;

public class Main {

    public static void main(String[] args) {


        Employees[] employees = new Employees[5];
        employees[0] = new Employees("Ivanov", "Ivan", "Ivanovich",
                "Director", "ivanov@mailbox.com", 250000, 50);
        employees[1] = new Employees("Smirnov", "Ivan", "Alekseevich", "manager",
                "smirnov@mailbox.com", 100000, 30);
        employees[2] = new Employees("Sidorov", "Peter", "Andreevich",
                "accountant", "sidorov@mailbox.com", 200000, 43);
        employees[3] = new Employees("Vetrov", "Vasilii", "Sergeevich",
                "manager", "vetrov@mailbox.com", 65000, 41);
        employees[4] = new Employees("Durov", "Ivan", "Frodovich",
                "manager", "durov@mailbox.com", 300000, 23);

        for (Employees change : employees) if (change.getAge() > 40) change.getFullInfo();
    }
}

