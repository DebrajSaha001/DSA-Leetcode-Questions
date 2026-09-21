class Solution {
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(int nums[], int start, List<Integer> current) {
        // Every current selection is a valid subset
        res.add(new ArrayList<>(current));

        for(int i = start; i < nums.length; i++) {
            // Skipping duplicate branches at the same level
            if(i > start && nums[i] == nums[i - 1])
                continue;

            // Choose
            current.add(nums[i]);

            // Explore
            backtrack(nums, i + 1, current);

            // Undo
            current.remove(current.size() - 1);
        }
    }
}
