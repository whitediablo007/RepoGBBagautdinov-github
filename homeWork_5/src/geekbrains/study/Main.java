package geekbrains.study;

import geekbrains.study.persons.Employee;

public class Main {

    public static void main(String[] args) {

        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Ivanov", "Ivan", "Ivanovich",
                "Director", "ivanov@mailbox.com", 250000, 50);
        employees[1] = new Employee("Smirnov", "Ivan", "Alekseevich", "manager",
                "smirnov@mailbox.com", 100000, 30);
        employees[2] = new Employee("Sidorov", "Peter", "Andreevich",
                "accountant", "sidorov@mailbox.com", 200000, 43);
        employees[3] = new Employee("Vetrov", "Vasilii", "Sergeevich",
                "manager", "vetrov@mailbox.com", 65000, 41);
        employees[4] = new Employee("Durov", "Ivan", "Frodovich",
                "manager", "durov@mailbox.com", 300000, 23);

        for (Employee change : employees) {
            if (change.getAge() > 40) {
                System.out.println(change.getInfoToString());
            }
        }
    }
}

