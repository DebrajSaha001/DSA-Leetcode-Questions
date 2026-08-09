class Solution {
    public int longestPalindrome(String s) {
        int count[] = new int[128];

        for(char ch : s.toCharArray())
            count[ch]++;

        int len = 0;
        boolean odd = false;

        for(int freq : count) {
            if(freq % 2 == 0)
                len += freq;

            else {
                len += freq - 1;
                odd = true;
            }
        }

        if(odd)
            len++;

        return len;
    }
}
