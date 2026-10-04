class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int res[] = new int[m * n];
        int idx = 0;

        for(int d = 0; d < m + n - 1; d++) {
            if(d % 2 == 0) {
                // Moving upward-right
                int row = Math.min(d, m - 1);
                int col = d - row;

                while(row >= 0 && col < n) {
                    res[idx++] = mat[row][col];
                    row--;
                    col++;
                }
            }

            else {
                // Moving downward-left
                int col = Math.min(d, n - 1);
                int row = d - col;

                while(row < m && col >= 0) {
                    res[idx++] = mat[row][col];
                    row++;
                    col--;
                }
            }
        }

        return res;
    }
}

