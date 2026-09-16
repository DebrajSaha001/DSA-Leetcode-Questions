class Solution {
    static final long MOD = 1000000007L;
    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;

        long fact[] = new long[N + 1];
        fact[0] = 1;

        for(int i = 1; i <= N; i++)
            fact[i] = fact[i - 1] * i % MOD;

        long numerator = fact[N];
        long denominator1 = power(fact[R], MOD - 2);
        long denominator2 = power(fact[N - R], MOD - 2);
        long ans = numerator;
        ans = ans * denominator1 % MOD;
        ans = ans * denominator2 % MOD;

        return (int) ans;
    }

    private long power(long base, long exponent) {
        long res = 1;
        while(exponent > 0) {
            if((exponent & 1) == 1)
                res = res * base % MOD;

            base = base * base % MOD;
            exponent >>= 1;
        }
        return res;
    }
}
