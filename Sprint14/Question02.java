/*
Create a Map<String, Stream<String>> phoneNumbers(List<Stream<String>> list)
 method of the MyUtils class to build a Map of all phone numbers.
The key of Map is code of network and value contains sorted list of phones.
Remove all spaces, brackets and dashes from phone numbers.
For example, for a given
[["093 987 65 43", "(050)1234567", "12-345"], ["067-21-436-57", "050-2345678", "0939182736", "224-19-28"], ["(093)-11-22-334", "044 435-62-18", "721-73-45"]]
you should get
{"050"=["1234567", "2345678"], "067"=["2143657"], "093"=["9876543", "9182736", "1122334"], "044"=["4356218"], "loc"=["7217345", "2241928"], "err"=["12345"]}
*/
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MyUtils {
    public Map<String, Stream<String>> phoneNumbers(List<Stream<String>> list) {
        class PhoneNumber {
            String areaCode;
            String number;
            PhoneNumber(String s) {
                s = s.replaceAll("[- )(]", "");
                if (s.matches("\\d{10}")) {
                    areaCode = s.substring(0, 3);
                    number = s.substring(3);
                } else {
                    areaCode = s.matches("\\d{7}") ? "loc" : "err";
                    number = s;
                }
            }
        }
        return list.stream()
                .filter(Objects::nonNull)
                .flatMap(Function.identity())
                .filter(Objects::nonNull)
                .filter(Predicate.not(String::isBlank))
                .map(PhoneNumber::new)
                .collect(Collectors.groupingBy(
                        phoneNumber -> phoneNumber.areaCode,
                        Collectors.mapping(phoneNumber -> phoneNumber.number,
                                Collectors.collectingAndThen(
                                        Collectors.toCollection(TreeSet::new), // sorting
                                        Collection::stream))));
    }

    public static void main(String[] args) {
        // smoke test
        var in = List.of(
                Stream.of("093 987 65 43", "(050)1234567", "12-345"),
                Stream.of("067-21-436-57", "050-2345678", "0939182736", "224-19-28"),
                Stream.of("(093)-11-22-334", "044 435-62-18", "721-73-45"));
        var expected = Map.of(
                "050", Stream.of("1234567", "2345678"),
                "067", Stream.of("2143657"),
                "093", Stream.of("1122334", "9182736", "9876543"),
                "044", Stream.of("4356218"),
                "loc", Stream.of("2241928", "7217345"),
                "err", Stream.of("12345")
        );
        var out = new MyUtils().phoneNumbers(in);
//        out.forEach((k,v) -> System.out.printf("%s : %s%n", k, v.collect(Collectors.joining(", ", "[", "]"))));
//        System.out.println("<<GOT **************** EXPECTED>>");
//        expected.forEach((k,v) -> System.out.printf("%s : %s%n", k, v.collect(Collectors.joining(", ", "[", "]"))));
        /* Streams are for single use, so either print or test */
        boolean res = out.entrySet().stream()
                .allMatch(mapEntry ->
                        expected.containsKey(mapEntry.getKey()) &&
                                Arrays.equals(
                                        expected.get(mapEntry.getKey()).toArray(),
                                        mapEntry.getValue().toArray()
                                )
                );
        System.out.println(res ? "OK" : "FAIL");
    }
}
