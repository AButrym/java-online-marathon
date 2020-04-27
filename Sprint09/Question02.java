/*
 * Given a text containing brackets  '(', ')', '{', '}', '[' and ']'.
 * Sequences "\\(", "\\)", "\\[", "\\]", "\\{" and "\\}" are not brackets.
 * Write a boolean verifyBrackets(String text) method of the MyUtils class to check a input text.
 * The brackets must close in the correct order, for example "()", "()[]{}", "{(())}" and "(\\()" are all valid
 *  but "(]", ")(" and "([)]" are not.
 * */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

class MyUtils {
    private static final Map<Character, Character> OPENING = Map.of(
            ']', '[',
            ')', '(',
            '}', '{'
    );

    public boolean verifyBrackets(String text) {
        text = text.replaceAll("\\Q\\\\E[\\Q(){}[]\\E]", "");
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : text.toCharArray()) {
            switch (ch) {
                case '[':
                case '(':
                case '{':
                    stack.push(ch);
                    break;
                case ']':
                case ')':
                case '}':
                    if (stack.isEmpty() || stack.pop() != OPENING.get(ch)) {
                        return false;
                    }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // smoke test
        MyUtils mu = new MyUtils();
        String[] trueTestCases = { "()", "()[]{}", "{(())}", "(\\()"};
        String[] falseTestCases = {"(]", ")(","([)]"};
        for (String s : trueTestCases) {
            if (!mu.verifyBrackets(s)) {
                System.out.println("Expected true, but get false for: " + s);
            }
        }
        for (String s : falseTestCases) {
            if (mu.verifyBrackets(s)) {
                System.out.println("Expected false, but get true for: " + s);
            }
        }
    }
}
