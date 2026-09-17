class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int best[] = new int[n];

        // Large values means no valid subarrays found
        int INF = n + 1;

        for(int i = 0; i < n; i++)
            best[i] = INF;

        int left = 0, sum = 0;
        int ans = INF, minLength = INF;

        for(int right = 0; right < n; right++) {
            sum += arr[right];

            // Shrinking the window if sum becomes too large
            while(sum > target) {
                sum -= arr[left];
                left++;
            }

            // We found a subarray with sum = target
            if(sum == target) {
                int currentLength = right - left + 1;

                // Cheecking if there is a previous non-overlapping subarray
                if(left > 0 && best[left - 1] != INF)
                    ans = Math.min(ans, currentLength + best[left  - 1]);

                // Keeping track of the shortest subarray seen so far
                minLength = Math.min(minLength, currentLength);
            }

            // Storing the best subarray up to this index
            best[right] = minLength;
        }

        return ans == INF ? -1 : ans;
    }
}
