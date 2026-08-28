class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        // Count frequency of characters in s
        int[] freq = new int[26];

        for (char ch : s.toCharArray())
            freq[ch - 'a']++;

        char[] result = new char[n];
        int i = 0;
        // Try to match target from left to right
        while (i < n) {
            int current = target.charAt(i) - 'a';
            if (freq[current] > 0) {
                result[i] = target.charAt(i);
                freq[current]--;
                i++;
            } else
                break;
        }

        // Case 1: We got stuck at position i
        if (i < n) {
            int current = target.charAt(i) - 'a';
            // Find smallest available character greater than target[i]
            for (int c = current + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    result[i] = (char) ('a' + c);
                    freq[c]--;
                    fillRemaining(result, i + 1, freq);
                    return new String(result);
                }
            }
        }

        // Case 2: Go backwards and try increasing an earlier position
        for (int pos = Math.min(i - 1, n - 1); pos >= 0; pos--) {
            // Restore the character previously used
            freq[result[pos] - 'a']++;
            int current = target.charAt(pos) - 'a';
            // Find smallest available character greater than target[pos]
            for (int c = current + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    result[pos] = (char) ('a' + c);
                    freq[c]--;
                    // Fill remaining positions in sorted order
                    fillRemaining(result, pos + 1, freq);
                    return new String(result);
                }
            }
        }
        return "";
    }

    private void fillRemaining(char[] result, int start, int[] freq) {
        int index = start;
        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                result[index++] = (char) ('a' + c);
                freq[c]--;
            }
        }
    }
}

