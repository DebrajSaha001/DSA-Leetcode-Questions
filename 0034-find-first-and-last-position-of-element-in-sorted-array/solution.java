class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = findFirst(nums, target);
        int last = findSecond(nums, target);

        return new int[]{first, last};
    }

    private int findFirst(int nums[], int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] == target) {
                ans = mid;
                // Search for the earlier occurence
                right = mid - 1;
            } else if(nums[mid] < target)
                left = mid + 1;

            else
                right = mid - 1;
        }
        return ans;
    }

    private int findSecond(int nums[], int target) {
        int left = 0;
        int right = nums.length - 1;
        int ans = -1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(nums[mid] == target) {
                ans = mid;
                // Search for the later occurence
                left = mid + 1;
            } else if(nums[mid] < target)
                left = mid + 1;

            else
                right = mid - 1;
        }
        return ans;
    }
}
