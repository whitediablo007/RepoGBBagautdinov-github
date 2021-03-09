package geekbrains.study.persons;

public class Employees {
    private String firstName, name, lastName, position, email;

    private int salary, age;

    public Employees(String firstname,
              String name, String lastName, String position, String email, int salary, int age) {
        this.firstName = firstname;
        this.name = name;
        this.lastName = lastName;
        this.position = position;
        this.email = email;
        this.salary = salary;
        this.age = age;
    }

    public void getFullInfo(){
        System.out.println("firstname: " + firstName + " | name: " + name
                + " | lastname: " + lastName + " | position: " + position
                + " | email: " + email + " | salary: " + salary + " | age: "
                + age);
    }

    public int getAge() {
        return age;
    }
}
