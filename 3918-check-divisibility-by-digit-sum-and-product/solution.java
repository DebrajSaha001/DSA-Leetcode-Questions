class Solution {
    public boolean checkDivisibility(int n) {
        int ogNum = n;
        int sum = 0;
        int prod = 1;

        while(n > 0) {
            int dig = n % 10;
            sum += dig;
            prod *= dig;
            n /= 10;
        }
        int tot = sum + prod;
        return ogNum % tot == 0;
    }
}
