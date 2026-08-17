class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        boolean flag = false;

        // calculating total XOR and checking if their are any non-zero element is existing
        for(int num : nums) {
            xor ^= num;
            if(num != 0)
                flag = true;
        }

        // if total xor is already non-zero, then take the whole array
        if(xor != 0)
            return nums.length;
        
        // if all elements are zero, no valid sequence
        if(!flag)
            return 0;
        
        // remove 1 non-zero element
        return nums.length - 1;
    }
}
