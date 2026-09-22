class Solution {
    public int findDuplicate(int[] nums) {
        // 1. Finding a meeting point inside the cycle
        int slow = nums[0];
        int fast = nums[0];

        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);

        // 2. Find the entrance of the cycle
        slow = nums[0];

        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
