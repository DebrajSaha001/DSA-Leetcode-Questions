class Solution {
    public int romanToInt(String s) {
        int res = 0;

        for(int i = 0; i < s.length(); i++) {
            int cur = value(s.charAt(i));

            if(i+1 < s.length() && cur < value(s.charAt(i+1))) {
                res -= cur;
            }
            
            else {
                res += cur;
            }
        }

        return res;
    }

    public int value(char ch) {
        if(ch == 'I') return 1;
        else if(ch == 'V') return 5;
        else if(ch == 'X') return 10;
        else if(ch == 'L') return 50;
        else if(ch == 'C') return 100;
        else if(ch == 'D') return 500;
        else return 1000;
    }
}
