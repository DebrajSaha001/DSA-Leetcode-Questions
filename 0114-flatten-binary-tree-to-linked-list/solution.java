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
    public void flatten(TreeNode root) {
        while(root != null) {
            // If there is no left subtree, nothing needs to be rearranged
            if(root.left != null) {
                // Find the rightmost node of the left subtree
                TreeNode predecessor = root.left;

                while(predecessor.right != null)
                    predecessor = predecessor.right;

                // Connecting the right subtree after the left subtree
                predecessor.right = root.right;

                // Move the left subtree to the right
                root.right = root.left;

                // Remove the left pointer
                root.left = null;
            }

            // Moving to the next node
            root = root.right;
        }
    }
}

