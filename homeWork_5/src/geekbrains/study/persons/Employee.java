package geekbrains.study.persons;


public class Employee {
    private String firstName;
    private String name;
    private String lastName;
    private String position;
    private String email;

    private int salary;
    private int age;

    public Employee(String firstname,
                    String name, String lastName, String position, String email, int salary, int age) {
        this.firstName = firstname;
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.salary = salary;
        this.age = age;
    }

    public void getFullInfo() {
        System.out.println("Фамилия: " + firstName + " | Имя: " + name
                + " | Отчество: " + lastName + " | Должность: " + position
                + " | email: " + email + " | Зарплата: " + salary + " | Возраст: "
                + age);
    }

    public String getInfoToString() {
        return String.format("Фамилия: %s \tИмя: %s \tОтчество: %s \tДолжность: %s \nEmail: %s \n Зарплата: %d \n Возраст: %d \n",
                firstName, name, lastName, position, email, salary, age);
    }

    public int getAge() {
        return age;
    }
}
