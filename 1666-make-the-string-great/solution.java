class Solution {
    public String makeGood(String s) {
        StringBuilder res = new StringBuilder();
        for(char ch : s.toCharArray()) {
            if(res.length() > 0) {
                int last = res.charAt(res.length() - 1);
                if(last + 32 == ch || last - 32 == ch) {
                    res.deleteCharAt(res.length() - 1);
                    continue;
                }
            }
            res.append(ch);
        }
        return res.toString();
    }
}
