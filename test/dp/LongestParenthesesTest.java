package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestParenthesesTest {

    @Test
    void longestValidParentheses() {
        String s = "()";
        System.out.println(LongestParentheses.longestValidParentheses(s));
        s = "(()";
        System.out.println(LongestParentheses.longestValidParentheses(s));
        s = "()()()";
        System.out.println(LongestParentheses.longestValidParentheses(s));
        s = "(()))";
        System.out.println(LongestParentheses.longestValidParentheses(s));
    }
}