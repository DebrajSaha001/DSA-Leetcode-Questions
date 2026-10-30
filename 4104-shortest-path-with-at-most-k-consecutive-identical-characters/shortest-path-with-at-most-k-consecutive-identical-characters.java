class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Building the adjacency list
        List<int[]> adj[] = new ArrayList[n];

        for(int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for(int edge[] : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];

            adj[u].add(new int[]{v, w});
        }

        // dist[node][count] represents the minimum cost to reach 'node' with 'count' consecutive occurrences of the current node's character.

        int dist[][] = new int[n][k + 1];

        for(int row[] : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        // Priority Queue stores: {totalCost, currentNode, consecutiveCount}
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (a, b) -> Integer.compare(a[0], b[0])
        );

        // Starting at node 0. It's character has appeared once
        dist[0][1] = 0;
        pq.offer(new int[]{0, 0, 1});

        while(!pq.isEmpty()) {
            int curr[] = pq.poll();

            int cost = curr[0];
            int u = curr[1];
            int count = curr[2];

            // Ignore outdated states
            if(cost != dist[u][count]) continue;

            // First valid destination state removed from the min-heap has the minimum cost.
            if(u == n - 1) return cost;

            // Exploring all outgoing edges
            for(int edge[] : adj[u]) {
                int v = edge[0];
                int weight = edge[1];
                int newCount;

                // If both the characters are identical, increase the consecutive count
                if(labels.charAt(u) == labels.charAt(v)) 
                    newCount = count + 1;

                else
                    // A different character starts a new sequence
                    newCount = 1;

                // Reject the paths exceeding the allowed limit
                if(newCount > k) continue;
                int newCost = cost + weight;

                // Relaxing the new state
                if(newCost < dist[v][newCount]) {
                    dist[v][newCount] = newCost;
                    pq.offer(new int[]{newCost, v, newCount});
                }
            }
        }

        // No valid path exists
        return -1;
    }
}
