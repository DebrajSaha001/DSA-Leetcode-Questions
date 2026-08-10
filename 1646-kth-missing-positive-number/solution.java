class Solution {
    public int findKthPositive(int[] arr, int k) {
        int n = arr.length;
        int left = 0;
        int right = n - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            int miss = arr[mid] - (mid + 1);

            if(miss < k)
                left = mid + 1;
            else
                right = mid - 1;
        }

        int prevMiss = (left == 0) ? 0 : arr[left - 1] - left;
        int ans = (left == 0 ? 0 : arr[left - 1]) + (k - prevMiss);

        return ans;
    }
}
