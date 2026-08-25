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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        // Empty tree
        if(root == null)
            return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // Processing all the nodes of the current level
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);

                // Adding the left child
                if(node.left != null)
                    queue.offer(node.left);

                // Adding the right child
                if(node.right != null)
                    queue.offer(node.right);
            }
            res.add(currentLevel);
        }
        return res;
    }
}

