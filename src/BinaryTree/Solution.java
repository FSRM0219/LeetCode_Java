package BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    private List<Integer> ans = new ArrayList<>();

    public int maxDepth(TreeNode root) {

    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        dfs(root.right);
        ans.add(root.val);
    }
}
