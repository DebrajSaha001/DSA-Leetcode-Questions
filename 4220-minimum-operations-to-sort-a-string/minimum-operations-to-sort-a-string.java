class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i - 1) > s.charAt(i)) {
                sorted = false;
                break;
            }
        }

        if (sorted)
            return 0;

        if (n == 2)
            return -1;

        char minChar = 'z';
        char maxChar = 'a';

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            minChar = (char) Math.min(ch, minChar);
            maxChar = (char) Math.max(ch, maxChar);
        }

        char first = s.charAt(0);
        char last = s.charAt(n - 1);

        if (first == minChar || last == maxChar)
            return 1;

        if (first == maxChar && last == minChar) {
            for (int i = 1; i < n - 1; i++) {
                char c = s.charAt(i);
                if (c == minChar || c == maxChar)
                    return 2;
            }
            return 3;
        }

        return 2;
    }
}
