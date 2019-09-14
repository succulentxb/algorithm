package sort;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SortTestUtilTest {

    @Test
    void genRandArr() {
        for (int i = 0; i < 10; i++) {
            System.out.println(SortTestUtil.genRandArr());
        }
    }

    @Test
    void testArr() {
        List<Integer> t = new ArrayList<>(10);
        System.out.println(t.size());
        t.add(1);
        System.out.println(t.size());
        t.add(2);
        System.out.println(t.size());
//        t.set(2, 3);
//        System.out.println(t.size());
        t.add(1, 0);
    }
}