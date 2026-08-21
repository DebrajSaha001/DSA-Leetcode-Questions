class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int totalMasks = 1 << n;

        long[] lcms = new long[totalMasks];
        int[] signs = new int[totalMasks];

        // Precompute LCM and sign for every subset
        for (int mask = 1; mask < totalMasks; mask++) {
            int bit = mask & -mask;
            int index = Integer.numberOfTrailingZeros(bit);
            int previousMask = mask ^ bit;

            if (previousMask == 0) {
                lcms[mask] = coins[index];
                signs[mask] = 1;
            } else {
                long value = lcm(lcms[previousMask], coins[index]);
                lcms[mask] = value;
                int bits = Integer.bitCount(mask);

                if (bits % 2 == 1)
                    signs[mask] = 1;
                else
                    signs[mask] = -1;
            }
        }
        int minCoin = Integer.MAX_VALUE;

        for (int coin : coins)
            minCoin = Math.min(minCoin, coin);

        long low = 1;
        long high = (long) minCoin * k;

        while (low < high) {
            long mid = low + (high - low) / 2;
            long count = countAmounts(lcms, signs, mid);

            if (count >= k) 
                high = mid;
            else
                low = mid + 1;
        }
        return low;
    }

    private long countAmounts(long[] lcms, int[] signs, long x) {
        long count = 0;
        for (int mask = 1; mask < lcms.length; mask++) {
            long lcm = lcms[mask];
            if (lcm > x)
                continue;

            count += signs[mask] * (x / lcm);
        }
        return count;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}

