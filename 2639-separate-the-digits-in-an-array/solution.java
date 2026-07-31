class Solution {
    public int[] separateDigits(int[] nums) {
        int length = 0;
        for(int num : nums){
            int a = num;
            while(a > 0){
                length++;
                a /= 10;
            }
        }
        int[] ans = new int[length];
        int idx = length - 1;
        length = nums.length - 1;

        while(length >= 0){
            int num = nums[length];
            while(num > 0){
                ans[idx--] = num % 10;
                num /= 10;
            }
            length--;
        }          
        return ans;
    }
}

// class Solution {
//     public int[] separateDigits(int[] nums) {
//         List<Integer> ans = new ArrayList<>();
        
//         for(int num : nums) {
//             List<Integer> temp = new ArrayList<>();
//             while(num > 0) {
//                 temp.add(num % 10);
//                 num /= 10;
//             }

//             for(int j = temp.size() - 1; j >= 0; j--) {
//                 ans.add(temp.get(j));
//             }
//         }

//         int res[] = new int[ans.size()];
//         for(int i = 0; i < ans.size(); i++) {
//             res[i] = ans.get(i);
//         }

//         return res;
//     }
// }
