class MyUtils {
    private static final String[] ROMAN = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    private static final int[] DECIMAL = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

    public String intToRoman(int number) {
        if (number < 1 || 3999 < number) {
            throw new IllegalArgumentException("The number should be within [1..3999]");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ROMAN.length && number > 0; i++) {
            int dec = DECIMAL[i];
            while (number >= dec) {
                sb.append(ROMAN[i]);
                number -= dec;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // smoke test
        MyUtils mu = new MyUtils();
        for (int i = 1; i < 40; i++) {
            System.out.println(i + " : " + mu.intToRoman(i));
        }
    }
}
