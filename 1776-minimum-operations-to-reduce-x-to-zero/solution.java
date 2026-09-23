class Solution {
    public int minOperations(int[] nums, int x) {
        int n = nums.length;

        // Calculate total sum
        int total = 0;

        for(int num : nums)
            total += num;

        // We need to keep a subarray whose sum is total -x
        int target = total - x;

        // If target is negative, it's possible
        if(target < 0)
            return -1;

        // Special Case: target = 0 means we have to remove the entire array
        if(target == 0)
            return n;

        int left = 0;
        int sum = 0;
        int maxLen = -1;

        for(int right = 0; right < n; right++) {
            sum += nums[right];

            // Shrinking the window if the sum is too large
            while(left <= right && sum > target) {
                sum -= nums[left];
                left++;
            }

            // Found a subarray with target sum
            if(sum == target)
                maxLen = Math.max(maxLen, right - left + 1);
        }

        // No valid subarray
        if(maxLen == -1)
            return -1;

        // Everything outside the longest valid subarray must be removed
        return n - maxLen;
    }
}

