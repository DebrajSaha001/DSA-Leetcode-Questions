class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int windowSum = 0;

        // First Window
        for(int i = 0; i < k; i++)
            windowSum += nums[i];

        int maxSum = windowSum;
        // Sliding the window
        for(int i = k; i < nums.length; i++) {
            windowSum += nums[i];
            windowSum -= nums[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return (double)maxSum / k;
    }
}

