class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int left = 0;
        int right = 0;

        int leftQ = 0;
        int rightQ = 0;

        // for the left hand side;
        for(int i = 0; i < half; i++) {
            if(num.charAt(i) == '?')
                leftQ++;

            else
                left += num.charAt(i) - '0';
        }

        // for the right hand side
        for(int i = half; i < n; i++) {
            if(num.charAt(i) == '?')
                rightQ++;

            else
                right += num.charAt(i) - '0';
        }

        // unequal number of ? means Alice can force imbalance
        if((leftQ + rightQ) % 2 == 1)
            return true;

        // equal number of ? on both the sides
        int diff = left - right;
        int bal = 9 * (rightQ - leftQ) / 2;

        // if diff can be corrected, Bob wins
        return diff != bal;
    }
}
