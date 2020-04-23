/*
 * Please, implement a static method getPredicateFromSet in MyUtils class.
 * getPredicateFromSet should take a Set of predicates working with integers as a parameter
 *  and return a predicate with the functionality of all predicates in the set invoked and
 *  connected by logical AND.
 * */

import java.util.function.Predicate;
import java.util.Set;

class MyUtils{
    public static Predicate<Integer> getPredicateFromSet(Set<Predicate<Integer>> conditions) {
        return i -> conditions.stream().allMatch(condition -> condition.test(i));
    }
}
