package dp;

public class LongestParentheses {
    public static int longestValidParentheses(String s) {
        int sLen = s.length();
        int[] pLens = new int[sLen];
        int i = 1;
        for ( ; i < sLen; i+=1) {
            char curr = s.charAt(i);
            char last = s.charAt(i-1);
            if (curr == ')' && last == '(') {
                if (i-2 >= 0)
                    pLens[i] = pLens[i-2] + 2;
                else
                    pLens[i] = 2;
            }
            else if (curr == ')' && last == ')') {
                int lastValidLen = pLens[i-1];
                if (i-lastValidLen-1 >= 0 && s.charAt(i-lastValidLen-1) == '(')
                    if (i-lastValidLen-2 >= 0)
                        pLens[i] = pLens[i-1] + pLens[i-lastValidLen-2] + 2;
                    else
                        pLens[i] = pLens[i-1] + 2;
            }
        }

        int max = 0;
        for (int j = 0; j < sLen; j++)
            if (pLens[j] > max)
                max = pLens[j];
        return max;
    }
}
