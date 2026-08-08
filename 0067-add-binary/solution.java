class Solution {
    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();

        int m = a.length() - 1;
        int n = b.length() - 1;
        int carry = 0;

        while(m >= 0 || n >= 0 || carry == 1) {
            int sum = carry;

            // a bit pe add
            if(m >= 0) {
                sum += a.charAt(m) - '0';
                m--;
            }

            // b bit pe add
            if(n >= 0) {
                sum += b.charAt(n) - '0';
                n--;
            }

            // Current bit ko store kare
            ans.append(sum % 2);

            // carry ko update kare
            carry = sum / 2;
        }

        // ans ko ulta karke likhte haii
        return ans.reverse().toString();
    }
}
