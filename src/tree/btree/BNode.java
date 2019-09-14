package tree.btree;

import java.util.ArrayList;
import java.util.List;

public class BNode<T extends Comparable> {
    public int n;
    public boolean leaf;
    public List<T> key;
    public List<BNode<T>> c;

    public BNode(int t, boolean leaf) {
        this.leaf = leaf;
        this.n = 0;
        this.key = new ArrayList<>(t*2-1);
        this.c = new ArrayList<>(t*2);
    }
}
