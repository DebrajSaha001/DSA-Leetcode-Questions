class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int num : nums) {
            int len1 = dp.getOrDefault(num, 0) + 1;
            int len2 = dp.getOrDefault(num + 1, 0) + 1;

            dp.put(num + 1, Math.max(dp.getOrDefault(num + 1, 0), len1));
            dp.put(num + 2, Math.max(dp.getOrDefault(num + 2, 0), len2));

            ans = Math.max(ans, Math.max(len1, len2));
        }

        return ans;
    }
}