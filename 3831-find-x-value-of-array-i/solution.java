class Solution {
    public long[] resultArray(int[] nums, int k) {
        long res[] = new long[k];
        long dp[] = new long[k];

        for(int num : nums) {
            long newDp[] = new long[k];
            int val = num % k;

            // Start a new subarray with just nums[i]
            newDp[val]++;

            // Extend all previous subarrays
            for(int r = 0; r < k; r++) {
                if(dp[r] == 0)
                    continue;

                int newRemainder = (r * val) % k;
                newDp[newRemainder] += dp[r];
            }

            // Add all subarrays ending here to the ans
            for(int r = 0; r < k; r++)
                res[r] += newDp[r];

            dp = newDp;
        }

        return res;
    }
}

