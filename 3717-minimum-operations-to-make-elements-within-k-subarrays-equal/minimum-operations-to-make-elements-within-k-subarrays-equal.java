class Solution {
    class MedianWindow {
        TreeMap<Integer, Integer> left = new TreeMap<>();
        TreeMap<Integer, Integer> right =  new TreeMap<>();

        long leftSum = 0;
        long rightSum = 0;
        int leftSize = 0;
        int rightSize = 0;

        void add(int num) {
            if(left.isEmpty() || num <= left.lastKey())
                addLeft(num);

            else
                addRight(num);

            balance();
        }

        void remove(int num) {
            if(left.containsKey(num))
                removeLeft(num);

            else
                removeRight(num);

            balance();
        }

        void addLeft(int num) {
            left.put(num, left.getOrDefault(num, 0) + 1);
            leftSum += num;
            leftSize++;
        }

        void addRight(int num) {
            right.put(num, right.getOrDefault(num, 0) + 1);
            rightSum += num;
            rightSize++;
        }

        void removeLeft(int num) {
            decrease(left, num);
            leftSum -= num;
            leftSize--;
        }

        void removeRight(int num) {
            decrease(right, num);
            rightSum -= num;
            rightSize--;
        }

        void decrease(TreeMap<Integer, Integer> map, int num) {
            int count = map.get(num);

            if(count == 1) 
                map.remove(num);
            
            else
                map.put(num, count - 1);
        }

        void balance() {
            while(leftSize > rightSize + 1) {
                int num = left.lastKey();
                removeLeft(num);
                addRight(num);
            }

            while(leftSize < rightSize) {
                int num = right.firstKey();
                removeRight(num);
                addLeft(num);
            }
        }

        long getCost() {
            long median = left.lastKey();

            return median * leftSize - leftSum + rightSum - median * rightSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // Step 1: Calculating the cost of every window of size x
        long cost[] = new long[n - x + 1];
        MedianWindow window = new MedianWindow();

        for(int i = 0; i < x; i++)
            window.add(nums[i]);

        cost[0] = window.getCost();

        for(int i = x; i < n; i++) {
            window.remove(nums[i - x]);
            window.add(nums[i]);

            cost[i - x + 1] = window.getCost();
        }

        // Step 2: DP to select k non-overlapping windows
        long INF = Long.MAX_VALUE / 4;
        long dp[][] = new long[k + 1][n + 1];

        for(int j = 1; j <= k; j++)
            Arrays.fill(dp[j], INF);

        for(int j = 1; j <= k; j++) {
            for(int i = 1; i <= n; i++) {
                // Choice 1: Skip the current element
                dp[j][i] = dp[j][i - 1];

                // Choice 2: Select a window ending at i - 1
                if(i >= x && dp[j - 1][i - x] != INF) {
                    long take = dp[j - 1][i - x] + cost[i - x];
                    dp[j][i] = Math.min(dp[j][i], take);
                }
            }
        }

        return dp[k][n];
    }
}
