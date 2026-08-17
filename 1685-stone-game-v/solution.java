class Solution {
    int[][] dp;
    int[] pref;

    private int solve(int left, int right) {
        if(left == right)
            return 0;

        if(dp[left][right] != -1)
            return dp[left][right];

        int maxScore = 0;
        for(int mid = left; mid < right; mid++) {
            int leftSum = getSum(left, mid);
            int rightSum = getSum(mid + 1, right);

            if(leftSum < rightSum) 
                maxScore = Math.max(maxScore, leftSum + solve(left, mid));

            else if(rightSum < leftSum)
                maxScore = Math.max(maxScore, rightSum + solve(mid + 1, right));

            else {
                maxScore = Math.max(maxScore, leftSum + Math.max(solve(left, mid), solve(mid + 1, right)));
            }
        }
        return dp[left][right] = maxScore;
    }

    private int getSum(int left, int right) {
        return pref[right + 1] - pref[left];
    }

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        pref = new int[n + 1];
        for(int i = 0; i < n; i++) 
            pref[i + 1] = pref[i] + stoneValue[i];

        dp = new int[n][n];
        for(int[] rows : dp)
            Arrays.fill(rows, -1);

        return solve(0, n - 1);
    }
}
