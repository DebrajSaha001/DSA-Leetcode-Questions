class Solution {
    public boolean isHappy(int n) {
        while(n != 1 && n != 4)
            n = squareSum(n);

        return n == 1;
    }

    public int squareSum(int n) {
        int sum = 0;

        while(n>0) {
            int last = n % 10;
            sum += last * last;
            n /= 10;
        }

        return sum;
    }
}
