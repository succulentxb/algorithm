package tree.bst;

public class BstNode<T extends Comparable> {
    public BstNode<T> parent;
    public BstNode<T> left;
    public BstNode<T> right;

    public T value;

    public BstNode(T value) {
        this.value = value;
    }
}
