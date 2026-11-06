class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        // Step 1: Sort logs by timestamp.
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Step 2: Store queries with their original indices.
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];

        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, (a, b) ->
            Integer.compare(a[0], b[0])
        );

        int[] ans = new int[m];

        // Step 3: Maintain the current window.
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;

        for (int[] query : sortedQueries) {
            int q = query[0];
            int originalIndex = query[1];

            int start = q - x;

            // Add logs with time <= q.
            while (right < logs.length && logs[right][1] <= q) {
                int server = logs[right][0];
                map.put(server, map.getOrDefault(server, 0) + 1);
                right++;
            }

            // Remove logs with time < q - x.
            while (left < right && logs[left][1] < start) {
                int server = logs[left][0];
                int count = map.get(server);

                if (count == 1)
                    map.remove(server);
                
                else
                    map.put(server, count - 1);

                left++;
            }

            // Number of servers that received zero requests.
            ans[originalIndex] = n - map.size();
        }

        return ans;
    }
}
