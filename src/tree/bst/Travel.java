package tree.bst;


import java.util.ArrayList;
import java.util.List;

public class Travel {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        List<Integer> leftRes = inorderTraversal(root.left);
        List<Integer> rightRes = inorderTraversal(root.right);
        if (leftRes != null)
            res.addAll(leftRes);
        res.add(root.val);
        if (rightRes != null)
            res.addAll(rightRes);
        return res;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)
            return res;
        res.add(root.val);
        res.addAll(preorderTraversal(root.left));
        res.addAll(preorderTraversal(root.right));
        return res;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}
