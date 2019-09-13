package tree.avl;

import org.junit.jupiter.api.Test;
import sort.SortTestUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    public void avlTreeTest() {
        List<Integer> inputs = SortTestUtil.getExpected();
        AVLTree<Integer> tree = new AVLTree<>();
        for (Integer x: inputs) {
            tree.print();
            tree.insert(x);
        }
        tree.delete(1);
        tree.print();
        tree.delete(5);
        tree.print();
    }

    @Test
    public void avlTest() {
        for (int i = 0; i < 100; i++) {
            List<Integer> inputs = SortTestUtil.genRandArr();
            AVLTree<Integer> tree = new AVLTree<>();
            for (Integer x: inputs) {
                tree.insert(x);
                tree.print();
            }
            tree.delete(3);
            tree.print();
            tree.delete(7);
            tree.print();
        }
    }
}