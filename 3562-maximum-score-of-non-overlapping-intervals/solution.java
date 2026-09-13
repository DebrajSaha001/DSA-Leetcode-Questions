class Solution {
    static class State {
        long score;
        int indices[];

        State(long score, int indices[]) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // (left, right, weight, originalIndex)
        int arr[][] = new int[n][4];
        for(int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        // Sort by starting position
        Arrays.sort(arr, (a, b) -> {
            if(a[0] != b[0])
                return Integer.compare(a[0], b[0]);

            return Integer.compare(a[1], b[1]);
        });

        // next[i] = first interval whose start > arr[i][1]
        int next[] = new int[n];

        for(int i = 0; i < n; i++) {
            int left = i + 1;
            int right = n;

            while(left < right) {
                int mid = left + (right - left) / 2;

                if(arr[mid][0] > arr[i][1])
                    right = mid;
                else
                    left = mid + 1;
            }
            next[i] = left;
        }

        // dp[i][k] best result from interval i onward when we can still choose at most k intervals.
        State dp[][] = new State[n + 1][5];
        // No intervals left
        for(int k = 0; k <= 4; k++)
            dp[n][k] = new State(0, new int[0]);

        // We cannot choose any interval
        for(int i = 0; i <= n; i++)
            dp[i][0] = new State(0, new int[0]);

        // Process from right to left
        for(int i = n - 1; i >= 0; i--) {
            for(int k = 1; k <= 4; k++) {
                // Option 1: Don't take current interval
                State skip = dp[i + 1][k];

                // Option 2: Take current interval
                State after = dp[next[i]][k - 1];

                int takeIndices[] = new int[after.indices.length + 1];
                takeIndices[0] = arr[i][3];

                for(int j = 0; j < after.indices.length; j++)
                    takeIndices[j + 1] = after.indices[j];

                // Sort original indices
                Arrays.sort(takeIndices);

                State take = new State(arr[i][2] + after.score, takeIndices);

                // Choose higher score
                if(take.score > skip.score)
                    dp[i][k] = take;

                // Choose smaller score
                else if(take.score < skip.score)
                    dp[i][k] = skip;

                // Same score -> lexicographically smaller
                else {
                    if(compare(take.indices, skip.indices) < 0)
                        dp[i][k] = take;
                    else
                        dp[i][k] = skip;
                }
            }
        }
        return dp[0][4].indices;
    }

    // Lexicographical comparison
    private int compare(int a[], int b[]) {
        int len = Math.min(a.length, b.length);

        for(int i = 0; i < len; i++) {
            if(a[i] != b[i])
                return Integer.compare(a[i], b[i]);
        }
        return Integer.compare(a.length, b.length);
    }
}
