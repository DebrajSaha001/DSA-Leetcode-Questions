class Solution {
    public int minMoves(int[] nums) {
        int max = Integer.MIN_VALUE;

        // Finding the maximum element
        for(int num : nums)
            max = Math.max(max, num);

        int moves = 0;

        // Calculating the total increments required
        for(int num : nums)
            moves += max - num;

        return moves;
    }
}
