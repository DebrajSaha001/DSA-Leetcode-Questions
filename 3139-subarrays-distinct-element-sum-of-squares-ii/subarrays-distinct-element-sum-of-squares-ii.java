class Solution {
    static final long MOD = 1_000_000_007L;

    int size, log;
    long[] sum, sq, lazy;
    int[] len;

    void apply(int p, long v) {
        sq[p] += 2L * v * sum[p] + v * v * len[p];
        sum[p] += v * len[p];
        lazy[p] += v;
    }

    void push(int p) {
        if (lazy[p] != 0) {
            apply(p << 1, lazy[p]);
            apply(p << 1 | 1, lazy[p]);
            lazy[p] = 0;
        }
    }

    void pull(int p) {
        sum[p] = sum[p << 1] + sum[p << 1 | 1];
        sq[p] = sq[p << 1] + sq[p << 1 | 1];
    }

    void update(int l, int r) {
        if (l >= r) return;

        int left = l + size;
        int right = r + size;
        int l0 = left, r0 = right;

        for (int i = log; i > 0; i--) {
            if (((left >> i) << i) != left)
                push(left >> i);

            if (((right >> i) << i) != right)
                push((right - 1) >> i);
        }

        while (left < right) {
            if ((left & 1) != 0)
                apply(left++, 1);

            if ((right & 1) != 0)
                apply(--right, 1);

            left >>= 1;
            right >>= 1;
        }

        for (int i = 1; i <= log; i++) {
            if (((l0 >> i) << i) != l0)
                pull(l0 >> i);

            if (((r0 >> i) << i) != r0)
                pull((r0 - 1) >> i);
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;

        size = 1;
        log = 0;
        while (size < n) {
            size <<= 1;
            log++;
        }

        sum = new long[size << 1];
        sq = new long[size << 1];
        lazy = new long[size << 1];
        len = new int[size << 1];

        for (int i = 0; i < n; i++)
            len[size + i] = 1;

        for (int i = size - 1; i > 0; i--)
            len[i] = len[i << 1] + len[i << 1 | 1];

        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            update(last[x] + 1, i + 1);

            ans = (ans + sq[1]) % MOD;
            last[x] = i;
        }

        return (int) ans;
    }
}