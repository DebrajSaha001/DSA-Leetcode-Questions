class Solution {
    public String multiply(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0"))
            return "0";

        int n = num1.length();
        int m = num2.length();
        int result[] = new int[m + n];

        for(int i = n - 1; i >= 0; i--) {
            for(int j = m - 1; j >= 0; j--) {
                int dig1 = num1.charAt(i) - '0';
                int dig2 = num2.charAt(j) - '0';
                int prod = dig1 * dig2;

                int pos1 = i + j;
                int pos2 = i + j + 1;
                int sum = prod + result[pos2];

                result[pos2] = sum % 10;
                result[pos1] += sum / 10;
            }
        }

        StringBuilder ans = new StringBuilder();
        for(int dig : result) {
            if(ans.length() == 0 && dig == 0)
                continue;

            ans.append(dig);
        }

        return ans.toString();
    }
}
