class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int MAX = 2048;

        boolean one[] = new boolean[MAX];
        boolean two[] = new boolean[MAX];
        boolean three[] = new boolean[MAX];

        for (int num : nums) {
            one[num] = true;

            for (int i = 0; i < MAX; i++) {
                if (one[i]) {
                    two[i ^ num] = true;
                }
            }

            for (int i = 0; i < MAX; i++) {
                if (two[i]) {
                    three[i ^ num] = true;
                }
            }
        }

        int count = 0;

        for (boolean possible : three) {
            if (possible) {
                count++;
            }
        }

        return count;
    }
}
