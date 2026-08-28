class Solution {

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Check if palindrome is possible
        int oddCount = 0;
        char middle = '\0';

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                oddCount++;
                middle = (char) ('a' + i);
            }
        }

        if (oddCount > 1) {
            return "";
        }

        // Characters available for the left half
        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        String targetLeft = target.substring(0, half);

        // Find smallest permutation >= targetLeft
        char[] left = smallestGreaterOrEqual(halfFreq, targetLeft);

        if (left == null) {
            return "";
        }

        String candidate =
                buildPalindrome(left, middle, oddCount == 1);

        // If strictly greater, we are done
        if (candidate.compareTo(target) > 0) {
            return candidate;
        }

        // Otherwise find the next left-half permutation
        if (!nextPermutation(left)) {
            return "";
        }

        candidate =
                buildPalindrome(left, middle, oddCount == 1);

        return candidate;
    }


    private char[] smallestGreaterOrEqual(int[] originalFreq,
                                          String bound) {

        int n = bound.length();

        char[] result = new char[n];
        int[] freq = originalFreq.clone();

        int i = 0;

        /*
         * Try matching the bound from left to right.
         */
        for (; i < n; i++) {
            int ch = bound.charAt(i) - 'a';

            if (freq[ch] > 0) {
                result[i] = bound.charAt(i);
                freq[ch]--;
            } else {
                break;
            }
        }

        // Exact match
        if (i == n) {
            return result;
        }

        /*
         * At position i, try to put a character
         * greater than bound[i].
         */
        int current = bound.charAt(i) - 'a';

        for (int c = current + 1; c < 26; c++) {
            if (freq[c] > 0) {
                result[i] = (char) ('a' + c);
                freq[c]--;

                fillSmallest(result, i + 1, freq);

                return result;
            }
        }

        /*
         * Cannot increase at position i.
         * Go backwards and try increasing an earlier position.
         */
        for (int pos = i - 1; pos >= 0; pos--) {

            // Restore character at this position
            freq[result[pos] - 'a']++;

            int boundChar = bound.charAt(pos) - 'a';

            for (int c = boundChar + 1; c < 26; c++) {
                if (freq[c] > 0) {

                    result[pos] = (char) ('a' + c);
                    freq[c]--;

                    fillSmallest(result, pos + 1, freq);

                    return result;
                }
            }
        }

        return null;
    }


    private void fillSmallest(char[] result,
                              int start,
                              int[] freq) {

        int index = start;

        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                result[index++] = (char) ('a' + c);
                freq[c]--;
            }
        }
    }


    private String buildPalindrome(char[] left,
                                   char middle,
                                   boolean hasMiddle) {

        StringBuilder result = new StringBuilder();

        for (char ch : left) {
            result.append(ch);
        }

        if (hasMiddle) {
            result.append(middle);
        }

        for (int i = left.length - 1; i >= 0; i--) {
            result.append(left[i]);
        }

        return result.toString();
    }


    private boolean nextPermutation(char[] arr) {
        int i = arr.length - 2;

        // Find first decreasing position
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = arr.length - 1;

        while (arr[j] <= arr[i]) {
            j--;
        }

        // Swap
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        // Reverse remaining part
        reverse(arr, i + 1, arr.length - 1);

        return true;
    }


    private void reverse(char[] arr, int left, int right) {

        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }
}
