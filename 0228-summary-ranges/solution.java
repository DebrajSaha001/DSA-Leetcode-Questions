class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();

        if(nums.length == 0)
            return res;

        int start = nums[0];

        for(int i = 1; i < nums.length; i++) {
            // Current number is not consecutive
            if(nums[i] != nums[i - 1] + 1) {
                // Finishing the current range
                if(start == nums[i - 1])
                    res.add(String.valueOf(start));
                else
                    res.add(start + "->" + nums[i - 1]);

                // Starting a new range
                start = nums[i];
            }
        }

        // Adding the final range
        int end = nums[nums.length - 1];

        if(start == end)
            res.add(String.valueOf(start));
        else
            res.add(start + "->" + end);

        return res;
    }
}
