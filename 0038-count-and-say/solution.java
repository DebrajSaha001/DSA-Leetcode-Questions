class Solution {
    public String countAndSay(int n) {
        String result = "1";

        for (int i = 2; i <= n; i++)
            result = next(result);

        return result;
    }

    private String next(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        while (i < s.length()) {
            char ch = s.charAt(i);
            int count = 0;

            // Count consecutive same characters
            while (i < s.length() && s.charAt(i) == ch) {
                count++;
                i++;
            }

            // Append count followed by character
            sb.append(count);
            sb.append(ch);
        }
 
        return sb.toString();
    }
}
