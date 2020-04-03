/*
 * https://leetcode.com/problems/sqrtx/
 * Implement int sqrt(int x).
 * Compute and return the square root of x, where x is guaranteed to be a non-negative integer.
 * Since the return type is an integer, the decimal digits are truncated and only the integer part
 * of the result is returned.
 */
class Task04 {
    public static int mySqrt(int x) {
        if (x >= 2147395600) return 46340; // prevent overflow
        int left = 46340 / 2;
        while (left * left > x) {
            left /= 2;
        }
        // binary search
        int right = 2 * left;
        while (right - left > 1) {
            int mid = left + (right - left) / 2;
            if (mid * mid <= x) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return (right * right <= x) ? right : left;
    }

    public static void main(String[] args) {
        int[] arguments = {0, 1, 2, 3, 4, 5, 8, 9, 10, Integer.MAX_VALUE / 2, Integer.MAX_VALUE};
        for (int arg : arguments) {
            int res = mySqrt(arg);
            int res_ref = (int) Math.sqrt(arg); // reference
            if (res != res_ref) {
                System.out.format("FAIL for '%d': expected '%d' but got '%d'%n",
                        arg, res_ref, res);
                return;
            }
        }
        System.out.println("Tests OK");
    }
}
