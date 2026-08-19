class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // store reserved seats for each row
        for(int seats[] : reservedSeats) {
            int row = seats[0];
            int col = seats[1];
            
            map.put(row, map.getOrDefault(row, 0) | (1 << col));
        }
        // seats 2, 3, 4, 5
        int left = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5);
        // seats 4, 5, 6, 7
        int middle = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7);
        // seats 6, 7, 8, 9
        int right = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9);
        // rows with no reserved seats can reserve a group of 2s
        int ans = (n - map.size()) * 2;
        // process only rows having reserved seats
        for(int mask : map.values()) {
            boolean leftFree = (mask & left) == 0;
            boolean middleFree = (mask & middle) == 0;
            boolean rightFree = (mask & right) == 0;
            // both non-overlapping blocks can be used
            if(leftFree && rightFree)
                ans += 2;
            // atleast one block can be used
            else if(leftFree || middleFree || rightFree)
                ans += 1;
        }

        return ans;
    }
}
