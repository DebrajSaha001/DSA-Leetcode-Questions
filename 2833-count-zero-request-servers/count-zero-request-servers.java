class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        // Step 1: Sort logs by timestamp.
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Step 2: Sort query indices by their query times.
        Integer[] order = new Integer[queries.length];

        for (int i = 0; i < order.length; i++)
            order[i] = i;

        Arrays.sort(order, (a, b) ->
            Integer.compare(queries[a], queries[b])
        );

        // Step 3: Maintain server request frequencies.
        int[] cnt = new int[n + 1];

        int active = 0;
        int left = 0;
        int right = 0;

        int[] ans = new int[queries.length];

        // Step 4: Process queries in sorted order.
        for (int qi : order) {
            int q = queries[qi];

            // Add logs within the upper boundary.
            while (right < logs.length && logs[right][1] <= q) {
                int server = logs[right][0];
                if (cnt[server]++ == 0)
                    active++;

                right++;
            }

            // Remove logs outside the lower boundary.
            while (left < right && logs[left][1] < q - x) {
                int server = logs[left][0];
                if (--cnt[server] == 0)
                    active--;

                left++;
            }

            // Count servers with zero requests.
            ans[qi] = n - active;
        }

        return ans;
    }
}
