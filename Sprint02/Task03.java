/*
 * https://leetcode.com/problems/divide-two-integers/
 * Given two integers dividend and divisor, divide two integers
 * without using multiplication, division and mod operator.
 * Return the quotient after dividing dividend by divisor.
 * The integer division should truncate toward zero, which means losing its fractional part.
 * For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.
 */
class Task03 {
    public static int divide(int dividend, int divisor) {
        // treat possible overflow in a prescribed way
        if (dividend == Integer.MIN_VALUE && divisor == -1) { return Integer.MAX_VALUE; }
        // treat division by zero to prevent infinite looping
        if (divisor == 0) { return 0; }
        // save the sign
        boolean isNegative = (dividend < 0) ^ (divisor < 0);
        // convert both to negative to use full range
        long dd = dividend > 0 ? -dividend : dividend;
        long dr = divisor > 0 ? -divisor : divisor;

        int res = 0;
        while (dd <= dr) { // (both are negative!) there is nonzero quotient
            int tb = -1; // find the top bit s.t.: |2**(tb-1) * dr| <= |dd| < |2**tb * dr|
            while (dr << ++tb >= dd && tb < 32) { }
            res += 1 << --tb;
            dd -= dr << tb;
        }
        return isNegative ? -res : res;
    }

    public static void main(String[] args) {
        int[] argument1 = {10, 7, -7, -7,  0, -2147483648, 2147483647, -2147483648};
        int[] argument2 = {3, -3,  3, -3, 10,           1,          1,           2};
        for (int i = 0; i < argument1.length; i++) {
            int res = divide(argument1[i], argument2[i]);
            int res_ref = argument1[i] / argument2[i];
            if (res != res_ref) {
                System.out.format("FAIL for '%d, %d': expected '%d' but got '%d'%n",
                        argument1[i], argument2[i], res_ref, res);
                return;
            }
        }
        System.out.println("Tests OK");
    }
}
