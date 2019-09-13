package tree;

public abstract class TreeNode<T extends Comparable> {
    public TreeNode<T> parent;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public T value;

    public TreeNode(T value) {
        this.value = value;
    }
}
