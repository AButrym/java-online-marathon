/*
 * In the class ArrayUtil write a public static generic method named "setAndReturn(...)" to modify
 * and return the element in an array from the given position.
*/

class ArrayUtil {
    
    public static <T> T setAndReturn(T[] arr, T setValue, int atIndex) {
        if (0 <= atIndex && atIndex < arr.length) {
            arr[atIndex] = setValue;
            return arr[atIndex];
        }
        return null;
    }
    
}
