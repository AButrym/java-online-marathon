/*
 * Write a String differentWords(String originalText, String modifyText) method of the MyUtils class
 *  to compare originalText and modifyText.
 * The differentWords() method return the modified text with uppercase of additional words.
 * For example, for a given originalText
Java is a programming language that is class-based and designed to have as few implementation dependencies as possible.
 * and modifyText
Java is a   general-purpose programming   language that is class-based object-oriented and designed to have as few implementation dependencies as possible.
 * you should get
Java is a   GENERAL-PURPOSE programming   language that is class-based OBJECT-ORIENTED and designed to have as few implementation dependencies as possible.
 * */

class MyUtils {
    private static final String WORD_RIGHT_BOUNDARY = "(?<=\\S)(?=\\s)";

    public String differentWords(String originalText, String modifiedText) {
        String[] originalTokens = originalText.split(WORD_RIGHT_BOUNDARY);
        String[] modifiedTokens = modifiedText.split(WORD_RIGHT_BOUNDARY);
        assert modifiedTokens.length >= originalTokens.length;
        StringBuilder res = new StringBuilder();
        int ixOriginal = 0;
        int ixModified = 0;
        while (ixOriginal < originalTokens.length && ixModified < modifiedTokens.length) {
            if (originalTokens[ixOriginal].equals(modifiedTokens[ixModified])) {
                res.append(originalTokens[ixOriginal]);
                ixOriginal++;
                ixModified++;
            } else if (modifiedTokens[ixModified].trim().equals(originalTokens[ixOriginal].trim())) {
                res.append(modifiedTokens[ixModified]); // only spaces differs
                ixOriginal++;
                ixModified++;
            } else {
                res.append(modifiedTokens[ixModified].toUpperCase());
                ixModified++;
            }
        }
        while (ixModified < modifiedTokens.length) {
            res.append(modifiedTokens[ixModified].toUpperCase());
            ixModified++;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        // smoke test
        MyUtils mu = new MyUtils();
        String original = "Java is a programming language that is class-based and designed to have as few implementation dependencies as possible.";
        String modified = "Java is a   general-purpose programming   language that is class-based object-oriented and designed to have as few implementation dependencies as possible.";
        String expected = "Java is a   GENERAL-PURPOSE programming   language that is class-based OBJECT-ORIENTED and designed to have as few implementation dependencies as possible.";
        String result = mu.differentWords(original, modified);
        System.out.println(expected.equals(result) ? "OK" : "FAIL");
        System.out.println(result);
    }
}
