/* 
* https://leetcode.com/problems/palindrome-number/
* Determine whether an integer is a palindrome.
* An integer is a palindrome when it reads the same backward as forward.
*/
class Task01 {
    static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int original = x;
        int reversed = 0;
        while (x != 0) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        return reversed == original;
    }

    public static void main(String[] args) {
        int[] arguments =   { 121,  -121,    10,    0,    1, 1111, 2332,  1234};
        boolean[] results = {true, false, false, true, true, true, true, false};
        for (int i = 0; i < arguments.length; i++) {
            boolean res = isPalindrome(arguments[i]);
            if (res != results[i]) {
                System.out.format("FAIL for '%d': expected '%b' but got '%b'%n", arguments[i], results[i], res);
                return;
            }
        }
        System.out.println("Tests OK");
    }
}
