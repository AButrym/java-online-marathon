/*
 * Write a String reformatLines(String text) method of the MyUtils class to replacing all spaces
 *  with one and reformat lines of input text.
 * Length of every lines should not exceed 60 characters.
 * For example, for a given text
Java    was      originally developed
   by    James   Gosling at Sun Microsystems (which
 has since been
acquired by Oracle) and released in 1995
 as a core component of Sun Microsystems' Java platform.
 * you should get
Java was originally developed by James Gosling at Sun
Microsystems (which has since been acquired by Oracle) and
released in 1995 as a core component of Sun Microsystems'
Java platform.
 * */
class MyUtils {
    private static final int MAX_LINE_LENGTH = 60;
    private static final String LINE_SEPARATOR = "\n";//System.lineSeparator();

    public String reformatLines(String text) {
        if (text == null || text.length() == 0) {
            return text;
        }
        StringBuilder line = new StringBuilder(MAX_LINE_LENGTH);
        StringBuilder res = new StringBuilder();
//        if (text.substring(0, 1).matches("\\s")) {
//            res.append(" "); // leading space
//        }
        for (String word : text.trim().split("\\s+")) {
            if (line.length() + word.length() > MAX_LINE_LENGTH) {
                // remove extra space
                res.append(line.substring(0, line.length() - 1));
                res.append(LINE_SEPARATOR);
                line.setLength(0);
            }
            line.append(word);
            line.append(" ");
        }
        if (line.length() > 0) {
            res.append(line.substring(0, line.length() - 1));
        } else {
            // remove extra line break
            res.setLength(res.length() - LINE_SEPARATOR.length());
        }
//        if (text.substring(text.length() - 1).matches("\\s")) {
//            res.append(" "); // trailing space
//        }
        return res.toString();
    }

    public static void main(String[] args) {
        // smoke test
        MyUtils mu = new MyUtils();
        String in = "   Java    was      originally developed\n" +
                "   by    James   Gosling at Sun Microsystems (which\n" +
                " has since been\n" +
                "acquired by Oracle) and released in 1995\n" +
                " as a core component of Sun Microsystems' Java platform.   ";
        String expected = "Java was originally developed by James Gosling at Sun\n" +
                "Microsystems (which has since been acquired by Oracle) and\n" +
                "released in 1995 as a core component of Sun Microsystems'\n" +
                "Java platform.";
        String out = mu.reformatLines(in);
        System.out.println(out);
        System.out.println(expected.equals(out) ? "OK" : "FAIL");
    }
}
