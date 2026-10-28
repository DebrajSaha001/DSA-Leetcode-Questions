class Solution {
    public int minCost(int[][] grid) {
        int m =  grid.length;
        int n = grid[0].length;
        int MAX = 1024;

        // dp[i][j][x] is true if there exists a path from (0, 0) to (i, j) with XOR value x.
        boolean dp[][][] = new boolean[m][n][MAX];

        // // dp[i][j][x] is true if there exists a path from (0, 0) to (i, j) with XOR value x.
        dp[0][0][grid[0][0]] = true;

        // // Traverse every cell row by row, from top to bottom and left to right.
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                // Starting cell is already initialized
                if(i == 0 && j == 0)
                    continue;

                // Value of the current cell
                int val = grid[i][j];

                // Try every possible XOR value that can reach the current cell from its neighbors.
                for(int x = 0; x < MAX; x++) {
                    // If XOR value x can reach the cell above, move down and XOR it with the current value.
                    if(i > 0 && dp[i - 1][j][x])
                        dp[i][j][x ^ val] = true;

                    // If XOR value x can reach the cell to the left, move right and XOR it with the current value.
                    if(j > 0 && dp[i][j - 1][x])
                        dp[i][j][x ^ val] = true;
                }
            }
        }

        // Find the smallest reachable XOR value at the bottom-right destination cell.
        for(int x = 0; x < MAX; x++) {
            if(dp[m - 1][n - 1][x])
                return x;
        }

        return -1;
    }
}