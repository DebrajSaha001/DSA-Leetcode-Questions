class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        // Try every cell as a starting position
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(dfs(board, word, i, j, 0))
                    return true;
            }
        }

        return false;
    }

    private boolean dfs(char board[][], String word, int row, int col, int index) {
        // Successfully matched the whole word
        if(index == word.length())
            return true;

        // Invalid position or the character doesn't match
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != word.charAt(index))
            return false;

        // Mark the current cell as visited
        char temp = board[row][col];
        board[row][col] = '#';

        // Exploring all the four directions
        boolean found = dfs(board, word, row + 1, col, index + 1) ||
        dfs(board, word, row - 1, col, index + 1) || 
        dfs(board, word, row, col + 1, index + 1) || 
        dfs(board, word, row, col - 1, index + 1);

        // Backtrack to restore the cells
        board[row][col] = temp;

        return found;
    }
}
