class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[] sum, square, lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;
        sum = new long[4 * n];
        square = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            update(1, 0, n - 1, last[x] + 1, i);

            ans = (ans + square[1]) % MOD;
            last[x] = i;
        }

        return (int) ans;
    }

    private void apply(int node, int start, int end, long val) {
        long len = end - start + 1L;

        square[node] += 2L * val * sum[node] + val * val * len;
        sum[node] += val * len;
        lazy[node] += val;
    }

    private void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) return;

        int mid = (start + end) >>> 1;
        long val = lazy[node];

        apply(node * 2, start, mid, val);
        apply(node * 2 + 1, mid + 1, end, val);

        lazy[node] = 0;
    }

    private void update(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return;

        if (left <= start && end <= right) {
            apply(node, start, end, 1);
            return;
        }

        push(node, start, end);
        int mid = (start + end) >>> 1;

        update(node * 2, start, mid, left, right);
        update(node * 2 + 1, mid + 1, end, left, right);

        sum[node] = sum[node * 2] + sum[node * 2 + 1];
        square[node] = square[node * 2] + square[node * 2 + 1];
    }
}
