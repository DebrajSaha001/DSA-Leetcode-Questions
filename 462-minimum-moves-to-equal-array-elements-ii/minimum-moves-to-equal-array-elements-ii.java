class Solution {
    public int minMoves2(int[] nums) {
        // Sorting the array
        Arrays.sort(nums);

        // Finding the median
        int median = nums[nums.length / 2];
        int moves = 0;

        // Calculating the total moves
        for(int num : nums)
            moves += Math.abs(num - median);

        return moves;
    }
}