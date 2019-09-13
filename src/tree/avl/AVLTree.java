package tree.avl;

public class AVLTree<T extends Comparable> {
    public AVLNode<T> root;

    public AVLTree() {}

    public AVLNode<T> search(T value) {
        return this.searchInNode(this.root, value);
    }

    private AVLNode<T> searchInNode(AVLNode<T> node, T value) {
        if (node == null)
            return null;

        if (value.compareTo(node.value) < 0)
            return searchInNode(node.left, value);
        else if (value.compareTo(node.value) > 0)
            return searchInNode(node.right, value);
        else
            return node;
    }

    public AVLNode<T> delete(T value) {
        AVLNode<T> node = this.search(value);
        if (node == null)
            return null;

        AVLNode<T> newNode = null;
        if (node.left == null)
            newNode = transplant(node, node.right);
        else if (node.right == null)
            newNode = transplant(node, node.left);
        else {
            AVLNode<T> suc = minNode(node.right);
            if (suc.parent != node) {
                transplant(suc, suc.right);
                suc.right = node.right;
                node.right.parent = suc;
            }
            suc.left = node.left;
            node.left.parent = suc;
            newNode = transplant(node, suc);
        }

        while (newNode != null) {
            balance(newNode);
            newNode = newNode.parent;
        }

        return node;
    }

    public AVLNode<T> minNode(AVLNode<T> node) {
        AVLNode<T> curr = node;
        AVLNode<T> prev = null;
        while (curr != null) {
            prev = curr;
            curr = curr.left;
        }
        return prev;
    }

    private AVLNode<T> transplant(AVLNode<T> origin, AVLNode<T> node) {
        if (origin == null)
            return null;

        if (node != null)
            node.parent = origin.parent;

        if (origin.parent == null) {
            this.setRoot(node);
            return node;
        }

        if (origin == origin.parent.left)
            origin.parent.left = node;
        else
            origin.parent.right = node;
        return node;
    }

    public AVLNode<T> insert(T value) {
        AVLNode<T> curr = this.root;
        AVLNode<T> prev = null;
        while (curr != null) {
            prev = curr;
            if (value.compareTo(curr.value) < 0)
                curr = curr.left;
            else if (value.compareTo(curr.value) > 0)
                curr = curr.right;
            else
                return curr;
        }
        AVLNode<T> newNode = null;
        if (prev != null) {
            if (value.compareTo(prev.value) < 0) {
                newNode = new AVLNode<>(value, prev);
                prev.left = newNode;
            }
            else if (value.compareTo(prev.value) > 0) {
                newNode = new AVLNode<>(value, prev);
                prev.right = newNode;
            }
        } else {
            newNode = new AVLNode<>(value);
            this.setRoot(newNode);
        }

        curr = newNode;
        while (curr != null) {
            this.balance(curr);
            curr = curr.parent;
        }
        return newNode;
    }

    private void balance(AVLNode<T> node) {
        if (node == null)
            return;
        int imBalance = 1;
        if (height(node.left)-height(node.right) > imBalance) {
            if (height(node.left.left) >= height(node.left.right))
                lRotate(node);
            else
                lDoubleRotate(node);
        } else if (height(node.right)-height(node.left) > imBalance) {
            if (height(node.right.right) >= height(node.right.left))
                rRotate(node);
            else
                rDoubleRotate(node);
        }
    }

    private int height(AVLNode<T> node) {
        if (node == null)
            return -1;
        return node.height();
    }

    private void lRotate(AVLNode<T> k2) {
        if (k2 == null || k2.left == null)
            return;

        AVLNode<T> k1 = k2.left;
        k2.left = k1.right;
        if (k1.right != null)
            k1.right.parent = k2;
        k1.parent = k2.parent;
        if (k2.parent == null)
            this.setRoot(k1);
        else if (k2 == k2.parent.left)
            k2.parent.left = k1;
        else
            k2.parent.right = k1;
        k2.parent = k1;
        k1.right = k2;
    }

    private void rRotate(AVLNode<T> k2) {
        if (k2 == null || k2.right == null)
            return;

        AVLNode<T> k1 = k2.right;
        k2.right = k1.left;
        if (k1.left != null)
            k1.left.parent = k2;
        k1.parent = k2.parent;
        if (k2.parent == null)
            this.setRoot(k1);
        else if (k2 == k2.parent.left)
            k2.parent.left = k1;
        else
            k2.parent.right = k1;
        k2.parent = k1;
        k1.left = k2;
    }

    private void lDoubleRotate(AVLNode<T> k3) {
        if (k3 == null || k3.left == null)
            return;
        this.rRotate(k3.left);
        this.lRotate(k3);
    }

    private void rDoubleRotate(AVLNode<T> k3) {
        if (k3 == null || k3.right == null)
            return;
        this.lRotate(k3.right);
        this.rRotate(k3);
    }

    public void print() {
        System.out.println("----AVL-TREE----");
        this.print(this.root);
        System.out.println("\n--------------");
    }

    private void print(AVLNode<T> node) {
        if (node == null)
            return;
        print(node.left);
        System.out.print(node.value.toString()+" ");
        print(node.right);
    }

    public void setRoot(AVLNode<T> node) {
        this.root = node;
        if (this.root != null)
            this.root.parent = null;
    }
}
