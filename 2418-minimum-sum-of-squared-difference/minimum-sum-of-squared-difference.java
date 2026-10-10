class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int maxDiff = 0;

        int diff[] = new int[n];

        // Calculating the absolute differences
        for(int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(maxDiff, diff[i]);
        }

        long ops = (long) k1 + k2;

        // Counting how many differences have each value
        int count[] = new int[maxDiff + 1];
        for(int d : diff)
            count[d]++;

        // Reducing the largest differences first
        for(int d = maxDiff; d > 0 && ops > 0; d--) {
            long move = Math.min(ops, count[d]);
            count[d] -= (int) move;
            count[d - 1] += (int) move;
            ops -= move;
        }

        // Calculating the final sum of squares
        long ans = 0;
        for(int d = 0; d <= maxDiff; d++)
            ans += (long) d * d * count[d];

        return ans;
    }
}