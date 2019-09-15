package dp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringMatch44Test {

    @Test
    void isMatch() {
        String p = "*";
        String s = "abd";
        assert StringMatch44.isMatch(s, p);
        p = "*a";
        s = "asdfa";
        assert StringMatch44.isMatch(s, p);
        s = "asdfasdg";
        assert !StringMatch44.isMatch(s, p);

        p = "???";
        s = "abl";
        assert StringMatch44.isMatch(s, p);

        p = "abl";
        assert StringMatch44.isMatch(s, p);

        p = "abc*cba";
        s = "abcba";
        assert !StringMatch44.isMatch(s, p);

        p = "a";
        s = "aa";
        assert !StringMatch44.isMatch(s, p);

        p = "*?*?";
        s = "b";
        assert !StringMatch44.isMatch(s, p);

        p = "*a*b";
        s = "adceb";
        assert StringMatch44.isMatch(s, p);
    }
}