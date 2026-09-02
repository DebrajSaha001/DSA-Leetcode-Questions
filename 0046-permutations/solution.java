class Solution {
    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        boolean used[] = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>());
        return result;
    }

    private void backtrack(int nums[], boolean used[], List<Integer> current) {
        // Base Case: Selecting all numbers
        if(current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Trying for every number
        for(int i = 0; i < nums.length; i++) {
            // Skip the numbers that have already been used
            if(used[i])
                continue;

            // Choose
            used[i] = true;
            current.add(nums[i]);

            // Explore
            backtrack(nums, used, current);

            // Undo the choices
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}

