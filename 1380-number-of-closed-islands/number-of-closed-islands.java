class Solution {
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for(int r = 0; r < m; r++) {
            for(int c = 0; c < n; c++) {
                if(grid[r][c] == 0) {
                    if(dfs(grid, r, c))
                        count++;
                }
            }
        }

        return count;
    }

    private boolean dfs(int grid[][], int row, int col) {
        int m = grid.length;
        int n = grid[0].length;

        if(row < 0 || row >= m || col < 0 || col >= n)
            return false;

        if(grid[row][col] == 1)
            return true;

        grid[row][col] = 1;

        boolean up = dfs(grid, row - 1, col);
        boolean down = dfs(grid, row + 1, col);
        boolean left = dfs(grid, row, col - 1);
        boolean right = dfs(grid, row, col + 1);

        return up && down && left && right;
    }
}