class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][]arr = new int[n][3];

        for(int i = 0; i < nums.length; i++) {
            arr[i][0] = digitSum(nums[i]);
            arr[i][1] = nums[i];
            arr[i][2] = i;
        }

        boolean visited[] = new boolean[n];

        Arrays.sort(arr, (a, b)->{
            if(a[0] != b[0])
                return a[0] - b[0];

            return a[1] - b[1];
        });

        int swaps = 0;

        for(int i = 0; i < n; i++){
            if(visited[i] || arr[i][2] == i)
                continue;

            int j = i;
            int cycleSize = 0;

            while(!visited[j]) {
                visited[j] = true;
                j = arr[j][2];
                cycleSize++;
            }
            swaps += cycleSize - 1;
        }

        return swaps;
    }

    private int digitSum(int num) {
        int sum = 0;

        while(num > 0) {
            int rem = num % 10;
            sum += rem;
            num = num / 10;
        }

        return sum;
    }
}