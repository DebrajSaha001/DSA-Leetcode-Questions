class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int []> queue = new LinkedList<>();
        int fresh = 0;
        int mins = 0;

        // Finding all the rotten and fresh oranges
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == 2)
                    queue.offer(new int[]{i, j});

                else if(grid[i][j] == 1)
                    fresh++;
            }
        }

        // Directions
        int directions[][] = {
            {0, 1}, {0, -1}, {1, 0}, {-1, 0}
        };

        // Spreading the rot level by level
        while(!queue.isEmpty() && fresh > 0) {
            int size = queue.size();
            boolean rotThisMin = false;

            for(int i = 0; i < size; i++) {
                int curr[] = queue.poll();
                int row = curr[0];
                int col = curr[1];

                for(int dir[] : directions) {
                    int nr = row + dir[0];
                    int nc = col + dir[1];

                    // Checking the boundaries and the fresh oranges
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        fresh--;
                        queue.offer(new int[]{nr, nc});
                        rotThisMin = true;
                    }
                }
            }

            if(rotThisMin)
                mins++;
        }

        // Checking it whether all fresh oranges or it is rotten
        return fresh == 0 ? mins : -1;
    }
}
