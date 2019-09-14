package tree.btree;

import org.junit.jupiter.api.Test;
import sort.Sort;
import sort.SortTestUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BTreeTest {

    @Test
    public void bTreeTest() {
        BTree<Integer> tree = new BTree<>(3);
        List<Integer> inputs = SortTestUtil.getExpected();
        for (Integer i: inputs) {
            tree.insert(i);
            tree.print();
        }
        tree.delete(1);
        tree.print();
        tree.delete(5);
        tree.print();
        tree.delete(16);
        tree.print();
        tree.delete(9);
        tree.print();
        tree.delete(1);
        tree.print();
    }

    @Test
    public void bTreeTest2() {
        for (int i = 0; i < 30; i++) {
            BTree<Integer> tree = new BTree<>(3);
            List<Integer> inputs = SortTestUtil.genRandArr();
            for (int j: inputs) {
                tree.insert(j);
            }
            tree.print();
            for (int j: inputs) {
                tree.delete(j);
            }
            tree.print();
        }
    }
}