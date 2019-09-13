package sort;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    @Test
    void sort() {
        InsertionSort<Integer> sorter = new InsertionSort<>();
        List<Integer> res = sorter.sort(SortTestUtil.genRandArr());
        assert res.equals(SortTestUtil.getExpected());
    }
}