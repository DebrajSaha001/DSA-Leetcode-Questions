class Solution {
    public String removeOuterParentheses(String s) {
        StringBuilder res = new StringBuilder();
        int bal = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                // Keeping '(' only if it is not outermost
                if(bal > 0)
                    res.append(ch);

                bal++;
            }

            else {
                bal--;
                // Keeping ')' only if it is not outermost
                if(bal > 0)
                    res.append(ch);
            }
        }

        return res.toString();
    }
}
