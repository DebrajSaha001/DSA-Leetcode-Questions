class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 0;

        // Finding the maximum pile
        for(int pile : piles)
            right = Math.max(right, pile);

        // Binary Searching for the minimum valid speed
        while(left <= right) {
            int mid = left + (right - left) / 2;
            long hours = 0;

            // Calculating the hours needed at the mid speed
            for(int pile : piles)
                hours += (pile + (long) mid - 1) / mid;
            
            if(hours <= h)
                // Speed is working, trying a smaller one
                right = mid - 1;

            else
                // Speed is too slow
                left = mid + 1;
        }

        return left;
    }
}