class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long squares[] = new long[n];

        // Squaring every element
        for(int i = 0; i < n; i++)
            squares[i] = (long) nums[i] * nums[i];

        // Sorting the values
        Arrays.sort(squares);
        long score = 0;

        for(int i = 0; i < n; i++) {
            if(i < n / 2)
                score -= squares[i];

            else
                score += squares[i];
        }

        return score;
    }
}