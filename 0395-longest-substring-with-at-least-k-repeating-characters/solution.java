class Solution {
    public int longestSubstring(String s, int k) {
        return solve(s, 0, s.length() - 1, k);
    }

    private int solve(String s, int left, int right, int k) {
        if(right - left + 1 < k)
            return 0;

        int freq[] = new int[26];

        // Counting the frequencies
        for(int i = left; i <= right; i++)
            freq[s.charAt(i) - 'a']++;

        // Finding the character whose frequncy is less than k
        for(int i = left; i <= right; i++) {
            char ch = s.charAt(i);
            if(freq[ch - 'a'] < k) {
                int leftPart = solve(s, left, i - 1, k);
                int rightPart = solve(s, i + 1, right, k);
                
                return Math.max(leftPart, rightPart);
            }
        }

        // Every character occurs atleast k times
        return right - left + 1;
    }
}
