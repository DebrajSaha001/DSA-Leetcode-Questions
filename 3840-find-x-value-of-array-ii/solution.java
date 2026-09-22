class Solution {
    class Node {
        // Product of the entire segment modulo k
        int prod;

        // prefix[r] = number of prefixes whose product % k == r
        long[] prefix;

        // suffix[r] = number of suffixes whose product % k == r
        long[] suffix;

        // sub[r] = number of subarrays whose product % k == r
        long[] sub;

        Node(int k) {
            prefix = new long[k];
            suffix = new long[k];
            sub = new long[k];
        }
    }

    int k;
    Node[] tree;
    int[] nums;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.nums = nums;
        this.k = k;
        int n = nums.length;

        tree = new Node[4 * n];
        build(1, 0, n - 1);
        int[] res = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int idx = queries[q][0];
            int val = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Update nums[idx]
            nums[idx] = val;

            // Update segment tree
            update(1, 0, n - 1, idx, val);

            // Get range [start, n - 1]
            Node node = query(1, 0, n - 1, start, n - 1);

            // Possible remaining arrays are prefixes of nums[start... n-1]
            res[q] = (int) node.prefix[x];
        }

        return res;
    }

    // BUILD

    private void build(int node, int left, int right) {
        if (left == right) {
            tree[node] = createLeaf(nums[left]);
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid);
        build(node * 2 + 1, mid + 1, right);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // CREATE LEAF

    private Node createLeaf(int val) {
        Node node = new Node(k);
        int rem = val % k;

        // The only prefix is the element itself
        node.prefix[rem] = 1;

        // The only suffix is the element itself
        node.suffix[rem] = 1;

        // The only subarray is the element itself
        node.sub[rem] = 1;

        // Product of entire segment
        node.prod = rem;

        return node;
    }

    // MERGE

    private Node merge(Node left, Node right) {
        Node parent = new Node(k);

        // 1. PRODUCT
        parent.prod = (left.prod * right.prod) % k;

        // 2. PREFIX
        // Prefix completely inside LEFT
        for (int r = 0; r < k; r++)
            parent.prefix[r] += left.prefix[r];

        // Entire LEFT + prefix of RIGHT
        for (int r = 0; r < k; r++) {
            if (right.prefix[r] == 0)
                continue;

            int newRem = (left.prod * r) % k;
            parent.prefix[newRem] += right.prefix[r];
        }

        // 3. SUFFIX
        // Suffix completely inside RIGHT
        for (int r = 0; r < k; r++)
            parent.suffix[r] += right.suffix[r];

        // Suffix of LEFT + entire RIGHT
        for (int r = 0; r < k; r++) {
            if (left.suffix[r] == 0)
                continue;

            int newRem = (r * right.prod) % k;
            parent.suffix[newRem] += left.suffix[r];
        }

        // 4. ALL SUBARRAYS
        // Subarrays completely inside LEFT
        for (int r = 0; r < k; r++)
            parent.sub[r] += left.sub[r];

        // Subarrays completely inside RIGHT
        for (int r = 0; r < k; r++)
            parent.sub[r] += right.sub[r];

        // Cross-boundary subarrays: suffix of LEFT + prefix of RIGHT
        for (int a = 0; a < k; a++) {
            if (left.suffix[a] == 0)
                continue;

            for (int b = 0; b < k; b++) {
                if (right.prefix[b] == 0)
                    continue;

                int newRem = (a * b) % k;
                parent.sub[newRem] += left.suffix[a] * right.prefix[b];
            }
        }

        return parent;
    }

    // UPDATE

    private void update(int node, int left, int right, int idx, int val) {
        if (left == right) {
            tree[node] = createLeaf(val);
            return;
        }

        int mid = left + (right - left) / 2;
        if (idx <= mid)
            update(node * 2, left, mid, idx, val);
        
        else
            update(node * 2 + 1, mid + 1, right, idx, val);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // RANGE QUERY

    private Node query(int node, int left, int right, int queryLeft, int queryRight) {
        // Completely outside
        if (right < queryLeft || left > queryRight)
            return null;

        // Completely inside
        if (queryLeft <= left && right <= queryRight)
            return tree[node];

        int mid = left + (right - left) / 2;
        
        Node leftNode = query(node * 2, left, mid, queryLeft, queryRight);
        Node rightNode = query(node * 2 + 1, mid + 1, right, queryLeft, queryRight);

        // Only right exists
        if (leftNode == null)
            return rightNode;

        // Only left exists
        if (rightNode == null)
            return leftNode;

        // Both exist
        return merge(leftNode, rightNode);
    }
}
