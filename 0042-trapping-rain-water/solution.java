class Solution {
    public int trap(int[] height) {
        int totWat = 0;
        int n = height.length;

        int leftMax = 0;
        int rightMax = 0;

        int start = 0;
        int end = n - 1;

        while(start < end) {
            leftMax = Math.max(leftMax, height[start]);
            rightMax = Math.max(rightMax, height[end]);
            
            if(leftMax < rightMax) {
                totWat += leftMax - height[start];
                start++;
            }

            else {
                totWat += rightMax - height[end];
                end--;
            }
        }

        return totWat;
    }
}
