class Solution {
    int m, n;
    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        int count = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] != 0) {
                    long sum = dfs(grid, i, j);

                    if(sum % k == 0) count++;
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, int row, int col) {
        // Outside the grid / water / already visited
        if(row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 0)
            return 0;

        // Saving this cell's value
        long sum = grid[row][col];

        // Marking it as visited
        grid[row][col] = 0;

        // Adding all the values from all the four directions
        sum += dfs(grid, row + 1, col);
        sum += dfs(grid, row - 1, col);
        sum += dfs(grid, row, col + 1);
        sum += dfs(grid, row, col - 1);

        return sum;
    }
}
