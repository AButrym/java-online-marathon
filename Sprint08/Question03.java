/*
 * Please, add to class App static BinaryOperator field greetingOperator.
 * greetingOperator should work with strings. It forms a new string as a result by
 * the rule: "Hello " + parameter1 + " " + parameter2 + "!!!"
 * Create a static method createGreetings that takes two parameters: List<Person> and BinaryOperator
 * and generates List<String> as a result. We should be able to pass greetingOperator as a parameter here.
 * Please, use the second parameter in creating the result.
 * */

import java.util.function.BinaryOperator;
import java.util.List;
import java.util.stream.Collectors;

class Person {
    String name;
    String surname;

    Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}

class App {
    public static BinaryOperator<String> greetingOperator = (parameter1, parameter2) ->
            "Hello " + parameter1 + " " + parameter2 + "!!!";

    public static List<String> createGreetings(List<Person> people, BinaryOperator<String> operator) {
        return people.stream()
                .map(person -> operator.apply(person.name, person.surname))
                .collect(Collectors.toList());
    }
}
