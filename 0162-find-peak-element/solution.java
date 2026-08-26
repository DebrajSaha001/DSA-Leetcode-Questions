class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while(left < right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] < nums[mid + 1])
                // As we are going uphill, so a peak is at mid or on the right side 
                left = mid + 1;

            else
                // As we are going downhill, so a peak is at mid or on the left side
                right = mid;
        }

        return left;
    }
}
