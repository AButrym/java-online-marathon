/*
Create a int countNumbers(IntStream intNum, Stream<String> strNum) method of the MyUtils class
 to count of numbers that is divisible by 3 or contains the digit 3.
The parameters of the method are two Streams with integers and Strings with one number.
For example, for a given
[[3, 2, 1, 13, 21, 15], ["9", "4", "23", "0", "32", "5"]]
you should get 7
*/

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class MyUtils {
    public long countNumbers(IntStream intNum, Stream<String> strNum) {
        return IntStream.concat(intNum, strNum
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isBlank))
                .mapToInt(Integer::parseInt))
                .filter(i -> i > 0 && i % 3 == 0 || String.valueOf(i).contains("3"))
                .count();
    }

    public static void main(String[] args) {
        IntStream intStream = IntStream.of(3, 2, 1, 13, 21, 15);
        Stream<String> strStream = Stream.of("9", "4", "23", "0", "32", "", null, "5");
        long res = new MyUtils().countNumbers(intStream, strStream);
        System.out.println(res == 7 ? "OK" : "FAIL: " + res);
    }
}
