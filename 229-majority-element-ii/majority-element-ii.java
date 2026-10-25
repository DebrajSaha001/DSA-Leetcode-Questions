class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        int n = nums.length;

        for(int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);

        for(Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if(entry.getValue() > n / 3)
                res.add(entry.getKey());
        }

        return res;
    }
}
