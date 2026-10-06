class Solution {
    public int minAddToMakeValid(String s) {
        int bal = 0;
        int ans = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '(')
                bal++;

            else {
                if(bal > 0)
                    bal--;

                else
                    ans++;
            }
        }

        ans += bal;
        return ans;
    }
}