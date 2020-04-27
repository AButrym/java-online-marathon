/*
 * Pig Latin is a spoken "secret code" that many English-speaking children learn.
 * The rules for written Pig Latin are:
 * If a word begins with a consonant cluster, move it to the end and add "ay";
 * If a word begins with a vowel, add "hay" to the end;
 * Write a String pigLatinConverter(String text) method of the MyUtils class to convert input text.
 * For example, for a given text
 * Pig Latin is simply a form of jargon unrelated to Latin.
 * you should get
 * igPay atinLay ishay implysay ahay ormfay ofhay argonjay unrelatedhay otay atinLay.
 * For more information, see https://en.wikipedia.org/wiki/Pig_Latin
 * */

import java.util.regex.Pattern;

class MyUtils {
    private static String convertWord(String word) {
        return word.matches("(?i)^[aeiouy].*")
                ? word + "hay"
                : word.replaceFirst("(?i)([^aeiouy]+)(.*)", "$2$1ay");
    }

    public String pigLatinConverter(String text) {
        return Pattern.compile("\\w+").matcher(text)
                .replaceAll(m -> convertWord(m.group()));
    }

    public static void main(String[] args) {
        // smoke test
        MyUtils mu = new MyUtils();
        String in = "Pig Latin is simply a form of jargon unrelated to Latin. The At";
        String expected = "igPay atinLay ishay implysay ahay ormfay ofhay argonjay unrelatedhay otay atinLay. eThay Athay";
        String out = mu.pigLatinConverter(in);
        System.out.println("in      : " + in);
        System.out.println("out     : " + out);
        System.out.println("expected: " + expected);
        System.out.println(expected.equals(out) ? "OK" : "FAIL");
    }
}
