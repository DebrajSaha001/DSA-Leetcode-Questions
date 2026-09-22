class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length;
        int left = 0;
        int right = n - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;
            int papers = n - mid;

            if(citations[mid] >= papers)
                // Possible ans. Trying to find a larger h on the left
                right = mid - 1;

            else
                // Not enough citations. Need fewer papers, so move right
                left = mid + 1; 
        }

        return n - left;
    }
}
