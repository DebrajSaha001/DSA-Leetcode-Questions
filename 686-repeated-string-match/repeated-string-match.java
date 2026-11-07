class Solution {
    public int repeatedStringMatch(String a, String b) {
        boolean[] bucket = new boolean[26];
        for (char ch : a.toCharArray())
            bucket[ch - 'a'] = true;

        for (char ch : b.toCharArray()) {
            if (!bucket[ch - 'a'])
                return -1;
        }

        int count = (b.length() + a.length() - 1) / a.length();
        StringBuilder sb = new StringBuilder(a.repeat(count));

        if (sb.indexOf(b) >= 0)
            return count;

        sb.append(a);
        if (sb.indexOf(b) >= 0)
            return count + 1;

        return -1;
    }
}