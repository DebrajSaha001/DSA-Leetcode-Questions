class Solution {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        // Sort it so that duplicates are adjacent
        Arrays.sort(nums);
        boolean used[] = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>());
        return result;
    }

    private void backtrack(int nums[], boolean used[], List<Integer> current) {
        // Base case
        if(current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for(int i = 0; i < nums.length; i++) {
            // Already used in the permutation
            if(used[i])
                continue;

            // Skip duplicates. If nums[i] is the same as nums[i - 1], and the previous duplicate has not been used, using nums[i] would create a duplicate branch.

            if(i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
                continue;

            // Choose
            used[i] = true;
            current.add(nums[i]);

            // Explore
            backtrack(nums, used, current);

            // Undo
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}

