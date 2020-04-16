/* Create classes Employee (fields String name, int experience and BigDecimal basePayment)
 * and Manager (field double coefficient) with methods which return the general working experience
 * and payment for work done.
 * A getter methods of class Employee return value of all fields: getName(), getExperience() and getPayment().
 * Classes Manager is derived from class Employee and override getPayment() method to return multiplication
 * of a coefficient and base payment.
 * Create a largestEmployees() method of the MyUtils class to return a List of unique employees
 * with maximal working experience and payment without duplicate objects.
 * The original list must be unchanged.
 * For example, for a given list
 * [Employee [name=Ivan, experience=10, basePayment=3000.00],
 *  Manager [name=Petro, experience=9, basePayment=3000.00, coefficient=1.5],
 *  Employee [name=Stepan, experience=8, basePayment=4000.00],
 *  Employee [name=Andriy, experience=7, basePayment=3500.00],
 *  Employee [name=Ihor, experience=5, basePayment=4500.00],
 *  Manager [name=Vasyl, experience=8, basePayment=2000.00, coefficient=2.0]]
 * you should get
 * [Employee [name=Ivan, experience=10, basePayment=3000.00],
 *  Manager [name=Petro, experience=9, basePayment=3000.00, coefficient=1.5],
 *  Employee [name=Ihor, experience=5, basePayment=4500.00]].
*/

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Employee {
    private String name;
    private int experience;
    private BigDecimal basePayment;

    public Employee(String name, int experience, BigDecimal basePayment) {
        this.name = name == null ? "" : name;
        this.experience = experience;
        this.basePayment = basePayment == null ? BigDecimal.ZERO : basePayment;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }

    public BigDecimal getPayment() {
        return basePayment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return experience == employee.experience &&
                Objects.equals(name, employee.name) &&
                Objects.equals(basePayment, employee.basePayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, experience, basePayment);
    }

    @Override
    public String toString() {
        return String.format("Employee [name=%s, experience=%d, basePayment=%.2f]",
                name, experience, basePayment.doubleValue());
    }
}

class Manager extends Employee {
    private double coefficient;

    public Manager(String name, int experience, BigDecimal basePayment, double coefficient) {
        super(name, experience, basePayment);
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    @Override
    public BigDecimal getPayment() {
        return super.getPayment().multiply(new BigDecimal(coefficient));
    }

    @Override
    public String toString() {
        return String.format("Manager [name=%s, experience=%d, basePayment=%.2f, coefficient=%.1f]",
                getName(), getExperience(), super.getPayment().doubleValue(), coefficient);
    }
}

class MyUtils {
    public List<Employee> largestEmployees(List<Employee> workers) {
        if (workers == null || workers.isEmpty()) {
            return List.of();
        }
        BigDecimal maxPayment = workers.stream()
                .filter(Objects::nonNull)
                .map(Employee::getPayment)
                .reduce(BigDecimal.ZERO, BigDecimal::max);
        int maxExperience = workers.stream()
                .filter(Objects::nonNull)
                .mapToInt(Employee::getExperience)
                .max().orElse(0);
        final BigDecimal threshold = new BigDecimal("0.001");
        Predicate<Employee> filter = employee ->
                employee.getExperience() == maxExperience ||
                employee.getPayment().subtract(maxPayment).abs().compareTo(threshold) < 0;
        return workers.stream()
                .filter(Objects::nonNull)
                .filter(filter)
                .distinct()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // smoke test
        List<Employee> employeeList = Arrays.asList(
                new Employee("Ivan", 10, new BigDecimal("3000.00")),
                new Manager("Petro", 9, new BigDecimal("3000.00"), 1.5),
                new Employee("Stepan", 8, new BigDecimal("4000.00")),
                new Employee("Andriy", 7, new BigDecimal("3500.00")),
                new Employee("Ihor", 5, new BigDecimal("4500.00")),
                new Manager("Vasyl", 8, new BigDecimal("2000.00"), 2.0),
                null
        );
        new MyUtils().largestEmployees(null).forEach(System.out::println);

        // test null
        List<Employee> originList = new ArrayList<>();
        originList.add(null);
        List<Employee> actual = new MyUtils().largestEmployees(originList);
        actual.forEach(System.out::println);
    }
}
