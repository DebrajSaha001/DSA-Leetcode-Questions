class Solution {
    public int findNumbers(int[] nums) {
        int count = 0;
        for(int num : nums) {
            int digs = 0;

            while(num > 0) {
                digs++;
                num /= 10;
            }

            if(digs % 2 == 0)
                count++;
        }

        return count;
    }
}
