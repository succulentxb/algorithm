package tree.bst;

public class BstTree<T extends Comparable> {
    public BstNode<T> root;

    public BstTree() {}

    public BstNode<T> search(T value) {
        return searchInNode(this.root, value);
    }

    private BstNode<T> searchInNode(BstNode<T> node, T value) {
        if (node == null || node.value.compareTo(value) == 0)
            return node;
        if (node.value.compareTo(value) < 0)
            return searchInNode(node.right, value);
        else
            return searchInNode(node.left, value);
    }

    public BstNode<T> insert(T value) {
        BstNode<T> curr = this.root;
        BstNode<T> prev = null;
        while (curr != null) {
            prev = curr;
            if (curr.value.compareTo(value) < 0)
                curr = curr.right;
            else if (curr.value.compareTo(value) > 0)
                curr = curr.left;
            else
                return curr;
        }
        if (prev == null) {
            this.root = new BstNode<>(value);
            return this.root;
        }
        BstNode<T> newNode = new BstNode<>(value);
        newNode.parent = prev;
        if (prev.value.compareTo(value) < 0)
            prev.right = newNode;
        else
            prev.left = newNode;
        return newNode;
    }

    public T delete(T value) {
        BstNode<T> node = this.search(value);
        if (node == null)
            return null;

        if (node.left == null)
            this.transplant(node, node.right);
        else if (node.right == null)
            this.transplant(node, node.left);
        else {
            BstNode<T> suc = this.minNode(node.right);
            if (suc.parent != node) {
                this.transplant(suc, suc.right);
                suc.right = node.right;
                node.right.parent = suc;
            }
            this.transplant(node, suc);
            suc.left = node.left;
            node.left.parent = suc;
        }
        return value;
    }

    private BstNode<T> transplant(BstNode<T> origin, BstNode<T> curr) {
        if (origin == null)
            return null;
        if (origin.parent == null) {
            this.root = curr;
            return curr;
        }

        BstNode<T> parent = origin.parent;
        if (origin == parent.left) {
            parent.left = curr;
        } else {
            parent.right = curr;
        }
        if (curr != null)
            curr.parent = parent;
        return curr;
    }

    private BstNode<T> minNode(BstNode<T> node) {
        if (node == null)
            return null;
        BstNode<T> min = node;
        while (min.left != null)
            min = min.left;
        return min;
    }

    private BstNode<T> maxNode(BstNode<T> node) {
        if (node == null)
            return null;
        BstNode<T> max = node;
        while (max.right != null)
            max = max.right;
        return max;
    }

    private BstNode<T> successor(BstNode<T> node) {
        if (node == null)
            return null;
        if (node.right != null)
            return this.minNode(node.right);

        BstNode<T> curr = node;
        while (curr.parent != null && curr == curr.parent.right)
            curr = curr.parent;
        return curr.parent;
    }

    private BstNode<T> predecessor(BstNode<T> node) {
        if (node == null)
            return null;
        if (node.left != null)
            return this.maxNode(node.left);

        BstNode<T> curr = node;
        while (curr.parent != null && curr == curr.parent.left)
            curr = curr.parent;
        return curr.parent;
    }

    public void print() {
        System.out.println("----BST-TREE----");
        this.print(this.root);
        System.out.println("\n--------------");
    }

    private void print(BstNode<T> node) {
        if (node == null)
            return;
        print(node.left);
        System.out.print(node.value.toString()+" ");
        print(node.right);
    }
}
