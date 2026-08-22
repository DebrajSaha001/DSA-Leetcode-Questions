class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        // Finding the breakpoint
        int i = n - 2;

        while(i >= 0 && nums[i] >= nums[i + 1])
            i--;

        // Finding the next greater element
        if(i >= 0) {
            int j = n - 1;
            while(nums[j] <= nums[i])
                j--;

            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        rev(nums, i + 1, n - 1);
    }

    private void rev(int nums[], int left, int right) {
        while(left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }
}

