package sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SortTestUtilTest {

    @Test
    void genRandArr() {
        for (int i = 0; i < 10; i++) {
            System.out.println(SortTestUtil.genRandArr());
        }
    }
}