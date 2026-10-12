class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int count[] = new int[31];
        long mod = 1_000_000_007L;

        // Counting the zeros
        for(int i : nums) {
            for(int bit = 0; bit < 31; bit++) {
                if((i & (1 << bit)) != 0)
                    count[bit]++;
            }
        }

        long ans = 0;

        // Constructing k maximum numbers
        for(int i = 0; i < k; i++) {
            long num = 0;

            for(int bit = 0; bit < 31; bit++) {
                if(count[bit] > 0) {
                    num |= (1L << bit);
                    count[bit]--;
                }
            }

            // Adding the square modulo MOD
            ans = (ans + num * num % mod) % mod; 
        }

        return (int) ans;
    }
}
