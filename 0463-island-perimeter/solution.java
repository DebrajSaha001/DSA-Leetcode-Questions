class Solution {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        int n = grid.length;
        int m = grid[0].length;

        for (int i = 0; i < n; i++) {
            int[] cur = grid[i];
            int[] prev = i > 0 ? grid[i - 1] : null;
            for (int j = 0; j < m; j++) {
                int cell = cur[j];
                perimeter += cell << 2;

                if (i > 0)
                    perimeter -= (cell & prev[j]) << 1;

                if (j > 0)
                    perimeter -= (cell & cur[j - 1]) << 1;
            }
        }
 
        return perimeter;
    }
}
