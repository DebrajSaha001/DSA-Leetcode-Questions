class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int islands = 0;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    islands++;
                    dfs(grid, i, j);
                }
            }
        }

        return islands;
    }

    private void dfs(char grid[][], int row, int col) {
        int m = grid.length;
        int n = grid[0].length;

        // Check the boundaries and whether the cell is water
        if(row < 0 || col < 0 || row >= m || col >= n || grid[row][col] == '0')
            return;

        // Marking as visited
        grid[row][col] = '0';

        // Exploring all the four directions
        dfs(grid, row - 1, col); // Up
        dfs(grid, row + 1, col); // Dowm
        dfs(grid, row, col - 1); // Left
        dfs(grid, row, col + 1); // Right
    }
}