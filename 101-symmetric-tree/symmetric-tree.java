/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    private boolean check(TreeNode left, TreeNode right) {
        // Both the sides are empty
        if(left == null && right == null)
            return true;

        // Only one side is empty
        if(left == null || right == null)
            return false;

        // Values must match
        if(left.val != right.val)
            return false;

        // Compare mirrored children
        return check(left.left, right.right) && check(left.right , right.left);
    }
}
