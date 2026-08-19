class Solution {
    public int largestInteger(int[] nums, int k) {
        // count[x] = number of size-k subarrays
        // that contain x
        int count[] = new int[51];
        int n = nums.length;
        // generate every subarray of size k
        for(int start = 0; start <= n - k; start++) {
            // seen[x] tells whether x has already appeared in the current subarray
            boolean seen[] = new boolean[51];

            for(int i = start; i < start + k; i++) {
                int val = nums[i];

                // count this value only once per subarray
                if(!seen[val]) {
                    seen[val] = true;
                    count[val]++;
                }
            }
        }

        // finding the largest number appearing in exactly one subarray
        for(int val = 50; val >= 0; val--) {
            if(count[val] == 1)
                return val;
        }
        return -1;
    }
}
