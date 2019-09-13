package tree.avl;

public class AVLNode<T extends Comparable> {
    public AVLNode<T> parent;
    public AVLNode<T> left;
    public AVLNode<T> right;
    public T value;

    public AVLNode(T value) {
        this.value = value;
    }

    public AVLNode(T value, AVLNode<T> parent) {
        this.value = value;
        this.parent = parent;
    }

    public int height() {
        return this.height(this);
    }

    private int height(AVLNode<T> node) {
        if (node == null)
            return -1;
        int lh = this.height(node.left);
        int rh = this.height(node.right);
        return Math.max(lh, rh) + 1;
    }
}
