class Solution {
    public int minInsertions(String s) {
        int need = 0;
        int ins = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                need += 2;

                // Required for closing parentheses must come in pairs
                if(need % 2 == 1) {
                    ins++;
                    need--;
                }
            }

            else {
                need--;

                // No opening parentheses is available
                if(need < 0) {
                    ins++;
                    need = 1;
                }
            }
        }

        return ins + need;
    }
}