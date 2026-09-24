class Solution {
    public int divisibleGame(int[] nums) {
        final int MOD = 1_000_000_007;
        int n = nums.length;

        long[] prefix = new long[n + 1];
        int maxNum = 0;
        int minNum = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
            maxNum = Math.max(maxNum, nums[i]);
            minNum = Math.min(minNum, nums[i]);
        }

        int[] head = new int[maxNum + 1];
        Arrays.fill(head, -1);

        int maxEntries = 2 * n * 400;
        int[] next = new int[maxEntries];
        int[] position = new int[maxEntries];

        int size = 0;

        // Find positions for every divisor > 1
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            for (int d = 2; d * d <= num; d++) {
                if (num % d == 0) {
                    position[size] = i;
                    next[size] = head[d];
                    head[d] = size++;

                    int other = num / d;

                    if (other != d) {
                        position[size] = i;
                        next[size] = head[other];
                        head[other] = size++;
                    }
                }
            }

            // num itself is a divisor
            if (num > 1) {
                position[size] = i;
                next[size] = head[num];
                head[num] = size++;
            }
        }

        // k = 2 is ALWAYS possible. If no number is divisible by 2, every element is negative, so the best score is simply -minimum element.
        long bestScore = -minNum;
        int bestK = 2;

        // Try all k that actually divide at least one element
        for (int k = 2; k <= maxNum; k++) {
            if (head[k] == -1)
                continue;

            long current = Long.MIN_VALUE;
            long best = Long.MIN_VALUE;
            int previous = -1;

            for (int node = head[k]; node != -1; node = next[node]) {
                int index = position[node];
                if (previous == -1)
                    current = nums[index];
                
                else {
                    // Sum of elements between previous and current
                    long gapSum = prefix[index] - prefix[previous + 1];
                    current = Math.max(nums[index], current - gapSum + nums[index]);
                }

                best = Math.max(best, current);
                previous = index;
            }

            if (best > bestScore || (best == bestScore && k < bestK)) {
                bestScore = best;
                bestK = k;
            }
        }

        long answer = (bestScore % MOD) * bestK % MOD;
        if (answer < 0)
            answer += MOD;

        return (int) answer;
    }
}
