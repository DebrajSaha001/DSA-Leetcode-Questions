class Solution {
    public String minWindow(String s, String t) {
        int need[] = new int[128];
        int window[] = new int[128];

        // Counting the characters by t
        for(char ch : t.toCharArray())
            need[ch]++;

        int required = 0;

        // Number of different characters we need
        for(int i = 0; i < 128; i++) {
            if(need[i] > 0)
                required++;
        }

        int formed = 0;
        int left = 0;
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for(int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            window[ch]++;

            // This character requirement has been justified
            if(need[ch] > 0 && window[ch] == need[ch]) 
                formed++;

            // Window is valid
            while(formed == required) {
                // Updating minimum window
                if(right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char leftChar = s.charAt(left);
                window[leftChar]--;

                // Removing this character makes the window invalid
                if(need[leftChar] > 0 && window[leftChar] < need[leftChar])
                    formed--;

                left++;
            }
        }

        if(minLen == Integer.MAX_VALUE)
            return "";

        return s.substring(start, start + minLen);
    }
}

