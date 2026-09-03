class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        boolean hasOdd = false;

        for(int num : nums1) {
            min = Math.min(min, num);

            if((num & 1) == 1)
                hasOdd = true;
        }

        // Can make all odd if minimum is odd
        if((min & 1) == 1)
            return true;

        // Can make all even only if there are no odd numbers
        return !hasOdd;
    }
}
