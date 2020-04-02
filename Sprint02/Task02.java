/*
 * https://leetcode.com/problems/reverse-integer/
 * Given a 32-bit signed integer, reverse digits of an integer.
 */
class Task02 {
    public static int reverse(int x) {
        int sign = 1;
        if (x < 0) {
            sign = -1;
            x = -x;
        }
        long reversed = 0;
        while (x != 0) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        reversed *= sign;
        if (reversed < Integer.MIN_VALUE || Integer.MAX_VALUE < reversed) {
            return 0;
        }
        return (int) reversed;
    }

    public static void main(String[] args) {
        int[] arguments = { 123, -123, 120, 0, -2147483648, 901000, 1534236469};
        int[] results   = { 321, -321,  21, 0,           0,    109,          0};
        for (int i = 0; i < arguments.length; i++) {
            int res = reverse(arguments[i]);
            if (res != results[i]) {
                System.out.format("FAIL for '%d': expected '%d' but got '%d'%n", arguments[i], results[i], res);
                return;
            }
        }
        System.out.println("Tests OK");
    }
}
