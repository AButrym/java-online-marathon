import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Question 2
 *
 * Create the strSort() method of the MyUtils class to sort a list of stirngs first by length
 * and alphabetically within the same length. The original list must be unchanged.
 * For example, for a given list [zz, abc, aa, aaa] you should get [aa, zz, aaa, abc].
 */
class MyUtils {
    public List<String> strSort(List<String> originList) {
        return originList.stream()
                .sorted(Comparator.comparing(String::length)
                .thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> arr = List.of("zz", "abc", "aa", "aaa");
        System.out.println(arr);
        List<String> sortedArr = new MyUtils().strSort(arr);
        System.out.println(sortedArr); // should get [aa, zz, aaa, abc]
        System.out.println(arr); // should be unchanged
    }
}
