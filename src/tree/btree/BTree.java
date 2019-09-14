package tree.btree;

import javax.naming.NameNotFoundException;
import java.util.Objects;

public class BTree<T extends Comparable> {
    public BNode<T> root;
    private int t;

    public BTree(int t) {
        this.t = t;
        this.root = new BNode<>(this.t, true);
        this.root.n = 0;
    }

    public int getT() {
        return t;
    }

    public T search(T k) {
        return this.searchInNode(this.root, k);
    }

    public boolean insert(T k) {
        BNode<T> r = this.root;
        if (r.n == this.t*2-1) {
            BNode<T> s = new BNode<>(this.t, false);
            this.root = s;
            s.n = 0;
            s.c.add(r);
            this.splitChild(s, 0);
            return this.insertNotFull(s, k);
        }
        else
            return this.insertNotFull(r, k);
    }

    public void delete(T k) {
        BNode<T> r = this.root;
        if (r.n == 1 && !r.leaf) {
            BNode<T> y = r.c.get(0);
            BNode<T> z = r.c.get(1);
            if (y.n == this.t-1 && z.n == this.t-1) {
                this.mergeChild(r, 0);
                this.root = y;
                this.deleteNotEmpty(y, k);
            }
            else
                this.deleteNotEmpty(r, k);
        }
        else
            this.deleteNotEmpty(r, k);
    }

    public void print() {
        System.out.println(this.printNode(this.root));
    }

    private String printNode(BNode<T> x) {
        if (x == null)
            return "";
        String res = "";
        for (int i = 0; i < x.n; i++) {
            if (!x.leaf)
                res += this.printNode(x.c.get(i));
            res += x.key.get(i) + " ";
        }
        if (!x.leaf)
            res += this.printNode(x.c.get(x.n));
        return res;
    }

    private void splitChild(BNode<T> x, int i) {
        BNode<T> y = x.c.get(i);
        BNode<T> z = new BNode<>(this.t, y.leaf);
        z.n = this.t - 1;
        for (int j = 0; j < this.t-1; j++)
            z.key.add(y.key.get(j+this.t));
        for (int j = this.t-2; j >= 0; j--)
            y.key.remove(j+this.t);

        if (!y.leaf) {
            for (int j = 0; j < this.t; j++)
                z.c.add(y.c.get(j+this.t));
            for (int j = this.t-1; j >= 0; j--)
                y.c.remove(j+this.t);
        }
        x.key.add(i, y.key.get(this.t-1));
        y.key.remove(this.t-1);
        x.c.add(i+1, z);
        x.n = x.n + 1;
        y.n = this.t - 1;
    }

    private boolean insertNotFull(BNode<T> x, T k) {
        int i = x.n-1;
        if (x.leaf) {
            while (i >= 0 && k.compareTo(x.key.get(i)) < 0)
                i--;
            x.key.add(i+1, k);
            x.n = x.n + 1;
        }
        else {
            while (i >= 0 && k.compareTo(x.key.get(i)) < 0)
                i--;
            i = i + 1;
            if (x.c.get(i).n == this.t*2-1) {
                this.splitChild(x, i);
                if (k.compareTo(x.key.get(i)) > 0)
                    i = i + 1;
            }
            this.insertNotFull(x.c.get(i), k);
        }
        return true;
    }

    private T searchInNode(BNode<T> x, T k) {
        int i = 0;
        while (i < x.n && k.compareTo(x.key.get(i)) > 0)
            i++;
        if (i < x.n && k.compareTo(x.key.get(i)) == 0)
            return x.key.get(i);
        else if (x.leaf)
            return null;
        else
            return this.searchInNode(x.c.get(i), k);
    }

    private void mergeChild(BNode<T> x, int i) {
        BNode<T> y = x.c.get(i);
        BNode<T> z = x.c.get(i+1);
        y.key.add(x.key.get(i));
        for (int j = 0; j < this.t-1; j++)
            y.key.add(z.key.get(j));
        for (int j = this.t-2; j >= 0; j--)
            z.key.remove(j);
        if (!y.leaf) {
            for (int j = 0; j < this.t; j++)
                y.c.add(z.c.get(j));
            for (int j = this.t-1; j >= 0; j--)
                z.c.remove(j);
        }
        y.n = this.t*2-1;
        x.key.remove(i);
        x.c.remove(i+1);
        x.n = x.n - 1;
    }

    private T successor(BNode<T> z) {
        while (!z.leaf)
            z = z.c.get(0);
        return z.key.get(0);
    }

    private T predeccessor(BNode<T> y) {
        while (!y.leaf)
            y = y.c.get(y.n);
        return y.key.get(y.n-1);
    }

    private void rightShift(BNode<T> x, int i) {
        BNode<T> y = x.c.get(i-1);
        BNode<T> z = x.c.get(i);

        z.key.add(0, x.key.get(i-1));
        if (!z.leaf) {
            z.c.add(0, y.c.get(y.n));
            y.c.remove(y.n);
        }
        z.n = z.n + 1;
        x.key.set(i-1, y.key.get(y.n-1));
        y.key.remove(y.n-1);
        y.n = y.n - 1;
    }

    private void leftShift(BNode<T> x, int i) {
        BNode<T> y = x.c.get(i);
        BNode<T> z = x.c.get(i+1);

        y.key.add(x.key.get(i));
        if (!y.leaf) {
            y.c.add(z.c.get(0));
            z.c.remove(0);
        }
        y.n = y.n + 1;
        x.key.set(i, z.key.get(0));
        z.key.remove(0);
        z.n = z.n - 1;
    }

    private void deleteNotEmpty(BNode<T> x, T k) {
        int i = 0;
        if (x.leaf) {
            while (i < x.n && k.compareTo(x.key.get(i)) > 0)
                i++;
            if (i >= x.n || k.compareTo(x.key.get(i)) != 0)
                return;
            x.key.remove(i);
            x.n = x.n - 1;
        }
        else {
            while (i < x.n && k.compareTo(x.key.get(i)) > 0)
                i++;
            if (i < x.n && k.compareTo(x.key.get(i)) == 0) {
                BNode<T> y = x.c.get(i);
                BNode<T> z = x.c.get(i+1);
                if (y.n >= this.t) {
                    T k_ = this.predeccessor(y);
                    this.deleteNotEmpty(y, k_);
                    x.key.set(i, k_);
                } else if (z.n >= this.t) {
                    T k_ = this.successor(z);
                    this.deleteNotEmpty(z, k_);
                    x.key.set(i, k_);
                } else {
                    this.mergeChild(x, i);
                    this.deleteNotEmpty(x.c.get(i), k);
                }
            }
            else {
                if (x.c.get(i).n >= this.t)
                    this.deleteNotEmpty(x.c.get(i), k);
                else {
                    if (i > 0 && x.c.get(i-1).n >= this.t) {
                        this.rightShift(x, i);
                        this.deleteNotEmpty(x.c.get(i), k);
                    } else if (i < x.n && x.c.get(i+1).n >= this.t) {
                        this.leftShift(x, i);
                        this.deleteNotEmpty(x.c.get(i), k);
                    } else if(i > 0) {
                        BNode<T> y = x.c.get(i-1);
                        this.mergeChild(x, i-1);
                        this.deleteNotEmpty(y, k);
                    } else {
                        BNode<T> y = x.c.get(i);
                        this.mergeChild(x, i);
                        this.deleteNotEmpty(y, k);
                    }
                }
            }
        }
    }
}
