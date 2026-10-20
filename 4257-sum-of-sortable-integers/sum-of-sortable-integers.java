class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int prefMax[] = new int[n + 1];
        int suffMin[] = new int[n + 1];
        int prefDrop[] = new int[n];

        prefMax[0] = Integer.MIN_VALUE;

        for(int i = 0; i < n; i++)
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);

        suffMin[n] = Integer.MAX_VALUE;

        for(int i = n - 1; i >= 0; i--)
            suffMin[i] = Math.min(suffMin[i + 1], nums[i]);

        for(int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1];

            if(nums[i - 1] > nums[i])
                prefDrop[i]++;
        }

        int ans = 0;

        for(int k = 1; k <= n; k++) {
            if(n % k != 0)
                continue;

            boolean sort = true;

            for(int start = 0; start < n; start += k) {
                int end = start + k - 1;
                int descents = prefDrop[end] - prefDrop[start];

                if(nums[end] > nums[start])
                    descents++;

                if(descents > 1) {
                    sort = false;
                    break;
                }

                if(end + 1 < n && prefMax[end + 1] > suffMin[end + 1]) {
                    sort = false;
                    break;
                }
            }

            if(sort)
                ans += k;
        }

        return ans;
    }
}