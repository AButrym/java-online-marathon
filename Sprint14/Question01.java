/*
* Let the key of Map is project name and value contains list of participants.
* Create a Stream<String> nameList(Map<String, Stream<String>> map) method of the MyUtils class to build
*  sorted stream of all participants without duplication.
* Please ignore null or empty strings, extra spaces and case sensitivity.
* Throw NullPointerException if map is null.
* For example, for a given map
{"Desktop"=[" iVan", "PeTro ", " Ira "], "Web"=["STepan", "ira ", " Andriy ", "an na"], "Spring"=["Ivan", "Anna"]}
* you should get
["Andriy", "Anna", "Ira", "Ivan", "Petro", "Stepan"]
* */

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

class MyUtils {
    public Stream<String> nameList(Map<String, Stream<String>> map) {
        Objects.requireNonNull(map);
        return map.values().stream()
                .filter(Objects::nonNull)
                .flatMap(Function.identity())
                .filter(Objects::nonNull)
                .map(s -> s.replaceAll("\\s", ""))
                .filter(Predicate.not(String::isEmpty))
                .map(s -> s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase())
                .distinct()
                .sorted();
    }

    public static void main(String[] args) {
        // smoke test
        var in = Map.of(
                "Desktop", Stream.of(" iVan", "PeTro ", " Ira "),
                "Web", Stream.of("STepan", "ira ", " Andriy ", "an na"),
                "Spring", Stream.of("Ivan", "Anna"));
        var expectedAsArray = new String[] {"Andriy", "Anna", "Ira", "Ivan", "Petro", "Stepan"};
//        var in = Map.of(
//                "Desktop", Stream.of("", " ", "  "),
//                "Web", Stream.of("", "", "", ""),
//                "Spring", Stream.of("", ""));
//        var expectedAsArray = new String[0];
        var out = new MyUtils().nameList(in).toArray();
        System.out.println(Arrays.equals(out, expectedAsArray) ? "OK" : "FAIL");
        System.out.println(Arrays.toString(expectedAsArray));
        System.out.println(Arrays.toString(out));
    }
}
