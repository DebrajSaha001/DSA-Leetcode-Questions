class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        final int MOD = 1_000_000_007;

        // R/D: ways of attempting to enter (i,j) while moving right/down
        long right[][] = new long[m][n];
        long down[][] = new long[m][n];
        right[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long r = right[i][j], d = down[i][j];
                if (r == 0 && d == 0) continue;

                if (grid[i][j] == 1) {
                    // mirror: right -> down, down -> right (out of bounds is dropped)
                    if (i + 1 < m) down[i + 1][j] = (down[i + 1][j] + r) % MOD;
                    if (j + 1 < n) right[i][j + 1] = (right[i][j + 1] + d) % MOD;
                } else {
                    // empty: free choice of either direction
                    long t = (r + d) % MOD;
                    if (j + 1 < n) right[i][j + 1] = (right[i][j + 1] + t) % MOD;
                    if (i + 1 < m) down[i + 1][j] = (down[i + 1][j] + t) % MOD;
                }
            }
        }
        return (int) ((right[m - 1][n - 1] + down[m - 1][n - 1]) % MOD);
    }
}