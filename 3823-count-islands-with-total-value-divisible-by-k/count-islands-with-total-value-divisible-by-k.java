class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> que = new ArrayDeque<>();
        int dirs[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

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

    private long dfs(int grid[][], int row, int col) {
        int m = grid.length;
        int n = grid[0].length;

        // Outside the grid / water / already visited
        if(row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 0)
            return 0L;

        // Saving this cell's value
        long value = grid[row][col];

        // Marking it as visited
        grid[row][col] = 0;

        // Adding values to all the four directions
        value += dfs(grid, row - 1, col);
        value += dfs(grid, row + 1, col);
        value += dfs(grid, row, col - 1);
        value += dfs(grid, row, col + 1);

        return value;
    }
}