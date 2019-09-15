import dp.StringMatch44;
import jdk.nashorn.internal.runtime.regexp.RegExp;
import org.junit.jupiter.api.Test;
import sun.misc.Regexp;

import java.io.*;
import java.util.regex.Pattern;

public class APITest {
    @Test
    public void ioTest() throws Exception {
        File f = new File("test_file");
        BufferedReader br = new BufferedReader(new FileReader(f));
        String c = "";
        String s;
        while ((s = br.readLine()) != null)
            c += s+"\n";
        String p = ".*";
        System.out.println(c);
        System.out.println(Pattern.matches(p, "abc"));
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        c = c.replaceAll("World", "Algorithms");
        System.out.println(c);
        bw.write(c);
        bw.close();
    }

    @Test
    public void sbTest() {
        StringBuffer sb = new StringBuffer("1");
        sb.append("23");
        sb.append("4");
        sb.append('5');
        System.out.println(sb);
        System.out.println(sb.toString());
    }
}
