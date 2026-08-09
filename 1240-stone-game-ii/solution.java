class Solution {
    int[][] memo;
    int[] pref;
    int n;

    public int stoneGameII(int[] piles) {
        n = piles.length;
        pref = new int[n + 1];

        for(int i = 0; i < n; i++)
            pref[i + 1] = pref[i] + piles[i];

        memo = new int[n][n + 1];
        
        for(int[] row : memo)
            Arrays.fill(row, -1);

        return solve(0, 1);    
    }

    private int solve(int index, int M) {
        if(index == n)
            return 0;

        if(memo[index][M] != -1)
            return memo[index][M];

        int maxStones = 0;
        int rem = pref[n] - pref[index];

        for(int X = 1; X <= 2 * M && index + X <= n; X++) {
            int taken = pref[index + X] - pref[index];
            int opp = solve(index + X, Math.max(X, M));
            maxStones = Math.max(maxStones, rem - opp);
        }

        return memo[index][M] = maxStones;
    }
}
