import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.nullsFirst;
import static java.util.Comparator.comparing;

/*
 * Create classes with name PersonComparator, EmployeeComparator, DeveloperComparator that implement
 *  the Comparator<Type> generic interface.
 * In the Utility class create public static method named sortPeople(...) that takes an array of Person
 *  type and derivative from it types, and comparator as input, and returns the value of void type.
 * Also, as second argument the method sortPeople(...) can takes generic comparator for elements of Object type.
 * The sortPeople(...) method should sorted records by ascending. At first by name, then by age, then by salary,
 *  and then by Level (JUNIOR < MIDDLE < SENIOR)
 * As implementation of sortPeople(...) method use the next code:
 * /return type/ sortPeople(/people/, /comparator/) {
 *      Arrays.sort(/people/, /comparator/);
 * }
 */

class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age;
    }
}

class Employee extends Person {
    private double salary;

    public Employee(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return super.toString() + ", Salary: " + salary;
    }
}

class Developer extends Employee {
    private Level level;

    public Developer(String name, int age, double salary, Level level) {
        super(name, age, salary);
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return super.toString() + ", Level: " + level;
    }
}

enum Level {
    JUNIOR, MIDDLE, SENIOR
}

class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        return nullsFirst(comparing(Person::getName)
                .thenComparingInt(Person::getAge))
                .compare(o1, o2);
    }
}

class EmployeeComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return nullsFirst(comparing(Employee::getName)
                .thenComparingInt(Employee::getAge)
                .thenComparingDouble(Employee::getSalary))
                .compare(o1, o2);
    }
}

class DeveloperComparator implements Comparator<Developer> {
    @Override
    public int compare(Developer o1, Developer o2) {
        return nullsFirst(comparing(Developer::getName)
                .thenComparingInt(Developer::getAge)
                .thenComparingDouble(Developer::getSalary)
                .thenComparing(Developer::getLevel))
                .compare(o1, o2);
    }
}

class Utility {
    public static <T extends Person> void sortPeople(T[] people, Comparator<? super T> comparator) {
        Arrays.sort(people, comparator);
    }
}

class StringComparator implements Comparator<String> {
     @Override
     public int compare(String s1, String s2) { return 0; }
 }

class Main {
    public static void main(String[] args) {
        String[] data = new String[]{ "ca", "ab", "dc", "bd" };
        System.out.println(Arrays.toString(data));
        Utility.sortPeople(data, new StringComparator());
        System.out.println(Arrays.toString(data));
    }
}
