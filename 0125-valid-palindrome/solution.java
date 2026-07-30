class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder clean = new StringBuilder();
        for(char ch : s.toCharArray()) {
            if(Character.isLetterOrDigit(ch)) 
                clean.append(Character.toLowerCase(ch));
        }

        int left = 0;
        int right = clean.length() - 1;

        while(left < right) {
            if(clean.charAt(left) != clean.charAt(right))
                return false;

            left++;
            right--;
        }

        return true;
    }
}
