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
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;

        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        int depth = 1;

        while(!que.isEmpty()) {
            int size = que.size();

            for(int i = 0; i < size; i++) {
                TreeNode node = que.poll();

                if(node.left == null && node.right == null)
                    return depth;

                if(node.left != null)
                    que.offer(node.left);

                if(node.right != null)
                    que.offer(node.right);
            }

            depth++;
        }

        return depth;
    }
}