class Solution {
    public int lengthOfLongestSubstring(String s) {
        int freq[] = new int[128];
        int left = 0, maxLen = 0;

        for(int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            left = Math.max(left, freq[ch]);
            freq[ch] = right + 1;
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
