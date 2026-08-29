class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0) return 0;

        Arrays.sort(nums);

        int longest = 1;
        int current = 1;

        for (int i = 1; i < nums.length; i++) {
            // Ignore duplicates
            if (nums[i] == nums[i - 1])
                continue;

            if ((long) nums[i] - nums[i - 1] == 1)
                current++;
            
            else {
                if (current > longest)
                    longest = current;

                current = 1;
            }
        }

        return Math.max(longest, current);
    }
}
