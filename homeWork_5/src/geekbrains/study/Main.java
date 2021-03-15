package geekbrains.study;

import geekbrains.study.persons.Employee;

public class Main {

    public static void main(String[] args) {

        Employee[] employee = new Employee[5];
        employee[0] = new Employee("Ivanov", "Ivan", "Ivanovich",
                "Director", "ivanov@mailbox.com", 250000, 50);
        employee[1] = new Employee("Smirnov", "Ivan", "Alekseevich", "manager",
                "smirnov@mailbox.com", 100000, 30);
        employee[2] = new Employee("Sidorov", "Peter", "Andreevich",
                "accountant", "sidorov@mailbox.com", 200000, 43);
        employee[3] = new Employee("Vetrov", "Vasilii", "Sergeevich",
                "manager", "vetrov@mailbox.com", 65000, 41);
        employee[4] = new Employee("Durov", "Ivan", "Frodovich",
                "manager", "durov@mailbox.com", 300000, 23);

        for (Employee change : employee) {
            if (change.getAge() > 40) {
                System.out.println(change.getInfoToString());
            }
        }
    }
}

