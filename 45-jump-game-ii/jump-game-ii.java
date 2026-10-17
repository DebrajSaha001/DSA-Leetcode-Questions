class Solution {
    public int jump(int[] nums) {
        return minJump(nums, 0, 0, 0);
    }

    private int minJump(int[] nums, int left, int right, int jumps) {
        if(right >= nums.length - 1)
            return jumps;

        int max = right;
        int maxIndex = left;

        for(int i = left; i <= right; i++) {
            if(max < i + nums[i]) {
                max = i + nums[i];
                maxIndex = i;
            }
        }

        return minJump(nums, maxIndex, max, jumps + 1);
    }
}