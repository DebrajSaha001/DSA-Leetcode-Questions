class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        // Storing the positions of 1
        int ones1[][] = new int[n * n][2];
        int ones2[][] = new int[n * n][2];

        int count1 = 0;
        int count2 = 0;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(img1[i][j] == 1) {
                    ones1[count1][0] = i;
                    ones1[count1][1] = j;
                    count1++;
                }

                if(img2[i][j] == 1) {
                    ones2[count2][0] = i;
                    ones2[count2][1] = j;
                    count2++;
                }
            }
        }

        int maxOverlap = 0;

        // Count how many pairs produce the same translation
        int size = 2 * n - 1;
        int count[][] = new int[size][size];

        for(int i = 0; i < count1; i++) {
            int r1 = ones1[i][0];
            int c1 = ones1[i][1];

            for(int j = 0; j < count2; j++) {
                int dr = ones2[j][0] - r1;
                int dc = ones2[j][1] - c1;

                // Shift negative values into array range
                int r = dr + n - 1;
                int c = dc + n - 1;

                count[r][c]++;
                maxOverlap = Math.max(maxOverlap, count[r][c]);
            }
        }

        return maxOverlap;
    }
}

