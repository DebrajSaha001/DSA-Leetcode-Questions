class Solution {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int sum = (len*(len+1))/2;
        int actSum = 0;
        
        for(int n : nums){
            actSum = actSum + n;
        }

        return sum - actSum;
    }
}
