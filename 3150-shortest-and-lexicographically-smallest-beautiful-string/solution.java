class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int left = 0;
        int countOnes = 0;
        String ans = "";

        for(int right = 0; right < s.length(); right++) {
            // Adding a current character
            if(s.charAt(right) == '1')
                countOnes++;

            // Shrinking from left for too many 1s
            while(countOnes > k) {
                if(s.charAt(left) == '1')
                    countOnes--;

                left++;
            }

            // We are having exactly k number of ones
            if(countOnes == k) {
                // Removing unnecessary leading zeros
                while(left <= right && s.charAt(left) == '0')
                    left++;

                String can = s.substring(left, right + 1);

                // First validating candidate
                if(ans.equals(""))
                    ans = can;

                // Shorter candidate
                else if(can.length() < ans.length())
                    ans = can;

                // Same len, lexicographically smaller
                else if(can.length() == ans.length() && can.compareTo(ans) < 0)
                    ans = can;
            }
        }

        return ans;
    }
}
