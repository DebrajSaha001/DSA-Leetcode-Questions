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
    private int postIndex;
    private Map<Integer, Integer> inorderMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        inorderMap = new HashMap<>();

        // Store value -> index from inorder
        for (int i = 0; i < inorder.length; i++)
            inorderMap.put(inorder[i], i);

        // Start from the last element of postorder
        postIndex = postorder.length - 1;
        return build(inorder, postorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int left, int right) {
        // No elements in this subtree
        if (left > right)
            return null;

        // Last remaining element in postorder is the root
        int rootValue = postorder[postIndex--];
        TreeNode root = new TreeNode(rootValue);

        // Find root position in inorder
        int rootIndex = inorderMap.get(rootValue);

        // Build RIGHT subtree first
        root.right = build(inorder, postorder, rootIndex + 1, right);

        // Then build LEFT subtree
        root.left = build(inorder, postorder, left, rootIndex - 1);

        return root;
    }
}
