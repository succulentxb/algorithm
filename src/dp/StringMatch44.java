package dp;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StringMatch44 {
    public static boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        if (pLen == 0 && sLen == 0)
            return true;
        else if (pLen == 0)
            return false;
        char pc = p.charAt(0);
        if (sLen == 0 && pc != '*')
            return false;
        char sc = s.charAt(0);
        if (pc == '?' || pc == sc)
            return isMatch(s.substring(1), p.substring(1));
        else if (pc == '*') {
            int pLeftLen = pLen - 1;
            if (pLeftLen == 0)
                return true;
            else {
                int sNextStart = sLen - pLeftLen;
                if (sNextStart < 0)
                    return false;
                return isMatch(s.substring(sNextStart), p.substring(1));
            }
        }
        else
            return false;
    }
}
