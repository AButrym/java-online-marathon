class Scratch {
  public static void main(String[] args) {
    Employee emp1 = new Employee();
    emp1.fullName = "Smith";
    emp1.salary = 50_000;

    Employee emp2 = new Employee();
    emp2.fullName = "Wesson";
    emp2.salary = 60_000;

    Employee[] employees = {emp1, emp2};

    String employeesInfo =
      java.util.Arrays.stream(employees)
        .map(emp -> "{fullName: \"" + emp.fullName + "\", salary: " + emp.salary + "}")
        .collect(java.util.stream.Collectors.joining(", ", "[", "]"));
  }
}

class Employee {
  public String fullName;
  public float salary;
}
