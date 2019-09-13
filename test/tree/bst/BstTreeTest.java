package tree.bst;

import org.junit.jupiter.api.Test;
import sort.SortTestUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BstTreeTest {
    @Test
    public void treeTest() {
        List<Integer> inputs = SortTestUtil.genRandArr();
        BstTree<Integer> tree = new BstTree<>();
        for (Integer x: inputs) {
            tree.print();
            tree.insert(x);
        }
        tree.delete(1);
        tree.print();
        tree.delete(5);
        tree.print();
    }
}