class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        int next[][] = new int[m][n];
        int directions[] = {-1, 0, 1};

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int liveNeighbors = 0;

                // Checking all 8 neighbours
                for(int rowOffset : directions) {
                    for(int colOffset : directions) {
                        // Skiping the cell itself
                        if(rowOffset == 0 && colOffset == 0)
                            continue;

                        int newRow = i + rowOffset;
                        int newCol = j + colOffset;

                        if(newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && board[newRow][newCol] == 1)
                            liveNeighbors++;
                    }
                }

                // Applying rules
                if(board[i][j] == 1) {
                    if(liveNeighbors == 2 || liveNeighbors == 3)
                        next[i][j] = 1;
                }

                else {
                    if(liveNeighbors == 3)
                        next[i][j] = 1;
                }
            }
        }

        // Copy the next state back to the board
        for(int i = 0; i < m; i++)
            System.arraycopy(next[i], 0, board[i], 0, n);
    }
}
