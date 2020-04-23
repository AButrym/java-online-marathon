/*
 * Please, create a static Consumer implementation cons that takes an array of doubles as a parameter
 * and changes it using the next rule: all values that are greater than 2 should be multiplied by 0.8,
 * and other values should be multiplied by 0.9.
 * Also, please. implement a static method getChanged that takes an array of doubles as a first parameter
 * and  Consumer as a second. The method should return an array changed by the second parameter.
 * getChanged method should not change initial array.
 * */

import java.util.Arrays;
import java.util.function.Consumer;

class App {

    public static Consumer<double[]> cons = arr -> {
        for (int i = 0; i < arr.length; i++) {
            double x = arr[i];
            arr[i] = x > 2 ? 0.8 * x : 0.9 * x;
        }
    };

    public static double[] getChanged(double[] initialArray, Consumer<double[]> consumer) {
        double[] res = Arrays.copyOf(initialArray, initialArray.length);
        consumer.accept(res);
        return res;
    }
}
