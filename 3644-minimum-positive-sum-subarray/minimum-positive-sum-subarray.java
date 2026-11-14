class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        int initial = 0;

        for (int i = 0; i < l - 1; i++)
            initial += nums.get(i);

        for (int length = l; length <= r; length++) {
            initial += nums.get(length - 1);
            int sum = initial;

            if (sum > 0 && sum < minSum)
                minSum = sum;

            for (int hi = length; hi < n; hi++) {
                sum += nums.get(hi) - nums.get(hi - length);
                if (sum > 0 && sum < minSum)
                    minSum = sum;
            }
        }

        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}