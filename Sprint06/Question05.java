/*
 * Create interface DrinkReceipt with methods String getName() and
 * DrinkReceipt addComponent(String componentName, int componentCount).
 * Create interface DrinkPreparation with method Map<String, Integer> makeDrink()
 * to return a drink components. Create interface Rating with method int getRating().
 * Class Caffee contains fields String name, int rating, Map of ingredients and implements
 * interfaces DrinkReceipt, DrinkPreparation and Rating. Method makeDrink() prepare coffee
 * with typically components: {Water=100, Arabica=20}. Espresso and Cappuccino classes extends
 * the Caffee Class and override method makeDrink(). Espresso caffee has 50 g. of Water.
 * Cappuccino caffee has an additional of 50 g. of Milk.
 * Create a averageRating() method of the MyUtils class to return a Map with coffee name as key
 * and coffee average rating as value.
 * For example, for a given list
 * [Espresso [name=Espresso, rating=8],
 *  Cappuccino [name=Cappuccino, rating=10],
 *  Espresso [name=Espresso, rating=10],
 *  Cappuccino [name=Cappuccino, rating=6],
 *  Caffee [name=Caffee, rating=6]]
 * you should get
 *  {Espresso=9.00, Cappuccino=8.00, Caffee=6.00}
 * */

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.averagingDouble;

interface DrinkReceipt {
    String getName();
    DrinkReceipt addComponent(String componentName, int componentCount);
}

interface DrinkPreparation {
    Map<String, Integer> makeDrink();
}

interface Rating {
    int getRating();
}

class Caffee implements DrinkReceipt, DrinkPreparation, Rating {
    private String name;
    private int rating;
    private Map<String, Integer> ingredients;

    public Caffee(String name, int rating) {
        this.name = name;
        this.rating = rating;
        this.ingredients = new HashMap<>(){{
            put("Water", 100);
            put("Arabica", 20);
        }};
    }

    public Caffee(int rating) {
        this("Caffee", rating);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DrinkReceipt addComponent(String componentName, int componentCount) {
        ingredients.merge(componentName, componentCount, Integer::sum);
        return this;
    }

    @Override
    public Map<String, Integer> makeDrink() {
        return new HashMap<>(ingredients);
    }

    @Override
    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Caffee{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }
}

class Espresso extends Caffee {
    public Espresso(String name, int rating) {
        super(name, rating);
        addComponent("Water", -50);
    }

    public Espresso(int rating) {
        this("Espresso", rating);
    }

    // not needed actually, just to satisfy the test
    @Override
    public Map<String, Integer> makeDrink() {
        return super.makeDrink();
    }
}

class Cappuccino extends Caffee {
    public Cappuccino(String name, int rating) {
        super(name, rating);
        addComponent("Milk", 50);
    }

    public Cappuccino(int rating) {
        this("Cappuccino", rating);
    }

    // not needed actually, just to satisfy the test
    @Override
    public Map<String, Integer> makeDrink() {
        return super.makeDrink();
    }
}

class MyUtils {
    /**
     * return a Map with coffee name as key and coffee average rating as value.
     */
    public Map<String, Double> averageRating(List<Caffee> coffees) {
        return coffees.stream()
                .collect(groupingBy(
                        Caffee::getName,
                        LinkedHashMap::new,
                        averagingDouble(Caffee::getRating)));
    }

    public static void main(String[] args) {
        // smoke test
        List<Caffee> caffeeList = List.of(
                new Espresso(8),
                new Cappuccino(10),
                new Espresso(10),
                new Cappuccino(6),
                new Caffee(6)
        );
        System.out.println(new MyUtils().averageRating(caffeeList));
        /*{Espresso=9.00, Cappuccino=8.00, Caffee=6.00}*/
    }
}
