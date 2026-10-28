class Solution {
    private int ans;
    private boolean[][][] memo;

    public int minCost(int[][] grid) {
        int startVal = grid[0][0];
        ans = Integer.MAX_VALUE;
        memo = new boolean[grid.length][grid[0].length][1024];
        dfs(grid, 0, 0, 0, grid.length, grid[0].length);
        return ans;
    }

    private void dfs(int[][] grid, int i, int j, int xor, int m, int n) {
        // Include the current cell's value in the path XOR.
        xor ^= grid[i][j];

        // Zero is the smallest possible XOR, so stop searching.
        if (ans == 0)
            return;

        // Skip this state if it has already been explored.
        if (memo[i][j][xor])
            return;

        // Mark the current cell and XOR state as visited.
        memo[i][j][xor] = true;

        // If the destination is reached, update the answer.
        if (i == m - 1 && j == n - 1) {
            ans = Math.min(ans, xor);
            return;
        }

        // Explore downward if within the grid.
        if (i + 1 < m)
            dfs(grid, i + 1, j, xor, m, n);

        // Explore rightward if within the grid.
        if (j + 1 < n)
            dfs(grid, i, j + 1, xor, m, n);
    }
}