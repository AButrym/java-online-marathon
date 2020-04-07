import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Question 3
 *
 * Create a listMapCompare() method of the MyUtils class to compare the contents of a list of strings and the values of a map.
 * For example, for a given list [aa, bb, aa, cc] and map {1=cc, 2=bb, 3=cc, 4=aa, 5=cc} you should get true.
 */
class MyUtils {
    public boolean listMapCompare(List<String> list, Map<String, String> map) {
        return new HashSet<>(list).equals(new HashSet<>(map.values()));
    }

    public static void main(String[] args) {
        // smoke test
        List<String> list = List.of("aa", "bb", "aa", "cc");
        Map<String, String> map = Map.of(
                "1", "cc",
                "2", "bb",
                "3", "cc",
                "4", "aa",
                "5", "cc");
        System.out.println(new MyUtils().listMapCompare(list, map)); // expect: true
    }
}
