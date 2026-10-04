class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                low++;
                high++;
            }

            else if(ch == ')') {
                low--;
                high--;
            }

            else { // '*'
                low--;
                high++;
            }

            // No possible balance exists
            if(high < 0)
                return false;

            // Minimum balance cannot be negative
            low = Math.max(0, low);
        }

        // We need to be able to finish with balance  0
        return low == 0;
    }
}

