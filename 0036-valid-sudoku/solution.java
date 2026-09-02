class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean rows[][] = new boolean[9][9];
        boolean cols[][] = new boolean[9][9];
        boolean boxes[][] = new boolean[9][9];

        // Traversing through every cell
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                char cell = board[i][j];

                // Ignoring the empty cells
                if(cell == '.')
                    continue;

                int num = cell - '1';

                // Find which of the 3X3 box this cell belongs to
                int boxIndex = (i / 3) * 3 + (j / 3);

                // Check if the number already exists
                if(rows[i][num] || cols[j][num] || boxes[boxIndex][num])
                    return false;

                // Add the number to row, column and boxes
                rows[i][num] = true;
                cols[j][num] = true;
                boxes[boxIndex][num] = true;
            }
        }
        return true;
    }
}
