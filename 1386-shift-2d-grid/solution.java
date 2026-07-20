class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] ans = new int[m][n];
        k = k % (m * n);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int currentIndex = i * n + j;
                int newIndex = (currentIndex + k) % (m * n);

                int newRow = newIndex / n;
                int newCol = newIndex % n;

                ans[newRow][newCol] = grid[i][j];
            }
        }

        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();

            for(int j = 0; j < n; j++) {
                row.add(ans[i][j]);
            }
            res.add(row);
        }
        return res;
    }
}
