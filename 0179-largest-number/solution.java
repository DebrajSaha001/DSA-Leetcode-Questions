class Solution {
    public String largestNumber(int[] nums) {
        String arr[] = new String[nums.length];

        // Converting the numbers to string
        for(int i = 0; i < nums.length; i++)
            arr[i] = String.valueOf(nums[i]);

        // Custom sorting
        Arrays.sort(arr, (a, b) ->
            (b + a).compareTo(a + b)
        );

        // All numbers are zero
        if(arr[0].equals("0"))
            return "0";

        // Building the answer
        StringBuilder ans = new StringBuilder();

        for(String s : arr)
            ans.append(s);

        return ans.toString();
    }
}
