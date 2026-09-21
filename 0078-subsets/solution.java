class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(int nums[], int index, List<Integer> current) {
        // Every current selection is a valid subset
        res.add(new ArrayList<>(current));

        // Try adding each remaining element
        for(int i = index; i < nums.length; i++) {
            // Choose
            current.add(nums[i]);

            // Explore
            backtrack(nums, i + 1, current);

            // Undo
            current.remove(current.size() - 1);
        }
    }
}
