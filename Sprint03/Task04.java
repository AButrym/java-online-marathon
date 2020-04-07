import java.util.*;
import java.util.stream.Collectors;

/**
 * Question 4
 *
 * Create a createNotebook() method of the MyUtils class to create a new map with name as key and phone list as value.
 * The method receives a map with phone as key and name as value.
 * For example, for a given map {0967654321=Petro, 0677654321=Petro, 0501234567=Ivan, 0970011223=Stepan, 0631234567=Ivan}
 * you should get {Ivan=[0501234567, 0631234567], Petro=[0967654321, 0677654321], Stepan=[0970011223]}.
 */
class MyUtils {
    public Map<String, List<String>> createNotebook(Map<String, String> phones) {
        if (phones == null || phones.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, List<String>> res = phones.entrySet().stream()
                .filter(mapEntry -> mapEntry.getValue() != null)
                .collect(Collectors.groupingBy(Map.Entry::getValue,
                        HashMap::new,
                        Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
        List<String> nullName = phones.entrySet().stream()
                .filter(me -> me.getValue() == null)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        if (!nullName.isEmpty()) {
            res.put(null, nullName);
        }
        return res;
    }

    public static void main(String[] args) {
        // smoke test
        // {0967654321=Petro, 0677654321=Petro, 0501234567=Ivan, 0970011223=Stepan, 0631234567=Ivan}
        Map<String, String> phones = new HashMap<>() {{
            put("0967654321", "Petro");
            put("0677654321", "Petro");
            put("0501234567", "Ivan");
            put("0970011223", "Stepan");
            put("0631234567", "Ivan");
            put("bad", null);
            put(null, null);
        }};

        var res = new MyUtils().createNotebook(phones);
        System.out.println(res);
        // {null=[null, bad], Ivan=[0501234567, 0631234567], Petro=[0967654321, 0677654321], Stepan=[0970011223]}
    }
}
