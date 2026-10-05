class Solution {
    public int findLHS(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // Counting the frequency of every number
        for(int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);

        int ans = 0;
        // Checking every number with number + 1
        for(int num : map.keySet()) {
            if(map.containsKey(num + 1))
                ans = Math.max(ans, map.get(num) + map.get(num + 1));
        }

        return ans;
    }
}
