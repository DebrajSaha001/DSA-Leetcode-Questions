class Solution {
    public int myAtoi(String s) {
        int i = 0;
        int n = s.length();

        // ignoring the leading spaces
        while(i < n && s.charAt(i) == ' ')
            i++;

        // determining the sign
        int sign = 1;
        if(i < n && s.charAt(i) == '-') {
            sign = -1;
            i++;
        }

        else if(i < n && s.charAt(i) == '+')
            i++;

        // converting the digits
        int result = 0;
        while(i < n && Character.isDigit(s.charAt(i))) {
            int dig = s.charAt(i) - '0';

            // checking the overflow before adding the dig
            if(result > (Integer.MAX_VALUE - dig) / 10) {
                if(sign == 1)
                    return Integer.MAX_VALUE;
                else
                    return Integer.MIN_VALUE;
            }
            result = result * 10 + dig;
            i++;
        }
        return result * sign;
    }
}

