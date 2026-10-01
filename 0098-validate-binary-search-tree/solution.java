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
    public boolean isValidBST(TreeNode root) {
        return check(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean check(TreeNode node, long min, long max) {
        if(node == null)
            return true;

        // The node must be stricly inside the allowed range
        if(node.val <= min || node.val >= max)
            return false;

        // Left subtree: The values must be smaller than node.val
        if(!check(node.left, min, node.val))
            return false;

        // Right subtree: The values must be greater than node.val
        if(!check(node.right, node.val, max))
            return false;

        return true;
    }
}

