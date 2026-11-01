class Solution {
    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;

        // Finding the minimum element
        for(int num : nums)
            min = Math.min(min, num);

        int moves = 0;

        // Calculating the total difference
        for(int num : nums)
            moves += num - min;

        return moves;
    }
}
