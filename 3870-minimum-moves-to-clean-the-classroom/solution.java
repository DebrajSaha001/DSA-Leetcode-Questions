class Solution {
    static class State {
        int row;
        int col;
        int energyLeft;
        int mask;
        int moves;

        State(int row, int col, int energyLeft, int mask, int moves) {
            this.row = row;
            this.col = col;
            this.energyLeft = energyLeft;
            this.mask = mask;
            this.moves = moves;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        int[][] litterId = new int[m][n];

        for (int[] row : litterId)
            Arrays.fill(row, -1);

        int startRow = 0;
        int startCol = 0;
        int litterCount = 0;

        // Find starting position and assign IDs to litter
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char cell = classroom[i].charAt(j);

                if (cell == 'S') {
                    startRow = i;
                    startCol = j;
                }

                else if (cell == 'L')
                    litterId[i][j] = litterCount++;
            }
        }

        // No litter to collect
        if (litterCount == 0)
            return 0;

        int allCollectedMask = (1 << litterCount) - 1;

        // bestEnergy[row][col][mask] maximum energy left when reaching this cell with this collection mask.

        int[][][] bestEnergy = new int[m][n][1 << litterCount];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                Arrays.fill(bestEnergy[i][j], -1);
        }

        Queue<State> queue = new ArrayDeque<>();

        queue.offer(new State(startRow, startCol, energy, 0, 0));
        bestEnergy[startRow][startCol][0] = energy;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            State current = queue.poll();

            // Cannot make another move without energy
            if (current.energyLeft == 0)
                continue;

            for (int d = 0; d < 4; d++) {
                int nr = current.row + dr[d];
                int nc = current.col + dc[d];

                // IMPORTANT: Check boundaries first
                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                // Cannot pass through obstacle
                if (classroom[nr].charAt(nc) == 'X')
                    continue;

                int nextEnergy = current.energyLeft - 1;
                int nextMask = current.mask;
                char cell = classroom[nr].charAt(nc);

                // Reset energy
                if (cell == 'R')
                    nextEnergy = energy;

                // Collect litter
                if (cell == 'L') {
                    int id = litterId[nr][nc];
                    nextMask |= (1 << id);
                }

                int nextMoves = current.moves + 1;

                // All litter collected
                if (nextMask == allCollectedMask)
                    return nextMoves;

                // If we've already reached the same position with the same collected litter and equal or more energy, this state is useless.
                
                if (bestEnergy[nr][nc][nextMask] >= nextEnergy)
                    continue;

                bestEnergy[nr][nc][nextMask] = nextEnergy;
                queue.offer(new State(nr, nc, nextEnergy, nextMask, nextMoves));
            }
        }
        return -1;
    }
}
