class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int k = nums[0];

        boolean asc = true, desc = true;
        for (int i = 0; i < n; i++) {
            if (nums[i] != (k + i) % n) asc = false;
            if (nums[i] != ((k - i) % n + n) % n) desc = false;
            if (!asc && !desc) return -1;
        }

        int best = Integer.MAX_VALUE;

        if (asc)
            best = (k == 0) ? 0 : Math.min(n - k, k + 2);

        if (desc) {
            int opt1 = 1 + ((n - k - 1) % n);   // reverse, then rotate left
            int opt2 = ((k + 1) % n) + 1;       // rotate left, then reverse
            best = Math.min(best, Math.min(opt1, opt2));
        }

        return best;
    }
}