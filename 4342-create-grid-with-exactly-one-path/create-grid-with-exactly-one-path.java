class Solution {
    public String[] createGrid(int m, int n) {
        char grid[][] = new char[m][n];

        // Blocking every cell initially
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++)
                grid[i][j] = '#';
        }

        // Opening the top row
        for(int j = 0; j < n; j++)
            grid[0][j] = '.';

        // Opening the last column
        for(int i = 0; i < m; i++)
            grid[i][n - 1] = '.';

        // Convert the char[][] to String[]
        String res[] = new String[m];

        for(int i = 0; i < m; i++)
            res[i] = new String(grid[i]);

        return res;
    }
}
