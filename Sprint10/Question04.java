/*
Suppose we have the next generic class:

In the class ArrayUtil write static method named "averageValue(...)" that takes an object of Array type as input,
and returns the average value its elements.
The given method should returns value of double type and take any array, whose elements extends Number type.
Examples of usage:

*/

class ArrayUtil {

    public static <T extends Number> double averageValue(Array<T> arr) {
        double sum = 0;
        int len = arr.length();
        for (int i = 0; i < len; i++) {
            sum += arr.get(i).doubleValue();
        }
        return sum / len;
    }
    
}
