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
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        findPath(root, targetSum, path, result);
        return result;
    }

    public void findPath(TreeNode root, int k, List<Integer> path, List<List<Integer>> result) {
        if (root == null)
            return;

        path.add(root.val);

        if (root.left == null && root.right == null) {
            int sum = 0;

            for (int value : path)
                sum += value;

            if (sum == k)
                result.add(new ArrayList<>(path));
        }

        findPath(root.left, k, path, result);
        findPath(root.right, k, path, result);

        path.remove(path.size() - 1);
    }
}