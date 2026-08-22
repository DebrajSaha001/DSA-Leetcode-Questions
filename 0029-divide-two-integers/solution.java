class Solution {
    public int divide(int dividend, int divisor) {
        // Overflow case
        if(dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        // Determining the sign of the number
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // Converting the positive numbers using long
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);
        long quotient = 0;

        // Subtracting the largest possible multiples of divisor
        while(a >= b) {
            long temp = b;
            long mul = 1;

            // Doubling the divisor untill it exceeds the dividend
            while(a >= (temp << 1)) {
                temp <<= 1;
                mul <<= 1;
            }

            // Removing the chunk
            a -= temp;
            quotient += mul;
        }

        // Sign applied
        if(negative)
            quotient = -quotient;

        return (int)quotient;
    }
}

