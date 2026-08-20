class Solution {
    public int[] resultArray(int[] nums) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        // 0th index element is added in the arr1
        arr1.add(nums[0]);
        // 1st index element is added in the arr2
        arr2.add(nums[1]);
        // for the remaining elements
        for(int i = 2; i < nums.length; i++) {
            int last1 = arr1.get(arr1.size() - 1);
            int last2 = arr2.get(arr2.size() - 1);

            if(last1 > last2)
                arr1.add(nums[i]);
            else
                arr2.add(nums[i]);
        }

        // creating the result array
        int result[] = new int[nums.length];
        int index = 0;

        for(int num : arr1)
            result[index++] = num;

        for(int num : arr2)
            result[index++] = num;

        return result;
    }
}
