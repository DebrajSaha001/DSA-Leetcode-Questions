class Solution {
    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        int[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);
        int uniqueCount = 0;
        
        for (int value : sorted) {
            if (uniqueCount == 0 || sorted[uniqueCount - 1] != value)
                sorted[uniqueCount++] = value;
        }

        int[] compressed = new int[n];
        for (int i = 0; i < n; i++) {
            compressed[i] = java.util.Arrays.binarySearch(sorted, 0, uniqueCount, nums[i]) + 1;
        }

        Fenwick fenwick = new Fenwick(uniqueCount);
        long windowSum = 0;

        for (int i = 0; i < x; i++) {
            fenwick.add(compressed[i], nums[i], 1);
            windowSum += nums[i];
        }

        int windowCount = n - x + 1;
        long[] windowCost = new long[windowCount];

        for (int start = 0; start < windowCount; start++) {
            int medianIndex = fenwick.kth((x + 1) / 2);
            long median = sorted[medianIndex - 1];
            int leftCount = fenwick.countPrefix(medianIndex);
            long leftSum = fenwick.sumPrefix(medianIndex);
            int rightCount = x - leftCount;
            long rightSum = windowSum - leftSum;

            windowCost[start] = median * leftCount - leftSum + rightSum - median * rightCount;

            if (start + x < n) {
                fenwick.add(compressed[start], nums[start], -1);
                fenwick.add(compressed[start + x], nums[start + x], 1);
                windowSum += (long) nums[start + x] - nums[start];
            }
        }

        long infinity = Long.MAX_VALUE / 4;
        long[] previous = new long[n + 1];
        Arrays.fill(previous, 0);

        for (int segments = 1; segments <= k; segments++) {
            long[] current = new long[n + 1];
            Arrays.fill(current, infinity);

            for (int end = 1; end <= n; end++) {
                current[end] = current[end - 1];
                
                if (end >= x && previous[end - x] != infinity) {
                    current[end] = Math.min(current[end], previous[end - x] + windowCost[end - x]);
                }
            }
            previous = current;
        }

        return previous[n];
    }

    private static class Fenwick {
        private final int[] counts;
        private final long[] sums;
        private final int size;

        Fenwick(int size) {
            this.size = size;
            counts = new int[size + 1];
            sums = new long[size + 1];
        }

        void add(int index, int value, int delta) {
            for (int i = index; i <= size; i += i & -i) {
                counts[i] += delta;
                sums[i] += (long) value * delta;
            }
        }

        int countPrefix(int index) {
            int result = 0;
            for (int i = index; i > 0; i -= i & -i) result += counts[i];
            
            return result;
        }

        long sumPrefix(int index) {
            long result = 0;
            for (int i = index; i > 0; i -= i & -i) result += sums[i];
            
            return result;
        }

        int kth(int order) {
            int index = 0;

            for (int bit = Integer.highestOneBit(size); bit > 0; bit >>= 1) {
                int next = index + bit;
                if (next <= size && counts[next] < order) {
                    index = next;
                    order -= counts[next];
                }
            }

            return index + 1;
        }
    }
}
