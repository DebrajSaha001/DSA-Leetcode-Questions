class Solution {
    private List<Integer>[] adj;
    private int[] parent, depth, heavy, size;
    private int[] head, pos;
    private int timer;
    private SegmentTree seg;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        // Building the adjacency list
        adj = new ArrayList[n];

        for(int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for(int edge[] : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];
        head = new int[n];
        pos = new int[n];

        Arrays.fill(parent, -1);
        Arrays.fill(heavy, -1);
        Arrays.fill(size, 1);

        // Traversing the tree iteratively
        int order[] = new int[n];
        int stack[] = new int[n];
        int top = 0, count = 0;

        stack[top++] = 0;

        while(top > 0) {
            int u = stack[--top];
            order[count++] = u;

            for(int v : adj[u]) {
                if(v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;
            }
        }

        // Calculating the subtree sizes and heavy children
        for(int i = n - 1; i >= 0; i--) {
            int u = order[i];
            int largest = 0;

            for(int v : adj[u]) {
                if(parent[v] == u) {
                    size[u] += size[v];

                    if(size[v] > largest) {
                        largest = size[v];
                        heavy[u] = v;
                    }
                }
            }
        }

        // Performing Heavy-Light Decomposition iteratively
        int chainNodes[] = new int[n];
        int chainHeads[] = new int[n];

        top = 0;
        timer = 0;

        chainNodes[top] = 0;
        chainHeads[top++] = 0;
        
        while(top > 0) {
            top--;

            int start = chainNodes[top];
            int chainHead = chainHeads[top];

            for(int u = start; u != -1; u = heavy[u]) {
                head[u] = chainHead;
                pos[u] = timer++;

                for(int v : adj[u]) {
                    if(parent[v] == u && v != heavy[u]) {
                        chainNodes[top] = v;
                        chainHeads[top++] = v;
                    }
                }
            }
        }

        // Convert each character into a bitmask
        int base[] = new int[n];

        for(int i = 0; i < n; i++)
            base[pos[i]] = 1 << (s.charAt(i) - 'a');

        // Building the segment tree
        seg = new SegmentTree(base);

        List<Boolean> ans = new ArrayList<>();

        // Processing all the queries
        for(String q : queries) {
            String parts[] = q.split("\\s+");

            if(parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char ch = parts[2].charAt(0);

                // Updating the node's character mask
                seg.update(pos[u], 1 << (ch - 'a'));
            }

            else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                // XOR all character masks on the path
                int mask = queryPath(u, v);

                // At most one character may have odd frequency
                ans.add(Integer.bitCount(mask) <= 1);
            }
        }

        return ans;
    }

    // Query the XOR mask along the path between two nodes.
    private int queryPath(int u, int v) {
        int mask = 0;

        while (head[u] != head[v]) {
            // Ensure u belongs to the deeper chain.
            if (depth[head[u]] < depth[head[v]]) {
                int temp = u;
                u = v;
                v = temp;
            }

            // Include the current heavy-chain segment.
            mask ^= seg.query(pos[head[u]], pos[u]);

            // Move to the parent of this chain's head.
            u = parent[head[u]];
        }

        // Both nodes are now on the same chain.
        int left = Math.min(pos[u], pos[v]);
        int right = Math.max(pos[u], pos[v]);

        mask ^= seg.query(left, right);

        return mask;
    }

    // Segment tree for point updates and range XOR queries.
    private static class SegmentTree {
        private final int size;
        private final int[] tree;

        SegmentTree(int[] arr) {
            int sz = 1;

            while (sz < arr.length)
                sz <<= 1;

            size = sz;
            tree = new int[size * 2];

            // Initialize the leaf nodes.
            for (int i = 0; i < arr.length; i++)
                tree[size + i] = arr[i];

            // Build the tree using XOR.
            for (int i = size - 1; i > 0; i--)
                tree[i] = tree[i * 2] ^ tree[i * 2 + 1];
        }

        // Update the value at one position.
        void update(int index, int value) {
            int p = size + index;
            tree[p] = value;

            while (p > 1) {
                p >>= 1;
                tree[p] = tree[p * 2] ^ tree[p * 2 + 1];
            }
        }

        // XOR of the inclusive range [left, right].
        int query(int left, int right) {
            left += size;
            right += size;

            int result = 0;

            while (left <= right) {
                if ((left & 1) == 1)
                    result ^= tree[left++];

                if ((right & 1) == 0)
                    result ^= tree[right--];

                left >>= 1;
                right >>= 1;
            }

            return result;
        }
    }
}