package sort;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortTest {

    @Test
    void sort() {
        Sort<Integer> sorter = new MergeSort<>();
        List<Integer> res = sorter.sort(SortTestUtil.genRandArr());
        assert res.equals(SortTestUtil.getExpected());
    }
}